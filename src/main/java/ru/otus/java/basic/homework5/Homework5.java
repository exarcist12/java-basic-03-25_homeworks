package ru.otus.java.basic.homework5;

import java.util.Arrays;
import java.util.Scanner;

public class Homework5 {
    public static void main(String[] args) {
        printWord(5, "Hello");
        sumArray(createArray());
        replaceArray(5, createArray());
        addArray(5, createArray());
        sumFirstOrSecondHalf(createArray());
        int[][] array = {{1, 2, 3, 5, 6}, {4, 5, 6}, {7, 8, 9, 0, 0, 0, 0}};
        sumArrays(array);
        compareHalfSum(new int[]{1, 2, 3, 3, 4, 5});
        compareSorted(new int[]{1, 2, 3, 4, 5});
        changeArray(createArray());
    }

    public static void  printWord(int count, String text) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    public static int[] createArray() {
        int length = (int) (Math.random() * 10);
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10);
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    public static void sumArray(int[] array) {
        int sum=0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 5) {
                sum = sum + array[i];
            }
        }
        System.out.println(sum);
    }

    public static void replaceArray(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void addArray(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i]+value;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void sumFirstOrSecondHalf(int[] array) {
        int sumFirst = 0;
        for (int i = 0; i < array.length/2; i++) {
            sumFirst = sumFirst + array[i];
        }
        System.out.println(sumFirst);
        int sumSecond = 0;
        for (int i = array.length/2; i >= array.length/2 && i < array.length; i++) {
            sumSecond = sumSecond + array[i];
        }
        System.out.println(sumSecond);

        if (sumFirst > sumSecond) {
            System.out.println("Сумма первой половины больше");
        } else {
            System.out.println("Сумма второй половины больше");
        }
    }

    public static void sumArrays(int[][] array1) {
        int maxLength = 0;
        for (int j = 0; j < array1.length; j++) {
            if (array1[j].length > maxLength) {
                maxLength = array1[j].length;
            }
        }
        System.out.println(maxLength);

        int[] sumArray = new int[maxLength];
        for (int j = 0; j < array1.length; j++) {
            for (int i = 0; i < array1[j].length; i++) {
                sumArray[i] = sumArray[i] + array1[j][i];
            }
        }
        System.out.println(Arrays.toString(sumArray));
    }


    public static void compareHalfSum(int[] array) {
        System.out.println(Arrays.toString(array));
        int sumFirst = 0;
        int sumSecond = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <= i; j++) {
                sumFirst = sumFirst + array[j];
            }

            for (int j = i+1; j < array.length; j++) {
                sumSecond = sumSecond + array[j];
            }

            if (sumFirst == sumSecond) {
                System.out.println("Точка находится между числами " + array[i] + " и " + array[i + 1]);
                break;
            } else {
                sumFirst = 0;
                sumSecond = 0;
            }
        }
    }

    public static void compareSorted(int[] array) {
        System.out.println("Введите 1 если хотите проверить, что массив отсортирован по возрастанию, 0 если хотите проверить, что массив отсортирован по убыванию:");
        Scanner scanner = new Scanner(System.in);
        int checkSort = scanner.nextInt();
        System.out.println(Arrays.toString(array));

        boolean sorted = true;

        if (checkSort == 1) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    sorted = false;
                    break;
                }
            }
        } else if (checkSort == 0) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] < array[i + 1]) {
                    sorted = false;
                    break;
                }
            }
        }

        if (sorted) {
            System.out.println("Массив отсортирован");
        } else {
            System.out.println("Массив не отсортирован");
        }
    }



    public static void changeArray(int[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        System.out.println(Arrays.toString(newArray));
    }
}
