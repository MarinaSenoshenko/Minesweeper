package CommonClasses;


import GraphicInterface.GraphicInterfaceController;
import TextInterface.TextInterfaceController;

public class TimeCounter {
	
	private static long costTime;
	private long startTime;
	private boolean isGraphic;
	private boolean play;	
	
    public TimeCounter(Object game, boolean _isGraphic) {
    	new Thread(new Runnable() {
            public void run() {
            	startTime = System.currentTimeMillis();
            	isGraphic = _isGraphic;
            	play = true;
            	try {
                    while (play) {                     
                        Thread.sleep(1); 
                        costTime = System.currentTimeMillis() - startTime;
    
                        if (isGraphic) {
                        	play = ((GraphicInterfaceController) game).getGameEndOrBegin().isGameExists();
                        	((GraphicInterfaceController) game).printTimeInSec(costTime);
                        }
                        else {
                        	play = ((TextInterfaceController) game).getGameEndOrBegin().isGameExists();
                        }
                    }
                    
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }).start();
    }
    

    public static long getCostTime() {
    	return costTime;
    }
}
