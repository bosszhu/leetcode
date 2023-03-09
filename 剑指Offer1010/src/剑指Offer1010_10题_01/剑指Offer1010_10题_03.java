package 剑指Offer1010_10题_01;

import java.util.HashMap;

public class 剑指Offer1010_10题_03 {
	/*
	 * 第1题:
	 * 剑指 Offer 10- I. 斐波那契数列
	 * 题目:写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
		F(0) = 0,   F(1) = 1
		F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
		斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
		答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof
	 * 思路:
	 *		1. 动态规划.状态方程F[n] = F[n-1]+F[n-2].
	 *		2. 注意初始值,因为n-2,所以f1,f2都是初始值
	 */
    public int fib(int n) {
    	if (n < 2) {
			return n;
		}
    	int[] dp = new int[n+1];
    	dp[0] = 0;dp[1] = 1;
    	for (int i = 2; i < dp.length; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%1000000007;
		}
    	return dp[n];
    }
	
	
	/*
	 * 第2题:
	 * 剑指 Offer 10- II. 青蛙跳台阶问题
	 * 题目:一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
		答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
	 * 思路:
	 * 		1. 动态规划.为什么是动态规划,跳n级台阶可以缩小最后跳一格n-1种,和最后跳2格n-2种,情况
	 * 		2. 状态方程:f[n] = f[n-1] + f[n-2];
	 * 		3. 初始值:dp[1] = 1;dp[2] = 2.所以dp[0] = 0;
	 */
    public int numWays(int n) {
    	int size = n < 2 ? 2:n;
    	int[] dp = new int[size+1];
    	dp[0] = 1;dp[1] = 1;
    	for (int i = 2; i < dp.length; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%1000000007;
		}
    	return dp[n];
    }
	
	/*
	 * 第3题:
	 * 剑指 Offer 63. 股票的最大利润
	 * 题目:假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/
	 * 思路:
	 * 		1. 动态规划.
	 * 		2. 两个变量一格代表成本一个代表利润.可能之间利润就最大所以需要临时变量存起来和后面的进行比较
	 * 		3. 遍历数组,成本为每次price和之前比较的最小值,利润为当前卖出产生利润和之前利润的最大值比较.得到最大利润
	 */
    public int maxProfit(int[] prices) {
    	//先把初始成本定最大,利润定为0
    	int cost = Integer.MAX_VALUE,profit = 0;
    	//利润最大就是成本最小,算出来的利润(成本减去当前卖的价格),利润取最大值.
    	for (int i = 0; i < prices.length; i++) {
			cost = Math.min(cost, prices[i]);
			profit = Math.max(profit, prices[i] - cost);
		}
    	return profit;
    }
    
	/*
	 * 第4题:
	 * 剑指 Offer 42. 连续子数组的最大和
	 * 题目:输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
		要求时间复杂度为O(n)。		
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
	 * 思路:
	 * 		1. 动态规划.
	 * 		2. dp数组(连续子数组最大和的数组),初始值为数组第一个值,一个最大值初始值为数组第一个值
	 * 		3. 遍历目标数组.求dp数组内的值(怎么得到的当前dp[i-1]+num[i]与num[i]比较得到的较大值).
	 * 		4. 根据dp[i]和初始最大值变量比较的值.
	 */
    public int maxSubArray(int[] nums) {
    	int[] dp = new int[nums.length];
    	dp[0] = nums[0];
    	int maxValue = nums[0];
    	for (int i = 1; i < nums.length; i++) {
			dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
			maxValue = Math.max(dp[i], maxValue);
		}
    	return maxValue;
    }
    
