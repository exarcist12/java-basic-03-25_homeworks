package ru.otus.java.basic.homeworks;

public class Cat {
    private String name;
    private int appetite;

    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
        System.out.println("У нас есть голодный кот:" + name);
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                '}';
    }

    public void eat(Plate plate) {
        if(this.satiety) {
            System.out.println("Кот " + this.name + " не хочет кушать, он сыт");
            System.out.println("В тарелке осталось еды: " + plate.getCurrentFoodAmount());
            return;
        }

        if (plate.getCurrentFoodAmount() < this.appetite) {
            System.out.println("Кот " + this.name + " не поел, в тарелке не хватает для кота еды");
        } else {
            System.out.println("Кот " + this.name + " поел");
            int food = plate.getCurrentFoodAmount() - this.appetite;
            plate.setCurrentFoodAmount(food);
            System.out.println("В тарелке осталось еды: " + plate.getCurrentFoodAmount());
            this.satiety = true;
        }
    }
}
