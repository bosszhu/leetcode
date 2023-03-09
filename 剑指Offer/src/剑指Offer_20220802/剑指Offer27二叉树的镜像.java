package 剑指Offer_20220802;

import java.util.LinkedList;
import java.util.Queue;

public class 剑指Offer27二叉树的镜像 {
    public TreeNode mirrorTree(TreeNode root) {
    	//相当于翻转:思路层序遍历翻转
    	if (root == null) {
			return null;
		}
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				//交换左右子树(为null时也能交换)
				swap(node);
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
    public void swap(TreeNode node) {
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;
	}
}
