import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.lang.Math;
public class ArrayDeque61B<T> implements Deque61B<T>{

    private int size;
    private T[] array;
    private int frontPosition;
    private int lastPosition;

    public ArrayDeque61B() {
         size = 0;
         array = (T[]) new Object[8];
         frontPosition = 0;
         lastPosition = 0;
    }

    @Override
    public void addFirst(T x) {
      if (size == 0) {
          array[0] = x;
          frontPosition =  Math.floorMod(frontPosition-1, array.length);
      }else {
          array[frontPosition-1] = x;
          frontPosition -= 1;
      }
      size++;
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            array[0] = x;
            lastPosition =  Math.floorMod(lastPosition + 1, array.length);
        }else {
            array[lastPosition + 1] = x;
            lastPosition += 1;
        }
        size++;
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size;
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
        if (index < 0 || index >= size) {
            return null;
        }else {
         if (index  >= size - lastPosition - 1) {
             return array[lastPosition];
         }else {
             return array[index + frontPosition];
         }
        }
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    private void Resize() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length + 8);
            frontPosition = 8 - array.length ;
            lastPosition = array.length - 8;
        }
    }
}
