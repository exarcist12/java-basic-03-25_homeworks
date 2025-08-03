package ru.otus.java.basic.homeworks;

import ru.otus.java.basic.homeworks.MathSolution;

import java.sql.Time;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        long beforeTime = System.currentTimeMillis();
        MathSolution.createArr(0, 100_000_000);
        long afterTime = System.currentTimeMillis();
        System.out.println(afterTime - beforeTime);

        Thread t = new Thread(()->{
            MathSolution.createArr(0, 25_000_000);
        });
        Thread t2 = new Thread(()->{
            MathSolution.createArr(25_000_000, 50_000_000);
        });
        Thread t3 = new Thread(()->{
            MathSolution.createArr(50_000_000, 75_000_000);
        });
        Thread t4 = new Thread(()->{
            MathSolution.createArr(75_000_000, 100_000_000);
        });
        long beforeTime2 = System.currentTimeMillis();
        t.start();
        t2.start();
        t3.start();
        t4.start();
        t.join();
        t2.join();
        t3.join();
        t4.join();
        long afterTime2 = System.currentTimeMillis();
        System.out.println(afterTime2 - beforeTime2);
    }
}