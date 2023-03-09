package 剑指Offer_20220831;

public class 剑指Offer55_II平衡二叉树 {
	/*
	 * 思路:左右子树高度之差小于等于并且左右子树的子树也需要满足这个要求
	 */
    public boolean isBalanced(TreeNode root) {
    	if (root == null) {
			return true;//空树是平衡二叉树
		}
    	int res = Math.abs(maxDepth(root.left) - maxDepth(root.right));
    	return res <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int maxDepth(TreeNode root) {
    	if (root == null) {
			return 0;
		}
    	return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
