package main.java.recursion;

import java.util.Stack;

public class ReverseStack {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(33);
    stack.push(3);
    stack.push(1);
    stack.push(110);
    stack.push(13);
    stack.push(31);
    stack.push(-11);
    stack.push(0);
    stack.push(-1);
    stack.push(2);

    System.out.println(stack);
    reverseStack(stack);
    System.out.println(stack);
  }

  private static void reverseStack(Stack<Integer> stack) {
    if (stack.isEmpty() || stack.size() == 1)
      return;
    int tmp = stack.pop();
    reverseStack(stack);
    insert(stack, tmp);
  }

  private static void insert(Stack<Integer> stack, Integer item) {
    if (stack.isEmpty()) {
      stack.push(item);
    } else {
      int tmp = stack.pop();
      insert(stack, item);
      stack.push(tmp);
    }
  }

}
