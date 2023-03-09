package 剑指Offer_20220805;

import java.util.HashMap;
import java.util.Map;

public class 剑指Offer48最长不含重复字符的子字符串 {
	 public int lengthOfLongestSubstring(String s) {
		 if (s.length() == 0 || s.equals("")) {
			return 0;
		}
		 //使用HashMap，记录前面是否出现过重复的字符
		 Map<Character, Integer> dic = new HashMap<>();
		 // 使用dp数组，记录以该字符为结尾的前面
		 int[] dp = new int[s.length()];
		 dp[0] = 1;
		 int maxLength = 1;//前面已经去掉0和空的情况能进来起始值就是1
         dic.put(s.charAt(0), 0);//因为从1遍历所以要取第0个元素放入数组
		 for (int i = 1; i < s.length(); i++) {
			//获取索引(此api没有值会返回默认值-1)
			int charIndex = dic.getOrDefault(s.charAt(i), -1);
			 //更新哈希表
			dic.put(s.charAt(i), i);
			if (dp[i - 1] < i - charIndex) {
			    dp[i] = dp[i - 1] + 1;
			} else {
				dp[i] =  i - charIndex;
			}
			maxLength = Math.max(maxLength, dp[i]);
		}
		 return maxLength;
	 }
}
