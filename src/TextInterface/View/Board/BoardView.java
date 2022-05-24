package TextInterface.View.Board;

import CommonClasses.Model.FieldChecker;

public class BoardView {
	
	private int height; 
	private int width;
	private int[][] boardDigit;	
	private FieldChecker[][] field;
	
    public BoardView(int height, int width, int[][] boardDigit, FieldChecker[][] field) {
    	this.height = height; 
    	this.width = width;
    	this.field = field;
    	this.boardDigit = boardDigit;
		
	}
    
    public void printBoard(boolean game) {
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
					if (boardDigit[x][y] == 11) {
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
}
