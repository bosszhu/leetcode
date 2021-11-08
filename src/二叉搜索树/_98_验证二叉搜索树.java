package 二叉搜索树;
import java.util.Stack;

//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
//假设一个二叉搜索树具有如下特征：
//
//节点的左子树只包含小于当前节点的数。
//节点的右子树只包含大于当前节点的数。
//所有左子树和右子树自身必须也是二叉搜索树。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/validate-binary-search-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class _98_验证二叉搜索树 {
	long pre = Long.MIN_VALUE; // 记录上一个节点的值，初始值为Long的最小值;
    public boolean isValidBST(TreeNode root) {
    	//中序遍历
    	return inorderBST(root);
    }
    //中序遍历是否满足(需提供
    public boolean inorderBST1(TreeNode node) {
    	if (node == null) {
			return true;
		}
    	if (!inorderBST(node.left)) {
			return false;
		}
    	if (node.val <= pre) {
			return false;
		}
    	pre = node.val;
    	if (!inorderBST(node.right)) {
			return false;
		}
    	return true;
	}
    /*
     * 迭代中序遍历
     */
    public boolean inorderBST(TreeNode node) {
		if (node == null) {
			return true;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		long preVal = Long.MIN_VALUE;
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			if (node.val <= preVal) {
				return false;
			}
			preVal = node.val;
			node = node.right;
		}
		return true;
	}
}
