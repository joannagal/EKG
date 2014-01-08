package pi.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JScrollPane;

import pi.gui.menu.MenuController;
import pi.gui.menu.MenuView;
import pi.shared.SharedController;

public class OurFrame extends JFrame {

	private MenuView menubar;
	private MenuController menuController;
	private JScrollPane content;

	public OurFrame() {

		this.setLocation(100, 0);
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		getContentPane().setLayout(new BorderLayout(5,5));
				//new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		setContent(new JScrollPane());
		//getContent().setBackground(Color.white);
		getContent().setVisible(true);
		//getContent().setLayout(new BoxLayout(getContent(), BoxLayout.PAGE_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(getContent());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		SharedController.getInstance().setFrame(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		setMenubar(new MenuView(this));
		this.setJMenuBar(getMenubar());
		setMenuController(new MenuController(getMenubar()));
		// this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		// JScrollPane pane = new
		// JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// this.setContentPane(pane);
		// this.setSize(new Dimension(1140,1000));
		this.setMinimumSize(new Dimension(1140, 1000));

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

	public JScrollPane getContent() {
		return content;
	}

	public void setContent(JScrollPane content) {
		this.content = content;
	}
}
