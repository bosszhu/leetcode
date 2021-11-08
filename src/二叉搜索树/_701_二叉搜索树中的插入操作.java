package 二叉搜索树;

public class _701_二叉搜索树中的插入操作 {
	//add
    public TreeNode insertIntoBST(TreeNode root, int val) {
    	if (root == null) {
			return new TreeNode(val);
		}
    	TreeNode parent = root;//保存上一次父节点
    	int compareVal = 0;//默认比较大小相等
    	
    	TreeNode node = root;
    	while (node != null) {
    		compareVal = compare(node.val, val);//前一次的值
    		parent = node;//前一次的值
			if (compareVal > 0) {
				node = node.left;
			} else if (compareVal < 0) {
				node = node.right;
			} else {
				return node;//存在相等的值,不做任何处理
			}
		}
    	if (compareVal > 0) {
			parent.left = new TreeNode(val);
		} else {
			parent.right = new TreeNode(val);
		}
    	return root;
    }
    /*
     * 比较大小,大于零添加到左边,小于零,添加到右边
     */
    private int compare(int nodeVal,int val) {
		return nodeVal - val;
	}
}
