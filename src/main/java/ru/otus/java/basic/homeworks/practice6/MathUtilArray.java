package ru.otus.java.basic.homeworks.practice6;

import java.util.ArrayList;
import java.util.List;

public class MathUtilArray {

    public static String getEvenNumbersUpTo(byte number) {

        String result = "1";
        for (int i = 1; i < number; i++) {
            if (i % 2 == 0) {

                result += String.valueOf(i);
            }
        }

        result = result + String.valueOf(number);
        return result;
    }

    private MathUtilArray() {

    }

    public static void main(String[] args) {
        System.out.println("Если передать число 5, то метод должен вернуть строку 1245 - " + MathUtilArray.getEvenNumbersUpTo((byte) 5));
    }
}