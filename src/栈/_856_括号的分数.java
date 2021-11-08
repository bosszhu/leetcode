package 栈;

import java.util.Stack;

/*
 * 856. 括号的分数
给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
() 得 1 分。
AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
(A) 得 2 * A 分，其中 A 是平衡括号字符串。

来源：力扣（LeetCode）
https://leetcode-cn.com/problems/score-of-parentheses
 * 例子:
 * 输入： "(()(()))"
 * 输出： 6
 * 
 * 思路：(()(())) = (())+((()))=2^1+2^2=6
 * 
 * 没理解.
 */
public class _856_括号的分数 {
    public int scoreOfParentheses(String s) {
    	Stack<Character> stack = new Stack<>();
    	int len = s.length();
    	
    	boolean tag = false;
    	int sum = 0;
    	for (int i = 0; i < len; i++) {
    		char c = s.charAt(i);
    		if (c == '(') {
				stack.push(c);
			} else {
				char preC = s.charAt(i - 1);
				if (preC == '(') {//如果前一个是(进行计算,并且出栈
					sum +=  Math.pow(2, stack.size() - 1);
					stack.pop();
				} else {//如果前一个是)直接出栈
					stack.pop();
				}
			}
		}
    	return sum;
    }
}
