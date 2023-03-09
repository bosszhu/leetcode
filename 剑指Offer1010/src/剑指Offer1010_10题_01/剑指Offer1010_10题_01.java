package 剑指Offer1010_10题_01;

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
	 *
	 */
	class CQueue {
		Stack<Integer> inStack;
		Stack<Integer> outStack;
	    public CQueue() {
	    	inStack = new Stack<Integer>();
	    	outStack = new Stack<Integer>();
	    }
	    //直接添加值
	    public void appendTail(int value) {
	    	inStack.push(value);
	    }
	    
	    public int deleteHead() {
	    	//先要哦按段当前outStack是否是空
	    	checkOutStack();
	    	if (!outStack.isEmpty()) {
	    		return outStack.pop();
			}
	    	return -1;//全部为空
	    }
	    
	    public void checkOutStack() {
			if (outStack.isEmpty()) {
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
	 *
	 */
	class MinStack {

		Stack<Integer> noramlStack;
		Stack<Integer> minStack;
	    public MinStack() {
	    	noramlStack = new Stack<>();
	    	minStack = new Stack<>();
	    	minStack.push(Integer.MAX_VALUE);
	    }
	    
	    public void push(int x) {
	    	noramlStack.push(x);
	    	minStack.push(Math.min(minStack.peek(), x));
	    }
	    
	    public void pop() {
	    	noramlStack.pop();
	    	minStack.pop();
	    }
	    
	    public int top() {
	    	return noramlStack.peek();
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
	 */
	class MyStack {
		Deque<Integer> q1;
		Deque<Integer> q2;
	    public MyStack() {
	    	q1 = new LinkedList<>();
	    	q2 = new LinkedList<>();
	    }
	    
	    public void push(int x) {
	    	q1.offer(x);
	    	while (!q2.isEmpty()) {
				q1.offer(q2.pop());
			}
	    	//交换队列
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
	 */
    public int[] reversePrint(ListNode head) {
    	Stack<Integer> stack = new Stack<Integer>();
    	if (head == null) {
			return new int[0];
		}
    	ListNode temp =  head;
    	while (temp != null) {
			stack.push(temp.val);
			temp = temp.next;
		}
    	int[] res = new int[stack.size()];
    	int index = 0;
    	while (!stack.isEmpty()) {
			res[index++] = stack.pop();
		}
    	return res;
    }
    
	/*
	 * 第五题:
	 * 剑指 Offer 24. 反转链表
	 * 思路:
	 * 		1. 头插法(创建newHead为空,临时变量保存head.next,主动断head.next,newHead赋值已断掉部分,head移动)
	 */
    public ListNode reverseList(ListNode head) {
    	if (head == null || head.next == null) {
			return head;
		}
    	ListNode newHead = null;
    	while (head != null) {
			ListNode temp = head.next;//先将head.next保存起来
			head.next = newHead;//第一次代表清空next
			newHead = head;
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
	 */
    HashMap<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
    	if (head == null) {
			return head;
		}
    	if (!map.containsKey(head)) {
			//不存在创建node
    		Node newHead = new Node(head.val);
    		map.put(head, newHead);//前面放当前节点,后面放心创建节点
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
	 * 		3. 转为字符串输出
	 */
    public String replaceSpace(String s) {
    	//创建字符数组,长度是初始数组的三倍
    	char[] chars = new char[s.length() * 3];
    	//创建初始化下标
    	int index = 0;
    	//遍历数组遇到字符' '转化为%2d,注意只需要遍历字符串的长度
    	for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				chars[index++] = '%';
				chars[index++] = '2';
				chars[index++] = '0';
			} else {
				chars[index++] = s.charAt(i);
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
	 * 		1. 二分查找:(找到mid下标循环缩小边界值,知道找到目前值未知).有序数组找目标值
	 * 		2. 找到最左边.然后判断当前最左边是否是最边界,且不相等,证明没有找到target直接返回0
	 * 		3. 然后以最左边开始找,知道不相等break退出循环,此时times++,times就是出现的次数
	 * 		
	 */
    public int search(int[] nums, int target) {
    	//异常判断
    	if (nums == null || nums.length == 0) {
			return 0;//没找到
		}
    	int left = 0,right = nums.length - 1;
    	while (left <= right) {
			int mid = (left + right)/2;
			if (nums[mid] < target) {//左移
				left = mid + 1;
			} else {//右移
				right = mid - 1;
			}
		}
    	int temp = right,times = 0;//确定左边界
    	//从temp位置开始知道找不到相等的值结束
    	for (int i = temp + 1; i < nums.length; i++) {
			if (nums[i] == target) {
				times++;
			} else {
				break;//没找到退出
			}
		}
    	return times;
    }
    
    
	/*
	 * 第十一题:
	 *	剑指 Offer 53 - II. 0～n-1中缺失的数字
	 * 思路:
	 * 		1. 二分查找
	 * 		2. 找到mid如果当前mid值和对应数组值相等,代表前面这部分都是正常的,left=mid+1右移.否则right=mid-1左移.
	 * 		3. 二分查找结束后当前left值已经+1就为缺失的数字
	 */
    public int missingNumber(int[] nums) {
    	int left = 0,right = nums.length - 1;
    	while (left <= right) {
			int mid = (left + right)/2;
			if (nums[mid] == mid) {//左边没缺失值
				left = mid + 1;
			} else {//右边没缺失值
				right = mid - 1;
			}
		}
    	return left;
    }
    
}
