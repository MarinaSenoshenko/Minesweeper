package GameDriver;

import GraphicInterface.View.Menu.GraphicInterfaceMenu;
import TextInterface.TextInterfaceController;

public class Driver
{
	public static void main(String[] Args) throws Exception
	{
		new GraphicInterfaceMenu("Minesweeper");
		//new TextInterfaceController();
	}
}

