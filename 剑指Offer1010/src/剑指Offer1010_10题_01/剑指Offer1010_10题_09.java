package 剑指Offer1010_10题_01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class 剑指Offer1010_10题_09 {
	public static void main(String[] args) {
		int[] ints = new int[] {1,3,88,2,56,8,5};
//		bubbleSort(ints);
//		selectSort(ints);
//		insertSort(ints);
//		shellSort(ints);
//		quickSort(ints);
//		mergeSort(ints);
//		countingSort(ints);
//		bucketSort(ints);
//		radixSort(ints);
		heapSort(ints);
		for (int i : ints) {
			System.out.print(i + " ");
		}
	}
	/*
	 * 第1题:
	 * 冒泡排序
	 * 思路:
	 *		1. 双层for循环,每次少冒泡一次
	 *		2. 优化:当标志值不改变代表这轮冒泡全部正确顺序,直接结束冒泡.
	 *		3. 第一层for循环代表冒泡次数,第二层for循环代表交换比较次数
	 *		4. 比较arr[j]和arr[j+1]以及flag标记;
	 */
	public static void bubbleSort(int[] arr) {
		boolean hasSwap = true;
		for (int i = 0; i < arr.length - 1; i++) {
			if (hasSwap == false) {//没进行交换退出循环
				break;
			}
			hasSwap = false;//每次冒泡循环之前设置没有交换
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j+1]) {
					hasSwap = true;//交换了一次标记改变
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}

		}
	}
	
	
	/*
	 * 第2题:
	 * 选择排序
	 * 思路:
	 * 		1. 每次理解数组第一个为临时最小值,然后找后面的只要存在更小的就替换直到找到最小结束.
	 * 		2. 两层for循环,第一层准备两个值第一个用来存最小值,第二个用来存找到最小值的下标.
	 * 		3. 第二层for循环,j从i+1开始,比较length次(举例确定).如果当前min>当前的值,证明后面的值更小,需要赋值为更小的值.
	 * 		4. 退出里层for循环需要判断当前i是否等于minIndex,如果不等需要赋值.
	 *
	 */
    public static void selectSort(int[] arr) {
    	for (int i = 0; i < arr.length - 1; i++) {
			int min = arr[i];
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					minIndex = j;
				}
			}
			if (i != minIndex) {
				arr[minIndex] = arr[i];//先赋值替换位置的
				arr[i] = min;//再赋值最小值
			}
		}
	}
	
	/*
	 * 第3题:
	 * 插入排序
	 * 思路:
	 * 		1. 第一个元素直接排序,第二个元素与前面的比较,大的插入后面小的插入前面,第三个再与前面的已排序好的集合比较
	 * 		2. 第一层for循环从1开始但是总次数还是要length-1次所以遍历截止到lenght.
	 * 		3. 初始化两个变量:insertIndex代表需要插入的下标,insertValue代表当前需要插入的值.注意index需要改变用来判断插入的具体位置,value是存的值,不需要改变后面用于交换.
	 * 		4. while循环:循环条件1. (index-1)>=0不越界,2. value < arr[index-1].值更小往前插入
	 * 				依次向前移动.知道循环停止.(需要移动arr以及index)
	 * 		5. 循环退出后,插入的下标就是需要插入的值.
	 * 		6. 距离数组下标1插入0中
	 */
    public static void insertSort(int[] arr) {
    	for (int i = 1; i < arr.length; i++) {
			int insertIndex = i;
			int insertValue = arr[i];
			while (insertIndex - 1 >= 0 && insertValue < arr[insertIndex - 1]) {
				arr[insertIndex] = arr[insertIndex -1];
				insertIndex --;
			}
			arr[insertIndex] = insertValue;
		}
	}
    
	/*
	 * 第4题:
	 * 希尔排序
	 * 思路:
	 * 		1. 希尔排序插入排序变种,只是插入的间隔从当前数组长度/2开始到1结束
	 */
    public static void shellSort(int[] arr) {
    	for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				int insertIndex = i;
				int insertValue = arr[i];
				while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {
					arr[insertIndex] = arr[insertIndex - gap];
					insertIndex -= gap;
				}
				arr[insertIndex] = insertValue;
			}
		}
    }
    
	/*
	 * 第5题:
	 * 快速排序
	 * 思路:
	 * 		1. 核心双指针,分治思想.
	 * 		2. 核心逻辑:
	 * 			1. 递归退出条件
	 * 			2. 先赋值左右指针.
	 * 			3. while循环l<r时.
	 * 			4. 先右后左:
	 * 					数组不越界时,1.右侧值大于等于当前最左边第一个值时,移动右指针
	 * 							   2.左侧值小于等于当前最左边第一个值时,移动左指针
	 * 			5. 判断当两者指针指向相同位置l==r时交换l和当前最左边第一个值此时相当于找到l,r同位置时.左侧都是比他小或者等于他的的,右侧都是比他大的或者等于他的.
	 * 			l,r位置不相等交换l和r内部的值.开始继续循环知道找到l==r结束
	 * 			此时循环退出时一轮快排结束.
	 * 		3. 递归继续查找left到l-1和r+1到right的快排元素.
	 * 			
	 * 						
	 */
    public static void quickSort(int[] arr) {
    	quickSort_detail(arr, 0, arr.length - 1);
    }
    public static void quickSort_detail(int[] arr,int left,int right) {
    	//递归退出条件
    	if (left >= right) {
			return;
		}
    	int l = left,r = right;
    	while (l < r) {
    		//先右后左,数组不越界
			while (l < r && arr[r] >= arr[left]) {
				r--;
			}
			while (l < r && arr[l] <= arr[left]) {
				l++;
			}
			if (l == r) {
				//交换left和l
				int temp = arr[left];
				arr[left] = arr[l];
				arr[l] = temp;
			} else {
				//交换l和r
				int temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
			}
		}
    	//递归其他范围快排
    	quickSort_detail(arr, left, l - 1);
    	quickSort_detail(arr, r + 1, right);
    }
	/*
	 * 第6题:
	 * 归并排序
	 * 思路:
	 * 		1. 将数组拆分为最小单元,然后开始合并.成若干小集合再继续递归合并,知道为一个集合为止.
	 * 		2. 拆分数组只要left<right就一直拆分(二分法拆分递归拆分)
	 * 		3. 拆分完以后调用合并函数.
	 * 		合并函数内部实现
	 * 		1. 确定好要合并元素的两个集合首元素下标具体位置.left和mid+1,以及新集合的下标从0开始.参数temp[]表示
	 * 		2. 调用合并逻辑.一个不越界,判断那个集合大合并哪个.知道一个完成,然后继续全部合并那个未合并完成的集合.
	 * 		3. 将temp内的值(0开始)合并到arr内的指定位置(left开始)
	 */
    public static void mergeSort(int[] arr) {
    	mergeSort(arr, 0, arr.length - 1);
    }
