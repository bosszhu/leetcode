package 二叉搜索树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _230_二叉搜索树中第K小的元素 {
	/*
	 * 思路:
	 * 	2. 中序遍历k取下标k
	 */
    public int kthSmallest(TreeNode root, int k) {
    	List<Integer> list = new ArrayList<>();
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
    	if (k > list.size()) {
    		return list.get(k);
		}
    	return 0;
    }
}
