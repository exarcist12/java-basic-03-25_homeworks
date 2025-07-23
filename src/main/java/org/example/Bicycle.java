package org.example;

public class Bicycle implements Transport {

    private final String name = "Велосипед";

    @Override
    public String getName() {
        return name;
    }

    public boolean drive(int distance, Location location) {
        if (location == Location.PLAIN || location == Location.FOREST) {
            System.out.println("Велосипед проехал расстояние " + distance + " км");
            return true;
        } else {
            System.out.println("Велосипед не может проехать по болоту");
            return false;
        }
    }

}
