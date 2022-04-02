package main.java.recursion;

// using recursion print from number to N to 1
public class PrintNto1 {
  public static void main(String[] args) {
    printNto1(11);
  }

  private static void printNto1(int n) {
    if ( n == 0) // base case
      return;

    System.out.print(n + " ");
    printNto1(n - 1); // recursion
  }
}
