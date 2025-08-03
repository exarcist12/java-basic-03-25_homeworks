package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class TreeBulder implements SearchTree<Tree> {


    protected Tree root;
    private List<Integer> list;

    public TreeBulder(List<Integer> list) {
        this.list = list;
        List<Tree> treeList = getSortedList();
        root = build(treeList, 0, list.size()-1);
    }


    private Tree build(List<Tree> treeList, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Tree tree = treeList.get(mid);
        tree.left = build(treeList, start, mid - 1);
        tree.right = build(treeList, mid + 1, end);
        return tree;
    }

    static void print(Tree tree) {
        if (tree == null) {
            return;
        }
        Queue<Tree> q = new LinkedList<>();
        q.add(tree);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i=0; i<n; i++) {
                Tree cur = q.poll();
                System.out.print(cur.value + " ");
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            System.out.println();
        }
    }


    @Override
    public Tree find(Tree root, Tree element) {
        if(root == null) {
            return null;
        }
        else if(root.value == element.value) {
            return root;
        }
        else if(root.value > element.value) {
            return find(root.left, element);
        }
        else {
            return find(root.right, element);
        }
    }

    @Override
    public List<Tree> getSortedList() {
        this.list = this.list.stream().sorted().collect(Collectors.toList());
        List<Tree> treeList = new ArrayList<>();
        for(Integer i : this.list){
            treeList.add(new Tree(i));
        }
        return treeList;
    }
}
