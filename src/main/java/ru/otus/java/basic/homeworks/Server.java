package ru.otus.java.basic.homeworks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {


        try (ServerSocket socket = new ServerSocket(8081)){
            System.out.println("SERVER APPLICATION RUN!");
            while (true) {
                Socket client = socket.accept();
                ExampleServer exampleServer = new ExampleServer(client.getInputStream(),client.getOutputStream());
                String getMessage = exampleServer.getMessage();
                String result = calculation(getMessage);
                exampleServer.send(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String calculation(String message){

        if (!message.contains("(")){
            return String.valueOf(operation(message));
        }

        char[] arr = message.toCharArray();
        String stringIn = null;
        String resultat = null;
        String example = null;

        int indexCurrentOpenSkobka = -1;
        int indexCurrentCloseSkobka = -1;


        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == ')') {
                indexCurrentCloseSkobka = i;
                break;
            }
        }

        for (int i=indexCurrentCloseSkobka; i>=0; i--){
            if (arr[i] == '(') {
                indexCurrentOpenSkobka = i;
                break;
            }
        }

        if((indexCurrentOpenSkobka!=-1)&&(indexCurrentCloseSkobka!=-1)){
            stringIn = message.substring(indexCurrentOpenSkobka+1, indexCurrentCloseSkobka);
            resultat = calculation(stringIn);
            example = message.replace("(" +stringIn + ")", "X");
            example = example.replace("X", resultat);
            resultat = calculation(example);
        }

        return resultat;
    }

    public static Integer operation(String message) {
        char[] arr = message.toCharArray();
        int num = 0;
        char operation = '+';
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (Character.isDigit(c)) {
                num = num * 10 + (c-'0');
            }
            if (!Character.isDigit(c) || i == arr.length - 1) {
                switch (operation) {
                    case '+':
                        result += num;
                        break;
                    case '-':
                        result -= num;
                        break;
                    case '*':
                        result *= num;
                        break;
                    case '/':
                        result /= num;
                        break;
                }
                operation = c;
                num = 0;
            }
        }
        return result;
    }
}
