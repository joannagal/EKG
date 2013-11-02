package pi.graph.signal;

import java.awt.Dimension;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.Graph;
import pi.inputs.signal.Channel;
import pi.inputs.signal.ECG;
import pi.population.Specimen;
import pi.project.ChooseProjectController;
import pi.project.ChooseProjectView;
import pi.project.Project;
import pi.shared.SharedController;

public class GraphView{

	private String path;
    private ECG ecg;
	private Channel signal1;
	private Graph graph;
	private GraphController controller;
	private GraphToolbar toolbar;
	private Specimen specimen;
	private String[] attributes = new String[4];
	
	public GraphView(String path){
       
		
		try {        
				
            Importer imp = new Importer(path);
            attributes = imp.getAttributes();
            
            specimen = new Specimen();
            specimen.setDetails(attributes);
            
            ArrayList <ECG> temp = imp.importSignals();
            this.ecg = temp.get(0);
            this.signal1 = ecg.getChannel().get(0);
            
            graph = new Graph(new Dimension(1100, 200), signal1);
            toolbar = new GraphToolbar(graph);
    		SharedController.getInstance().addPanel(toolbar);
    		controller = new GraphController(toolbar, graph);
            graph.setLocation(10, 120);
            SharedController.getInstance().addPanel(graph);    
            
            graph.recalculate();
    		graph.setVisible(true);

    		SharedController.getInstance().packFrame();
		} catch (DocumentException e){
            e.printStackTrace();
		}
		
		
	}
}
