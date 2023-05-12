package main.java.recursion;

public class NBitBinaryNumbers {
  public static void main(String[] args) {
    printNBitBinaryNumbers(4, 0, 0, "");
  }

  private static void printNBitBinaryNumbers(int n, int ones, int zeros, String output) {
    if ( n == 0 ) {
      System.out.println(output);
    } else {
      printNBitBinaryNumbers(n - 1, ones + 1, zeros, output + "1");
      if ( ones > zeros) {
        printNBitBinaryNumbers(n - 1, ones, zeros + 1, output + "0");
      }
    }

  }
}
