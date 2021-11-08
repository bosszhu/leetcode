package 数组;

import java.util.HashMap;

/*
 * https://leetcode-cn.com/problems/two-sum/
 * 1. 两数之和
	给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
	你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
	你可以按任意顺序返回答案。
	
	思路:
	1. 暴力破解
		逐级遍历内部元素,找到和为target的返回.
	2. hash查找
		逻辑代码:
		1. 将nums内的元素和下标存到hashTable中.
		2. tagrget - hash表中的元素是否包含在hash表中且和之前的下标不一样返回.
		
 */
public class _1_两数之和 {
    public int[] twoSum1(int[] nums, int target) {
    	int[] result = new int[2];
    	HashMap<Integer, Integer> hash = new HashMap<>();
    	for (int i = 0; i < nums.length; i++) {
    		hash.put(nums[i],i);//key ,value
		}
    	for (int i = 0; i < nums.length; i++) {
			int diff = target - nums[i];
			if (hash.containsKey(diff) && i != hash.get(diff)) {
				result[0] = i;
				result[1] = hash.get(diff);
				return result;
			}
		}
    	return result;
    }
    public int[] twoSum(int[] nums, int target) {
    	int[] result = new int[2];
    	for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target && i != j) {
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}
    	return result;
    }
}
