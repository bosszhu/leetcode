package 剑指Offer_20220831;

public class 剑指Offer40最小的k个数 {
	/*
	 * 思路,堆排序,大顶堆,交换倒数k个数
	 */
    public int[] getLeastNumbers(int[] arr, int k) {
    	if (arr.length < k) {
			return null;//没有倒数第k个数
		}
    	int[] newArr = new int[k];
    	heapSort(arr, newArr);
    	return newArr;
    }
    public static void heapSort(int[] arr,int[] newArr) {
    	//创建堆结构,从最后一个非叶子节点开始遍历
		for (int i = (arr.length - 1 - 1)/2; i >= 0; i--) {
			adjustSmallHeap(arr, i, arr.length - 1);
		}
		//遍历到k结束
		int k = 0;
		for (int i = arr.length - 1; i > arr.length - newArr.length - 1; i--) {
			//交换堆尾和堆首
			swap(arr, 0, i);
			//取交换的最后一个值
			newArr[k++] = arr[i];
			//调整堆结构
			adjustSmallHeap(arr, 0, i - 1);
		}
	}
	public static void adjustSmallHeap(int[] arr,int parent,int last_index) {
		int min = parent;
		int lChild = parent * 2 + 1;
		int rChild = parent * 2 + 2;
		if (lChild <= last_index && arr[lChild] < arr[min]) {
			min = lChild;
		}
		if (rChild <= last_index && arr[rChild] < arr[min]) {
			min = rChild;
		}
		if (min != parent) {
			swap(arr, min, parent);
			adjustSmallHeap(arr, min, last_index);
		}
	}
	public static void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
