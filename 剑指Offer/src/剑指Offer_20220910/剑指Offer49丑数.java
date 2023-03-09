package 剑指Offer_20220910;

public class 剑指Offer49丑数 {
	/*
	 * 思路:
	 * 		准备三个下标用来存放p2,p3,p5的最小值,然后移动下标
	 */
    public int nthUglyNumber(int n) {
    	int[] dp = new int[n];
    	int p2 = 0,p3 = 0,p5 = 0;//对应的下标
    	dp[0] = 1;
    	for (int i = 1; i < dp.length; i++) {
    		int n2 = dp[p2] * 2,n3 = dp[p3] * 3,n5 = dp[p5] * 5;
    		dp[i] = Math.min(Math.min(n2, n3), n5);
    		if (dp[i] == n2) {
				p2++;
			}
    		if (dp[i] == n3) {
				p3++;
			}
    		if (dp[i] == n5) {
				p5++;
			}
		}
    	return dp[n - 1];//取下标
    }
}
