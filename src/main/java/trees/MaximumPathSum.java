package main.java.trees;

public class MaximumPathSum {

  static int result = Integer.MIN_VALUE;

  /*
              6
           2       3
                4     5
             7
 */

  public static void main(String[] args) {
    TreeNode root = new TreeNode(6);
    root.left(new TreeNode(2));
    root.right(new TreeNode(3));
    root.right.left(new TreeNode(4));
    root.right.left.left(new TreeNode(7));
    root.right.right(new TreeNode(5));

    maximumPathSum(root);

    System.out.println("Result : " + result);
  }

  // maximum path sum from any node to any node
  private static int maximumPathSum(TreeNode root) {
    if (root == null)
      return 0;

    int l = maximumPathSum(root.left);
    int r = maximumPathSum(root.right);

    int temp = Math.max(Math.max(l, r) + root.val, root.val);
    int ans = Math.max(temp, l + r + root.val);
    result = Math.max(result, ans);
    return temp;
  }

  private static int maxPathSumFromLeafToLeaf(TreeNode root) {
    if (root == null)
      return 0;

    int l = maximumPathSum(root.left);
    int r = maximumPathSum(root.right);

    int temp = Math.max(l, r) + root.val;
    int ans = Math.max(temp, l + r + root.val);
    result = Math.max(result, ans);
    return temp;
  }
}
