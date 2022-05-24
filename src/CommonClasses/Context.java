package CommonClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Context {
	
	 private HashMap<String, Class<?>> operations = new HashMap<String, Class<?>>();
	 
	 public Context(String fileName) throws FileNotFoundException, ClassNotFoundException {
		 ScanOperation(fileName);
	 }
	 
	 public HashMap<String, Class<?>> getHashMap() {
		 return operations;
	 }
	 
	 private void ScanOperation(String fileName) throws FileNotFoundException, ClassNotFoundException {
	     try(Scanner scan = new Scanner(new File(fileName))) {	      	
            while (scan.hasNext()) {
                String op, classPath;
                op = scan.next().toLowerCase();
                classPath = scan.next();
                operations.put(op, Class.forName(classPath));            
            } 
            scan.close();
	     }
	 }
}
