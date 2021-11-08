package 链表;

import java.awt.geom.CubicCurve2D;
import java.util.Currency;

import javax.swing.text.html.HTML.Tag;

/*
 * https://leetcode-cn.com/problems/add-two-numbers/
 *
 * 2. 两数相加
	给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
	请你将两个数相加，并以相同形式返回一个表示和的链表。
	你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
	
	思路,
	1. 一个新链表.一个指针current,一个标记是否需要加1的进位.
	当前位是两数相加取其模,进位是两数相加取其模
	2. 当有一个链表为null时,需要继续加上另外一个链表的值
	3. 当两个链表处理完成时,还需要要进位是不是空,非空还需要继续next一位
	
	技巧,注意使用前置节点.就不需要进行节点为null判断,但是最后返回为node.next.
 */
public class _2_两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	ListNode newNode = new ListNode(-1);
    	ListNode cur = newNode; 
    	
    	
//    	ListNode newNode =  null, cur = null;
    	int nextTag = 0;
    	while (l1 != null || l2 != null) {
    		int n1 = (l1 != null) ? l1.val : 0;
    		int n2 = (l2 != null) ? l2.val : 0;
    		int sum = n1 + n2 + nextTag;
    		nextTag = sum / 10;
    		cur.next = new ListNode(sum % 10);
			cur = cur.next;
    		
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}
    	if (nextTag != 0) {
			cur.next = new ListNode(nextTag);//最后一个
		}
    	return newNode.next;
    }
}
