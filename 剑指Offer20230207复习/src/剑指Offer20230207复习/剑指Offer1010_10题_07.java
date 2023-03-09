package 剑指Offer20230207复习;

import java.util.ArrayList;
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
		/*
		 * 四指针,越界退出
		 * 1. 先确定边界,以及四指针的初始大小
		 * 
		 */
		if (matrix.length == 0 || matrix == null) {
			return new int[0];
		}
		//容器
		List<Integer> list = new ArrayList<Integer>();
		int left = 0,right = matrix[0].length - 1,top = 0,bottom = matrix.length - 1;
		while (true) {
			//先从左上往右上
			int tempLeft = left;
			while (tempLeft <= right) {
				list.add(matrix[top][tempLeft++]);
			}
			//走完后
			top++;
			//走完后大于边界
			if (top > bottom) {
				break;
			}
			
			//再从右上到右下
			int tempTop = top;
			while (tempTop <= bottom) {
				list.add(matrix[tempTop++][right]);
			}
			//走完后
			right--;
			//走完后大于边界
			if (right < left) {
				break;
			}
			
			//再从右下到左下
			int tempRight = right;
			while (left <= tempRight) {
				list.add(matrix[bottom][tempRight--]);
			}
			//走完后
			bottom--;
			//走完后大于边界
			if (bottom < top) {
				break;
			}
			
			//再从左下到左上
			int tempBottom = bottom;
			while (top <= tempBottom) {
				list.add(matrix[tempBottom--][left]);
			}
			//走完后
			left++;
			//走完后大于边界
			if (left > right) {
				break;
			}
		}
		int[] res = new int[list.size()];
		int index = 0;
		for (int i = 0; i < res.length; i++) {
			res[index++] = list.get(i);
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
		for (int i = 0; i < pushed.length; i++) {
			stack.push(pushed[i]);
			while (!stack.isEmpty() && stack.peek() == popped[index]) {
				stack.pop();
				index++;
			}
		}
		return stack.isEmpty();
    }
	
//	/*
//	 * 第3题:
//	 * 剑指 Offer 20. 表示数值的字符串
//	 * 题目:请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
//		来源：力扣（LeetCode）
//		链接：https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
//	 * 思路:
//	 * 		1. 有限状态机:定义各种状态当满足某些条件时进入另外一种状态.最后判断最终状态是否满足要求
//	 * 		2. 不写
//	 */
//    public boolean isNumber(String s) {
//    		Map[] states = {
//                new HashMap<Character,Integer>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
//                new HashMap<Character,Integer>() {{ put('d', 2); put('.', 4); }},                           // 1.
//                new HashMap<Character,Integer>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
//                new HashMap<Character,Integer>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
//                new HashMap<Character,Integer>() {{ put('d', 3); }},                                        // 4.
//                new HashMap<Character,Integer>() {{ put('s', 6); put('d', 7); }},                           // 5.
//                new HashMap<Character,Integer>() {{ put('d', 7); }},                                        // 6.
//                new HashMap<Character,Integer>() {{ put('d', 7); put(' ', 8); }},                           // 7.
//                new HashMap<Character,Integer>() {{ put(' ', 8); }}                                         // 8.
//            };
//            int p = 0;
//            char t;
//            for(char c : s.toCharArray()) {
//                if(c >= '0' && c <= '9') t = 'd';
//                else if(c == '+' || c == '-') t = 's';
//                else if(c == 'e' || c == 'E') t = 'e';
//                else if(c == '.' || c == ' ') t = c;
//                else t = '?';
//                if(!states[p].containsKey(t)) return false;
//                p = (int)states[p].get(t);
//            }
//            return p == 2 || p == 3 || p == 7 || p == 8;
//    }
    
