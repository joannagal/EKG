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
        		ArrayList <Specimen> pop = new ArrayList<>(1);
        		pop.add(specimen);
        		specimen.setDetails(importer.getAttributes());
        		
        		population = new Population();
        		population.setSpecimen(pop);
        		
				SharedController.getInstance().getProject().setFirstPopulation(population);
				SharedController.getInstance().createProjectToolbar();
        			        		
			} catch (DocumentException ae) {
				ae.printStackTrace();
			}
			if (SharedController.getInstance().getProject().getType() == 1){
				GraphView view = new GraphView(path, population);
				view.setBounds(0, 0, 1250, 600);
			}		
			if (SharedController.getInstance().getProject().getType() == 2){
				
				String path2 = this.view.getPath(1);
				
				try {
					
					importer = new Importer(path2);
					ArrayList<ECG> temp2 = importer.importSignals();
					
	        		specimen.setAfter(temp2.get(0));
	        			        		
					GraphView view = new GraphView(path2, population);
					view.setBounds(0, 1000, 800, 200);
	        			        		
				} catch (DocumentException ae) {
					ae.printStackTrace();
				}
				
			}
			
			view.dispose();
		}
		
	}
}