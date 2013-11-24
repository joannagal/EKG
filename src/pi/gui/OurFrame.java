package pi.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import pi.gui.menu.MenuController;
import pi.gui.menu.MenuView;
import pi.shared.SharedController;

public class OurFrame extends JFrame{
	
	private MenuView menubar;
	private MenuController menuController;
	
	public OurFrame(){
		
		this.setLocation(100, 0);
		this.setLayout(null);
		
		SharedController.getInstance().setFrame(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true); 
    
		setMenubar(new MenuView(this));
		this.setJMenuBar(getMenubar());
		setMenuController(new MenuController(getMenubar()));
		this.setMinimumSize(new Dimension(1140,1000));
	}

	public MenuView getMenubar() {
		return menubar;
	}

	public void setMenubar(MenuView menubar) {
		this.menubar = menubar;
	}

	public MenuController getMenuController() {
		return menuController;
	}

	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}
}
