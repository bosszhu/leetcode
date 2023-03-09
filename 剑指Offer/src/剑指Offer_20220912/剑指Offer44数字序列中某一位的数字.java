package 剑指Offer_20220912;

public class 剑指Offer44数字序列中某一位的数字 {
	/*
	 * 思路:
	 * 	规律:
	 * 	1. 根据n从1-9依次相减得到n所在指定的位数的n
	 * 	2. 根据剩余的n,求出此时的num为start+(n-1)/digits
	 * 	3. 根据剩余的n,求出此时的num的下标index:(n-1)%digits.
	 * 	4. 根据num和index求出此时的具体位置的数字
	 */
    public int findNthDigit(int n) {
    	if (n == 0) {//边界值
			return 0;
		}
    	//起始值
    	long start = 1;int digits = 1;
    	while (n > start * digits * 9) {//还需要继续相减
			n -= start * digits * 9;
			start *= 10;//start*10
			digits += 1;//位数+1
		}
    	//确定当前的num
    	long num = start + (n - 1) / digits;
    	int index = (n - 1) % digits;
    	//将num转为字符串
    	return Long.toString(num).charAt(index) - '0';
    }
}
