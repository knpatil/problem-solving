package main.java.recursion;

public class TreeHeight {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(10);
    root.left(new TreeNode(1));

    root.right(new TreeNode(2));
    root.right.right(new TreeNode(5));
    root.right.left(new TreeNode(7));
    root.right.right.right(new TreeNode(6));

    int h = heightOfTree(root);
    System.out.println("Height : " + h);
  }

  private static int heightOfTree(TreeNode root) {
    if (root == null)
      return 0;
    int lh = heightOfTree(root.left);
    int rh = heightOfTree(root.right);
    return 1 + Math.max(lh, rh);
  }

  private static class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
      this.value = value;
    }

    public void left(TreeNode treeNode) {
      this.left = treeNode;
    }

    public void right(TreeNode treeNode) {
      this.right = treeNode;
    }
  }
}
