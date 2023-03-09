package 剑指Offer_20220831;

public class 剑指Offer68_II二叉树的最近公共祖先 {
	/*
	 * 思路:
	 * 	  判断p,q是否都在左边
	 * 	  判断p,q是否都在右边
	 * 	  否则返回根节点
	 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	//如果root是null或者是p或q返回当前节点
    	if (root == null || root == p || root == q) {
			return root;
		}
    	//返回treeNode
    	TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
    	TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
    	if (leftNode == null) {//如果左子树是null,代表两者一定在右子树中
			return rightNode;
		}
    	if (rightNode == null) {//如果右子树是null,代表一定在左子树中
			return leftNode;
		}
    	return root;
    }
}
