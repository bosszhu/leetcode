package 剑指Offer_20220902;

public class 剑指Offer15二进制中1的个数 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
    	int res = 0;
    	while (n != 0) {
			res += n & 1;//判断最后一位&1为1计数+1
			//右移n(无符号右移(需要考虑负数的符号))
			n >>>= 1;
		}
        return res;
    }
}
