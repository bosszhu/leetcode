package 剑指Offer_20220801;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 剑指Offer32_I从上到下打印二叉树 {
//	实现二叉树前中后层序遍历
    public int[] preOrderRecursion(TreeNode root) {
    	if (root == null) {
    		return new int[]{};
		}
    	List<Integer> list = new ArrayList<>();
    	preOrderTree(list, root);
    	int []result = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		result[i] = list.get(i);
		}
    	return result;
    }
    public void preOrderTree(List <Integer>list,TreeNode node) {
		if (node == null) {
			return;
		}
		list.add(node.val);
		preOrderTree(list, node.left);
		preOrderTree(list, node.right);
	}
    
//    前序
    public int[] preOrder(TreeNode root) {
    	if (root == null) {
    		return new int[]{};
		}
    	List<Integer> list = new ArrayList<>();
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode node = root;
    	while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				list.add(node.val);
				node = node.left;
			}
			node = stack.pop();
			node = node.right;
		}
    	int []result = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		result[i] = list.get(i);
		}
    	return result;
    }
//    中序
    public int[] norderOrder(TreeNode root) {
    	if (root == null) {
    		return new int[]{};
		}
    	List<Integer> list = new ArrayList<>();
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode node = root;
    	while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			list.add(node.val);
			node = node.right;//开始右边
		}
    	int []result = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		result[i] = list.get(i);
		}
    	return result;
    }
    
    public int[] postorder(TreeNode root) {
    	if (root == null) {
    		return new int[]{};
		}
    	List<Integer> list = new ArrayList<>();
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode node = root;
    	TreeNode pre = null;//前置节点
    	while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();//取出最左边的节点
			if (node.right != null && node.right != pre) {//当前根节点右侧有值
				stack.push(node);
				node = node.right;
			} else {
				list.add(node.val);
				pre = node;
				node = null;
			}
		}
    	int []result = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		result[i] = list.get(i);
		}
    	return result;
    }
    
    //层序遍历
    public int[] levelOrder(TreeNode root) {
    	if (root == null) {
    		return new int[]{};
		}
    	List<Integer> list = new ArrayList<>();
    	Deque<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);//根节点入队
    	while (!queue.isEmpty()) {
        	for (int i = 0; i < queue.size(); i++) {
    			//拿到当前节点
        		TreeNode node = queue.poll();
        		if (node.left != null) {
    				queue.offer(node.left);
    			}
        		if (node.right != null) {
    				queue.offer(node.right);
    			}
        		list.add(node.val);
    		}
		}
    	int []result = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		result[i] = list.get(i);
		}
    	return result;
    }

}
