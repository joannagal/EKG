package pi.gui.menu;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

import pi.shared.SharedController;

public class MenuView extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private JMenu projectMenu;
	private JMenuItem createProjectItem;
	private JMenuItem openProjectItem;
	private JMenuItem saveProjectItem;
	private JMenuItem saveAsProjectItem;
	private JMenuItem closeProjectItem;

	private JMenu mainMenu;
	private JMenuItem logItem;
	private JMenuItem closeAppItem;
	private JMenuItem aboutAppItem;
	private JMenuItem[] menuItemsArray;
	private String[] menuItemEvent = new String[] { "ABOUT", "EXIT", "LOG",
			"CREATE_PROJECT", "OPEN_PROJECT", "SAVE_AS_PROJECT",
			"SAVE_PROJECT", "CLOSE_PROJECT" };
	private JFrame parent;

	private JFileChooser fileChooser;

	public MenuView(JFrame frame) {

		this.parent = frame;
		mainMenu = new JMenu("Application");
		projectMenu = new JMenu("Project");
		this.add(projectMenu);
		this.add(mainMenu);
		setProjectMenuItems();
		setMainMenuItems();
		menuItemsArray = new JMenuItem[] { aboutAppItem, closeAppItem, logItem,
				createProjectItem, openProjectItem, saveAsProjectItem,
				saveProjectItem, closeProjectItem };
		setEditable(false);
		//this.setInProject(false);
	}

	public void closeParent() {
		this.parent.dispose();
	}

	public void setProjectMenuItems() {
		createProjectItem = new JMenuItem("New");
		openProjectItem = new JMenuItem("Open");
		saveProjectItem = new JMenuItem("Save");
		saveAsProjectItem = new JMenuItem("Save as...");
		closeProjectItem = new JMenuItem("Close");

		projectMenu.add(createProjectItem);
		projectMenu.add(new JSeparator());

		projectMenu.add(openProjectItem);
		projectMenu.add(new JSeparator());

		projectMenu.add(saveProjectItem);
		projectMenu.add(new JSeparator());

		projectMenu.add(saveAsProjectItem);
		projectMenu.add(new JSeparator());

		projectMenu.add(closeProjectItem);

	}

	public void setMainMenuItems() {
		aboutAppItem = new JMenuItem("About application");
		closeAppItem = new JMenuItem("Exit");
		logItem = new JMenuItem();

		mainMenu.add(aboutAppItem);
		mainMenu.add(new JSeparator());

		mainMenu.add(closeAppItem);
		mainMenu.add(new JSeparator());

		mainMenu.add(logItem);

	}

	public void setEditable(boolean value) {
		projectMenu.setEnabled(value);
	}

	public void setMenuItemListener(MenuController al) {
		for (int i = 0; i < menuItemsArray.length; i++) {
			menuItemsArray[i].setActionCommand(menuItemEvent[i]);
			menuItemsArray[i].addActionListener(al);
		}
	}

	public void setLogItemText() {
		if (SharedController.getInstance().isLogged()) {
			logItem.setText("Log out");
		} else {
			logItem.setText("Log in");
		}
	}

	public void setInProject(boolean value) {
		this.saveProjectItem.setEnabled(value);
		this.saveAsProjectItem.setEnabled(value);
		this.closeProjectItem.setEnabled(value);
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}
	
	public void initFileChooser(){
		this.fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"XML files (*.xml)", "xml");
		getFileChooser().setFileFilter(filter);
		getFileChooser().addChoosableFileFilter(filter);
		getFileChooser().setSelectedFile(new File("*.xml"));
		getFileChooser().setFileSelectionMode(JFileChooser.FILES_ONLY);
	}
}
