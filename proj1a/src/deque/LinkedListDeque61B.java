package deque;
import java.util.ArrayList; // import the ArrayList class


import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

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
        last = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
            sentinel.next = new Node(sentinel, x, sentinel.next);
            if (size == 0) {
                last = last.next;
            }
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
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }else {
            T returnItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.next.prev = sentinel;
            size--;
            return returnItem;
        }
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }else {
            T returnItem = last.item;
            last.prev.next = sentinel;
            sentinel.prev = last.prev;
            last = last.prev;
            size--;
            return returnItem;
        }
    }

    @Override
    public T get(int index) {
        Node p = sentinel.next;
        if (index < 0 || index >= size) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        return helper(p, index);
    }

    private T helper(Node p, int index) {
            if (index == 0) {
                return p.item;
            } else {
                return helper(p.next, index-1);
            }
        }
}
