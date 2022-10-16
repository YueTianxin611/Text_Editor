package test;
import modules.SpellCheck;
import util.*;

import java.io.*;

public class SpellCheckRuntime {

    public static void useBloomFilter() throws IOException {
        SpellCheck sc_bf = new SpellCheck("BloomFilter");

        BufferedReader br = new BufferedReader(new FileReader("files/USdict.txt"));
        //BufferedWriter bw = new BufferedWriter(new FileWriter("perf/SpellCheck_bf_add.txt"));
        BufferedWriter bw_query = new BufferedWriter(new FileWriter("perf/SpellCheck_bf_isvalid.txt"));

        for (String line; (line = br.readLine()) != null; ) {
            sc_bf.addWord(line);
        }

        long nanotime_bf = System.nanoTime();
        bw_query.write(String.valueOf(nanotime_bf));

        BufferedReader br_query = new BufferedReader(new FileReader("files/USdict.txt"));
        for (String line; (line = br_query.readLine()) != null; ) {
            sc_bf.isValidWord(line);
            nanotime_bf = System.nanoTime();
            bw_query.newLine();
            bw_query.write(String.valueOf(nanotime_bf));
        }

    }

    public static void useTrie() throws IOException {
        SpellCheck sc_trie = new SpellCheck("Trie");

        BufferedReader br = new BufferedReader(new FileReader("files/USdict.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("perf/SpellCheck_trie_add.txt"));

        long nanotime_trie = System.nanoTime();

        bw.write(String.valueOf(nanotime_trie));

        for (String line; (line = br.readLine()) != null; ) {

            sc_trie.addWord(line);
            nanotime_trie = System.nanoTime();
            bw.newLine();
            bw.write(String.valueOf(nanotime_trie));
        }
    }



    public static void main(String[] args) throws IOException {
        useBloomFilter();
        //useTrie();
    }


}
