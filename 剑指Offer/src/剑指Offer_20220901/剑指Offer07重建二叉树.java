package 剑指Offer_20220901;

import java.util.HashMap;
import java.util.Map;

public class 剑指Offer07重建二叉树 {
	/*
	 * 重建二叉树:
	 * 		创建构造数的函数:参数当前节点,左边界,右边界
	 * 		1. 前序遍历的首元素是根节点
	 * 		2. 通过前序遍历根节点的值找到中序遍历的根节点下标.然后根节点左边的元素都是左子树元素.根节点右边的元素都是右子树元素.
	 * 		3. 确定左子树的size.(infixOrderRootIndex - left)
	 * 		4. 构造treeNode,当前root的val就是传进来的节点val.递归创建左子树确定左子树的left和right的值,递归创建右子树确定右子树的left和right
	 */
	int[] preorder;
	int[] inorder;
	Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();//将中序遍历的值和下标单独放在key内,否则需要遍历找下标
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if (preorder.length == 0) {//边界值数组没值,当前树就是空树
			return null;
		}
    	this.preorder = preorder;
    	this.inorder = inorder;
    	for (int i = 0; i < inorder.length; i++) {//将中序遍历的值对应上下标
			inorderMap.put(inorder[i], i);
		}
    	return constructionTreeNode(0, 0, inorder.length - 1);
    }
    /**
     * 递归创建二叉树
     * @return	TreeNode 返回创建的树
     * @param preRootIndex 前序遍历根节点
     * @param left 中序遍历左边界
     * @param right 中序遍历右边界
     */
    public TreeNode constructionTreeNode(int preRootIndex,int left,int right) {
    	//递归退出条件
    	if (left > right) {
			return null;
		}
    	//中序遍历根节点下标
    	int inorderRootIndex = inorderMap.get(preorder[preRootIndex]);
    	//当前左子树大小
    	int leftSonTreeSize = inorderRootIndex - left;
    	TreeNode root = new TreeNode(preorder[preRootIndex]);
    	//左子树根节点:之前的根节点+1,左边界:之前中序遍历的左边界,右边界:之前的中序遍历根节点-1
    	root.left = constructionTreeNode(preRootIndex + 1, left, inorderRootIndex - 1);
    	//右子树根节点:之前的根节点+左子树的大小+1,左边界:之前的中序遍历根节点+1,右边界:之前的中序遍历的右边界
    	root.right = constructionTreeNode(preRootIndex + leftSonTreeSize + 1, inorderRootIndex + 1, right);
    	return root;
	}
}
