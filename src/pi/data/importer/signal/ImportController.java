package pi.data.importer.signal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.GraphView;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.project.Project;
import pi.shared.SharedController;

public class ImportController implements ActionListener{

	private ImporterView view;
	
	public ImportController(ImporterView panel){
		this.view = panel;
		
		view.setButtonsListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();
		
		if (action.equals("NEXT")){
			if (SharedController.getInstance().getProject().getType() == 1){
				String path = this.view.getPath(0);
				
				try {
					Importer importer;
					importer = new Importer(path);
					ArrayList<ECG> temp = importer.importSignals();
					
	        		Specimen specimen = new Specimen();
	        		specimen.setBefore(temp.get(0));
	        		ArrayList <Specimen> pop = new ArrayList<>(1);
	        		pop.add(specimen);
	        		specimen.setDetails(importer.getAttributes());
	        		
	        		Population population = new Population();
	        		population.setSpecimen(pop);
	        		
					Project project = new Project();
					project.setFirstPopulation(population);
					project.setType(1);
					SharedController.getInstance().setProject(project);
					SharedController.getInstance().createProjectToolbar();
					
					GraphView view = new GraphView(path);
					view.setBounds(10, 10, 1250, 600);
	        			        		
				} catch (DocumentException ae) {
					ae.printStackTrace();
				}
				view.dispose();
				
			}
			if (SharedController.getInstance().getProject().getType() == 2){
				
			}
		}
		
	}
}