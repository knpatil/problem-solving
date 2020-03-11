package main.java.recursion;

import java.text.NumberFormat;
import java.util.Locale;

public class Pow {

  public static void main(String[] args) {
    NumberFormat nf = NumberFormat.getInstance(Locale.US);
    System.out.println(nf.format(pow(2, 10)));
    System.out.println(nf.format(pow(2, 20)));
    System.out.println(nf.format(pow(2, 30)));
    System.out.println(nf.format(pow(2, 40)));
    System.out.println(nf.format(pow(2, 50)));
    System.out.println(nf.format(pow(2, 60)));
  }

  private static long pow(int x, int y) {
    if (y < 0) {
      throw new IllegalArgumentException("Negative exponent!");
    } else if (y == 0) {
      return 1;
    } else {
      return x * pow(x, y - 1);
    }
  }
}
