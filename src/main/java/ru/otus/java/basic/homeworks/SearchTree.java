package ru.otus.java.basic.homeworks;

import java.util.List;

public interface SearchTree<T> {

    T find(T root, T element);

    List<T> getSortedList();

//    TreeBulder.Tree find(TreeBulder.Tree root, TreeBulder.Tree element);

//   TreeBulder.Tree find(TreeBulder.Tree root, TreeBulder.Tree element);
}

