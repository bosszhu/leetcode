package 剑指Offer_20220803;

public class 剑指Offer10_II青蛙跳台阶问题 {
//	一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
    public int numWays(int n) {
    	if (n == 0) {
			return 1;
		}
    	if (n <= 3) {
			return n;
		}
    	int mod = 1000000007;
    	int[]dp = new int[n+1];
    	dp[0] = 0;
    	dp[1] = 1;
    	for (int i = 2; i < dp.length; i++) {
    		dp[i] = (dp[i - 1] + dp[i-2])%mod;
		}
    	return dp[n];
    }
}
