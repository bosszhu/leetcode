package 剑指Offer_20220803;

public class 剑指Offer10_I斐波那契数列 {
    public int fib(int n) {
    	if (n <= 1) {
            return n;
        }
    	int first = 0,second = 1,mod = 1000000007;
    	for (int i = 0; i < n - 1; i++) {
    		int sum = (first + second ) % mod;
			first = second;
			second = sum;
		}
//    	return fib(n-1) + fib(n-2);//理论解法肯定超时
    	return second;
    }
    
    //如果遇到刁难O(1)复杂度,直接根据n返回对应的值.
    
    //动态规划解法
    public int fib1(int n) {
    	if (n <= 1) {
            return n;
        }
    	int mod = 1000000007;
    	int []dp = new int[n+1];
    	dp[0] = 0;
    	dp[1] = 1;
    	for (int i = 2; i < dp.length; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%mod;
		}
    	return dp[n];
    }
    
    
    
}
