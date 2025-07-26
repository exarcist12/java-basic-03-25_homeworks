package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    public static List<Integer> fillingArray(int min, int max) {

        List<Integer> list = new ArrayList<>();
        for(int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    public static int sumMoreFive(List<Integer> list) {
        int sum = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) > 5) {
                sum += list.get(i);
            }
        }
        return sum;
    }

    public static List<Integer> fillingArrayMyNumber(int value, List<Integer> list) {

        Collections.fill(list, value);
        return list;
    }


    public static List<Integer> addMyNumber(int value, List<Integer> list) {

        list.replaceAll(i -> value + i);
        return list;
    }
}
