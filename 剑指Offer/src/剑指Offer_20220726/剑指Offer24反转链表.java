package 剑指Offer_20220726;

import sun.net.www.content.text.plain;

public class 剑指Offer24反转链表 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	class Solution {
	    public ListNode reverseList(ListNode head) {
	    	if (head == null) {
				return head;
			}
	    	ListNode newHead = null;
	    	while (head != null) {
				ListNode temp = head.next;
				head.next = newHead;
				newHead = head;
				head = temp;
			}
	    	return newHead;
	    }
	}
}