	/*
	 * 第5题:
	 * 剑指 Offer 47. 礼物的最大价值
	 * 题目:在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof
	 * 思路:
	 * 		1. 计算最多能拿到多少礼物.只需要知道此一步上面一格或者左边一格最大礼物再加上当前格礼物
	 * 		2. 动态规划.
	 * 		3. 转移方程:dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + grid[i][j];
	 * 		4. 初始值:dp[0][0] = grid[0][0]
	 * 		5. 边界值,如果i,j都为0直接continue,有一个为0只能向下或者右移动(如果使用状态方程会出现数组越界问题),其余使用状态方程.
	 * 		
	 */
    public int maxValue(int[][] grid) {
    	int row = grid.length,col = grid[0].length;
    	int[][] dp = new int[row][col];
    	dp[0][0] = grid[0][0];
    	for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 0 && j == 0) {
					continue;
				} else if (i == 0) {
					dp[i][j] = dp[i][j-1] + grid[i][j];
				} else if (j == 0) {
					dp[i][j] = dp[i-1][j] + grid[i][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
				}
			}
		}
    	return dp[row-1][col-1];
    }
    
    
	/*
	 * 第6题:
	 * 剑指 Offer 46. 把数字翻译成字符串
	 * 题目:给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
	 * 思路:
	 * 		1. 动态规划:类似于求最大连续子数组和,此处求能翻译成的多少种方法.假设前面n-1或n-2已经确定了翻译方法
	 * 			分成两种情况.如果最后的数字<25.状态方程为f[n] = f[n-1]+f[n-2].代表一个数字一起翻译和两个数字组成一个字符翻译
	 * 					   如果最后的数字>25.状态方程就只为f[n] = f[n-1].
	 * 		2. 状态转移方程分成两种情况f[n] = f[n-1]+f[n-2]或f[n] = f[n-1].
	 * 		3. 初始值.dp[2] = 2,dp[1] = 1,所以dp[0] = 1;
	 */
    public int translateNum(int num) {
    	String numString = String.valueOf(num);
    	int[] dp = new int[numString.length()+1];
    	dp[0] = 1;dp[1] = 1;
    	for (int i = 2; i < dp.length; i++) {
    		//获取最后两个数字组成的字符
    		String lastString = numString.substring(i-2, i);
			if (lastString.compareTo("10") >=0 && lastString.compareTo("25") <=0) {
				dp[i] = dp[i-1] + dp[i-2];
			} else {
				dp[i] = dp[i-1];
			}
		}
    	return dp[numString.length()];
    }
    
	/*
	 * 第7题:
	 * 剑指 Offer 48. 最长不含重复字符的子字符串
	 * 题目:请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
	 	来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
	 * 思路:
	 * 		1. 动态规划.
	 * 		2. 先找一个当前字符的index当第一次出现时为-1.
	 * 		3. 状态方程:
	 * 			1. 如果前面没有重复字符出现也就是dp[i-1] < i - index(此时为-1) f[n] = f[n-1]+1;
	 * 			2. 如果前面出现重复字符也就是	dp[i-1] >= i - index(此时index是之前存的下标必然是>=1的) f[n] = i - index.当前下标减去之前重复下标index.
	 * 		3. 初始值:dp[0]代表有一个字符为1,maxValue=1.代表有一个字符也是1.
	 * 		4. 临时变量可能中间就会出现目标值所以需要临时变量再次比较
	 */
    public int lengthOfLongestSubstring(String s) {
    	if (s.length() == 0 || s.equals("")) {
			return 0;
		}
    	int[] dp = new int[s.length()];//不需要+1
    	dp[0] = 1;int maxValue = 1;
    	//准备map用来存每个字符下标,默认下标是-1
    	HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        //设置第0个下标字符的默认index
        hashMap.put(s.charAt(0), 0);
    	for (int i = 1; i < dp.length; i++) {
			int charIndex = hashMap.getOrDefault(s.charAt(i), -1);//取当前遍历的字符的下标,没取到时赋值为-1
			//存当前下标
			hashMap.put(s.charAt(i), i);
			//判断动态规划类型
			if (dp[i-1] < i - charIndex) {
				//没有出现过重复
				dp[i] = dp[i-1] + 1;
			} else {
				//出现过重复(当前字符i减去之前重复字符index就是不含重复字符最大长度)
				dp[i] = i - charIndex;
			}
			//需要和临时变量比较一下.
			maxValue = Math.max(maxValue, dp[i]);
		}
    	return maxValue;
    }
    
	/*
	 * 第8题:
	 * 剑指 Offer 18. 删除链表的节点
	 * 题目:给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。
	 	来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
	 * 思路:
	 * 		1. 前置节点的next节点是当前val,直接赋值为这个节点的next.next.
	 */
    public ListNode deleteNode(ListNode head, int val) {
    	if (head == null) {
			return  head;
		}
    	if (head.val == val) {
			return head.next;//注意返回head.next
		}
    	ListNode pre = head;
    	while (pre != null) {
    		if (pre.next.val == val) {
    			pre.next = pre.next.next;
				break;
			}
    		pre = pre.next;
		}
    	return head;
    }
    
    
	/*
	 * 第9题:
	 * 剑指 Offer 22. 链表中倒数第k个节点
	 * 题目:输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
		例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
	 * 思路:
	 * 		1. 双指针.让一个指针先移动k步,然后当其走到结束时就是倒数第k个节点
	 */
    public ListNode getKthFromEnd(ListNode head, int k) {
    	if (head == null) {
			return null;
		}
    	ListNode fast = head;
    	ListNode slow = head;
    	for (int i = 0; i < k; i++) {//先走k步
			fast = fast.next;
		}
    	while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
    	return slow;
    }
    
    
	/*
	 * 第10题:
	 * 剑指 Offer 25. 合并两个排序的链表
	 * 题目:输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
	 	来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
	 * 思路:
	 * 		1. 归并排序思路
	 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;//单链表为null
		}
    	//创造链表带前置节点
    	ListNode preNode = new ListNode(-1),curNode = preNode;
    	//两个只要有一个为null就退出循环
    	while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				curNode.next = l1;
				l1 = l1.next;
			} else {
				curNode.next = l2;
				l2 = l2.next;
			}
			curNode = curNode.next;//自身移动
		}
    	//拼接剩余
    	curNode.next = (l1 == null) ? l2:l1;
    	return preNode.next;
    }
    
	/*
	 * 第11题:
	 * 剑指 Offer 52. 两个链表的第一个公共节点
	 * 题目:输入两个链表，找出它们的第一个公共节点。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
	 * 思路:
	 * 		1. 你走我走的路.交叉走路径,当交叉走后存在相等值就是第一个公共节点
	 */
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	if (headA == null || headB == null) {
			return null;//不存在相交
		}
    	ListNode A = headA,B = headB;
    	while (A != B) {//一直循环
			if (A == null) {
				A = headB;//走headB
			} else {
				A = A.next;
			}
			if (B == null) {
				B = headA;
			} else {
				B = B.next;
			}
		}
        return A;
    }
    
    /*
	 * 第12题:
	 * 141. 环形链表,142. 环形链表 II
	 * 题目:给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
	如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
	不允许修改 链表。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/linked-list-cycle-ii
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/linked-list-cycle
	 * 思路:
	 * 		1. 双指针快慢指针.
	 * 		2. 一直循环知道找到slow==fast退出循环,或者快指针或者fast.next有一个为null退出循环.
	 * 		3. 找到公共节点后,将fast指针指向head.找到第二次相遇点,此时fast就是入口偏移量.注意第一次追赶fast移动两步,第二次追赶fast和slow移动相同步频
	 * 		为什么是这样,根据题意可得公司fast = 2 * slow,fast = a + nb,得出slow = nb.从head节点再次到入环点需要走a + nb,而此时slow已经走了nb.那么slow再走a步就到入环点了
	 */
    public ListNode detectCycle(ListNode head) {
    	if (head == null) {
			return null;
		}
    	//让其第一次相遇
    	ListNode fast = head,slow = head;
    	while (true) {
			if (fast == null || fast.next == null) {//异常判断
				return null;
			}
			fast =  fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				//第一次相遇退出循环
				break;
			}
		}
    	//第二次相遇
    	fast = head;
    	while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
    	return fast;
    }
}
