package ru.otus.java.basic.homeworks;

import java.util.Scanner;

public class Homework3 {
    public static void main(String[] args) {

        System.out.println("Введите номер метода: 1 - greetings, 2 - checkSign, 3 - selectColor, 4 - compareNumbers, 5 - addOrSubtractAndPrint:");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        if (a==1){
            System.out.println("Выполняется метод greetings");
            greetings();
        } else if (a==2){
            System.out.println("Выполняется метод checkSign");
            System.out.println("Введите последовательно 3 числа:");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            checkSign(x, y, z);
        } else if (a==3){
            System.out.println("Выполняется метод selectColor");
            selectColor();
        } else if (a==4){
            System.out.println("Выполняется метод compareNumbers");
            compareNumbers();
        } else if (a==5){
            System.out.println("Выполняется метод addOrSubtractAndPrint");
            System.out.println("Введите последовательно 2 числа и одно булевое значение:");
            int initValue = scanner.nextInt();
            int delta = scanner.nextInt();
            boolean increment = scanner.nextBoolean();
            addOrSubtractAndPrint(initValue, delta, increment);
        }
    }

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    public static void checkSign(int a, int b, int c) {
        if (a + b + c < 0) {
            System.out.println("Сумма отрицательная");
        } else {
            System.out.println("Сумма положительная");
        }
    }

    public static void selectColor() {
        int data = (int) (Math.random() * 30);
        System.out.println(data);
        if (data <= 10) {
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = (int) (Math.random() * 30);
        int b = (int) (Math.random() * 30);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        if (a >= b) {
            System.out.println("a больше b");
        } else  {
                System.out.println("a меньше b");
        }
    }

    public static void  addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) {
            System.out.println(initValue + delta);
        } else {
            System.out.println(initValue - delta);
        }
    }
}
