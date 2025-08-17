import java.io.*;
import java.net.Socket;
import java.util.Optional;

public class ServerConnection {
    private Socket socket;
    private ServerBase server;
    private DataInputStream in;
    private DataOutputStream out;

    private boolean authenticated;
    private String username;
    private String role;

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
                    if (message.startsWith("/w")) {
                        privateMessage(message, this);
                    }  else if (message.startsWith("/kick") && this.getRole().equals("admin")) {
                        kickUser(message, this);
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


    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }


    public void privateMessage(String message, ServerConnection sender) throws IOException {
            String[] tokens = message.split(" ", 3);
            if (tokens.length < 3) {
                sender.send("Формат: /w имя сообщение");
                return;
            }
            String name = tokens[1];
            String text = tokens[2];

            Optional<ServerConnection> optReceiver = server.getClients().stream()
                    .filter(p1 -> p1.getUsername().equals(name)).findFirst();

            if (optReceiver.isPresent()) {
                ServerConnection receiver = optReceiver.get();
                receiver.send("Вам пришло личное сообщение от " + sender.getUsername() + ": " + text + ". Напишите свой ответ:");
            } else {
                sender.send("Пользователь '" + name + "' не найден.");
            }
    }


    public void kickUser(String message, ServerConnection sender) throws IOException {
        String[] tokens = message.split(" ", 2);
        if (tokens.length != 2) {
            sender.send("Формат: /kick имя сообщение");
            return;
        }
        String name = tokens[1];

        Optional<ServerConnection> optReceiver = server.getClients().stream()
                .filter(p1 -> p1.getUsername().equals(name)).findFirst();

        if (optReceiver.isPresent()) {
            ServerConnection receiver = optReceiver.get();
            receiver.send("/kick");
            server.unsubscribe(receiver);
        } else {
            sender.send("Пользователь '" + name + "' не найден.");
        }
    }
}
