package pi.data.importer.signal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.GraphView;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.shared.SharedController;

public class ImportSingleController implements ActionListener {

	private ImportSingleView view;
	private Importer importer;
	private Specimen specimen;
	private Population population;

	public ImportSingleController(ImportSingleView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();

		if (action.equals("OK")) {
			if (!this.view.getPathArea().getText().isEmpty()) {
				String path = this.view.getPathArea().getText();

				try {
					importer = new Importer(path);
					ArrayList<ECG> temp = importer.importSignals();

					specimen = new Specimen();
					specimen.setBefore(temp.get(0));
					specimen.setPath(path);
					ArrayList<Specimen> pop = new ArrayList<>(1);
					pop.add(specimen);
					specimen.setDetails(importer.getAttributes());

					population = new Population();
					population.setSpecimen(pop);
					population.setName("First population");


					SharedController.getInstance().getProject()
							.setFirstPopulation(population);
					SharedController.getInstance().createProjectToolbar();

					@SuppressWarnings("unused")
					GraphView view = new GraphView(population, 1);
					
					SharedController.getInstance().getFrame().getMenubar().setInProject(true);
					
				} catch (DocumentException ae) {
					ae.printStackTrace();
				}
				
				this.view.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "All fields are required, please fill in path!");
			}
			
		} else if (action.equals("CANCEL")) {
			this.view.setVisible(false);
		} else if (action.equals("CHOOSE")) {
			int returnValue = this.view.getFileChooser().showDialog(view.getContext(), "Choose specimen...");
			
			if (returnValue == JFileChooser.APPROVE_OPTION)
			{
				File file = this.view.getFileChooser().getSelectedFile();
				this.view.getPathArea().setText(file.getAbsolutePath());
        		SharedController.getInstance().setLastDirectory(this.view.getFileChooser().getSelectedFile());
        		System.out.println(file.getAbsolutePath().toString());
			}
		}
	}
}
