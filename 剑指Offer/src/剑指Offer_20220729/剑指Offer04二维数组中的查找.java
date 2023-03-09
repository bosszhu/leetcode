package 剑指Offer_20220729;

public class 剑指Offer04二维数组中的查找 {
	
	//二分查找(因为都是排序好的)
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
    	if (matrix == null || matrix.length ==0) {
			return false;
		}
    	//找标志树(移动i,j分别代表行列)
    	int i = matrix.length - 1,j = 0;
    	while (i >= 0 && j <= matrix[0].length -1) {//循环判断条件时i>=0,j<=m,
    		if (matrix[i][j] > target) {
				i--;
			} else if (matrix[i][j] < target) {
				j++;
			} else {
				return true;
			}
		}
    	return false;
    }
}
