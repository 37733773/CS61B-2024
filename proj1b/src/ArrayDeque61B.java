import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.lang.Math;
public class ArrayDeque61B<T> implements Deque61B<T> {

    private int size;
    private T[] array;
    private int frontPosition;
    private int lastPosition;

    public ArrayDeque61B() {
        size = 0;
        array = (T[]) new Object[8];
        frontPosition = 1;
        lastPosition = -1;
    }

    @Override
    public void addFirst(T x) {
        if (size == array.length) {
            Resize();
        }
        int Position = Math.floorMod(frontPosition - 1, array.length);
        array[Position] = x;
        if (frontPosition == 1 && lastPosition == -1) {
            lastPosition += 1;
        }
        frontPosition -= 1;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == array.length) {
            Resize();
        }
        int Position = Math.floorMod(lastPosition + 1, array.length);
        array[Position] = x;
        if (frontPosition == 1 && lastPosition == -1) {
            frontPosition -= 1;
        }
        lastPosition += 1;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        int x = frontPosition;
        while (x <= lastPosition) {
            int Position = Math.floorMod(x, array.length);
            list.add(array[Position]);
            x += 1;
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
        int Position = Math.floorMod(frontPosition, array.length);
        T returnItem = array[Position];
        array[Position] = null;
        frontPosition += 1;
        size--;
        if (array.length < 16 || size >= array.length / 4) {
            Resize();
        }
        return returnItem;
    }

    @Override
    public T removeLast() {
        int Position = Math.floorMod(lastPosition, array.length);
        T returnItem = array[Position];
        array[Position] = null;
        lastPosition -= 1;
        size--;
        if (array.length < 16 || size >= array.length / 4) {
            Resize();
        }
        return returnItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            int Position = Math.floorMod(index + frontPosition, array.length);
            return array[Position];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private class ArrayIterator implements Iterator<T> {

        public int nowPosition = 0;

        @Override
        public boolean hasNext() {
            return nowPosition < size;
        }

        @Override
        public T next() {
            T returnItem = get(nowPosition);
            nowPosition += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }


    private void Resize() {
        int newLength = ResizeHelper(array.length);
        T[] newArray = (T[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            int Position = Math.floorMod(frontPosition + i, newLength);
            newArray[Position] = get(i);
        }
        array = newArray;
    }

    private int ResizeHelper(int length) {
        if (size == length) { return 2*length; }
        else if (length < 16 || size >= length / 4) {return length;}
        else {return ResizeHelper(length/4);}
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque61B otherDeque) {
            if (this.size() != otherDeque.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if(!get(i).equals(otherDeque.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}

