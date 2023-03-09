package 排序算法;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class 排序算法 {
	public static void main(String[] args) {
		int[] ints = new int[] {1,3,88,2,56,8,5};
//		bubbleSort(ints);
//		selectSort(ints);
//		insertSort(ints);
//		shellSort(ints);
//		quickSort(ints, 0, ints.length - 1);
//      mergeSort(ints, 0, ints.length - 1, new int[ints.length]);
//		bucketSort(ints);
//		countingSort(ints);
//		radixSort(ints);
		heapSort(ints);
		for (int i : ints) {
			System.out.print(i + " ");
		}
	}
	//冒泡排序
	public static void bubbleSort(int[] arr) {
		boolean flag = true;
		for (int i = 0; i < arr.length - 1; i++) {//冒泡次数
			for (int j = 0; j < arr.length - 1 - i; j++) {//比较次数每次都比较出了最大值都可以少比较一次
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					flag = false;
				}
			}
			if (flag) {
				break;
			}
		}
	}
	//选择排序:找最小值,放在第一个位置往前遍历.知道遍历完为止.这些循环中止条件,通过实例判断,注意是和i+1比较
	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {////比较次数距离当前数组长度是2,需要比较一次,循环进入一次
			int min = arr[i];
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {//跟谁比较:跟他下一个值比较从i+1比较.比较停止条件将i = 0带入,也是需要比较一次的.所以是arr.length
				if (min > arr[j]) {
					min = arr[j];
					minIndex = j;
				}
			}
			//当最小值下标和当前下标不等时,
			if (minIndex != i) {
				arr[minIndex] = arr[i];//最小值的位置换成i的值
				arr[i] = min;//i的值换成最小值
			}
		}
	}
	
	//插入排序:第一层循环:插入次数,以及起始插入位置 第二层循环:while循环终止条件,当前insertIndex - 1 >=0(防止数组越界) && insertValue < arr[insertIndex - 1]
	public static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {//插入次数,以及起始插入位置从1开始,插入次数以长度为2的数组为例,需要一次插入
			int insertIndex = i;
			int insertValue = arr[i];
			while (insertIndex - 1 >= 0 &&insertValue < arr[insertIndex - 1]) {
				arr[insertIndex] = arr[insertIndex - 1];
				insertIndex -= 1;
			}
			//插入值
			arr[insertIndex] = insertValue;
		}
	}
	
	
	//希尔排序:插入排序变种,初始以当前数组length/2,步长插入,知道插入步长为1为止,其他和插入排序类似
	public static void shellSort(int[] arr) {
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				int insertIndex = i;
				int insertValue = arr[i];
				while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {
					arr[insertIndex] = arr[insertIndex - gap];
					insertIndex -= gap;
				}
				//插入位置赋值
				arr[insertIndex] = insertValue;
			}
		}
	}
	
	/*
	 * 快速排序:
	 * 		使用l,r的指针,跟当前集合(会递归)首元素比较,r位置>=当前首元素时,移动r,l位置<=首元素时,移动l.
	 * 		当l,r相等当前快排结束,此时交换首元素和当前l,r的元素,得到他的左边都是<=他的,他的右边都是>=他的.
	 * 		当l.r不相等时交换两者,再次快排.
	 * 		递归他的左右边界
	 * 		
	 */
	public static void quickSort(int[] arr,int left,int right) {
		if (left >= right) {
			return;//递归退出条件
		}
		int l = left,r = right;
		while (l < r) {//循环条件
			while (l < r && arr[r] >= arr[left]) {
				r--;
			}
			while (l < r && arr[l] <= arr[left]) {
				l++;
			}
			if (l == r) {
				int temp = arr[left];
				arr[left] = arr[l];
				arr[l] = temp;
			} else {
				int temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
			}
		}
		quickSort(arr, left, l-1);//此处的left的值已经被交换成<=之前首元素的值了
		quickSort(arr, r+1, right);//此集合所有值都是>=之前首元素的值
	}
	
	
	
	/*
	 * 归并排序:先分再合,将数组先差分为一个一个元素,然后开始合并成有序小集合,再将有序小集合合并
	 */
	public static void mergeSort(int[] arr,int left,int right,int[] temp) {
		if (left < right) {//只要左边比右边小就可以分.递归是if
			int mid = (left + right)/2;
			//分隔
			mergeSort(arr, 0, mid, temp);//左边分隔//从0开始不是left开始
			mergeSort(arr, mid+1, right, temp);//右边分隔
			//合并
			merge(arr, left, mid, right, temp);//开始合并
		}
	}
	public static void merge(int[] arr,int left,int mid,int right,int[] temp) {
		//确定好需要合并的两个集合的首元素下标
		int i = left,j = mid + 1;
		int t = 0;//临时数组的下标从0开始一起增长
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {//i集合内元素更小
				temp[t++] = arr[i++];
			} else {
				temp[t++] = arr[j++];
			}
		}
		while (i <= mid) {
			temp[t++] = arr[i++];
		}
		while (j <= right) {
			temp[t++] = arr[j++];
		}
		
		//将临时数组转化为arr数组
		t = 0;//重置数组
		int tempLeft = left;//当前集合最左边
		while (tempLeft <= right) {//当前集合最右边
			arr[tempLeft++] = temp[t++];
		}
	}
	
	
	/*
	 * 计数排序:
	 * 		1. 先找到最大值和最小值
	 * 		2. 确定集合数组长度.
	 * 		3. 开始装值和计算次数
	 * 		4. 整合原数组和集合数组
	 */
	public static void countingSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int min = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		//计数数组
		int[] countArr = new int [max - min + 1];
		//遍历原数组加入到计数数组中
		for (int i = 0; i < arr.length; i++) {
			//统计次数,找到对应下标++,此时已经转化为为排序好的下标(开始是0,只要存在++变成1,重复存在++计数+1
			countArr[arr[i] - min]++; //i的真正下标需要减去最小值偏移量
		}
		//整合数组
		int k = 0;
		for (int i = 0; i < countArr.length; i++) {//遍历计数数组
			if (countArr[i] > 0) {//存在数据
				arr[k++] = i + min;//此时需要加上偏移量
				countArr[i]--;
			}
		}
	}
	
	/*
	 * 桶排序:
	 * 	1. 先确定数组的最大值和最小值
	 * 	2. 创建数组,确定桶的数量(规则自定义,此处我们使用(最大值 - 最小值)/数组长度+1.此处我们使用二维数组.使用java的ArrayList(因为不确定桶内的元素个数)
	 * 	3. 遍历原数组将值放入桶中
	 * 	4. 遍历每个桶将桶内元素排序
	 * 	5. 整合数组
	 */
	public static void bucketSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		//最大值最小值
		int min = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		//创建数组确定桶的个数
		ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
		int count = (max - min)/arr.length + 1;//6个桶
		//根据桶的个数创建二位数组内的数组对象
		for (int i = 0; i < count; i++) {
			buckets.add(new ArrayList<>());
		}
		//遍历数组将对应的数据放在桶里
		for (int i = 0; i < arr.length; i++) {
			int bucketIndex = (arr[i] - min)/arr.length;//求出当前的值再桶中的下标
			buckets.get(bucketIndex).add(arr[i]);//对应下标的数组内添加元素
		}
		//对每个桶进行排序
		for (int i = 0; i < buckets.size(); i++) {
			Collections.sort(buckets.get(i));//或者使用快排
		}
		//将桶内排序好的集合整合到数组中
		int k = 0;
		for (int i = 0; i < buckets.size(); i++) {
			for (int j = 0; j < buckets.get(i).size(); j++) {
				arr[k++] = buckets.get(i).get(j) ;
			}
		}
	}
	
	
	
	public static void radixSort(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		int radix = (max + "").length();//转字符串找到最大位数
		LinkedList<Integer>[] list = new LinkedList[10];
		for (int i = 0; i < list.length; i++) {
			list[i] = new LinkedList<Integer>();//数组内数组填充LinkedList
		}
		for (int i = 1; i <= radix; i++) {//遍历到最高位
			//向桶内加值
			for (int j = 0; j < arr.length; j++) {//遍历原数组
				list[getIndex(arr[j], i)].offer(arr[j]);
			}
			//将每次遍历桶内的值重新组装
			int k = 0;
			for (int j = 0; j < list.length; j++) {
				while (!list[j].isEmpty()) {
					arr[k++] = list[j].poll();
				}
			}
		}
	}
	/*
	 * 传入当前值,传入当前需要获取的位数,得到相应位数的值
	 */
	public static int getIndex(int num,int r) {//前面代表具体数据,后面代表位数
		int ret = 0;
		for (int i = 1; i <= r; i++) {
			ret = num % 10;
			num /= 10;
		}
		return ret;
	}
	
	
	/*
	 * 堆排序:
	 * 	根据大顶堆(小顶堆)特性,我们得到
	 * 		1. 当前节点的父节点是(i-1)/2
	 * 		2. 某一节点的左孩子节点是2*i+1,
	 * 		3. 某一节点的右孩子节点是2*i+2.
	 * 	堆排序步骤:
	 * 		1. 将数组装换为堆结构.从第一个非叶子节点开始转化.
	 * 			怎么转化:函数传参数:当前数组,当前节点下标,当前堆结构最大值.
	 * 			1. 判断是否有左子树并且大于当前节点.如果是max等于左子树节点下标
	 * 			2. 判断是否有右子树并且大于前面得到的最大最,如果是max等于右子树节点下标
	 * 			3. 如果当前节点不等于传进来的函数参数节点代表需要交换,交换两者之间的值.
	 * 			4. 当前节点交换后,子节点内部结构可能发生变化,需要递归调用转化(已经从上面知道最大max,最大值直接透传)
	 * 		2. 循环从最后一个节点开始,堆大小每次缩小1,知道为0为止
	 * 			1. 交换堆顶和堆位元素位置.
	 * 			2. 转化新的堆结构(从堆顶开始调整,注意最大值会缩减).
	 * 
	 */
	public static void heapSort(int[] arr) {
		//创建大顶堆结构(起始值是第一个非叶子节点,只要当前节点下标>=0就可以一直改变堆结构)
		for (int i = (arr.length - 1 - 1)/2; i >= 0; i--) {
			adjustHeap(arr, i, arr.length - 1);
		}
		//交换堆顶队尾,改变堆结构(起始值最后一个叶子节点,一直到i=1进入最后一次循环,缩小到0的最后一个last_index为止)
		for (int i = arr.length - 1; i > 0; i--) {
			swap(arr, 0, i);//交换堆顶队尾
			adjustHeap(arr, 0, i-1);//每次缩小堆结构
		}
	}
	public static void adjustHeap(int[] arr,int parent,int last_index) {
		int max = parent;//默认值父节点最大
		int lChild = parent * 2 + 1;
		int rChild = parent * 2 + 2;
		if (lChild <= last_index && arr[lChild] > arr[max]) {//左孩子节点不越界,且内部的值大于max的值,改变max
			max = lChild;
		}
		if (rChild <= last_index && arr[rChild] > arr[max]) {
			max = rChild;
		}
		if (max != parent) {//需要交换
			swap(arr, parent, max);
			//递归子结构(不用区分左子树还是右子树)
			adjustHeap(arr, max, last_index);
		}
	}
	public static void swap(int[]arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
