package TextInterface;

import java.util.Scanner;
import CommonClasses.Context;
import CommonClasses.SaveNewScore;
import CommonClasses.TimeConverter;
import CommonClasses.TimeCounter;
import CommonClasses.Model.BoardLogic;
import CommonClasses.Model.FieldChecker;
import CommonClasses.Model.GameEndOrBegin;
import CommonClasses.Model.ProduceBombs;
import TextInterface.View.Board.BoardView;
import TextInterface.View.Menu.CommandInterface;
import TextInterface.View.Menu.TextExit;

public class TextInterfaceController {
	
	private int boardHeight;
	private int boardWidth;
	private int bombs;	
	private BoardView board;
	private BoardLogic logic;
	private FieldChecker[][] field;	
	private GameEndOrBegin gameEndOrBegin;
		
	public TextInterfaceController() throws Exception {   
    	Scanner scan = new Scanner(System.in);

    	gameEndOrBegin = new GameEndOrBegin();
  	
        System.out.println("Chose the level. Write beginner, intermediate or advanced");
        
        String level = scan.next().toLowerCase();
        String fileName = "";
        
        if (level.equals("beginner")) {        	 
        	boardHeight = 10;
        	boardWidth = 10; 
        	bombs = 10;
        	fileName = "beginner.txt";
        }
        
        else if (level.equals("intermediate")) {
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
        
        this.field = new FieldChecker[boardWidth][boardHeight];
    	
    	for (int y = 0; y < boardHeight; y++) {
		    for (int x = 0; x < boardWidth; x++) {
			    field[x][y] = new FieldChecker(x, y);
		    }
	    }  	
    	 	
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
            	
                if (gameEndOrBegin.isTryFirst()) {                	
                	gameEndOrBegin.setTryFirst(false);
                	gameEndOrBegin.setGameExists(true);       		
        		    logic = new BoardLogic(boardHeight, boardWidth, x, y, bombs, field);
        		    board = new BoardView(boardHeight, boardWidth, logic.getBoard(), field);    
            	    new ProduceBombs(boardHeight, boardWidth, bombs, logic, field);           	                	    
            	    new TimeCounter(this, false);            	    
                }        
                
                field[x][y].setGuessThisSquareIsBomb(false);
          
        		if (field[x][y].getBombExist()) {	
        			gameEndOrBegin.setGame(false);
        			System.out.println("You lose this game. Do you want to restart. Print yes or no: ");  
        			
        			String restart = scan.next().toLowerCase();
    				if (restart.equals("yes")) {
    					board.printBoard(gameEndOrBegin.getGame()); 
    					new TextInterfaceController();
    				}
    				else if (restart.equals("no")) {
    					TextExit exit = new TextExit();
    					exit.doCommand(gameEndOrBegin, scan, level);
    				}
        		} 
        		
        		else {        			
        			field[x][y].setTraverse(false);        			
        			logic.countBomb(field[x][y].getxLocation(), field[x][y].getyLocation());
        			
        			if (logic.isSuccess()) {
        				System.out.println("Enter your name:");
        				String name = scan.next();
        				new SaveNewScore(boardHeight, boardWidth, TimeCounter.getCostTime(), name);	
        				System.out.println("You win this game in " + 
        				      TimeConverter.calculateTime(TimeCounter.getCostTime()) + 
        				      " Do you want to restart. Print yes or no: ");
        				
        				String restart = scan.next().toLowerCase();
        				if (restart.equals("yes")) {
        					board.printBoard(gameEndOrBegin.getGame()); 
        					new TextInterfaceController();
        				}
        				else if (restart.equals("no")) {
        					TextExit exit = new TextExit();
        					exit.doCommand(gameEndOrBegin, scan, level);
        				}
        			}
        		}        	
        		board.printBoard(gameEndOrBegin.getGame());         	
            }
            
            else if (action.equals("mine")) {
            	x = scan.nextInt();
             	y = scan.nextInt();
             	
             	if (logic.getBoardSquare(x, y) == 11) {
             		logic.setBoardSquare(x, y, 10);
             	}            	
             	else {
            	    logic.setBoardSquare(x, y, 11);
             	}
            	board.printBoard(gameEndOrBegin.getGame());
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
