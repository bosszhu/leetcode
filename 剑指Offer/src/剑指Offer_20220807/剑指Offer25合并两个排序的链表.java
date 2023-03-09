package 剑指Offer_20220807;

public class 剑指Offer25合并两个排序的链表 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preNewNode = new ListNode(-1),cur = preNewNode;
    	while (l1 != null && l2 != null) {//只要一个为null退出循环
			if (l1.val < l2.val) {
				cur.next = l1;//前置节点的下一个节点为l1当前节点
				l1 = l1.next;
			} else {
				cur.next = l2;//前置节点的下一个节点为l2当前节点
				l2 = l2.next;
			}
			cur = cur.next;//前置节点移动
		}
    	cur.next  = (l1 != null) ? l1 : l2;
    	return preNewNode.next;
    }
}
