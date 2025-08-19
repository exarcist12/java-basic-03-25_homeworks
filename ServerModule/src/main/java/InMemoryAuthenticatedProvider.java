import entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticatedProvider implements  AuthenticatedProvider{

    UserServiceImpl userService;

    {
        try {
            userService = new UserServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<User> users;
    private ServerBase server;

    public InMemoryAuthenticatedProvider(ServerBase server) {
        this.server = server;
        this.users = userService.getAllUsers();
    }



    private String getUsernameByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPasswordUser().equals(password)) {
                return u.getUsername();
            }
        }
        return null;
    }

    private String getRoleByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPasswordUser().equals(password)) {
                return u.getRole().getRoleName();
            }
        }
        return null;
    }

    private boolean isLoginAlreadyExist(String login) {
        for (User u : users) {
            if (u.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameAlreadyExist(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
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
        userService.addUser(User.builder()
                        .login(login)
                        .passwordUser(password)
                        .username(username)
                .build());
        users = userService.getAllUsers();
        serverConnection.setUsername(username);
        server.subscribe(serverConnection);
        serverConnection.send("/regok " + username);

        return true;
    }
}
