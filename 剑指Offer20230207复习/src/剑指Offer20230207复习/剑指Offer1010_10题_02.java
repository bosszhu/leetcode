package 剑指Offer20230207复习;

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
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
	 */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
    	//从右下或者左上开始查找.边界是当前二维数组边界查找完成.如果大于,或者小于直接移动下标,等于返回true,全部查找完成未找到返回false
    	//边界
    	if (matrix == null || matrix.length == 0) {
			return false;
		}
    	int i = 0,j = matrix[0].length - 1;
    	//在下标范围内
    	while (i <= matrix.length - 1 && j >=0) {	
			if (matrix[i][j] > target) {
				j--;
			} else if (matrix[i][j] < target) {
				i++;
			} else {
				return true;
			}
		}
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
	 * 		2. 循环执行条件不含=因为low值需要比high小才继续循环,不存在low和hight为同一个值时是目标值
	 * 		3. 分为三种情况,当前mid数组的值和high比较,
	 * 			1. 大于high,low左移可以取+1,
	 * 			2. 小于high,high右移不能-1(因为-1就可能到最大值去了)
	 * 			3. 等于high,代表后面都是相等此时left前移也相等high = high -1,缩小范围.
	 * 		4. 返回值为low的下标对应的值.
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
	 */
    
    public int minArray(int[] numbers) {
    	//边界,必须存在这个旋转数组才能开始查找
    	if (numbers.length == 0 || numbers == null) {
    		//没有最小值
			return -1;
		}
    	int low = 0,high = numbers.length - 1;
    	//不存在left=hgih进入循环,因为不存在low和high相等时,找到最小的目标值
    	while (low < high) {
			int mid = (low + high)/2;
			if (numbers[mid] > numbers[high]) {
				//右移动low,并且目标值一定是在mid+1闭区间
				low = mid + 1;
			} else if (numbers[mid] < numbers[high]) {
				//左移high,此时high不能等于mid-1,mid-1可能变成上段上升数组最大值
				high = mid;
			} else {
				//相等时,可能存在多个重复元素,所以high需要-1再次循环
				high = high - 1;
			}
		}
    	//最终返回值,low此时应该已经+1,直接返回结果
    	return numbers[low];
    }
	
	/*
	 * 第3题:
	 * 剑指 Offer 50. 第一个只出现一次的字符
	 * 题目:在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
	 * 思路:
	 * 		1. hashMap存字符以及出现次数
	 * 		2. 队列(先进先出),当出现一次加入队列.否则找到相同字符移除队头元素
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
	 */

    public char firstUniqChar(String s) {
    	/*hash和队列,出现一次存入队列,再次出现key中的value置为-1,
    	当前队列不为空,且根据map获取队列顶端元素对应的value是否是-1,如果是移除队列*/
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	Deque<Character> deque = new LinkedList<Character>();
    	for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (map.containsKey(ch)) {
				//再次遇见,map更改为-1代表重复出现
				map.put(ch,-1);
				//循环判断当前队列是否为空且栈顶的字符是否为-1
				while (!deque.isEmpty() && map.get(deque.peek()) == -1) {
					deque.poll();
				}
			} else {
				//第一次遇见,加入map,入队
				map.put(ch, 1);
				deque.offer(ch);
			}
		}
		//最终根据队列是否为空判断第一次出现一次的字符是否存在
		return deque.isEmpty() ? ' ' : deque.peek();
    }
    
	/*
	 * 第4题:
	 * 剑指 Offer 32 - I. 从上到下打印二叉树
	 * 题目:从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
	 * 思路:
	 * 		1. 层序遍历,从左到右依次打印每个节点的值
	 * 		2. 队列.
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
	 */
    public int[] levelOrder_I(TreeNode root) {
    	/*
    	 * 数据类型:Deque,Arraylist转new int[];
    	 * 1. 边界判断
    	 * 2. 队列,将root入队
    	 * 3. 循环当队列不为空时,先拿出队首元素,然后向队列内加入左右子树,直到队列为空
    	 * */
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
    	//list转int[]
    	int index = 0;
    	int[] res = new int[list.size()];
    	for (int i = 0; i < list.size() ; i++) {
			res[index++] = list.get(i);
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
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
	 */
    public List<List<Integer>> levelOrder_II(TreeNode root) {
    	/*
    	 * 1. 创建二维数组
    	 * 2. 层序遍历时确定当前node内部的size并且临时变量存储,循环size取得每一层数组
    	 * 3. 返回二维数组
    	 * */
    	List<List<Integer>> list = new ArrayList();
    	if (root == null) {
			return list;
		}
    	Deque<TreeNode> deque = new LinkedList<>();
    	deque.offer(root);
    	while (!deque.isEmpty()) {
    		int levelSize = deque.size();
    		List<Integer> levelList = new ArrayList<Integer>();
    		for (int i = 0; i < levelSize; i++) {
    			TreeNode node = deque.poll();
    			levelList.add(node.val);
    			if (node.left != null) {
    				deque.offer(node.left);
    			}
    			if (node.right != null) {
    				deque.offer(node.right);
    			}
			}
    		list.add(levelList);
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
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
	 */
    public List<List<Integer>> levelOrder_III(TreeNode root) {
       	/*
    	 * 1. 创建二维数组
    	 * 2. 层序遍历时确定当前node内部的size并且临时变量存储,循环size取得每一层数组
    	 * 3. 返回二维数组
    	 * 4. 准备flag值每次取反,然后根据flag值入队列
    	 * */
    	List<List<Integer>> list = new ArrayList();
    	if (root == null) {
			return list;
		}
    	Deque<TreeNode> deque = new LinkedList<>();
    	deque.offer(root);
    	boolean flag = true;
    	while (!deque.isEmpty()) {
    		int levelSize = deque.size();
    		Deque<Integer> levelList = new LinkedList<Integer>();
    		for (int i = 0; i < levelSize; i++) {
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
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/binary-tree-preorder-traversal/
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
    	/*
    	 * 前序遍历:NLR
    	 * 		1. 先遍历根节点,再遍历左右子树
    	 * 		2. 栈结构存储,当当前栈不为空,或者当前节点不为空,while循环
    	 * 		3. 当前节点不为空时,加入栈,左子树遍历
    	 * 		4. 拿到栈顶节点,右子树遍历
    	 */
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if (root == null) {
			return list;
		}
    	//创建栈
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	//临时变量存当前根节点
    	TreeNode temp = root;
    	while (!stack.isEmpty() || temp != null) {
			//存入栈
    		while (temp != null) {
    			//遍历左子树
    			stack.push(temp);
    			list.add(temp.val);
    			temp = temp.left;
			}
    		//拿出栈顶元素,遍历右子树
    		TreeNode node = stack.pop();
    		temp = node.right;
		}
    	return list;
    }
    
	/*
	 * 第8题:
	 * 94. 二叉树的中序遍历
	 * 题目:给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
	 * 思路:
	 * 		1. 同前序遍历类似,但是添加数组的时机需要在左侧全部转移完后才开始
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/binary-tree-inorder-traversal/
	 */
    public List<Integer> inorderTraversal(TreeNode root) {
    	/*
    	 * 中序遍历:LNR
    	 */
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if (root == null) {
			return list;
		}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode temp = root;
    	while (!stack.isEmpty() || temp != null) {
			while (temp != null) {
				stack.push(temp);
				temp = temp.left;
			}
			TreeNode node = stack.pop();
			list.add(node.val);
			temp = node.right;
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
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/binary-tree-postorder-traversal/
	 */
    public List<Integer> postorderTraversal(TreeNode root) {
    	/*
    	 * 后序遍历:LRN
    	 * 		注意访问左右子树怎么避免重复访问,方案:后续遍历需要增加一个前置节点用来保存刚刚遍历的节点,然后
    	 * 		判断是否右子树有值且没被访问过(不等于前置节点代表没被访问过才需要遍历右子树).
    	 * 		如果被访问过代表当前位叶子节点,直接加到数组,并且前置节点赋值为当前节点,当前节点赋值为null
    	 */
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if (root == null) {
			return list;
		}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode temp = root;
    	TreeNode preNode = null;
    	while (!stack.isEmpty() || temp != null) {
			while (temp != null) {
				stack.push(temp);
				temp = temp.left;
			}
			TreeNode popNode = stack.pop();
			if (popNode.right != null && popNode.right != preNode) {
				stack.push(popNode);
				temp = popNode.right;
			} else {
				//访问叶子节点
				list.add(popNode.val);
				preNode = popNode;
				temp = null;
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
	 * 			2. 当前A树中的节点为null,但是B不是null,代表不是子结构
	 * 			3. 当前A树中的节点值!= B的值.代表不是子结构
	 * 			4. 递归判断sonA和B的左子树和右子树是否满足子结构需求.
	 * 
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/
	 */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
    	/*
    	 * 前序遍历A的每个子节点,然后判断每个子节点是否是B的一模一样的结构
    	 * 怎么判断是否是一样的结构:递归
    	 * 		1. 当B为null的时候代表递归退出得到是相同
    	 * 		2. 当A为nullB不为null代表不是相同
    	 * 		3. 当当前节点A的值和B的值不相等代表不是相同
    	 * 		4. 递归遍历A子节点左子树和右子树
    	 * 
    	 */
    	//边界值
    	if (A == null || B == null) {
			return false;
		}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode temp = A;
    	while (!stack.isEmpty() || temp != null) {
			while (temp != null) {
				stack.push(temp);
				if (isSubStructure_son(temp, B)) {
					return true;
				}
				temp = temp.left;
			}
			TreeNode popNode = stack.pop();
			temp = popNode.right;
		}
    	return false;
    }
    public boolean isSubStructure_son(TreeNode sonA,TreeNode B) {
    	if (B == null) {
			return true;
		}
    	if (sonA == null && B != null) {
			return false;
		}
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
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
	 */
    public TreeNode mirrorTree(TreeNode root) {
    	/*
    	 * 输出镜像,既左右子树以及左右子树的子节点相互交换.
    	 * 使用层序遍历
    	 */
    	if (root == null) {
			return root;
		}
    	Deque<TreeNode> deque = new LinkedList<TreeNode>();
    	deque.offer(root);
    	while (!deque.isEmpty()) {
			TreeNode node = deque.poll();
			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
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
	 * 
	 * 	 	来源：力扣（LeetCode）
	 * 		链接：https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
	 */
    public boolean isSymmetric(TreeNode root) {
    	//边界
    	if (root == null) {
			return true;
		}
    	return checkSymmetryDuichen(root, root);
    }
    public boolean checkSymmetryDuichen(TreeNode node1,TreeNode node2) {
    	//注意判断是否对称的三个条件
    	/*
    	 * 采用将左右节点同时入队列,相当于拆成两个,然后左节点左子树,右节点右子树.左节点右子树以及右节点左子树.是否相同
    	 * 1. 都为null此时循环继续
    	 * 2. 一个null一个不为null,返回false
    	 * 3. 节点值不相等返回false
    	 * 4. 将第一个节点的左子树第二节点右子树入队,将第一个节点右子树第二个节点左子树入队,
    	 * 5. 所有遍历完成返回true
    	 */
    	Deque<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(node1);
    	queue.offer(node2);
    	//层序遍历
    	while (!queue.isEmpty()) {
			node1 = queue.poll();
			node2 = queue.poll();
			if (node1 == null && node2 == null) {
				//退出此次循环
				continue;
			}
			if (node1 == null || node2 == null) {
				return false;
			}
			if (node1.val != node2.val) {
				return false;
			}
			//将子节点对称入队
			queue.offer(node1.left);
			queue.offer(node2.right);
			queue.offer(node1.right);
			queue.offer(node2.left);
		}
    	return true;
    }
}
