package ds;

public class StackLinedList<T> {
    LinkedList list = new LinkedList<T>();

    public void push(T key) {
        list.pushFront(key);
    }

    public T peek() {
        if (list.empty()) throw new Error("stack is empty");
        return (T) list.topFront().key;
    }

    public T pop() {
        if (list.empty()) throw new Error("stack is empty");
        return (T) list.popFront().key;
    }

    public boolean empty() {
        return list.empty();
    }
}
