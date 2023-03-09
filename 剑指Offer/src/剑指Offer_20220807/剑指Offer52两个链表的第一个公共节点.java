package 剑指Offer_20220807;

public class 剑指Offer52两个链表的第一个公共节点 {
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	ListNode A = headA,B = headB;
    	while (A != B) {//不相等时
			if (A == null) {
				A = headB;//开始走B走过的路径
			} else {
				A = A.next;
			}
			
			if (B == null) {
				B = headA;//B开始走A的路径
			} else {
				B = B.next;
			}
		}
        return A;
    }
}
