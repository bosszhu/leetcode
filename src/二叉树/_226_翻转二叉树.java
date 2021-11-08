package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class _226_翻转二叉树 {
	
    public TreeNode invertTree(TreeNode root) {
    	/*
    	 * 利用层序遍历翻转
    	 */
    	if (root == null) return null;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(root);
    	while (!queue.isEmpty()) {
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				TreeNode node = queue.poll();
				TreeNode tempNode = node.left;
				node.left = node.right;
				node.right = tempNode;
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}
    	return root;
    }
    
    public TreeNode invertTree1(TreeNode root) {
    	/*
    	 * 利用迭代翻转
    	 */
    	if (root == null) return null;
    	preOrder(root);
    	return root;
    }
    
    public void preOrder(TreeNode node) {
		if (node == null) return;
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;
		preOrder(node.left);
		preOrder(node.right);
	}
}
