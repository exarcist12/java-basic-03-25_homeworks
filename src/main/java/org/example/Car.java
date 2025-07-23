package org.example;

public class Car implements Transport {

    private final String name;
    protected int gasoline;

    public Car() {
        this.name = "Машина";
        this.gasoline = 500;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean drive(int distance, Location location) {
        if(location == Location.PLAIN){
            gasoline -= distance;
            System.out.println("Машина проехала расстояние " + distance + " км");
            System.out.println("Бензина осталось " + this.gasoline + " литров");
            return true;
        } else  if(location == Location.FOREST){
            System.out.println("Машина не может проехать такое расстояние по густому лесу");
            return false;
        } else {
            System.out.println("Машина не может проехать такое расстояние по болоту");
            return false;
        }
    }
}
