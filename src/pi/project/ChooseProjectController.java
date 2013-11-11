package pi.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pi.data.importer.Importer;
import pi.data.importer.population.ImportPopulationFrame;
import pi.data.importer.signal.ImportController;
import pi.data.importer.signal.ImporterView;
import pi.shared.SharedController;


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
				view.setVisible(false);
				
				Project project = new Project();
				project.setType(1);
				SharedController.getInstance().setProject(project);
				
				ImporterView importerView = new ImporterView();
				ImportController controller = new ImportController(importerView);
				importerView.setBounds(400, 200, 350, 200);
			}
			
			if (selected.equals("TWO_SIGNALS")){
				view.setVisible(false);
				
				Project project = new Project();
				project.setType(2);
				SharedController.getInstance().setProject(project);
				
				ImporterView importerView = new ImporterView();
				ImportController controller = new ImportController(importerView);
					
				importerView.setBounds(400, 200, 350, 200);
				
			}
			if (selected.equals("TWO_POPULATIONS")){
				view.dispose();
				ImportPopulationFrame importFrame = new ImportPopulationFrame();
			}
			if (selected.equals("POPULATION_DIFFERENCE")){
				view.dispose();
				ImportPopulationFrame importFrame = new ImportPopulationFrame();
			}
		}

	}
	
}