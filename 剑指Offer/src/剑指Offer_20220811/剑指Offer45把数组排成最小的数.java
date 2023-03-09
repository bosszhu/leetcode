package 剑指Offer_20220811;
import java.util.Arrays;


public class 剑指Offer45把数组排成最小的数 {
//	输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
    public String minNumber(int[] nums) {
    	String[] strs = new String[nums.length];//创建字符串数组长度和nums相等
    	for (int i = 0; i < strs.length; i++) {
    			strs[i] = String.valueOf(nums[i]);
    		}
			//x,y和y,x字符串进行比较按照x+y大于y+x的规则.然后进行排序比较然后排序.java自己api
			Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));//此处是排序好的数组
			StringBuilder newString = new StringBuilder();
			for (String string : strs) {
				newString.append(string);
			}
	    	return newString.toString();
    }
}
