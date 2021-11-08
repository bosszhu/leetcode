package 队列;

import java.util.Stack;

/*
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *	232. 用栈实现队列 
 *	请你仅使用两个栈实现先入先出队列。
 *
 *
 *	思路:栈后进先出(LIFO),怎么转化为队列,先进先出(FIFO)
 *	将两个栈底放在一起,就是一个队列模型.
 */
public class _232_用栈实现队列 {
	
	
	class MyQueue {
		Stack<Integer> inStack;
		Stack<Integer> outStack;
		
	    /** Initialize your data structure here. */
	    public MyQueue() {
	    	inStack = new Stack<>();
	    	outStack = new Stack<>();
	    }
	    
	    /** Push element x to the back of queue. */
	    public void push(int x) {
	    	inStack.push(x);
	    }
	    
	    /** Removes the element from in front of queue and returns that element. */
	    public int pop() {
	    	checkOutStack();
	    	return outStack.pop();
	    }
	    
	    /** Get the front element. */
	    public int peek() {
	    	checkOutStack();
	    	return outStack.peek();
	    }
	    
	    /** Returns whether the queue is empty. */
	    public boolean empty() {
	    	return inStack.isEmpty() && outStack.isEmpty();
	    }
	    
	    private void checkOutStack() {
	    	if (outStack.isEmpty()) {
	    		while (!inStack.isEmpty()) {//当instack不为空时,就pop,然后装到outStack中
	    			outStack.push(inStack.pop());	
				}
			}
		}
	}

	
}
