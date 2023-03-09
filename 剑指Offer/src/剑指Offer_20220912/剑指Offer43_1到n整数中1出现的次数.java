package 剑指Offer_20220912;

public class 剑指Offer43_1到n整数中1出现的次数 {
    public int countDigitOne(int n) {
    	long base = 1;//代表从个位数开始计算
    	int res = 0;//代表出现1的次数初始为0
    	while (base <= n) {
    		//切割为两个部分
			int b = (int) (n % base);//当前位后面的数字
			int a = (int) (n / base);
			int cur = a % 10;//当前位
			a /= 10;//当前位前面的数字
			if (cur > 1) {
				res += ((a + 1) * base);
			} else if (cur < 1) {
				res += (a * base);
			} else {
				res += (a * base + b + 1);
			}
			//base需要*10
			base *= 10;
		}
    	return res;
    }
}
