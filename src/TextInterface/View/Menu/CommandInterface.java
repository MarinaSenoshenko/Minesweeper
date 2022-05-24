package TextInterface.View.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import CommonClasses.Model.GameEndOrBegin;

public interface CommandInterface {	
    void doCommand(GameEndOrBegin gameEndOrBegin, Scanner scan, String level) throws FileNotFoundException, Exception;
}
