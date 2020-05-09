package main.java;

/*
Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */

// The diameter of a given tree is the largest of the following three quantities:
// diameter left subtree
// diameter right subtree
// the longest path between leaf nodes that goes through the root of tree

import java.util.Arrays;

public class BinaryTreeDiameter {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int diameter = diameterOfBinaryTree2(root);
        System.out.println("Diameter of the tree is " + diameter);
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int L = getHeight(root.left);
        int R = getHeight(root.right);

        int lDiameter = diameterOfBinaryTree(root.left);
        int rDiameter = diameterOfBinaryTree(root.right);

        return Math.max(L + R, Math.max(lDiameter, rDiameter));
    }

    public static int getHeight(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int diameterOfBinaryTree2(TreeNode root) {
        int[] result = traversal(root);
        return result[1];
    }

    /**
     * ret[0] max depth; ret[1] max diameter
     */
    private static int[] traversal(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = traversal(node.left);
        Arrays.stream(l).forEach(System.out::print);
        System.out.println();
        int[] r = traversal(node.right);
        Arrays.stream(r).forEach(System.out::print);
        System.out.println();

        int depth = Math.max(l[0], r[0]) + 1;
        System.out.println("Depth: " + depth);

        int diameter = Math.max(l[1], Math.max(r[1], l[0] + r[0]));
        System.out.println("Diameter: " + diameter);
        return new int[]{depth, diameter};
    }
}

