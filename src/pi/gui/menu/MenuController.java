package pi.gui.menu;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import pi.data.importer.open.OpenPopulationController;
import pi.data.importer.open.OpenPopulationView;
import pi.data.importer.save.SavePopulationView;
import pi.gui.OurFrame;
import pi.gui.login.LoginDialog;
import pi.project.ChooseProjectController;
import pi.project.ChooseProjectView;
import pi.project.Project;
import pi.shared.SharedController;

public class MenuController implements ActionListener{

	MenuView menuView;
	
	public MenuController(MenuView view){
		this.menuView = view;
		
		menuView.setMenuItemListener(this);
	}
	
	public void actionPerformed(ActionEvent ae){
		
		String action = ae.getActionCommand();	
		if (action.equals("LOG")){
			if (!SharedController.getInstance().isLogged()){
				pi.gui.login.LoginDialog login = new pi.gui.login.LoginDialog();
				login.setLocation(400,300);
			}
			else {
				int response = JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Confirm", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (response == JOptionPane.YES_OPTION){
					SharedController.getInstance().setLogged(false);
				}
			}
		}
		if (action.equals("EXIT")){
			int response = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION){
					menuView.closeParent();
			}
		}

		if (action.equals("ABOUT")){
			try {
					JOptionPane.showMessageDialog(null, "", "", 
							JOptionPane.INFORMATION_MESSAGE, 
			            new ImageIcon(new URL("http://i645.photobucket.com/albums/uu172/lovelife1197/37275-11-baby-panda.jpg")));
			    } catch (Exception e) {
			        JOptionPane.showMessageDialog(null, e.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
			        e.printStackTrace();
			    } 
			}
		if (action.equals("CREATE_PROJECT")){
				//SharedController.getInstance().getFrame().setVisible(false);
				
				
				Project project = new Project();
				ChooseProjectView projectView = new ChooseProjectView();
				ChooseProjectController projectController = new ChooseProjectController(project, projectView);
				projectView.setLocation(400, 200);
		}
		
		if (action.equals("OPEN_PROJECT")){
			OpenPopulationView openPopulation = new OpenPopulationView();
			OpenPopulationController controller = new OpenPopulationController(openPopulation);
		}
		if (action.equals("SAVE_PROJECT")){
			try {
				SavePopulationView save = new SavePopulationView();
			} catch (FileNotFoundException | UnsupportedEncodingException
					| XMLStreamException | FactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

