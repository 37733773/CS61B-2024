import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest2 {


    @Test
    public void getAndtoListTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        /* deque: [ 3, 2, 1, 4, 5, 6]   inner [1, 4, 5, 6, 3, 2]*/
        assertThat(deque.get(0)).isEqualTo(3);
        assertThat(deque.get(1)).isEqualTo(2);
        assertThat(deque.get(2)).isEqualTo(1);
        assertThat(deque.get(3)).isEqualTo(4);
        assertThat(deque.get(4)).isEqualTo(5);
        assertThat(deque.get(5)).isEqualTo(6);
    }

    @Test
    public void toListTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        assertThat(deque.toList()).containsExactly(3, 2, 1, 4, 5, 6).inOrder();
    }

    @Test
    public void isEmptyAndSizeTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        assertThat(deque.size()).isEqualTo(0);
        assertThat(deque.isEmpty()).isTrue();

        deque.addFirst(1);
        deque.addLast(2);
        assertThat(deque.size()).isEqualTo(2);
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void removeTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        /* deque: [ 3, 2, 1, 4, 5, 6]   inner [1, 4, 5, 6, 3, 2]*/

        assertThat(deque.removeFirst()).isEqualTo(3);
        assertThat(deque.removeLast()).isEqualTo(6);
        assertThat(deque.size()).isEqualTo(4);
        assertThat(deque.isEmpty()).isFalse();
        assertThat(deque.removeFirst()).isEqualTo(2);
        assertThat(deque.removeLast()).isEqualTo(5);
    }
}
