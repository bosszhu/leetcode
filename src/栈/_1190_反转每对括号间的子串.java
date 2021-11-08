package 栈;

import java.util.Stack;
/*
 * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/comments/给出一个字符串 s（仅含有小写英文字母和括号）。

请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。

注意，您的结果中 不应 包含任何括号。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

示例:
输入：s = "(u(love)i)"
输出："iloveu"


思路:栈,注意入栈的是下标出栈的也是下标
遇到'('将下数组下标入栈.
遇到')'出栈,
	1. 出栈时需要反转遍历字符串
	2. 然后开始消除括号
(u(love)i)
第一步:
(u(evol)i)	
第二步:
(i(love)u)
第三步:
去掉所有括号
iloveu
 * 
 * 
 */
public class _1190_反转每对括号间的子串 {
	public static void main(String[] args) {
		_1190_反转每对括号间的子串 obj = new _1190_反转每对括号间的子串();
		String s = obj.reverseParentheses("(abcd)");
		System.out.print(s);
	}
	 public String reverseParentheses(String s) {
		 Stack<Integer> stack = new  Stack<>();
		 char[] arr = s.toCharArray();
		 for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (c == '(') {
				stack.push(i);
			}
			if (c == ')') {//不是else就出栈
				reverseString(arr, stack.pop()+1, i-1);//将括号一起翻转
			}
		}
		 //去除arr中的括号
		 String newS = String.valueOf(arr);
		 while (newS.contains("(") || newS.contains(")")) {
			newS = newS.replace("(", "");
			newS = newS.replace(")", "");
		}
		 return newS;
	 }
	 //翻转字符串
	 public void reverseString( char[] arr,int left,int right) {
		 while (right > left) {//当右侧更大
			 char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			right--;
			left++;
		}
	 }
//	 public String reverseParentheses(String s) {
//	        StringBuilder sb = new StringBuilder();
//	        char[] arr = s.toCharArray();
//	        Stack<Integer> stack = new Stack<>();
//	        
//	        for (int i = 0; i < arr.length; i++) {
//	            
//	            if (arr[i] == '(')
//	                stack.push(i);
//	            
//	            if (arr[i] == ')')
//	            	reverseString(arr, stack.pop() + 1, i - 1);
//	        }
//	        String newS = arr.toString();
//	        for (int i = 0; i < arr.length; i++)
//	            if (arr[i] != ')' && arr[i] != '(')
//	                sb.append(arr[i]);
//	        
//	        return sb.toString();
//	    }
//	    
//	    public void reverse(char[] arr, int left, int right) {
//	        while (right > left) {
//	            char tmp = arr[left];
//	            arr[left] = arr[right];
//	            arr[right] = tmp;
//	            right--;
//	            left++;
//	        }
//	    }
}
