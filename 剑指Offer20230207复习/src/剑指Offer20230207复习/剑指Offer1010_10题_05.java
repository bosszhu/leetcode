package 剑指Offer20230207复习;

import java.util.ArrayList;
import java.util.HashMap;

import javax.security.auth.kerberos.KerberosKey;

public class 剑指Offer1010_10题_05 {
	/*
	 * 第1题:
	 * 剑指 Offer 40. 最小的k个数(集合)
	 * 题目:输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
	 * 思路:
	 * 		1. 小顶堆
	 *
	 */
    public int[] getLeastNumbers(int[] arr, int k) {
    	if (arr.length < k) {//越界
			return new int[0];
		}
    	int[] newArr = new int[k];
    	heapSort(arr, newArr);
    	return newArr;
    }
    public void heapSort(int[] arr,int[] newArr) {
    	//调整堆结构
    	for (int i = ((arr.length - 1) - 1)/2; i >= 0; i--) {
			adjust_small_heapSort(arr, i, arr.length - 1);
		}
    	//遍历到从length-1到k指定到位置
    	int index = 0;
    	for (int i = arr.length - 1; i > arr.length - newArr.length - 1; i--) {
			swap(arr, 0, i);
			//从堆顶调整
			adjust_small_heapSort(arr, 0, i-1);
			newArr[index++] = arr[i];
		}
	}
    public void adjust_small_heapSort(int[] arr,int parent,int last_index) {
    	int min = parent;
    	int lChild = parent * 2 + 1;
    	int rChild = parent * 2 + 2;
    	//子节点下标不要越界是可以等于的
    	if (lChild <= last_index && arr[lChild] < arr[min]) {
			min = lChild;
		}
    	if (rChild <= last_index && arr[rChild] < arr[min]) {
			min = rChild;
		}
    	if (min != parent) {
			swap(arr, min, parent);
			//子元素调整已经交换了
			adjust_small_heapSort(arr, min, last_index);
		}
	}
	public void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	/*
	 * 第2题:
	 * 剑指 Offer 41. 数据流中的中位数
	 * 题目:如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
	 * 思路:
	 * 		1. 大顶堆和小顶堆结合.
	 * 		2. 准备全局变脸大顶堆和小顶堆.
	 * 		3. MedianFinder实例化
	 * 		4. 添加时需要判断当前添加是奇数还是偶数(当大顶堆size和小顶堆size相同代表新添加的是奇数),奇数时向小顶堆中添加元素.否则向大顶堆中添加.
	 * 				怎么向大顶堆或者小顶堆中添加新元素?先加其加入另一个堆中,然后改变另一个堆中元素直到满足堆结构.然后剔除堆顶元素,加入需要添加的堆中.
	 * 		5. 怎么求中位数:如果大小顶堆size相等,代表总数据流是偶数个数值.(大顶堆堆顶+小顶堆堆顶)/2,如果是奇数直接取小顶堆堆顶元素.
	 * 
	 */

    class MedianFinder {
    	ArrayList<Integer> bigHeap;
    	ArrayList<Integer> smallHeap;
        public MedianFinder() {
        	this.bigHeap = new ArrayList<Integer>();
        	this.smallHeap = new ArrayList<Integer>();
        }
        
