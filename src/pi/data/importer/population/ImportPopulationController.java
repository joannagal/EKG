package pi.data.importer.population;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pi.shared.SharedController;

public class ImportPopulationController implements ActionListener {

	ImportPopulationFrame frame;
	
	public ImportPopulationController(ImportPopulationFrame frame){
		this.frame = frame;
		
		frame.setButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();	
		if (action.equals("NEXT")){
			
			if (SharedController.getInstance().getProject().getType() == 3){
				
			}
			
			if (SharedController.getInstance().getProject().getType() == 4){
				
			}
			
		}
	}
	

}
