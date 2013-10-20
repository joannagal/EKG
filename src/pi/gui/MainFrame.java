package pi.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class MainFrame extends JFrame{
	
	public MainFrame(){
		
		super("ECG Analyzer");
		setSize(1000, 1000);	
		setVisible(true);
		
		Menu menu = new Menu();
		setJMenuBar(menu);
			
	}
}