        public void addNum(int num) {
        	if (bigHeap.size() == smallHeap.size()) {
        		//先加入小顶堆当大顶堆元素等于小顶堆时,加入小顶堆
        		//怎么加入小顶堆?先加入大顶堆取大顶堆堆顶加入小顶底
				bigHeap.add(num);
				//调整大顶堆
        		for (int i = (bigHeap.size() - 1 - 1) /2; i >= 0; i--) {
					adjust_big_small_heap(bigHeap, i, bigHeap.size() - 1, true);
				}
        		//取堆顶值,删除小顶堆顶堆堆顶
        		int temp = bigHeap.remove(0);
        		//再次调整大顶堆(因为删除了顶堆)
         		for (int i = (bigHeap.size() - 1 - 1) /2; i >= 0; i--) {
    				adjust_big_small_heap(bigHeap, i, bigHeap.size() - 1, true);
    			}
        		//加入到小顶堆
         		smallHeap.add(temp);
         		//调整小顶堆
         		for (int i = (smallHeap.size() - 1 - 1) /2; i >= 0; i--) {
    				adjust_big_small_heap(smallHeap, i, smallHeap.size() - 1, false);
    			}
			} else {
				//否则加入大顶堆
				//怎么加入大顶堆?先加入小顶堆取小顶堆堆顶加入大顶堆
				smallHeap.add(num);
				//调整小顶堆
        		for (int i = (smallHeap.size() - 1 - 1) /2; i >= 0; i--) {
					adjust_big_small_heap(smallHeap, i, smallHeap.size() - 1, false);
				}
        		//删除小堆顶
        		int temp = smallHeap.remove(0);
        		//再次调整小顶堆
        		for (int i = (smallHeap.size() - 1 - 1) /2; i >= 0; i--) {
					adjust_big_small_heap(smallHeap, i, smallHeap.size() - 1, false);
				}
        		//加入大顶堆
        		bigHeap.add(temp);
        		//调整大顶堆
           		for (int i = (bigHeap.size() - 1 - 1) /2; i >= 0; i--) {
           			adjust_big_small_heap(bigHeap, i, bigHeap.size() - 1, true);
           		}
			}
        }
        
        public double findMedian() {
        	double temp = 0;
        	if (bigHeap.size() == 0 && smallHeap.size() == 0) {
				return temp;
			}
        	//当大顶堆和小顶堆相等时直接取两个平均值
        	if (bigHeap.size() == smallHeap.size()) {
        		temp = (smallHeap.get(0) + bigHeap.get(0))/(double)2;
			} else {
				//如果小顶堆多一个直接返回小顶堆
				temp = smallHeap.get(0);
			}
        	return temp;
        }
        
