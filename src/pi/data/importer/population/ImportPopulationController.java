package pi.data.importer.population;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.shared.SharedController;
import pi.graph.signal.GraphView;
import pi.project.Project;

public class ImportPopulationController implements ActionListener {

	private ImportPopulationFrame frame;
	private ArrayList<Specimen> specimens;
	private ArrayList<Specimen> specimens2;
	private Population population;
	private Population population2;
	private Importer importer;
	private Specimen specimen;

	public ImportPopulationController(ImportPopulationFrame frame) {
		this.setFrame(frame);
		frame.setButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equals("NEXT")) {

			if (SharedController.getInstance().getProject().getType() == 4) {

				// second population - before
				if (SharedController.getInstance().isFirstPopulationSet() == true) {
					population2 = new Population();

					int length2 = getFrame().getImport1().list.size();
					specimens2 = new ArrayList<Specimen>();

					for (int i = 0; i < length2; i++) {

						try {
							setImporter(new Importer(
									getFrame().getImport1().list.get(i)
											.getString()));

							String path = getFrame().getImport1().list.get(i)
									.getString();
							ArrayList<ECG> temp = importer.importSignals();

							specimen = new Specimen();
							specimen.setBefore(temp.get(0));
							specimen.setPath(path);
							specimens2.add(specimen);
							specimen.setDetails(importer.getAttributes());

						} catch (DocumentException e1) {

							e1.printStackTrace();
						}
					}

					population2.setSpecimen(specimens2);
					SharedController.getInstance().getProject()
							.setSecondPopulation(population2);
					SharedController.getInstance().createProjectToolbar();

					// GraphView view = new GraphView(this.population2, 1);
					// second population - after

					for (int i = 0; i < length2; i++) {

						try {
							setImporter(new Importer(
									getFrame().getImport2().list.get(i)
											.getString()));

							String pathAfter = getFrame().getImport2().list
									.get(i).getString();
							ArrayList<ECG> temp = importer.importSignals();

							Specimen specimen = SharedController.getInstance()
									.getProject().getSecondPopulation()
									.getSpecimen().get(i);
							specimen.setAfter(temp.get(0));
							specimen.setPathAfter(pathAfter);
						} catch (DocumentException e1) {
							e1.printStackTrace();
						}
					}
					
					GraphView view = new GraphView(SharedController.getInstance().getProject().getFirstPopulation(), 1);
					GraphView view2 = new GraphView(SharedController.getInstance().getProject().getSecondPopulation(), 2);

					

				}

				else {
					
					SharedController.getInstance().setFirstPopulationSet(true);

					population = new Population();

					int length1 = getFrame().getImport1().list.size();
					specimens = new ArrayList<Specimen>();

					for (int i = 0; i < length1; i++) {

						try {
							setImporter(new Importer(
									getFrame().getImport1().list.get(i)
											.getString()));

							String path = getFrame().getImport1().list.get(i)
									.getString();
							ArrayList<ECG> temp = importer.importSignals();

							specimen = new Specimen();
							specimen.setBefore(temp.get(0));
							specimen.setPath(path);
							specimens.add(specimen);
							specimen.setDetails(importer.getAttributes());

						} catch (DocumentException e1) {

							e1.printStackTrace();
						}
					}

					population.setSpecimen(specimens);
					SharedController.getInstance().getProject()
							.setFirstPopulation(population);

					// GraphView view = new GraphView(this.population2, 1);

					// second population - after

					for (int i = 0; i < length1; i++) {

						try {
							setImporter(new Importer(
									getFrame().getImport2().list.get(i)
											.getString()));

							String pathAfter = getFrame().getImport2().list
									.get(i).getString();
							ArrayList<ECG> temp = importer.importSignals();

							Specimen specimen = SharedController.getInstance()
									.getProject().getFirstPopulation()
									.getSpecimen().get(i);
							specimen.setAfter(temp.get(0));
							specimen.setPathAfter(pathAfter);
						} catch (DocumentException e1) {
							e1.printStackTrace();
						}
					}
					
					ImportPopulationFrame importFrame = new ImportPopulationFrame();
					ImportPopulationController controller = new ImportPopulationController(
							importFrame);
					TitledBorder title = BorderFactory.createTitledBorder("POPULATION 2 - BEFORE");
					importFrame.getImport1().setBorder(title);
					title =  BorderFactory.createTitledBorder("POPULATION 2 - AFTER");
					importFrame.getImport2().setBorder(title);

				}

				if (SharedController.getInstance().getProject().getType() == 3) {

					population = new Population();

					int length1 = getFrame().getImport1().list.size();
					specimens = new ArrayList<Specimen>();

					for (int i = 0; i < length1; i++) {

						try {
							setImporter(new Importer(
									getFrame().getImport1().list.get(i)
											.getString()));

							String path = getFrame().getImport1().list.get(i)
									.getString();
							ArrayList<ECG> temp = importer.importSignals();

							specimen = new Specimen();
							specimen.setBefore(temp.get(0));
							specimen.setPath(path);
							specimens.add(specimen);
							specimen.setDetails(importer.getAttributes());

						} catch (DocumentException e1) {
							e1.printStackTrace();
						}
					}

					population.setSpecimen(specimens);
					SharedController.getInstance().getProject()
							.setFirstPopulation(population);
					GraphView view = new GraphView(this.population, 1);
					SharedController.getInstance().createProjectToolbar();

					for (int i = 0; i < length1; i++) {

						try {
							setImporter(new Importer(
									getFrame().getImport2().list.get(i)
											.getString()));

							String pathAfter = getFrame().getImport2().list
									.get(i).getString();
							ArrayList<ECG> temp = importer.importSignals();

							Specimen specimen = getPopulation().getSpecimen()
									.get(i);
							specimen.setAfter(temp.get(0));
							specimen.setPathAfter(pathAfter);
						} catch (DocumentException e1) {
							e1.printStackTrace();
						}
					}
					SharedController.getInstance().createProjectToolbar();
					GraphView view2 = new GraphView(this.population, 2);

		
				}

			}

			getFrame().setVisible(false);
		}
	}

	private Importer getImporter() {
		return importer;
	}

	private void setImporter(Importer importer) {
		this.importer = importer;
	}

	private Population getPopulation() {
		return population;
	}

	private void setPopulation(Population population) {
		this.population = population;
	}

	private ImportPopulationFrame getFrame() {
		return frame;
	}

	private void setFrame(ImportPopulationFrame frame) {
		this.frame = frame;
	}

}
