package ds;

public class Stack {
    int[] list = new int[100];
    int len = 0;

    public void push(int key) {
        if (len == list.length) increaseList();
        list[len++] = key;
    }

    public int top() {
        if (len == 0) throw new Error("stack is empty");
        return list[len - 1];
    }

    public int pop() {
        if (len == 0) throw new Error("stack is empty");
        int key = list[len - 1];
        list[len - 1] = 0;
        len--;
        return key;
    }

    public boolean empty() {
        return len == 0;
    }

    private void increaseList() {
        int[] list2 = new int[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            list2[i] = list[i];
        }
        list = list2;
    }
}
