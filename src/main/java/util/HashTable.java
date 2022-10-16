package util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class HashTable<K, V> implements Map<K, V> {
    int size;
    LinkedList<MapEntry>[] hashmap;
    double collision_time = 0;
    int numElements;
    final double load_factor_thre = 0.75;

    /**
     * Create a new hash table.
     *
     * @param numElements A guess at the number of elements
     *                    the hash table will eventually contain,
     *                    as a hint for improving performance.
     */
    public HashTable(int numElements) {
        this.numElements = numElements;
        hashmap = new LinkedList[numElements];
    }

    /**
     * Get the size of the hash table
     *
     * @return size of hash table
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Whether a hash table is empty or not
     *
     * @return True if the size of hash table is zero
     *         False if the size of hash table is not zero
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Whether the hash table contains the given Key
     * Use get method
     * The runtime should be O(1)
     *
     * @param key key whose presence in this map is to be tested
     * @return True if the hash table contains the Key
     *         False if the Key not find in the hash table
     */
    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /**
     * Whether the hash table contains the given Value
     *
     * @param value value whose presence in this map is to be tested
     * @return True if the hash table contains the Value
     *         False if the Value not find in the hash table
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < hashmap.length; i++) {
            if (hashmap[i] == null)
                continue;

            for (MapEntry e : hashmap[i]) {
                if (value.equals(e.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the value of a given key
     * The average run time is O(1)
     * Get the hash code by using Object.hashCode()
     *
     * @param key the key whose associated value is to be returned
     * @return V The value of the key, return null if the value not find
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % hashmap.length;

        if (index < 0)
            index = -index;

        if (hashmap[index] == null)
            return null;

        for (MapEntry e : hashmap[index]) {
            if (key.equals(e.getKey())) {
                return (V) e.getValue();
            }
        }
        return null;
    }

    /**
     * Put an entry to the hash table
     * The run time is O(1)
     * If load_factor exceeds the threshold, call resize() function to resize the hash table
     * If there is a collision, using chaining method to add entry in a linked list
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return V The value put in the hash map
     */
    @Override
    public V put(K key, V value) {
        if (size / hashmap.length > load_factor_thre)
            resize();

        int index = key.hashCode() % hashmap.length;

        if (index < 0)
            index = -index;

        if (hashmap[index] == null) {
            hashmap[index] = new LinkedList<>();
            hashmap[index].add(new MapEntry<>(key, value));
            size++;
            return value;
        } else {
            collision_time++;
            for (MapEntry e : hashmap[index]) {
                if (e.getKey().equals(key)) {
                    e.setValue(value);
                    return value;
                }
            }
            hashmap[index].add(new MapEntry<>(key, value));
            size++;
        }

        return value;
    }

    /**
     * Remove an entry of the given key
     * The run time is O(1)
     *
     * @param key key whose mapping is to be removed from the map
     * @return V The value of the deleted entry, return null if no entry be deleted
     */
    @Override
    public V remove(Object key) {
        if (get(key) == null)
            return null;

        int index = key.hashCode() % hashmap.length;

        if (index < 0)
            index = -index;

        for (MapEntry e : hashmap[index]) {
            if (e.getKey().equals(key)) {
                hashmap[index].remove(e);
                size--;
                return (V) e.getValue();
            }
        }

        return null;
    }

    /**
     * Put all entries in a Map to the hash table
     * put(K, V) method is used, run time is O(map.size())
     *
     * @param m mappings to be stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K key : m.keySet()) {
            put(key, m.get(key));
        }
    }

    /**
     * Remove all the entries in a hash table
     * Reset the size to zero
     */
    @Override
    public void clear() {
        hashmap = new LinkedList[size];
        size = 0;
    }

    /**
     * Get the key set of a hash table
     * mySet is implemented in util
     *
     * @return Set<K> the key set
     */
    @Override
    public Set<K> keySet() {
        Set<K> keyset = new mySet<>();

        for (int i = 0; i < hashmap.length; i++) {
            if (hashmap[i] != null) {
                for (MapEntry e : hashmap[i]) {
                    keyset.add((K) e.getKey());
                }
            }
        }
        return keyset;
    }

    /**
     * Resize the hash table, expand the size to its twice
     * Use put(K, V) method to put old table entries to new table
     */
    public void resize() {
        LinkedList<MapEntry>[] oldhashmap = hashmap;

        hashmap = new LinkedList[size * 2];
        numElements = size * 2;
        size = 0;
        collision_time = 0;

        for (int i = 0; i < oldhashmap.length; i++) {
            if (oldhashmap[i] == null)
                continue;

            for (MapEntry e : oldhashmap[i]) {
                put((K) e.getKey(), (V) e.getValue());
            }
        }
    }

    /**
     * Get the collision time of the hash table
     *
     * @return collision time
     */
    public double getCollision_time() {
        return collision_time;
    }

    /**
     * Get the empty buckets rate of the hash table
     * The rate is calculated by (total array size - size of hash table) / total array size
     *
     * @return empty buckets rate
     */
    public float getEmpty_buckets() {
        return (float) (numElements - size) / numElements;
    }

    /**
     * I don't implement those methods
     */
    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
