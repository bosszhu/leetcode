package 剑指Offer1010_10题_01;


public class 剑指Offer1010_10题_08 {
	/*
	 * 第1题:
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
		if (n <= 0) {
			return new int[0];
		}
		int max = (int)Math.pow(10, n) - 1,index = 0;
		int[] res = new int[max];
		for (int i = 1; i <= res.length; i++) {
			res[index++] = i;
		}
		return res;
	}
	
	
	/*
	 * 第2题:
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
    	this.count = 0;
    	mergeSort(nums, 0, nums.length - 1);
        return count;
    }
	public void mergeSort(int[] arr,int left,int right) {
		int mid = left + ((right - left) >> 1);
		if (left < right) {
			//分
			mergeSort(arr, left, mid);
			mergeSort(arr, mid+1, right);
			merge(arr, left, mid, right);
		}
	}
	public void merge(int[] arr,int left,int mid,int right) {
		int[] temp = new int[right - left + 1];
		int i = left,j = mid + 1,t = 0;
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[t++] = arr[i++];
			} else {
                //用来统计逆序对的个数
                count += (mid - i + 1);
				temp[t++] = arr[j++];
			}
		}
		while (i <= mid) {
			temp[t++] = arr[i++];
		}
		while (j <= right) {
			temp[t++] = arr[j++];
		}
		
        //把新数组中的数覆盖arr数组
        for (int k = 0; k < temp.length; k++) {
            arr[k + left] = temp[k];
        }
	}
	/*
	 * 第3题:
	 * 剑指 Offer 14- II. 剪绳子 II
	 * 题目:给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
	答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof
	 * 思路:
	 */
	public int cuttingRope(int n) {
		if (n < 4) {
			return n - 1;
		}
		long res = 1;
		int overLength = n;
		while (overLength > 4) {
			res = (res * 3 % 1000000007);//防止越界
			overLength -= 3;
		}
		return (int)(res * overLength % 1000000007);
	}
    
	/*
	 * 第4题:
	 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
	 * 题目:输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
	 * 思路:
	 * 		找规律:忽略吧,暂时不重要
	 * 		1. 当数字分为当前位cur,当前位的前面位a,当前位的后面位b,以及当前是哪个进制位base(从1开始代表个位).
	 * 		2. 根据当前位cur可以分为三种情况
	 * 			1. cur>1,此时前面的数字1的和为a+1,后面的数字和为base
	 * 			2. cur<1,此时前面的数字1的和为a,后面的数字和为base(因为把cur看成1以后前面取不到a这个值会超过当前数字)
	 * 			3. cur=1,又分为两种情况:最后结果是a*base+b+1
	 * 				1. 从1到a-1此时次数是a,b的次数是base
	 * 				2. 当为a时此时前面出现的次数是1,此时b为0到b中出现的次数为b+1
	 */
    public int countDigitOne(int n) {
    	long base = 1;//代表从个位数开始计算(注意因为*10可能越界)
    	int res = 0;//代表出现1的次数初始为0
    	while (base <= n) {
    		//切割为两个部分
			int b = (int) (n % base);//当前位后面的数字
			int a = (int) (n / base);
			int cur = a % 10;//当前位
			a /= 10;//当前位前面的数字
			if (cur > 1) {
				res += ((a + 1) * base);
			} else if (cur < 1) {
				res += (a * base);
			} else {
				res += (a * base + b + 1);
			}
			//base需要*10
			base *= 10;
		}
    	return res;
    }
    
	/*
	 * 第5题:
	 * 剑指 Offer 44. 数字序列中某一位的数字
	 * 题目:数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
请写一个函数，求任意第n位对应的数字。
		来源：力扣（LeetCode）
		链接：https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
	 * 思路:
	 * 		1. 找规律:
	 * 			1-9   	位数和是1,数字数量是1*9为9,    	位数的数量是1*1*9为9
	 * 			10-99,	位数和是2,数字数量是10*9为90,	位数的数量是10*9*2为180
	 * 			100-999,位数和是3,数字数量是100*9为900,	位数的数量是10*3*9为180
	 * 		2. 根据n从1-9依次相减得到n所在指定的位数的n
	 * 		3. 根据剩余的n,求出此时的num为start+(n-1)/digits
	 * 		4. 根据剩余的n,求出此时的num的下标index:(n-1)%digits.
	 * 		5. 根据num和index求出此时的具体位置的数字
	 */
    public int findNthDigit(int n) {
    	if (n == 0) {//边界值
			return 0;
		}
    	//起始值
    	long start = 1;int digits = 1;
    	while (n > start * digits * 9) {//还需要继续相减
			n -= start * digits * 9;
			start *= 10;//start*10
			digits += 1;//位数+1
		}
    	//确定当前的num
    	long num = start + (n - 1) / digits;
    	int index = (n - 1) % digits;
    	//将num转为字符串
    	return Long.toString(num).charAt(index) - '0';
    }
}
