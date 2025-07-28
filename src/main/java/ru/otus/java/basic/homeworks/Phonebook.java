package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Phonebook {

    LinkedHashMap<Integer, String> phonebook = new LinkedHashMap<>();

    public void add(String name, Integer number) {
        if (!containsPhoneNumber(number)) {
            this.phonebook.put(number, name);
            System.out.println("В телефонную книгу был добавлен " + name + " с номером " + number);
        }
    }

    public List<Integer> find(String name) {
        List<Integer> phoneList = new ArrayList<>();
        for(Map.Entry entry : this.phonebook.entrySet()) {
            if(entry.getValue().equals(name)) {
                phoneList.add((Integer) entry.getKey());
            }
        }
        return phoneList;
    }

    public boolean containsPhoneNumber(Integer number) {
        if (this.phonebook.containsKey(number)) {
            System.out.println("Нельзя добавить "+  number +", в телефонной книге есть номер " + number);
            return true;
        } else return false;
    }
}
