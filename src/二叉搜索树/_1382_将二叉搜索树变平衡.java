package 二叉搜索树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _1382_将二叉搜索树变平衡 {
	/*
	 * 中序遍历,再生成新的二叉树
	 */
    public TreeNode balanceBST(TreeNode root) {
    	if (root == null) {
			return null;
		}
    	List<Integer> list = new ArrayList<Integer>();
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			list.add(root.val);
			root = root.right;
		}
    	return balanceBSTFromArray(list,0,list.size()-1);
    }
    private TreeNode balanceBSTFromArray(List<Integer> list,int left,int right) {
		if (left > right) {
			return null;
		}
		int mid = (left + right)/2;
		TreeNode node = new TreeNode(list.get(mid));
		node.left = balanceBSTFromArray(list, left, mid - 1);
		node.right = balanceBSTFromArray(list, mid + 1, right);
		return node;
	}
}
