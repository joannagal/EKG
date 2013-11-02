package pi.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
				LoginDialog login = new LoginDialog();
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
				Project project = new Project();
				ChooseProjectView projectView = new ChooseProjectView();
				ChooseProjectController projectController = new ChooseProjectController(project, projectView);
				projectView.setLocation(400, 200);
		}
	}
}

