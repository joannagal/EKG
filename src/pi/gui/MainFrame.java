package pi.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class MainFrame extends JFrame{
	
	private JPanel cardPanel;
	private LoginDialog loginDialog;
	private ProjectDialog projectDialog;
	
	public MainFrame(){
		
		super("ECG Analyzer");
		setSize(1000, 1000);	
		setVisible(true);
		Menu menu = new Menu();
		setJMenuBar(menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
