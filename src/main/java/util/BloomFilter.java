package util;

import java.util.Collection;

/**
 * A collection of elements of type E for which the only operation is a probabilistic membership
 * test.
 */
public class BloomFilter<E>{
    int[] array;
    int bitarrayLen;
    int numHashFunctions;

    /**
     * Create a new Bloom filter with {@code elems} inside. The bit array is of length 8 * numBytes.
     * The Bloom filter uses the specified number of hash functions.
     *
     * @param elems The collection of elements to be added to this filter
     * @param numBytes The length of the byte array representing bit array
     * @param numHashFunctions The number of hash functions to be used in this filter
     */
    public BloomFilter(Collection<E> elems, int numBytes, int numHashFunctions) {
        // TODO implement
        this.bitarrayLen = 8*numBytes;
        this.array = new int[8*numBytes];
        this.numHashFunctions = numHashFunctions;

        for(int i=0;i<numBytes;i++){
            array[i] = 0;
        }

        for(E e:elems){
            insert(e);
        }
    }

    /**
     * Create a new Bloom filter. The bit array is of length 8 * numBytes.
     * The Bloom filter uses the specified number of hash functions.
     * @param numBytes The length of the byte array representing bit array
     * @param numHashFunctions The number of hash functions to be used in this filter
     */
    public BloomFilter(int numBytes, int numHashFunctions){
        this.bitarrayLen = 8*numBytes;
        this.array = new int[8*numBytes];
        this.numHashFunctions = numHashFunctions;

        for(int i=0;i<numBytes;i++){
            array[i] = 0;
        }
    }

    /** Add {@code elem} to the Bloom filter. */
    public void insert(E elem) {
        int[] hasharray = hash_code(elem);

        for(int i=0; i<numHashFunctions;i++){
            array[hasharray[i]] = 1;
        }
    }

    /** Check whether {@code elem} might be in the collection. */
    public boolean mightContain(E elem) {
        int[] hasharray = hash_code(elem);

        for(int i=0; i< numHashFunctions; i++){
            if(array[hasharray[i]]==0)
                return false;
        }
        return true;
    }

    /**
     * Get the hash code of an element by converting the element to a string.
     * Add a single char to the end of the string and get the new string's hashcode.
     * @param element The given element
     * @return The hash code of the given element
     */
    public int[] hash_code(E element){
        String string = element.toString();
        int[] hasharray = new int[numHashFunctions];

        for(int i=0; i<numHashFunctions; i++){
            char c = (char)(i+97);
            string = string+c;
            hasharray[i] = (string.hashCode() & 0x7fffffff)% bitarrayLen;
        }

        return hasharray;
    }
}
