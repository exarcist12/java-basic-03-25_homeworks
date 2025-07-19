package ru.otus.java.basic.homeworks;

public class Animal {
    protected String name;
    protected int speedRun;
    protected int speedSwim;
    protected int endurance;
    protected int payEnduranceForSwim;


    public int run(int distance) {
        this.endurance = this.endurance - distance;
        if (this.endurance < 0) {
            return -1;
        } else {
            System.out.println("Выносливость после пробежки у " + this.name + ":" + this.endurance);
            return distance / this.speedRun;
        }
    }


    public int swim(int distance) {
        this.endurance = this.endurance - distance * this.payEnduranceForSwim;
        if (this.endurance < 0) {
            return -1;
        } else {
            System.out.println("Выносливость после плавания у " + this.name + ":" + this.endurance);
            return distance / this.speedSwim;
        }
    }

    public void info() {
        if (this.endurance < 0) {
            System.out.println("У животного состояние: Усталость");
        } else {
            System.out.println("У животного состояние: Здоров");
        }
    }
}
