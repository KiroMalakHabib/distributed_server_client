package distributed_server_client;

import java.util.*;

public class Graph {
	private List<Node> Nodes;
	private List<Integer> Nodes_num;
	private int size;
	
	public Graph() {
		Nodes = new ArrayList<>();
		Nodes_num = new ArrayList<>();
		size = 0;
	}
	
	// function to detect if this node is in the graph or not
	public boolean isExist(int n) {
		if (Nodes_num.contains(n)) return true;
		return false;
	}
	
	// function to add edges between nodes 
	//if doesn't exist and to add nodes if don't exist
	public void addEdge(int first, int second) {
		if (!isExist(first)) {
			Nodes_num.add(first);
			Nodes.add(new Node(first));
			size++;
		}
		if (!isExist(second)) {
			Nodes_num.add(second);
			Nodes.add(new Node(second));
			size++;
		}
		if (!Nodes.get(Nodes_num.indexOf(first)).getChildren().contains(second)) {
			Nodes.get(Nodes_num.indexOf(first)).add_child(second);
		}
		
	}
	
	// function to remove edge between two nodes 
	//by removing the second node from 
	//the children list of the first node
	public void removeEdge(int first, int second) {
		if (isExist(first) && Nodes.get(Nodes_num.indexOf(first)).getChildren().contains(second)) {
			Nodes.get(Nodes_num.indexOf(first)).delete_child_if_exist(second);
		}
	}
	
	// Function to find the length of the shortest path
	// from n1 to n2 returns -1 of there's no path.
	// using Dijkstra's algorithm.
	public int shortestPath(int n1, int n2) {
		if (!isExist(n1) || !isExist(n2))
			return -1;
		if (n1 == n2)
			return 0;
		int[] distances = new int[Nodes.size()];
		List<Integer> visited = new ArrayList<Integer>();
		List<Integer> unVisited = new ArrayList<Integer>();
		for (int i = 0; i < distances.length; i++) {
			distances[i] = Integer.MAX_VALUE;
			unVisited.add(i+1);
		}
		int current_node = n1;
		distances[current_node-1] = 0;
		while (!unVisited.isEmpty()) {
			for (int i : Nodes.get(Nodes_num.indexOf(current_node)).getChildren()) {
				if (!visited.contains(i)) {
					distances[i-1] = Math.min(distances[i-1], distances[current_node-1]+1);
				}
			}
			visited.add(current_node);
			unVisited.remove((Integer) current_node);
			current_node = -1;
			int smallest = Integer.MAX_VALUE;
			for (int i : unVisited) {
				if (distances[i-1] < smallest) {
					current_node = i;
					smallest = distances[i-1];
				}
			}
			if (current_node == -1)
				break;
		}
		if (distances[n2-1] == Integer.MAX_VALUE) return -1;
		return distances[n2-1];
	}
	

	// function to return the size of the graph
	public int getSize() {
		return size;
	}
	
	// function to print the nodes of the graph and their children
	public void print_graph() {
		for(Node n : Nodes) {
			System.out.println(n.getVertexNum());
			for (int c : n.getChildren()) {
				System.out.println("->" + c);
			}
			System.out.println();
		}
	}
	
	// function to execute operations to the graph
	public List<Integer> excuter(List<Operation> operatioons){
		List<Integer> distances = new ArrayList<Integer>();
		for (Operation o : operatioons) {
			switch (o.getOperation()) {
			case A:
				addEdge(o.getFirstNode(), o.getSecondNode());
				break;
			case D:
				removeEdge(o.getFirstNode(), o.getSecondNode());
				break;
			case Q:
				distances.add(shortestPath(o.getFirstNode(), o.getSecondNode()));
				break;
			default:
				break;
			}
		}
		return distances;
	}
}
