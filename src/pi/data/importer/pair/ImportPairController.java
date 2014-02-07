package pi.data.importer.pair;

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

public class ImportPairController implements ActionListener {

	private ImportPairView view;
	private Importer importer;
	private Importer importer2;
	private Specimen specimen;
	private Population population;
	private GraphView firstView;
	private GraphView secondView;

	public ImportPairController(ImportPairView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();

		if (action.equals("OK")) {
			if ((!this.view.getFirstPathArea().getText().isEmpty())
					&& (!this.view.getSecondPathArea().getText().isEmpty())) {
				String path = this.view.getFirstPathArea().getText().toString();

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

					setSecondView(new GraphView(population, 1));

				} catch (DocumentException ae) {
					ae.printStackTrace();
				}

				String path2 = this.view.getSecondPathArea().getText()
						.toString();

				try {

					importer2 = new Importer(path2);
					ArrayList<ECG> temp2 = importer2.importSignals();

					specimen.setAfter(temp2.get(0));
					specimen.setPathAfter(path2);

					setFirstView(new GraphView(population, 2));

				} catch (DocumentException ae) {
					ae.printStackTrace();
				}
				
				SharedController.getInstance().getFrame().getMenubar().setInProject(true);

				this.view.dispose();

			} else {
				JOptionPane.showMessageDialog(null,
						"All fields are required, please fill in paths!");
			}

		} else if (action.equals("CANCEL")) {
			this.view.setVisible(false);
		} else if (action.equals("CHOOSE_FIRST")) {
			int returnVal = this.view.getFileChooser().showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = this.view.getFileChooser().getSelectedFile();
				this.view.getFirstPathArea().setText(file.getAbsolutePath());
				SharedController.getInstance().setLastDirectory(
						this.view.getFileChooser().getSelectedFile());
				System.out.println(file.getAbsolutePath().toString());
			}

		} else if (action.equals("CHOOSE_SECOND")) {
			int returnVal = this.view.getFileChooser().showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = this.view.getFileChooser().getSelectedFile();
				this.view.getSecondPathArea().setText(file.getAbsolutePath());
				SharedController.getInstance().setLastDirectory(
						this.view.getFileChooser().getSelectedFile());
				System.out.println(file.getAbsolutePath().toString());
			}

		}
	}

	public GraphView getSecondView() {
		return secondView;
	}

	public void setSecondView(GraphView secondView) {
		this.secondView = secondView;
	}

	public GraphView getFirstView() {
		return firstView;
	}

	public void setFirstView(GraphView firstView) {
		this.firstView = firstView;
	}
}
