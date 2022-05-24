package GraphicInterface.View.Board;

import javax.swing.*;
import java.net.URL;

public abstract class GameSquare extends JButton {
	
	private int xLocation;
	private int yLocation;

	public GameSquare(int x, int y, URL filename) {
		super(new ImageIcon(filename));

		this.xLocation = x;
		this.yLocation = y;
	}

	public void setImage(URL filename) {
		this.setIcon(new ImageIcon(filename));
	}

	public int getxLocation() {
		return xLocation;
	}

	public int getyLocation() {
		return yLocation;
	}
}
