package pi.gui;

import pi.gui.login.LoginDialog;
import pi.shared.SharedController;

public class Application {


	private static LoginDialog login;

	public static void main(String[] args) {
		
		OurFrame ourFrame = new OurFrame();
		setLogin(new LoginDialog());
			
		while(true){
			ourFrame.getMenubar().setLogItemText();
			
			if (!SharedController.getInstance().isLogged()){
				ourFrame.getMenubar().setEditable(true);
			} else {
				ourFrame.getMenubar().setEditable(true);
			}
		}
		 
	
		
	}

	public static LoginDialog getLogin() {
		return login;
	}

	public static void setLogin(LoginDialog login) {
		Application.login = login;
	}
		
}
