package 剑指Offer20230207复习;

import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.border.Border;

public class 剑指Offer1010_10题_04 {
	/*
	 * 第1题:
	 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
	 * 题目:输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
	 * 思路:
	 * 		1. 双指针	,当i从头开始,j从尾部开始,当i为奇数,j为偶数,移动下标,此时结束当前次循环,否则直接交换i,j.然后又会进入前面i,j的退出当前次循环.
	 * 		2. 当前面是奇数,或者后面是偶数,移动双指针位置,退出当前循环
	 * 		3. 当来到这里是,只可能是前面不是奇数且后面不是偶数,交换两者位置.再次执行循环即可.
	 *
	 */
    public int[] exchange(int[] nums) {
    	if (nums.length == 0) {
			return nums;
		}
    	int i = 0,j = nums.length -1;
    	while (i < j) {
			if (i % 2 == 1) {
				i++;
				continue;
			}
			if (j % 2 == 0) {
				j--;
				continue;
			}
			//循环走到此时i,j必然前面不是奇数后面不是偶数重新进入循环
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
    	return nums;
    }
	
	
	/*
	 * 第2题:
	 * 剑指 Offer 57. 和为s的两个数字
	 * 题目:输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/
	 * 思路:
	 * 		1. 双指针.
	 * 		2. 位置:一个最前一个最后,当两者位置内元素之和大于目标值.缩小最后下标,反之缩小最前下标,直到找到相加和为target的值,直接返回包含此两个值的数组.
	 */
    public int[] twoSum(int[] nums, int target) {
    	if (nums.length == 0) {
			return new int[0];
		}
    	int i = 0,j = nums.length - 1;
    	while (i < j) {
			if (nums[i] + nums[j] < target) {
				i++;
			} else if (nums[i] + nums[j] > target) {
				j--;
			} else {
				return new int[] {
						nums[i],nums[j]
				};
			}
		}
    	return new int[0];
    }
	
	/*
	 * 第3题:
	 * 剑指 Offer 58 - I. 翻转单词顺序
	 * 题目:输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof
	 * 思路:
	 * 		1. 双指针:双指针位置都从最后位置开始
	 * 		2. 先去除头尾空格.
	 * 		3. 直到尾部下标为0之前一直循环
	 * 		4. 移动一个指针直到空格结束.截取此段长度内的字符串.添加空格
	 * 		5. 继续将双指针移动到空格位置之后,开始循环操作.
	 * 		6. 注意最后字符串去除头尾空格
	 */
    public String reverseWords(String s) {
    	if (s.length() == 0) {
			return s;
		}
    	//先去头尾空格
    	String vaildString = s.trim();
    	//创建新单词
    	StringBuilder newStringBuild = new StringBuilder();
    	//取指针两个都指向尾结点
    	int i = vaildString.length() - 1,j = i;
    	while (i >= 0) {
    		//注意数组越界
			while (i>=0 && vaildString.charAt(i) != ' ') {
				i--;
			}
			//截取单词
			newStringBuild.append(vaildString.substring(i+1, j+1) + ' ');
			//截取到空格位置
			while (i>=0 && vaildString.charAt(i) == ' ') {
				i--;
			}
			j = i;
		}
    	
    	return newStringBuild.toString().trim();
    }
    
	/*
	 * 第4题:
	 * 剑指 Offer 12. 矩阵中的路径是否与字符串匹配
	 * 题目:给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
		单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof
	 * 思路:
	 * 		1. DFS+回溯.
	 * 		2. 遍历网格没一个字符使其为起点开始和word字符进行比较.
	 * 		3. DFS判断是否匹配word:
	 * 			递归退出条件:
	 * 			1. 边界值退出.i,j越界或者为负数
	 * 			2. 当前位置字符不等于当前word位置字符.不符合退出.
	 * 			3. 符合要求退出:当前k值等于当前word长度-1.
	 * 			
	 * 			判断上下左右位置是否可以继续比较(相等),在递归之前需要标记此处的访问值为特殊字符'#'代表访问过后面保证后面不会再次匹配.当递归结束后置为原有值.
	 */
    public boolean exist(char[][] board, String word) {
    	//遍历每一个节点
    	for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (dfs_exist(board, word.toCharArray(), i, j, 0)) {
					return true;//找到此节点返回true
				}
			}
		}
    	return false;//否则返回没找到
    }
    public boolean dfs_exist(char[][] board, char[] word,int i, int j,int k) {
    	//递归退出条件,
    	//边界时退出
    	if (i<0 || j <0 || i >= board.length || j >= board[0].length) {
			return false;
		}
    	//字符不相等退出
    	if (board[i][j] != word[k]) {
			return false;
		}
    	//符合要求退出
    	if (word.length - 1 == k) {
			return true;
		}
    	//递归其他路径
    	//注意递归之前要先将当前位置置为特殊字符
    	char temp = board[i][j];
    	board[i][j] = '#';
    	Boolean  resBoolean = dfs_exist(board, word, i+1, j, k+1) || dfs_exist(board, word, i, j+1, k+1)
    						||dfs_exist(board, word, i-1, j, k+1) || dfs_exist(board, word, i, j-1, k+1);
    	//递归结束以后将当前位置字符还原
    	board[i][j] = temp;
    	return resBoolean;
	}
    
    
	/*
	 * 第4.1题:
	 * 面试题13. 机器人的运动范围
	 * 题目:地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
	 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
	 * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，
	 * 因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
	 * 思路:
	 * 		1. 创建二维数组
	 * 			1. 递归(条件类似)
	 * 			2. 队列(条件类似)
	 * 		2. 递归他的位数和
	 * 			1. 递归边界
	 * 			2. 大于目标值
	 * 			3. 已经被访问过格子,return 0
	 * 			4. 标记当前格子被访问
	 * 			5. 递归其他位置
	 */
    /*
	 * 思路:
	 * 		1. 创建二维数组
	 * 			1. 递归(条件类似)
	 * 			2. 队列(条件类似)
	 * 		2. 递归他的位数和
	 * 			1. 递归边界
	 * 			2. 大于目标值
	 * 			3. 已经被访问过格子,return 0
	 * 			4. 标记当前格子被访问
	 * 			5. 递归其他位置
	 */
    int m,n,k;
    boolean [][] visit;
    public int movingCount(int m, int n, int k) {
    	//传递文件全局变量,初始boolean二维数组
    	this.m = m;
    	this.n = n;
    	this.k = k;
    	this.visit = new  boolean[m][n];
    	//从0,0开始
    	int res = dfs_movingCount(0,0);
    	return res;
    }
    public int dfs_movingCount(int i,int j) {
    	//递归退出条件
    	//数组越界
    	if (i >= m || j >= n) {
			return 0;
		}
    	//位数和超过
    	if (digitSum(i, j) > k) {
			return 0;
		}
    	//被访问过
    	if (visit[i][j] == true) {
			return 0;
		}
    	//标记是否被访问
    	visit[i][j] = true;
    	//递归其他路径
    	int res = dfs_movingCount(i + 1, j) + dfs_movingCount(i, j + 1) + 1;
		return res;
	}
    //求位数之和的函数(参数二维数组i,j的下标)
    public int digitSum(int i,int j) {
    	int sum = 0;
    	while (i > 0) {
			sum += (i % 10);
			i /= 10;
		}
    	while (j > 0) {
			sum += (j % 10);
			j /= 10;
		}
		return sum;
	}
	/*
	 * 第5题:
	 * 剑指 Offer 34. 二叉树中和为某一值的路径
	 * 题目:给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
		叶子节点 是指没有子节点的节点。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
	 * 思路:
	 * 		1. dfs回溯与剪枝
	 * 		1. 先序遍历注意退出条件
	 * 		2. 注意技巧,dfs算法我们传入实际的target.然后让其根据走过的节点自减.
	 * 		3. 将节点中的值加入到队列内,如果发现目标集合,直接转数组返回.
	 * 				1. 当前target自减为0,且当前节点是叶子节点.
	 * 		4. 继续递归左右子节点
	 * 		//找到已经添加没找到删除上一条路径继续查找
	 * 		5. 删除上一条路径
	 */
    LinkedList<List<Integer>> res = new LinkedList<>();
    Deque<Integer> path = new LinkedList<Integer>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
    	/*
    	 * dfs+回溯剪枝
    	 * 1. 使用先序遍历
    	 * 2. 注意dfs算法我们已经传入实际节点.
    	 * 
    	 */
    	dfs_pathSum(root, target);
    	return res;
    }
    public void dfs_pathSum(TreeNode node,int target) {
    	if (node == null) {
			return;
		}
    	//target递归计算
    	target -= node.val;
    	//加入路径
    	path.add(node.val);
    	//递归结果查询
    	if (target == 0 && node.left == null && node.right == null) {
			res.add(new LinkedList<Integer>(path));
		}
    	//递归下一个左右子树
    	dfs_pathSum(node.left, target);
    	dfs_pathSum(node.right, target);
    	//回溯
    	path.removeLast();
	}
    
	/*
	 * 第6题:
	 * 剑指 Offer 36. 二叉搜索树与双向链表
	 * 题目:输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
	 * 思路:
	 * 		1. 中序遍历:根据二叉搜索树切分为最小单元.我们需要得到排序链表所以使用中序遍历
	 * 		2. 怎么链接双向循环中的循环链表(即头尾怎么连接,头的left指针指向尾,尾的right指针指向头,其他内部已经转化),注意最终return head代表头结点
	 * 		3. 中序遍历中组装数据:
	 * 				以2,3,4二叉搜索树为例子
	 * 				1. 如果还未进行转化:尾部tail节点为空.head = node.代表2进入
	 * 				2. 如果已经开始转化:tail.right = node,node.left = tail.代表3进入.
	 * 				3. 尾结点需要移动到新节点
	 * 				4. 开始遍历右子树.
	 * 		
	 */
    Node head,tempTail;//tempTail会移动在组合链表时代表前一个节点
    public Node treeToDoublyList(Node root) {
    	/*
    	 * 我们需要得到排序链表:中序遍历
    	 * 1. 递归内双向链表
    	 * 		中序递归,先递归左子树到叶子节点.然后开始组装,然后递归右子树到叶子节点
    	 * 		组装中如果tail==null代表刚开始组装head = node,然后tail = node代表上一个节点,
    	 * 		最后再来判断tail.right = node.node.left = tail.再次移动tail
    	 * 2. 怎么链接双向循环中的循环链表,头的left指向尾,尾的right指向头.
    	 * 
    	 */
    	if (root == null) {
			return null;
		}
    	head = null;tempTail = null;
    	dfs_treeToDoublyList(root);
    	head.left = tempTail;
    	tempTail.right = head;
    	//组成双向循环
		return head;
    }
    public void dfs_treeToDoublyList(Node node) {
    	//递归退出条件
    	if (node == null) {
			return;
		}
    	//中序遍历递归左子树
    	dfs_treeToDoublyList(node.left);
    	
    	//拼接双向链表
    	if (tempTail == null) {
			//代表第一个节点
    		head = node;
		} else {
			//代表后面节点.tail刚刚底下已经赋值为前一个node节点(是前一个节点)
			tempTail.right = node;
			node.left = tempTail;
		}
    	//移动队尾指针tail赋值
    	tempTail = node;

    	//中序遍历递归右子树
    	dfs_treeToDoublyList(node.right);
    }
	/*
	 * 第7题:
	 * 剑指 Offer 54. 二叉搜索树的第k大节点
	 * 题目:给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
	 	来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
	 * 思路:
	 * 		1. 了解二叉搜索树,中序遍历是排序遍历
	 * 		2. 全部变量count赋值为k,last_k_value代表倒数第k个节点的值初始值为-1代表k超过搜索树所有节点大小时找不到.
	 * 		3. 注意是第k个大的节点.需要从大到小开始排序,所以需要中序遍历变种(RNL)而不是LNR.
	 */
    int count,last_k_value;
    public int kthLargest(TreeNode root, int k) {
    	if (root == null) {
			return 0;
		}
    	this.count = k;
    	this.last_k_value = -1;
    	dfs_kthLargest(root);
    	return this.last_k_value;
    }
    public void dfs_kthLargest(TreeNode node) {
    	//递归退出条件
    	if (node == null) {
			return;
		}
    	//中序从右往左代表大到小遍历
    	dfs_kthLargest(node.right);
    	this.count--;
    	//判断递归满足退出条件(count == 0代表经历k次遍历)
    	if (this.count == 0) {
			this.last_k_value = node.val;
			return;
		}
    	dfs_kthLargest(node.left);
	}
    
	/*
	 * 第8题:
	 * 剑指 Offer 45. 把数组排成最小的数
	 * 题目:输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
	 	来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
	 * 思路:
	 * 		1. 数学规律: mn > nm则m>n,mn < nm则m<n,mn = nm则m=n.
	 * 		2. 根据这个规律我们可以将数字转化为字符串,然后比较字符串的大小.最小的值往前放
	 * 		代码:
	 * 		1. 将数字数组转成字符串数组(x+y)代表们mn,(y+x)代表nm.
	 */
    public String minNumber(int[] nums) {
    	/*
    	 * mn < nm,m<n
    	 * 将数字数组每个值转为字符串,然后比较字符串集合(x,y)规则为(x+y)和(y+x)比较
    	 * 得到从下到大的字符排序数组.得到字符串
    	 */
    	//创建字符串数组
    	String[] strings = new String[nums.length];
    	//将数组数组遍历逐步加入每个字符串数组中
    	for (int i = 0; i < strings.length; i++) {
			strings[i] = String.valueOf(nums[i]);
		}
    	//比较大小排序
    	Arrays.sort(strings, (x,y) -> (x+y).compareTo(y+x));
    	StringBuilder newBuilder = new StringBuilder();
    	for (String string : strings) {
			newBuilder.append(string);
		}
    	return newBuilder.toString();
    }
    
    
	/*
	 * 第9题:
	 * 剑指 Offer 61. 扑克牌中的顺子
	 * 题目:从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof
	 * 思路:
	 * 		1. 数学规律:将题目理解为数学含义:不管有无大小王,数组内一定没有重复数字(怎么保证),数组内最大值减去最小值一定是小于5.
	 * 		2. 怎么保证集合没有重复值.需要使用java类Set,HashSet自带判断
	 * 		3. 遍历每一个值:如果是0直接退出循环不考虑.如果新加值,集合内含有返回false.然后加入集合中.
	 * 		4. 退出循环后判断集合内的最大值和最小值只差是否小于5
	 * 		
	 */
    public boolean isStraight(int[] nums) {
    	/*
    	 * 转化为数学并且利用set集合判断重复出现
    	 * 循环遍历当前数组
    	 * 1. 如果当前元素是0,退出当前次循环
    	 * 2. 如果当前集合存在当前元素,直接返回false代表出现重复元素
    	 * 3. 将元素加入集合
    	 * 循环结束后判断集合内的最大值和最小值之差是否<5
    	 */
    	HashSet<Integer> set = new HashSet<Integer>();
    	for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				//大小王继续循环不用判断
				continue;
			}
			if (set.contains(nums[i])) {
				//有重复值
				return false;
			}
			set.add(nums[i]);
		}
    	return Collections.max(set) - Collections.min(set) < 5;
    }
}
