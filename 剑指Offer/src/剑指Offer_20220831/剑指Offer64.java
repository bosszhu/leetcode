package 剑指Offer_20220831;

public class 剑指Offer64 {
	/*
	 * 短路效应:
	 * 		if(A && B)当A是falseB不会执行
	 * 		if(A || B)当A是trueB不会执行 
	 */
    public int sumNums(int n) {
    	boolean res = (n > 1) && (n += sumNums(n-1)) > 0;
    	return n;
    }
}
