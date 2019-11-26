package ds;

public class Queue {
    int[] list = new int[100];
    int read = 0;
    int write = 0;
    int len = 0;

    public void Enqueue(int key) {
        if (len == list.length)
            throw new Error("Queue is Full");
        list[write++] = key;
        if (write == list.length) write = 0;
        len++;
    }

    public int Dequeue() {
        if (len == 0) throw new Error("stack is empty");
        int key = list[read];
        read--;
        len--;
        if (read == list.length) read = 0;
        return key;
    }

    public boolean Empty() {
        return len == 0;
    }

    private void IncreaseList() {
        int[] list2 = new int[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            list2[i] = list[i];
        }
        list = list2;
    }
}
