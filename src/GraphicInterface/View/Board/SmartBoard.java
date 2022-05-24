package GraphicInterface.View.Board;

import CommonClasses.Model.BoardLogic;

public class SmartBoard {
	
	private int boardHeight;
	private int boardWidth;
	private GameSquare[][] gameSquare;
	
	public SmartBoard(int boardHeight, int boardWidth, GameSquare[][] gameSquare) {	
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.gameSquare = gameSquare;
	}

	
	public void game(BoardLogic cq) {
		for (int y = 0; y < boardHeight; y++) {
			for (int x = 0; x < boardWidth; x++) {
				if (cq.getBoardSquare(x, y) != 10) {
					SmartSquare currentObject = ((SmartSquare)gameSquare[x][y]);
			        currentObject.setImage(SmartSquare.class.getResource( "/" + cq.getBoardSquare(x, y) + ".png"));
				}
			}
		}
	}
}
