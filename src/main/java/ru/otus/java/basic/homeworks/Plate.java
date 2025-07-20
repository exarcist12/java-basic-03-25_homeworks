package ru.otus.java.basic.homeworks;

public class Plate {
    private int currentFoodAmount;
    private final int maxFoodAmount;

    public Plate(int maxFoodAmount) {
        this.maxFoodAmount = maxFoodAmount;
        this.currentFoodAmount = maxFoodAmount;
        System.out.println("У нас есть полная тарелка еды с объемом: " + maxFoodAmount);
    }

    public void addFoodInPlate(int amountOfFood) {
        if (this.currentFoodAmount + amountOfFood <= this.maxFoodAmount) {
            this.currentFoodAmount = this.currentFoodAmount + amountOfFood;
            System.out.println("В тарелку добавлено еды: " + amountOfFood);
        } else {
            System.out.println("Тарелка полная");
            this.currentFoodAmount = this.maxFoodAmount;
        }
    }

    public int getCurrentFoodAmount() {
        return this.currentFoodAmount;
    }

    public void setCurrentFoodAmount(int currentFoodAmount) {
        this.currentFoodAmount = currentFoodAmount;
    }

    public boolean isEmpty(int amountOfFoodEaten) {
        if (this.currentFoodAmount - amountOfFoodEaten < 0) {
            return false;
        }
        return true;
    }

}
