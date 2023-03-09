package 剑指Offer_20220910;

public class 剑指Offer19正则表达式匹配 {
	
//	public static void main(String[] args) {
//		isMatch("aa", "a");
//	}
    public boolean isMatch(String s, String p) {
    	//去字符串长度记为m和n
    	int m = s.length(),n = p.length();
    	//初始化二维数组,初始为false
    	boolean[][] dp = new boolean[m+1][n+1];
    	for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				//考虑p为空串的情况
				if (j == 0 ) {
					if (i == 0) {
						//i,j都为0代表s的前i个字符和p的前j个字符都为空
						dp[i][j] = true;
					}
				} else {
					//p不为空串
					if (p.charAt(j-1) != '*') {//p(j-1)不是*
						if (i >=1 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) =='.')) {
							dp[i][j] = dp[i-1][j-1];
						}
					} else {
						//p(j-1)是*
						if (j >= 2) {
							dp[i][j] |= dp[i][j-2]; 
						}
						if (j >= 2 && i>= 1 && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')) {
							dp[i][j] |= dp[i-1][j];//为什么回头找
						}
					}
				}
			}
		}
    	return dp[m][n];
    }
    
    public boolean isMatch1(String A, String B) {
        int n = A.length();
        int m = B.length();
        boolean[][] f = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //分成空正则和非空正则两种
                if (j == 0) {
                    f[i][j] = i == 0;
                } else {
                    //非空正则分为两种情况 * 和 非*
                    if (B.charAt(j - 1) != '*') {
                        if (i > 0 && (A.charAt(i - 1) == B.charAt(j - 1) || B.charAt(j - 1) == '.')) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    } else {
                        //碰到 * 了，分为看和不看两种情况
                        //不看
                        if (j >= 2) {
                            f[i][j] |= f[i][j - 2];
                        }
                        //看
                        if (i >= 1 && j >= 2 && (A.charAt(i - 1) == B.charAt(j - 2) || B.charAt(j - 2) == '.')) {
                            f[i][j] |= f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }
}
