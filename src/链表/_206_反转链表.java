package 链表;

public class _206_反转链表 {
    public ListNode reverseList(ListNode head) {
    	if (head == null || head.next == null) {
			return head;
		}
    	ListNode newHead = null;
    	while (head != null && head.next != null) {
        	ListNode temp = head.next;
        	head.next = newHead;
        	newHead = head;
        	head = temp;
		}
    	return newHead;
    }
    
    
    public ListNode reverseList1(ListNode head) {
    	if (head == null || head.next == null) {
			return head;
		}
    	ListNode newHead = reverseList1(head.next);
    	head.next.next = head;
    	head.next = null;
 
    	return newHead;
    }
}
