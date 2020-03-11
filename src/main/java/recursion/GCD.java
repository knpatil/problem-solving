package main.java.recursion;

public class GCD {

  public static void main(String[] args) {
    int z = gcd(13122, 23044);
    System.out.println("GCD: " + z);
  }

  private static int gcd(int x, int y) {
    if (y == 0) {  // base case when y is 0
      return x;
    } else {
      return gcd(y, x % y);
    }
  }

}
