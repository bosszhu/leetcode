package 剑指Offer_20220904;

public class 剑指Offer66构建乘积数组 {
    public int[] constructArr(int[] a) {
    	//边界情况
    	if (a == null || a.length == 0) {
			return new int[0];
		}
    	int length = a.length;
    	//创建左右两侧乘积和两个新数组用来维护左右两边的乘积
    	int[] leftDp = new int[length];
    	int[] rightDp = new int[length];
    	
    	//初始值(左右)
    	leftDp[0] = rightDp[length - 1] = 1;
    	int temp = 1;
    	//左侧(理解为动态规划:起始位置从1开始,终止位置是数组最后一个下标截止,到数组结束转换方程dp[i] = dp[i-1] * a[i])
    	for (int i = 1; i <= length - 1; i++) {
    		//举例:leftDp[1] = 1 * a[0],leftDp[2] = (1 * a[0]) * a[1],leftDp[3] = (1 * a[0] * a[1]) * a[2];正好就是左边左右数的乘积
    		leftDp[i] = leftDp[i - 1] * a[i - 1];
		}
    	//右侧(起始位置:跟左边类似,左边其实位置是0+1,右边起始位置应该就是length - 1 - 1),终止位置是数组的第一个位置截止
    	for (int i = length - 1 - 1; i >= 0; i--) {
    		//举例:已数组长度为4为例,rightDp[3]初始值是1,rightDp[2] = rightDp[3] * a[3],rightDp[1] = 1 * a[2] * a[3],正好就是右边所有值的乘积
			rightDp[i] =  rightDp[i + 1] * a[i + 1];
		}
    	//总乘积
    	int[] ans = new int[length];
    	for (int i = 0; i < length; i++) {
			ans[i] = leftDp[i] * rightDp[i];
		}
    	return ans;
    }
}
