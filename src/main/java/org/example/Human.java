package org.example;

public class Human {
    private final String name;
    private Transport currentTransport;

    private int power;

    public Human(String name) {
        this.name = name;
        this.power = 1000;
    }


    public void sitIn(Transport transport) {
        this.currentTransport = transport;
        System.out.println("Человек " + this.name + " сел на транспорт " + transport.getName());
    }

    public void getGoing(int distance, Location location) {
        if (this.currentTransport == null) {
            System.out.println("Человек " + this.name + " прошел сам " + distance + " км");
            this.power -= distance * 2;
            System.out.println("У человека сил осталась " + this.power);
        } else if (this.currentTransport instanceof Bicycle) {
            if (currentTransport.drive(distance, location)) {
                this.power -= distance;
                System.out.println("У человека сил осталась " + this.power);
            }
        } else {
            currentTransport.drive(distance, location);
        }
    }

    public void getOutOfTransport() {
        if (this.currentTransport != null) {
            this.currentTransport = null;
            System.out.println("Человек " + this.name + " вышел из транспорта");
        } else {
            System.out.println("Человек " + this.name + " не находится в транспорте");
        }
    }

}
