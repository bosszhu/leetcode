package N叉数;

import java.util.ArrayList;
import java.util.List;

public class _589_N叉树的前序遍历 {
    public List<Integer> preorder(Node root) {
    	List<Integer> list = new ArrayList<Integer>();
    	preorderForList(root, list);
    	return list;
    }
    
    public void preorderForList(Node node,List<Integer> list) {
		if (node == null) return;
		list.add(node.val);
		for (int i = 0; i < node.children.size(); i++) {
			Node childNode = node.children.get(i);
			preorderForList(childNode, list);
		}
	}
}
