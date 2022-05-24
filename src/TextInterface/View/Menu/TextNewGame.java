package TextInterface.View.Menu;

import java.util.Scanner;

import CommonClasses.Model.GameEndOrBegin;
import TextInterface.TextInterfaceController;

public class TextNewGame implements CommandInterface {

	 @Override
	 public void doCommand(GameEndOrBegin gameEndOrBegin, Scanner scan, String level) throws Exception {
		 gameEndOrBegin.setGameExists(false);
		 new TextInterfaceController();
	 }
}
