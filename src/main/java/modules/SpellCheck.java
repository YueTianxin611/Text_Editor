package modules;

import util.*;


public class SpellCheck implements SpellCheckModule{

    String method;
    BloomFilter<String> bl;
    Trie trie;
    boolean useBF;

    /**
     * Create a SpellCheck using Bloom Filter as default
     */
    public SpellCheck(){
        useBF = true;
        bl = new BloomFilter<>(100000, 5);
    }

    /**
     * Create a SpellCheck
     *
     * @param method Method to be used by this module, choose between "BloomFilter" and "Trie"
     */
    public SpellCheck(String method){
        this.method = method;
        if(method.equals("BloomFilter")){
            useBF=true;
            bl = new BloomFilter<>(100000, 5);
        }
        else if(method.equals("Trie")){
            this.useBF = false;
            trie = new Trie();
        }
    }

    /**
     * Add word to a bloom filter or a trie
     * @param word Word to be added
     */
    @Override
    public void addWord(String word) {
        if(useBF)
            this.bl.insert(word);
        else
            trie.insert(word);
    }

    /**
     * Whether a word is in the Bloom filter or is in the Trie
     * @param word Word to be tested
     * @return true if a word is found, false if not
     */
    @Override
    public boolean isValidWord(String word) {
        if(useBF)
            return bl.mightContain(word);
        else
            return trie.contains(word);
    }
}