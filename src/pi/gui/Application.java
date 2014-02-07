package pi.gui;

import pi.gui.login.LoginDialog;
import pi.shared.SharedController;

public class Application {

	private static LoginDialog login;
	private static OurFrame ourFrame;

	public static void main(String[] args) {

		setOurFrame(new OurFrame());
		SharedController.getInstance().getFrame().getMenubar()
				.setInChoose(true);
		SharedController.getInstance().getFrame().getMenubar()
				.disableLogOut(false);
		setLogin(new LoginDialog());
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
