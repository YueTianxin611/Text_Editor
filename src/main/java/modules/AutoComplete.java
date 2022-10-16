package modules;

import util.Trie;

public class AutoComplete implements AutoCompleteModule{

    Trie trie;

    /**
     * Auto complete constructor
     * Use Trie to complete this module
     */
    public AutoComplete(){
        trie = new Trie();
    }

    /**
     * Add word to this trie
     * @param word to be added to this trie
     */
    @Override
    public void addWord(String word) {
        trie.insert(word);
    }

    /**
     * Get the shortest word that contains the given prefix
     * Use closestWordToPrefix(prefix) in trie to implement the method
     * @param prefix given prefix
     * @return the shortest word that contains the given prefix
     */
    @Override
    public String getWordForPrefix(String prefix) {
        return trie.closestWordToPrefix(prefix);
    }
}
