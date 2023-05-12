package main.java.recursion;

import java.util.ArrayList;
import java.util.List;

public class JosephusProblem {
  public static void main(String[] args) {
//    int n = 40;
//    int k = 7;
    int n = 5;
    int k = 2;
    List<Integer> arr = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      arr.add(i);
    }
    System.out.println(solve(n, k - 1, arr, 0));
  }

  private static int solve(int n, int k, List<Integer> arr, int startIndex) {
    if (arr.size() == 1) {
      return arr.get(0);
    } else {
      int indexToKill = (startIndex + k) % n;
      arr.remove(indexToKill);
      return solve(n - 1, k, arr, indexToKill);
    }
  }
}
