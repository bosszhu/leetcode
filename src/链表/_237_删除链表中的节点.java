package 链表;
/*
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * 剑指 Offer 10- I. 斐波那契数列
 * 
 */
public class _237_删除链表中的节点 {
	 public void deleteNode(ListNode node) {
		 node.val = node.next.val;
		 node.next = node.next.next;
	 }
}
