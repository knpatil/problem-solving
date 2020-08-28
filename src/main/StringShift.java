package main;

/*

You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [direction, amount]:

direction can be 0 (for left shift) or 1 (for right shift).
amount is the amount by which string s is to be shifted.
A left shift by 1 means remove the first character of s and append it to the end.
Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.

Return the final string after all operations.

 */
public class StringShift {

    public static void main(String[] args) {
        String str = "abc";
        int[][] shift = new int[][]{{0, 1}, {1, 2}};
        System.out.println(stringShift(str, shift));
        System.out.println(stringShift("abcdefg", new int[][]{{1, 1}, {1, 1}, {0, 2}, {1, 3}}));
    }

    public static String stringShift(String s, int[][] shift) {
        for (int[] ints : shift) {
            int direction = ints[0];
            int amt = ints[1];
            if (direction == 0) {
                s = leftShift(s, amt);
            } else {
                s = rightShift(s, amt);
            }
        }
        return s;
    }

    private static String rightShift(String s, int amt) {
        return leftShift(s, s.length() - amt);
    }

    private static String leftShift(String s, int amt) {
        return s.substring(amt) + s.substring(0, amt);
    }

}
