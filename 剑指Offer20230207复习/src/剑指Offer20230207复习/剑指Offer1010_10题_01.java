package 剑指Offer20230207复习;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class 剑指Offer1010_10题_01 {
	/*
	 * 第一题:
	 * 剑指 Offer 09. 用两个栈实现队列
	 * 思路:
	 * 		1. 准备两个栈全部变量
	 * 		2. 初始时创建对象.
	 * 		3. 创建函数用来判断是否栈中,为空.当outStack为空,循环将instack内不为空的全部加入到outStack中.
	 * 		4. 添加值,直接向inStack添加值,删除值先调用当前outStack是否为空函数,然后判断当前outStack是否为空,为空返回-1,不为空返回outStack内的pop值
	 *			来源：力扣（LeetCode）
				链接：https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cgyt5wt
	 */
	class CQueue {
		Stack<Integer> inStack;
		Stack<Integer> outStack;

	    public CQueue() {
	    	inStack = new Stack<Integer>();
	    	outStack = new Stack<Integer>();
	    }
	    
	    public void appendTail(int value) {
	    	inStack.push(value);
	    }
	    
	    public int deleteHead() {
	    	checkoutOutStackisEmpty();
	    	if (outStack.isEmpty()) {
				return -1;
			}
	    	return outStack.pop();
	    }
	    //检查outStack是否为空
	    public void checkoutOutStackisEmpty() {
	    	//如果outStack为空将inStack内所有元素全部(即循环)加入outStack
			if (this.outStack.isEmpty()) {
				while (!inStack.isEmpty()) {
					outStack.push(inStack.pop());
				}
			}
		}
	    
	}
	
	
	/*
	 * 第二题:
	 * 剑指 Offer 30. 包含min函数的栈
	 * 思路:
	 * 		1. 单调栈.在栈添加元素时,新增一个数据结构用来添加最小值.保证其单调性
	 * 		来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cgyt5wt
	 */
	class MinStack {
		Stack<Integer> normalStack;
		Stack<Integer> minStack;
	    public MinStack() {
	    	normalStack = new Stack<Integer>();
	    	minStack = new Stack<Integer>();
	    	minStack.push(Integer.MAX_VALUE);//放入默认最大值
	    }
	    
	    public void push(int x) {
	    	normalStack.push(x);
	    	minStack.push(Math.min(x, minStack.peek()));
	    }
	    
	    public void pop() {
	    	normalStack.pop();
	    	minStack.pop();
	    }
	    
	    public int top() {
	    	return normalStack.peek();
	    }
	    
	    public int min() {
	    	return minStack.peek();
	    }
	}
	
	/*
	 * 第三题:
	 * 225用队列实现栈
	 * 思路:
	 * 		1. 创建队列1,队列2,注意最后交换q1队列和q2队列
	 * 		2. 怎么保证队列达到栈的结构,新元素添加到空白队列,将另一个队列中的元素依次重新加入添加新元素的队列.交换队列.
	 * 		3. 注意明确哪个队列是含有所有值的队列
	 * 		来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/implement-stack-using-queues/
	 */
	class MyStack {
		Deque<Integer> q1;
		Deque<Integer> q2;
		
	    public MyStack() {
	    	q1 = new LinkedList<Integer>();
	    	q2 = new LinkedList<Integer>();
	    }
	    
	    public void push(int x) {
	    	q1.offer(x);
	    	while (!q2.isEmpty()) {
				q1.offer(q2.poll());
			}
	    	Deque<Integer> tempDeque = q1;
	    	q1 = q2;
	    	q2 = tempDeque;
	    }
	    
	    public int pop() {
	    	return q2.poll();
	    }
	    
	    public int top() {
	    	return q2.peek();
	    }
	    
	    public boolean empty() {
	    	return q2.isEmpty();
	    }
	}
	
	/*
	 * 第四题:
	 * 剑指 Offer 06. 从尾到头打印链表
	 * 思路:
	 * 		注意:链表问题需要使用临时变量接收链表否则会出现问题
	 * 		1. 将当链表的next不为空时,遍历链表,将值存入stack
	 * 		2. 遍历stack(先进后出)就能从尾到头打印链表
	 * 		来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cgyt5wt
	 * 		
	 */
    public int[] reversePrint(ListNode head) {
    	 if (head == null) {
    		 return new int[0];
    	 }
    	 Stack<Integer> stack = new Stack<Integer>();
    	 ListNode tempNode = head;
    	 while (tempNode != null) {
			stack.push(tempNode.val);
			tempNode = tempNode.next;
    	 }
    	 int [] res = new int[stack.size()];
    	 int index = 0;
    	 for (int i = 0; i < res.length; i++) {
			res[index++] = stack.pop();
    	 }
    	 return res;
    }
    
	/*
	 * 第五题:
	 * 剑指 Offer 24. 反转链表
	 * 思路:
	 * 		1. 头插法(创建newHead为空,临时变量保存head.next,主动断head.next,newHead赋值已断掉部分,head移动)
	 *		来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cgyt5wt
	 * 		
	 */
    public ListNode reverseList(ListNode head) {
    	if (head == null || head.next == null) {
    		//边界head为空,或者head只有一个节点,直接返回,不需要反转
			return head;
		}
    	ListNode newHead = null;
    	while (head != null) {
    		//先存head.next
        	ListNode temp = head.next;
        	//断开指向null
        	head.next = newHead;
        	//newHead赋值为断开的head
        	newHead = head;
        	//head重新赋值为去掉前面的temp
        	head = temp;
		}
    	return newHead;
    }
    
	/*
	 * 第六题:
	 * 剑指 Offer 35. 复杂链表的复制
	 * 思路:
	 * 		1. 递归
	 * 		2. map
	 * 		3. 创建新的hashMap对象,里面存node,node,异常判断,判断当前node是否存在与map中,如果存在,直接返回node.否则,创建node,加入map,递归当前的next指针和random指针.
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cgyt5wt
	 * 		
	 */
    HashMap<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
    	if (head == null) {
			return head;
		}
    	if (!map.containsKey(head)) {
			Node newHead =  new Node(head.val);
			map.put(head, newHead);
			newHead.next = copyRandomList(head.next);
			newHead.random = copyRandomList(head.random);
		}
    	return map.get(head);
    }
    
    
	/*
	 * 第七题:
	 * 剑指 Offer 05. 替换空格
	 * 思路:
	 * 		1. 创建int数组长度是s字符长度三倍(保证字符串都是空格是也能满足)
	 * 		2. 遍历s当遇到空格是替换为字符串%20
	 * 		3. 转为字符串输出 new String(res, 0, index);//将int数组转为字符串
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/ti-huan-kong-ge-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cgyt5wt
	 * 		
	 */
    public String replaceSpace(String s) {
    	//创建字符数组,长度是初始数组的三倍
    	char[] chars = new char[s.length() * 3];
    	//创建初始化下标
    	int index = 0;
    	//遍历数组遇到字符' '转化为%2d
    	for (int i = 0; i < chars.length; i++) {
			char ch = s.charAt(i);
			if (ch == ' ') {
				chars[index++] = '%';
				chars[index++] = '2';
				chars[index++] = '0';
			} else {
				chars[index++] = ch;
			}
		}
    	//格式化字符串输出(将字符数组转化为字符串0-index位)
    	String newString = new String(chars, 0, index);
    	return newString;
    }
    
	/*
	 * 第八题:
	 * 剑指 Offer 58 - II. 左旋转字符串
	 * 思路:
	 * 		1. 利用api直接截取字符串
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cgyt5wt
	 * 		
	 */
	public String reverseLeftWords(String s, int n) {
		return s.substring(n) + s.substring(0, n);
	}
    
	/*
	 * 第九题:
	 * 	剑指 Offer 03. 数组中重复的数字
	 * 思路:
	 * 		1. hashMap判断是否有重复字符串
	 * 		2. 没有找到重复数字返回-1
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cgyt5wt
	 * 		
	 */
	HashMap<Integer, Integer> timesMap = new HashMap<Integer, Integer>();
    public int findRepeatNumber(int[] nums) {
    	for (int i = 0; i < nums.length; i++) {
    		int num = nums[i];
			if (timesMap.containsKey(num)) {
				return num;
			}
			timesMap.put(num, 1);
		}
    	return -1;
    }
    
    
	/*
	 * 第十题:
	 * 	剑指 Offer 53 - I. 在排序数组中查找数字 I
	 * 思路:
	 * 		1. 二分查找:(找到mid下标循环缩小边界值,知道找到目前值未知).有序数组找目标值,注意左右边界的缩小范围是mid+1和mid-1,是以找到目标值为基准的二分
	 * 		2. 找到最左边.然后判断当前最左边是否是最边界,且不相等,证明没有找到target直接返回0
	 * 		3. 然后以最左边开始找,知道不相等break退出循环,此时times++,times就是出现的次数
	 * 		
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
	 * 		
	 */
    public int search(int[] nums, int target) {
    	//边界
    	if (nums.length == 0 || nums == null) {
			return 0;
		}
    	//二分查找
    	int left = 0,right = nums.length - 1;
    	//为什么是小于且等于,等于也要进一次循环.来获取到右边界点是等于target的前一个元素
    	while (left <= right) {
    		int mid = (left + right)/2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
    	//此时right为=target之前的一个值
    	int temp = right,times = 0;
    	for (int i = temp + 1; i < nums.length; i++) {
			if (nums[i] == target) {
				times++;
			} else {
				//此时已经查找完毕
				break;
			}
		}
    	return times;
    }
    
    
	/*
	 * 第十一题:
	 *	剑指 Offer 53 - II. 0～n-1中缺失的数字
	 * 思路:
	 * 		1. 二分查找:思路列举到最后,确实是left<=right中的等于最后一步,得到查找元素前一个值
	 * 		2. 找到mid如果当前mid值和对应数组值相等,代表前面这部分都是正常的,left=mid+1右移.否则right=mid-1左移.
	 * 		3. 二分查找结束后当前left值已经+1就为缺失的数字
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/
	 * 		
	 */
    public int missingNumber(int[] nums) {
    	int left = 0,right = nums.length - 1;
    	while (left <= right) {
			int mid = (left + right)/2;
			if (nums[mid] == mid) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
    	//最后因为left==right走了一次,还走了一次mid+1,正好得到缺失的值
    	return left;
    }
    
}
