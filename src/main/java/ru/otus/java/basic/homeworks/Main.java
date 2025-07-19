package ru.otus.java.basic.homeworks;

public class Main {

    public static void main(String[] args) {
        Dog dog = new Dog("Бобик", 15,  10, 300);
        Cat cat = new Cat("Мурзик", 20,  0, 200);
        Horse horse = new Horse("Серый", 25,  7, 1000);
        dog.info();
        System.out.println("Собаке времени потребовалось на пробежку: " + dog.run(105));
        System.out.println("Собаке времени потребовалось на плавание:" + dog.swim(50));
        System.out.println("Собаке времени потребовалось на плавание:" + dog.swim(50));
        dog.info();
        cat.info();
        System.out.println("Коту времени потребовалось на пробежку: " + cat.run(100));
        System.out.println("Коту времени потребовалось на плавание:" + cat.swim(50));
        System.out.println("Коту времени потребовалось на пробежку: " + cat.run(100));
        System.out.println("Коту времени потребовалось на пробежку: " + cat.run(100));
        cat.info();
        System.out.println("Лошади времени потребовалось на пробежку: " + horse.run(500));
        System.out.println("Лошади времени потребовалось на плавание:" + horse.swim(70));
    }
}
