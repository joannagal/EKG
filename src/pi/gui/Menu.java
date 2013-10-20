package pi.gui;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;


public class Menu extends JMenuBar {
		
	/*
	 * projectMenu enables user to create a new project, open already existing project, 
	 * save changes and close the project.
	 */
	private JMenu projectMenu;
	private JMenuItem createProjectItem;
	private JMenuItem openProjectItem;
	private JMenuItem saveProjectItem;
	private JMenuItem saveAsProjectItem;
	private JMenuItem closeProjectItem;
		
	/*
	 * mainMenu enables users to log out, set preferences, get information 
	 * about application and close the application.
	 */
	private JMenu mainMenu;
	private JMenuItem logOutItem;
	private JMenuItem preferencesItem;
	private JMenuItem closeAppItem;
	private JMenuItem aboutAppItem;
	
	public Menu(){
		
		mainMenu = new JMenu("Application");
		projectMenu = new JMenu("Project");
		this.add(projectMenu);
		this.add(mainMenu);		
		setProjectMenuItems();
		setMainMenuItems();
		
	}
	
	public void setProjectMenuItems(){		
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
	
	public void setMainMenuItems(){
		preferencesItem = new JMenuItem("Preferences");
		aboutAppItem = new JMenuItem("About application");
		closeAppItem = new JMenuItem("Exit");
		logOutItem = new JMenuItem("Log Out");
		
		mainMenu.add(preferencesItem);
		mainMenu.add(new JSeparator());
		
		mainMenu.add(aboutAppItem);
		mainMenu.add(new JSeparator());

		mainMenu.add(closeAppItem);
		mainMenu.add(new JSeparator());

		mainMenu.add(logOutItem);

	}
	
}
