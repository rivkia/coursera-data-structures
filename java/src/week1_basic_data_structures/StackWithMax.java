package week1_basic_data_structures;

import ds.StackLinedList;

import java.util.*;
import java.io.*;

public class StackWithMax {
    StackLinedList<Integer> stack = new StackLinedList<Integer>();
    StackLinedList<Integer> max_stack = new StackLinedList<Integer>();

    public void Push(int value) {
        stack.push(value);
        if (max_stack.empty())
            max_stack.push(value);
        else {
            int last_max_value = max_stack.peek();
            int max = last_max_value > value ? last_max_value : value;
            max_stack.push(max);
        }
    }

    public void Pop() {
        if (stack.empty())
            return;
        stack.pop();
        max_stack.pop();
    }

    public int Max() {
        if (stack.empty())
            return 0;
        return max_stack.peek();
    }

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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                Push(value);
            } else if ("pop".equals(operation)) {
                Pop();
            } else if ("max".equals(operation)) {
                System.out.println(Max());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
