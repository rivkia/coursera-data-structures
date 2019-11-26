package week3_hash_tables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class substring_equality {

    public class HashString {
        private int m1 = 1000000007;
        private int m2 = 1000000009;
        private int x = (int) (Math.random() * (1000000000)) + 1;
        private String text;
        private int y;

        public HashString(String t) {
            this.text = t;
        }

        public Boolean isFound(int a, int b, int n) {
            long[] h1 = new long[n + 1];
            long[] h2 = new long[n + 1];
            long[] h3 = new long[n + 1];
            long[] h4 = new long[n + 1];
            //precomputeHashes
            for (int i = 1; i <= n; i++) {
                h1[i] = (x * h1[i - 1] + text.charAt(a + i - 1)) % m1;
                h2[i] = (x * h2[i - 1] + text.charAt(b + i - 1)) % m1;
                h3[i] = (x * h3[i - 1] + text.charAt(a + i - 1)) % m2;
                h4[i] = (x * h4[i - 1] + text.charAt(b + i - 1)) % m2;
            }
            if (h1[n] == h2[n] && h3[n] == h4[n])
                return true;
            return false;
        }
    }

    public class Solver {
        private String s;
        HashString hashString;

        public Solver(String s) {
            hashString = new HashString(s);
            this.s = s;
        }

        public boolean ask(int a, int b, int l) {
            return hashString.isFound(a, b, l);
        }

        public boolean ask_naive(int a, int b, int l) {
            return s.substring(a, a + l).equals(s.substring(b, b + l));
        }
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        String s = in.next();
        int q = in.nextInt();
        Solver solver = new Solver(s);
        for (int i = 0; i < q; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int l = in.nextInt();
            out.println(solver.ask(a, b, l) ? "Yes" : "No");
        }
        out.close();
    }

    static public void main(String[] args) throws IOException {
        new substring_equality().run();
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
}
