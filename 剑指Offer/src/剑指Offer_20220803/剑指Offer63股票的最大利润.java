package 剑指Offer_20220803;

public class 剑指Offer63股票的最大利润 {
//	假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
//	  public int maxProfit(int[] prices) {
//	    	int cost = Integer.MAX_VALUE,pro = 0;
//	    	for (int i = 0; i < prices.length; i++) {
//				int price = prices[i];
//				cost = Math.min(cost, price);
//				pro  = Math.max(pro, price - cost);
//			}
//	    	return pro;
//	 }
	
	
//    public int maxProfit(int[] prices) {
//    	int cost = Integer.MAX_VALUE,pro = 0;
//    	for (int i = 0; i < prices.length; i++) {
//			int price = prices[i];
//			cost = Math.min(cost, price);
//			pro  = Math.max(pro, price - cost);
//		}
//    	return pro;
//    }
    
	
	//转换方程 pro[i] = max(pro[i-1],
    public int maxProfit(int[] prices) {
    	int cost = Integer.MAX_VALUE,pro = 0;//成本最大,利润是0
    	for (int i = 0; i < prices.length; i++) {
			cost = Math.min(cost, prices[i]);//成本取最小
			pro = Math.max(pro, prices[i]-cost);//利润取最大
		}
    	return pro;
    }
}
