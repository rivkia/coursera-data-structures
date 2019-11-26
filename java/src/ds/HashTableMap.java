package ds;

import java.util.ArrayList;
import java.util.List;

public class HashTableSet {
    private int m;
    private int prime = 1000000007;
    private int multiplier = 263;
    private List<String>[] hashTable;

    public HashTableSet(int m) {
        this.m = m;
        hashTable = new List[m];
        for (int i = 0; i < m; i++) {
            hashTable[i] = new ArrayList<>();
        }
    }
    public void add(String key){
        int index = hashFunc(key);
        if (!hashTable[index].contains(key))
            hashTable[index].add(key);
    }
    public void remove(String key){
        int index = find(key);
        if (index >= 0)
            hashTable[index].remove(key);
    }
    public int find(String key) {
        int index = hashFunc(key);
        return hashTable[index].contains(key) ? index : -1;
    }
    
    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int) hash % m;
    }
}
