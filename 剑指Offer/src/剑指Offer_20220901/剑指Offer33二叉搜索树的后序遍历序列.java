package 剑指Offer_20220901;

public class 剑指Offer33二叉搜索树的后序遍历序列 {
	
	/*
	 * 了解后续遍历特性:
	 * 		后序遍历LRN,所以最后一个值一定是根节点(大于所有左子树,小于所有右子树).然后可以根据根节点与前面数组的值进行比较.
	 * 			先判断左子树全部是比根节点小的,指针移动.
	 * 			此时记录右子树头结点再判断右子树全部是比根节点大的,指针继续移动.判断指针移动最终大小是否和当前数组的size相同不同代表内部有不符合二叉搜索树的值
	 * 			判断上面的是否返回以及判断左右子树是否满足
	 * 		
	 */
    public boolean verifyPostorder(int[] postorder) {
    	if (postorder.length == 0) {//空树满足二叉搜索树性质
			return true;
		}
    	return verify(postorder, 0, postorder.length - 1);
    }
    
    public boolean verify(int[] postorder,int i,int j) {
		//递归退出条件
    	if (i >= j) {
			return true;
		}
    	int index = i;
    	while (postorder[index] < postorder[j]) {
			index++;
		}
    	int mid = index;
    	while (postorder[index] > postorder[j]) {
			index++;
		}
    	boolean res = index == j;
    	return res && verify(postorder, i, mid - 1) && verify(postorder, mid, j - 1);
	}
    
}
