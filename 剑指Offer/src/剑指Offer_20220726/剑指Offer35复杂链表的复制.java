package 剑指Offer_20220726;

import java.util.HashMap;
import java.util.Map;

public class 剑指Offer35复杂链表的复制 {
	class Node {
	    int val;
	    Node next;
	    Node random;

	    public Node(int val) {
	        this.val = val;
	        this.next = null;
	        this.random = null;
	    }
	}
	class Solution {
		Map<Node, Node>map = new HashMap<>(); 
	    public Node copyRandomList(Node head) {
	    	if (head == null) {
				return head;
			}
	    	if (!map.containsKey(head)) {
				Node newHead = new Node(head.val);
				map.put(head, newHead);
				newHead.next = copyRandomList(head.next);
				newHead.random = copyRandomList(head.random);
			}
	    	return map.get(head);
	    }
	}
}
