package 剑指Offer20230207复习;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class 剑指Offer1010_10题_06 {
	/*
	 * 第1题:
	 * 剑指 Offer 15. 二进制中1的个数
	 * 题目:编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
	 * 思路:
	 *		1. 位运算.当前位 (n & 1)如果当前位为1得到1,否则为0;
	 */
    public int hammingWeight(int n) {
    	/*
    	 * 从最后一位怎么判断是1 &1为1代表是1,否则为0
    	 * 然后右移动一位java中右移一位 >>>= 1
    	 */
    	int res = 0;
    	while (n != 0) {
			res += (n & 1);
			n >>>= 1;
		}
    	return res;
    }
	
	
	/*
	 * 第2题:
	 * 剑指 Offer 65. 不用加减乘除做加法
	 * 题目:写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
	 * 思路:
	 * 		1. 位运算.
	 * 				与:	0 & 0 = 0,0 & 1 = 0,1 & 1 = 1;
	 * 				或:	0 | 0 = 0,0 | 1 = 1,1 | 1 = 1;
	 * 				异或:0 ^ 0 = 0,0 ^ 1 = 1, 1 ^ 1 = 0; (两者为异为1否则为0)
	 * 		2. 此处加法怎么用上位运算:
	 * 				1. 观察发现无进位和和异或运算规律相同.有进位时三种情况都满足
	 * 				2. 进位运算和与运算规律相同并且需要左移一位.无进位时三种情况都满足.
	 */
    public int add(int a, int b) {
    	/*
    	 * 1. 先算进位,再算非进位
    	 * 进位 c = (a & b) << 1
    	 * 非进位 a ^= b
    	 * b重新赋值为c
    	 * 最后返回值为a
    	 */
    	while (b != 0) {
    		//先算进位和
			int c = (a & b) << 1;
			a = a ^ b;
			b = c;
		}
    	return a;
    }
	
	/*
	 * 第3题:
	 * 剑指 Offer 56 - I. 数组中数字出现的次数
	 * 题目:一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
	 * 思路:
	 * 		1. 位运算.
	 * 		规律:任何相等数字异或为0.
	 * 		2. 遍历数组内的值与z=0异或.得到的结果就是只出现一次的两个数字异或结果
	 * 		3. 拿到异或结果后怎么得到实际的两个值?
	 * 			1. 我们根据异或特性x,y中至少有一个数字的二进制是包含1的.
	 * 			2. 用临时变量m=1&z.知道找到1时退出循环.否则m左移(继续与高位&运算),直到找到这个不同位.
	 * 			3. 再遍历数组与此m值&运算.有一个值必然在==0之中一个在==1之中,此时x,y分割为两个数组.再通过x,y=0对两个数组进行异或就得到需要的值.(相等的值被异或掉了)
	 * 			
	 */
    public int[] singleNumbers(int[] nums) {
    	//边界
    	if (nums.length == 0) {
			return new int[0];
		}
    	//先临时变量z初始值为0,异或nums内部元素值,将相同的值去掉
    	int z = 0;
    	for (int i : nums) {
			z ^= i;
		}
    	//再去临时变量m初始值为1,&异或结果知道找到不同1位置
    	int m = 1;
    	while ((z & m) == 0) {
			m <<= 1;
		}
    	
    	//根据不同1将数组分成两部分并对内部元素进行异或得到两个不同值
    	int x = 0,y = 0;
    	for (int i : nums) {
			if ((m & i) == 0) {
				x ^= i;
			} else {
				y ^= i;
			}
		}
    	return new int[] {
    			x,y
    	};
    }
    
	/*
	 * 第4题:
	 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
	 * 题目:在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
	 * 思路:
	 * 		1. map统计每个数字出现的次数
	 */
    public int singleNumber(int[] nums) {
    	HashMap<Integer, Integer> hashMap = new HashMap<>();
    	for (int i : nums) {
			if (!hashMap.containsKey(i)) {
				hashMap.put(i, 1);
				continue;
			}
			hashMap.put(i, hashMap.get(i) + 1);
		}
    	//遍历map
        for(HashMap.Entry<Integer,Integer> entry:hashMap.entrySet()){
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}
    	return -1;
    }
    
	/*
	 * 第5题:
	 * 剑指 Offer 39. 数组中出现次数超过一半的数字
	 * 题目:数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。你可以假设数组是非空的，并且给定的数组总是存在多数元素。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
	 * 思路:
	 * 		1. 摩尔投票法:什么意思?其实就是求众数.
	 * 		2. 遍历数组统计投票数
	 * 			1. 当投票数为0是x假设为众数(可能后续-1会被替换掉),
	 * 			2. 当后续与这个值不同时投票数-1,相等时投票数+1.
	 * 		3. 验证众数是否存在.不存在返回异常-1
	 * 		
	 * 
	 */
    public int majorityElement(int[] nums) {
    	/*
    	 * 先将众数设置为数组第0个元素,当遇见不同值--,遇见相同值++,当投票数归0,重新获取众数,
    	 * 验证是否是众数.
    	 */
    	int x = 0, vote = 0;
    	for (int i : nums) {
			if (vote == 0) {
				x = i;
			}
			if (x == i) {
				vote++;
			} else {
				vote--;
			}
		}
    	//判断是否是众数
    	int count = 0;
    	for (int i : nums) {
			if (x == i) {
				count++;
			}
		}
    	return count > nums.length/2 ? x:-1;
    }
    
    
	/*
	 * 第6题:
	 * 剑指 Offer 66. 构建乘积数组
	 * 题目:给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof
	 * 思路:
	 * 		1. 动态规划.将数组分为两个三角部分,创建两个动态规划数组.
	 * 		2. 创建leftDp和rightDp数组.
	 * 		3. 初始值leftDp[0] = rightDp[length-1] = 1;
	 * 		4. leftDp的状态方程: dp[i] = dp[i-1] * a[i-1],从1开始
	 * 		5. rightDp的状态方程:dp[i] = dp[i+1] * a[i+1]:参考最后一个初始值dp[length-1-1] = dp[length -1] * a[length -1]
	 * 		6. 遍历数组长度将左右dp数组内的值相乘
	 */
    public int[] constructArr(int[] a) {
    	/*
    	 * 动态规划:举例表格分区
    	 * 注意求数组处自身位置以外的乘积.将数组以自身为边界分成两半,算出左半部分乘积再乘以有半部分乘积
    	 * int[] leftChildDp,int[] rightChildDp:大小??初始值一个从0开始一个从length-1开始都为1.
    	 * left动态规划从1开始到length结束,状态方程:dp[i] = dp[i-1] * a[i-1]
    	 * right动态规划从length-1-1开始,到>=0结束,状态方程:dp[i] = dp[i+1] * a[i+1];
    	 * 最后算两者乘积得到目标数组
    	 */
    	//边界
    	if (a.length == 0 || a == null) {
			return new int[0];
		}
    	//创建dp两个子数组
    	int[] leftDp = new int[a.length];
    	int[] rightDp = new int[a.length];
    	leftDp[0] = 1;rightDp[a.length - 1] = 1;
    	//左边数组动态规划方程从1开始
    	for (int i = 1; i < leftDp.length; i++) {
			leftDp[i] = leftDp[i-1] * a[i-1];
		}
    	//右边数组动态规划方程从length-1-1开始
    	for (int i = rightDp.length - 1 - 1; i >= 0; i--) {
			rightDp[i] = rightDp[i+1] * a[i+1];
		}
    	int index = 0;
    	for (int i = 0; i < a.length; i++) {
			a[index++] = leftDp[i] * rightDp[i];
		}
    	return a;
    }
    
	/*
	 * 第7题:
	 * 剑指 Offer 14- I. 剪绳子
	 * 题目:给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/jian-sheng-zi-lcof
	 * 思路:
	 * 		1. 贪心:根据规律可得尽可能将绳子长度切割为3所得到的的乘积最大.所以思路为尽可能将绳子分隔为3.
	 * 		2. 异常边界,n < 4时,直接返回n-1
	 * 		3. 开始循环当剩余长度>4时一直执行循环	
	 * 		4. 注意res = res * 3 % 1000000007;因为可能在取模之前相乘已经越界不能使用res *= 3 % 1000000007;
	 * 		5. 为什么能开始就取模,然后最后一次再取模根据公式得:(a * b) % c=((a % c) * b) % c.所以成立
	 * 		6. 最后要再转换为int.
	 */
    public int cuttingRope(int n) {
    	/*
    	 * 贪心算法:
    	 * 尽可能剪成3的倍数,此时乘积最大,注意循环条件<4时,2*2大于3*1所以不需要继续剪断
    	 * 
    	 */
    	if (n < 4) {
			return n - 1;
		}
    	int overLength = n;
    	long res = 1;
    	while (n > 4) {
			res = res * 3 % 1000000007;
			overLength -= 3;
		}
    	return (int)(res * overLength % 1000000007);
    }
    
	/*
	 * 第8题:
	 * 剑指 Offer 57 - II. 和为s的连续正数序列
	 * 题目:输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
	序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
	 * 思路:
	 * 		1. 滑动窗口,注意左右值移动和加减的顺序,画图理解.
	 * 		2. 确定窗口大小默认low为1,high为2,sum为两者之和
	 * 		3. left < high代表不越界,循环一直执行
	 * 			1. 当前sum > target代表需要缩小,此时需要先减去当前low,low再++,
	 * 			2. 当前sum < target需要需要扩大,此时需要high++.high再++,
	 * 			3. 当等于时,加入集合创建数组加入集合内,此时low++继续开始判断(注意此时移动low,否则low还会再次计算);
	 */
    public int[][] findContinuousSequence(int target) {
    	/*
    	 * 注意low先减旧值,再移动low,而high先移动再++;以及找到目标数组需要左移动窗口
    	 */
    	//边界条件
    	if (target <= 2) {
			return new int[0][0];
		}
    	List<int[]> list = new ArrayList<int[]>();
    	int low = 1,high = 2, sum = 3;
    	while (low < high) {
			if (sum > target) {
				sum -= low;
				low++;
			} else if (sum < target) {
				high++;
				sum += high;
			} else {
				int[] items = new int[high - low + 1];
				int index = 0;
				for (int i = low; i <= high; i++) {
					items[index++] = i;
				}
				list.add(items);
				
				//移动
				sum -= low;
				low++;
			}
		}
    	return list.toArray(new int[list.size()][]);
    }
    
    
	/*
	 * 第9题:
	 * 剑指 Offer 62. 圆圈中最后剩下的数字
	 * 题目:0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
	例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
	 * 思路:
	 * 		1. 转换为链表但是链表删除效率太慢,转换成数组查下标
	 * 		2. 创建数组将n从0开始存入数组
	 * 		3. 初始化下标为0,创建下标当当前数组长度>1时循环继续
	 * 			1. 举例从0到m的位置(数组第几个月元素,需要-1得到下标,所以就是index+m-1)%当前数组长度.以题目为例删除第三个数字删除的第一个数是2,就是(0 + 3 - 1) % 5 = 2
	 * 			2. 返回数组的值
	 */			
    public int lastRemaining(int n, int m) {
    	/*
    	 * 约瑟夫环,但是链表效率太慢,转化为数组下标,注意下标持续变化,且删除的位置可能大于当前数组的size(因为大小在不断删除)
    	 * 核心:index从0开始index每次循环index = (index + m - 1)%list.size();
    	 */
    	//将n转化为数组
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	for (int i = 0; i < n; i++) {
			list.add(i);
		}
    	//将数组下标转化为环的下标
    	int index = 0;
    	while (list.size() > 1) {
			index = (index + m - 1) % list.size();
			list.remove(index);
		}
    	return list.get(0);
    }
}
