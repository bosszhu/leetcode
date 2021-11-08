package 二叉搜索树;

public class _450_删除二叉搜索树中的节点 {
	/*
	 * 思路:二叉搜索树,了解什么事二叉搜索树?什么是度?以及什么是前驱节点或后继节点以及他们的性质.
	 * 		1. 删除度为2的节点(怎样的节点是度为二的节点,以及两种场景的特殊处理)
	 * 		2. 删除度为1的节点(怎样的节点是度为一的节点,两种场景的特殊处理)
	 * 		3. 删除度为0的节点(叶子节点,两种场景的特殊处理)/
	 */
	  public TreeNode deleteNode(TreeNode root, int key) {
		   return remove(root,key);
	  }
	  
	  private TreeNode remove(TreeNode node,int key) {
		   if (node == null) {//当前的二叉树为null,直接返回null
			   return null;	
		   }
		   if (key < node.val) {//key小于当前节点,右子树中查询
			   node.left = remove(node.left, key);
		   } else if (key > node.val) {//key大于当前节点,左子树查询
			   node.right = remove(node.right, key);
		   } else {//命中key
			   //需要判断当前节点度为几
			   if (node.left == null) return node.right;
			   if (node.right == null) return node.left;
			   TreeNode minNode = minNode(node.right);
			   node.val = minNode.val;
			   node.right = remove(node.right, minNode.val);
			   
//   			    TreeNode maxNode = maxNode(node.left);
//   			    node.val = maxNode.val;
//   			    node.left = remove(node.left, maxNode.val);
		   }
		   return node;
	  }
	  /*
	   * 后继节点(右子树一直找最左的节点)
	   * 递归解法:
	   */
	  private TreeNode minNode(TreeNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
 	  }
	  /*
	   * 前驱节点(左子树一直在找最右的节点)
	   */
	  @SuppressWarnings("unused")
	  private TreeNode maxNode(TreeNode node) {
		  while (node.right != null) {
			node = node.right;
		}
		return node;
	  }
}
