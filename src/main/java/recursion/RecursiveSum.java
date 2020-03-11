package main.java.recursion;

public class RecursiveSum {

  public static void main(String[] args) {
    int[] numbers = {4, 3, 2, 51, 10};
    System.out.println("Sum: " + sum(numbers));
  }

  private static int sum(int[] numbers) {
    return sum(numbers, 0);
  }

  private static int sum(int[] numbers, int index) {
    if (index == numbers.length) {
      return 0;
    } else {
      return numbers[index] + sum(numbers, index + 1);
    }
  }
}
