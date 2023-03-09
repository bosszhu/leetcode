package 剑指Offer20230207复习;

import java.util.HashMap;

public class 剑指Offer补充题目 {
	/*
	 * 第1题:
	 * 160. 相交链表
	 * 题目:给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/intersection-of-two-linked-lists/
		思路:
			1. 你走我走的路,我走你的路,如果相遇必然相交.否则为null(若不相交a+b等于b+a)
			2. 怎么转化为代码?
	*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	//边界
    	if (headA == null || headB == null) {
			return null;
		}
    	ListNode A = headA,B = headB;
    	//存在不相交的情况,但是只要遍历交换一轮必然可以看出来是否相交
    	int times = 0;
    	//要么A=B要么times=3
    	while (A != B || times < 3) {
			if (A == null) {
				A = headB;
				times++;
			} else {
				A = A.next;
			}
			if (B == null) {
				B = headA;
				times++;
			} else {
				B = B.next;
			}
		}
    	//如果time累加了两次然后退出循环了,代表有相交点,如果times到第三次了代表a+b等于b+a此时永不相交
        return times == 3 ? null : A;
    }
    
	
	/*
	 * 第2题:
	 * 19. 删除链表的倒数第 N 个结点
	 * 题目:给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
		思路:
			1. 注意是倒数第k个节点,双指针先遍历到n节点,然后遍历当前节点到null后
	*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	//边界
    	if (n<1 || head == null) {
			return head;
		}
    	ListNode fast = head;
    	ListNode slow = head;
    	for (int i = 0; i < n; i++) {
			fast = fast.next;
		}
    	//异常判断fast已经走完,以为k太大没有倒数第k个元素
    	if (fast == null) {
			return head.next;
		}
    	//既然没办法改变slow直接改变fast.next就提前一步保证slow在倒数第k节点前
    	while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
    	slow.next = slow.next.next;
    	return head;
    }
	
	/*
	 * 第3题:
	 * 876. 链表的中间结点
	 * 题目:给定一个头结点为 head 的非空单链表，返回链表的中间结点。
		如果有两个中间结点，则返回第二个中间结点。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/middle-of-the-linked-list/
		思路:
			快慢指针,一个走一步一个走两步,当快的走完,慢的正好走到一般的位置.
			注意循环判断条件fast != null且fast.next != null(否则a.null.next报错)
	*/
    public ListNode middleNode(ListNode head) {
    	if (head == null) {
			return null;
		}
    	ListNode fast = head, slow = head;
    	while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
    	return slow;
    }
    
    
	/*
	 * 第4题:
	 * 剑指 Offer II 026. 重排链表
	 * 题目:给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 		L0 → L1 → … → Ln-1 → Ln 
		请将其重新排列后变为：
		L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
		不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/LGjMqU/
		思路:
			综合题:寻找链表中点 + 链表逆序 + 合并链表
			1. 快慢指针寻找中间节点(注意是 876. 链表的中间结点此题的后一个节点)
			2. 头插法翻转后半段链表
			3. 合并链表
	*/
    public void reorderList(ListNode head) {
    	//边界
    	if (head == null || head.next == null) {
			return;
		}
    	//1. 快慢指针获取中间节点
    	ListNode mid = middleListNode(head);
    	//2. 获取中间节点之后链表l2
    	ListNode l2 = mid.next;
    	//默认l1为head
    	ListNode l1 = head;
    	//3. 截断mid后面获取l1
    	mid.next = null;
    	//4. 翻转l2
    	l2 = reverseListNode(l2);
    	//5. 合并链表(交叉合并非排序合并)
    	mergeListNode(l1, l2);
    }
    //获取链表的中间节点
    public ListNode middleListNode(ListNode head) {
    	ListNode fast = head,slow = head;
    	//之前拿的是偶数节点的前面节点,现在需要拿后面节点,所以循环需要多一步
    	while (fast.next != null && fast.next.next != null ) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
    //翻转链表(头插法)
    public ListNode reverseListNode(ListNode head) {
    	ListNode newHead = null;
    	while (head != null) {
			ListNode temp = head.next;
			head.next = newHead;
			newHead = head;
			head = temp;
		}
		return newHead;
	}
    //合并链表
    public void mergeListNode(ListNode l1,ListNode l2) {
    	//当l1不为空且l2不为空,都为null退出
    	ListNode l1_temp,l2_temp;
		while (l1 != null && l2 != null) {
			//存起下一个节点,防止丢失
			l1_temp = l1.next;
			l2_temp = l2.next;
			
			l1.next = l2;
			l1 = l1_temp;
			
			l2.next = l1;
			l2 = l2_temp;
		}
	}
	/*
	 * 第5题:
	 * 3. 无重复字符的最长子串
	 * 题目:给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/
		思路:
			
			1. 动态规划
				辅助数据结构:HashMap
				初始值:dp[0] = 1,maxLength = 1
				状态方程:
					1. 先默认将s内的每个字符根据value找下标没找到默认为-1,
					2. 当前字符存当前下标.
					2. 循环dp(循环从1开始)
						如果当前dp[i-1](前一个dp) < i - charIndex代表没有重复出现的值
						dp[i] = dp[i-1]+1
						否则有重复出现的值,就等于当前的i减去之间获取到的重复字符的下标
						dp[i] = i - charIndex
					    需要比较一下中间变量和dp[i]的大小可能之前的更大
			2. 滑动窗口
			
	*/
    public int lengthOfLongestSubstring(String s) {
    	if (s.length() == 0 || s.equals("")) {
			return 0;
		}
    	//创建dp数组,初始化值
    	int[] dp = new int[s.length()];
    	int max = 1;dp[0] = 1;
    	//创建map初始化第0下标元素
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	map.put(s.charAt(0), 0);
    	for (int i = 1; i < dp.length; i++) {
			int charIndex = map.getOrDefault(s.charAt(i), -1);
			map.put(s.charAt(i), i);
			if (dp[i-1] <  i - charIndex) {
				dp[i] = dp[i-1] + 1;
			} else {
				dp[i] = i - charIndex;
			}
			max = Math.max(max, dp[i]);
		}
    	return max;
    }
    
	/*
	 * 第6题:
	 * 704. 二分查找
	 * 题目:给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/binary-search/
		
		思路:
		1. 首先有序升序数组,可能在升序的过程确实这个target值,所以不一定能找到,但是什么可以找到,最左边小于target的值可以找到
		2. 找到这个值的下标,然后从此处+1开始,没有元素直接返回-1.否则直接返回当前下标
	*/
    public int search(int[] nums, int target) {
    	//边界
    	if (nums.length == 0) {
			return -1;
		}
    	int left = 0,right = nums.length-1;
    	while (left <= right) {
			int mid = (left+right)/2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid -1;
			}
		}
    	//越界
    	if (right+1 > nums.length - 1) {
			return -1;
		}
    	//如果等于就是right+1否则就是找不到
    	return nums[right+1] == target ? right + 1 : -1;
    }
    
    
    
