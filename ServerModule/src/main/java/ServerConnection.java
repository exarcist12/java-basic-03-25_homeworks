import java.io.*;
import java.net.Socket;

public class ServerConnection {
    private Socket socket;
    private ServerBase server;
    private DataInputStream in;
    private DataOutputStream out;

    private boolean authenticated;
    private String username;

    public ServerConnection(Socket client, ServerBase server) throws IOException {
        this.socket = client;
        this.server = server;
        this.in = new DataInputStream(client.getInputStream());
        this.out = new DataOutputStream(client.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    send("Перед работой с чатом необходимо выполнить аутентификацию '/auth login password'" +
                            " или зарегистрироваться '/reg login password username'");
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.equals("/exit")) {
                            send("/exitok");
                            break;
                        }
                        // /auth login password
                        if (message.startsWith("/auth ")) {
                            String[] token = message.split(" ");
                            if (token.length != 3) {
                                send("Неверный формат команды /auth");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .authenticate(this, token[1], token[2])) {
                                authenticated = true;
                                break;
                            }
                        }
                        // /reg login password username
                        if (message.startsWith("/reg ")) {
                            String[] token = message.split(" ");
                            if (token.length != 4) {
                                send("Неверный формат команды /reg");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .registration(this, token[1], token[2], token[3])) {
                                authenticated = true;
                                break;
                            }
                        }
                    }
                }


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
