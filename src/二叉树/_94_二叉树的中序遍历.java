package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.Popup;

public class _94_二叉树的中序遍历 {

	/*
	 * LNR
	 */
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> resluList = new ArrayList<Integer>();
    	inorder(root, resluList);
    	return resluList;
    }
    
    public void inorder(TreeNode node,List<Integer> res) {
		if (node == null) return;
		inorder(node.left, res);
		res.add(node.val);
		inorder(node.right, res);
	}
    
    /*
     * 迭代遍历
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
    	List<Integer> res = new ArrayList<Integer>();
    	if (root == null) {
			return res;
		}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = root;
    	while (!stack.isEmpty() || node != null) {
    		while (node != null) {
    			stack.push(node);
    			node = node.left;
			}
    		node = stack.pop();
    		res.add(node.val);
    		node = node.right;
		}
    	return res;
    }
}
