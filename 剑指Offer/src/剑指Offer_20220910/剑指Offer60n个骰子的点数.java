package 剑指Offer_20220910;

public class 剑指Offer60n个骰子的点数 {
	
    public double[] dicesProbability(int n) {
    	//准备目标容器
    	double[] res = new double[5*n+1];//所有点数情况
    	//确定dp数组(i代表多少个骰子,j代表多少种点数情况)
    	int[][] dp = new int[n+1][6*n+1];//dp数组长度需要比实际值+1
    	//动态规划
    	dp[0][0] = 1;//初始值
    	for (int i = 1; i <= n; i++) {//点数从1开始到n结束
			for (int j = i; j <= i * 6; j++) {//从j开始到最大值结束
				for (int lastSum = 1; lastSum <= 6 && j - lastSum >= 0; lastSum ++) {
					dp[i][j] += dp[i-1][j - lastSum];
				}
			}
		}
    	//开始计算概率
    	double all = Math.pow(6, n);
    	int countSum = n;//点数
    	for (int i = 0; i < res.length; i++) {
			res[i] = dp[n][countSum++]/all;
		}
    	return res;
    }
}
