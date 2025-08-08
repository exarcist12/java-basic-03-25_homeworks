package ru.otus.java.basic.homeworks;

public class MathSolution {

    final static int QUANTITY = 100_000_000;
    static double[] arr = new double[QUANTITY];


    public static void createArr(int start, int end){
        for (int i = start; i < end; i++){
            arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }
}
