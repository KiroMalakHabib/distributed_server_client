package distributed_server_client;

import java.util.*;

public class Node {
	private int vertexNum;
	 
    // Adjacency list that shows the vertexNumber of child vertex and the weight of the edge which is always 1.
    private List<Integer> children;
 
    Node(int vertexNum) {
    	this.vertexNum = vertexNum;
        children = new ArrayList<>();
    }
 
    // Function to add the child for
    // the given node
    void add_child(int vNum) {
        children.add(vNum);
    }
    
    void delete_child_if_exist(int childNum) {
    	for (int p : children) {
    		if (p == childNum) {
    			children.remove((Integer)childNum);
    			break;
    		}
    	}
    }

	public int getVertexNum() {
		return vertexNum;
	}
	
	public List<Integer> getChildren() {
		return children;
	}
}
