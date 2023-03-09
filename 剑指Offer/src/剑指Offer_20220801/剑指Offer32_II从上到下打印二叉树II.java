package 剑指Offer_20220801;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 剑指Offer32_II从上到下打印二叉树II {
    
	//按层打印,每一层打印到第一行
    public List<List<Integer>> levelOrder(TreeNode root) {
	List<List<Integer>> list = new ArrayList<>();
	if (root == null) {
		return list;
	}
	Deque<TreeNode> queue = new LinkedList<>();
	queue.offer(root);//根节点入队
	while (!queue.isEmpty()) {
    	List<Integer> levelList = new ArrayList<>();
        int levelSize = queue.size();//size需要通过临时变量存起来否则,会在循环内poll出来相当于数组长度会变小
    	for (int i = 0; i < levelSize; i++) {//多取一个值
			//拿到当前节点
    		TreeNode node = queue.poll();
    		if (node.left != null) {
				queue.offer(node.left);
			}
    		if (node.right != null) {
				queue.offer(node.right);
			}
    		levelList.add(node.val);
		}
    	list.add(levelList);
	}
	return list;
}
}
