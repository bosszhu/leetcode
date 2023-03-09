package 剑指Offer1010_10题_01;

import java.time.Year;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 剑指Offer1010_10题_02 {
	/*
	 * 第1题:
	 * 剑指 Offer 04. 二维数组中的查找
	 * 题目:在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	 * 思路:
	 *		1. 右上或左下标志数.已右上或者左下为起始位置,与当前target判断,以左下为例,如果target大,证明在右边,反之在上面
	 *		2. 循环判断条件是以左下为起始位置越界还没找到
	 */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
    	if (matrix == null || matrix.length ==0) {
			return false;
		}
    	int i = 0,j = matrix[0].length - 1;//确定左下位置下标
    	while (i <= matrix.length - 1 && j >= 0) {//循环继续条件
			if (matrix[i][j] > target) {
				j--;
			} else if (matrix[i][j] < target) {
				i++;
			} else {
				return true;
			}
		}
    	//查找完成还未找到
    	return false;
    }
	
	
	/*
	 * 第2题:
	 * 剑指 Offer 11. 旋转数组的最小数字
	 * 题目:把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。

给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。  

注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
	 * 思路:
	 * 		1. 二分查找.画坐标图来确定
	 * 		2. 循环执行条件不含=因为low值肯定要比high小才继续循环
	 * 		3. 分为三种情况,当前mid数组的值和high比较,
	 * 			1. 大于high,low左移可以取+1,
	 * 			2. 小于high,high右移不能-1(因为-1就可能到最大值去了)
	 * 			3. 等于high,代表后面都是相等此时left前移也相等high = high -1,缩小范围.
	 * 		4. 返回值为low的下标对应的值.
	 */
    
    public int minArray(int[] numbers) {
    	int low = 0,high = numbers.length - 1;
    	while (low < high) {
			int mid = (high + low)/2;
			if (numbers[mid] > numbers[high]) {
				low = mid + 1;//可以+1取到的mid必不是最小值,+1才可能为最小
			} else if (numbers[mid] < numbers[high]) {
				high = mid;//不能继续-1(可能会到达最高值初)
			} else {
				high = high - 1;//high可以缩小high-1,而不是根据mid缩小
			}
		}
    	return numbers[low];
    }
	
	/*
	 * 第3题:
	 * 剑指 Offer 50. 第一个只出现一次的字符
	 * 题目:在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
	 * 思路:
	 * 		1. hashMap存字符以及出现次数
	 * 		2. 队列(先进先出),当出现一次加入队列.否则找到相同字符移除队头元素
	 */

    public char firstUniqChar(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        Deque<Character> deque = new LinkedList<>();
    	//遍历取字符串
        for (int i = 0; i < s.length(); i++) {
			Character character = s.charAt(i);
			if (hashMap.containsKey(character)) {
				hashMap.put(character, -1);
				while (!deque.isEmpty() && hashMap.get(deque.peek()) == -1) {
					deque.poll();
				}
			} else {
				hashMap.put(character, 1);//出现一次
				deque.offer(character);//加入队列
			}
		}
    	return deque.isEmpty() ? ' ' : deque.peek();
    }
    
	/*
	 * 第4题:
	 * 剑指 Offer 32 - I. 从上到下打印二叉树
	 * 题目:从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
	 * 思路:
	 * 		1. 层序遍历,从左到右依次打印每个节点的值
	 * 		2. 队列.
	 */
    public int[] levelOrder_I(TreeNode root) {
    	if (root == null) {
    		return new int[0];
		}
    	Deque<TreeNode> deque = new LinkedList<>();
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	deque.offer(root);
    	while (!deque.isEmpty()) {
			TreeNode node = deque.poll();
			list.add(node.val);
			if (node.left != null) {
				deque.offer(node.left);
			}
			if (node.right != null) {
				deque.offer(node.right);
			}
		}
    	//java没办法直接转化
    	int size = list.size();
    	int[] res = new int[size];
    	for (int i = 0; i < size; i++) {
			res[i] = list.get(i);
		}
    	return res;
    }
    
	/*
	 * 第5题:
	 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
	 * 题目:从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
	 * 思路:
	 * 		1. 层序遍历打印成二维数组
	 * 		2. 队列
	 */
    public List<List<Integer>> levelOrder_II(TreeNode root) {
    	List<List<Integer>> list = new ArrayList<>();
    	if (root == null) {
			return list;
		}
    	Deque<TreeNode> deque = new LinkedList<>();
    	deque.offer(root);
    	while (!deque.isEmpty()) {
    		List<Integer> leveList = new ArrayList<Integer>();//每一层
    		int size = deque.size();//临时变量保存
    		for (int i = 0; i < size; i++) {
				TreeNode node = deque.poll();
				leveList.add(node.val);
				if (node.left != null) {
					deque.offer(node.left);
				}
				if (node.right != null) {
					deque.offer(node.right);
				}
			}
    		list.add(leveList);
		}
    	return list;
    }
    
    
	/*
	 * 第6题:
	 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
	 * 题目:请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
	 * 思路:	 
	 * 		1. 层序遍历,第一层左到右,第二层右到左依次类推
	 * 		2. 队列
	 */
    public List<List<Integer>> levelOrder_III(TreeNode root) {
    	List<List<Integer>> list = new ArrayList<>();
    	if (root == null) {
			return list;
		}
    	Deque<TreeNode> deque = new LinkedList<>();
    	boolean flag = true;
    	deque.offer(root);
    	while (!deque.isEmpty()) {
    		Deque<Integer>levelList  = new LinkedList<>();//用双端队列
    		int size = deque.size();//临时变量保存
    		for (int i = 0; i < size; i++) {
				TreeNode node = deque.poll();
				if (flag) {
					levelList.offerLast(node.val);
				} else {
					levelList.offerFirst(node.val);
				}
				if (node.left != null) {
					deque.offer(node.left);
				}
				if (node.right != null) {
					deque.offer(node.right);
				}
			}
    		list.add(new LinkedList<Integer>(levelList));
    		flag = !flag;
		}
    	return list;
    }
    
	/*
	 * 第7题:
	 * 144. 二叉树的前序遍历
	 * 题目:
	 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
	 * 思路:
	 * 		1. 前序遍历:先遍历根节点再遍历左右子树
	 * 		2. 栈结构,当栈不为空或者当前节点不为空继续循环(这个条件如果忘记基本就写不出来)
	 * 		3. 当前节点不为空,取值,加入栈,向左转移
	 * 		4. 拿到栈顶节点,向右转移
	 */
    public List<Integer> preorderTraversal_recursion(TreeNode root) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if (root == null) {
			return list;
		}
    	preorderTraversal_recursion_detail(root, list);
    	return list;
    }
    public void preorderTraversal_recursion_detail(TreeNode node,List<Integer> res) {
    	res.add(node.val);
    	preorderTraversal_recursion_detail(node.left,res);
    	preorderTraversal_recursion_detail(node.right,res);
    }
    public List<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if (root == null) {
    		return list;
		}
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode tempNode = root;
    	while (!stack.isEmpty() || tempNode != null) {//栈不为空,或者当前节点不为null
			while (tempNode != null) {
				list.add(tempNode.val);
				stack.push(tempNode);
				tempNode = tempNode.left;
			}
			TreeNode popNode = stack.pop();
			tempNode = popNode.right;
		}
    	return list;
    }
    
	/*
	 * 第8题:
	 * 94. 二叉树的中序遍历
	 * 题目:给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
	 * 思路:
	 * 		1. 同前序遍历类似,但是添加数组的时机需要在左侧全部转移完后才开始
	 */
    public List<Integer> inorderTraversal(TreeNode root) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if (root == null) {
    		return list;
		}
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode tempNode = root;
    	while (!stack.isEmpty() || tempNode != null) {//栈不为空,或者当前节点不为null
			while (tempNode != null) {
				stack.push(tempNode);
				tempNode = tempNode.left;
			}
			TreeNode popNode = stack.pop();
			list.add(popNode.val);
			tempNode = popNode.right;
		}
    	return list;
    }
    
    
	/*
	 * 第9题:
	 * 145. 二叉树的后序遍历
	 * 题目:给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
	 * 思路:
	 * 		1. 后续遍历:
	 * 		2. 栈机构:
	 * 		3. 有难点注意点:
	 * 			1. 注意访问左子树右子树,怎么避免重复访问,后续遍历需要增加一个前置节点用来保存刚刚遍历的节点.
	 * 	
	 */
    public List<Integer> postorderTraversal(TreeNode root) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if (root == null) {
			return list;
		}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = root;
    	TreeNode pre = null;
    	while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			TreeNode popNode = stack.pop();
			if (popNode.right != null && popNode.right != pre) {//是否右子树有值且没有被访问过
				//访问右子树一直向右
				stack.push(popNode);
				node = popNode.right;
			} else {
				//当前为叶子节点
				list.add(popNode.val);
				//前置节点赋值(标记右子树已经遍历过)
				pre = popNode;
				//node节点赋值(防止继续遍历左子树)
				node = null;
			}
		}
    	return list;
    }
    
    
	/*
	 * 第10题:
	 * 剑指 Offer 26. 树的子结构
	 * 题目:输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构).B是A的子结构， 即 A中有出现和B相同的结构和节点值。
	 * 思路:
	 * 		1. 判空操作
	 * 		2. 遍历A树每一个节点.判断是否是子结构.
	 * 			如何判断是否是树的子结构.递归判断
	 * 			1. 递归退出条件.当前B树节点为null,是子结构
	 * 			2. 当前A树中的节点为null,代表不是子结构
	 * 			3. 当前A树中的节点值!= B的值.代表不是子结构
	 * 			4. 递归判断sonA和B的左子树和右子树是否满足子结构需求.
	 * 
	 */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
    	if (A == null || B == null) {
			return false;
		}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode node = A;
    	while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				if (isSubStructure_son(node, B)) {
					return true;//判断当前节点是否完全匹配B节点
				}
				node = node.left;
			}
			TreeNode popNode = stack.pop();
			node = popNode.right;
		}
    	return false;
    }
    public boolean isSubStructure_son(TreeNode sonA,TreeNode B) {
    	if (B == null) {//递归返回值
			return true;
		}
    	//一个为空一个不为空
    	if (sonA == null && B != null) {
			return false;
		}
    	//值不相等
    	if (sonA.val != B.val) {
			return false;
		}
		return isSubStructure_son(sonA.left, B.left) && isSubStructure_son(sonA.right, B.right);
	}
    
    
	/*
	 * 第11题:
	 * 剑指 Offer 27. 二叉树的镜像
	 * 题目:请完成一个函数，输入一个二叉树，该函数输出它的镜像。
	 * 思路:
	 * 		1. 理解什么是镜像.
	 * 		2. 层序遍历交换左右子树,入队.
	 * 		
	 */
    public TreeNode mirrorTree(TreeNode root) {
    	if (root == null) {
			return root;
		}
    	Deque<TreeNode> deque = new LinkedList<TreeNode>();
    	deque.offer(root);
    	while (!deque.isEmpty()) {
			TreeNode node = deque.poll();
			TreeNode tempNode = node.left;
			node.left = node.right;
			node.right = tempNode;
			if (node.left != null) {
				deque.offer(node.left);
			}
			if (node.right != null) {
				deque.offer(node.right);
			}
		}
    	return root;
    }
    
    
	/*
	 * 第12题:
	 * 剑指 Offer 28. 对称的二叉树
	 * 题目:请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
	 * 思路:
	 * 		1. 将根节点两次入队.层序遍历
	 * 		2. 两次出队.
	 * 			判断出队的两个节点.
	 * 			1. 都为null继续循环.
	 * 			2. 一个null一个不为null,返回flase.
	 * 			3. 节点的值不相等返回false.
	 * 			4. 将第一个节点的左子树和第二个节点的右子树入队.将第一个节点的右子树和第二个节点的左子树入队.
	 * 			5. 所有值遍历完成返回true
	 */
    public boolean isSymmetric(TreeNode root) {
    	if (root == null) {
			return true;
		}
    	return checkSymmetryDuichen(root, root);
    }
    public boolean checkSymmetryDuichen(TreeNode node1,TreeNode node2) {
    	Deque<TreeNode> deque = new LinkedList<TreeNode>();
    	deque.offer(node1);
    	deque.offer(node2);
    	while (!deque.isEmpty()) {
			node1 = deque.poll();
			node2 = deque.poll();
			//判断两个节点是否是对称的
			if (node1 == null && node2 == null) {
				continue;
			}
			if (node1 == null || node2 == null) {//必定有一个不为null所以可以直接这样判断
				return false;
			}
			if (node1.val != node2.val) {
				return false;
			}
			deque.offer(node1.left);
			deque.offer(node2.right);
			deque.offer(node1.right);
			deque.offer(node2.left);
		}
    	return true;
    }
}
