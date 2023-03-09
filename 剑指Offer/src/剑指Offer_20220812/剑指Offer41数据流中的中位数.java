package 剑指Offer_20220812;

import java.util.ArrayList;

public class 剑指Offer41数据流中的中位数 {
	class MedianFinder {
		/*
		 * 思路:准备两个数组,一个用来保存全局大顶堆(动态size),一个用来保存全局小顶堆(动态size)
		 * 		1. 添加时需要判断当前添加是奇数还是偶数(当大顶堆size和小顶堆相同),此时需要向小顶堆中添加新元素,否则向大顶堆中添加
		 * 			1. 怎么向大(小)顶堆中添加新元素.先将其加入另外一个堆中,然后剔除其堆顶元素加入到需要添加的堆中.
		 * 		2. 怎么求中位数,如果大小顶堆size相等,代表是偶数需要取(大顶堆顶堆+小顶堆堆顶元素)/2,如果是奇数直接取小顶堆堆顶元素
		 */
	    /** initialize your data structure here. */
		ArrayList<Integer> bigHeap;
		ArrayList<Integer> smallHeap;
	    public MedianFinder() {
	    	this.bigHeap = new ArrayList<Integer>();
	    	this.smallHeap = new ArrayList<Integer>();
	    }
	    
	    public void addNum(int num) {
	    	if (bigHeap.size() == smallHeap.size()) {
				//向小顶堆添加:先将其加入大顶堆,再取堆顶值,并且移除大顶堆堆顶元素调整大顶堆,添加到小顶堆,调整小顶堆.
	    		bigHeap.add(num);
	    		//重新建堆
	    		for (int i = (bigHeap.size() - 1 - 1)/2; i >= 0; i--) {
	    			adjustHeap(bigHeap, i, bigHeap.size() - 1, 1);
	    		}
	    		//取堆顶,删除堆顶
	    		int temp = bigHeap.remove(0);
	    		for (int i = (bigHeap.size() - 1 - 1)/2; i >= 0; i--) {
	    			adjustHeap(bigHeap, i, bigHeap.size() - 1, 1);
	    		}
	    		smallHeap.add(temp);
	    		for (int i = (smallHeap.size() - 1 - 1)/2; i >= 0; i--) {
	    			adjustHeap(smallHeap, i, smallHeap.size() - 1, 0);
	    		}
			} else {
				//向大顶堆添加:先将其加入小顶堆,再取堆顶值,并且移除小顶堆堆顶元素调整大顶堆,调整小顶堆.
				smallHeap.add(num);
				//重新建堆
	    		for (int i = (smallHeap.size() - 1 - 1)/2; i >= 0; i--) {
	    			adjustHeap(smallHeap, i, smallHeap.size() - 1, 0);
	    		}
	    		//取堆顶,删除堆顶
	    		int temp = smallHeap.remove(0);
	    		for (int i = (smallHeap.size() - 1 - 1)/2; i >= 0; i--) {
	    			adjustHeap(smallHeap, i, smallHeap.size() - 1, 0);
	    		}
	    		bigHeap.add(temp);
	    		for (int i = (bigHeap.size() - 1 - 1)/2; i >= 0; i--) {
	    			adjustHeap(bigHeap, i, bigHeap.size() - 1, 1);
	    		}
			}
	    }
	    
	    public double findMedian() {
	    	//注意判断最开始没值无法获取顶堆的情况
	    	if (bigHeap.size() == smallHeap.size()) {
				//大小顶堆堆顶元素相加/2
	    		int midMin = smallHeap.size() > 0 ? smallHeap.get(0) : 0;
	    		int midMax = bigHeap.size() > 0 ? bigHeap.get(0) : 0;
	    		return (midMin + midMax)/(double)2;
			} else {
				//小顶堆最后一个元素
				return smallHeap.size() > 0 ? smallHeap.get(0) : 0;
			}
	    }
	    
		public void adjustHeap(ArrayList<Integer> list,int parent,int last_index,int flag) {
			int temp = parent;
			int lChild = parent * 2 + 1;
			int rChild = parent * 2 + 2;
			if (flag == 1) {//代表大顶堆
				if (lChild <= last_index && list.get(lChild) > list.get(temp)) {
					temp = lChild;
				}
				if (rChild <= last_index && list.get(rChild) > list.get(temp)) {
					temp = rChild;
				}
			} else {//代表小顶堆
				if (lChild <= last_index && list.get(lChild) < list.get(temp)) {
					temp = lChild;
				}
				if (rChild <= last_index && list.get(rChild) < list.get(temp)) {
					temp = rChild;
				}
			}
			if (temp != parent) {
				swap(list, temp, parent);
				adjustHeap(list, temp, last_index,flag);
			}
		}
		public void swap(ArrayList<Integer> list,int i,int j) {
			Integer temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);
		}

	}
}
