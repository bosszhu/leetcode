package 栈;

import java.util.HashMap;
import java.util.Stack;

/*
 *	https://leetcode-cn.com/problems/valid-parentheses/
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：1.左括号必须用相同类型的右括号闭合。 2. 左括号必须以正确的顺序闭合。
 */
public class _20_有效的括号 {
	static  HashMap<Character, Character> map = new HashMap<>();
	static {
		map.put('{', '}');
		map.put('[', ']');
		map.put('(', ')');
	}
    public boolean isValid(String s) {
    	//栈结构解答此题
    	Stack<Character> stack = new Stack<>();
    	//获取字符串的长度进行遍历
    	int len = s.length();
    	for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				stack.push(c);
			} else {
				//内部还有字符
				if (stack.isEmpty()) return false;
				char left = stack.pop();
				if (c != map.get(left)) return false;//c不等于左侧拿到的key对应的value
			}
		}
    	return stack.isEmpty();//如果全部遍历完全,栈为空代表是有效括号
    }
    
    public boolean isValid1(String s) {
    	//栈结构解答此题
    	Stack<Character> stack = new Stack<>();
    	//获取字符串的长度进行遍历
    	int len = s.length();
    	for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c == '{' || c == '[' || c == '(') {
				stack.push(c);
			} else {
				//内部还有字符
				if (stack.isEmpty()) return false;
				char left = stack.pop();
				if (left == '{' && c != '}') return false;
				if (left == '[' && c != ']') return false;
				if (left == '(' && c != ')') return false;
			}
		}
    	return stack.isEmpty();//如果全部遍历完全,栈为空代表是有效括号
    }
    
    public boolean isValid2(String s) {
    	while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
			s = s.replace("{}", "");
			s = s.replace("[]", "");
			s = s.replace("()", "");
		}
    	return s.isEmpty();
    }
}
