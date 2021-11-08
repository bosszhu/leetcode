package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _145_二叉树的后序遍历 {
	/*
	 * LRN
	 */
    public List<Integer> postorderTraversal(TreeNode root) {
    	List<Integer> resluList = new ArrayList<Integer>();
    	postorder(root, resluList);
    	return resluList;
    }
    
    public void postorder(TreeNode node,List<Integer> res) {
		if (node == null) return;
		postorder(node.left, res);
		postorder(node.right, res);
		res.add(node.val);
	}
    
    /*
     * 迭代后续遍历
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
    	List<Integer> res = new ArrayList<Integer>();
    	if (root == null) {
			return res;
		}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = root;
    	TreeNode prev =  null;
    	while (!stack.isEmpty()||node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
            if (node.right != null && node.right != prev) {//右子树存在且未访问过
				stack.push(node);//进栈
				node = node.right;//继续向下
			} else {//开始访问
				res.add(node.val);//数组添加
				prev = node;//前置节点赋值,避免重复访问右子树
				node = null;//避免重复访问左子树
			}
		}
    	return res;
    }
}
