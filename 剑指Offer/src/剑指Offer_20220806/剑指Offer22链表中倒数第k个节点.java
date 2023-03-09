package 剑指Offer_20220806;

public class 剑指Offer22链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
    	ListNode fast = head;
    	ListNode slow = head;
    	for (int i = 0; i < k; i++) {
			fast = fast.next;
		}
    	while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
    	return slow;
    }
}
