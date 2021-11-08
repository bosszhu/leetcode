package 链表;
/*
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 
 * 思路:快慢指针,但是不是移动快慢而是初始快慢
 */
public class 剑指Offer22_链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
    	ListNode fast = head;
    	for (int i = 0; i < k; i++) {
			fast = fast.next;
		}
    	ListNode slow = head;
    	
    	while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
    	return slow;
    }
    
    
    public int getKthFromEnd1(ListNode head, int k) {
    	ListNode fast = head;
    	for (int i = 0; i < k; i++) {
			fast = fast.next;
		}
    	ListNode slow = head;
    	
    	while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
    	return slow.val;
    }
    
    /*
     * '
     * 
     * 关键思路:前置节点进行摘链处理
     * 
     */
    public ListNode deleteNode(ListNode head, int val) {
    	if (head.val == val) {
			return head.next;
		}
    	ListNode temp = head;
    	ListNode pre = null;
    	while (temp != null) {
			if (temp.val == val) {
				pre.next = temp.next;//前置节点的next等于当前节点的next
			}
			pre = temp;
			temp = temp.next;
		}
    	return head;
    }
}
