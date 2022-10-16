package test;

import java.io.*;
import util.HashTable;

public class RuntimeTest {

    public static void HashTableget() throws IOException {
        HashTable<String, Integer> hash = new HashTable<>(1000);
        BufferedReader br = new BufferedReader(new FileReader("files/USdict.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("perf/HashTableGettime.txt"));

        int i = 0;
        for (String line; (line = br.readLine()) != null; ) {
            hash.put(line, i++);
        }


        long nanotime = System.nanoTime();
        bw.write(String.valueOf(nanotime));

        BufferedReader brnew = new BufferedReader(new FileReader("files/USdict.txt"));
        for (String line; (line = brnew.readLine()) != null; ) {
            hash.get(line);
            nanotime = System.nanoTime();
            bw.newLine();
            bw.write(String.valueOf(nanotime));
        }


    }

    public static void HashTablePuttime() throws IOException {
        HashTable<String, Integer> hash = new HashTable<>(1000);

        long nanotime = System.nanoTime();
        int i=0;

        BufferedWriter bw = new BufferedWriter(new FileWriter("perf/HashTablePuttime.txt"));
        bw.write(String.valueOf(nanotime));

        try (BufferedReader br = new BufferedReader(new FileReader("files/USdict.txt"))) {
            for (String line; (line = br.readLine()) != null; ) {
                hash.put(line,i++);
                nanotime = System.nanoTime();
                bw.newLine();
                bw.write(String.valueOf(nanotime));
            }
        } catch (IOException ioe) {
            System.err.println("Error reading provided dictionary file.");
        }
    }

    public static void collision() throws IOException {
        HashTable<String, Integer> hash = new HashTable<>(1000);
        BufferedReader br = new BufferedReader(new FileReader("files/USdict.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("perf/HashTableCollision.txt"));

        bw.write(0);

        int i=0;
        for (String line; (line = br.readLine()) != null; ) {
            hash.put(line, i++);
            bw.newLine();
            bw.write(String.valueOf(hash.getCollision_time()));
        }

    }

    public static void empty_b() throws IOException {
        HashTable<String, Integer> hash = new HashTable<>(1000);
        BufferedReader br = new BufferedReader(new FileReader("files/USdict.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("perf/HashTableEmptyBuckets.txt"));


        int i=0;
        for (String line; (line = br.readLine()) != null; ) {
            hash.put(line, i++);
            bw.write(String.valueOf(hash.getEmpty_buckets()));
            bw.newLine();
        }

    }

    public static void main(String[] args) throws IOException {
        System.out.println("Runtime analysis for HashTable:");
        HashTablePuttime();
        HashTableget();
        collision();
        empty_b();
    }


}
