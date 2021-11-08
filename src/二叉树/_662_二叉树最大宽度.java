package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _662_二叉树最大宽度 {
	/*
	 * 思路:层序遍历,找到最大的就替换否则,就是最大的知道完成.
	 * 但是题目中需要找到的还包含null,所以此算法不可行,我们需要标记这个treeNode中每一个元素的位置
	 * 1. 宽度优先搜索
	 * 		1. 准备一个新的数据结构AnnotatedNode:用来注释node的详细位置.
	 * 			内部包含,position,depth,node(当前节点).
	 * 		2. 入队时,不进将root入队还需要带其他参数.
	 * 		3. 层序遍历
	 *			1. 当前节点他的每个节点都满足,左节点是position * 2,右节点是position * 2 + 1(可以自己尝试计算)
	 *			2. 需要注意每层深度变化时,需要改变临时变量深度和left
	 *			3. 最大宽度就是上一次宽度和当前宽度比较得到的大小(当前宽度为当前节点的位置position - left + 1)
	 * 2. 高度优先搜索
	 * 
	 */
    public int widthOfBinaryTree(TreeNode root) {
    	if (root == null) return 0;
    	int ans = 0;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	LinkedList<Integer> indexQueue = new LinkedList<Integer>();//辅助计算
    	
    	queue.offer(root);//加入根节点同时,indexQueue加入值
    	indexQueue.add(1);
    	while (!queue.isEmpty()) {
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {//因为循环是多次进来的
				TreeNode node = queue.poll();//删除节点,indexQueue也需要删除最前面的值
				int index = indexQueue.removeFirst();
				
				if (node.left != null) {
					queue.offer(node.left);
					indexQueue.add(index * 2);
				}
				if (node.right != null) {
					queue.offer(node.right);
					indexQueue.add(index * 2 + 1);
				}
			}
	    	
	    	if (indexQueue.size() > 1) {
	    		//最后当前层的宽度就是最后一个的下标减去第一个下标+1;
				ans = Math.max(ans, indexQueue.peekLast() - indexQueue.peekFirst() + 1);
			}
		}
    	return ans;
    }
}
