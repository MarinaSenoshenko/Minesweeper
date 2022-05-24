package TextInterface.View.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import CommonClasses.Model.GameEndOrBegin;

public class TextAbout implements CommandInterface {
	
	@Override
    public void doCommand(GameEndOrBegin gameEndOrBegin, Scanner scan, String level) {
		try {
			scan = new Scanner(new File("about.txt"));
			String str = "";

			while (scan.hasNext()) {
				str += scan.nextLine() + "\n";
			}
			System.out.println(str);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
    }
}
