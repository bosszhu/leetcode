package 剑指Offer_20220727;

public class 剑指Offer58_II左旋转字符串 {
	class Solution {
	    public String reverseLeftWords(String s, int n) {
	    	return s.substring(n)+s.substring(0, n);
	    }
	}
}
