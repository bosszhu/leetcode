package 剑指Offer_20220802;

import java.util.Stack;

public class 剑指Offer26树的子结构 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
    	//先序遍历A树的每一个节点
    	if (A == null || B == null) {
			return false;
		}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = A;
    	while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				//此处判断
				if (judgeParent(node, B)) {
					return true; 
				}
				node = node.left;
			}
			//取出node
			TreeNode temp = stack.pop();
			node = temp.right;
		}
    	return false;//遍历完还没找到就为false
    }
    
    public boolean judgeParent(TreeNode Ason,TreeNode B) {
    	if (B == null) {
			return true;
		}
    	if (Ason == null) {
			return false;
		}
    	if (Ason.val != B.val) {
			return false;
		}
		return judgeParent(Ason.left, B.left) && judgeParent(Ason.right, B.right);
	}
}
