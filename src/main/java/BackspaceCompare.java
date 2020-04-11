package main.java;

/*

Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".

Example 2:
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".

Example 3:
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".

Example 4:
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".

Note:
1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.

Follow up: Can you solve it in O(N) time and O(1) space?
 */

import java.util.Stack;

public class BackspaceCompare {
    public static void main(String[] args) {
        String S = "ab#c", T = "ad#c";
        System.out.println(backspaceCompare(S, T));
        S = "a#c";
        T = "b";
        System.out.println(backspaceCompare(S, T));
    }

    public static boolean backspaceCompare(String S, String T) {
        return process(S).equals(process(T));
    }

    private static String process(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(S.charAt(i));
            }
        }
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            s.append(stack.pop());
        }
        return s.toString();
    }
}
