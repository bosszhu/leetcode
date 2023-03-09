package 剑指Offer_20220906;

import java.util.Stack;

public class 剑指Offer31栈的压入弹出序列 {
	/*
	 * 思路:
	 * 		辅助栈.
	 * 		原理:循环添加压入序列入栈,每次检查当前栈顶元素是否是弹出序列对应下标元素,
	 * 		如果是弹出,并且弹出序列index++.最后判断当前栈是否为空,如果还有值代表没有完全弹出
	 */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
    	Stack<Integer> stack = new Stack<>();
    	int index = 0;//弹出栈中需要判断的下标元素
    	for (int i = 0; i < pushed.length; i++) {
			stack.push(pushed[i]);
    		while (!stack.isEmpty() && stack.peek() == popped[index]) {
				stack.pop();
				index++;
			}
		}
    	return stack.isEmpty();
    }
    
    public boolean validateStackSequences1(int[] pushed, int[] popped) {
    	//辅助栈
    	Stack<Integer> stack = new Stack<>();
    	//将pushed数组内的元素入栈,然后循环判断
    	int index = 0;
    	for (int i : pushed) {
			stack.push(i);//入栈
			//循环判断当前的栈顶元素是否是弹出栈元素
			while (!stack.isEmpty() && stack.peek() == popped[index]) {
				stack.pop();
				index++;
			}
		}
    	return stack.isEmpty();
    }
}
