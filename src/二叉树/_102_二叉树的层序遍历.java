package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _102_二叉树的层序遍历 {
	/*
	 * 思路
	 * 	
	 */
    public List<List<Integer>> levelOrder(TreeNode root) {
//    	<List<Integer>>代表二位数组,每一个元素代表每一层遍历的结果,一维数组内是每一层的值.
    	List<List<Integer>> resList = new ArrayList<List<Integer>>();
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	if (root == null) return resList;
    	queue.offer(root);
    	while (!queue.isEmpty()) {
			List<Integer> leveList = new ArrayList<Integer>();
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				TreeNode node = queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				leveList.add(node.val);
			}
			resList.add(leveList);
		}
    	return resList;
    }
}
