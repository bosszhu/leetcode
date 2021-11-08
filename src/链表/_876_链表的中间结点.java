package 链表;
/*
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * 
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * 
 * 思路快慢指针,
 */
public class _876_链表的中间结点 {
    public ListNode middleNode(ListNode head) {
    	ListNode fast = head;
    	ListNode slow = head;
    	//因为是连续的两次移动判断需要判断,当前的fast是否为null以及fast.next是否为null.奇数情况,最后一个元素就停止遍历,偶数情况,要遍历完成才结束.
    	while (fast != null && fast.next != null) {
    
			fast = fast.next.next; //连续移动两次
			slow = slow.next;
		}
    	return slow;
    }
}
