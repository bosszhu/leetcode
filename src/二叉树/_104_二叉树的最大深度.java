package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class _104_二叉树的最大深度 {
//	/*
//	 * 递归
//	 */
//    public int maxDepth(TreeNode root) {
//    	if (root == null) return 0;
//    	return 1 + Math.max(maxDepth(root.left),  (root.right));
//    }
	
	/*
	 * 迭代
	 */
    public int maxDepth(TreeNode root) {
    	if (root == null) return 0;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(root);
    	int levelDeep = 0;
    	while (!queue.isEmpty()) {
			int levelSize = queue.size();
			int tempSize = levelSize;
			for (int i = 0; i < levelSize; i++) {//因为循环是多次进来的
				TreeNode node = queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				tempSize--;
			}
			if (tempSize == 0) {
				levelDeep++;
			}
		}
    	return levelDeep;
    }
}
