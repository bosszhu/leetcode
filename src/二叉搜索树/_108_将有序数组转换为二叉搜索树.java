package 二叉搜索树;
//给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
//
//高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class _108_将有序数组转换为二叉搜索树 {
	/*
	 * 注意要求是高度平衡的二叉搜索树,因此需要每次添加元素,给树恢复平衡.
	 */
    public TreeNode sortedArrayToBST(int[] nums) {
    	return helper(nums, 0, nums.length - 1);
    }
    /*
     * 递归
     */
    private TreeNode helper(int[] nums,int left,int right) {
    	if (left > right) {//左边更大
    		return null;
		}
    	int mid = (left + right) / 2;
    	//创造中间节点,在第一个每次创建时都是下一次的根节点
    	TreeNode midNode = new TreeNode(nums[mid]);//当前mid已经创建
    	midNode.left = helper(nums, left, mid-1);//他的左边自然是在他的left到mid
    	midNode.right = helper(nums, mid+1, right);
		return null;
	}
}
