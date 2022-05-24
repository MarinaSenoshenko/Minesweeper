package GraphicInterface.View.Board;

import javax.swing.*;
import CommonClasses.SaveNewScore;
import CommonClasses.TimeConverter;
import CommonClasses.TimeCounter;
import CommonClasses.Model.FieldChecker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SmartSquare extends GameSquare implements MouseListener, TimeConverter {	
	
	private int boardHeight;
	private int boardWidth;
	private FieldChecker field;
	private GameSquare[][] gameSquare;	
	
	public SmartSquare(int x, int y, FieldChecker field, GameSquare[][] gameSquare, int boardHeight, int boardWidth) {
		super(x, y, SmartSquare.class.getResource("/block.png"));		
		this.field = field;;				
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;	
		this.gameSquare = gameSquare;
	
		addMouseListener(this);
	}

	public void loseWindow() {		
		setImage(SmartSquare.class.getResource("/bombReveal.png"));
		showBomb(field.getxLocation(), field.getyLocation());	
	}
	
	public void winWindow() {		
		showBomb(field.getxLocation(), field.getyLocation());			    
	    String name = JOptionPane.showInputDialog("Enter your name:", null);		    
		new SaveNewScore(boardHeight, boardWidth, TimeCounter.getCostTime(), name);	
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			int clickCount = e.getClickCount();

			if (clickCount == 1) {
				setImage(SmartSquare.class.getResource("/redFlag.png"));
				field.setGuessThisSquareIsBomb(true);
			}

			if (clickCount == 2) {
				setImage(SmartSquare.class.getResource("/questionMark.png"));
				field.setGuessThisSquareIsBomb(true);
			}
		}
	}
	
	public void showBomb(int currentX, int currentY) {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                if (currentX == x && currentY == y){
                	continue;
                }
                else if (((SmartSquare) gameSquare[x][y]).field.getBombExist()) {
                	gameSquare[x][y].setImage(SmartSquare.class.getResource("/bomb.png"));
                }
                else if (((SmartSquare) gameSquare[x][y]).field.getGuessThisSquareIsBomb()) {
                	gameSquare[x][y].setImage(SmartSquare.class.getResource("/flagWrong.png")); 
                }
            }
        }
    }
	

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}