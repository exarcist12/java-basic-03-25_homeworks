package org.example;

public class Main {
    public static void main(String[] args) {


        Car car = new Car();
        Horse horse = new Horse();
        Bicycle bicycle = new Bicycle();
        Rover rover = new Rover();

        Human human = new Human("Ivan");
        human.sitIn(car);
        human.getGoing(100, Location.PLAIN);
        human.getGoing(100, Location.SWAMP);
        human.getGoing(100, Location.FOREST);
        human.getOutOfTransport();
        human.sitIn(horse);
        human.getGoing(100, Location.PLAIN);
        human.getGoing(100, Location.SWAMP);
        human.getGoing(100, Location.FOREST);
        human.getOutOfTransport();
        human.sitIn(rover);
        human.getGoing(100, Location.PLAIN);
        human.getGoing(100, Location.SWAMP);
        human.getGoing(100, Location.FOREST);
        human.getOutOfTransport();
        human.sitIn(bicycle);
        human.getGoing(100, Location.PLAIN);
        human.getGoing(100, Location.SWAMP);
        human.getGoing(100, Location.FOREST);
        human.getOutOfTransport();
        human.getGoing(100, Location.PLAIN);
        human.getGoing(100, Location.SWAMP);
        human.getGoing(100, Location.FOREST);
    }
}