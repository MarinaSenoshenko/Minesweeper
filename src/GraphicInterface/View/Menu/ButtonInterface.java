package GraphicInterface.View.Menu;

import java.io.FileNotFoundException;

public interface ButtonInterface {	
      public void doCommand(int width, int height, int bombs, String fileName) throws FileNotFoundException;
}
