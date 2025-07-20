package ru.otus.java.basic.homeworks;

public class Main {
    public static void main(String[] args) {
        Plate plate = new Plate(1000);
        Cat cat = new Cat("Вася", 100);
        Cat cat2 = new Cat("Федор", 200);
        Cat cat3 = new Cat("Иван", 300);
        Cat cat4 = new Cat("Стас", 400);
        Cat cat5 = new Cat("Петр", 500);
        cat.eat(plate);
        cat.eat(plate);
        cat2.eat(plate);
        cat3.eat(plate);
        cat4.eat(plate);
        cat5.eat(plate);
        plate.addFoodInPlate(1000);
        cat5.eat(plate);
    }
}
