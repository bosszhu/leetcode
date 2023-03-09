package 剑指Offer_20220801;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 剑指Offer32_III从上到下打印二叉树III {
//	第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
//	java中Queue是单向队列,Deque是双端队列
    public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> list = new ArrayList<>();
    	if (root == null) {
    		return list;
		}
    	Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
    	queue.offer(root);//根节点入队
    	boolean flag = true;//是否翻转
    	while (!queue.isEmpty()) {
    		Deque<Integer> levelList = new LinkedList<Integer>();
            int levelSize = queue.size();//size需要通过临时变量存起来否则,会在循环内poll出来相当于数组长度会变小
        	for (int i = 0; i < levelSize; i++) {//多取一个值
    			//拿到当前节点
        		TreeNode node = queue.poll();
        		if (flag == true) {//不需要改变位置
        			levelList.offerLast(node.val);
				} else {
					levelList.offerFirst(node.val);
				}
        		if (node.right != null) {
    				queue.offer(node.right);
    			}
        		if (node.left != null) {
    				queue.offer(node.left);
    			}
        		
    		}
        	list.add(new LinkedList<Integer>(levelList));
    		flag = !flag;//取反
		}
    	return list;
    }
}
