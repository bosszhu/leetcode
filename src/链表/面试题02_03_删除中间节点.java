package 链表;

public class 面试题02_03_删除中间节点 {
    public void deleteNode(ListNode node) {
    	node.val = node.next.val;
        node.next = node.next.next;
    }
}
