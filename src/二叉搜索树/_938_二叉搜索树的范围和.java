package 二叉搜索树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _938_二叉搜索树的范围和 {
	/*
	 * 中序遍历,顺序从小到大
	 */
    public int rangeSumBST(TreeNode root, int low, int high) {
    	int res = 0;
    	List<Integer> list = new ArrayList<>();
    	if (root == null) {
			return res;
		}
    	//中序遍历
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode node = root;
    	while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			TreeNode temp = stack.pop();
			list.add(temp.val);
			node = temp.right;
		}
    	for (int i = 0; i < list.size(); i++) {
    		int itemVal = list.get(i);
			if (itemVal >= low && itemVal <= high) {
				res += list.get(i);
			}
		}
    	return res;
    }
}
