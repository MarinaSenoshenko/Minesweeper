package GraphicInterface;

import javax.swing.*;
import CommonClasses.TimeConverter;
import CommonClasses.Model.FieldChecker;
import CommonClasses.Model.GameEndOrBegin;
import CommonClasses.Model.OpenCell;
import GraphicInterface.View.Board.GameSquare;
import GraphicInterface.View.Board.SmartBoard;
import GraphicInterface.View.Board.SmartSquare;
import GraphicInterface.View.Menu.ButtonInterface;
import GraphicInterface.View.Menu.GraphicInterfaceMenu;
import java.awt.*;
import java.awt.event.*;

public class GraphicInterfaceController implements ButtonInterface, ActionListener {
	
	private int boardHeight;
	private int boardWidth;
	private int bombs;
	private JPanel boardPanel = new JPanel();
	private JButton back = new JButton("back");
	private JLabel subtitle = new JLabel();
	private JFrame frame;
	private GameSquare[][] gameSquare;
	private SmartBoard smartBoard;
	private FieldChecker[][] field;	
	private GameEndOrBegin gameEndOrBegin;
	private OpenCell open;
	
	public GraphicInterfaceController() {
		super();
		gameEndOrBegin = new GameEndOrBegin();	
	}

	
	@Override
	public void doCommand(int width, int height, int bombs, String fileName) {	
		
		frame = new JFrame();
		
		this.boardWidth = width;
		this.boardHeight = height;		
		this.bombs = bombs;
		

		gameEndOrBegin.setTryFirst(true);
	
	    this.gameSquare = new GameSquare[width][height];    
	    this.field = new FieldChecker[width][height];

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        boardPanel.setLayout(new GridLayout(height, width));	

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				 field[x][y] = new FieldChecker(x, y);
				 gameSquare[x][y] = new SmartSquare(x, y, field[x][y], gameSquare, height, width);
				 gameSquare[x][y].addActionListener(this);				 
				   
				 boardPanel.add(gameSquare[x][y]);	
			}
		}
		
		smartBoard = new SmartBoard(height, width, gameSquare);	
				
		frame.add(boardPanel);
		
		frame.setSize(20 + width * 20, height * 20 + 80);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
		frame.setIconImage(icon.getImage());
    	
		frame.add(back, BorderLayout.NORTH);
    	back.addActionListener(this); 
    	
    	subtitle = new JLabel("tap to play!");
    	frame.add(subtitle, BorderLayout.AFTER_LAST_LINE);
     
    	frame.setVisible(true);
    	
    	open = new OpenCell(true, this, field, gameEndOrBegin, boardHeight, boardWidth, bombs);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			gameEndOrBegin.setGameExists(false);
			frame.dispose();
			new GraphicInterfaceMenu("Minesweeper");
		}
		else {			
		    GameSquare b = (GameSquare)e.getSource();
		    
		    int x = b.getxLocation();
		    int y = b.getyLocation();		    		
		    
		    open.open(x, y);
		    smartBoard.game(open.getLogic().getBoard());
		    
		    if (open.getIsLose()) {
				((SmartSquare)b).loseWindow(frame);
			     frame.dispose();
			} 
		
		    if (open.isSuccess()) {
		    	gameEndOrBegin.setGameExists(false);
		    	((SmartSquare)b).winWindow(frame);
		    	frame.dispose();
			}
		}
	}		
	
	public void printTimeInSec(long costTime) {
	    if (costTime % 1000 <= 1) {
	    	subtitle.setVisible(false);
            subtitle = new JLabel(TimeConverter.calculateTime(costTime / 1000 * 1000));
            frame.add(subtitle, BorderLayout.AFTER_LAST_LINE);
            frame.setVisible(true);
	    }
	}
	
	public GameEndOrBegin getGameEndOrBegin() {
		return gameEndOrBegin;
	}
}
