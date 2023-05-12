package main.java.trees;

public class DiameterOfTree {

  static int diameter = Integer.MIN_VALUE;

  /*
    General format:
      Base Condition
      Hypotheses -- if you know the height of left subtree and right subtree, then height from root is 1 + that height
      Induction -- induce answer based on the hypothesis for current state (node)
  */

  /*
                1
             2       3
                  4     5
               7
   */
  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left(new TreeNode(2));
    root.right(new TreeNode(3));
    root.right.left(new TreeNode(4));
    root.right.left.left(new TreeNode(7));
    root.right.right(new TreeNode(5));
    diameterOfTree(root);
    System.out.println("Diameter : " + diameter);
  }

  // longest path between any two nodes is a diameter of the tree
  private static int diameterOfTree(TreeNode root) {
    // base condition
    if (root == null)
      return 0;

    // hypotheses
    int lh = diameterOfTree(root.left);
    int rh = diameterOfTree(root.right);

    // induction
    int temp = 1 + Math.max(lh, rh); // temporary answer to pass to the parent node
    int ans = Math.max(temp, 1 + lh + rh);  // this could be highest diameter
    diameter = Math.max(diameter, ans);  // update global diameter
    return temp;  // pass the current highest
  }

}
