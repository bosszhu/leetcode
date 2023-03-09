package 剑指Offer_20220808;

public class 剑指Offer57和为s的两个数字 {
//	输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
    public int[] twoSum(int[] nums, int target) {
    	int firstIndex = 0,lastIndex = nums.length - 1,sum;
    	while (firstIndex < lastIndex) {
			sum = nums[firstIndex] + nums[lastIndex];
			if (sum > target) {
				lastIndex--;
			} else if (sum < target) {
				firstIndex++;
			} else {
				return new int[] {
					nums[firstIndex],nums[lastIndex]	
				};
			}
			
		}
    	return new int[0];
    }
}
