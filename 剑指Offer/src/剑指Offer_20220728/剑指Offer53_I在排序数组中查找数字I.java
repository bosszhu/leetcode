package 剑指Offer_20220728;

import java.util.HashMap;
import java.util.Map;

public class 剑指Offer53_I在排序数组中查找数字I {
//	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//    public int search(int[] nums, int target) {
//		 for (int i = 0; i < nums.length; i++) {
//			 int showTime = 0;
//			 int key = nums[i];
//			 if (map.containsKey(key)) {
//				showTime = map.get(key);
//				showTime++;
//				return key;
//			 } else {
//				showTime = 1;
//			 }
//			 map.put(key, showTime);
//		 }
//		 if (map.containsKey(target)) {
//			 return map.get(target);
//		 } else {
//			 return 0;//没有重复数字
//		 }
//    }
	
	//二分查找
    public int search(int[] nums, int target) {
    	int i = 0,j = nums.length - 1;
    	//找right值.
    	while (i<= j) {
			int mid = (i+j)/2;
			if (nums[mid] <= target) {
				i = mid+1;
			} else {
				j = mid-1;
			}
		}
    	int right = i;
    	i = 0;j = nums.length - 1;
     	//找left值.
     	while (i<= j) {
 			int mid = (i+j)/2;
 			if (nums[mid] < target) {
 				i = mid+1;
 			} else {
 				j = mid-1;
 			}
 		}
     	int left = j;
    	return right - left - 1;
    }
}
