package ru.otus.java.basic.homeworks;

public class MainBox {
    public static void main(String[] args) {
        Box box = new Box(3, "red");
        box.openBox();
        box.openBox();
        box.putItem("item1");
        box.putItem("item2");
        box.putItem("item3");
        box.closeBox();
        box.closeBox();
        box.openBox();
        box.deleteItem("item2");
        box.closeBox();
    }
}
