package main.java.recursion;

// use recursion to print from 1 to n numbers
public class Print1ToN {

  public static void main(String[] args) {
    print(10);
  }

  private static void print(int n) {
    if (n == 0) // base case
      return;
    print(n-1); // recursive case
    System.out.print(n + " ");
  }

}
