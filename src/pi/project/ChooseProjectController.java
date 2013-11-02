package pi.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pi.data.importer.Importer;
import pi.data.importer.ImporterView;


public class ChooseProjectController implements ActionListener{
	
	Project model;
	ChooseProjectView view;
	
	public ChooseProjectController(Project model, ChooseProjectView view){
		this.model = model;
		this.view = view;
		
		view.setButtonsListener(this);
	}
	
	public void actionPerformed(ActionEvent ae){
		
		String action = ae.getActionCommand();	
		if (action.equals("CANCEL")){
			view.dispose();
		}
		if (action.equals("NEXT")){
			String selected = view.findSelectedRadio();
			if (selected.equals("SINGLE_SIGNAL")){
				view.dispose();
				
				ImporterView importerView = new ImporterView();
				importerView.setBounds(400, 200, 400, 100);
			}
		}

	}
	
}