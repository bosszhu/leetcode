package 剑指Offer_20220802;

import java.util.LinkedList;
import java.util.Queue;

public class 剑指Offer28对称的二叉树 {
	//层序遍历只能拿到一个节点,无法比较叔父节点
    public boolean isSymmetric(TreeNode root) {
    	if (root == null) {
			return true;//当前是null肯定是左右对称的
		}
//    	return checkSymmetryDigui(root, root);
    	return checkSymmetry(root, root);//开始直接将根节点入队两次
    }
    public boolean checkSymmetryDigui(TreeNode node1,TreeNode node2) {
		if (node1 == null && node2 == null) {
			return true;
		}
		if (node1 == null || node2 == null) {
			return false;//有一个有值另一个没值就不是镜像
		}
		if (node1.val != node2.val) {//值不相等不是镜像
			return false;
		}	
		return checkSymmetryDigui(node1.left, node2.right) && checkSymmetryDigui(node1.right, node2.left);
	}
    
    
    public boolean checkSymmetry(TreeNode node1,TreeNode node2) {
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(node1);
    	queue.offer(node2);
    	while (!queue.isEmpty()) {
			node1 = queue.poll();
			node2 = queue.poll();
			if (node1 == null && node2 == null) {
				continue;
			}
			if ((node1 == null || node2 == null)) {
				//有一个有值另一个没值
				return false;
			}
			if (node1.val != node2.val) {
				return false;
			}
			queue.offer(node1.left);
			queue.offer(node2.right);
			queue.offer(node1.right);
			queue.offer(node2.left);
		}
		return true;
	}
}
