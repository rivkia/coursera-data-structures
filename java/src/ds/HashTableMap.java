package ds;


public class HashTableMap<T> {
    class HashTableData {
        String key;
        T value;

        HashTableData(String key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    private int m;
    private int prime = 1000000007;
    private int multiplier = 263;
    private LinkedList<HashTableData>[] hashTable;

    public HashTableMap(int m) {
        this.m = m;
        hashTable = new LinkedList[m];
        for (int i = 0; i < m; i++) {
            hashTable[i] = new LinkedList<HashTableData>();
        }
    }

    public void set(String key, T value) {
        int index = hashFunc(key);
        if (find(hashTable[index], key) == null)
            hashTable[index].pushFront(new HashTableData(key, value));
    }

    public T get(String key) {
        int index = hashFunc(key);
        HashTableData data = find(hashTable[index], key);
        return data == null ? null : data.value;
    }

    public boolean hasKey(String key) {
        int index = hashFunc(key);
        LinkedList list = hashTable[index];

        return find(list, key) != null;
    }

    public HashTableData find(LinkedList<HashTableData> list, String key) {
        LinkedList.Node p = list.head;
        while (p.next != null && ((HashTableData) p.key).key != key) {
            p = p.next;
        }
        return ((HashTableData) p.key).key != key ? (HashTableData) p.key : null;
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int) hash % m;
    }
}
