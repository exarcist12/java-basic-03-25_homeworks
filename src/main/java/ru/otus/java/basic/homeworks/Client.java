package ru.otus.java.basic.homeworks;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        while(true){
           try(Socket socket  = new Socket("localhost", 8081)){
               ExampleClient exampleClient = new ExampleClient(socket.getInputStream(), socket.getOutputStream());
               System.out.println("Введи сообщение: ");
               String userMessage = scanner.nextLine();
               if(userMessage.equalsIgnoreCase("exit")){
                   exampleClient.send(userMessage);
                   break;
               }
               exampleClient.send(userMessage);
           } catch (IOException e) {
               throw new RuntimeException(e);

           }
        }
    }
}
