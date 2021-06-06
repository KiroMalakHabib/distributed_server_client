package distributed_server_client;

import java.io.File;
import java.util.*;

public class Test {

	public static void main(String[] args) {
		Graph g = new Graph();
		
		File f = new File("./input_01.txt");
		File f1 = new File("./01_instructions_01.txt");
		List<Operation> initial = InputReader.getInstance().initiateGraph(f);
		List<Operation> instructions1 = InputReader.getInstance().clientInput(f1);
		
		g.excuter(initial);
		g.print_graph();
		
		List<Integer> distances = g.excuter(instructions1);
		g.print_graph();
		
		for(int i : distances) {
			System.out.println(i);
		}
	}

}
