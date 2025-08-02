package ru.otus.java.basic.homeworks;

import ru.otus.java.basic.homeworks.Console;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Console.getCurrentFiles(Console.currentDirectory);

        while (Console.continueAction) {
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.print("Выберите файл или папку: ");
            String fileName = scanner.nextLine();
            Console.openFileOrDirectory(fileName);
            if(Console.currentFile!=null){
                System.out.println();
                System.out.print("Хотите что-нибудь записать в файл: Y/N ?");
                scanner = new Scanner(System.in);
                String select = scanner.nextLine().toUpperCase(Locale.ROOT);
                if(select.equals("Y")){
                    System.out.print("Вы хотите полностью стереть или добавить в конце: Y (в конце добавить)/N (полностью стереть и записать новую запись ?");
                    scanner = new Scanner(System.in);
                    select = scanner.nextLine().toUpperCase();
                    System.out.print("Напишите текст:");
                    if(select.equals("Y")){
                        scanner = new Scanner(System.in);
                        String text = scanner.nextLine();
                        Console.writeStringToFile(text, true);
                    } else {
                        scanner = new Scanner(System.in);
                        String text = scanner.nextLine();
                        Console.writeStringToFile(text, false);
                    }
                }
                Console.goToBackFromFile();
            } else {

            }
            boolean isBack=true;
            while (isBack){
                if(Console.currentDirectory.equals("src/main/java")){
                    isBack=false;
                } else {
                    System.out.println("Хотите вернуться на папку выше? Y/N");
                    scanner = new Scanner(System.in);
                    String select = scanner.nextLine().toUpperCase(Locale.ROOT);
                    if(select.equals("Y")){
                        Console.goToBackDirectory();
                    } else {
                        isBack=false;
                    }
                }
            }
        }
    }
}