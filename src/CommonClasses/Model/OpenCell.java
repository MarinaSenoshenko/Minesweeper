package CommonClasses.Model;

import CommonClasses.TimeCounter;

public class OpenCell {
	private FieldChecker[][] field;	
	private BoardLogic logic;	
	private GameEndOrBegin gameEndOrBegin;
	private int boardHeight;
	private int boardWidth;
	private int bombs;
	private Object interf;
	private boolean is;
	private boolean isLose;
	
	
    public OpenCell(boolean is, Object interf, FieldChecker[][] field, GameEndOrBegin gameEndOrBegin, int boardHeight, int boardWidth, int bombs) {
    	this.field = field;
    	this.gameEndOrBegin = gameEndOrBegin;
    	this.boardHeight = boardHeight; 
    	this.boardWidth = boardWidth;
    	this.bombs = bombs;
    	this.interf = interf;
    	this.is = is;
    	isLose = false;
    }
    
    
    
    public void open(int x, int y) {
    	if (gameEndOrBegin.isTryFirst()) {
	    	gameEndOrBegin.setGameExists(true);
	    	gameEndOrBegin.setTryFirst(false);	
		    logic = new BoardLogic(boardHeight, boardWidth, x, y, bombs, field);		
			new ProduceBombs(boardHeight, boardWidth, bombs, logic, field);	
			new TimeCounter(interf, is);		    	
		}	    		

	    field[x][y].setTraverse(false);
		
	    logic.countBomb(x, y);				
	    
	    if (field[x][y].getBombExist()) {
	    	gameEndOrBegin.setGameExists(false);
	    	isLose = true;
		} 
	
	    if (isSuccess()) {
	    	gameEndOrBegin.setGameExists(false);
		} 
	}
    
    public boolean isSuccess() {
        int count = 0;

        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                if (field[x][y].getTraverse()) {
                    count++;
                }
            }
        }

        return count == boardHeight * boardWidth;
    }
    
    public boolean getIsLose() {
    	return isLose;
    }
    
    public BoardLogic getLogic() {
    	return logic;
    }
    
}