	/*
	 * 第7题:
	 * 146. LRU 缓存
	 * 题目:请你设计并实现一个满足  LRU  英文名字Least Recently Used (最近最少使用) 缓存 约束的数据结构。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/lru-cache/
		
	*/
    class LRUCache {
    	/*
    	 * 步骤:
    	 * 1. 创建双向链表数据结构,包含pre,next,key,value
    	 * 2. 在缓存类中声明变量,head(虚拟头结点),tail(虚拟尾结点),capacity(容量),size(当前使用容量),cache(HashMap代表缓存key:int,value:node)
    	 * 3. 初始化类,初始化所有变量初始化双向链表
    	 * 4. 获取值
    	 * 		1. 先从缓存中获取如果缓存为空,返回-1未查找到
    	 * 		2. 缓存返回有值.删除缓存中node在双向链表位置,删除并改变当前节点为头结点
    	 * 5. 设置值
    	 * 		1. 先从缓存中获取,如果没有拿到值
    	 * 			1. size++,缓存设置值,插入头节点.
    	 * 			2. 判断size是否大于capacity,如果大于,需要删除尾结点,删除缓存尾结点值.size--
    	 * 		2. 如果拿到值
    	 * 			1. 将新值覆盖旧值,删除并改变当前节点为头结点
    	 * 
    	 */
    	
        class Node {
    		Node pre;
    		Node next;
    		int key;
    		int value;
        	
        	public Node() {
        		
			}
        	public Node(int key, int value) {
        		this.key = key;
        		this.value = value;
        	}
    	}
    	
    	Node head,tail;
    	int capacity,size;
    	HashMap<Integer, Node> cache;
        public LRUCache(int capacity) {
        	//初始化变量初始化双向链表
        	this.capacity = capacity;
        	this.size = 0;
            initDoublyLinkedlist();
        	this.cache = new HashMap<>();
        }
        public void initDoublyLinkedlist() {
        	/*
        	 * 初始化双向链表
        	 */
        	this.head = new Node();
        	this.tail = new Node();
			head.next = tail;
			tail.pre = head;
		}
        public void removeAndChangeToHead(Node node) {
        	removeNode(node);
        	addToHead(node);
        }
        public void removeNode(Node node) {
        	node.pre.next = node.next;
        	node.next.pre = node.pre;
        }
        public void addToHead(Node node) {
        	node.next = head.next;
        	head.next = node;
        	node.next.pre = node;
        	node.pre = head;
        }
        public Node removeTail() {
        	Node node = tail.pre;
        	removeNode(node);
        	return node;
        }  
        
        public int get(int key) {
        	Node node = cache.get(key);
        	if (node == null) {
				return -1;
			}
        	removeAndChangeToHead(node);
        	return node.value;
        }
        
        public void put(int key, int value) {
        	Node node = cache.get(key);
        	if (node == null) {
				size++;
				Node newNode = new Node(key, value);
				cache.put(key, newNode);
				addToHead(newNode);
				if (size > capacity) {
					size--;
					Node oldTail = removeTail();
					cache.remove(oldTail.key);
					
				}
			} else {
				//存在旧值
				node.value = value;
				removeAndChangeToHead(node);
			}
        }
    }
}
