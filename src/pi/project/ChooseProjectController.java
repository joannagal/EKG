package pi.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pi.data.importer.pair.ImportPairController;
import pi.data.importer.pair.ImportPairView;
import pi.data.importer.population.pair.PopulationPairController;
import pi.data.importer.population.pair.PopulationPairView;
import pi.data.importer.population.single.PopulationSingleController;
import pi.data.importer.population.single.PopulationSingleView;
import pi.data.importer.signal.ImportSingleController;
import pi.data.importer.signal.ImportSingleView;
import pi.shared.SharedController;


public class ChooseProjectController implements ActionListener{
	
	Project model;
	ChooseProjectView view;
	private Project project;
	
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
			SharedController.getInstance().getFrame().getContent().removeAll();
			String selected = view.findSelectedRadio();
			SharedController.getInstance().setFirstPopulationSet(false);
			if (selected.equals("SINGLE_SIGNAL")){
				view.setVisible(false);
				
				project = new Project();
				project.setType(1);
				project.setName("New Project");
				SharedController.getInstance().setProject(project);
				
				ImportSingleView importerView = new ImportSingleView();
				@SuppressWarnings("unused")
				ImportSingleController controller = new ImportSingleController(importerView);
				importerView.setBounds(400, 200, 450, 200);
				
			}
			
			if (selected.equals("TWO_SIGNALS")){
				view.setVisible(false);
				
				project = new Project();
				project.setType(2);
				project.setName("New Project");

				SharedController.getInstance().setProject(project);
				
				ImportPairView importerView = new ImportPairView();
				@SuppressWarnings("unused")
				ImportPairController controller = new ImportPairController(importerView);
					
				importerView.setBounds(400, 200, 500, 200);
				
				
			}
			if (selected.equals("TWO_POPULATIONS")){
				view.setVisible(false);
				
				project = new Project();
				project.setType(3);
				project.setName("New Project");

				SharedController.getInstance().setProject(project);
				
				PopulationSingleView view = new PopulationSingleView();
				@SuppressWarnings("unused")
				PopulationSingleController controller = new PopulationSingleController(view);
				
			}
			if (selected.equals("POPULATION_DIFFERENCE")){
				view.setVisible(false);
				
				project = new Project();
				project.setType(4);
				project.setName("New Project");

				SharedController.getInstance().setProject(project);
				PopulationPairView view = new PopulationPairView();
				@SuppressWarnings("unused")
				PopulationPairController controller = new PopulationPairController(view);
				

			}
		}

	}
	
}