package distributed_server_client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;

public class InputReader {
	private static InputReader firstInstance = null;
	
	private InputReader() {}
	
	public static InputReader getInstance() {
		if (firstInstance == null) {
			firstInstance = new InputReader();
		}
		return firstInstance;
	}
	
	public List<Operation> initiateGraph(File f) {
		List<Operation> l = new ArrayList<Operation>();
		try {
			Scanner myReader = new Scanner(f);
			while (myReader.hasNextLine()) {
			    String data = myReader.nextLine();
			    Pattern pattern = Pattern.compile("(\\d+) +(\\d+)");
			    Matcher matcher = pattern.matcher(data);
			    if(matcher.find()) {
			        Operation o = new Operation('A', Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
			        l.add(o);
			      }
			    if(data.equals("S") || data.equals("s")) {
			    	break;
			    }
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return l;
	}
	
	public List<Operation> clientInput(File f) {
		List<Operation> l = new ArrayList<Operation>();
		try {
			Scanner myReader = new Scanner(f);
			while (myReader.hasNextLine()) {
			    String data = myReader.nextLine();
			    Pattern pattern = Pattern.compile("(a|A|d|D|q|Q) +(\\d+) +(\\d+)");
			    Matcher matcher = pattern.matcher(data);
			    if(matcher.find()) {
			        Operation o = new Operation(matcher.group(1).charAt(0), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
			        l.add(o);
			      }
			    if(data.equals("F") || data.equals("f")) {
			    	break;
			    }
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return l;
	}
}
