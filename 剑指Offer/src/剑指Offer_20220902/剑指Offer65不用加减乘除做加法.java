package 剑指Offer_20220902;

public class 剑指Offer65不用加减乘除做加法 {
    public int add(int a, int b) {
    	while (b != 0) {
    		//先算进位(与运算左移一位)
			int c = (a & b) << 1;
			//再算非进位(异或运算)
			a ^= b;
			//将进位c赋值给b.再次运算进位和非进位.
			b = c;
		}
    	return a;
    }
}
