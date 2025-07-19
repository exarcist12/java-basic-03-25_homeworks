package ru.otus.java.basic.homeworks;

public class Cat extends Animal{
    public Cat(String name, int speedRun, int speedSwim, int endurance) {
        this.name = name;
        this.speedRun = speedRun;
        this.speedSwim = speedSwim;
        this.endurance = endurance;
        this.payEnduranceForSwim = 0;
    }

    @Override
    public int swim(int distance) {
        return 0;
    }
}
