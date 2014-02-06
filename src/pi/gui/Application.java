package pi.gui;

import pi.gui.login.LoginDialog;
import pi.shared.SharedController;

public class Application {


	private static LoginDialog login;
	private static OurFrame ourFrame;

	public static void main(String[] args) {
		
		setOurFrame(new OurFrame());
		setLogin(new LoginDialog());
		SharedController.getInstance().getFrame().setEnabled(false);
	
	}

	public static LoginDialog getLogin() {
		return login;
	}

	public static void setLogin(LoginDialog login) {
		Application.login = login;
	}

	public static OurFrame getOurFrame() {
		return ourFrame;
	}

	public static void setOurFrame(OurFrame ourFrame) {
		Application.ourFrame = ourFrame;
	}
		
}
