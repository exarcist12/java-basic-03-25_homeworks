import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket socket  = new Socket("localhost", 8189);
        ClientBase exampleClient = new ClientBase(socket.getInputStream(), socket.getOutputStream());
        String firstMessage = exampleClient.getMessage();
        System.out.println(firstMessage);
        System.out.flush();
        new Thread(() -> {
            try {
                while (true) {
                    String message = exampleClient.getMessage();
                    System.out.print("\r\033[2K");
                    if (!message.equals("/kick")) {
                        System.out.println(message);
                    }
                    if (message.equals("/kick")){
                        System.out.println("Вас отключили от сервера");
                        exampleClient.close();
                    }

                    System.out.print("Ваше сообщение: ");
                    System.out.flush();
                }
            } catch (RuntimeException e) {
                System.out.println("Соединение закрыто");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

//        System.out.println("Введи сообщение: ");
        while(true){
            try {
                String userMessage = scanner.nextLine();
                if(userMessage.equalsIgnoreCase("exit")){
                    exampleClient.send(userMessage);
                    exampleClient.close();
                    break;
                }
                exampleClient.send(userMessage);

            } catch (UnknownHostException e) {
                throw new RuntimeException(e);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
