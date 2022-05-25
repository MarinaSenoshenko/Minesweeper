package TextInterface;

import java.util.Scanner;
import CommonClasses.Context;
import CommonClasses.Model.FieldChecker;
import CommonClasses.Model.GameEndOrBegin;
import CommonClasses.Model.OpenCell;
import TextInterface.View.Board.BoardView;
import TextInterface.View.Menu.CommandInterface;

public class TextInterfaceController {	
	private int boardHeight;
	private int boardWidth;
	private int bombs;	
	private BoardView board;
	private FieldChecker[][] field;	
	private GameEndOrBegin gameEndOrBegin;
	private OpenCell open;
		
	public TextInterfaceController() throws Exception {   
    	Scanner scan = new Scanner(System.in);
    	gameEndOrBegin = new GameEndOrBegin(); 	
        System.out.println("Chose the level. Write beginner, intermediate or advanced");

        boardHeight = 10;
        boardWidth = 10; 
        bombs = 10;
        String fileName = "beginner.txt";
        
        String level = scan.next().toLowerCase();
  
        if (level.equals("intermediate")) {
        	boardHeight = 16;
        	boardWidth = 16; 
        	bombs = 40;
        	fileName = "intermediate.txt";
        }
 
        else if (level.equals("advanced")) {
        	boardHeight = 30;
        	boardWidth = 25; 
        	bombs = 100;
        	fileName = "advanced.txt";
        }
        
        else if (!level.equals("beginner")) {
        	System.out.println("Unknown level. We set you the level beginner");
        }
        
        this.field = new FieldChecker[boardWidth][boardHeight];
    	
    	for (int y = 0; y < boardHeight; y++) {
		    for (int x = 0; x < boardWidth; x++) {
			    field[x][y] = new FieldChecker(x, y);
		    }
	    }  	
    	
    	open = new OpenCell(false, this, field, gameEndOrBegin, boardHeight, boardWidth, bombs);
    	board = new BoardView(boardHeight, boardWidth, field); 
        Action(scan, level, fileName);      
       
    }	
	
	private void Action(Scanner scan, String level, String fileName) throws Exception {
		int x, y;
        Context context = new Context("text_config");   
        System.out.println("Start. Chose the option. Write Mine - to mark mine, Cell - to open cell, Exit - to exit, About - to read about the game, New - to play a new game, Scores - to watch record table");    	
        gameEndOrBegin.setGame(true);
        
        while (gameEndOrBegin.getGame()) {
        	System.out.println("Chose the option:");
            String action = scan.next().toLowerCase();
	
            if (action.equals("cell")) {
            	
            	x = scan.nextInt();
             	y = scan.nextInt();
             	
             	if (x < boardWidth && y < boardHeight) { 	           		             		
             		
        		    open.open(x, y);
        		    board.printBoard(gameEndOrBegin.getGame(), open.getLogic().getBoard()); 
        		    
        		    if (open.getIsLose()) {	
            			board.loseMessage();
            			board.restartGame(scan, gameEndOrBegin, level);
            		} 
            		
        			if (open.isSuccess()) {
        				board.winMessage(scan, boardHeight, boardWidth);
        				board.restartGame(scan, gameEndOrBegin, level);
        			}        		    
            		  
             	}
             	else {
             		System.out.println("Incorrect x or y");
             	}
            }          
            
            else if (action.equals("mine")) {
            	x = scan.nextInt();
             	y = scan.nextInt();
             	
             	if (x < boardWidth && y < boardHeight) {
             		board.mineMarker(x, y);             	   
            	    board.printBoard(gameEndOrBegin.getGame(), open.getLogic().getBoard());
             	}
             	else {
             		System.out.println("Incorrect x or y");
             	}
            }
            else {
            	try {
                    CommandInterface command = (CommandInterface)getOperationClass(action, context).getConstructor().newInstance();      
                    command.doCommand(gameEndOrBegin, scan, level);
            	}
            	catch (Throwable e) {
            		System.out.println("Unknown command. Repeat please");
            	}
            }
        }
	}	
        
    public Class<?> getOperationClass(String name, Context context) throws Exception  {
        name = name.toLowerCase();
        if (context.getHashMap().containsKey(name)) {
            return context.getHashMap().get(name);
        }    
        else {
            throw new Exception();
        }
    }
    
    public GameEndOrBegin getGameEndOrBegin() {
		return gameEndOrBegin;
	}
}
