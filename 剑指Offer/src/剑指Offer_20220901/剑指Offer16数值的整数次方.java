package 剑指Offer_20220901;

public class 剑指Offer16数值的整数次方 {
	/*
	 * 快速幂:
	 * 		
	 */
    public double myPow(double x, int n) {
        if (x == 0) {
			return 0;
		}
        //转化幂为double类型
        long power = n;
        //初始化结果
        double res = 1.0;
        //判断幂是负数的情况
        if (power < 0) {
			x = 1/x;
			power = -power;
		}
        while (power > 0) {
			if ((power % 2) == 1) {//奇数
				res *= x;
			}
			x *= x;//x每次增大平方
			power /= 2;//幂每次减少至一半,直到为0为止
		}
        return res;
    }
    
    
    public double myPow1(double x, int n) {
        //当x=0时,直接返回0
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b % 2) == 1) res *= x;
            x *= x;
            b /= 2;
        }
        return res;
    }
}
