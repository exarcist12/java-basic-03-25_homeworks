import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        Socket socket  = new Socket("localhost", 8189);
        ClientBase exampleClient = new ClientBase(name, socket.getInputStream(), socket.getOutputStream());
        exampleClient.send("Имя: "+ name);
        new Thread(() -> {
            try {
                while (true) {
                    String message = exampleClient.getMessage();
                    System.out.print("\r\033[2K");
                    System.out.println(message);
                    System.out.print("Ваше сообщение: ");
                    System.out.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        System.out.println("Введи сообщение: ");
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
