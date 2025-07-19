package ru.otus.java.basic.homeworks;

public class Horse extends Animal{
    public Horse(String name, int speedRun, int speedSwim, int endurance) {
        this.name = name;
        this.speedRun = speedRun;
        this.speedSwim = speedSwim;
        this.endurance = endurance;
        this.payEnduranceForSwim = 4;
    }
}
