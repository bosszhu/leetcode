package 剑指Offer_20220905;

import java.util.LinkedList;
import java.util.List;

public class 剑指Offer57_II和为s的连续正数序列 {
	/*
	 * 思路:
	 * 		滑动窗口:
	 * 			
	 */
    public int[][] findContinuousSequence(int target) {
    	//边界条件
    	if (target <= 2) {
			return null;
		}
    	//设置滑动窗口左右边界默认从1,2出发
    	int low = 1,high = 2,sum = 3;
    	//准备数组接收二维数组
    	List<int[]> list = new LinkedList<>();
    	//循环边界
    	while (low < high) {//左边界比右边界小时一直循环
			if (sum > target) {
				sum -= low;
				low++;
			} else if (sum < target) {
				high++;
				sum += high;
			} else {
				//保存数组
				int[] temp = new int[high - low + 1];
				int index = 0;
				for (int i = low; i < high; i++) {
					temp[index++] = i;
				}
				list.add(temp);
				//减去移除low的值
				sum -= low;
				low++;//移动low
			}
		}
    	return list.toArray(new int[list.size()][]);//对象数组转数组
    }
}
