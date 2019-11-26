package ds;

public class PriorityQueue {
    public int[] H;
    int size;
    int maxSize;

    public PriorityQueue(int n) {
        H = new int[n];
        maxSize = size = n;

    }

    public PriorityQueue(int[] A) {
        H = A;
        maxSize = size = A.length;
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
        while (i > 0 && H[parent(i)] < H[i]) {
            swap(H, parent(i), i);
            i = parent(i);
        }
    }

    private void siftDown(int i) {
        int maxIndex = i;
        int l = leftChild(i);
        if (l < size && H[l] > H[maxIndex])
            maxIndex = l;
        int r = rightChild(i);
        if (r < size && H[r] > H[maxIndex])
            maxIndex = r;
        if (i != maxIndex) {
            swap(H, i, maxIndex);
            siftDown(maxIndex);
        }
    }

    private static void swap(int[] H, int i, int j) {
        int temp = H[i];
        H[i] = H[j];
        H[j] = temp;
    }

    public boolean empty() {
        return size > 0;
    }

    public int getMax() {
        if (size == 0) throw new Error("Empty");
        return H[0];
    }

    public void insert(int p) {
        if (size == maxSize)
            throw new Error("Capacity is Full");
        size++;
        H[size - 1] = p;
        siftUp(size - 1);
    }

    public int extractMax() {
        if (size == 0) throw new Error("Empty");
        int result = H[0];
        H[0] = H[size - 1];
        size--;
        siftDown(0);
        return result;
    }

    public void changePriority(int i, int p) {
        int oldp = H[i];
        H[i] = p;
        if (p > oldp)
            siftUp(i);
        else
            siftDown(i);
    }

    public void remove(int i) {
        H[i] = Integer.MAX_VALUE;
        siftUp(i);
        extractMax();
    }

    public static void heapSort(int[] A) {
        PriorityQueue pq = new PriorityQueue(A.length);
        for (int i = 0; i < A.length; i++) {
            pq.insert(A[i]);
        }
        for (int i = A.length - 1; i >= 0; i--) {
            A[i] = pq.extractMax();
        }
    }

    public static PriorityQueue buildHeap(int[] A) {
        PriorityQueue pq = new PriorityQueue(A);
        int size = A.length;
        for (int i =  (size-1) / 2; i >= 0; i--) {
            pq.siftDown(i);
        }
        return pq;
    }

    public static void heapSortInPlace(int[] A) {
        PriorityQueue pq = buildHeap(A);
        for (int i = 0; i < A.length; i++) {
            swap(A, 0, pq.size - 1);
            pq.size--;
            pq.siftDown(0);
        }
    }

    public static void heapSort_partialSort(int[] A, int k) {
        PriorityQueue pq = buildHeap(A);
        for (int i = 0; i < k; i++) {
            A[A.length - i - 1] = pq.extractMax();
        }
    }

}
