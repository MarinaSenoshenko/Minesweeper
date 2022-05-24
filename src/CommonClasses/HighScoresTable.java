package CommonClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class HighScoresTable {
	
	private HashMap<Double, String> map = new HashMap<Double, String>();
	private String text;
	private String fileName = "";
	
	public HighScoresTable(String fileName) {
		 this.fileName = fileName;
		 text = "";
		 try {
		     Scanner scan = new Scanner(new File(fileName));
		     while (scan.hasNext()) {
		         double result = scan.nextDouble();
		         String name = scan.next();
		         map.put(result, name);
		     }
		 }
		 catch (FileNotFoundException e) {
		     e.printStackTrace();
		 }
    }

    public void addNew(double result, String name) throws IOException {
		 try {
		     PrintStream stream = new PrintStream(fileName);
		     stream.print(result + " " + name + "\n");
		     map.put(result, name);

		     for (Double res : map.keySet()) {
		          stream.print(res.toString() + " " + map.get(res).toString() + "\n");

		     }		     
		     stream.close();
		 }
		 catch (FileNotFoundException e) {
		     e.printStackTrace();
		 }
    }

    public void tablePrinter() throws FileNotFoundException {
	     Map<Double, String> sorted = new TreeMap<Double, String>(map);
	     printMap(sorted);
	 }


    public <K, V> void printMap(Map<K, V> sorted) throws FileNotFoundException {
	    String s = "";
	    int count = 1;
	    PrintStream stream = new PrintStream(fileName);

	     for (Map.Entry<K, V> entry : sorted.entrySet()) {
	          s = entry.getValue() +  " " + entry.getKey();
	          stream.print(entry.getKey() + " " + entry.getValue() + "\n");
	          text = getText() + count + ") " + s + "\n";
	          count++;
	          if (count == 11) {
	        	  break;
	          }
	     } 
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
}
