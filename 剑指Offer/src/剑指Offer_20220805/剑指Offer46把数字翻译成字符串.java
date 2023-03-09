package 剑指Offer_20220805;

public class 剑指Offer46把数字翻译成字符串 {
//	给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	//返回的是有多少种编译方式
	
    public int translateNum(int num) {
    	String nString = String.valueOf(num);
    	int[] dp = new int[nString.length() + 1];
    	dp[0] = 1;
    	dp[1] = 1;
    	for (int i = 2; i < dp.length; i++) {
			String tempString = nString.substring(i-2, i);
			if (tempString.compareTo("10") >= 0 && tempString.compareTo("25") <= 0) {
				dp[i] = dp[i-1] + dp[i-2];
			} else {
				dp[i] = dp[i-1];
			}
		}
    	
    	return dp[nString.length()];
    }
}
