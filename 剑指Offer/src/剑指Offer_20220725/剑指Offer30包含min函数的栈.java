package 剑指Offer_20220725;

import java.util.Deque;
import java.util.LinkedList;

public class 剑指Offer30包含min函数的栈 {
	class MinStack {
		Deque<Integer> normalStack;
		Deque<Integer> minStack;
	    /** initialize your data structure here. */
	    public MinStack() {
	    	normalStack = new LinkedList<Integer>();
	    	minStack = new LinkedList<Integer>();
	    	minStack.push(Integer.MIN_VALUE);//先加入最小值
	    }
	    
	    public void push(int x) {
	    	normalStack.push(x);
	    	minStack.push(Math.min(minStack.peek(), x));
	    }
	    
	    public void pop() {
	    	normalStack.pop();
	    	minStack.pop();
	    }
	    
	    public int top() {
	    	return normalStack.peek();
	    }
	    
	    public int min() {
	    	return minStack.peek();
	    }
	}

}
