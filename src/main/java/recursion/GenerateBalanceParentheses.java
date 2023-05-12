package main.java.recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateBalanceParentheses {

  public static void main(String[] args) {
    int n = 3;
    String output = "";
    List<String> result = generateBalancedParentheses(n, n, output, new ArrayList<>());
    System.out.println(result);
  }

  private static List<String> generateBalancedParentheses(int open, int close, String output, List<String> result) {
    if (open == 0 && close == 0) {
      result.add(output);
    } else {
      // two choices -> ( or ) for each
      if (open > 0)
        result = generateBalancedParentheses(open - 1, close, output + "(", result);
      if (close > open)
        result = generateBalancedParentheses(open, close - 1, output + ")", result);
    }
    return result;
  }

}
