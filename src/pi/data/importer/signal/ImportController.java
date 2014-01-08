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
	private Population population;
	private Specimen specimen;
	private Importer importer;
	private Importer importer2;
	
	
	public ImportController(ImporterView panel){
		this.view = panel;
		
		view.setButtonsListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();
		
		if (action.equals("NEXT")){
			
			String path = this.view.getPath(0);
			
			try {
				importer = new Importer(path);
				ArrayList<ECG> temp = importer.importSignals();
				
        		specimen = new Specimen();
        		specimen.setBefore(temp.get(0));
        		specimen.setPath(path);
        		ArrayList <Specimen> pop = new ArrayList<>(1);
        		pop.add(specimen);
        		specimen.setDetails(importer.getAttributes());
        		
        		population = new Population();
        		population.setSpecimen(pop);
        		
				SharedController.getInstance().getProject().setFirstPopulation(population);
				SharedController.getInstance().createProjectToolbar();
				
				GraphView view = new GraphView(population, 1);

				//GraphView view = new GraphView(path, population, 1);
        			        		
			} catch (DocumentException ae) {
				ae.printStackTrace();
			}
				
			if (SharedController.getInstance().getProject().getType() == 2){
				
				String path2 = this.view.getPath(1);
				
				try {
					
					importer2 = new Importer(path2);
					ArrayList<ECG> temp2 = importer2.importSignals();
					
	        		specimen.setAfter(temp2.get(0));
	        		specimen.setPathAfter(path2);
	        			        
					GraphView view2 = new GraphView(population, 2);

				//	GraphView view2 = new GraphView(path2, population, 2);
	        			        		
				} catch (DocumentException ae) {
					ae.printStackTrace();
				}
				
			}
			
			view.dispose();
		}
		
	}
}