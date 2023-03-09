package 剑指Offer_20220908;

import java.util.Deque;
import java.util.LinkedList;

public class 剑指Offer59_I滑动窗口的最大值 {
	/*
	 * 思路:滑动窗口最大值
	 * 		单调队列:什么是单调队列:就是队列内存在的值是按照一定规则递增或递减的队列(单调函数)
	 * 		单调性:函数的递增性或者递减性.
	 * 		定义两个函数用来自定义单调队列:第一个push.第二个pop
	 * 		先循环添加到k.然后从k开始直到数组结束.每次移动时先调pop再调用push最后获取单调队列的队首元素
	 * 
	 */
	Deque<Integer> maxQueue = new LinkedList<>();
    public int[] maxSlidingWindow(int[] nums, int k) {
    	if (nums.length == 0 || k == 0) {
			return new int[0];
		}
    	int[] res = new int[nums.length - k + 1];//以窗口是1,数组长度是2为例.最大值数组有两个值
    	int index = 0;
    	for (int i = 0; i < k; i++) {//先将窗口值加入单调队列
			push(nums[i]);
		}
    	//添加第一个元素(单调队列的队首)
    	res[index++] = maxQueue.peekFirst();
    	for (int i = k; i < nums.length; i++) {
			pop(nums[i-k]);
			push(nums[i]);
			res[index++] = maxQueue.peekFirst();
		}
    	return res;
    }
    /*
     * 单调队列添加新值时,如果原先队尾比添加小的全部移除
     */
    public void push(int value) {
		while (!maxQueue.isEmpty() && value > maxQueue.peekLast()) {
			maxQueue.pollLast();
		}
		//队尾添加新值
		maxQueue.offerLast(value);
	}
    /*
     * 单调队列移除元素时,判断当前队首是否是需要移除的元素如果是才移除
     */
    public void pop(int value) {
		if (!maxQueue.isEmpty() && value == maxQueue.peekFirst()) {
			maxQueue.pollFirst();
		}
	}
    
}
