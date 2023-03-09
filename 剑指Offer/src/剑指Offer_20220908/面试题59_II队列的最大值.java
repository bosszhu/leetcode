package 剑指Offer_20220908;

import java.util.LinkedList;

public class 面试题59_II队列的最大值 {
	class MaxQueue {
		/*
		 * 单调队列
		 */
		LinkedList<Integer> normalQueue;
		LinkedList<Integer> maxQueue;
	    public MaxQueue() {
	    	normalQueue = new LinkedList<>();
	    	maxQueue = new LinkedList<>();
	    }
	    
	    public int max_value() {
	    	if (!maxQueue.isEmpty()) {
		    	return maxQueue.peekFirst();
			}
	    	return -1;
	    }
	    
	    public void push_back(int value) {
	    	normalQueue.offer(value);//正常队列直接添加
	    	while (!maxQueue.isEmpty() && maxQueue.peekLast() < value) {
				maxQueue.pollLast();
			}
	    	maxQueue.offerLast(value);
	    }
	    
	    public int pop_front() {//移除队首
	    	if (!normalQueue.isEmpty()) {
	    		//判断当前正常队列的队首是否是最大值队列的队首如果是移除
	    		if (!maxQueue.isEmpty() && normalQueue.peekFirst().equals(maxQueue.peekFirst())) {
					maxQueue.pollFirst();
				}
	    		return normalQueue.pollFirst();
			}
	    	return -1;
	    }
	}
}
