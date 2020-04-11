package main.java;

import java.util.Stack;

public class MinStack2 {
    private static class StackItem {
        private int value;
        private int min;

        public StackItem(int value, int min) {
            this.value = value;
            this.min = min;
        }

        public int getMin() {
            return min;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "[" +
                    "value=" + value +
                    ", min=" + min +
                    ']';
        }
    }

    private Stack<StackItem> stack;

    public MinStack2() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new StackItem(x, x));
        } else {
            int min = Math.min(x, stack.peek().getMin());
            stack.push(new StackItem(x, min));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().getValue();
    }

    public int getMin() {
        return stack.peek().getMin();
    }

    public static void main(String[] args) {
        MinStack2 minStack = new MinStack2();
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
