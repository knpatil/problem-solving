package main;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    private int size;

    public MinStack() {
        size = 0;
    }

    public void push(int x) {
        if (size == 0) {
            stack.push(x);
            minStack.push(x);
        } else {
            if (x <= minStack.peek()) {
                minStack.push(x);
            }
            stack.push(x);
        }
        size++;
    }

    public void pop() {
        if (size > 0) {
            int top = stack.pop();
            if (top == minStack.peek()) {
                minStack.pop();
            }
            size--;
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.print();
        System.out.println("Min: " + minStack.getMin());
        minStack.pop();
        minStack.pop();
        minStack.print();
        System.out.println("Min: " + minStack.getMin());
    }

    public void print() {
        System.out.println(stack);
    }
}
