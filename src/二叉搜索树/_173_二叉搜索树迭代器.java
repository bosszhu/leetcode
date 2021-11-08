package 二叉搜索树;

import java.util.Stack;

public class _173_二叉搜索树迭代器 {
	/*
	 * 不理解什么事迭代器:cur变化了,不需要另外删除元素.当前cur不为null,或者stack内还有值,就是代表二叉树未迭代完
	 */
	class BSTIterator {
	    private TreeNode cur;
	    private Stack<TreeNode> stack;
	    public BSTIterator(TreeNode root) {
	    	cur = root;
	    	stack = new Stack<TreeNode>();
	    }
	    
	    public int next() {
	    	while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
	    	cur = stack.pop();
	    	int ret = cur.val;
	    	cur = cur.right;
	    	return ret;
	    }
	    public boolean hasNext() {
	    	//当前节点不为null,或者stack中有值
	    	return cur != null || !stack.isEmpty();
	    }
	}

}
