package 剑指Offer_20220725;

import java.util.Deque;
import java.util.LinkedList;

public class _225用队列实现栈 {
	class MyStack {
		Deque<Integer> q1;
		Deque<Integer> q2;
	    public MyStack() {
	    	q1 = new LinkedList<Integer>();
	    	q2 = new LinkedList<Integer>();
	    }
	    
	    public void push(int x) {
	    	q2.offer(x);
	    	//将q1的所有值进队列到q2.交换
	    	while (!q1.isEmpty()) {
				q2.offer(q1.poll());
			}
	    	Deque<Integer> temp = q1;
	    	q1 = q2;
	    	q2 = temp;
	    }
	    
	    public int pop() {
	    	return q1.poll();
	    }
	    
	    public int top() {
	    	return q1.peek();
	    }
	    
	    public boolean empty() {
	    	return q1.isEmpty();
	    }
	}

}
