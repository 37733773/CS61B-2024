package deque;
import java.util.ArrayList; // import the ArrayList class


import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{

    private class Node {
        Node prev;
        T item;
        Node next;

        public Node(Node p, T x, Node n) {
            prev = p;
            item = x;
            next = n;
        }
    }

    private Node sentinel;
    private int size;
    private Node last;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        last = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        last = last.next;
        size++;
    }

    @Override
    public void addLast(T x) {
        last.next = new Node(last, x, sentinel);
        last = last.next;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>(size);
        Node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            list.add(p.item);
            p = p.next;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
