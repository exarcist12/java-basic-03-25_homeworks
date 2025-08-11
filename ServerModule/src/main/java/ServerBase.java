import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerBase {

    private int port;
    private List<ServerConnection> clients;
    private AuthenticatedProvider authenticatedProvider;

    public ServerBase(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
        authenticatedProvider = new InMemoryAuthenticatedProvider(this);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            authenticatedProvider.initialize();
            ServerConnection serverConnection;
            while (true) {
                Socket client = serverSocket.accept();
                serverConnection = new ServerConnection(client, this);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribe(ServerConnection serverConnection) {
        clients.add(serverConnection);
        System.out.println("Клиент авторизован как: " + serverConnection.getUsername());
    }

    public void unsubscribe(ServerConnection serverConnection) throws IOException {
        clients.remove(serverConnection);
        broadcastMessage("Клиент " + serverConnection.getUsername() + " вышел из чата", serverConnection);
        System.out.println("Клиент " + serverConnection.getUsername() + " вышел из чата");
    }

    public void broadcastMessage(String message, ServerConnection sender) throws IOException {
        if (message.startsWith("/w")) {
            String[] tokens = message.split(" ", 3);
            if (tokens.length < 3) {
                sender.send("Формат: /w имя сообщение");
                return;
            }
            String name = tokens[1];
            String text = tokens[2];

            Optional<ServerConnection> optReceiver = clients.stream()
                    .filter(p1 -> p1.getUsername().equals(name)).findFirst();

            if (optReceiver.isPresent()) {
                ServerConnection receiver = optReceiver.get();
                receiver.send("Вам пришло личное сообщение от " + sender.getUsername() + ": " + text + ". Напишите свой ответ:");
            } else {
                sender.send("Пользователь '" + name + "' не найден.");
            }
        }else if (message.startsWith("/kick") && sender.getUsername().equals("admin")) {
            String[] tokens = message.split(" ", 2);
            if (tokens.length != 2) {
                sender.send("Формат: /kick имя сообщение");
                return;
            }
            String name = tokens[1];

            Optional<ServerConnection> optReceiver = clients.stream()
                    .filter(p1 -> p1.getUsername().equals(name)).findFirst();

            if (optReceiver.isPresent()) {
                ServerConnection receiver = optReceiver.get();
                receiver.send("Вас отключили от сервера");
                unsubscribe(receiver);
            } else {
                sender.send("Пользователь '" + name + "' не найден.");
            }
        }else {
            for (ServerConnection c : clients) {
                if (c != sender) {
                    c.send(sender.getUsername() + ": " + message);
                }
            }
        }
    }

    public boolean isUsernameBusy(String username) {
        for (ServerConnection c : clients) {
            if (c.getUsername() != null && c.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public AuthenticatedProvider getAuthenticatedProvider() {
        return authenticatedProvider;
    }
}
