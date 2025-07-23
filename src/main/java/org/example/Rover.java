package org.example;

public class Rover extends Car implements Transport {
    private final String name;

    @Override
    public String getName() {
        return name;
    }
    public Rover() {
        this.name = "Вездеход";
        this.gasoline = 10000;
    }

    @Override
    public boolean drive(int distance, Location location) {
        gasoline -= distance * 2;
        System.out.println("Машина проехала расстояние " + distance + " км");
        System.out.println("Бензина осталось " + this.gasoline + " литров");
        return true;
    }
}
