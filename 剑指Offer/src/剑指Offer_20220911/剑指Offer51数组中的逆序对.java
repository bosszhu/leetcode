package 剑指Offer_20220911;

public class 剑指Offer51数组中的逆序对 {
	/*
	 * 思路:归并排序,每次归并count为mid - i + 1
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
}
