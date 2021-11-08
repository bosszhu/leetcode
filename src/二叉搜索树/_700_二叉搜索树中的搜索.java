package 二叉搜索树;

public class _700_二叉搜索树中的搜索 {
	/*
	 * 利用性质:返回子树
	 */
    public TreeNode searchBST(TreeNode root, int val) {
    	TreeNode node = root;
    	while (node != null) {
			if (val > node.val) {
				node = node.right;
			} else if (val < node.val) {
				node = node.left;
			} else {
				return node;
			}
		}
    	return null;
    }
}
