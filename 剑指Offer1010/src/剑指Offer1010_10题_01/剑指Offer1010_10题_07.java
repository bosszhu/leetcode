package 剑指Offer1010_10题_01;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class 剑指Offer1010_10题_07 {
	/*
	 * 第1题:
	 * 剑指 Offer 29. 顺时针打印矩阵
	 * 题目:输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
	 * 思路:
	 *		1. 四指针
	 *		2. left,right,top,bottom四指针
	 *		3. 每次走完一行或者一列判断是否为边界然后退出
	 */
	public int[] spiralOrder(int[][] matrix) {
    	//边界问题
    	if (matrix.length == 0 || matrix == null) {
			return new int[0];
		}
    	//四指针初始化(指的是下标)和目标数组及index初始化
    	int left = 0,right = matrix[0].length - 1,top = 0,bottom = matrix.length - 1,index = 0;
    	int[] res = new int[(right+1) * (bottom+1)];//2*3数组总长度应该是2*3
    	while (true) {//死循环
    		//从left->right
    		for (int i = left; i <= right; i++) {
				res[index++] = matrix[top][i];//加入第一层数据
			}
    		top++;//走完就++
    		//边界
    		if (top > bottom) {
				break;
			}
    		//从top->bottom
    		for (int i = top; i <= bottom; i++) {
				res[index++] = matrix[i][right];//加入第一层数据
			}
    		right--;//走完就++
    		//边界
    		if (right < left) {
				break;
			}
    		//从right->left
    		for (int i = right; i >= left; i--) {
				res[index++] = matrix[bottom][i];//加入第一层数据
			}
    		bottom--;//走完就++
    		//边界
    		if (bottom < top) {
				break;
			}
    		//从bottom->top
    		for (int i = bottom; i >= top; i--) {
				res[index++] = matrix[i][left];//加入第一层数据
			}
    		left++;//走完就++
    		//边界
    		if (left > right) {
				break;
			}
		}
    	return res;
    }
	
	
	/*
	 * 第2题:
	 * 剑指 Offer 31. 栈的压入、弹出序列
	 * 题目:输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
	 * 思路:
	 * 		1. 辅助栈
	 * 		2. 初始下标index = 0;
	 * 		3. 循环压入序列,加入栈.每次循环判断当前不为空且栈顶元素是否是当前index的值,如果是index++判断下一个是否是栈顶元素
	 * 		4. 最后判断栈是否为空
	 */
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		Stack<Integer> stack = new Stack<Integer>();
		int index = 0;
		for (int i : pushed) {
			//入栈
			stack.push(i);
			//判断是否是弹出序列相应下标元素
			while (!stack.isEmpty() && stack.peek() == popped[index]) {
				stack.pop();
				index++;
			}
		}
    	return stack.isEmpty();
    }
	
	/*
	 * 第3题:
	 * 剑指 Offer 20. 表示数值的字符串
	 * 题目:请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
	 * 思路:
	 * 		1. 有限状态机:定义各种状态当满足某些条件时进入另外一种状态.最后判断最终状态是否满足要求
	 */
    public boolean isNumber(String s) {
    		Map[] states = {
                new HashMap<Character,Integer>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                new HashMap<Character,Integer>() {{ put('d', 2); put('.', 4); }},                           // 1.
                new HashMap<Character,Integer>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                new HashMap<Character,Integer>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                new HashMap<Character,Integer>() {{ put('d', 3); }},                                        // 4.
                new HashMap<Character,Integer>() {{ put('s', 6); put('d', 7); }},                           // 5.
                new HashMap<Character,Integer>() {{ put('d', 7); }},                                        // 6.
                new HashMap<Character,Integer>() {{ put('d', 7); put(' ', 8); }},                           // 7.
                new HashMap<Character,Integer>() {{ put(' ', 8); }}                                         // 8.
            };
            int p = 0;
            char t;
            for(char c : s.toCharArray()) {
                if(c >= '0' && c <= '9') t = 'd';
                else if(c == '+' || c == '-') t = 's';
                else if(c == 'e' || c == 'E') t = 'e';
                else if(c == '.' || c == ' ') t = c;
                else t = '?';
                if(!states[p].containsKey(t)) return false;
                p = (int)states[p].get(t);
            }
            return p == 2 || p == 3 || p == 7 || p == 8;
    }
    
	/*
	 * 第4题:
	 * 剑指 Offer 67. 把字符串转换成整数
	 * 题目:写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
	当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
	该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
	注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
	在任何情况下，若函数不能进行有效的转换时，请返回 0。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
	 * 思路:
	 * 		1. 有限状态机
	 */
    public int strToInt(String str) {
        char[] c = str.trim().toCharArray();
        if(c.length == 0) return 0;
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if(c[0] == '-') sign = -1;
        else if(c[0] != '+') i = 0;
        for(int j = i; j < c.length; j++) {
            if(c[j] < '0' || c[j] > '9') break;
            if(res > bndry || res == bndry && c[j] > '7') return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }
    
	/*
	 * 第5题:
	 * 剑指 Offer 59 - I. 滑动窗口的最大值
	 * 题目:给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
	 * 思路:	
	 * 题意:给定一个数组和滑动窗口k(初始从0下标到k-1下标).每次找到当前窗口最大值然后向后滑动,每次的最大值组成新的数组
	 * 		1. 单调队列:辅助队列的一种,维护队列,其实满足线性递增或者递减需求.需要满足push和pop需求.
	 * 		队列里面维护可能成为最大值的序列
	 * 		pop怎么实现??pop值大于队列队首最大值,poll最大值??为什么?因为push已经把小的值都poll消除了.
	 * 		push怎么实现??如果我加入的元素比你队列队尾的元素都大,就把队列队尾全部弹出.
	 * 		获取最大值就是队首元素就是最大值
	 * 
	 * 		2. 具体实现
	 * 			1. 异常判断
	 * 			2. 创建数组nums.length - k + 1(如数组有4个元素k为3此时滑动窗口有两个最大值)
	 * 			3. 遍历到滑动窗口,向单调队列内添加元素
	 * 			4. 赋值第一个值.
	 * 			5. 遍历剩余元素滑动窗口.
	 * 				1. 单调队列移除旧值
	 * 				2. 单调队列添加新值
	 * 				3. 向新数组相应位置添加最大值元素(单调队列内的队首值);
	 * 			6. 怎么向单调队列添加值
	 * 				1. 循环判断如果单调队列不为空且单调队列目前队尾元素(单调队列里面最小的)小于新加进来的值添加新值
	 * 				2. 移除单调队列内小于新添加的值加入新值
	 * 			7. 怎么向单调队列移除值
	 * 				1. 如果单调队列不为空且当前需要移除的值是单调队列的队首元素才移除
	 */
	Deque<Integer> maxDeque = new LinkedList<>();
    public int[] maxSlidingWindow(int[] nums, int k) {
    	if (nums.length == 0 || k == 0) {
			return new int[0];
		}
    	//初始化新数组(当前数组减去滑动窗口大小+1)
    	int[] res = new int[nums.length - k + 1];
    	//初始化滑动窗口,加入单调队列(先向从0初始滑动窗口大小,往单调队列内加值)
    	for (int i = 0; i < k; i++) {
			push_maxDeque(nums[i]);
		}
    	//向新数组加第一个值(单调队列里面的队首)
    	int index = 0;
    	res[index] = maxDeque.peekFirst();
    	index++;
    	//滑动窗口加入后面的值
    	for (int i = k; i < nums.length; i++) {
			//移除旧值(比如数组长度是4滑动窗口是3第一次进入需要移除下标是0的值)
    		pop_maxDeque(nums[i-k]);
    		//添加新值
    		push_maxDeque(nums[i]);
    		//加入数组
    		res[index++] = maxDeque.peekFirst();
		}
    	return res;
    }
    public void push_maxDeque(int value) {
    	//当前队列队尾有比添加值小的值全部移除
		while (!maxDeque.isEmpty() && maxDeque.peekLast() < value) {
			//移除单调队列中小的值
			maxDeque.pollLast();
		}
		maxDeque.offerLast(value);//向队尾添加新值
	}
    public void pop_maxDeque(int value) {
    	//代表当前移除的值是单调队列最大值时才需要删除,因为如果不是最大值移除时已经删除.
		if (!maxDeque.isEmpty() && maxDeque.peekFirst() == value) {
			maxDeque.pollFirst();
		}
	}
    
	/*
	 * 第6题:
	 * 面试题59 - II. 队列的最大值
	 * 题目:请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
	 * 若队列为空，pop_front 和 max_value 需要返回 -1
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof
	 * 思路:
	 * 		1. 单调队列
	 * 
	 */
    class MaxQueue {
    	Deque<Integer> maxDeque;
    	Deque<Integer> normalDeque;
        public MaxQueue() {
        	 maxDeque = new LinkedList<>();
        	 normalDeque = new LinkedList<>();
        }
        
        public int max_value() {
        	if (!maxDeque.isEmpty()) {
				return maxDeque.peekFirst();
			}
        	return -1;
        }
        
        public void push_back(int value) {
        	normalDeque.offer(value);
        	while (!maxDeque.isEmpty() && maxDeque.peekLast() < value) {
				maxDeque.pollLast();
			}
        	maxDeque.offerLast(value);
        }
        
        public int pop_front() {
        	if (!normalDeque.isEmpty()) {
	    		//判断当前正常队列的队首是否是最大值队列的队首如果是移除
	    		if (!maxDeque.isEmpty() && normalDeque.peekFirst().equals(maxDeque.peekFirst())) {
	    			maxDeque.pollFirst();
				}
	    		return normalDeque.pollFirst();
			}
        	return -1;
        }
    }
	/*
	 * 第6题:
	 * 剑指 Offer 37. 序列化二叉树
	 * 题目:请实现两个函数，分别用来序列化和反序列化二叉树。
	你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof
		
		题意:序列化和反序列化就是转成字符串和字符串转TreeNode
	 * 思路:
	 * 		1. 层序遍历
	 * 		2. 序列化:
	 * 			层序遍历添加到数组中,注意字符串的拼接问题
	 * 		3. 反序列化:
	 * 			去除头尾[]然后以,分隔为字符串.取第一个元素作为根节点入队,开始层序遍历判断
	 * 
	 */
    public String serialize(TreeNode root) {
    	if (root == null) {
			return "[]";
		}
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("[");
    	Deque<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == null) {
				stringBuilder.append("null,");
			} else {
				stringBuilder.append(node.val + ",");
				queue.offer(node.left);
				queue.offer(node.right);
			}
		}
    	return stringBuilder.toString();
    }
    public TreeNode deserialize(String data) {
    	if (data.equals("[]") ) {
			return null;
		}
    	//消除左右符号,切割为字符串数组
    	String[] strings = data.substring(1, data.length() - 1).split(",");
    	//根据数组创建根节点
    	int index = 0;
    	TreeNode root = new TreeNode(Integer.parseInt(strings[index++]));
    	//入队
    	Deque<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			//根据数组对应下标判断是不是null字符串入队
			if (!strings[index].equals("null")) {
				node.left = new TreeNode(Integer.parseInt(strings[index]));
				//将左节点入队
				queue.offer(node.left);
			}
			index++;
			if (!strings[index].equals("null")) {
				node.right = new TreeNode(Integer.parseInt(strings[index]));
				//将左节点入队
				queue.offer(node.right);
			}
			index++;
		}
    	return root;
    }
	/*
	 * 第7题:
	 * 剑指 Offer 38. 字符串的排列
	 * 题目:输入一个字符串，打印出该字符串中字符的所有排列。
	你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
	 * 思路:
	 * 		1. 递归回溯
	 * 		2. 递归思路:
	 * 			1. 递归退出条件当前递归元素是当前字符数组最后一个元素时结束递归.添加到目标数组中
	 * 			2. 使用HashSet(防止集合内元素重复)
	 * 			3. 遍历字符数组从fixIndex开始到字符结束
	 * 				1. 判断当前set内含有该字符结束当前次循环
	 * 				2. 否则加入set,交换当前下标和对应fixIndex内字符.
	 * 				3. 继续递归找固定字符的下一个字符.
	 * 				4. 递归结束后需要复原交换的两个字符下标
	 * 		
	 * 			
	 */
    char[] chs;
    List<String> resStrings = new LinkedList<String>();
    public String[] permutation(String s) {
    	chs = s.toCharArray();
    	dfs_permutation(0);
    	return resStrings.toArray(new String[resStrings.size()]);
    }
    public void dfs_permutation(int fixIndex) {
    	//递归退出条件
    	if (fixIndex == chs.length - 1) {
    		//退出前加入目标数组中
    		resStrings.add(String.valueOf(chs));//此时内部是已经交换过的
			return;
		}
    	//创建集合
    	HashSet<Character> set = new HashSet<Character>();
    	//遍历字符数组从固定下标位置开始便利
    	for (int i = fixIndex; i < chs.length; i++) {
			char ch = chs[i];
			if (set.contains(ch)) {
				//剪枝
				continue;
			}
			set.add(ch);
			//交换
			swap_permutation(i, fixIndex);
			//递归
			dfs_permutation(fixIndex + 1);
			//复原
			swap_permutation(i, fixIndex);
		}
    }
    public void swap_permutation(int i,int j) {
		char temp = chs[i];
		chs[i] = chs[j];
		chs[j] = temp;
	}
	/*
	 * 第8题:
	 * 剑指 Offer 19. 正则表达式匹配
	 * 题目:请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof
	 * 思路:
	 * 		1. 动态规划:二维数据用来表示s前i个字符和p的前j个字符是否存在完全匹配.
	 * 		2. 状态转移
	 * 			1. p[j]不等于*此时f(i,j) = f(i-1,j-1) && (i,j)表示s的i-1和p的j-1匹配,i,j匹配
	 * 				1. s的i字符等于p的j字符,
	 * 				2. p的j字符是.
	 * 				都满足匹配
	 * 			2. p[j]等于*,由于*可以匹配前面字符的任意次包含0次
	 * 				1. *符号匹配0个字符,f(i,j) = f(i,j-2)
	 * 				2. *符号匹配一个或多个字符,f(i,j) = f(i-1,j) && (s[i] == p[j -1] || p[j-1] == '.')
	 * 			
	 * 
	 */
    public boolean isMatch(String A, String B) {
    	//去字符串长度记为m和n
    	int m = A.length(),n = B.length();
    	//初始化二维数组,初始为false
    	boolean[][] dp = new boolean[m+1][n+1];
    	for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				//考虑p为空串的情况
				if (j == 0 ) {
					if (i == 0) {
						//i,j都为0代表s的前i个字符和p的前j个字符都为空
						dp[i][j] = true;
					}
				} else {
					//p不为空串
					if (B.charAt(j-1) != '*') {//p(j-1)不是*
						if (i >=1 && (A.charAt(i-1) == B.charAt(j-1) || B.charAt(j-1) =='.')) {
							dp[i][j] = dp[i-1][j-1];
						}
					} else {
						//p(j-1)是*
						if (j >= 2) {
//							dp[i][j] |= dp[i][j-2]; 
							dp[i][j] = dp[i][j] || dp[i][j-2];
						}
						//A的i-1字符等于B的j-2字符即让字符 p[j - 2] 多出现 1 次时，能否匹配或者j-2等于''
						if (j >= 2 && i>= 1 && (A.charAt(i-1) == B.charAt(j-2) || B.charAt(j-2) == '.')) {
							dp[i][j] |= dp[i-1][j];//为什么回头找
						}
					}
				}
			}
		}
    	return dp[m][n];
    }
    
    
	/*
	 * 第9题:
	 * 剑指 Offer 49. 丑数
	 * 题目:我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/chou-shu-lcof/
	 * 思路:
	 * 		1. 动态规划.
	 * 		2. 初始值dp[0] = 1,
	 * 		2. 默认创建三个质因子指针都指向下标为0的位置p2,p3,p5.
	 * 		3. 然后根据下标*2,3,5得到相应的n2,n3,n5
	 * 		4. 判断当前n2,n3,n5的最小值.得到dp[i]
	 * 		5. 根据dp[i]得到值反查是哪个n然后移动相应的p指针(p++),开始下一轮循环
	 */
    public int nthUglyNumber(int n) {
    	int[] dp = new int[n];//根据举例求得创建元素就是n,如第一个最小元素是1,此时是数组下标为0位置的元素.代表
    	dp[0] = 1;
    	int p2 = 0, p3 = 0,p5 = 0;
    	for (int i = 1; i < dp.length; i++) {
			int n2 = 2 * dp[p2],n3 = 3 * dp[p3],n5 = 5 * dp[p5];
			dp[i] = Math.min(Math.min(n2, n3), n5);
			if (dp[i] == n2) { 
				p2++;
			}
			if (dp[i] == n3) {
				p3++;
			} 
			if(dp[i] == n5) {
				p5++;
			};
		}
        return dp[n-1];
    }
    
	/*
	 * 第9题:
	 * 剑指 Offer 60. n个骰子的点数
	 * 题目:把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
	你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof
	 * 思路:
	 * 		1. 动态规划:
	 * 		2. dp数组二维数组,第一维代表当前投掷第几个骰子:(n+1),第二个代表点数(6*n+1)
	 * 		3. 初始值:骰子第一个骰子时每个点数出现的次数.都是1,dp[1[i] = 1;
	 * 		4. 转化方程:
	 * 			1. 三层for循环,第一层for循环从掷第二粒骰子开始.
	 * 			2. 第二层for循环代表点数从i开始到6*i结束.
	 * 			3. 第三层for循环,代表最后一次投掷的点数从1开始到6结束.注意越界问题j < k
	 * 			逻辑可得,我们需要得到n个骰子j点的情况,如果我们投掷最后一个骰子的点数是1点,此时需要找到[i-1][j-1],为2点[i-1][j-2]
	 * 			由dp[i][j] = dp[i-1][j-1]+dp[i-1][j-2]+...+dp[i-1[j-6] 得到dp[i][j] += dp[i-1][j-k]此为状态方程.
	 * 		5. 确定总点数:double类型6的n次方
	 * 		6. 确定结果数组总长度:double类型数组.5*n+1由6*n - n + 1得到.1一个骰子6,2个骰子11,3个骰子16.
	 * 		7. 计算每一个概率.
	 */
    public double[] dicesProbability(int n) {
    	//创建dp数组
    	int[][] dp = new int[n+1][6*n+1];
    	//初始化
    	for (int i = 1; i <= 6; i++) {
			dp[1][i] = 1;
		}
    	//状态方程
    	for (int i = 2; i <= n; i++) {//第二个骰子开始知道n个骰子结束
			for (int j = i; j <= 6*n; j++) {//i点开始知道6*n点数结束
				for (int k = 1; k <= 6; k++) {//最后一个骰子点数
					//每个点数对应值.
					//dp[i][j] = dp[i-1][j-1] + dp[i-1[j-2]...
					//注意越界问题
					if (j < k) {//数组越界,不需要计算
						break;
					}
					dp[i][j] += dp[i-1][j-k];
				}
			}
		}
    	//最终数据计算
    	//总点数
    	double allNum = Math.pow(6, n);
    	//结果数组
    	double[] res = new double[5*n+1];
    	int index = n;//从最小值n开始
    	for (int i = 0; i < res.length; i++) {
			res[i] = dp[n][index++] / allNum;
		}
    	return res;
    }
}
