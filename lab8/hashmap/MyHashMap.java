package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

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

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int initialSize = 16;
    private HashSet<K> keySet = new HashSet<>();
    private double loadFactor = 0.75;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(this.initialSize);
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        buckets = createTable(this.initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.loadFactor = maxLoad;
        buckets = createTable(this.initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new HashSet<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        Arrays.fill(buckets, null);
        keySet.clear();
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    private int hashing(K key) {
        int hashcode = key.hashCode();
        return (hashcode & 0x7fffffff) & (initialSize - 1);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if(!containsKey(key)) {
            return null;
        }
        int hashkey = hashing(key);
        for (Node n: buckets[hashkey]) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return keySet.size();
    }

    private void expandBucketsSize() {
        double factorLoad = (double) this.size() / buckets.length;
        if (factorLoad >= loadFactor) {
            this.initialSize *= 2;
            Collection<Node>[] newBuckets = createTable(this.initialSize);
            Collection<Node>[] oldBuckets = this.buckets;
            buckets = newBuckets;
            for (Collection<Node> bucket: oldBuckets) {
                if (bucket != null) {
                    for (Node n: bucket) {
                        int newIndex = hashing(n.key);
                        if (newBuckets[newIndex] == null) {
                            newBuckets[newIndex] = createBucket();
                        }
                        newBuckets[newIndex].add(n);
                    }
                }
            }
        }
    }
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if(containsKey(key)) {
            int hashkey = hashing(key);
            for (Node n: buckets[hashkey]) {
                if (n.key.equals(key)) {
                    n.value = value;
                }
            }
        }
        int hashkey = hashing(key);
        if (buckets[hashkey] == null) {
            buckets[hashkey] = createBucket();
        }
        buckets[hashkey].add(createNode(key, value));
        keySet.add(key);
        expandBucketsSize();
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return this.keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public Iterator<K> iterator() {
        return this.keySet.iterator();
    }
}
