package week4_binary_search_trees;

import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.awt.event.WindowStateListener;
import java.io.*;
import java.util.*;

class RopeProblem {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    class Vertex {
        char key;
        // size of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        long size;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(char key, long size, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.size = size;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    class VertexPair {
        Vertex left;
        Vertex right;

        VertexPair() {
        }

        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    class Tree {

        void update(Vertex v) {
            if (v == null) return;
            v.size = (v.left != null ? v.left.size : 0) + (v.right != null ? v.right.size : 0) + 1;
            if (v.left != null) {
                v.left.parent = v;
            }
            if (v.right != null) {
                v.right.parent = v;
            }
        }

        void smallRotation(Vertex v) {
            Vertex parent = v.parent;
            if (parent == null) {
                return;
            }
            Vertex grandparent = v.parent.parent;
            if (parent.left == v) {
                Vertex m = v.right;
                v.right = parent;
                parent.left = m;
            } else {
                Vertex m = v.left;
                v.left = parent;
                parent.right = m;
            }
            update(parent);
            update(v);
            v.parent = grandparent;
            if (grandparent != null) {
                if (grandparent.left == parent) {
                    grandparent.left = v;
                } else {
                    grandparent.right = v;
                }
            }
        }

        void bigRotation(Vertex v) {
            if (v.parent.left == v && v.parent.parent.left == v.parent) {
                // Zig-zig
                smallRotation(v.parent);
                smallRotation(v);
            } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
                // Zig-zig
                smallRotation(v.parent);
                smallRotation(v);
            } else {
                // Zig-zag
                smallRotation(v);
                smallRotation(v);
            }
        }

        // Makes splay of the given vertex and returns the new root.
        Vertex splay(Vertex v) {
            if (v == null) return null;
            while (v.parent != null) {
                if (v.parent.parent == null) {
                    smallRotation(v);
                    break;
                }
                bigRotation(v);
            }
            return v;
        }

        // Searches for the given kth in the tree with the given root
        // and calls splay for the deepest visited node after that.
        // Returns pair of the result and the new root.
        // If found, result is a pointer to the node with the given key.
        // If the kth is bigger than the tree,then result is null.
        VertexPair findKth(Vertex root, long kth) {
            Vertex v = root;
            Vertex last = root;
//			Vertex next = null;
//			long nexts = 0;
            while (v != null) {
                long s = v.left == null ? 0 : v.left.size;
//				if (s+1 >= kth && (next == null || s+1 < nexts)) {
//					next = v;
//					nexts = next.left == null ? 0 : next.left.size;
//				}
                last = v;
                if (kth == s) {
                    break;
                }
                if (kth < s) {
                    if (v.left == null)
                        break;
                    v = v.left;
                } else {
                    v = v.right;
                    kth = kth - s - 1;
                }
            }

            root = splay(last);
            return new VertexPair(v, root);
        }

        VertexPair split(Vertex root, int kth) {
            VertexPair result = new VertexPair();
            VertexPair findAndRoot = findKth(root, kth);
            root = findAndRoot.right;
            result.right = findAndRoot.left;
            if (result.right == null) {
                result.left = root;
                return result;
            }
            result.right = splay(result.right);
            result.left = result.right.left;
            result.right.left = null;
            if (result.left != null) {
                result.left.parent = null;
            }
            update(result.left);
            update(result.right);
            return result;
        }

        Vertex merge(Vertex left, Vertex right) {
            if (left == null) return right;
            if (right == null) return left;
            while (right.left != null) {
                right = right.left;
            }
            right = splay(right);
            right.left = left;
            update(right);
            return right;
        }

        Vertex insert(Vertex root, Vertex new_vertex, int kth) {
            VertexPair splitV = split(root, kth);
            root = merge(merge(splitV.left, new_vertex), splitV.right);
            return root;
        }
        // Code that uses splay tree to solve the problem

        Vertex root = null;

        public Tree(String s) {
            char[] list = s.toCharArray();
            String s2;
            for (int i = 0; i < list.length; i++) {
                Vertex new_vertex = new Vertex(list[i], 1, null, null, null);
                root = insert(root, new_vertex, i);
                s2 = result();
            }

        }

        void process(int from, int to, int k) {
            VertexPair split1 = split(root, from);
            VertexPair split2 = split(split1.right, to - from + 1);
            Vertex middle = split2.left;
            root = merge(split1.left, split2.right);
            root = insert(root, middle, k);
        }

        String inOrderTraversal(String s, Vertex v) {
            if (v == null)
                return "";
            s = inOrderTraversal(s, v.left);
            s += v.key;
            s += inOrderTraversal(s, v.right);
            return s;
        }

        String result() {
            return inOrderTraversal("", root);
        }
    }

    class Rope {
        String s;
        Tree ropeTree;

        void process_naive(int i, int j, int k) {
            // Replace this code with a faster implementation
            String t = s.substring(0, i) + s.substring(j + 1);
            s = t.substring(0, k) + s.substring(i, j + 1) + t.substring(k);
        }

        void process(int i, int j, int k) {
            ropeTree.process(i, j, k);
        }

        String result() {
            return ropeTree.result();
        }

        Rope(String s) {
            ropeTree = new Tree(s);
            this.s = s;
        }

    }

    public static void main(String[] args) throws IOException {
        new RopeProblem().run();
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        Rope rope = new Rope(in.next());
        for (int q = in.nextInt(); q > 0; q--) {
            int i = in.nextInt();
            int j = in.nextInt();
            int k = in.nextInt();
            rope.process(i, j, k);
        }
        out.println(rope.result());
        out.close();
    }
}
