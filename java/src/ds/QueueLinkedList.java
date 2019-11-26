package ds;

public class QueueLinkedList<T> {
    LinkedList list = new LinkedList<T>();

    public void enqueue(T key) {
        list.pushBack(key);
    }

    public T top() {
        return (T) list.tobBack().key;
    }
    public int size() {
        return list.size();
    }
    public T dequeue() {
        if (list.empty()) throw new Error("stack is empty");
        return (T) list.popFront().key;
    }

    public boolean empty() {
        return list.empty();
    }
}
