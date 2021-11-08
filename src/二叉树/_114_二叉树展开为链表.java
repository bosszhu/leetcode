package 二叉树;

import java.util.ArrayList;
import java.util.List;

public class _114_二叉树展开为链表 {
    public void flatten(TreeNode root) {
    	/*
    	 * 思路:
    	 * 	 1. 首先先序遍历二叉树,将节点全部存到数组内.
    	 * 	 2. 然后组装二叉树.
    	 */
    	List<TreeNode> list = new ArrayList<TreeNode>();
    	preorder(root, list);
    	for (int i = 1; i < list.size(); i++) {
			TreeNode preNode = list.get(i-1),
			cur = list.get(i);
			preNode.left = null;
			preNode.right = cur;
		}
    }
    public void preorder(TreeNode node,List<TreeNode> res) {
		if (node == null) return;
		res.add(node);
		preorder(node.left, res);
		preorder(node.right, res);
	}
    
}
