package test;

import modules.AutoComplete;
import modules.Search;
import modules.SpellCheck;
import util.BloomFilter;
import util.HashTable;
import util.Trie;
import util.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Test {

	@org.junit.jupiter.api.Test
	void testHashMap() {
		HashTable<String, Integer> map = new HashTable<>(2);
		assertEquals(1,map.put("A", 1));
		map.put("B", 2);
		map.put("C", 3);
		map.put("D", 4);
		map.remove("A");
		assertEquals(3,map.size());
		assertTrue(map.containsValue(2));
		assertFalse(map.containsKey("A"));
		assertNull(map.get("A"));
		assertEquals(map.get("C"), 3);
		assertFalse(map.isEmpty());
		assertEquals(map.keySet().size(), 3);
		assertEquals(false, map.keySet().contains("A"));
		map.clear();
		assertEquals(0,map.size());

		Map<String, Integer> all = new HashTable<>(10);
		all.put("E", 5);
		all.put("F", 6);
		all.put("G",7);
		map.putAll(all);
		assertEquals(3,map.size());

	}

	@org.junit.jupiter.api.Test
	void testTrie() {
		Trie trie = new Trie();
		trie.insert("CS2110");
		trie.insert("CS2112");
		trie.insert("CS");
		trie.delete("CS2112");
		assertEquals(false, trie.contains("CS2112"));
		assertEquals(true, trie.contains("CS"));
		assertEquals("CS2110", trie.closestWordToPrefix("CS2"));
	}

	@org.junit.jupiter.api.Test
	void testBloomFilter(){
		BloomFilter bl = new BloomFilter<>(10000000, 5);
		try (BufferedReader br = new BufferedReader(new FileReader("files/USdict.txt"))) {
			for (String line; (line = br.readLine()) != null; ) {
				bl.insert(line);
			}
		} catch (IOException ioe) {
			System.err.println("Error reading provided dictionary file.");
		}

		assertEquals(false, bl.mightContain("djis"));
		assertEquals(true, bl.mightContain("hello"));
		assertEquals(true, bl.mightContain("hat"));
		assertEquals(false, bl.mightContain("rng"));
	}

	@org.junit.jupiter.api.Test
	void testAutoComplete(){
		AutoComplete auto = new AutoComplete();
		auto.addWord("hello");
		auto.addWord("world");
		auto.addWord("my");
		auto.addWord("name");
		auto.addWord("TianxinYue");
		assertEquals("TianxinYue", auto.getWordForPrefix("Ti"));
	}

	@org.junit.jupiter.api.Test
	void testSearch(){
		Search search = new Search();
		int have = search.find("ECE", "Hello my name is Tianxin Yue I am major in ECE");
		int none = search.find("LPL", "Hello my name is Tianxin Yue I am major in ECE");
		assertEquals(43, have);
		assertEquals(-1, none);
	}

	@org.junit.jupiter.api.Test
	void testSpellCheck(){
		SpellCheck sc = new SpellCheck();
		try (BufferedReader br = new BufferedReader(new FileReader("files/USdict.txt"))) {
			for (String line; (line = br.readLine()) != null; ) {
				sc.addWord(line);
			}
		} catch (IOException ioe) {
			System.err.println("Error reading provided dictionary file.");
		}

		assertEquals(true, sc.isValidWord("name"));
		assertEquals(false, sc.isValidWord("rng"));
		assertEquals(false, sc.isValidWord("shiudda"));
		assertEquals(true, sc.isValidWord("maintain"));

	}



}
