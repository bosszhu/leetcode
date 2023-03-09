package 剑指Offer_20220728;

import java.util.HashMap;
import java.util.Map;

public class 剑指Offer03数组中重复的数字 {
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	 public int findRepeatNumber(int[] nums) {
		 for (int i = 0; i < nums.length; i++) {
			 int showTime = 0;
			 int key = nums[i];
			 if (map.containsKey(key)) {
				showTime = map.get(key);
				showTime++;
				return key;
			 } else {
				showTime = 1;
			 }
			map.put(key, showTime);
		}
		 return -1;//没有重复数字
	 }
}
