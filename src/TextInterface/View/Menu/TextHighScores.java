package TextInterface.View.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;
import CommonClasses.HighScoresTable;
import CommonClasses.Model.GameEndOrBegin;

public class TextHighScores implements CommandInterface {
	 
	 @Override
	 public void doCommand(GameEndOrBegin gameEndOrBegin, Scanner scan, String level) throws FileNotFoundException {
		gameEndOrBegin.setGameExists(false);
		HighScoresTable highScoresTable = new HighScoresTable(level + ".txt");
		highScoresTable.tablePrinter();
	    System.out.println(highScoresTable.getText());
	 }
}
