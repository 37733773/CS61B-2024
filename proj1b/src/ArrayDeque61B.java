import java.util.ArrayList;
import java.util.Arrays;
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
        return returnItem;
    }

    @Override
    public T removeLast() {
        int Position = Math.floorMod(lastPosition, array.length);
        T returnItem = array[Position];
        array[Position] = null;
        lastPosition -= 1;
        size--;
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

    private void Resize() {
        T[] newArray = (T[]) new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            int Position = Math.floorMod(frontPosition + i, array.length*2);
            newArray[Position] = get(i);
        }
        array = newArray;
    }
}

