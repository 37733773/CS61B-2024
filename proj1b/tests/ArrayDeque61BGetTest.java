import java.util.ArrayDeque;

public class ArrayDeque61BGetTest {

    public static void main(String[] args) {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        /* deque: [ 3, 2, 1, 4, 5, 6] */

        System.out.println(deque.get(0));
        System.out.println(deque.get(1));
        System.out.println(deque.get(2));
        System.out.println(deque.get(3));
        System.out.println(deque.get(4));
        System.out.println(deque.get(5));
        System.out.println(deque.get(6));
        System.out.println(deque.get(7));
    }
}
