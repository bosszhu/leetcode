package 剑指Offer_20220806;

public class _141环形链表 {
	//思路:快慢指针
    public boolean hasCycle(ListNode head) {
    	if (head == null || head.next == null) {
			return false;
		}
    	ListNode fast = head.next;
    	ListNode slow = head;
    	while (slow != fast) {
			if (fast == null || fast.next == null) {//如果快指指针在遍历的时候等于null
				return false;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
    	return true;
    }
}
