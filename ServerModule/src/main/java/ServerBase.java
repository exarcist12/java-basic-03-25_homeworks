import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerBase {

    private int port;
    private List<ServerConnection> clients;

    public ServerBase(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true) {
                Socket client = serverSocket.accept();
                ServerConnection serverConnection = new ServerConnection(client, this);
                System.out.println("Клиент подключился с именем " + serverConnection.getName());
                subscribe(serverConnection);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribe(ServerConnection serverConnection) {
        clients.add(serverConnection);
    }

    public void unsubscribe(ServerConnection serverConnection) throws IOException {
        clients.remove(serverConnection);
        broadcastMessage("Клиент " + serverConnection.getName() + " вышел из чата", serverConnection);
        System.out.println("Клиент " + serverConnection.getName() + " вышел из чата");
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
                    .filter(p1 -> p1.getName().equals(name)).findFirst();

            if (optReceiver.isPresent()) {
                ServerConnection receiver = optReceiver.get();
                receiver.send("Вам пришло личное сообщение от " + sender.getName() + ": " + text + ". Напишите свой ответ:");
            } else {
                sender.send("Пользователь '" + name + "' не найден.");
            }
        } else {
            for (ServerConnection c : clients) {
                if (c != sender) {
                    c.send(sender.getName() + ": " + message);
                }
            }
        }
    }
}
