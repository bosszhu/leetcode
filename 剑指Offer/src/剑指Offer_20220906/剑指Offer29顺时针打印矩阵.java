package 剑指Offer_20220906;

public class 剑指Offer29顺时针打印矩阵 {
	/*
	 * 思路:
	 * 		四指针,判断越界退出循环
	 */
    public int[] spiralOrder(int[][] matrix) {
    	//边界问题
    	if (matrix.length == 0 || matrix == null) {
			return new int[0];
		}
    	//四指针初始化(指的是下标)和目标数组及index初始化
    	int left = 0,right = matrix[0].length - 1,top = 0,bottom = matrix.length - 1,index = 0;
    	int[] res = new int[(right+1) * (bottom+1)];//2*3数组总长度应该是2*3
    	while (true) {//死循环
    		//从left->right
    		for (int i = left; i <= right; i++) {
				res[index++] = matrix[top][i];//加入第一层数据
			}
    		top++;//走完就++
    		//边界
    		if (top > bottom) {
				break;
			}
    		//从top->bottom
    		for (int i = top; i <= bottom; i++) {
				res[index++] = matrix[i][right];//加入第一层数据
			}
    		right--;//走完就++
    		//边界
    		if (right < left) {
				break;
			}
    		//从right->left
    		for (int i = right; i >= left; i--) {
				res[index++] = matrix[bottom][i];//加入第一层数据
			}
    		bottom--;//走完就++
    		//边界
    		if (bottom < top) {
				break;
			}
    		//从bottom->top
    		for (int i = bottom; i >= top; i--) {
				res[index++] = matrix[i][left];//加入第一层数据
			}
    		left++;//走完就++
    		//边界
    		if (left > right) {
				break;
			}
		}
    	return res;
    }
}
