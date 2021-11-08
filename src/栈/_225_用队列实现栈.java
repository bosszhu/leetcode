package 栈;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * 225. 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通队列的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。


思路:
取值和判空都只看主队列
push入栈,
	1. 先将元素入队列2
	2. 判断队列1不为空,将队列1中全部元素,入队列2
	3. 交换队列1,2.使队列2中满元素队列,成为主队列1.
 */
public class _225_用队列实现栈 {
	
	
	class MyStack {
		Queue<Integer> queue1;
		Queue<Integer> queue2;
		
	    /** Initialize your data structure here. */
	    public MyStack() {
	    	queue1 = new LinkedList<>();
	    	queue2 = new LinkedList<>();
	    }
	    
	    /** Push element x onto stack. */
	    public void push(int x) {
	    	//将新元素加入第二队列
	    	queue2.offer(x);
	    	while (!queue1.isEmpty()) {
	    		//将队列1中的元素全部倾倒到队列2中
				queue2.offer(queue1.poll());
			}
	    	
	    	Queue<Integer> temp = queue1;
	    	queue1 = queue2;
	    	queue2 = temp;
	    }
	    
	    /** Removes the element on top of the stack and returns that element. */
	    public int pop() {
	    	return queue1.poll();
	    }
	    
	    /** Get the top element. */
	    public int top() {
	    	return queue1.peek();
	    }
	    
	    /** Returns whether the stack is empty. */
	    public boolean empty() {
	    	return queue1.isEmpty();
	    }
	}
}
