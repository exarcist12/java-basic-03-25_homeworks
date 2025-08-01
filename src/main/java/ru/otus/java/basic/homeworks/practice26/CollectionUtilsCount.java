package ru.otus.java.basic.homeworks.practice26;
import java.util.*;

public class CollectionUtilsCount {
    public static <K> Map<K, Integer> countElements(K[] inputArray) {

        Map<K, Integer> pairs = new LinkedHashMap<>();
        for (K element : inputArray){
            if (!(pairs.containsKey(element))) {
                pairs.put(element, 1);
            } else {
                Integer count = pairs.get(element);
                count++;
                pairs.put(element, count);

            }

        }
//TODO
        return pairs;
    }

    private CollectionUtilsCount() {

    }

    public static void main(String[] args) {
        System.out.println("При вводе массива [1, 2, 4, 1, 1, 1, 2, 3, 2], метод должен вернуть [\"1\", 4, \"2\", 3, \"4\", 1, \"3\", 1] - "
                + CollectionUtilsCount.countElements(new String[]{"1", "2", "4", "1", "1", "1", "2", "3", "2"}));
    }
}