package 数组;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */


/*
 * 思路:
 * 		1. 简单暴力法,将数组扩大,后面填充数组2,再开始排序(随便哪种排序
 * 		2. 双指针,当谁向后填充,指针前移.直到一个遍历完成.(优化逆向双指针)
 * 
 */
public class _88_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    	int len1 = m - 1;//num1原数组指针
    	int len2 = n - 1;
    	int size = n + m - 1;
    	while (len1 >= 0 && len2 >= 0) {
			if (nums1[len1] > nums2[len2]) {
				nums1[size--] = nums1[len1--];
			} else {
				nums1[size--] = nums2[len2--];
			}
		}
    	while (len2 >= 0) {
			nums1[size--] = nums2[len2--];
		}
    }
    
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
    	for (int i = 0; i != n; i++) {//增加n个值
			nums1[i+n] = nums2[i]; 
		}
    	Arrays.sort(nums1);//当然也可以用自己的排序
    }
}
