package org.example;

import ru.otus.java.basic.homeworks.Phonebook;

public class Main {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        String name = "Иванов";
        String name2 = "Петров";
        phonebook.add(name, 123);
        phonebook.add(name, 123);
        phonebook.add(name2, 456);
        phonebook.add("Сидоров", 789);
        phonebook.add(name, 124);
        phonebook.add(name2, 457);
        System.out.println("У адресата "+ name  +" есть следующие телефоны: " + phonebook.find(name));
    }
}