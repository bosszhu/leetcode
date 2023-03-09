package 剑指Offer_20220909;

import java.util.Deque;
import java.util.LinkedList;

public class 剑指Offer37序列化二叉树 {
	/*
	 * 思路:层序遍历
	 * 		序列化:层序遍历添加到数组中.注意字符串拼接
	 * 		反序列化:去除头尾[],然后以,分隔为字符串.去第一个元素作为根节点入队,开始层序遍历判断
	 */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	if (root == null) {
			return "[]";
		}
    	StringBuilder stringBuilder = new StringBuilder("[");
    	Deque<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == null) {
				stringBuilder.append("null,");
			} else {
				stringBuilder.append(node.val + ",");
				queue.offer(node.left);
				queue.offer(node.right);
			}
		}
    	//删除,添加]
    	stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    	stringBuilder.append("]");
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if (data.equals("[]") ) {
			return null;
		}
    	//分隔字符串并且创建下标
    	String[] strins = data.substring(1, data.length() - 1).split(",");
    	int index = 1;
    	//根据数组的首元素创建节点,并且入队
    	TreeNode root = new TreeNode(Integer.parseInt(strins[0]));
    	Deque<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	
    	while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (!strins[index].equals("null")) {//如果是null
				node.left = new TreeNode(Integer.parseInt(strins[index]));
				//将左节点入队
				queue.offer(node.left);
			}
			//然后下标++
			index++;
			if (!strins[index].equals("null")) {//如果是null
				node.right = new TreeNode(Integer.parseInt(strins[index]));
				//将左节点入队
				queue.offer(node.right);
			}
			index++;
		}
        return root;
    }
}
