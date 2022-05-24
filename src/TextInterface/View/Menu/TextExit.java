package TextInterface.View.Menu;

import java.util.Scanner;

import CommonClasses.Model.GameEndOrBegin;

public class TextExit implements CommandInterface {

	 @Override
	 public void doCommand(GameEndOrBegin gameEndOrBegin, Scanner scan, String level) throws Exception {
		 gameEndOrBegin.setGame(false);
		 gameEndOrBegin.setGameExists(false);
	 }
}
