package 剑指Offer_20220909;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class 剑指Offer38字符串的排列 {
	/*
	 * 思路:
	 * 		将字符串转为字符数组.
	 * 		递归,注意需要使用集合用来存防止重复交换,固定第一个元素然后交换,继续递归(固定的下标+1)知道倒数第一个元素结束递归
	 * 		注意递归调用完毕后需要复原字符串.
	 * 		
	 */
	char[] c;
	List<String> res = new LinkedList<>();
    public String[] permutation(String s) {
    	c = s.toCharArray();
    	dfs(0);
    	return res.toArray(new String[res.size()]);
    }
    public void dfs(int fixIndex) {    	
		if (fixIndex == c.length - 1) {//最后一个元素
			res.add(String.valueOf(c));
			return;
		}
		HashSet<Character> set = new HashSet<>();
		//从固定位置到结束
		for (int i = fixIndex; i < c.length; i++) {
			char ch = c[i];
			if (set.contains(ch)) {//如果包含不继续
				continue;//重复剪枝
			}
			set.add(ch);
			//交换
			swap(i, fixIndex);
			//递归(固定的index+1)
			dfs(fixIndex + 1);
			//复原
			swap(i, fixIndex);
		}
	}
    public void swap(int i,int j) {
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
	}
}
