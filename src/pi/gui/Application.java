package pi.gui;

import pi.data.importer.populationpair.PopulationSingleView;
import pi.inputs.signal.ECG;
import pi.shared.SharedController;

public class Application {

	private static ECG ecg;

	public static void main(String[] args) {
		
		OurFrame ourFrame = new OurFrame();
		PopulationSingleView test = new PopulationSingleView();
		
		
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
