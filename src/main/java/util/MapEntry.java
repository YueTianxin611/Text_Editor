package util;

public class MapEntry<K,V>
{
    private K key;
    private V value;

    /**
     * Create a new MapEntry
     * @param key the Key of an entry
     * @param value the value of an entry
     */
    public MapEntry(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * get the key of a map entry
     * @return key
     */
    public K getKey() {
        return key;
    }

    /**
     * get the value of a map entry
     * @return valye
     */
    public V getValue() {
        return value;
    }

    /**
     * set the value of a map entry
     * @param value to be set
     */
    public void setValue(V value){
        this.value = value;
    }
}