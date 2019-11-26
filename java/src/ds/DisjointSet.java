package ds;

class Node {
    Node parent;
    int rank;
    int data;

    Node(int data) {
        data = data;
        rank = 0;
        parent = this;
    }

    Node getParent() {
        // find super parent and compress path
        if (this != parent) {
            Node parent_node = this.parent;
            this.parent = parent_node.getParent();
        }
        return parent;
    }
}

public class DisjointSet {
    Node[] sets;
    DisjointSet(int n){
        sets = new Node[n];
    }
    void union(Node destination, Node source) {
        Node realDestination = destination.getParent();
        Node realSource = source.getParent();
        if (realDestination == realSource) {
            return;
        }
        int size;
        if (realDestination.rank > realSource.rank) {
            realSource.parent = realDestination.parent;
        } else {
            realDestination.parent = realSource.parent;
            if (realDestination.rank == realSource.rank)
                realSource.rank++;
        }
    }

}
