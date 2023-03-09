package 排序算法;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class 排序算法巩固_0823 {
	public static void main(String[] args) {
		int[] ints = new int[] {1,1,3,88,2,56,8,5};
//		bubbleSort(ints);
//		selectSort(ints);
//		insertSort(ints);
//		shellSort(ints);
//		quickSort(ints, 0, ints.length - 1);
//		mergeSort(ints, 0, ints.length - 1, new int[ints.length]);
//		bucketSort(ints);
//		radixSort(ints);
//		countingSort(ints);
		heapSort(ints);
		for (int i : ints) {
			System.out.print(i + " ");
		}
	}
	/*
	 * 冒泡排序
	 */
	public static void bubbleSort(int[] arr) {
		//优化
		boolean flag = true;
		for (int i = 0; i < arr.length - 1; i++) {//冒泡次数,初始值从0开始??
			for (int j = 0; j < arr.length - i - 1; j++) {//比较次数:要进一次循环条件时length - j -1,所以从0开始
				if (arr[j] > arr[j+1]) {
					flag = false;
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
			if (flag) {
				break;
			}
		}
		
	}
	/*
	 * 选择排序:移动最小值和下标找到最小值
	 */
	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			//当前位置最小值初始值
			int minIndex = i;
			int minValue = arr[i];
			//确定最小值
			for (int j = i + 1; j < arr.length; j++) {//注意其实质i+1.
				if (arr[j] < minValue) {
					minIndex = j;
					minValue = arr[j];
				}
			}
			//判断是否是最小值.交换
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = minValue;
			}
		}
	}
	/*
	 * 插入排序:以初始值1,插入
	 */
	public static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {//从1开始插入
			//插入初始值
			int insertIndex = i;//此值改变
			int insertValue = arr[i];//此值不变
			//是否插入
			while (insertIndex - 1 >= 0 && insertValue < arr[insertIndex - 1]) {//数组不越界,且小于当前插入之前值
				//改变插入的值先为上一个值
				arr[insertIndex] = arr[insertIndex - 1];//将对应值后移
				//改变插入的index
				insertIndex -= 1;
			}
			arr[insertIndex] = insertValue;	
		}
	}
	/*
	 * 希尔排序
	 */
	public static void shellSort(int[] arr) {
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {//从1开始插入
				//插入初始值
				int insertIndex = i;//此值改变
				int insertValue = arr[i];//此值不变
				//是否插入
				while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {//数组不越界,且小于当前插入之前值
					//改变插入的值先为上一个值
					arr[insertIndex] = arr[insertIndex - gap];//将对应值后移
					//改变插入的index
					insertIndex -= gap;
				}
				arr[insertIndex] = insertValue;	
			}
		}
	}
	
	/*
	 * 快速排序:
	 * 双指针:先右后左,右侧:如果当前值>=初始值,移动r--,左侧:如果当前值<=初始值,移动l++,
	 * 判断当前l,r是否相等,不相等交换l,r位置,相等交换初始值和l,r当前坐标位置.此时做测全是<=初始值的值,右侧全是>=初始值的值
	 * 递归查左右两边
	 * 
	 */
	public static void quickSort(int[] arr,int left,int right) {
		if (left >= right) {
			return;
		}
		int l = left,r = right;
		while (l < r) {
			while (l < r && arr[r] >= arr[left]) {
				r--;
			}
			while (l < r && arr[l] <= arr[left]) {
				l++;
			}
			if (l == r) {
				int temp = arr[left];
				arr[left] = arr[r];
				arr[r] = temp;
			} else {
				int temp = arr[l];
				arr[l] = arr[r];
				arr[r] = temp;
			}
		}
		quickSort(arr, 0, l-1);
		quickSort(arr, r+1, right);
	}
	
	/*
	 * 归并排序:先分再合:将数组递归分成最小单位.开始合并数组
	 */
	public static void mergeSort(int[] arr,int left,int right,int[] temp) {
		if (left < right) {//此时需要递归和合并
			int mid = (left + right)/2;
			mergeSort(arr, 0, mid, temp);
			mergeSort(arr, mid + 1, right, temp);
			merge(arr, left, mid, right, temp);
		}
	}
	//合并
	public static void merge(int[] arr,int left,int mid,int right,int[] temp) {
		int l = left,r = mid + 1,k = 0;
		while (l <= mid && r <= right) {
			if (arr[l] < arr[r]) {
				temp[k++] = arr[l++];
			} else {
				temp[k++] = arr[r++];
			}
		}
		
		while (l <= mid) {
			temp[k++] = arr[l++];
		}
		while (r <= right) {
			temp[k++] = arr[r++];
		}
		//重置
		k = 0;
		int arrLeft = left;
		while (arrLeft <= right) {
			arr[arrLeft++] = temp[k++];
		}
	}
	
	/*
	 * 桶排序:求出最大值,最小值,二维数组,一维为桶,通过规则确定桶的个数,二维是桶内元素,对桶内元素排序.重新组装桶.
	 */
	public static void bucketSort(int[] arr) {
		int min = arr[0],max = arr[0];
		for (int i : arr) {
			if (i < min) {
				min = i;
			}
			if (i > max) {
				max = i;
			}
		}
		int bucketCount = (max - min)/arr.length + 1;		//确定桶多少
		ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();//创建二维数组
		for (int i = 0; i < bucketCount; i++) {
			buckets.add(new ArrayList<Integer>());//添加桶
		}
		//想桶内添加值
		for (int i = 0; i < arr.length; i++) {
			int bucketIndex = (arr[i] - min)/arr.length;//根据上面桶的数量规则确定桶的下标
			buckets.get(bucketIndex).add(arr[i]);//向桶内对应下标添加值
		}
		//对桶内元素排序
		for (int i = 0; i < buckets.size(); i++) {
			Collections.sort(buckets.get(i));
		}
		//重新添加到数组中
		int k = 0;
		for (int i = 0; i < buckets.size(); i++) {
			ArrayList<Integer> list = buckets.get(i);
			for (int j = 0; j < list.size(); j++) {
				arr[k++] = list.get(j);
			}
		}
	}
	
	/*
	 * 基数排序:非负整数排序,准备二维数组桶:桶长度为10,对应0-9下标.先将数组以个位依次升高位数排序,再放在原数组中.当最高位排好序就是升序的
	 */
	public static void radixSort(int[] arr) {
		int min = arr[0],max = arr[0];
		for (int i : arr) {
			if (i < min) {
				min = i;
			}
			if (i > max) {
				max = i;
			}
		}
		int radix = (max + "").length();//最大位数
		//创建二维数组
//		LinkedList<Integer>[] lists = new LinkedList[10];
		LinkedList<LinkedList<Integer>> lists = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			lists.add(new LinkedList<Integer>());//一维数组用队列表示
		}
		for (int i = 1; i <= radix; i++) {//根据位数排序
			//将数组的值,分配给桶
			for (int j = 0; j < arr.length; j++) {
				lists.get(getIndex(arr[j], i)).offer(arr[j]);
			}
			//重新组装每次桶里的数据
			int k = 0;
			for (int j = 0; j < lists.size(); j++) {
				while (!lists.get(j).isEmpty()) {
					arr[k++] = lists.get(j).poll();
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
	 * 计数排序:准备数组最大值-最小值+1长度数组.取原数组对应值放在相应下标中,如果重复出现直接++,整合数组当下标中的值>0,添加到原数组中;
	 */
	public static void countingSort(int[] arr) {
		int min = arr[0],max = arr[0];
		for (int i : arr) {
			if (i < min) {
				min = i;
			}
			if (i > max) {
				max = i;
			}
		}
		int[] countArr = new int[max - min + 1];
		for (int i = 0; i < arr.length; i++) {
			countArr[arr[i] - min]++;//出现++为1,再次出现++为2
		}
		int k = 0;
		for (int i = 0; i < countArr.length; i++) {
			while (countArr[i] > 0) {
				arr[k++] = i + min;
				countArr[i]--;	
			}
		}
	}
	
	/*
	 * 堆排序:遍历数组,创建堆,调整堆.从堆尾缩小堆(交换堆顶堆尾,调整堆)
	 */
	public static void heapSort(int[] arr) {
		for (int i = (arr.length - 1 -1)/2; i >= 0; i--) {//公式规律
			//从最后一个非叶子节点开始从下到上,从右至左调整结构
			adjustHeap(arr, i, arr.length-1);
		}
		//
		for (int i = arr.length - 1; i > 0; i--) {
			//交换元素
			swap(arr, 0, i);
			//重新调整堆(从顶部根节点开始)
			adjustHeap(arr, 0, i - 1);
		}
		
	}
    /**
     * 调整堆
     * @param arr 待排序列
     * @param parent 父节点
     * @param length 待排序列尾元素索引
     */
	public static void adjustHeap(int[] arr,int parent,int last_index) {
		int max = parent;
		int lChild = parent * 2 + 1;
		int rChild = parent * 2 + 2;
		if (lChild <= last_index && arr[lChild] > arr[max]) {
			max = lChild;
		}
		if (rChild <= last_index && arr[rChild] > arr[max]) {
			max = rChild;
		}
		if (max != parent) {
			swap(arr, max, parent);
			adjustHeap(arr, max, last_index);
		}
	}
	public static void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
