package main.java.recursion;

import java.util.Stack;

public class SortStack {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(3);
    stack.push(1);
    stack.push(110);
    stack.push(0);
    stack.push(-100);
    stack.push(-1);
    stack.push(2);

    System.out.println(stack);
    sortStack(stack);
    System.out.println(stack);
  }

  // using recursion
  private static void sortStack(Stack<Integer> stack) {
    // base condition
    if (stack.isEmpty() || stack.size() == 1)
      return;

    int last = stack.pop(); // remove top of the stack
    sortStack(stack); // sort remaining stack
    insert(stack, last); // insert the top element back into the sorted stack
  }

  private static void insert(Stack<Integer> stack, int last) {
    if (stack.isEmpty() || stack.peek() <= last) {
      stack.push(last); // if last element is bigger just add it to the top
    } else {
      // remove top of the stack
      int val = stack.pop();
      // call insert recursively
      insert(stack, last);
      // add the value back to the top
      stack.push(val);
    }
  }

}
