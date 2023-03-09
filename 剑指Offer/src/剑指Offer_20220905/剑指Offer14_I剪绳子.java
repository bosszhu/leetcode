package 剑指Offer_20220905;

public class 剑指Offer14_I剪绳子 {
	/*
	 * 思路:
	 * 		动态规划
	 */
    public int cuttingRope(int n) {
    	//创建初始动态规划数组长度为n+1
    	int[] dp = new int[n+1];
    	//创建数组初始值
    	dp[2] = 1;//n,m>1此时只有一种情况就是单独为2不剪绳子的长度
    	int maxMultiplyCount = 0;//最大乘积初始值为0
    	//循环遍历
    	for (int i = 2; i < dp.length; i++) {//绳子长度从2开始,一直到n循环完结束,所以是<n+1
			for (int j = 1; j < i - 1; j++) {//之前长度从1开始(为2的时候是1),到i-1结束.
				maxMultiplyCount = j * Math.max(i-j, dp[i-j]);//之前长度j*最后一段剪开dp[i-j]或者不剪开的长度(i-j)
				dp[i] = Math.max(dp[i], maxMultiplyCount);//取剪开时最大乘积和整个不减时最大乘积比较
			}
		}
    	return dp[n];
    }
}
