import java.io.*;
import java.net.Socket;

public class ServerConnection {
    private Socket socket;
    private ServerBase server;
    private DataInputStream in;
    private DataOutputStream out;

    private String clientName;

    public ServerConnection(Socket client, ServerBase server) throws IOException {
        this.socket = client;
        this.server = server;
        this.in = new DataInputStream(client.getInputStream());
        this.out = new DataOutputStream(client.getOutputStream());

        String firstMessage = getMessage();
        if (firstMessage.startsWith("Имя:")){
            this.clientName = firstMessage.split(" ", 2)[1];
        } else {
            this.clientName = "Гость";
        }

        new Thread(() -> {
            try {
                while (true) {
                    String message = getMessage();
                    System.out.println("Получено сообщение: " + message);
                    if (message.equalsIgnoreCase("exit")) {
                        server.unsubscribe(this);
                        break;
                    }
                    this.server.broadcastMessage(message, this);
                }
            } catch (IOException e) {
                try {
                    server.unsubscribe(this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } finally {
                close();
            }
        }).start();
    }

    public String getMessage() throws IOException {

            return in.readUTF();
    }

    public void send(String message) throws IOException {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.flush();
    }

    public String getName(){
        return this.clientName;
    }

    public void close() {
        try {
            in.close();
        } catch (IOException ignored) {}
        try {
            out.close();
        } catch (IOException ignored) {}
        try {
            socket.close();
        } catch (IOException ignored) {}
    }
}
