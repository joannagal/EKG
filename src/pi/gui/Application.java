package pi.gui;


import pi.inputs.signal.ECG;
import pi.shared.SharedController;

public class Application {

	private static ECG ecg;

	public static void main(String[] args) {
		
		OurFrame ourFrame = new OurFrame();
		
		while(true){
			ourFrame.getMenubar().setLogItemText();
			
			if (!SharedController.getInstance().isLogged()){
				ourFrame.getMenubar().setEditable(true);
			} else {
				ourFrame.getMenubar().setEditable(true);
			}
		}
		 
	
		
	}
		
}
