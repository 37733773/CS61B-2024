package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int initialCapacity = 16;
    private double loadFactor = 0.75;
    private int size;

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Constructors
     */
    public MyHashMap() {
        buckets = new Collection[initialCapacity];
        for (int j = 0; j < initialCapacity; j++) {
            buckets[j] = createBucket();
        }
    }


    public MyHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        buckets = new Collection[initialCapacity];
        for (int j = 0; j < this.initialCapacity; j++) {
            buckets[j] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor      maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = new Collection[initialCapacity];
        for (int j = 0; j < this.initialCapacity; j++) {
            buckets[j] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * Note that that this is referring to the hash table bucket itself,
     * not the hash map itself.
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void put(K key, V value) {
        int bucketIndex = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node : buckets[bucketIndex]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        buckets[bucketIndex].add(new Node(key, value));
        size++;

        if ((double) size / (double) buckets.length > loadFactor) {
            Collection<Node>[] oldBuckets = buckets;
            buckets = new Collection[buckets.length * 2];
            for (int j = 0; j < buckets.length; j++) {
                buckets[j] = createBucket();
            }
            for (int j = 0; j < oldBuckets.length; j++) {
                for (Node node : oldBuckets[j]) {
                    put(node.key, node.value);
                    size--;
                }
            }
        }
    }

    @Override
    public V get(K key) {
        int bucketIndex = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node : buckets[bucketIndex]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int bucketIndex = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node : buckets[bucketIndex]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public int size() {
       return size;
    }

    @Override
    public void clear() {
        buckets = new Collection[initialCapacity];
        for (int j = 0; j < initialCapacity; j++) {
            buckets[j] = createBucket();
        }
        size = 0;
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

