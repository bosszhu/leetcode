package 剑指Offer_20220729;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class 剑指Offer50第一个只出现一次的字符 {
	Map<Character, Integer> map = new HashMap<Character, Integer>();
	Deque<Character> queue = new LinkedList<>();//想到用队列了
    public char firstUniqChar(String s) {
    	for (int i = 0; i < s.length(); i++) {
    		Character charC = new Character(s.charAt(i)); 
    		if (map.containsKey(charC)) {
				map.put(charC, -1);//包含存异常值
				while (!queue.isEmpty() && map.get(queue.peek()) == -1) {
					//当前队列不为空,且队列头部的字符出现的次数为-1时(即代表多次出现)清队列头部
					queue.poll();
				}
			} else {
				map.put(charC, i);//不包含进队
				queue.offer(charC);
			}
		}
    	return queue.isEmpty() ? ' ' : queue.peek();//如果是空返回' '
    }
}
