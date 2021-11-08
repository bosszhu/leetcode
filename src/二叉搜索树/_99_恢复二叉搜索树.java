package 二叉搜索树;

import java.util.Stack;

//给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
//
//进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
//
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/recover-binary-search-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class _99_恢复二叉搜索树 {
	
	/*
	 * 自己思路:二叉搜索树,肯定是中序遍历的值是增大的,当出现,小于中序遍历的值时.交换两者值的位置.就不改变结构
	 */
    public void recoverTree(TreeNode root) {
    	inorderBST(root);
    }
    public void inorderBST(TreeNode node) {
		if (node == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode prev = null;
        TreeNode x = null,y = null;
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			if (prev != null && node.val <= prev.val) {
                y = node;
                if (x == null) {
                    x = prev;
                } else {
                    break;
                }
			}
			prev = node;
			node = node.right;
		}
        swap(x,y);
	}

    private void swap(TreeNode x,TreeNode y) {
		int tempVal = x.val;
		x.val = y.val;
		y.val = tempVal;
	}
}
