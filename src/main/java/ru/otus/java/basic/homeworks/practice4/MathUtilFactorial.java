package ru.otus.java.basic.homeworks.practice4;

public class MathUtilFactorial {
    public static long getFactorial(byte number) {
        long result = 1;
        for(int i = 1; i <= number; i++) {
            result *= i;
        }

        return 0;
    }

    private MathUtilFactorial() {

    }

    public static void main(String[] args) {
        System.out.println("Факториал от 5 должен равняться 120 - " + MathUtilFactorial.getFactorial((byte) 5));
    }
}