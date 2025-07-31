package ru.otus.java.basic.homeworks.pracitice5;

import java.util.ArrayList;
import java.util.List;

public class MathUtilAvg {
    public static int getPrimeNumbersSum(byte lowEdge, byte highEdge) {
        List<Integer> primeNumbers = new ArrayList<>();
        int sum = 0;
        int avg = 0;
        for (int i = lowEdge+1; i < highEdge; i++) {
            if (i % 2 == 0) {
                primeNumbers.add(i);
                sum += i;
            }
        }

        avg = sum/primeNumbers.size();
        return avg;
    }

    private MathUtilAvg() {

    }

    public static void main(String[] args) {
        System.out.println("avg четных чисел у ряда с 1 до 10 должен равняться 6 - "
                + MathUtilAvg.getPrimeNumbersSum((byte) 1, (byte) 15));
    }

}