//	/*
//	 * 第4题:
//	 * 剑指 Offer 67. 把字符串转换成整数
//	 * 题目:写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
//	当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
//	该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
//	注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
//	在任何情况下，若函数不能进行有效的转换时，请返回 0。
//		来源：力扣（LeetCode）
//		链接：https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
//	 * 思路:
//	 * 		1. 有限状态机
//	 *      2. 不写
//	 */
//    public int strToInt(String str) {
//        char[] c = str.trim().toCharArray();
//        if(c.length == 0) return 0;
//        int res = 0, bndry = Integer.MAX_VALUE / 10;
//        int i = 1, sign = 1;
//        if(c[0] == '-') sign = -1;
//        else if(c[0] != '+') i = 0;
//        for(int j = i; j < c.length; j++) {
//            if(c[j] < '0' || c[j] > '9') break;
//            if(res > bndry || res == bndry && c[j] > '7') return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//            res = res * 10 + (c[j] - '0');
//        }
//        return sign * res;
//    }
    
	/*
	 * 第5题:
	 * 剑指 Offer 59 - I. 滑动窗口的最大值
	 * 题目:给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
	 * 思路:	
	 * 题意:给定一个数组和滑动窗口k(初始从0下标到k-1下标).每次找到当前窗口最大值然后向后滑动,每次的最大值组成新的数组
	 * 		1. 单调队列:辅助队列的一种,此队列满足线性递增或者递减需求.
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
	
	/*
	 * 理解题意:首先第一个滑动窗口最大值就是当前一个小集合的最大值,但是当他移动后,需要删除最前面的位置,然后将新值添加进这个集合再来找最大值.
	 * 怎么添加新值:首选队列不为空且队列队尾小于当前值,所有小于值poll再加入新值
	 * 怎么删除前面的值:判断删除的值是否是队首元素,是删除队首,否则不做操作
	 */
	Deque<Integer> maxDeque = new LinkedList<>();
    public int[] maxSlidingWindow(int[] nums, int k) {
    	//边界
    	if (nums.length == 0 || k == 0) {
			return new int[0];
		}
    	//创建目标数组
    	int[] res = new int[nums.length - k + 1];
    	//先移动k步获取到滑动窗口大小,push到单调队列
    	for (int i = 0; i < k; i++) {
    		//将前面值依次入队.如果后面值更大,自动会剔除前面值
			push_maxDeque(nums[i]);
		}
    	//获取单调队列队首(目前滑动窗口最大值),加入到数组指定下标,下标移动
    	int index = 0;
    	res[index] = maxDeque.peekFirst();
    	index++;
    	//循环后面的数组,先pop最左边的值,再加入新值,然后获取单调队列队首,加入到指定数组下标
    	for (int i = k; i < nums.length; i++) {
			pop_maxDeque(nums[i-k]);
			push_maxDeque(nums[i]);
			res[index++] = maxDeque.peekFirst();
		}
    	return res;
    }
    public void push_maxDeque(int value) {
    	//单调队列核心点就是往单调队列添加和删除元素的逻辑
    	//1. 添加元素,向队尾添加,且需要循环清除所有队列中小于添加值的元素
    	while (!maxDeque.isEmpty() && maxDeque.peekLast() < value) {
			maxDeque.pollLast();
		}
    	maxDeque.offerLast(value);
	}
    public void pop_maxDeque(int value) {
    	//1. 删除元素,因为添加其实内部有删除逻辑,只需要判断最大值是不是队首,如果是删除队首.
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
	 * 		1. 单调双端队列,并且维护一个正常队列
	 * 		2. 添加值单调队列按照单调队列方式添加,正常队列也需要添加值
	 * 		3. 删除值.首选判断当前正常队列是否存在值,如果存在,我们需要考虑当前poll出来的值是否是单调队列的队首,如果是队首,单调队列也需要pollFirst值
	 * 		    并且返回正常队列的poll(),如果正常队列为空直接返回-1;
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
        	while (!maxDeque.isEmpty() && maxDeque.peekLast() < value) {
				maxDeque.pollLast();
			}
        	normalDeque.offer(value);
        	maxDeque.offerLast(value);
        }
        
        public int pop_front() {
        	if (!normalDeque.isEmpty()) {
	    		//判断当前正常队列的队首是否是最大值队列的队首如果是移除
        		/*
        		 * pop_front中如果用 q.peek() == d.peekFirst() 作判定条件，是不对的。
					用 q.peek().equals(d.peekFirst()) 作判定条件，才是对的。
					因为队列peek()或者peekFirst()返回的对象不是整型，而是Object，所以不能用==，而应该用equals。
        		 */
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
    	StringBuilder builder = new StringBuilder("[");
    	Deque<TreeNode> deque = new LinkedList<TreeNode>();
    	deque.offer(root);
    	while (!deque.isEmpty()) {
			TreeNode node = deque.poll();
			if (node == null) {
				builder.append("null,");
			} else {
				//不为null时才需要你添加左右子树
				builder.append(node.val + ",");
				deque.offer(node.left);
				deque.offer(node.right);
			}
		}
    	//最后多出,
    	builder.deleteCharAt(builder.length() - 1);
    	//重新添加"]"
    	builder.append("]");
        
		return builder.toString();
    }
    public TreeNode deserialize(String data) {
    	//边界
    	if (data.equals("[]")) {
			return null;
		}
    	//去除首尾[]
    	String vaildString = data.substring(1, data.length() - 1);
    	//以,分隔为数组
    	String[] itemStrings = vaildString.split(",");
    	
    	//入队
    	Deque<TreeNode> deque = new LinkedList<TreeNode>();
    	//创建root
    	int index = 0;
    	TreeNode root = new TreeNode(Integer.parseInt(itemStrings[index++]));
    	deque.offer(root);
    	while (!deque.isEmpty()) {
			TreeNode node = deque.poll();
			if (!itemStrings[index].equals("null")) {
				node.left = new TreeNode(Integer.parseInt(itemStrings[index]));
				deque.offer(node.left);
			}
            index++;
			if (!itemStrings[index].equals("null")) {
				node.right = new TreeNode(Integer.parseInt(itemStrings[index]));
				deque.offer(node.right);
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
	 */
    char[] chs;
    List<String> resStrings = new LinkedList<String>();
    public String[] permutation(String s) {
    	this.chs = s.toCharArray();
    	dfs_permutation(0);
		return resStrings.toArray(new String[resStrings.size()]);
    }
    public void dfs_permutation(int fixIndex) {
    	//递归退出条件
    	if (fixIndex == chs.length - 1) {
    		resStrings.add(String.valueOf(chs));
			return;
		}
    	//创建集合遍历非重复递归
    	HashSet<Character> set = new HashSet<Character>();
    	for (int i = fixIndex; i < chs.length; i++) {
			char ch = chs[i];
			if (set.contains(ch)) {
				continue;
			}
			set.add(ch);
			swap_permutation(fixIndex, i);
			dfs_permutation(fixIndex+1);
			swap_permutation(fixIndex, i);
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
	 * 		1. 动态规划
	 */
    public boolean isMatch(String A, String B) {

    	int m = A.length(),n = B.length();
    	boolean[][] dp = new boolean[m+1][n+1];
    	for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (j == 0) {
					if (i == 0) {
						//都是空串代表相等
						dp[i][j] = true;
						continue;
					}
				} else {
					//正常状态方程
					if (B.charAt(j-1) != '*') {
						//最后不是'*'
						if (i >= 1 && (A.charAt(i-1) == B.charAt(j-1) || B.charAt(j-1) == '.')) {
							dp[i][j] = dp[i-1][j-1];
						}
					} else {
						//是'*'
						if (j >= 2) {//数组不越界
							//先取为'*'但是代表0个字符
							dp[i][j] = dp[i][j-2];
						}
						if (i>=1 && j>=2 && (A.charAt(i-1) == B.charAt(j-2) || B.charAt(j-2) == '.')) {
							dp[i][j] = dp[i][j] || dp[i-1][j];
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
	 * 		4. 判断当前n2,n3,n5的最小值.得到dp[i],注意需要每个都使用if判断为什么因为有可能n2,n3相等,此时都需要累加.如果只加一个会出现两次.
	 * 		5. 根据dp[i]得到值反查是哪个n然后移动相应的p指针(p++),开始下一轮循环,
	 */
    public int nthUglyNumber(int n) {
    	int[] dp = new int[n];
    	dp[0] = 1;
    	int p2 = 0,p3 = 0,p5 = 0;
    	for (int i = 1; i < dp.length; i++) {
			int n2 = 2 * dp[p2],n3 = 3 * dp[p3],n5 = 5 * dp[p5];
			dp[i] = Math.min(Math.min(n2, n3), n5);
			if (dp[i] == n2) {
				p2++;
			}
			if (dp[i] == n3) {
				p3++;
			}
			if (dp[i] == p5) {
				p5++;
			}
		}
    	return dp[n-1];
    }
    
	/*
	 * 第10题:
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
    	//创建二维dp数组一维代表投掷次数二维代表点数
    	int[][] dp = new int[n+1][6*n+1];
    	//初始值
    	for (int j = 1;j <= 6; j++) {
			dp[1][j] = 1;
		}
    	//从第二次投掷开始
    	for (int i = 2; i < n + 1; i++) {
    		//从i点开始不是0点开始
			for (int j = i; j < 6 * n + 1; j++) {
				for (int k = 1; k <= 6; k++) {
					if (j < k) {
						continue;
					}
					dp[i][j] += dp[i-1][j-k];
				}
			}
		}
    	double allNum = Math.pow(6, n);
    	//结果数组
    	double[] res = new double[5*n+1];
    	int index = n;//从最小值n开始
    	for (int i = 0; i < res.length; i++) {
			res[i] = dp[n][index++] / allNum;
		}
    	return res;
    }
    
    
	/*
	 * 第11题:
	 * 剑指 Offer 17. 打印从1到最大的n位数
	 * 题目:输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
	 * 思路:
	 * 		规律题:
	 * 		当n为1打印到9为10的一次方-1
	 * 		当n为2打印到99为10的二次方-1
	 * 		当为n打印到99...999为10的n次方-1
	 *		当n为负数为异常
	 */
	public int[] printNumbers(int n) {
		//边界
		if (n == 0) {
			return new int[0];
		}
		int sum = (int)Math.pow(10, n),index = 0;
		int[] res = new int[sum - 1];
		for (int i = 1; i < sum; i++) {
			res[index++] = i;
		}
		return res;
	}
	
	
	/*
	 * 第12题:
	 * 剑指 Offer 51. 数组中的逆序对
	 * 题目:在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
	 * 思路:
	 * 		1. 归并排序
	 * 		原因:归并排序会将数组拆分为最小单元然后合并.合并过程中就能知道逆序对.
	 * 		注意逆序对计算不是累加+=1,而是合并集合内每个满足要求的都是逆序对:
	 * 		如果左右两个集合,左边更小逆序对不变,如果右边更小,逆序对个数就是当前的mid-left+1.
	 * 		举例将9,9,0,5,2,5,8,3先切割再求逆序对,可以发现是这个规律
	 */
	int count;
    public int reversePairs(int[] nums) {
    	//边界
    	if (nums.length == 0) {
			return 0;
		}
    	this.count = 0;
    	//归并排序
    	mergeSort(nums, 0, nums.length - 1);
        return this.count;
    }
	public void mergeSort(int[] arr,int left,int right) {
		if (left < right) {
			int mid = (left + right) /2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid+1, right);
			merge(arr, left, mid, right);
		}
	}
	public void merge(int[] arr,int left,int mid,int right) {
		//每次两两合并
		int[] list = new int[right - left + 1];
		
		int i = left,j = mid + 1,index = 0;
		while (i <= mid && j <= right) {
			if (arr[i] > arr[j]) {//前面大
				list[index++] = arr[j++];
				this.count += mid - i + 1;
			} else {
				list[index++] = arr[i++];
			}
		}
		//合并未合并完成的
		while (i <= mid) {
			list[index++] = arr[i++];
		}
		while (j <= right) {
			list[index++] = arr[j++];
		}
		for (int k = 0; k < list.length; k++) {
			arr[left+k] = list[k];
		}
	}
	/*
	 * 第13题:
	 * 剑指 Offer 14- II. 剪绳子 II
	 * 题目:给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
	答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof
	 * 思路:
	 */
	public int cuttingRope(int n) {
		/*
		 * 贪心:在绳子大于4时,尽可能让绳子3长度一段
		 */
		if (n  < 4) {
			return n - 1;
		}
		int overLength = n;
		long res = 1;
		while (overLength > 4) {
			res = res * 3 % 1000000007;
			overLength -= 3;
		}
		return (int)(res * overLength % 1000000007);
	}
    
	/*
	 * 第14题:
	 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
	 * 题目:输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
	 * 思路:
	 * 		找规律:1出现的次数是1在各个计数位出现的次数之和.
	 * 		举例一个3101592,以100进制位为例,a为592前面的数,b为92,base为100,怎么得到三者.自己列举可以距离31203然后从0开始
	 * 		公式:
	 * 		在百位上此时为5
	 * 		base = 100, a = n / base / 10,b = n % 100
	 * 		以a为3101为例他的范围是0000-3101,也就是a + 1,b为92为例的范围0-99(为什么是99,因为存在cur存在a,所以他能到99)正好是base
	 * 		类比:在千位上此时为1
	 * 		base =1000,此时存在两种情况,
	 * 			一种a是000-309此时为a不需要+1 此时b是0-999此时为base.
	 * 			另一种是:如果a为310此时a的取值个数是1时此时b只有0-592,此时b的取值是b+1
	 * 			合并起来就是(a* base) + 1 * (b+1)
	 * 		类比:在万位上,此时为0
	 * 		base = 10000,此时a的变化范围为0-30,b的变化范围为0-9999.这是因为当前位的值比1小此时为0所以当把他固定为1时,如果前面a数字最多取到30.此时对应b对应就是0-9999
	 * 		a * base
	 * 		总结当前cur位分为三种情况一种<1,一种等于1一种>1都需要分析.
	 * 		
	 */
    public int countDigitOne(int n) {
    	/*
    	 * 将其转化为当前位是否比1大,比1小,或者等于1.每种情况都有多少种情况
    	 * 		
    	 * 然后从个位开始一直到最高位结束累加就是所有为1的次数
    	 * 
    	 * 注意:base可能越界用long声明
    	 */
    	int times = 0;
    	long base = 1;
    	while (base <= n) {
			int a = (int)(n/base/10);
			int b = (int)(n%base);
			int cur = (int)(n/base%10);
			if (cur == 1) {
				times += (a * base) + 1 * (b+1);
			} else if (cur > 1) {
				times += (a + 1) * base;
			} else {
				times += a * base;
			}
			base *= 10;
		}   	
    	return times;
    }
    
	/*
	 * 第15题:
	 * 剑指 Offer 44. 数字序列中某一位的数字
	 * 题目:数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
请写一个函数，求任意第n位对应的数字。
		来源：力扣（LeetCode）
		链接：                                                                                                                                                                                                                                                                                                                                                  
	 * 思路:
	 * 		1. 找规律:
	 * 			1-9   	位数和是1,数字数量是1*9为9,    	位数的数量是1*1*9为9
	 * 			10-99,	位数和是2,数字数量是10*9为90,	位数的数量是2位总共数字10*9为180
	 * 			100-999,位数和是3,数字数量是100*9为900,	位数的数量是3位总共100*9为2700
	 * 		2. 根据n从1-9依次相减得到n所在指定的位数的n
	 * 		3. 根据剩余的n,求出此时的num为start+(n-1)/digits
	 * 		4. 根据剩余的n,求出此时的num的下标index:(n-1)%digits.
	 * 		5. 根据num和index求出此时的具体位置的数字
	 */
    public int findNthDigit(int n) {
    	/*
    	 * 列出1-9,10-99,100-999,当前范围,数字个事,以及字符个数.
    	 * 公式:当前数字为:start + (n - 1) /digits
    	 * 当前数字位数为(n - 1) % digits
    	 */
    	if (n == 0) {
			return 0;
		}
    	//初始设置数字为1,位数为1
    	long start = 1;int digits = 1;
    	while (n > start * digits * 9) {
			n -= start * digits * 9;
			start *=  10;
			digits += 1;
		}
    	//此处拿到在哪个数字上比如15,333等
    	long num = start + (n - 1) / digits;
    	//找到哪个数具体哪一位
    	int index = (n - 1) % digits;
    	//上面已经拿到inde,num为字符串,我们直接用num转字符串取下标得到字符然后-'0',得到整形ascii码
		return Long.toString(num).charAt(index) - '0';
    }
}
