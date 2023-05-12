package main.java.recursion;

public class PermutationChangeCase {

  public static void main(String[] args) {
    String str = "ab";
    String output = "";
    String input = str;
    permutationChangeCase(input, output);
  }

  private static void permutationChangeCase(String input, String output) {
    if (input.length() == 0) {
      System.out.println(output);
    } else {
      String op1 = output + input.charAt(0);
      String op2 = output + Character.toUpperCase(input.charAt(0));
      input = input.substring(1);
      permutationChangeCase(input, op1);
      permutationChangeCase(input, op2);
    }
  }


}
