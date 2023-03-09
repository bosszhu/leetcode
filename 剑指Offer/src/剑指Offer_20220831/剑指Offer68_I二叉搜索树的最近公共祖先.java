package 剑指Offer_20220831;

public class 剑指Offer68_I二叉搜索树的最近公共祖先 {
	/*
	 * 思路:
	 * 		二叉搜索树特点:所有节点左子树都小于当前节点的值,右子树都大于当前节点的值.
	 * 		判断公共祖先:判断p,q是否在同一侧.
	 * 			1. 递归
	 * 			2. 判断p,q内部的值和root的值比较.
	 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	if (root == null) {
            return root;
        }
    	if (root.val > p.val && root.val > q.val) {
			//根节点都大于p,q
    		return lowestCommonAncestor(root.left, p, q);//找左边
		}
    	if (root.val < p.val && root.val < q.val) {
    		//根节点都小于p,q
    		return lowestCommonAncestor(root.right, p, q);//找右边
		}
        return root;
    }
}
