package 链表;
/*
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * 	21. 合并两个有序链表
	将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
	
	
	思路:迭代,依次比较两个链表内的元素值,哪个小就作为新节点的值,往后一位.
	重复元素去除掉
	当有一个为null时,另一个直接加到后面
	1. 递归解法
	2. 循环解法
	
	注意递归思想的:不要把东西从复杂的开始想,从简单的开始想
	比如,只剩下l1了,怎么递归.
	只剩下l2,怎么递归.
	l1中只剩下最后一个元素l2有值怎么递归?
	
	注意前置节点返回值是next,cur的next为新的值.
 */
public class _21_合并两个有序链表 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode preNewNode = new ListNode(-1),cur = preNewNode;
    	while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				cur.next = new ListNode(l1.val);
				l1 = l1.next;
			} else {
				cur.next = new ListNode(l2.val);
				l2 = l2.next;
			}
			cur = cur.next;
		}
    	cur.next = l1 == null ? l2 :l1;
    	//注意这种问题后续不用
    	return preNewNode.next;
    }
    
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
    	if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		} else if (l1.val <= l2.val) {
			return mergeTwoLists1(l1.next, l2);
		} else {
			return mergeTwoLists1(l1, l2.next);
		}
    }
}
