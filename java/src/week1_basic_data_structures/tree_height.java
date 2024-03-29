package week1_basic_data_structures;

import java.util.*;
import java.io.*;

public class tree_height {
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

    public class TreeHeight {
        int n;
        int parent[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight_Naive() {
            // Replace this code with a faster implementation
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;
                for (int i = vertex; i != -1; i = parent[i])
                    height++;
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }

        int calculateHeight(int[] parent, int parentHeight[], int vertex) {
            if (parentHeight[vertex] != 0)
                return parentHeight[vertex];
            int height = 0;
            if (parent[vertex] == -1)
                height = 1;
            else
                height = 1 + calculateHeight(parent, parentHeight, parent[vertex]);
            parentHeight[vertex] = height;
            return height;
        }


        int computeHeight() {
            int[] parentHeight = new int[n];
            // Replace this code with a faster implementation
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) {
                int height = calculateHeight(parent, parentHeight, vertex);
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
