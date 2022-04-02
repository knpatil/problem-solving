package main;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  private final int capacity;
  private int count;

  // data structure to store the key/value
  private final Node head;
  private final Node tail;

  // data structure for fast access based on key
  private final Map<Integer, Node> cache = new HashMap<>();

  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.count = 0;  // set count to zero

    // setup empty doubly linked list
    head = new Node();
    tail = new Node();
    head.prev = null;
    head.next = tail;
    tail.prev = head;
    tail.next = null;
  }

  public int get(int key) {
    if ( cache.containsKey(key) ) {
      Node node = cache.get(key);

      // mark as most recently used
      markAsRecentlyUsed(node);

      return node.value;
    } else {
      return -1; // throw exception, not found
    }
  }

  // move this node to the head of the doubly linked list
  private void markAsRecentlyUsed(Node node) {

    // remove from current position
    removeNode(node);

    // add to front
    addToFront(node);
  }

  private void removeNode(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void addToFront(Node node) {
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
  }

  public void put(int key, int value) {
    if ( cache.containsKey(key) ) {
      Node node = cache.get(key);
      node.setValue(value);
      // mark as recently used
      markAsRecentlyUsed(node);
    } else {
      // create a new node
      Node node = new Node();
      node.setKey(key);
      node.setValue(value);

      // add it to cache
      cache.put(key, node);
      addToFront(node);

      // increment count of entries
      this.count++;
      if ( this.count > this.capacity ) {
        Node lastNode = removeLastNode();
        this.cache.remove(lastNode.key);
        count--;
      }
    }
  }

  private Node removeLastNode() {
    Node nodeToRemove = tail.prev;
    removeNode(nodeToRemove);
    return nodeToRemove;
  }

  private static class Node {
    private int key;
    private int value;
    private Node prev;
    private Node next;

    public int getKey() {
      return key;
    }

    public void setKey(int key) {
      this.key = key;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public Node getPrev() {
      return prev;
    }

    public void setPrev(Node prev) {
      this.prev = prev;
    }

    public Node getNext() {
      return next;
    }

    public void setNext(Node next) {
      this.next = next;
    }
  }

  public static void main(String[] args) {

    LRUCache cache = new LRUCache(5);
    cache.put(1,100);
    cache.put(2,200);
    cache.put(1,1000);
    cache.put(3,300);
    cache.put(4,400);
    cache.put(5,500);
    System.out.println(cache.get(4));

    cache.put(6,600);
    System.out.println(cache.get(2));
    System.out.println(cache.get(1));
  }
  
}
