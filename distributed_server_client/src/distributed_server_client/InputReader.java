package distributed_server_client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public List<Operation> generateInstructions(){
		List<Operation> l = new ArrayList<Operation>();
		char[] c = {'A', 'D', 'Q', 'Q'}; 
		Random rand = new Random();
		// generate random number of instructions between 5 & 20 instructions.
		int numOfInstructions = rand.nextInt((20 - 5) + 1) + 5;
		for (int i = 0; i < numOfInstructions; i++) {
			int type = rand.nextInt((3 - 0) + 1) + 0;
			// generate two random numbers between 1 and 10 
			int first = rand.nextInt((10 - 1) + 1) + 1;
			int second = rand.nextInt((10 - 1) + 1) + 1;
			Operation o = new Operation(c[type], first, second);
			l.add(o);
		}
		writeInstructions(l);
		return l;
	}
	
	private void writeInstructions(List<Operation> l) {
		Random rand = new Random();
		// generate random name.
		int fileName = rand.nextInt((500 - 1) + 1) + 1;
		File file = new File(String.valueOf(fileName)+".txt");
		try {
			file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (Operation o : l) {
				System.out.println(operationToString(o));
				bw.write(operationToString(o));
				bw.newLine();
			}
			bw.write("F");
			bw.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	private String operationToString(Operation o) {
		String s = "";
		switch(o.getOperation()) {
		case A:
			s += "A ";
			break;
		case D:
			s += "D ";
			break;
		case Q:
			s += "Q ";
			break;
		default:
			s += "A ";
			break;	
		}
		s += String.valueOf(o.getFirstNode());
		s += " ";
		s += String.valueOf(o.getSecondNode()); 
		return s;
	}
}
