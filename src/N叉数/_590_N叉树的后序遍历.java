package N叉数;

import java.util.ArrayList;
import java.util.List;

public class _590_N叉树的后序遍历 {
    public List<Integer> postorder(Node root) {
    	List<Integer> list = new ArrayList<Integer>();
    	postorderForList(root, list);
    	return list;
    }
    
    public void postorderForList(Node node,List<Integer> list) {
		if (node == null) return;
		for (int i = 0; i < node.children.size(); i++) {
			Node childNode = node.children.get(i);
			postorderForList(childNode, list);
		}
		list.add(node.val);
	}
}
