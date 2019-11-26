package ds;

// Java program to implement
// a Double Linked List
public class DoubleLinkedList<T> {

    Node head;
    Node tail;

    class Node {

        T key;
        Node next;
        Node prev;

        // Constructor
        Node(T _key) {
            key = _key;
            next = null;
            prev = null;
        }
    }

    public void PushFront(T key) {
        Node new_node = new Node(key);
        new_node.next = this.head;
        head = new_node;
        if (tail == null)
            tail = head;
    }

    public Node TopFront() {
        return head;
    }

    public Node PopFront() {
        if (head == null)
            return null;
        Node front = head;
        head = head.next;
        head.prev = null;
        if (head == null)
            tail = null;
        return front;
    }

    public void PushBack(T key) {
        Node new_node = new Node(key);
        if (tail == null) {
            tail = head = new_node;
        } else {
            tail.next = new_node;
            new_node.prev = tail;
            tail = new_node;
        }
    }

    public Node TobBack() {
        return tail;
    }

    public Node PopBack() {
        if (head == null)
            return null;//empty list
        Node back = tail;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        return back;
    }

    public void AddBefore(Node node, T key) {
        Node new_node = new Node(key);
        new_node.next = node;
        new_node.prev = node.prev;
        node.next = new_node;
        if (node.next != null)
            new_node.prev.next = new_node;
        if (head == node)
            head = new_node;
    }

    public void AddAfter(Node node, T key) {
        Node new_node = new Node(key);
        new_node.next = node.next;
        new_node.prev = node;
        node.next = new_node;
        if (new_node.next != null)
            new_node.next.prev = new_node;
        if (tail == node)
            tail = new_node;
    }

    public Node Find(T key) {
        Node p = head;
        while (p.next != null && p.key != key) {
            p = p.next;
        }
        return p.key == key ? p : null;
    }

    public Node Earse(T key) {
        Node node_to_delete = Find(key);
        if (node_to_delete == null)
            return null;

        if (head == node_to_delete) {
            if (head == tail)
                head = tail = null;
            else {
                head = head.next;
                head.prev = null;
            }
            return node_to_delete;
        }
        //not found
        if (node_to_delete == tail) {
            tail = node_to_delete.prev;
        }else{
            node_to_delete.next.prev = node_to_delete.prev;
        }
        node_to_delete.prev.next = node_to_delete.next;
        return node_to_delete;
    }

    public Boolean Empty() {
        return head == null;
    }
}
