package 剑指Offer_20220806;

public class 剑指Offer18删除链表的节点 {
//	剑指 Offer 18. 删除链表的节点
    public ListNode deleteNode(ListNode head, int val) {
    	if (head.val == val) {
			return head.next;
		}
    	ListNode temp = head;
    	while (temp != null) {
    		if (temp.next.val == val) {
				temp.next = temp.next.next;
				break;
			}
			temp = temp.next;
		}
    	return head;
    }
    
//    剑指 Offer 22. 链表中倒数第k个节点
    //思路:先给定0-k的窗口,然后从k遍历到结束就剩下k到结束的位置了
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
    
    
//    剑指 Offer 25. 合并两个排序的链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
    	ListNode newNode = null;
    	while (l1 != null && l2 != null) {//一个不为null退出循环
			if (l1.val < l2.val) {
				newNode = l1;
				l1 = l1.next;
			} else {
				newNode = l2;
				l2 = l2.next;
			}
			newNode =  newNode.next;
		}
    	newNode.next = l1 == null ? l2:l1;
    	return newNode;
    }
}
