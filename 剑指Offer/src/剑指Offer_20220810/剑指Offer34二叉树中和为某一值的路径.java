package 剑指Offer_20220810;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 剑指Offer34二叉树中和为某一值的路径 {
	LinkedList<List<Integer>> res = new LinkedList<>();
	Deque<Integer> path = new LinkedList<Integer>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
    	//思路,小数组使用双端队列,先序遍历,回溯.添加到一维数组条件tar -= 0,为叶子节点
    	dfs(root, target);
    	return res;
    }
    public void dfs(TreeNode node,int target) {
		if (node == null) {
			return;
		}
		target -= node.val;
		path.add(node.val);
		if (target == 0 && node.left == null && node.right == null) {
			res.add(new LinkedList<Integer>(path));
		}
		dfs(node.left, target);
		dfs(node.right, target);
		path.removeLast();//回溯
	}
}
