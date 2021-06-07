package distributed_server_client;

public class Operation {
	private OperationType type = null;
	private int first_node, second_node;
	
	public Operation(Character c, int first, int second) {
		switch(c) {
			case 'A':
				type = OperationType.A;
				break;
			case 'a':
				type = OperationType.A;
				break;
			case 'Q':
				type = OperationType.Q;
				break;
			case 'q':
				type = OperationType.Q;
				break;
			case 'D':
				type = OperationType.D;
				break;
			case 'd':
				type = OperationType.D;
				break;
			default:
				type = OperationType.A;
				break;	
		}
		first_node = first;
		second_node = second;
	}
	
	public OperationType getOperation() {
		return type;
	}
	
	public int getFirstNode() {
		return first_node;
	}
	
	public int getSecondNode() {
		return second_node;
	}
}
