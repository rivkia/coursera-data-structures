package week3_hash_tables;

import java.util.*;
import java.io.*;

public class common_substring {
    public class Answer {
        int i, j, len;
        Answer(int i, int j, int len) {
            this.i = i;
            this.j = j;
            this.len = len;
        }
    }

    public Answer solve(String s, String t) {
        Answer ans = new Answer(0, 0, 0);
        for (int i = 0; i < s.length(); i++)
            for (int j = 0; j < t.length(); j++)
                for (int len = 0; i + len <= s.length() && j + len <= t.length(); len++)
                    if (len > ans.len && s.substring(i, i + len).equals(t.substring(j, j + len)))
                        ans = new Answer(i, j, len);
        return ans;
    }

    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        in.lines().forEach(line -> {
            StringTokenizer tok = new StringTokenizer(line);
            String s = tok.nextToken();
            String t = tok.nextToken();
            Answer ans = solve(s, t);
            out.format("%d %d %d\n", ans.i, ans.j, ans.len);
        });
        out.close();
    }

    static public void main(String[] args) {
        new common_substring().run();
    }
}


/*static String s1;
static String s2;

    static int arr[][];
    static void lcs(String s1, String s2) {
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                else
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
            }
        }
    }

    static Set<String> lcs(String s1, String s2, int len1, int len2) {
        if (len1 == 0 || len2 == 0) {
            Set<String> set = new HashSet<String>();
            set.add("");
            return set;
        }
        if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
            Set<String> set = lcs(s1, s2, len1 - 1, len2 - 1);
            Set<String> set1 = new HashSet<>();
            for (String temp : set) {
                temp = temp + s1.charAt(len1 - 1);
                set1.add(temp);
            }
            return set1;
        } else {
            Set<String> set = new HashSet<>();
            Set<String> set1 = new HashSet<>();
            if (arr[len1 - 1][len2] >= arr[len1][len2 - 1]) {
                set = lcs(s1, s2, len1 - 1, len2);
            }
            if (arr[len1][len2 - 1] >= arr[len1 - 1][len2]) {
                set1 = lcs(s1, s2, len1, len2 - 1);
            }
            for (String temp : set) {
                set1.add(temp);
            }
            return set1;

        }
    }


    public static void main(String[] args) {
        String s1 = "bcab";
        String s2 = "abc";
        arr = new int[s1.length() + 1][s2.length() + 1];
        lcs(s1, s2);
        System.out.println(lcs(s1, s2, s1.length(), s2.length()));
    }*/