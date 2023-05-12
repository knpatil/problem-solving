package main.java.trees;

public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  public TreeNode(int val) {
    this.val = val;
  }

  public void left(TreeNode node) {
    this.left = node;
  }

  public void right(TreeNode node) {
    this.right = node;
  }
}
