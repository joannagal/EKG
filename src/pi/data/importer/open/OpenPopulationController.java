package pi.data.importer.open;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pi.graph.signal.GraphView;
import pi.project.Project;
import pi.shared.SharedController;

public class OpenPopulationController implements ActionListener {

	private OpenPopulationView view;

	public OpenPopulationController(OpenPopulationView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equals("OPEN")) {
			PopImporter pi = new PopImporter();
			SharedController.getInstance().getFrame().getContent().removeAll();

			XMLReader p;
			try {
				p = XMLReaderFactory.createXMLReader();
				p.setContentHandler(pi);
				System.out.println(view.getPath().toString());
				p.parse(view.getPath());
				Project importedProject = pi.getProject();
				int type = importedProject.getType();


				SharedController.getInstance().setProject(importedProject);
				SharedController.getInstance().createProjectToolbar();

				
				if (type == 1) {
					GraphView view = new GraphView(
							importedProject.getFirstPopulation(), 1);
				} else if (type == 2) {
					GraphView view = new GraphView(importedProject.getFirstPopulation(), 1);
					GraphView view2 = new GraphView(importedProject.getFirstPopulation(), 2);

				} else if (type == 3) {
					GraphView view = new GraphView(importedProject.getFirstPopulation(), 1);
					GraphView view2 = new GraphView(importedProject.getFirstPopulation(), 2);
				} else if (type == 4) {
					GraphView view = new GraphView(importedProject.getFirstPopulation(), 1);
					GraphView view2 = new GraphView(importedProject.getSecondPopulation(), 2);
				}

			} catch (SAXException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (action.equals("CANCEL")) {
			view.dispose();
		}
		if (action.equals("CHOOSE")){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(SharedController.getInstance()
					.getLastDirectory());
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML (*.xml)", "xml");
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setFileFilter(filter);
			int returnValue = fileChooser.showDialog(null,
					"Choose project...");

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String path = selectedFile.getAbsolutePath();
				SharedController.getInstance().setLastDirectory(
						fileChooser.getSelectedFile());
				System.out.println(path);
				view.setPath(path);
			}
			
			view.dispose();
		}
			
		
		
	}

}