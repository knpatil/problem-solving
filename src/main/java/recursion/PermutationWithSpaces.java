package main.java.recursion;

public class PermutationWithSpaces {

  public static void main(String[] args) {
    String str = "ABCD";
    String output = str.substring(0,1);
    String input = str.substring(1);
    permutationSpaces(input, output);
  }

  private static void permutationSpaces(String input, String output) {
    if (input.length() == 0) {
      System.out.println(output);
    } else {
      String output1 = output + "_" + input.charAt(0);
      String output2 = output + input.charAt(0);
      String newInput = input.substring(1);
      permutationSpaces(newInput, output1);
      permutationSpaces(newInput, output2);
    }
  }

}
