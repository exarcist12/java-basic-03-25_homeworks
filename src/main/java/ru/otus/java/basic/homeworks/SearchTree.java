package ru.otus.java.basic.homeworks;

import java.util.List;

public interface SearchTree<T> {

    T find(T root, T element);

    List<T> getSortedList();

}

