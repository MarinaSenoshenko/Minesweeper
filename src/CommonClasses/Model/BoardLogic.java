package CommonClasses.Model;

public class BoardLogic {
	
	private int boardHeight;
	private int boardWidth;
	private int firstCellY;
	private int firstCellX;	
	private int[][] boardDigit;	
	private FieldChecker[][] field;
	    
	private static final int[] distantX = {-1, 0, 1};
	private static final int[] distantY = {-1, 0, 1};
	
	public BoardLogic(int boardHeight, int boardWidth, int firstCellX, int firstCellY, int bombs, FieldChecker[][] field) {
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.firstCellY = firstCellY;
		this.firstCellX = firstCellX;
		
		this.field = field;
		
		boardDigit = new int[boardWidth][boardHeight];
		
		for (int y = 0; y < boardHeight; y++) {
    		for (int x = 0; x < boardWidth; x++) {
    			boardDigit[x][y] = 10;
        	}
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
	
	private boolean hasKickedBoundary(int x, int y) {
	     return x < 0 || x >= boardWidth || y < 0 || y >= boardHeight;
	}

	public void countBomb(int currentX, int currentY) {
	    int count = 0;

	    if (hasKickedBoundary(currentX, currentY)) {
	        return; 
	    }
	    else if (field[currentX][currentY].getTraverse()) {
	        return;
	    }
	    else {
	        field[currentX][currentY].setTraverse(true);

	        for (int x : distantX) {
	            for (int y: distantY) {
	                if (hasKickedBoundary(currentX + x, currentY + y)) {
	                    continue;
	                }
	                else if (x == 0 && y == 0) {
	                    continue;
	                }
	                else {
	                    count = field[currentX + x][currentY + y].getBombExist() ? count + 1 : count;
	                }
	            }
	        }
	    }
	    
	    boardDigit[currentX][currentY] = count;
	        
	    if (count == 0) {        	
	         for (int x : distantX) {        	 
	             for (int y : distantY) {
	                countBomb(currentX + x, currentY + y); 
	            }
	        }
	    }
	}

	public int getFirstCellY() {
		return firstCellY;
	}

	public int getFirstCellX() {
		return firstCellX;
	}
	
	public int[][] getBoard() {
		return boardDigit;
	}
	
	public int getBoardSquare(int x, int y) {
		if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
			return 0;
		}
		return boardDigit[x][y];
	}
	
	public void setBoardSquare(int x, int y, int digit) {
		if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
			return;
		}
		boardDigit[x][y] = digit;
	}
}
