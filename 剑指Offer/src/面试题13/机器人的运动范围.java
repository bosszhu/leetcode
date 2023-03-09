package 面试题13;

import java.util.Deque;
import java.util.LinkedList;

public class 机器人的运动范围 {
	/*
	 * 思路:因为从起始点(0,0)开始所以方向只能是右下
	 * DFS:
	 * 		条件:1. 数组越界返回0,
	 * 			2. 当前位数和>k,返回0
	 * 			3. 包含当前的点返回0,(需要一个二维数组记录当前的点)
	 * 				注意将这个点加入到标记二维数组
	 * 	 	返回值dfs(i-1,j) + dfs(j-1,i) + 1
	 * 			
	 */
	int m,n,k;
	boolean [][] visited;
	Deque<int[]> queue;
    public int movingCount(int m, int n, int k) {
    	this.m = m;
    	this.n = n;
    	this.k = k;
    	this.visited = new boolean[m][n];
    	this.queue = new LinkedList<>();
//    	return dfs(0, 0);
    	return bfs(0, 0);
    }
    /*
     * 传初始坐标进来
     */
    public int dfs(int i,int j) {
    	if (i >= m || j >= n) {
			return 0;
		}
    	if (digitSum(i, j) > k) {
			return 0;
		}
    	if (visited[i][j]) {
			return 0;
		}
    	visited[i][j] = true;
		return dfs(i+1, j) + dfs(i, j+1) + 1;
	}
    /*
     * 位数和
     */
    public int digitSum(int i,int j) {
		int res = 0;
		while (i > 0) {
			res += i % 10;
			i /= 10;
		}
		while (j > 0) {
			res += j % 10;
			j /= 10;
		}
		return res;
	}
    
    
    //广度优先算法:队列
    public int bfs(int i,int j) {
    	int res = 0;//初始值
    	queue.offer(new int[] {
    		i,j
    	});
    	while (!queue.isEmpty()) {
			int[] reslut = queue.poll();
			i = reslut[0]; j = reslut[1];
			if (i >= m || j >= n) {
				continue;
			}
			if (digitSum(i, j) > k) {
				 continue;
			}
			if (visited[i][j]) {
				 continue;
			}
			visited[i][j] = true;
			res++;
			queue.offer(new int[] {
				i+1,j
			});
			queue.offer(new int[] {
				i,j+1
			});
		}
    	return res;
	}
	

}
