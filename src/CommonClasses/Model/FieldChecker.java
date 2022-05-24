package CommonClasses.Model;

public class FieldChecker {
	
	private int xLocation;
	private int yLocation;
	private boolean thisSquareHasBomb;
	private boolean guessThisSquareIsBomb;
	private boolean thisSquareHasTraversed;	
	
	public FieldChecker(int xLocation, int yLocation) {
		thisSquareHasBomb = false;
		thisSquareHasTraversed = false;
		guessThisSquareIsBomb = false;
		
		this.xLocation = xLocation;
		this.yLocation = yLocation;
	}

	protected void setBombExist(boolean result) {
		thisSquareHasBomb = result;
	}
	
	public boolean getBombExist() {
		return thisSquareHasBomb;
	}
	
	public boolean getTraverse() {
		return thisSquareHasTraversed;
	}
	
	public void setTraverse(boolean result) {
		thisSquareHasTraversed = result;
	}
	
	public boolean getGuessThisSquareIsBomb() {
		return guessThisSquareIsBomb;
	}
	
	public void setGuessThisSquareIsBomb(boolean guessThisSquareIsBomb) {
		this.guessThisSquareIsBomb = guessThisSquareIsBomb;
	}
	
	public int getxLocation() {
		return xLocation;
	}
	
	public int getyLocation() {
		return yLocation;
	}
}
