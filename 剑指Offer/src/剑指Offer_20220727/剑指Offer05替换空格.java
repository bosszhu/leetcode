package 剑指Offer_20220727;

import java.util.HashMap;

public class 剑指Offer05替换空格 {
	class Solution {
	    public String replaceSpace(String s) {
	    	int length = s.length();
	    	char[]arr = new char[length *3];//最多所有替换也就*3,相当于数组最大长度,但是可能不会完全用上
	    	int size = 0;//默认为0.随着遍历逐渐增加
	    	for (int i = 0; i < length; i++) {
				char c = s.charAt(i);
				if (c == ' ') {
					arr[size++] = '%';
					arr[size++] = '2';
					arr[size++] = '0';
				} else {
					arr[size++] = c;
				}
			}
	    	String newString = new String(arr, 0, size);
	    	return newString;
	    }
	}
}
