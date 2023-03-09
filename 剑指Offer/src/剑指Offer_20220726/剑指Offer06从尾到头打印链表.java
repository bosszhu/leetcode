package 剑指Offer_20220726;
import java.util.Stack;

import sun.launcher.resources.launcher;
import 剑指Offer_20220726.剑指Offer06从尾到头打印链表.ListNode;

public class 剑指Offer06从尾到头打印链表 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	class Solution {
	    public int[] reversePrint(ListNode head) {
	    	Stack<Integer> stack = new Stack<Integer>();
	    	ListNode temp = head;
	    	while (temp !=null) {
				stack.push(temp.val);
				temp = temp.next;
			}
	    	int size = stack.size();
	    	int []result = new int[size];
	    	for (int i = 0; i < size; i++) {
				result[i] = stack.pop();
			}
	    	return result;
	    }
	}
}
