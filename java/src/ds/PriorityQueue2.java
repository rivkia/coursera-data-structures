package ds;

public class PriorityQueue2<T extends Comparable<T>> {
    public T[] H;
    int size;
    int maxSize;

    public PriorityQueue2(T[] A, int size, int n) {
        H = A;
        size = size;
        maxSize = n;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void siftUp(int i) {
        while (i > 0 && H[parent(i)].compareTo(H[i]) == 0) {
            swap(H, parent(i), i);
            i = parent(i);
        }
    }

    private void siftDown(int i) {
        int maxIndex = i;
        int l = leftChild(i);
        if (l < size && H[l].compareTo(H[maxIndex]) > 0)
            maxIndex = l;
        int r = rightChild(i);
        if (r < size && H[r].compareTo(H[maxIndex]) > 0)
            maxIndex = r;
        if (i != maxIndex) {
            swap(H, i, maxIndex);
            siftDown(maxIndex);
        }
    }

    private void swap(T[] H, int i, int j) {
        T temp = H[i];
        H[i] = H[j];
        H[j] = temp;
    }

    public boolean empty() {
        return size > 0;
    }

    public T getMax() {
        if (size == 0) throw new Error("Empty");
        return H[0];
    }

    public void insert(T p) {
        if (size == maxSize)
            throw new Error("Capacity is Full");
        size++;
        H[size - 1] = p;
        siftUp(size - 1);
    }

    public T extractMax() {
        if (size == 0) throw new Error("Empty");
        T result = H[0];
        H[0] = H[size - 1];
        size--;
        siftDown(0);
        return result;
    }

    public void changePriority(int i, T p) {
        T oldp = H[i];
        H[i] = p;
        if (p.compareTo(oldp) > 0)
            siftUp(i);
        else
            siftDown(i);
    }

   /* public void remove(int i) {
        H[i] = Integer.MAX_VALUE;
        siftUp(i);
        extractMax();
    }*/
}
