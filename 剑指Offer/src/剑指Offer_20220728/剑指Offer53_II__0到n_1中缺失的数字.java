package 剑指Offer_20220728;

public class 剑指Offer53_II__0到n_1中缺失的数字 {
    public int missingNumber(int[] nums) {
    	int left = 0,right = nums.length - 1;
    	while (left <= right) {
			int mid = (left + right)/2;
			if (mid == nums[mid]) {
				left = mid + 1;//下标
			} else {
				right = mid - 1;
			}
		}
    	return left;//为什么返回left.枚举以及子数组[left right]时回归到left 为mid+1此时就是left输出的值
    }
}
