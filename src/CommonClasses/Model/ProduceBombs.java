package CommonClasses.Model;

import java.util.Random;

public class ProduceBombs {
	
	private int boardHeight;
	private int boardWidth;	
	private boolean[][] isBombTakePlace;
	private boolean[][] isTraverse;		
	private FieldChecker[][] field;	
	private BoardLogic logic;	
	private static final int[] distantX = {-1, 0, 1};
	private static final int[] distantY = {-1, 0, 1};

    public ProduceBombs(int boardHeight, int boardWidth, int number, BoardLogic logic, FieldChecker[][] field) {

    	this.boardHeight = boardHeight;
    	this.boardWidth = boardWidth;       
        this.logic = logic;
        
        isBombTakePlace = new boolean[boardWidth][boardHeight];
        isTraverse = new boolean[boardWidth][boardHeight];
        
        this.field = field;
                        
        int count = 0;

        do {
            reproduceBomb();
            count++;
        }       
        while (count < number);
    }

    public void reproduceBomb() {

        Random r = new Random();

        int x = r.nextInt(boardWidth);
        int y = r.nextInt(boardHeight);
        boolean canBombTakePlace = false;
 
        if (checkArround(x, y)) {       	
        	
            if (isBombTakePlace[x][y] == false) {
            	canBombTakePlace = true;
            	isBombTakePlace[x][y] = true;
            	isTraverse[x][y] = true;
                field[x][y].setBombExist(true);
                field[x][y].setTraverse(true); 
            } 
            
        }
        if (!canBombTakePlace) {
            reproduceBomb();
        }
    }

    
    private boolean checkArround(int xLocation, int yLocation) {
        for (int x : distantX) {        	 
            for (int y : distantY) {
            	if (xLocation == logic.getFirstCellX() + x && yLocation == logic.getFirstCellY() + y) {;
        		    return false;
        	    } 
            }
        }
        return true;
    }
}