        public void adjust_big_small_heap(ArrayList<Integer> arr,int parent,int last_index,boolean flag) {
    		int temp = parent;
    		int lChild = parent * 2 + 1;
    		int rChild = parent * 2 + 2;
    		if (flag == true) {
        		if (lChild <= last_index && arr.get(lChild) < arr.get(temp)) {
        			temp = lChild;
        		}
        		if (rChild <= last_index && arr.get(rChild) < arr.get(temp)) {
        			temp = rChild;
        		}
			} else {
        		if (lChild <= last_index && arr.get(lChild) > arr.get(temp)) {
        			temp = lChild;
        		}
        		if (rChild <= last_index && arr.get(rChild) > arr.get(temp)) {
        			temp = rChild;
        		}
			}

    		if (temp != parent) {
    			//交换
    			swap(arr, temp, parent);
    			//调整
    			adjust_big_small_heap(arr, temp, last_index,flag);
    		}
		}
		public void swap(ArrayList<Integer> list,int i,int j) {
			Integer temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);
		}
    }
	
	/*
	 * 第3题:
	 * 剑指 Offer 55 - I. 二叉树的深度
	 * 题目:输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
	 * 思路:
	 * 		方法1:
	 * 		1. 递归
	 * 		方法2:
	 * 		层序遍历数层数
	 * 		
	 */
    public int maxDepth(TreeNode root) {
    	if (root == null) {
			return 0;
		}
    	return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    
	/*
	 * 第4题:
	 * 剑指 Offer 55 - II. 平衡二叉树
	 * 题目:输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/
	 * 思路:
	 * 		1. 递归
	 * 		2. 结合深度计算
	 * 		
	 * 		
	 */
    public boolean isBalanced(TreeNode root) {
    	if (root == null) {
			return true;
		}
    	boolean curRes = Math.abs(maxDepth(root.left)- maxDepth(root.right)) <= 1;
    	return curRes && isBalanced(root.left) && isBalanced(root.right);
    }
    
	/*
	 * 第5题:
	 * 剑指 Offer 64. 求1+2+…+n
	 * 题目:求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/qiu-12n-lcof/
	 * 思路:
	 * 		1. 短路效应:就是前面的条件执行完成会已经知道整个条件的结果,会直接退出条件.
	 * 			if(A && B)当A是falseB不会执行,A为trueA后面的条件一定会执行
	 * 			if(A || B)当A是trueB不会执行 
	 * 		2. 规定不能用乘除所以只能用加减.
	 * 		3. 怎么使用短路效应:
	 * 		A为n>1注意java要拿变量接收这个结果,B为n += sumNums(n-1) > 0,代表n>1时会一直执行后面n += sumNum(n-1) > 0 的这个递归条件,条件内已经计算了n
	 */
    public int sumNums(int n) {
    	/*
    	 * 不用加减乘除做累加1+..n
    	 * 短路效应,A && B,A为tureB一定会执行,A为falseB一定不会执行.
    	 * A为n>1,B为n += sum(n-1) > 0为真会一直执行,直到n大于1位置前面为假退出
    	 */
    	Boolean A = n >  1;
    	Boolean B = (n += sumNums(n-1)) > 0;
    	boolean res = A && B;
    	return n;
    }
    
    
	/*
	 * 第6题:
	 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
	 * 题目:给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
	百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof
	 * 思路:
	 * 		1. 什么是二叉搜索树???
	 * 			所有节点的左子树都小于当前节点的值,右子树都大于当前节点的值.
	 * 		2. 递归
	 * 		3. 递归思路:判断p,q节点的值是否都小于左边和右边.然后递归找当前节点的左右子树,直到找到.
	 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	if (root == null) {
			return root;
		}
    	if (root.val > p.val && root.val > q.val) {
			return lowestCommonAncestor(root.left, p, q);
		}
    	if (root.val < p.val && root.val < q.val) {
			return lowestCommonAncestor(root.right, p, q);
		}
    	//p,q一个大于root一个小于root代表在异侧
    	return root;
    }
    
	/*
	 * 第7题:
	 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
	 * 题目:给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
	百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof
	 * 思路:
	 * 		1. 根据二叉搜索树的最近祖先节点变种.
	 * 		2. 判断p,q是否都在左边,判断p,q是否都在右边.
	 * 		3. 否则返回根节点
	 * 		4. 注意递归退出条件有所改变,当前节点为空或者当前节点等于p,q节点直接返回当前节点
	 */
    public TreeNode lowestCommonAncestor_II(TreeNode root, TreeNode p, TreeNode q) {
    	if (root == null || root == p || root == q) {
			return root;
		}
    	TreeNode lNode  = lowestCommonAncestor(root.left, p, q);
    	TreeNode rNode  = lowestCommonAncestor(root.right, p, q);
    	if (lNode == null) {
			return rNode;
		}
    	if (rNode == null) {
			return lNode;
		}
    	//在左子树l和右子树r都找不到,代表在异侧
    	return root;
    }
    
	/*
	 * 第8题:
	 * 剑指 Offer 07. 重建二叉树
	 * 题目:输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
	假设输入的前序遍历和中序遍历的结果中都不含重复的数字
	 	来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
	 * 思路:
	 * 		1. 全局变量保存前序,中序遍历数组
	 * 		2. 全局变量将中序遍历的index和值保存到map中
	 * 		3. 构造递归创建二叉树的函数:返回值:当前树,参数1:当前前序遍历的得到的根节点下标,参数2:中序遍历得到的左边界,参数3:中序遍历得到的右边界
	 * 		内部实现:
	 * 			1. 递归退出条件:左边界>右边界返回null
	 * 			2. 根据前序遍历的根节点下标,通过中序遍历保存的map查找到当前的前序遍历的根节点所在index
	 * 			3. 创建二叉树,传根节点
	 * 			4. 递归创建左子树:新的参数是前序遍历当前根节点index+1(左子树根节点),left(左边界),中序遍历当前根节点index - 1(右边界);
	 * 			5. 确定左子树大小:中序遍历当前根节点index - left
	 * 			6. 递归创建右子树:新的参数:前序遍历当前根节点index+左子树大小+1,中旬遍历当前根节点+1(右子树左边界),rigt(右子树右边界)
	 * 			7. 最后返回root.
	 * 			
	 */
    
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> inorderMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if (preorder.length == 0) {
			return null;
		}
    	this.preorder = preorder;
    	this.inorder = inorder;
    	this.inorderMap = new HashMap<Integer, Integer>();
    	//组装map
    	for (int i = 0; i < inorder.length; i++) {
			inorderMap.put(inorder[i], i);//值为key下标为value
		}
		return dfs_constructionTreeNode(0, 0, inorder.length - 1);
    }
    public TreeNode dfs_constructionTreeNode(int preIndex_root,int inorderLeft,int inorderRight) {
    	/*
    	 * 注意理解前序遍历下标只跟前序遍历和左子树size有关,中序遍历下标只跟中序遍历和size有关
    	 */
    	//递归退出条件(左边界大于右边界)
    	if (inorderLeft > inorderRight) {
			return null;
		}
    	int rootValue = preorder[preIndex_root];
    	int inIndex_root = inorderMap.get(rootValue);
    	TreeNode root = new TreeNode(rootValue);
    	root.left = dfs_constructionTreeNode(preIndex_root+1, inorderLeft, inIndex_root-1);
    	int leftSize = inIndex_root - inorderLeft;
    	root.right = dfs_constructionTreeNode(preIndex_root+leftSize+1, inIndex_root+1, inorderRight);
		return root;
	}
    
	/*
	 * 第9题:
	 * 剑指 Offer 16. 数值的整数次方
	 * 题目:实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
	 * 思路:
	 * 		1. 快速幂:幂函数分两种一种是n>0,增大,一种是n小于0.要转化同一种方式处理中.x<0时怎么转化
	 * 		2. 异常判断当前x是0直接返回
	 * 		3. 转化当前n为long类型,判断转化后小于0,直接x = 1/x,power取正数.x的-2次方等于(1/x)的2次方
	 * 		4. 统计循环计算处理:初始值double类型res.当当前的power也就是指数大于0时.循环存在
	 * 			循环内:
	 * 				1. 当幂为奇数提前多乘一次:当前指数%2==1代表是奇数.res 需要多乘一次
	 * 				2. 底数平方,指数搜小一半.
	 * 					
	 */
    public double myPow(double x, int n) {
    	//数字为0无意义
    	if (x == 0) {
			return 0;
		}
    	long power =  n;
    	//幂小于0转化
    	if (power < 0) {
			x = 1/x;
			power = -power;
		}
    	//开始累乘
    	double res = 1.0;
    	while (power > 0) {
			if (power % 2 == 1) {
				res *= x;
			}
			x *= x;
			power /= 2;
			
		}
    	return res;
    }
    
	/*
	 * 第10题:
	 * 剑指 Offer 33. 二叉搜索树的后序遍历序列(判断该数组是不是某二叉搜索树的后序遍历结果)
	 * 题目:输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
	 * 思路:
	 * 		1. 后续遍历序列数组最后一个值为当前树的根节点小于左子树大于右子树
	 * 		2. 创建递归函数.
	 * 			1. 递归退出条件,left越界退出代表满足要求
	 * 			2. 临时变量index保存left.先遍历左子树与根节点比较比根节点值小.index右移
	 * 			3. 当上面循环退出找到当前mid为右子树的第一个节点.遍历右子树比较比根节点值大,index右移
	 * 			4. 储存当前index右移最终值是否等于右边界的标记值
	 * 			5. 递归当前标记值&&左子树满足要求,且右子树满足要求.返回结果
	 */
    public boolean verifyPostorder(int[] postorder) {
    	if (postorder.length == 0) {
			return true;
		}
		return dfs_verifyPostorder(postorder, 0, postorder.length - 1);
    }
    public boolean dfs_verifyPostorder(int[] postorder,int left,int right) {
    	//递归退出条件(最后也没发现不满足就是满足)
    	if (left > right) {
			return true;
		}
    	int index = 0;
    	while (postorder[index] < postorder[right]) {
			index++;
		}
    	int mid = index;
    	while (postorder[index] > postorder[right]) {
			index++;
		}
    	boolean res = index == right;
    	return res && dfs_verifyPostorder(postorder, left, mid) && dfs_verifyPostorder(postorder, mid+1, right - 1);
    }
}
