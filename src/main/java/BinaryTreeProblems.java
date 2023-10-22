
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeProblems {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        testAllPathsBinaryTree();

        TreeNode root = createSampleBinaryTree();
        System.out.println(pathSum(root, 18));

        List<List<Integer>> result = pathSumII(root, 7);
        System.out.println(result);


    }

    /*
    Print (return) all paths from root to leaf in a binary tree -- START
     */
    private static void testAllPathsBinaryTree() {
        TreeNode root = createSampleBinaryTree();
        System.out.println("---- All Paths BFS ----");
        List<List<Integer>> allPaths = allPathsUsingBFS(root);
        for (List<Integer> l: allPaths) {
            System.out.println(l);
        }
        System.out.println("---- All Paths DFS ----");
        List<List<Integer>> allPathsDFS = allPathsUsingDFS(root);
        for (List<Integer> l: allPathsDFS) {
            System.out.println(l);
        }
    }

    private static List<List<Integer>> allPathsUsingDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root != null)
            allPathsDFSHelper(root, path, result);
        return result;
    }

    private static void allPathsDFSHelper(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        path.add(node.val);  // add current node value
        // Base case: if this is a leaf node, append this path to result
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        }
        // Recursive case
        if (node.left != null) {
            allPathsDFSHelper(node.left, path, result);
        }
        if (node.right != null) {
            allPathsDFSHelper(node.right, path, result);
        }
        path.remove(path.size() - 1); // remove last node value for backtracking
    }

    private static List<List<Integer>> allPathsUsingBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        // push pair: node, list -> queue
        Queue<NodePathPair> q = new LinkedList<>();
        q.add(new NodePathPair(root, new ArrayList<>()));

        while (!q.isEmpty()) {
            NodePathPair p = q.poll();
            TreeNode node = p.node;
            p.path.add(node.val);
            if (node.left == null && node.right == null) {
                result.add(new ArrayList<>(p.path));
            }
            if (node.left != null) {
                q.add(new NodePathPair(node.left, new ArrayList<>(p.path)));  // create a fresh list
            }
            if (node.right != null) {
                q.add(new NodePathPair(node.right, new ArrayList<>(p.path))); // create a fresh list
            }
        }

        return result;
    }

    static class NodePathPair {
        TreeNode node;
        List<Integer> path;

        public NodePathPair(TreeNode node, List<Integer> path) {
            this.node = node;
            this.path = path;
        }
    }
    /*
    Print (return) all paths from root to leaf in a binary tree -- END
     */

    private static TreeNode createSampleBinaryTree() {

        /*

            1
        2       3
      4  5   6    7
             \
              -3
         */

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n6.right = new TreeNode(-3);

        return n1;
    }

    /*
    Path Sum

    Given the root of a binary tree and an integer targetSum,
    return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
    */
    public static boolean pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        return pathSumUtils(root, targetSum);
    }

    private static boolean pathSumUtils(TreeNode node, int targetSum) {
        // base case: leaf node
        if (node.left == null && node.right == null && node.val == targetSum) {
            return true;
        }
        boolean leftTree = false;
        if (node.left != null) {
            leftTree = pathSumUtils(node.left, targetSum - node.val);
        }
        boolean rightTree = false;
        if (node.right != null) {
            rightTree = pathSumUtils(node.right, targetSum - node.val);
        }
        return leftTree || rightTree;
    }

    /*
    Path Sum II

    Given the root of a binary tree and an integer targetSum,
    return all root-to-leaf paths where the sum of the node values in the path equals targetSum.

    Each path should be returned as a list of the node values, not node references.
    */
    public static List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        pathSumIIUtils(root, targetSum, result, new ArrayList<Integer>());
        return result;
    }

    private static void pathSumIIUtils(TreeNode node, int targetSum,
                                                      List<List<Integer>> result,
                                                      ArrayList<Integer> path) {
        // Common code for leaf nodes and internal nodes
        path.add(node.val); // add current val to path

        // Base case: leaf node
        if (node.left == null && node.right == null) {
            if (node.val == targetSum) {
                result.add(new ArrayList<>(path)); // path found
            }
        }
        if (node.left != null) {
            pathSumIIUtils(node.left, targetSum - node.val, result, path);
        }
        if (node.right != null) {
            pathSumIIUtils(node.right, targetSum - node.val, result, path);
        }

        // remove last value added to the path, to go in the different direction to find valid path sum
        path.remove(path.size() - 1);
    }

    /*
    Binary Tree Longest Consecutive Sequence

    Given the root of a binary tree, return the length of the longest consecutive sequence path.
    A consecutive sequence path is a path where the values increase by one along the path.

    Note that the path can start at any node in the tree, and you cannot go from a node to its parent in the path.
     */
    static int longestSeqLength = 0; // you can use int[] with of length 1, to avoid global var
    public static int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        int startValue = root.val; // this can be any dummy value, which won't create any seq,
                                   // don't use Integer.MAX_VALUE, it may cause int overflow, when you do +1
        int lengthSoFar = 0;
        longestConsecutiveHelper(root, startValue, lengthSoFar);
        return longestSeqLength;
    }

    private static void longestConsecutiveHelper(TreeNode node, int parentVal, int lengthSoFar) {
        // common code to leaf and internal nodes
        if (node.val == parentVal + 1) {
            lengthSoFar += 1;
        } else {
            lengthSoFar = 1;
        }
        longestSeqLength = Math.max(longestSeqLength, lengthSoFar);
        if (node.left != null)
            longestConsecutiveHelper(node.left, node.val, lengthSoFar);
        if (node.right != null)
            longestConsecutiveHelper(node.right, node.val, lengthSoFar);
    }

    /*
    Path Sum III

    Given the root of a binary tree and an integer targetSum,
    return the number of paths where the sum of the values along the path equals targetSum.

    The path does not need to start or end at the root or a leaf,
    but it must go downwards (i.e., traveling only from parent nodes to child nodes).
     */
    static int pathSumIIICount = 0;
    public static int pathSumIII(TreeNode root, int sum) {
        if (root != null) {
            pathSumIIIHelper(root, sum, new ArrayList<>());
        }
        return pathSumIIICount;
    }

    private static void pathSumIIIHelper(TreeNode node, int target, ArrayList<Integer> path) {
        path.add(node.val); // common code for leaf & internal nodes

        // check if the sum ending at this node equals target for all suffix sub-arrays in the path
        long suffixSum = 0; // declar long for int overflow
        for (int i = path.size() - 1; i >= 0; i--) {
            suffixSum += path.get(i);
            if (suffixSum == target) {
                pathSumIIICount++;
            }
        }

        // continue on left and right branches
        if (node.left != null)
            pathSumIIIHelper(node.left, target, path);
        if (node.right != null)
            pathSumIIIHelper(node.right, target, path);

        path.remove(path.size() - 1); // remove last val, for backtracking
    }

    /*
    Invert Binary Tree

    Given the root of a binary tree, invert the tree, and return its root.
     */
    public TreeNode invertTree(TreeNode root) {
        if (root != null)
            dfsInvertTree(root);
        return root;
    }

    private void dfsInvertTree(TreeNode node) {
        // swap left and right node
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        if (node.left != null)
            dfsInvertTree(node.left);

        if (node.right != null)
            dfsInvertTree(node.right);
    }

    /*
    Diameter of Binary Tree

    Given the root of a binary tree, return the length of the diameter of the tree.

    The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
    This path may or may not pass through the root.

    The length of a path between two nodes is represented by the number of edges between them.
     */
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root != null)
            dfsForDiameterOfBinaryTree(root);
        return diameter;
    }

    /*** Bottom up DFS approach: where child returns value to parent node ***/

    // return the max height rooted at this node
    private int dfsForDiameterOfBinaryTree(TreeNode node) {
        // base case: leaf
        if (node.left == null && node.right == null)
            return 0;
        // recursive case: compute max height and update diameter so far as well
        int leftHeight = 0;
        if (node.left != null) {
            leftHeight = 1 + dfsForDiameterOfBinaryTree(node.left);
        }
        int rightHeight = 0;
        if (node.right != null) {
            rightHeight = 1 + dfsForDiameterOfBinaryTree(node.right);
        }
        int diameterAtThisNode = leftHeight + rightHeight;
        diameter = Math.max(diameter, diameterAtThisNode);
        return Math.max(leftHeight, rightHeight); // return max height (longest path at this node)
    }

    /*
    Count Univalue Subtrees

    Given the root of a binary tree, return the number of uni-value subtrees.
    A uni-value subtree means all nodes of the subtree have the same value.
     */
    int totalUnivalCount = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root != null)
            dfsCountUnivalSubstrees(root);
        return totalUnivalCount;
    }
    // Return true if subtree rooted at this node is univalued
    private boolean dfsCountUnivalSubstrees(TreeNode node) {
        // base case: leaf
        if (node.left == null && node.right == null) {
            totalUnivalCount++; // increment unival subtree count
            return true; // one node is always univalued
        }
        // recursive case
        boolean isUnival = true;
        if (node.left != null) {
            boolean bL = dfsCountUnivalSubstrees(node.left);
            if (!bL || node.val != node.left.val)
                isUnival = false;
        }
        if (node.right != null) {
            boolean rL = dfsCountUnivalSubstrees(node.right);
            if (!rL || node.val != node.right.val)
                isUnival = false;
        }
        if (isUnival)
            totalUnivalCount++;
        return isUnival;
    }

    /*
    Lowest Common Ancestor of a Binary Tree

    Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

    According to the definition of LCA on Wikipedia:
    “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
    that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     */
    TreeNode lca = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root != null) {
            lowestCommonAncestorHelper(root, p, q);
        }
        return lca;
    }

    static class LcaReturnVal {
        boolean pFound;
        boolean qFound;
    }

    // This method returns two boolean flags indicating whether p and q were found in the subtree or not
    private LcaReturnVal lowestCommonAncestorHelper(TreeNode node, TreeNode p, TreeNode q) {
        // common code
        LcaReturnVal rVal = new LcaReturnVal();
        if (node == p)
            rVal.pFound = true;
        if (node == q)
            rVal.qFound = true;

        // base case: leaf node
        if (node.left == null && node.right == null)
            return rVal;

        // recursive case
        if (node.left != null) {
            LcaReturnVal rVL = lowestCommonAncestorHelper(node.left, p, q);
            rVal.pFound = rVal.pFound || rVL.pFound;
            rVal.qFound = rVal.qFound || rVL.qFound;
        }
        if (node.right != null) {
            LcaReturnVal rVR = lowestCommonAncestorHelper(node.right, p, q);
            rVal.pFound = rVal.pFound || rVR.pFound;
            rVal.qFound = rVal.qFound || rVR.qFound;
        }

        // check if LCA found at this stage
        if (rVal.pFound && rVal.qFound && lca == null) {
            lca = node; // found both p & q under it's subtree
        }

        return rVal;
    }

}
