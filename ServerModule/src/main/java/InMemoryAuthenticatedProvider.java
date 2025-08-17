import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticatedProvider implements  AuthenticatedProvider{

    private class User {
        private String login;
        private String password;
        private String username;
        private String role;

        public User(String login, String password, String username, String role) {
            this.login = login;
            this.password = password;
            this.username = username;
            this.role = role;
        }
    }
    private List<User> users;
    private ServerBase server;

    public InMemoryAuthenticatedProvider(ServerBase server) {
        this.server = server;
        this.users = new CopyOnWriteArrayList<>();
        this.users.add(new User("qwe", "qwe", "qwe1", "user"));
        this.users.add(new User("asd", "asd", "asd1", "user"));
        this.users.add(new User("zxc", "zxc", "zxc1", "user"));
        this.users.add(new User("admin", "admin", "admin", "admin"));
    }



    private String getUsernameByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.login.equals(login) && u.password.equals(password)) {
                return u.username;
            }
        }
        return null;
    }

    private String getRoleByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.login.equals(login) && u.password.equals(password)) {
                return u.role;
            }
        }
        return null;
    }

    private boolean isLoginAlreadyExist(String login) {
        for (User u : users) {
            if (u.login.equals(login)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameAlreadyExist(String username) {
        for (User u : users) {
            if (u.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize() {
        System.out.println("AuthenticatedProvider запущен в режиме InMemory");
    }

    @Override
    public boolean authenticate(ServerConnection serverConnection, String login, String password) throws IOException {
        String authUsername = getUsernameByLoginAndPassword(login, password);
        String authRole = getRoleByLoginAndPassword(login, password);
        if (authUsername == null) {
            serverConnection.send("Некорректный логин / пароль");
            return false;
        }
        if (server.isUsernameBusy(authUsername)) {
            serverConnection.send("Указанная учетная запись уже занята");
            return false;
        }
        serverConnection.setUsername(authUsername);
        serverConnection.setRole(authRole);
        server.subscribe(serverConnection);
        serverConnection.send("/authok " + authUsername);
        return true;
    }

    @Override
    public boolean registration(ServerConnection serverConnection, String login, String password, String username) throws IOException {
        if (login.length() < 3) {
            serverConnection.send("Логин должен содержать 3+ символа");
            return false;
        }
        if (username.length() < 3) {
            serverConnection.send("Имя пользователя должна содержать 3+ символа");
            return false;
        }
        if (isLoginAlreadyExist(login)) {
            serverConnection.send("Указанный логин уже занят");
            return false;
        }
        if (isUsernameAlreadyExist(username)) {
            serverConnection.send("Указанное имя пользователя уже занято");
            return false;
        }
        users.add(new User(login, password, username, "user"));
        serverConnection.setUsername(username);
        server.subscribe(serverConnection);
        serverConnection.send("/regok " + username);

        return true;
    }
}
