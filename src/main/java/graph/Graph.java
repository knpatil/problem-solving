package main.java.graph;

import java.util.*;

/*

              1
       2      3       4
   5       6      7       8
       9              10

   11     12           13     14

 */
public class Graph {

  public static void main(String[] args) {
    Graph g = new Graph(false);

    Node a = new Node(1); g.addNode(a);
    Node b = new Node(2); g.addNode(b);
    Node c = new Node(3); g.addNode(c);
    Node d = new Node(4); g.addNode(d);
    Node e = new Node(5); g.addNode(e);
    Node f = new Node(6); g.addNode(f);
    Node h = new Node(7); g.addNode(h);

    g.addEdge(a, b); g.addEdge(a, c); g.addEdge(a, d);
    g.addEdge(b, e); g.addEdge(e, f); g.addEdge(d, h);
    g.addEdge(c, f); g.addEdge(c, h);

    System.out.println(g.bfs(a));
    System.out.println(g.bfs(h));
    System.out.println("-----------------------------");
    System.out.println(g.dfs(a));
    System.out.println(g.dfs(d));
  }

  private final Map<Node, Set<Node>> graph;    // adjacency list representation
  private final boolean directed;

  public Graph(boolean directed) {
    this.graph = new HashMap<>();
    this.directed = directed;
  }

  public void addNode(Node node) {
    graph.put(node, new HashSet<>());
  }

  public void addEdge(Node a, Node b) {
    if (!graph.containsKey(a)){
      addNode(a);
    }
    if (!graph.containsKey(b)) {
      addNode(b);
    }
    graph.get(a).add(b);
    if (!directed) {
      graph.get(b).add(a);
    }
  }

  // you can start breadth first search from a given node
  public List<Node> bfs(Node node) {
    List<Node> bfsOrder = new ArrayList<>();
    if (node == null)
      return bfsOrder;

    Set<Node> visited = new HashSet<>();

    Queue<Node> queue = new LinkedList<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      Node curr = queue.poll();

      if (visited.contains(curr))
        continue;

      bfsOrder.add(curr);
      visited.add(curr);

      Set<Node> neighbors = graph.get(curr);
      for (Node n: neighbors) {
        if (!visited.contains(n))
          queue.add(n);
      }
    }

    return bfsOrder;
  }

  public List<Node> dfs(Node node) {
    List<Node> result = new ArrayList<>();
    if (node == null)
      return result;

    Set<Node> visited = new HashSet<>();
    dfsHelper(node, visited, result);
    return result;
  }

  private void dfsHelper(Node node, Set<Node> visited, List<Node> result) {
    if (visited.contains(node))
      return;

    result.add(node);  // visit
    visited.add(node); // mark visited

    Set<Node> neighbors = graph.get(node); // get all neighbors
    for (Node n: neighbors) {
      if (!visited.contains(n))
        dfsHelper(n, visited, result);
    }

  }

  static class Node {
    int val;

    public Node(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return "v:" + val;
    }
  }
}
