package main.java;

import java.util.ArrayList;
import java.util.List;

public class FindPath {

  public static void main(String[] args) {
    System.out.println(findPath(4));
  }

  public static List<String> findPath(int n) {
    List<String> result = new ArrayList<>();
    if (n == 0)
      return result;

    int i = 0;
    int j = 0;

    findWays(i + 1, j, n - 1, n - 1, "D", result);
    findWays(i, j + 1, n - 1, n - 1, "R", result);

    return  result;
  }

  private static void findWays(int i, int j, int r, int c, String out, List<String> result) {
    if (i > r || j > c)
      return;

    if (i == r && j == c) {
      result.add(out);
      return;
    }

    findWays(i + 1, j, r, c, out + "D", result);
    findWays(i, j + 1, r, c, out + "R", result);
  }
}
