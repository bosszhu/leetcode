package 二叉树;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _144_二叉树的前序遍历 {
	/*
	 * 递归:NLR(针对的是根节点)
	 */
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> resluList = new ArrayList<Integer>();
    	preorder(root, resluList);
    	return resluList;
    }
    
    public void preorder(TreeNode node,List<Integer> res) {
		if (node == null) return;
		res.add(node.val);
		preorder(node.left, res);
		preorder(node.right, res);
	}
    
    /*
     * 迭代:和递归的区别,递归隐式的维护了一个栈.迭代需要将他显现出来.和层序遍历的区别,前序遍历用栈结构,需要先压栈.层序遍历用的队列,入队操作.
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
    	List<Integer> res = new ArrayList<Integer>();
    	if (root == null) {
			return res;
		}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = root;
    	while (!stack.isEmpty() || node != null) {
			while (node != null) {
				res.add(node.val);
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			node = node.right;
		}
    	return res;
    }
}
