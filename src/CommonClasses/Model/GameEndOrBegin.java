package CommonClasses.Model;

public class GameEndOrBegin {
	
	private boolean isGameExists;
	private boolean isTryFirst;
	private boolean game;
	
    public GameEndOrBegin() {
    	isGameExists = false;
    	isTryFirst = true;
    	game = false;
    }    
    
    public void setGameExists(boolean isGameExists) {
		this.isGameExists = isGameExists;
	}

    public void setTryFirst(boolean isTryFirst) {
		this.isTryFirst = isTryFirst;
	}

	public boolean isGameExists() {
		return isGameExists;
	}
	
	public boolean isTryFirst() {
		return isTryFirst;
	}
	
	public boolean getGame() {
		return game;
	}
	
	public void setGame(boolean game) {
		this.game = game;
	}
}
