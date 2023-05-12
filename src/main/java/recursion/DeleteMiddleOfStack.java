package main.java.recursion;

import java.util.Stack;

public class DeleteMiddleOfStack {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(3);
    stack.push(1);
    stack.push(0);
    stack.push(4);
    stack.push(2);

    System.out.println(stack);
    int k = stack.size() / 2 + 1; // middle 5 / 2 + 1
    deleteMiddle(stack, k);
    System.out.println(stack);
  }

  // using recursion
  private static void deleteMiddle(Stack<Integer> stack, int k) {
    if (k == 1)
      stack.pop();
    else {
      int top = stack.pop();
      deleteMiddle(stack, k - 1);
      stack.push(top);
    }
  }
}
