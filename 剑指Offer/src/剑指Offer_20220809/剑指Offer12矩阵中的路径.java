package 剑指Offer_20220809;

public class 剑指Offer12矩阵中的路径 {
//    public boolean exist(char[][] board, String word) {
//    	char[] words = word.toCharArray();
//    	for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[0].length; j++) {
//				if (dfs(board, words, i, j, 0)) {//初始值k传work的下标0.从0开始查找
//					return true;
//				}
//			}
//		}
//    	return false;
//    }
//    public boolean dfs(char[][] board,char[] words,int i,int j, int k) {
//    	//判断边界和不符合条件
//    	if (i >= board.length || j >= board[0].length || i < 0 || j < 0) {//超过数组边界
//			return false;
//		}
//    	if (board[i][j] != words[k]) {//值不相等
//			return false;
//		}
//    	if (k == words.length - 1) {//代表遍历完成
//			return true;
//		}
//    	//将查询到的置为'\0'
//    	board[i][j] = '\0';
//    	//递归下一个值(之前的已经置为'\0'了,所以前后左右都可以查找,然后k的位置也要+1
//    	boolean res = dfs(board, words, i-1, j, k+1) || dfs(board, words, i+1, j, k+1) 
//    			|| dfs(board, words, i, j-1, k+1) || dfs(board, words, i, j+1, k+1);
//    	
//    	//还原当前矩阵元素： 将 board[i][j] 元素还原至初始值，即 word[k] 。
//    	board[i][j] = words[k];//这是啥意思
//		return res;
//	}
	
	//思路:因为可以从任意节点作为起始位置所以需要遍历所有二维数组节点.
	//DFS:深度优先算法 BFS:广度优先算法
	//回溯:查找当前节点如果不满足向上查找父节点的子节点是否满足,都不满足查父节点的父节点
    public boolean exist(char[][] board, String word) {
    	char[] words = word.toCharArray();
    	for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				//从0开始
				if (dfs(i, j, board, words, 0)) {
					return true;
				}
			}
		}
    	return false;//都没有找到
    }
    
    /*
     * 参数
     */
    public boolean dfs(int i,int j,char[][] board,char[]words,int k) {
    	if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
			return false;
		}
    	if (board[i][j] != words[k]) {
			return false;
		}
    	if (k == words.length - 1) {//执行到这一步时,正好k和words长度相等代表全部找到
    		return true;
		}
    	char temp = board[i][j];
    	//将自身的board置为特殊字符防止被原路返回访问
    	board[i][j] = '#';
    	boolean res = dfs(i-1, j, board, words, k+1) || dfs(i+1, j, board, words, k+1)
    			|| dfs(i, j-1, board, words, k+1) || dfs(i, j+1, board, words, k+1);
    	board[i][j] = temp;
		return res;
	}
}
