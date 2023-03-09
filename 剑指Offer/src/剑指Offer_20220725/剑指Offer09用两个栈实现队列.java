package 剑指Offer_20220725;

import java.util.Stack;

public class 剑指Offer09用两个栈实现队列 {
	class CQueue {
		
		Stack<Integer> inStack;
		Stack<Integer> outStack;
	    public CQueue() {
	    	inStack = new Stack<Integer>();
	    	outStack = new Stack<Integer>();
	    }
	    
	    public void appendTail(int value) {
	    	inStack.push(value);
	    }
	    
	    public int deleteHead() {
	    	checkOutStack();
	    	if (outStack.isEmpty()) {
				return -1;
			}
	    	return outStack.pop();
	    }
	    
	    public void checkOutStack() {
			if (outStack.isEmpty()) {
				while (!inStack.isEmpty()) {
					outStack.push(inStack.pop());
				}
			}
		}
	}
}
