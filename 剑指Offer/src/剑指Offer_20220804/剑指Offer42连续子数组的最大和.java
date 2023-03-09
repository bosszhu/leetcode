package 剑指Offer_20220804;

public class 剑指Offer42连续子数组的最大和 {
	
	//连续子数组和最大和
	//转换方程
	//dp[i] = max(dp[i-1] + nums[i],nums[i])
    public int maxSubArray(int[] nums) {
    	int[] dp = new int[nums.length];
    	dp[0] = nums[0];
    	int max = dp[0];
    	for (int i = 1; i < nums.length; i++) {
			dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
			max = Math.max(max, dp[i]);
		}
    	return max;
    }
}
