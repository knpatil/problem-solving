package main.java.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subsets {
  public static void main(String[] args) {
    String input = "aab";
    String output = "";
    List<String> allSubsets = subsets(input, output, new ArrayList<>());
    System.out.println(allSubsets);
    Set<String> uniqSubsets = uniqSubsets(input, output, new HashSet<>());
    System.out.println(uniqSubsets);
  }

  private static List<String> subsets(String input, String output, ArrayList<String> result) {
    // when input is empty, output is one of the answers
    if (input.length() == 0) {
      result.add(output);
    } else {
      String op1 = output;
      String op2 = output;
      op2 += input.charAt(0);
      String newInput = input.substring(1);
      subsets(newInput, op1, result); // do not take first char
      subsets(newInput, op2, result);  // take first character
    }
    return result;
  }

  private static Set<String> uniqSubsets(String input, String output, Set<String> result) {
    // when input is empty, output is one of the answers
    if (input.length() == 0) {
      result.add(output);
    } else {
      String op1 = output;
      String op2 = output;
      op2 += input.charAt(0);
      String newInput = input.substring(1);
      uniqSubsets(newInput, op1, result); // do not take first char
      uniqSubsets(newInput, op2, result);  // take first character
    }
    return result;
  }
}
