import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
   private class BSTNode {
       K key;
       V value;
       BSTNode left, right;

       BSTNode(K k, V v, BSTNode l, BSTNode r) {
           key = k;
           value = v;
           left = l;
           right = r;
       }

       BSTNode get(K k) {
           if (k == null) return null;
           if (k.compareTo(key) < 0 && left != null) return left.get(k);
           if (k.compareTo(key) > 0 && right != null) return right.get(k);
           return this;
       }

       void put(K k, V v) {
           if (k.compareTo(key) < 0) {
               if (left != null) left.put(k, v);
               else {
                   left = new BSTNode(k, v, null, null);
                   size++;
               }
           }
           else if (k.compareTo(key) > 0) {
               if (right != null) right.put(k, v);
               else {
                   right = new BSTNode(k, v, null, null);
                   size++;
               }
           }
           else value = v;

       }
   }

   private BSTNode root;
   int size = 0;

   public int compareKey(K k1, K k2) {
       return k1.compareTo(k2);
   }

    @Override
    public void put(K key, V value) {
       if (key == null) throw new NullPointerException();
       if (root == null) {
           root = new BSTNode(key, value, null, null);
           size++;
       }
       else root.put(key,value);
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
       if (root == null) return false;
       return root.get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
       size = 0;
       root = null;

    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
