package 剑指Offer_20220808;

public class 剑指Offer21调整数组顺序使奇数位于偶数前面 {
//	输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
    public int[] exchange(int[] nums) {
    	int firstIndex = 0,lastIndex = nums.length - 1,temp;
    	while (firstIndex < lastIndex) {
			if ((nums[firstIndex] % 2 == 1)) {
				firstIndex++;
				continue;
			} 
			if (nums[lastIndex] % 2 == 0) {
				lastIndex--;
				continue;
			}
			//需要交换
			temp = nums[firstIndex];
			nums[firstIndex] = nums[lastIndex];
			nums[lastIndex] = temp;
		}
    	return nums;
    }
}
