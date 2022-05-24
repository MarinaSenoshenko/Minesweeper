package GraphicInterface;

import javax.swing.*;

import CommonClasses.TimeConverter;
import CommonClasses.TimeCounter;
import CommonClasses.Model.BoardLogic;
import CommonClasses.Model.FieldChecker;
import CommonClasses.Model.GameEndOrBegin;
import CommonClasses.Model.ProduceBombs;
import GraphicInterface.View.Board.GameSquare;
import GraphicInterface.View.Board.SmartBoard;
import GraphicInterface.View.Board.SmartSquare;
import GraphicInterface.View.Menu.ButtonInterface;
import GraphicInterface.View.Menu.GraphicInterfaceMenu;

import java.awt.*;
import java.awt.event.*;

public class GraphicInterfaceController extends JFrame implements ButtonInterface, ActionListener {
	
	private int boardHeight;
	private int boardWidth;
	private int bombs;
	private JPanel boardPanel = new JPanel();
	private JButton back = new JButton("back");
	private JLabel subtitle = new JLabel();
	private GameSquare[][] gameSquare;
	private SmartBoard smartBoard;
	private FieldChecker[][] field;	
	private BoardLogic logic;	
	private GameEndOrBegin gameEndOrBegin;
	
	public GraphicInterfaceController() {
		super();
		gameEndOrBegin = new GameEndOrBegin();	
	}

	
	@Override
	public void doCommand(int width, int height, int bombs, String fileName) {		
		
		this.boardWidth = width;
		this.boardHeight = height;		
		this.bombs = bombs;

		gameEndOrBegin.setTryFirst(true);
	
	    this.gameSquare = new GameSquare[width][height];    
	    this.field = new FieldChecker[width][height];

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
				
		add(boardPanel);
		
		setSize(20 + width * 20, height * 20 + 80);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
    	setIconImage(icon.getImage());
    	
    	add(back, BorderLayout.NORTH);
    	back.addActionListener(this); 
    	
    	subtitle = new JLabel("tap to play!");
        add(subtitle, BorderLayout.AFTER_LAST_LINE);
     
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			gameEndOrBegin.setGameExists(false);
			this.dispose();
			new GraphicInterfaceMenu("Minesweeper");
		}
		else {			
		    GameSquare b = (GameSquare)e.getSource();
		    
		    int x = b.getxLocation();
		    int y = b.getyLocation();		    
		    
		    if (gameEndOrBegin.isTryFirst()) {
		    	gameEndOrBegin.setGameExists(true);
		    	gameEndOrBegin.setTryFirst(false);		    	
				logic = new BoardLogic(boardHeight, boardWidth,  x, y, bombs, field);
				new ProduceBombs((this.getHeight() - 20) / 20 - 3, (this.getWidth() - 20) / 20, bombs, logic, field);				
				new TimeCounter(this, true);		    	
			}	    		
		    
		    field[x][y].setGuessThisSquareIsBomb(false);	
		    field[x][y].setTraverse(false);
			
		    logic.countBomb(field[x][y].getxLocation(), field[x][y].getyLocation());				
			smartBoard.game(getLogic());
		    
		    if (field[x][y].getBombExist()) {
		    	gameEndOrBegin.setGameExists(false);
				((SmartSquare)b).loseWindow();
				window("You lose. Do you want to try again?", "Game Over",
						new ImageIcon(SmartSquare.class.getResource("/failFace.png")));	
				dispose();
			} 
				
		    if (logic.isSuccess()) {
		    	gameEndOrBegin.setGameExists(false);
		    	((SmartSquare)b).winWindow();
		    	window("You win this game in " + TimeConverter.calculateTime(TimeCounter.getCostTime()) +
		                "! Do you want to try again?","Congratulations",
						new ImageIcon(SmartSquare.class.getResource("/passFace.jpg"))); 
		    	dispose();
			}		    
		}
	}
		
	
	public void printTimeInSec(long costTime) {
	    if (costTime % 1000 <= 1) {
	    	subtitle.setVisible(false);
            subtitle = new JLabel(TimeConverter.calculateTime(costTime / 1000 * 1000));
            add(subtitle, BorderLayout.AFTER_LAST_LINE);
            setVisible(true);
	    }
	}
	
	public void window(String msg, String title, Icon img) {
		int choose = JOptionPane.showConfirmDialog(this, msg, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,img);

		if (choose == JOptionPane.YES_OPTION) {
			new GraphicInterfaceMenu("Minesweeper");
		}
	}
	
	public GameSquare getSquareAt(int x, int y) {
		if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
			return null;
		}
		return gameSquare[x][y];
	} 
	
	public BoardLogic getLogic() {
		return logic;
	}
	
	public SmartBoard getSmartBoard(){
		return smartBoard;
	}
	
	public GameEndOrBegin getGameEndOrBegin() {
		return gameEndOrBegin;
	}
}
