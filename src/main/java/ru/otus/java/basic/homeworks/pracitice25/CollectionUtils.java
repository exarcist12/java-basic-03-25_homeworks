package ru.otus.java.basic.homeworks.pracitice25;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {
    public static <T> Collection<T> methodDel(Collection<T> inputCollection) {
        List<T> result = new ArrayList<>();

        for(T element : inputCollection){
            if(!(result.contains(element))){
                result.add(element);
            }
        }


        return result;
    }

    private CollectionUtils() {

    }

    public static void main(String[] args) {
        System.out.println("При вводе коллекции (1, 2, 3, 3, 2, 4) метод должен вернуть (1, 2, 3, 4) - "
                + CollectionUtils.methodDel(List.of(1, 2, 3, 3, 2, 4)));
    }
}
