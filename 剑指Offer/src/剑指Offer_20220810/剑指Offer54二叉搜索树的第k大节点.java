package 剑指Offer_20220810;

public class 剑指Offer54二叉搜索树的第k大节点 {
	int count,res;
    public int kthLargest(TreeNode root, int k) {
    	this.count = 0;
    	this.res = 0;//初始值
    	dfs(root, k);
    	return res;
    }
    void dfs(TreeNode node,int k) {
    	if (node != null) {
        	//先找右节点
        	dfs(node.right, k);
        	//此时node是最右叶子
        	count++;
        	if (count == k) {
				res = node.val;
			}
        	dfs(node.left, k);
		}
    }
}
