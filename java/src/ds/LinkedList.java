package ds;

// Java program to implement
// a Singly Linked List
public class LinkedList<T> {

    Node head; // head of list
    Node tail; // tail of list
    int size = 0;
    public int size() {
        return size;
    }

    // Linked list Node.
    // This inner class is made static
    // so that main() can access it
    class Node {

        T key;
        Node next;

        // Constructor
        Node(T _key) {
            key = _key;
            next = null;
        }
    }

    // **************INSERTION**************

    public void pushFront(T key) {
        Node new_node = new Node(key);
        new_node.next = this.head;
        head = new_node;
        if (tail == null)
            tail = head;
        size++;
    }

    public Node topFront() {
        return head;
    }

    public Node popFront() {
        if (head == null)
            return null;
        Node front = head;
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return front;
    }

    public void pushBack(T key) {
        Node new_node = new Node(key);
        if (tail == null) {
            tail = head = new_node;
        } else {
            tail.next = new_node;
            tail = new_node;
        }
        size++;
    }

    public Node tobBack() {
        return tail;
    }

    public Node popBack() {
        if (head == null)
            return null;//empty list
        Node back = tail;
        if (head == tail) {
            head = tail = null;
        } else {
            Node p = head;
            while (p.next.next != null)
                p = p.next;
            p.next = null;
            tail = p;
        }
        size--;
        return back;
    }

    public void addBefore(Node node, T key) {
        if (head == node) {
            pushFront(key);
            return;
        }

        Node p = head;
        while (p.next.next != null && p.next == node)
            p = p.next;
        //error! not found
        if (p.next != node)
            return;
        Node new_node = new Node(key);
        new_node.next = node;
        p.next = new_node;
        size++;
    }

    public void addAfter(Node node, T key) {
        Node node2 = new Node(key);
        node2.next = node.next;
        node.next = node2;
        if (tail == node)
            tail = node2;
        size++;
    }

    public Node find(T key) {
        Node p = head;
        while (p.next != null && p.key != key) {
            p = p.next;
        }
        return p.key == key ? p : null;
    }

    public Node earse(T key) {
        Node node_to_delete = null;
        if (head.key == key) {
            size--;
            node_to_delete = head;
            if (head == tail)
                head = tail = null;
            else head = head.next;
            return node_to_delete;
        }
        Node p = head;
        while (p.next.next != null && p.next.key != key)
            p = p.next;
        //not found
        if (p.next.key != key)
            return null;
        if (p.next == tail) {
            tail = p;
        }
        node_to_delete = p.next;
        p.next = p.next.next;
        size--;
        return node_to_delete;
    }

    public Boolean empty() {
        return head == null;
    }
  /*  // Method to insert a new node without tail
    public static LinkedList insert(LinkedList list, T key) {
        // Create a new node with given key
        Node new_node = new Node(key);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        // Return the list by head
        return list;
    }
*/
    // **************TRAVERSAL**************

    // Method to print the LinkedList.
    public void printList(LinkedList list) {
        Node currNode = list.head;

        System.out.print("\nLinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the key at current node
            System.out.print(currNode.key + " ");

            // Go to next node
            currNode = currNode.next;
        }
        System.out.println("\n");
    }

    // **************DELETION BY KEY**************

    // Method to delete a node in the LinkedList by KEY
    public  LinkedList deleteByKey(LinkedList list, T key) {
        // Store head node
        Node currNode = list.head, prev = null;

        //
        // CASE 1:
        // If head node itself holds the key to be deleted

        if (currNode != null && currNode.key == key) {
            list.head = currNode.next; // Changed head

            // Display the message
            System.out.println(key + " found and deleted");

            // Return the updated List
            return list;
        }

        //
        // CASE 2:
        // If the key is somewhere other than at head
        //

        // Search for the key to be deleted,
        // keep track of the previous node
        // as it is needed to change currNode.next
        while (currNode != null && currNode.key != key) {
            // If currNode does not hold key
            // continue to next node
            prev = currNode;
            currNode = currNode.next;
        }

        // If the key was present, it should be at currNode
        // Therefore the currNode shall not be null
        if (currNode != null) {
            // Since the key is at currNode
            // Unlink currNode from linked list
            prev.next = currNode.next;

            // Display the message
            System.out.println(key + " found and deleted");
        }

        //
        // CASE 3: The key is not present
        //

        // If key was not present in linked list
        // currNode should be null
        if (currNode == null) {
            // Display the message
            System.out.println(key + " not found");
        }

        // return the List
        return list;
    }

    // **************DELETION AT A POSITION**************

    // Method to delete a node in the LinkedList by POSITION
    public  LinkedList deleteAtPosition(LinkedList list, int index) {
        // Store head node
        Node currNode = list.head, prev = null;

        //
        // CASE 1:
        // If index is 0, then head node itself is to be deleted

        if (index == 0 && currNode != null) {
            list.head = currNode.next; // Changed head

            // Display the message
            System.out.println(index + " position element deleted");

            // Return the updated List
            return list;
        }

        //
        // CASE 2:
        // If the index is greater than 0 but less than the size of LinkedList
        //
        // The counter
        int counter = 0;

        // Count for the index to be deleted,
        // keep track of the previous node
        // as it is needed to change currNode.next
        while (currNode != null) {

            if (counter == index) {
                // Since the currNode is the required position
                // Unlink currNode from linked list
                prev.next = currNode.next;

                // Display the message
                System.out.println(index + " position element deleted");
                break;
            } else {
                // If current position is not the index
                // continue to next node
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }

        // If the position element was found, it should be at currNode
        // Therefore the currNode shall not be null
        //
        // CASE 3: The index is greater than the size of the LinkedList
        //
        // In this case, the currNode should be null
        if (currNode == null) {
            // Display the message
            System.out.println(index + " position element not found");
        }

        // return the List
        return list;
    }
}
