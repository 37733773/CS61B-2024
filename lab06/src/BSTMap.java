import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static com.google.common.truth.Truth.assertThat;

public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V> {
   private BSTNode root;
   int size = 0;
    Set<K> keys = new TreeSet<>();


    @Override
    public void put(K key, V value) {
       if (key == null) throw new NullPointerException();
       if (root == null) {
           root = new BSTNode(key, value, null, null);
           size++;
       }
       else root.put(key,value);
       keys.add(key);
   }


    @Override
    public V get(K key) {
       if (root == null) return null;
       BSTNode lookup = root.get(key);
       if (lookup == null) return null;
       else return lookup.value;
    }

    @Override
    public boolean containsKey(K key) {
       return keys.contains(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
       size = 0;
       root = null;
       keys.clear();

    }

    @Override
    public Set<K> keySet() {
       return keys;
    }

    @Override
    public V remove(K key) {
       keys.remove(key);
       return root.remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {

       int count = size;
       BSTNode current = minKey(root);
        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public K next() {
         K returnKey = current.key;
         current = getCurrent(current);
         count--;
         return returnKey;
        }

        public BSTNode getCurrent(BSTNode curr) {
            if (curr.left != null && curr.right != null) return curr.right;
            if (curr.parent.right == curr) return curr.parent.parent;
            return curr.parent;
        }

        public BSTNode minKey(BSTNode curr) {
            while (curr.left != null) curr = curr.left;
            return curr;
        }
    }

    private class BSTNode {
        K key;
        V value;
        BSTNode left, right;
        BSTNode parent;

        BSTNode(K k, V v, BSTNode l, BSTNode r) {
            key = k;
            value = v;
            left = l;
            right = r;
            if (left != null) left.parent = this;
            if (right != null) right.parent = this;
        }

        BSTNode get(K k) {
            if (k == null) return null;
            if (k.compareTo(key) < 0 && left != null) return left.get(k);
            if (k.compareTo(key) > 0 && right != null) return right.get(k);
            if (k.compareTo(key) == 0) return this;
            return null;
        }

        void put(K k, V v) {
            if (k.compareTo(key) < 0) {
                if (left != null) left.put(k, v);
                else {
                    left = new BSTNode(k, v, null, null);
                    left.parent = this;
                    size++;
                }
            }
            else if (k.compareTo(key) > 0) {
                if (right != null) right.put(k, v);
                else {
                    right = new BSTNode(k, v, null, null);
                    right.parent = this;
                    size++;
                }
            }
            else value = v;
        }

        V remove(K k) {
            BSTNode node = get(k);
            if (node == null) return null;
            V returnValue = node.value;

            if (node.left == null && node.right == null) {
                if (node == root) root = null;
                else if (node == node.parent.left) node.parent.left = null;
                else node.parent.right = null;
            }
            else if (node.left == null) {
                node.right.parent = node.parent;
                if (node == root) root = root.right;
                else if (node == node.parent.left) {
                    node.parent.left = node.right;
                }
                else node.parent.right = node.right;

            }
            else {
                BSTNode newNode = node.left;
                while (newNode.right != null) {
                    newNode = newNode.right;
                }
                node.key = newNode.key;
                node.value = newNode.value;
                if (newNode == newNode.parent.left) newNode.parent.left = null;
                else newNode.parent.right = null;
            }
            size--;
            return returnValue;

        }
    }
    public static void main(String[] args) {
        BSTMap<Character, Integer> rightChild = new BSTMap<>();
        for (int i = 0; i < 10; i++) {
            rightChild.put((char) ('C' + i), 3 + i);
        }
    }
}
