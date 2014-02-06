package pi.gui.login;

import pi.shared.SharedController;

public class Login {
	
	public static boolean authenticate(String username, String password){
		
		if(username.equals("admin") && password.equals("admin")){
			SharedController.getInstance().getFrame().setEnabled(true);
			return true;
		}
		
		if(username.equals("user") && password.equals("user")){
			SharedController.getInstance().getFrame().getMenubar().setModeBeforeProject();
			return true;
		}
		
		
		return false;
	}

}
