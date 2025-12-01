package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {
        private final K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K k, V v, BSTNode l, BSTNode r) {
            key = k;
            value = v;
            left = l;
            right = r;
        }
    }

    BSTNode root;
    public int size;

    public BSTMap() {
        root = null;
    }
    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return getHelper(key, root, false) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        BSTNode b = getHelper(key, root, false);
        return b == null ? null: b.value;
    }

    private BSTNode getHelper(K key, BSTNode tree, boolean put) {
        if (tree == null) {
            return null;
        }
        int compResult = key.compareTo(tree.key);
        if (compResult == 0) {
            return tree;
        } else if (compResult < 0) {
            if (put && tree.left == null) {
                return tree;
            }
            return getHelper(key, tree.left, put);
        }
        if (put && tree.right == null) {
            return tree;
        }
        return getHelper(key, tree.right, put);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value, null, null);
            size = 1;
            return;
        }
        BSTNode b = getHelper(key, root, true);
        BSTNode n = new BSTNode(key, value, null, null);
        int compResult = key.compareTo(b.key);
        if (compResult == 0) {
            b.value = value;
            return;
        } else if (compResult < 0) {
            size += 1;
            b.left = n;
            return;
        }
        size += 1;
        b.right = n;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new TreeSet<>();
        addKeys(root, set);
        return set;
    }

    private void addKeys(BSTNode node, Set<K> set) {
        if (node == null) return;
        addKeys(node.left, set);
        set.add(node.key);
        addKeys(node.right, set);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        BSTNode b = getHelper(key, root, false);
        if (b == null) {
            return null;
        }
        if (b.right == null && b.left == null) {
            BSTNode n = getHelper(key, root, true);
            V value = b.value;
            if (n.left == b) {
                n.left = null;
            }
            n.right = null;
            return value;
        }else if (b.right == null) {
            BSTNode n = getHelper(key, root, true);
            V value = b.value;
            if (n.left == b) {
                n.left = b.left;
            }
            n.right = b.left;
            return value;
        }else if (b.left == null) {
            BSTNode n = getHelper(key, root, true);
            V value = b.value;
            if (n.left == b) {
                n.left = b.right;
            }
            n.right = b.right;
            return value;
        }
        return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("keySet() is not implemented in this map.");
    }

    public void printInOrder() {
        printHelper(root);
    }

    private void printHelper(BSTNode n) {
        if (n == null) return;
        printHelper(n.left);
        System.out.println(n.key + " -> " + n.value);  // 中序就是从小到大
        printHelper(n.right);
    }


}
