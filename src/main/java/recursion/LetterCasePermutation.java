package main.java.recursion;

public class LetterCasePermutation {

  public static void main(String[] args) {
    String input = "a1B2";
    String output = "";
    letterCasePermutations(input, output);
  }

  private static void letterCasePermutations(String input, String output) {
    if (input.length() == 0) {
      System.out.println(output);
    } else {
      char c = input.charAt(0);
      // only 1 branch for digit
      if (Character.isDigit(c)) {
        letterCasePermutations(input.substring(1), output + c); // no change in letter, no choices
      } else { // two branches for letter
        char t;
        if (Character.isLowerCase(c)) {
          t = Character.toUpperCase(c);
        } else {
          t = Character.toLowerCase(c);
        }
        String op1 = output + c; // no change
        String op2 = output + t; // change case
        letterCasePermutations(input.substring(1), op1);
        letterCasePermutations(input.substring(1), op2);
      }
    }
  }

}
