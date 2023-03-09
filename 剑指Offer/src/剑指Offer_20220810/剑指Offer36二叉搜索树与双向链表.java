package 剑指Offer_20220810;

public class 剑指Offer36二叉搜索树与双向链表 {
	/*
	 * 准备头尾结点,
	 * 1. 先序遍历
	 *		1. 当前尾节点是空,当前节点是头结点
	 *		2. 当前尾结点不是空,当前尾结点的right是当前节点,当前节点的left节点是尾结点
	 * 2. 当前尾结点更新
	 * 3. 主函数修改为循环链表,head.left = tail,tail.right = head;返回head 
	 */
	Node head,tail;
    public Node treeToDoublyList(Node root) {
    	if (root == null) {
			return null;
		}
    	this.head = null;
    	this.tail = null;
    	dfs(root);
    	head.left = tail;
    	tail.right = head;
    	return head;
    }
    public void dfs(Node cur) {
		if (cur == null) {
			return;
		}
		dfs(cur.left);
		if (tail == null) {
			head = cur;
		} else {
			tail.right = cur;
			cur.left = tail;
		}
		tail = cur;
		dfs(cur.right);
	}
    
}
