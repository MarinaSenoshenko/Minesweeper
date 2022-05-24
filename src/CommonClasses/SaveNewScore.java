package CommonClasses;

import java.io.IOException;

public class SaveNewScore {
	
	 private int height;
	 private int width; 
	 private long costTime;
	 private String name;
	
	public SaveNewScore(int height, int width, long costTime, String name) {
		this.height = height;
		this.width = width; 
		this.costTime = costTime;
		this.name = name;
		
		checkField();
	}
	
    public void checkField() {
    	
    	double time = (double)costTime / 1000;	
		
		String fileName = "beginner.txt";
		
		if (height == 16 && width == 16) {
        	fileName = "intermediate.txt";
		}
        
        else if (height == 25 && width == 30) {
        	fileName = "advanced.txt";
		}
		
		HighScoresTable t = new HighScoresTable(fileName);
		
		try {
			t.addNew(time, saveRecord(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public String saveRecord(String name) {
		if (name == null) {
			return "admin";
		}		
		
		return name;
	}
}
