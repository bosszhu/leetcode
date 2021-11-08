package 队列;
/*
 * 链接：https://leetcode-cn.com/problems/design-circular-deque

641. 设计循环双端队列
设计实现双端队列。
你的实现需要支持以下操作：

MyCircularDeque(k)：构造函数,双端队列的大小为k。
insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
isEmpty()：检查双端队列是否为空。
isFull()：检查双端队列是否满了。
 */


public class _641_设计循环双端队列 {
	
	public class MyCircularDeque {
		int[] elements;
		int front;//当前队列中队头位置
		int size;//当前存储内容的size大小
		int capacity;//当前的length,容量
		
	    /** Initialize your data structure here. Set the size of the deque to be k. */
	    public MyCircularDeque(int k) {
	    	elements = new int[k];
	    	capacity = k;
	    	size = 0;
	    	front = 0;
	    }
	    
	    /** Adds an item at the front of Deque. Return true if the operation is successful. */
	    public boolean insertFront(int value) {
	    	if (size == capacity) {//当size和容量都为0,或者size满了都不能insert元素
				return false;
			}
	        /*
	        对头插入元素,就是当前的front前插入一个元素(front - 1).首先需要判断下标是否小于0,小于0需要加上容量capcity.
	        然后需要改变front的位置,指向新的index,size++
	        */
	    	int index = index(-1);//
	    	elements[index] = value;
	    	front = index;
	    	size++;
	    	return true;
	    }
	    
	    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
	    public boolean insertLast(int value) {
	    	if (size == capacity) {//当size和容量都为0,或者size满了都不能insert元素
				return false;
			}
	        /*
	        队尾插入元素,目前队列的最大值为size-1,再往后移一位就是size.下标应该是(size+front)取模.
	        此时不需要改变front的位置size++
	        */
	    	elements[index(size)] = value;
	    	size++;
	    	return true;
	    }
	    
	    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
	    public boolean deleteFront() {
	    	if (size == 0) {//size为0不能删除
				return false;
			}
	        /*
	        队头删除元素,删除的位置应该是(front+1),.
	        此时front移动到front+1取模,size++
	        */
	    	front = index(1);
	    	size--;
	    	return true;
	    }
	    
	    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
	    public boolean deleteLast() {
	    	if (size == 0) {//size为0不能删除
				return false;
			}
	        /*
	        队尾删除元素,删除的位置应该是(size-1).整数不用进行任何操作.
	        此时front无变化,size--
	        */
	    	size--;
	    	return true;
	    }
	    
	    /** Get the front item from the deque. */
	    public int getFront() {
	    	if (size == 0) {
				return -1;
			}
	        /*
	        队头的元素,就是相对front的位置的元素
	        */
	    	return elements[front];
	    }
	    
	    /** Get the last item from the deque. */
	    public int getRear() {
	    	if (size == 0) {
				return -1;
			}
	        /*
	        队尾的元素,位置就是(front+size-1),但是仍然要取模.
	        */
	    	int index = index(size - 1);
	    	return elements[index];
	    }
	    
	    /** Checks whether the circular deque is empty or not. */
	    public boolean isEmpty() {
	    	return size == 0;
	    }
	    
	    /** Checks whether the circular deque is full or not. */
	    public boolean isFull() {
	    	return size == capacity;
	    }
	    
		private int index(int index) {
			index += front;
			if (index < 0) {
				return index + capacity;
			}
			return index %= capacity;
		}
	}

}
