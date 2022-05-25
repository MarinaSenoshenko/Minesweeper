package TextInterface.View.Board;

import java.util.Scanner;
import CommonClasses.SaveNewScore;
import CommonClasses.TimeConverter;
import CommonClasses.TimeCounter;
import CommonClasses.Model.FieldChecker;
import CommonClasses.Model.GameEndOrBegin;
import TextInterface.TextInterfaceController;
import TextInterface.View.Menu.TextExit;

public class BoardView {	
	private int height; 
	private int width;
	private FieldChecker[][] field;
	
    public BoardView(int height, int width, FieldChecker[][] field) {
    	this.height = height; 
    	this.width = width;
    	this.field = field;		
	}
    
    public void printBoard(boolean game, int[][] boardDigit) {
        System.out.print("  x ");
		
		for (int x = 0; x < width; x++) {
			System.out.print(" " + x + " ");
		}
		System.out.println();
		for (int y = 0; y < height; y++) {
			if (y == 0) {
				System.out.println("  y");
			}
			if (y <= 9) {
				System.out.print(" ");
			}
			System.out.print(" " + y + " ");
			for (int x = 0; x < width; x++) {
				if (game) {
					if (x > 9) {
						System.out.print(" ");
					}		

					if (field[x][y].getGuessThisSquareIsBomb()) {
						System.out.print(" ^ ");
					}
					else if (boardDigit[x][y] == 10) {
					    System.out.print(" * ");
				    }
				    else {
				        System.out.print(" " + boardDigit[x][y] + " ");
				    }
				}
				else {
					if (x > 9) {
						System.out.print(" ");
					}
                    if (field[x][y].getBombExist()) {
                    	System.out.print(" ! ");
					}
                    else {
					    System.out.print(" * ");
                    }
				}
			}
			System.out.println();
		}
    }
    
    public void restartGame(Scanner scan, GameEndOrBegin gameEndOrBegin, String level) throws Exception {
        String restart = scan.next().toLowerCase();
	    if (restart.equals("yes")) {
		    new TextInterfaceController();
	    }
	    else if (restart.equals("no")) {
		    TextExit exit = new TextExit();
		    exit.doCommand(gameEndOrBegin, scan, level);
	    }
    }
    
    public void winMessage(Scanner scan, int boardHeight, int boardWidth) {
    	System.out.println("Enter your name:");
		String name = scan.next();
		new SaveNewScore(boardHeight, boardWidth, TimeCounter.getCostTime(), name);	
		System.out.println("You win this game in " + 
		      TimeConverter.calculateTime(TimeCounter.getCostTime()) + 
		      " Do you want to restart. Print yes or no: ");
    }
    
    public void loseMessage() {
    	System.out.println("You lose this game. Do you want to restart. Print yes or no: ");  
    }
    
    public void mineMarker(int x, int y) {     
    	if (!field[x][y].getGuessThisSquareIsBomb()) {
    		field[x][y].setGuessThisSquareIsBomb(true);
 	    }
 	
 		else if (field[x][y].getGuessThisSquareIsBomb()) {
 			field[x][y].setGuessThisSquareIsBomb(false);
	    }
    }
    
}

