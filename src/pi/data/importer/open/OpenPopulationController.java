package pi.data.importer.open;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pi.graph.signal.GraphView;
import pi.project.Project;
import pi.shared.SharedController;


public class OpenPopulationController implements ActionListener {
	
	private OpenPopulationView view;
	
	public OpenPopulationController(OpenPopulationView view){
		
		this.view = view;
		view.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();	
		
		if (action.equals("OPEN")){
			PopImporter pi = new PopImporter();
			
			XMLReader p;
			try {
				p = XMLReaderFactory.createXMLReader();
				p.setContentHandler(pi);
				System.out.println(view.getPath().toString());
				p.parse(view.getPath());
				Project importedProject = pi.getProject();
				int type = importedProject.getType();
				SharedController.getInstance().createProjectToolbar();
				
				if (type == 1){							
					GraphView view = new GraphView(importedProject.getFirstPopulation(), 1);
				}
				else if (type ==2){
					
				} 
				else if (type == 3){
					
				}
				else if (type == 4){
					
				}
				
				
			} catch (SAXException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if (action.equals("CANCEL")){
			
		}		
	}
	
	
	
}