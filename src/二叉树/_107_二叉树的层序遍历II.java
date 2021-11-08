package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _107_二叉树的层序遍历II {
	/*
	 * 返回二维数组,自底向下存到数组.
	 */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
			resList.add(0, leveList);;
		}
    	return resList;
    }
}
