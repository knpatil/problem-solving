package patterns;

import java.util.*;

public class QueueProblems {

    /*
    Design Circular Queue

    Design your implementation of the circular queue.
    The circular queue is a linear data structure in which the operations are performed based
    on FIFO (First In First Out) principle, and the last position is connected back to the first position
    to make a circle. It is also called "Ring Buffer".

    One of the benefits of the circular queue is that we can make use of the spaces in front of the queue.
    In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a
    space in front of the queue. But using the circular queue, we can use the space to store new values.

    Implement the MyCircularQueue class:

    MyCircularQueue(k) Initializes the object with the size of the queue to be k.
    int Front() Gets the front item from the queue. If the queue is empty, return -1.
    int Rear() Gets the last item from the queue. If the queue is empty, return -1.
    boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
    boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
    boolean isEmpty() Checks whether the circular queue is empty or not.
    boolean isFull() Checks whether the circular queue is full or not.

    You must solve the problem without using the built-in queue data structure in your programming language.
     */

    static class MyCircularQueue {
        private final int[] queue;
        private int head;
        private int tail;
        private int size;
        private final int capacity;

        public MyCircularQueue(int k) {
            this.capacity = k;
            this.queue = new int[k];
            this.head = -1;   // first element
            this.tail = -1;   // last element
            this.size = 0;    // count of elements in queue
        }

        public boolean enQueue(int value) {
            if (this.size == capacity) {
                return false; // queue is Full, can't enqueue
            }
            if (this.size == 0) {   // empty queue
                this.queue[0] = value;
                this.head = 0;
                this.tail = 0;
            } else {
                // increment tail and store value
                this.tail = (this.tail + 1) % this.capacity;
                this.queue[this.tail] = value;
            }
            this.size += 1;
            return true;
        }

        public boolean deQueue() {
            if (this.size == 0) {  // queue empty
                return false;
            }
            // just increment head, as we are not returning the value in this method
            this.head = (this.head + 1) % this.capacity;
            this.size -= 1;
            return true;
        }

        public int Front() {
            if (this.size != 0) {
                return this.queue[this.head]; // first element
            }
            return -1; // empty queue
        }

        public int Rear() {
            if (this.size != 0) {
                return this.queue[this.tail]; // last element
            }
            return -1; // empty queue
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public boolean isFull() {
            return this.size == this.capacity;
        }
    }

    /*
    Implement Queue using Stacks

    Implement a first in first out (FIFO) queue using only two stacks.
    The implemented queue should support all the functions of a normal
    queue (push, peek, pop, and empty).

    Implement the MyQueue class:

    void push(int x) Pushes element x to the back of the queue.
    int pop() Removes the element from the front of the queue and
    returns it.
    int peek() Returns the element at the front of the queue.
    boolean empty() Returns true if the queue is empty, false otherwise.

    Notes:
    You must use only standard operations of a stack,
    which means only push to top, peek/pop from top, size,
    and is empty operations are valid.
    Depending on your language, the stack may not be supported natively.
    You may simulate a stack using a list or deque (double-ended queue)
    as long as you use only a stack's standard operations.
     */
    // All operations altogether for single element are long constant time operations
    static class MyQueue {
        private Stack<Integer> s1;
        private Stack<Integer> s2;

        public MyQueue() {
            this.s1 = new Stack<>();
            this.s2 = new Stack<>();
        }

        // Always push to s1
        public void push(int x) {
            s1.push(x);
        }

        // Pop from s2 if it is not empty
        // Otherwise transfer all from s1 to s2 and then pop from s2
        public int pop() {
            if (!s2.isEmpty()) {
                return s2.pop();
            } else {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
                return s2.pop();
            }
        }

        // You can also maintain one member variable for front of the queue
        public int peek() {
            if (!s2.isEmpty()) {
                return s2.peek();
            } else {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
                return s2.peek();
            }
        }

        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }

    }

    // Implement queue using one stack
    static class QueueUsingOneStack {
        private final Stack<Integer> stack;

        public QueueUsingOneStack() {
            this.stack = new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(x);
            } else {
                // use recursion, O(n) operation
                int item = stack.pop();
                push(x);
                stack.push(item);
            }
        }

        public int pop() {
            if (stack.isEmpty())
                return -1;
            return stack.pop();
        }

        public int peek() {
            if (stack.isEmpty())
                return -1;
            return stack.peek();
        }

        public boolean empty() {
            return stack.isEmpty();
        }
    }

    /*
    Implement Stack using Queues

    Implement a last-in-first-out (LIFO) stack using only two queues.
    The implemented stack should support all the functions of a normal stack
    (push, top, pop, and empty).

    Implement the MyStack class:

    void push(int x) Pushes element x to the top of the stack.
    int pop() Removes the element on the top of the stack and returns it.
    int top() Returns the element on the top of the stack.
    boolean empty() Returns true if the stack is empty, false otherwise.

    Notes:
    You must use only standard operations of a queue, which means that only push
    to back, peek/pop from front, size and is empty operations are valid.
    Depending on your language, the queue may not be supported natively.
    You may simulate a queue using a list or deque (double-ended queue)
    as long as you use only a queue's standard operations.
     */
    static class MyStack {
        private final Queue<Integer> queue;
        private int size;

        public MyStack() {
            this.queue = new LinkedList<>();
            this.size = 0;
        }

        // every time you push, move all other items to the back of the current
        // O(n) operation
        public void push(int x) {
            queue.add(x);
            for (int i = 0; i < size; i++)
                queue.add(queue.remove());
            size += 1;
        }

        public int pop() {
            if (size == 0)
                return -1;
            size -= 1;
            return queue.remove();
        }

        public int top() {
            if (size == 0)
                return -1;
            return queue.peek();
        }

        public boolean empty() {
            return size == 0;
        }
    }

    /*
    LRU Cache

    Design a data structure that follows the constraints of a Least Recently Used
    (LRU) cache.

    Implement the LRUCache class:

    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists,
        otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists.
        Otherwise, add the key-value pair to the cache.
        If the number of keys exceeds the capacity from this operation,
        evict the least recently used key.

    The functions get and put must each run in O(1) average time complexity.
     */
    static class LRUCache {

        private final Map<Integer, Node> cache;
        private final int capacity;
        private final Node head;
        private final Node tail;
        private int size;

        public LRUCache(int capacity) {
            this.cache = new HashMap<>();
            this.capacity = capacity;
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                Node n = cache.get(key);

                // most recently accessed, move to head,
                // if not already at the front
                removeNode(n);
                addNode(n); // adds to front

                return n.val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {

            if (cache.containsKey(key)) {
                Node n = cache.get(key);
                n.val = value;
                // move to front
                removeNode(n);
                addNode(n);
            } else {
                Node newNode = new Node(key, value);
                addNode(newNode);
                cache.put(key, newNode);
                size++;

                if (size > capacity) {
                    Node lastNode = tail.prev;
                    removeNode(lastNode);
                    cache.remove(lastNode.key);
                    size--;
                }
            }
        }

        private void addNode(Node n) {
            Node firstNode = head.next;
            head.next = n;
            n.prev = head;
            n.next = firstNode;
            firstNode.prev = n;
        }

        private void removeNode(Node n) {
            Node prev = n.prev;
            Node next = n.next;
            prev.next = next;
            next.prev = prev;
        }

        static class Node {
            private final int key;
            private int val;
            private Node prev;
            private Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }


}
