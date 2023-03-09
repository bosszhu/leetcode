package 剑指Offer_20220905;

import java.util.ArrayList;

public class 剑指Offer62圆圈中最后剩下的数字 {
	/*
	 * 转换成链表.但是链接查询时间较长,转换成数组查下标
	 */
    public int lastRemaining(int n, int m) {
    	//创建数组从0到n
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	for (int i = 0; i < n; i++) {
			res.add(i);
		}
    	//创建循环.以及初始下标
    	int index = 0;
    	while (res.size() > 1) {
			index = (index + m - 1) % res.size();//举例从0(数组长度每次减少1,所以需要总长度-1)%当前数组长度
			res.remove(index);//数组长度减少
		}
    	return res.get(0);
    }
}
