package 剑指Offer_20220804;

public class 剑指Offer47礼物的最大价值 {
//    public int maxValue(int[][] grid) {
//    	int row = grid.length,column = grid[0].length;
//    	int[][] dp = new int[row + 1][column + 1];
//    for (int i = 1; i <= row; i++) {
//        for (int j = 1; j <= column; j++) {
//				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1];
//			}
//		}
//    	return dp[row][column];
//    }
//    public int maxValue(int[][] grid) {
//    	int row = grid.length,column = grid[0].length;
//    	int[][] dp = new int[row][column];
//    	dp[0][0] = grid[0][0];
//    	for (int i = 0; i < row; i++) {
//			for (int j = 0; j < column; j++) {
//				if (i == 0 && j == 0) {
//					dp[i][j] = grid[i][j];
//					continue;
//				}
//				if (i == 0) {
//					dp[i][j] = dp[i][j-1] + grid[i][j];
//				} else if (j == 0) {
//					dp[i][j] = dp[i-1][j] + grid[i][j];
//				} else {
//					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]) + grid[i][j];
//				}
//			}
//		}
//    	return dp[row-1][column-1];
//    }

	 public int maxValue(int[][] grid) {
		 int row = grid.length,col = grid[0].length;
		 int[][] dp = new int[row][col];
		 dp[0][0] = grid[0][0];
		 for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = grid[i][j];
					continue;
				}
				if (i == 0) {//只能右移
					dp[i][j] = dp[i][j-1] + grid[i][j];
				} else if (j == 0) {//只能下移动
					dp[i][j] = dp[i-1][j] + grid[i][j];
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]) + grid[i][j];
				}
			}
		}
		return dp[row-1][col-1];
	 }


	    public int maxValue1(int[][] grid) {
	    	int row = grid.length,column = grid[0].length;
	    	int[][] dp = new int[row][column];
	    	dp[0][0] = grid[0][0];
	    	for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					if (i == 0 && j == 0) {//边界不能-1
						dp[i][j] = grid[i][j];
						continue;
					}
					if (i == 0) {//i边界不能i-1
						dp[i][j] = dp[i][j-1] + grid[i][j];
					} else if (j == 0) {//j边界不能j-1
						dp[i][j] = dp[i-1][j] + grid[i][j];
					} else {
						dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]) + grid[i][j];
					}
				}
			}
	    	return dp[row-1][column-1];
	    }
}
