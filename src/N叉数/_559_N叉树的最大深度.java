package N叉数;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class _559_N叉树的最大深度 {
    public int maxDepth(Node root) {
    	if (root == null) {
			return 0;
		} else if (root.children.isEmpty()) {
			return 1;
        } else {
            List<Integer> heights = new LinkedList<>();
            for (Node item : root.children) {
              heights.add(maxDepth(item)); 
            }
            return 1 + Collections.max(heights);	
		}
    }
}
