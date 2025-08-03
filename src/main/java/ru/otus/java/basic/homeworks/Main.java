package ru.otus.java.basic.homeworks;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TreeBulder treeBulder = new TreeBulder(List.of(55,66,2,3,4,5,6,7,8,9,10));
        TreeBulder.print(treeBulder.root);
    }
}