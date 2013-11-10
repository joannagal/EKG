package pi.graph.signal;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.Graph;
import pi.gui.toolbar.ProjectToolbar;
import pi.inputs.signal.Channel;
import pi.inputs.signal.ECG;
import pi.population.Specimen;
import pi.project.ChooseProjectController;
import pi.project.ChooseProjectView;
import pi.project.Project;
import pi.shared.SharedController;

public class GraphView extends JPanel{

	private String path;
    private ECG ecg;
	private Channel signal1;
	private Graph graph;
	private GraphController controller;
	private GraphToolbar toolbar;
	private Specimen specimen;
	private String[] attributes = new String[4];
	
	public GraphView(String path) {
       
		
		try {        
				
			this.setBounds(10, 10, 1250, 600);
			this.setVisible(true);
			this.setLayout(null);
			
			ProjectToolbar tool = new ProjectToolbar();
			tool.setVisible(true);
			tool.setBounds(15, 15, 1100, 100);
			this.add(tool);
			
            Importer imp = new Importer(path);
            attributes = imp.getAttributes();
            
            specimen = new Specimen();
            specimen.setDetails(attributes);
            
            ArrayList <ECG> temp = imp.importSignals();
            this.ecg = temp.get(0);
            this.signal1 = ecg.getChannel().get(0);
            
            graph = new Graph(new Dimension(1100, 190), signal1);
            toolbar = new GraphToolbar(graph, this);
            toolbar.setLocation(10, 120);
            
    		controller = new GraphController(toolbar, graph);
            graph.setLocation(10, 230);
            
            graph.recalculate();
    		graph.setVisible(true);
    		
    		this.add(graph);
    		this.add(toolbar);
    		
    		this.validate();
    		this.repaint();
            
            SharedController.getInstance().addPanel(this);    
    		SharedController.getInstance().packFrame();
		} catch (DocumentException e){
            e.printStackTrace();
		}
		
		
	}
}