//    //递归拆分以及调用合并函数
//    public static void mergeSort_split_merge(int[] arr,int left,int right,int[] temp) {
//		if (left < right) {//只要left没有到right位置就一直拆分
//			int mid = (left + right) / 2;
//			mergeSort_split_merge(arr, 0, mid, temp);//从0到mid继续切割(注意这里不是从left到mid因为最左侧一直要从0开始)
//			mergeSort_split_merge(arr, mid + 1, right, temp);//从mid+1到right继续切割(到最右边)
//			//合并集合
//			mergeSort_split_merge_detail(arr, left, right, mid, temp);
//		}
//	}
//    public static void mergeSort_split_merge_detail(int[] arr,int left,int right,int mid,int[] temp) {
//		//确定两个集合最开始下标
//    	int i = left,j = mid+1,index = 0;
//    	while (i <= mid && j <= right) {
//			if (arr[i] < arr[j]) {
//				temp[index++] = arr[i++];
//			} else {
//				temp[index++] = arr[j++];
//			}
//		}
//    	//合并还未完全合并完的值
//    	while (i <= mid) {
//    		temp[index++] = arr[i++];
//		}
//    	while (j <= right) {
//    		temp[index++] = arr[j++];
//		}
//    	//整理到arr数组对应位置
//    	index = 0;int leftIndex = left;
//    	while (leftIndex <= right) {
//			arr[leftIndex++] = temp[index++];
//		}
//	}
	public static void mergeSort(int[] arr,int left,int right) {
		int mid = left + ((right - left) >> 1);
		if (left < right) {
			//分
			mergeSort(arr, left, mid);
			mergeSort(arr, mid+1, right);
			merge(arr, left, mid, right);
		}
	}
	public static void merge(int[] arr,int left,int mid,int right) {
		int[] temp = new int[right - left + 1];
		int i = left,j = mid + 1,t = 0;
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
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
		
        //把新数组中的数覆盖arr数组
        for (int k = 0; k < temp.length; k++) {
            arr[k + left] = temp[k];
        }
	}
	/*
	 * 第7题:
	 * 计数排序
	 * 思路:
	 * 		1. 计算每个元素出现的次数.放在对应的下标位置.
	 * 		2. 整合数组根据刚保存次数整合成排序数组
	 * 		逻辑代码:
	 * 		1. 先确定集合最大值和最小值.
	 * 		2. 根据最大值和最小值创建数组大小
	 * 		3. 遍历原数组,往创建数组内衣次数装填数组
	 * 		4. 将次数数组整合成排序数组.
	 */
    public static void countingSort(int[] arr) {
    	if (arr.length < 2 || arr == null) {//不需要排序
			return;
		}
    	int max = arr[0],min = arr[0];
    	for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
    	//创建新数组
    	int[] countArr = new int[max - min + 1];
    	//遍历原数组整合到计数数组
    	for (int i = 0; i < countArr.length; i++) {
			countArr[arr[i] - min]++;//arr[i]- min代表值所在的位置,此时已经通过值排序到下标了.++代表重复次数.默认是0,再次出现这个值的下标时会++
		}
    	//整合成排序数组
    	int index = 0;
    	for (int i = 0; i < countArr.length; i++) {
			if (countArr[i] > 0) {//代表出现过
				arr[index++] = i + min;
				countArr[i]--;
			}
		}
    }
    
	/*
	 * 第8题:
	 * 桶排序
	 * 思路:
	 * 		1. 将数组内元素按照一定规则装配到对应的桶中
	 * 		逻辑代码
	 * 		1. 先确定数组最大值最小值
	 * 		2. 确定桶的数量(最大值-最小值)/数组长度+1,创建桶数量数组,往里面添加空集合.
	 * 		3. 遍历原数组.以上述规则向对应桶内添加元素
	 * 		4. 对每个桶进行排序
	 * 		5. 将桶内排好序集合整合好数组中
	 * 		
	 */
    public static void bucketSort(int[] arr) {
    	if (arr.length < 2 || arr == null) {//不需要排序
			return;
		}
    	int max = arr[0],min = arr[0];
    	for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
    	//确定桶的数量
    	ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
    	int bucketCount = (max - min)/arr.length + 1;
    	for (int i = 0; i < bucketCount; i++) {
			buckets.add(new ArrayList<>());
		}
    	//遍历数组将元素放在桶内
    	for (int i = 0; i < arr.length; i++) {
			//根据当前值求出应该呆在的桶的下标
    		int bucketIndex = (arr[i] - min)/arr.length;
    		buckets.get(bucketIndex).add(arr[i]);
		}
    	//对桶内元素进行排序
    	for (int i = 0; i < buckets.size(); i++) {
			Collections.sort(buckets.get(i));
		}
    	//将桶内值整合到数组中
    	int index = 0;
    	for (int i = 0; i < buckets.size(); i++) {
			for (int j = 0; j < buckets.get(i).size(); j++) {
				arr[index++] = buckets.get(i).get(j);
			}
		}
    }
    
	/*
	 * 第9题:
	 * 基数排序
	 * 思路:
	 * 		1. 求出数组最大值,确定最大值位数.
	 * 		2. 正整数数组,创建数组固定长度10,使用队列创建.
	 * 		3. 遍历原数组放入桶中
	 * 			从个位到最高位,取对应位数的值放在对应桶中,每次存到桶中然后需要从桶内取出来,当最高位都都完成排好序后,就是排序好的数组.
	 * 		
	 * 			
	 */
    public static void radixSort(int[] arr) {
    	if (arr.length < 2 || arr == null) {//不需要排序
			return;
		}
    	int max = arr[0];
    	for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
    	//创建10个桶
    	LinkedList<Integer>[] list = new LinkedList[10];
		for (int i = 0; i < list.length; i++) {
			list[i] = new LinkedList<Integer>();//数组内数组填充LinkedList
		}
		//确定最大值位数
		int radix = (max + "").length();//转字符串找到最大位数
		//从位数个位开始遍历到最高位
		for (int i = 1; i <= radix; i++) {
			//将原数组的值通过相应位数的值排序到桶中
			for (int j = 0; j < arr.length; j++) {
				list[getIndex(arr[j], i)].offer(arr[j]);
			}
			//将每次遍历桶内的值重新组装
			int index = 0;
			for (int j = 0; j < list.length; j++) {
				while (!list[j].isEmpty()) {
					arr[index++] = list[j].poll();
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
	 * 第10题:
	 * 堆排序
	 * 思路:
	 * 		1. 了解大顶堆,小顶堆结构.
	 * 		特点:每个节点的父节点是(i - 1)/2,当前节点的左孩子是i * 2 + 1,右孩子节点是i * 2 + 2
	 * 		逻辑代码:
	 * 		1. 从第一个非叶子节点开始(最后一个叶子节点的父节点):((length - 1) - 1)/2到i>=0结束.改变数组内部为堆结构
	 * 		2. 从原数组下标最后一个元素开始length - 1到只剩下一个元素结束>0.
	 * 			1. 交换堆顶和堆尾元素.
	 * 			2. 重新调整堆结构
	 * 		3. 调整堆结构函数逻辑.
	 * 			1. 先默认父节点值最大,找到父节点的左右孩子节点.
	 * 			2. 判断左右孩子节点和max节点比较取最大值.注意此处需要判断左右孩子节点不越界
	 * 			3. 如果当前的max最大值改变,交换两者.递归改变堆结构.
	 * 		
	 */
    public static void heapSort(int[] arr) {
    	if (arr.length < 2 || arr == null) {//不需要排序
			return;
		}
    	//数组转化为堆结构
    	for (int i = ((arr.length - 1) - 1)/2; i >= 0; i--) {
			adjust_heapSort(arr, i, arr.length - 1);
		}
    	//交换堆顶堆尾:因为堆顶已经排好序.并且改变转化堆结构范围
    	for (int i = arr.length - 1; i > 0; i--) {
			swap(arr, 0, i);//堆顶数组为0的元素,堆尾数组为i的元素
			//重新调整堆结构
			adjust_heapSort(arr, 0, i-1);//从新的堆顶调整到i-1结束
		}
    }
    //调整堆结构
    public static void adjust_heapSort(int[] arr,int parent,int last_index) {
    	int max = parent;
    	int lChild = parent * 2 + 1;
    	int rChild = parent * 2 + 2;
    	//找更大值的节点
    	if (lChild <= last_index && arr[lChild] > arr[max]) {
			max = lChild;
		}
    	if (rChild <= last_index && arr[rChild] > arr[max]) {
			max = rChild;
		}
    	//判断节点是否改变,如果改变交换值,并且调整堆结构
    	if (max != parent) {
			swap(arr, parent, max);
			adjust_heapSort(arr, max, last_index);
		}
    }
	public static void swap(int[]arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
