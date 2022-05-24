package GraphicInterface.View.Menu;

import javax.swing.*;
import CommonClasses.Context;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicInterfaceMenu extends JFrame implements ActionListener
{
    private JButton start, about, record, exit;
    private JRadioButton beginner, intermediate, advanced; 
    public String action;
      
    public GraphicInterfaceMenu(String title) {
	
    	ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
    	setIconImage(icon.getImage());
   
        setTitle(title);

        JLabel subtitle = new JLabel("Choose level");
        
        subtitle.setBounds(50,10,100,20);
        add(subtitle);     

        beginner = new JRadioButton("Beginner");
        beginner.setBounds(40,40,150,20);
        add(beginner);

        JLabel beginnerFirstLine = new JLabel("10 mines");
        beginnerFirstLine.setBounds(70,60,100,20);
        JLabel beginnerSecondLine = new JLabel("10 x 10 size");
        beginnerSecondLine.setBounds(70,80,100,20);
        add(beginnerFirstLine);
        add(beginnerSecondLine);     

        intermediate = new JRadioButton("Intermediate");
        intermediate.setBounds(40,100,150,20);
        add(intermediate);

        JLabel intermediateFirstLine = new JLabel("40 mines");
        intermediateFirstLine.setBounds(70,120,100,20);
        JLabel intermediateSecondLine = new JLabel("16 x 16 size");
        intermediateSecondLine.setBounds(70,140,100,20);
        add(intermediateFirstLine);
        add(intermediateSecondLine);    

        advanced = new JRadioButton("Advanced");
        advanced.setBounds(40,160,160,20);
        add(advanced);

        JLabel advancedFirstLine = new JLabel("100 mines");
        advancedFirstLine.setBounds(70,180,100,20);
        JLabel advancedSecondLine = new JLabel("30 x 25 size");
        advancedSecondLine.setBounds(70,200,100,20);
        add(advancedFirstLine);
        add(advancedSecondLine);        
        
        start = new JButton("Game");
        start.setBounds(50,250,160,25);
        add(start);
        
        about = new JButton("About");
        about.setBounds(50,275,160,25);
        add(about);

        record = new JButton("Scores");
        record.setBounds(50,300,160,25);
        add(record);
        
        exit = new JButton("Exit");
        exit.setBounds(50,325,160,25);
        add(exit);

        beginner.addActionListener(this);
        intermediate.addActionListener(this);
        advanced.addActionListener(this);
        start.addActionListener(this);
        about.addActionListener(this);
        record.addActionListener(this);
        exit.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(beginner);
        group.add(intermediate);
        group.add(advanced);

        beginner.setSelected(true);
        
        setSize(300,450);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {   
    	
    	action = "";
    	String fileName = "";
        int boardWidth = 0;
        int boardHeight = 0;
        int bombs = 0;
        
        try {       
    	    if (e.getActionCommand() != "Intermediate" && e.getActionCommand() != "Beginner" && e.getActionCommand() != "Advanced") {  
		    
			    this.dispose();
			    
			    if (beginner.isSelected()) {
		            boardWidth = 10;
		            boardHeight = 10;
		            bombs = 10;
		            fileName = "beginner.txt";
		        } 
		        else if (intermediate.isSelected()) {
		            boardWidth = 16;
		            boardHeight = 16;
		            bombs = 40;
		            fileName = "intermediate.txt";
		        } 
		        else if (advanced.isSelected()) {
		            boardWidth = 30;
		            boardHeight = 25;
		            bombs = 100;
		            fileName = "advanced.txt";
		        } 
			    
			    action = e.getActionCommand().toLowerCase();
			    
		        Context context = new Context("graphic_config");
	            ButtonInterface command = (ButtonInterface)getOperationClass(action, context).getConstructor().newInstance();      
	            command.doCommand(boardWidth, boardHeight, bombs, fileName);
    	    }   	       			    
		}
		
		catch (Throwable e1) {
			e1.printStackTrace();
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
}
 
  