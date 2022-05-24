package GraphicInterface.View.Menu;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GraphicAbout extends JFrame implements ActionListener, ButtonInterface {
	
	private JButton back = new JButton("back");
	private JPanel panel = new JPanel();
	
	public GraphicAbout() throws FileNotFoundException {
		super();
	}
	
	@Override
	public void doCommand(int width, int height, int bombs, String fileName) throws FileNotFoundException {
		
		Scanner scan = new Scanner(new File("about.txt"));
		String str = "";

		while (scan.hasNext()) {
			str += scan.nextLine() + "\n";
		}
    
	    JTextArea area1 = new JTextArea(str, 25, 30);        
        area1.setFont(new Font("Dialog", Font.PLAIN, 10));
        area1.setTabSize(10);
        area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
           
        panel.add(new JScrollPane(area1));
        setContentPane(panel);
        
        add(back, BorderLayout.NORTH);
        back.addActionListener(this);
	    
	    area1.setEditable(false);         
	    
		setSize(350, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
    	setIconImage(icon.getImage());	
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			this.dispose();
			new GraphicInterfaceMenu("Minesweeper");
		}
	}
}
