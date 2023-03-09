package 剑指Offer_20220912;

public class 剑指Offer14_II剪绳子II {
    public int cuttingRope(int n) {
    	if (n < 4) {
			return n - 1;
		}
    	long res = 1;
    	int overlength = n;
    	while (overlength > 4) {
			res = res * 3 % 1000000007;
			overlength -= 3;
		}
    	return (int)(res * overlength % 1000000007);
    }
}
