package week1_basic_data_structures;

import ds.StackLinedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        StackLinedList<Bracket> opening_brackets_stack = new StackLinedList<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);
            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {
                if (opening_brackets_stack.empty()) {
                    System.out.println(position + 1);
                    return;
                }
                Bracket lastBracket = opening_brackets_stack.pop();
                if (!lastBracket.Match(next)) {
                    System.out.println(position + 1);
                    return;
                }
            }
        }
        if (opening_brackets_stack.empty()) {
            System.out.println("Success");
        } else {
            Bracket lastBracket = opening_brackets_stack.pop();
            System.out.println(lastBracket.position + 1);
        }
    }
}

