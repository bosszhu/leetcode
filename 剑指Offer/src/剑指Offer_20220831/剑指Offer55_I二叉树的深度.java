package 剑指Offer_20220831;

public class 剑指Offer55_I二叉树的深度 {
	/*
	 * 递归:左子树右子树的最大深度+1
	 */
    public int maxDepth(TreeNode root) {
    	if (root == null) {
			return 0;
		}
    	return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
