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
            for (ServerConnection c : clients) {
                if (c != sender) {
                    c.send(sender.getUsername() + ": " + message);
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


    public List<ServerConnection> getClients() {
        return clients;
    }
}
