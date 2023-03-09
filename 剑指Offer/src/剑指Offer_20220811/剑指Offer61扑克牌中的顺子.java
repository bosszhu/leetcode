package 剑指Offer_20220811;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class 剑指Offer61扑克牌中的顺子 {
	/*
	 * 思路:
	 * 总结:
	 * 	1. 当前排中不能出现重复的数(采用集合存储判断重复)
	 *	2. 当前牌中最大牌减去最小牌<5,不管是否存在大小王都是如此
	 *	3. 遇到大小王直接退出循环
	 * 
	 */
	Set<Integer> set = new HashSet<Integer>();
    public boolean isStraight(int[] nums) {
    	for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				continue;
			}
			if (set.contains(nums[i])) {
				return false;
			}
			set.add(nums[i]);
		}
        return Collections.max(set) - Collections.min(set) < 5;
    }
}
