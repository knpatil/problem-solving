package main.java.dp;

public class Fibonacci {

  public int fib(int n) {
    if (n < 2) {
      return n;
    }
    return fib(n - 1) + fib(n - 2);
  }

  public int fibMemoization(int n) {
    int[] cache = new int[n + 1];
    return fibMemoizationHelper(cache, n);
  }

  private int fibMemoizationHelper(int[] cache, int n) {
    if (n < 2) {
      return n;
    }
    if (cache[n] != 0) {
      return cache[n];
    }
    cache[n] = fibMemoizationHelper(cache, n - 1) + fibMemoizationHelper(cache, n - 2);
    return cache[n];
  }

  public int fibTabulation(int n) {
    if (n == 0) {
      return 0;
    }
    int[] dpTable = new int[n + 1];
    dpTable[0] = 0;
    dpTable[1] = 1;
    for (int i = 2; i <= n; i++) {
      dpTable[i] = dpTable[i - 1] + dpTable[i - 2];
    }
    return dpTable[n];
  }

  public static void main(String[] args) {
    System.out.println(new Fibonacci().fib(10));
    System.out.println(new Fibonacci().fibMemoization(10));
    System.out.println(new Fibonacci().fibTabulation(10));
  }
}

/*

Dynamic programming is a technique to solve recursive problems optimally.

 */