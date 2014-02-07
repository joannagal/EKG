package pi.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pi.gui.menu.MenuController;
import pi.gui.menu.MenuView;
import pi.shared.SharedController;

public class OurFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private MenuView menubar;
	private MenuController menuController;
	private JPanel content;
	private ComponentListener cl = new ComponentListener() {

		@Override
		public void componentShown(ComponentEvent e) {
		}

		@Override
		public void componentResized(ComponentEvent e) {
			JPanel source = (JPanel) e.getSource();
			source.setPreferredSize(source.getSize());
		}

		@Override
		public void componentMoved(ComponentEvent e) {
		}

		@Override
		public void componentHidden(ComponentEvent e) {
		}
	};

	public OurFrame() {

		this.setTitle("ECG Analyzer");

		URL iconURL = getClass().getResource("../../images/logo1.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) dimension.getWidth() - 50;
		int y = (int) dimension.getHeight() - 50;
		this.setSize(new Dimension(x, y));
		getContentPane().setLayout(new BorderLayout());

		initContent();

		SharedController.getInstance().setFrame(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		setMenubar(new MenuView(this));
		this.setJMenuBar(getMenubar());
		setMenuController(new MenuController(getMenubar()));
		this.pack();

	}

	public void initContent() {
		content = new JPanel();
		content.setVisible(true);
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.addComponentListener(cl);

		getContentPane().add(content, BorderLayout.CENTER);
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

	public JPanel getContent() {
		return content;
	}

	public void setContent(JPanel content) {
		this.content = content;
	}

}
