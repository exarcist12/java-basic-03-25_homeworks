package ru.otus.java.basic.homeworks.practice3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FibonacciUtils {
    public static String getFibonacciSum(byte limit) {

        List<Integer> fibonacci = new ArrayList<>();

        int sum = 1;
        int oldNumber = 0;
        int next = 0;
        int next2 = 1;
        fibonacci.add(next);
        fibonacci.add(next2);

        if (limit < 0) {
            return "0";
        }


            for (int i = 2; i < Integer.MAX_VALUE; i++) {
                oldNumber = next2;
                next2 = next + next2;
                next = oldNumber;
                fibonacci.add(next2);
                if (next2 == limit) {
                    break;
                }
                sum += next2;
            }
        return String.valueOf(sum);

    }

    private FibonacciUtils(){

    }

    public static void main(String[] args) {
        System.out.println("Сумма чисел фибоначчи до 5 должна равняться 7 - " + FibonacciUtils.getFibonacciSum((byte) 5));
    }
}