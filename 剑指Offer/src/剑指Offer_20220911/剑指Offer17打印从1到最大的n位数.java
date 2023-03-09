package 剑指Offer_20220911;

public class 剑指Offer17打印从1到最大的n位数 {
    public int[] printNumbers(int n) {
    	if (n <= 0) {
			return new int[0];
		}
    	int max = (int) (Math.pow(10, n) - 1),index = 0;
    	int[] res = new int[max];
    	for (int i = 1; i <= max; i++) {
			res[index++] = i;
		}
    	return res;
    }
}
