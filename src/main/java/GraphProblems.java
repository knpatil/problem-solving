import java.util.*;

public class GraphProblems {

    /*
    1. Graph Representation
        - Adjacency Lists
        - Adjacency Matrix
        - Adjacency Maps
        - Edge Lists
    2. Traversal
        - BFS (Queue based)
        - DFS Recursive
        - DFS Iterative (Stack based)
     */

    /*
    Breadth First Search

    Explore the graph in increasing order of distance from Source (S)
        - First capture the immediate neighbors of S (one hop away)
        - Then capture their neighbors (two hops away)
        - and so on ...
    Queue is used to capture one-hop neighbors first then two hop neighbors.
    Queue helps as it is FIFO structure.
    Use visited Set to avoid cycles, just before adding it to queue, check visited Set
     */

    /*
    Depth First Search

    Instead of going broad, go as deep as possible and only retreat/backtrack when necessary.
    Just like you would explore a Maze.

    Use STACK for LIFO structure
        visited and captured mean same thing in recursive DFS
     */

    /*
    Clone Graph

    Given a reference of a node in a connected undirected graph. Return a deep copy (clone) of the graph.

    Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

    class Node {
        public int val;
        public List<Node> neighbors;
    }
     */
    // Definition for a Node.
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> nodeToCloneMap = new HashMap<>();
        return cloneGraphDFS(node, nodeToCloneMap);
    }

    private Node cloneGraphDFS(Node node, Map<Node, Node> nodeToCloneMap) {
        // check if this node is already cloned
        if (nodeToCloneMap.containsKey(node)) {
            return nodeToCloneMap.get(node);
        }

        // create a clone node
        Node cloneNode = new Node(node.val);
        nodeToCloneMap.put(node, cloneNode); // add to map

        // clone each neighbor of this node
        for (Node nbr : node.neighbors) {
            cloneNode.neighbors.add(cloneGraphDFS(nbr, nodeToCloneMap));
        }

        return cloneNode;
    }

    private Node cloneGraphBFS(Node node) {
        Map<Node, Node> nodeToCloneMap = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        Node cloneNode = new Node(node.val);
        nodeToCloneMap.put(node, cloneNode);

        while (!q.isEmpty()) {
            Node n = q.poll(); // the node added to queue is already cloned
            Node nClone = nodeToCloneMap.get(n); // fetch the clone

            // clone neighbor nodes for this node
            for (Node nbr : n.neighbors) {
                if (!nodeToCloneMap.containsKey(nbr)) { // if not cloned already
                    // clone the nbr
                    Node nbrClone = new Node(nbr.val);
                    nodeToCloneMap.put(nbr, nbrClone);
                    q.offer(nbr); // add to queue so that it's neighbors get cloned
                }
                // connect the nbr clone to it's parent
                Node nbrClone = nodeToCloneMap.get(nbr);
                nClone.neighbors.add(nbrClone);
            }
        }
        return cloneNode;
    }

    /* Dijkstra's Algorithm */
    /*
    Network Delay Time

    You are given a network of n nodes, labeled from 1 to n.
    You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
    where ui is the source node, vi is the target node, and wi is the time it takes for a signal
    to travel from source to target.

    We will send a signal from a given node k. Return the minimum time it takes for all the n nodes
    to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
     */

    /*
    Complexity Analysis

    Here N is the number of nodes and E is the number of total edges in the given network.

    Time complexity: O(N + E logN)

    Dijkstra's Algorithm takes O(E log N). Finding the minimum time required in signalReceivedAt takes O(N).

    The maximum number of vertices that could be added to the priority queue is E.
    Thus, push and pop operations on the priority queue take O(log E) time.
    The value of E can be at most N⋅(N−1).
    Therefore, O(log E) is equivalent to O(log N^2) which in turn equivalent to O(2⋅logN).
    Hence, the time complexity for priority queue operations equals O(logN).

    Although the number of vertices in the priority queue could be equal to E, we will only visit each vertex only once.
    If we encounter a vertex for the second time, then currNodeTime will be greater than signalReceivedAt[currNode],
    and we can continue to the next vertex in the priority queue.
    Hence, in total E edges will be traversed and for each edge, there could be one priority queue insertion operation.

    Hence, the time complexity is equal to O(N + E logN).

    Space complexity: O(N + E)O(N+E)
    Building the adjacency list will take O(E) space.
    Dijkstra's algorithm takes O(E) space for priority queue because each vertex could be added
    to the priority queue N−1 time which makes it N∗(N−1) and O(N^2) is equivalent to O(E).
    signalReceivedAt takes O(N) space.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: Build the Graph
        //      Use Adjacency Map representation to build a graph in this case
        //      e.g. v -> u1:w1,u2:w2 (where u1 is nbr and w1 is the weight etc.)
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] vertexInfo : times) {
            graph.putIfAbsent(vertexInfo[0], new HashMap<>()); // add entry for vertex if not added earlier
            graph.get(vertexInfo[0]).put(vertexInfo[1], vertexInfo[2]); // add nbr and its weight into the adj map
        }

        // Data structure to keep track of every nodes distance from source
        Map<Integer, Integer> nodeToDistanceFromSource = new HashMap<>(); // 1D int array works as well to save space
        // as then nodes are ints from 1 to n

        // Step 2: Implement Dijkstra Algorithm (Variant of BFS)
        // Use Priority Queue to store nodes based on their current shortest known time
        // The node with the smallest time will be dequeued first
        // We will store a pair of values: node_id : distance (time in this case) from source
        // PQ is ordered based on the value of the distance using Lambda function comparator
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{k, 0}); // add source node with distance (time) 0

        while (!pq.isEmpty()) {
            int[] currNodeInfo = pq.poll();
            int node = currNodeInfo[0];
            int distance = currNodeInfo[1]; // time to reach for this problem

            // If we already have shorted time for this node in the Map, we can skip it
            // Note that because of PQ property we will reach the node in shorted time value
            if (nodeToDistanceFromSource.containsKey(node)) {
                continue;
            }
            // otherwise add the current distance to map
            nodeToDistanceFromSource.put(node, distance);

            // Explore adjacent nodes
            if (graph.containsKey(node)) { // if there are neighbors to this node
                // for each nbr of this node
                for (int nbr : graph.get(node).keySet()) { // nbrs are stored in adj Map
                    // if we haven't found a distance yet for this nbr
                    if (!nodeToDistanceFromSource.containsKey(nbr)) {
                        pq.offer(new int[]{nbr, distance + graph.get(node).get(nbr)}); // add wt of this nbr to distance
                    }
                }
            }
        }

        // If distance map does not contain all nodes, that means some nodes didn't receive the signal
        // from the given source k
        if (nodeToDistanceFromSource.size() != n) {
            return -1;
        }

        // Otherwise find maximum time it took to reach the final node (by that time all nodes would have
        // received the signal from k)
        int delayTime = 0;
        for (int time : nodeToDistanceFromSource.values()) {
            delayTime = Math.max(delayTime, time);
        }
        return delayTime;
    }

    /*
    ***Dijkstra's Algorithm***

    Path with Maximum Probability

    You are given an undirected weighted graph of n nodes (0-indexed),
    represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with
    a probability of success of traversing that edge succProb[i].

    Given two nodes start and end, find the path with the maximum probability of success to go from start to end
    and return its success probability.

    If there is no path from start to end, return 0.
    Your answer will be accepted if it differs from the correct answer by at most 1e-5.
     */

    /*
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            double pathProb = succProb[i];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair<>(v, pathProb));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair<>(u, pathProb));
        }

        double[] maxProb = new double[n];
        maxProb[start] = 1d;

        PriorityQueue<Pair<Double, Integer>> pq = new PriorityQueue<>((a, b) -> -Double.compare(a.getKey(), b.getKey()));
        pq.add(new Pair<>(1.0, start));
        while (!pq.isEmpty()) {
            Pair<Double, Integer> cur = pq.poll();
            double curProb = cur.getKey();
            int curNode = cur.getValue();
            if (curNode == end) {
                return curProb;
            }
            for (Pair<Integer, Double> nxt : graph.getOrDefault(curNode, new ArrayList<>())) {
                int nxtNode = nxt.getKey();
                double pathProb = nxt.getValue();
                if (curProb * pathProb > maxProb[nxtNode]) {
                    maxProb[nxtNode] = curProb * pathProb;
                    pq.add(new Pair<>(maxProb[nxtNode], nxtNode));
                }
            }
        }

        return 0d;
    }
    */

    /*
    Variations of Dijkstra's Algorithm

    Path With Minimum Effort

    You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
    where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
    and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down,
    left, or right, and you wish to find a route that requires the minimum effort.

    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
     */
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] differenceMatrix = new int[row][col];

        for (int[] eachRow : differenceMatrix)
            Arrays.fill(eachRow, Integer.MAX_VALUE);

        differenceMatrix[0][0] = 0;

        PriorityQueue<Cell> queue = new PriorityQueue<Cell>((a, b) -> (a.difference.compareTo(b.difference)));
        boolean[][] visited = new boolean[row][col];
        queue.add(new Cell(0, 0, differenceMatrix[0][0]));

        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            visited[curr.x][curr.y] = true;

            if (curr.x == row - 1 && curr.y == col - 1)
                return curr.difference;

            for (int[] direction : directions) {
                int adjacentX = curr.x + direction[0];
                int adjacentY = curr.y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                    int maxDifference = Math.max(currentDifference, differenceMatrix[curr.x][curr.y]);
                    if (differenceMatrix[adjacentX][adjacentY] > maxDifference) {
                        differenceMatrix[adjacentX][adjacentY] = maxDifference;
                        queue.add(new Cell(adjacentX, adjacentY, maxDifference));
                    }
                }
            }
        }
        return differenceMatrix[row - 1][col - 1];
    }

    boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }

    static class Cell {
        int x;
        int y;
        Integer difference;

        Cell(int x, int y, Integer difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }
    }

    /* ------------------------------------- */
    // BFS - Breadth-First Search (BFS) with Bus Stops as Nodes
    /*
    Bus Routes

    You are given an array routes representing bus routes where routes[i]
    is a bus route that the ith bus repeats forever.

    For example, if routes[0] = [1, 5, 7],
    this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.

    You will start at the bus stop source (You are not on any bus initially),
    and you want to go to the bus stop target. You can travel between bus stops by buses only.

    Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
     */

    /*
    Complexity Analysis

    Here, MMM is the size of routes, and KKK is the maximum size of routes[i].

    Time complexity: O(M2∗K)O(M^2 * K)O(M2∗K)

    To store the routes for each stop we iterate over each route and for each route, we iterate over each stop,
    hence this step will take O(M∗K)O(M* K)O(M∗K) time. In the BFS, we iterate over each route in the queue.
    For each route we popped, we will iterate over its stop, and for each stop, we will iterate over the
     connected routes in the map adjList,
     hence the time required will be O(M∗K∗M)O(M * K * M)O(M∗K∗M) or O(M2∗K)O(M^2 * K)O(M2∗K).

    Space complexity: O(M⋅K)O(M \cdot K)O(M⋅K)

    The map adjList will store the routes for each stop. There can be M⋅KM \cdot KM⋅K number of stops in routes
    in the worst case (each of the MMM routes can have KKK stops), possibly with duplicates.
    When represented using adjList, each of the mentioned stops appears exactly once.
    Therefore, adjList contains an equal number of stop-route element pairs.

     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        // Create a map from the bus stop to all the routes that include this stop.
        for (int r = 0; r < routes.length; r++) {
            for (int stop : routes[r]) {
                // Add all the routes that have this stop.
                ArrayList<Integer> route = adjList.getOrDefault(stop, new ArrayList<>());
                route.add(r);
                adjList.put(stop, route);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<Integer>(routes.length);
        // Insert all the routes in the queue that have the source stop.
        for (int route : adjList.get(source)) {
            q.add(route);
            vis.add(route);
        }

        int busCount = 1;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int route = q.remove();

                // Iterate over the stops in the current route.
                for (int stop : routes[route]) {
                    // Return the current count if the target is found.
                    if (stop == target) {
                        return busCount;
                    }

                    // Iterate over the next possible routes from the current stop.
                    for (int nextRoute : adjList.get(stop)) {
                        if (!vis.contains(nextRoute)) {
                            vis.add(nextRoute);
                            q.add(nextRoute);
                        }
                    }
                }
            }
            busCount++;
        }
        return -1;
    }

    /*
    Course Schedule

    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [ai, bi]
    indicates that you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

    Return true if you can finish all courses. Otherwise, return false.
     */

    /*
    DFS to check cycle

    Here we have described an algorithm to detect cycles in a directed graph using depth-first search.

    During a depth-first traversal of the graph every node of the graph is visited exactly once.
    So for every node in the graph, a DFS call will be initiated once for that node. The nodes can be in one of the following three states.

    Undiscovered: The set of nodes for which no DFS call has been initiated yet, belongs to this state. Initially all the nodes are undiscovered.
    Discovered and finished processing: A node is considered to be discovered when a DFS call has been initiated for that node. A node is considered to have finished processing when the DFS function call for that particular node gets popped from the recursion call stack.
    Discovered and still being processed: The nodes that have been discovered but the DFS function calls for those nodes are still in the call stack, possess this state.

    We will be coloring the nodes with white, black and grey color respectively to represent the above states.
    Initially all the nodes will be colored white. Upon discovering a node, it will be colored grey. Just before the termination of the DFS call for a particular node, we will color that node in black.

    Our solution with a depth-first search has the following steps:

    Iterate through the given edges and form adjacency list of the graph. This will help us run the depth-first search(DFS) in O(n + m) complexity.
    Initiate a DFS call from every undiscovered node in the graph in any order.
    Check the existence of any cycle: Inside the DFS call for a node, we loop over its neighbours and initiate a DFS call for each of its undiscovered neighbours. We can claim that the given graph has a cycle if inside the DFS call for a node, it discovers a grey neighbour. Such an edge between two grey nodes is known as a back edge.

    If no cycle gets reported during the depth-first search traversal, we can claim that all the courses can be completed.
     */
    /*
     * Asymptotic complexity in terms of the number of courses `n` and size of `a`(or number of edges) `m`:
     * Time: O(n + m).
     * Auxiliary space: O(n + m).
     * Total space: O(n + m).
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1. Build a graph: directed graph in this case
        // Adjacency List representation
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());  // initialize empty adj list for each node (course)
        }
        for (int[] coursePair : prerequisites) {
            graph.get(coursePair[1]).add(coursePair[0]); // relationship: have to take course 1 before 0 if [0,1]
        }

        // Step 2: Do DFS
        /*
        static int WHITE = 0; // NOT VISITED NODE
        static int GREY = 1; // VISITED NODE
        static int BLACK = 2; // ALL NEIGHBORS ARE VISITED
        */
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                boolean isCyclePresent = isCycleDFS(graph, visited, i);
                if (isCyclePresent) return false;
            }
        }
        return true; // if no cycle, you can take all courses given
    }

    private boolean isCycleDFS(Map<Integer, List<Integer>> graph, int[] visited, int course) {
        if (visited[course] == 1) {
            return true; // we are visiting this course again, so there is a cycle
        }
        // If this course and all it's nbrs are already explored, then skip
        if (visited[course] == 2) {
            return false;
        }
        // otherwise, mark course visited
        visited[course] = 1;

        // Check all adj courses
        for (int nextCourse : graph.get(course)) {
            if (isCycleDFS(graph, visited, nextCourse)) {
                return true;
            }
        }
        visited[course] = 2; // all nbrs visited
        return false; // no cycle found from given course
    }

    /*
    Course Schedule II

    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
    take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return the ordering of courses you should take to finish all courses. If there are many valid answers,
    return any of them. If it is impossible to finish all courses, return an empty array.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Step 1. Build a graph: directed graph in this case
        // Adjacency List representation
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());  // initialize empty adj list for each node (course)
        }
        for (int[] coursePair : prerequisites) {
            graph.get(coursePair[1]).add(coursePair[0]); // relationship: have to take course 1 before 0 if [0,1]
        }

        // Step 2: Do DFS
        /*
        static int WHITE = 0; // NOT VISITED NODE
        static int GREY = 1; // VISITED NODE
        static int BLACK = 2; // ALL NEIGHBORS ARE VISITED
        */
        Stack<Integer> order = new Stack<>();
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                boolean isCyclePresent = isCycleDFS(graph, visited, i, order);
                if (isCyclePresent) return new int[]{};
            }
        }
        int[] result = new int[numCourses];
        int i = 0;
        while (i < numCourses) {
            result[i] = order.pop();
            i++;
        }
        return result; // if no cycle, you can take all courses given
    }

    private boolean isCycleDFS(Map<Integer, List<Integer>> graph, int[] visited, int course, Stack<Integer> order) {
        if (visited[course] == 1) {
            return true; // we are visiting this course again, so there is a cycle
        }
        // If this course and all it's nbrs are already explored, then skip
        if (visited[course] == 2) {
            return false;
        }
        // otherwise, mark course visited
        visited[course] = 1;

        // Check all adj courses
        for (int nextCourse : graph.get(course)) {
            if (isCycleDFS(graph, visited, nextCourse, order)) {
                return true;
            }
        }
        visited[course] = 2; // all nbrs visited
        order.push(course);
        return false; // no cycle found from given course
    }

    /*
    Number of Islands

    Given an m x n 2D binary grid called grid which represents a map of '1's (land) and '0's (water),
    return the number of islands.

    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    You may assume all four edges of the grid are all surrounded by water.
    */
    public int numIslands(char[][] grid) {
        if (grid.length == 0)
            return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    checkIslandDFS(grid, rows, cols, i, j, visited);
                }
            }
        }
        return count;
    }

    private void checkIslandDFS(char[][] grid, int rows, int cols, int r, int c, boolean[][] visited) {
        if (grid[r][c] == '0' || visited[r][c])
            return;

        visited[r][c] = true; // mark this node visited

        for (Integer[] nbr : getNeighbors(r, c, rows, cols)) {
            checkIslandDFS(grid, rows, cols, nbr[0], nbr[1], visited);
        }

        /*
         THIS IS MORE EFFICIENT
                dfs(grid, rows, cols, r + 1, c, visited); // down
                dfs(grid, rows, cols, r - 1, c, visited); // left
                dfs(grid, rows, cols, r, c + 1, visited); // right
                dfs(grid, rows, cols, r, c - 1, visited); // up
        */
    }

    private List<Integer[]> getNeighbors(int r, int c, int rows, int cols) {
        List<Integer[]> neighbors = new ArrayList<>();
        if (r - 1 >= 0) {
            neighbors.add(new Integer[]{r - 1, c});
        }
        if (r + 1 < rows) {
            neighbors.add(new Integer[]{r + 1, c});
        }
        if (c - 1 >= 0) {
            neighbors.add(new Integer[]{r, c - 1});
        }
        if (c + 1 < cols) {
            neighbors.add(new Integer[]{r, c + 1});
        }
        return neighbors;
    }

    /*
    Max Area of Island

    You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

    The area of an island is the number of cells with a value 1 in island.

    Return the maximum area of an island in grid. If there is no island, return 0.
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = measureIslandAreaDFS(grid, i, j, m, n, visited);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private int measureIslandAreaDFS(int[][] grid, int i, int j, int m, int n, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1 || visited[i][j])
            return 0;

        visited[i][j] = true;

        // check all 4 directions
        return 1 + measureIslandAreaDFS(grid, i - 1, j, m, n, visited)
                + measureIslandAreaDFS(grid, i + 1, j, m, n, visited)
                + measureIslandAreaDFS(grid, i, j - 1, m, n, visited)
                + measureIslandAreaDFS(grid, i, j + 1, m, n, visited);
    }

    /*
    Number of Connected Components in an Undirected Graph

    You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi]
    indicates that there is an edge between ai and bi in the graph.

    Return the number of connected components in the graph.
     */
    public int countComponents(int n, int[][] edges) {
        // Step 1: Build a graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); // undirected graph hence add both ways
        }

        // Step 2: Do DFS starting from every node to count connected components
        int components = 0;
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) { // if not already visited
                components++;
                countComponentsDFS(graph, i, visited);
            }
        }

        return components;
    }

    private void countComponentsDFS(Map<Integer, List<Integer>> graph, int n, int[] visited) {
        if (visited[n] == 1) {
            return;
        }
        visited[n] = 1;
        for (int nbr: graph.get(n)) {
            if (visited[nbr] == 0)
                countComponentsDFS(graph, nbr, visited);
        }
    }

    /*
    Graph Valid Tree

    You have a graph of n nodes labeled from 0 to n - 1.
    You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that
    there is an undirected edge between nodes ai and bi in the graph.

    Return true if the edges of the given graph make up a valid tree, and false otherwise.
     */
    public boolean validTree(int n, int[][] edges) {
        // using adjacency list representation
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>()); // array of linked list
        }

        // build a graph
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); // undirected edge, both ways
        }

        // check valid tree using DFS, check cycle
        // start traversal from node 0, set parent (incoming node) to -1
        // Set<Integer> visited = new HashSet<>();
        // return isValidTreeUsingDFS(graph, 0, -1, visited) && visited.size() == n;

        return isValidTreeUsingBFS(graph, n);
    }

    private boolean isValidTreeUsingDFS(List<List<Integer>> graph, int node, int parent, Set<Integer> visited) {
        if (visited.contains(node))
            return false;

        visited.add(node);
        for (int neighbor: graph.get(node)) {
            if (parent != neighbor) { // parent is neighbor, then skip
                boolean result = isValidTreeUsingDFS(graph, neighbor, node, visited);
                if (!result)
                    return false;
            }
        }
        return true;
    }

    private boolean isValidTreeUsingBFS(List<List<Integer>> graph, int noOfNodes) {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> visited = new HashMap<>(); // keep track of visited node and its parent
        q.offer(0);
        visited.put(0, -1); // set parent for the starting node
        while (!q.isEmpty()) {
            int v = q.poll();
            int parent = visited.get(v);
            // check neighbors
            for (int neighbor: graph.get(v)) {
                if (neighbor != parent) {
                    if (visited.containsKey(neighbor))
                        return false;
                    visited.put(neighbor, v);
                    q.offer(neighbor);
                }
            }
        }
        return visited.keySet().size() == noOfNodes;
    }






}
