package pi.gui;

public class Login {
	
	public static boolean authenticate(String username, String password){
		
		if(username.equals("admin") && password.equals("admin")){
			return true;
		}
		
		if(username.equals("user") && password.equals("user")){
			return true;
		}
		
		return false;
	}

}
