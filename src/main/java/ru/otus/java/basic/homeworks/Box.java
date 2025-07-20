package ru.otus.java.basic.homeworks;

public class Box {
    private int size;
    private String color;

    private String[] items;

    private boolean isOpened = false;

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        System.out.println("Цвет коробки изменен на " + color);
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }


    public Box(int size, String color) {
        this.size = size;
        this.color = color;
        this.items = new String[size];
    }

    public void openBox() {
          if(isOpened){
              System.out.println("Коробка уже открыта");
          } else {
              isOpened = true;
              System.out.println("Коробка открыта");
          }
    }

    public void closeBox() {
        if(!isOpened){
            System.out.println("Коробка уже закрыта");
        } else {
            isOpened = false;
            System.out.println("Коробка закрыта");
        }
    }

    public void putItem(String item) {
        if(isOpened){
            for (int i = 0; i < items.length; i++) {
                if(items[i] == null){
                    items[i] = item;
                    System.out.println("В коробку положили предмет: " + item);
                    break;
                }
            }
            System.out.println("Состав коробки:");
            for (int i =0; i< items.length; i++){
                if(items[i] != null){
                    System.out.println(items[i]);
                }
            }
        }
    }

    public void deleteItem(String item) {
        if(isOpened){
            for (int i = 0; i < items.length; i++) {
                if(items[i].equals(item)){
                    items[i] = null;
                    System.out.println("Из коробки убрали предмет: " + item);
                }
            }

            System.out.println("Состав коробки:");
            for (int i =0; i< items.length; i++){
                if(items[i] != null){
                    System.out.println(items[i]);
                }
            }
        }
    }
}

