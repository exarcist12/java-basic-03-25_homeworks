package org.example;

public class Horse implements Transport {

    private int power;
    private final String name;

    public Horse() {
        this.name = "Лошадь";
        this.power = 600;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public boolean drive(int distance, Location location) {
        if(location == Location.PLAIN){
            power -= distance;
            System.out.println("Лошадь прошла расстояние " + distance + " км");
            System.out.println("Сил у лошади осталось: " + this.power);
            return true;
        } else  if(location == Location.FOREST){
            power -= distance * 2;
            System.out.println("Лошадь прошла расстояние " + distance + " км");
            System.out.println("Сил у лошади осталось " + this.power);
            return true;
        } else {
            System.out.println("Лошадь не может пройти такое расстояние по болоту");
            return false;
        }
    }
}
