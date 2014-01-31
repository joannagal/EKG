package pi.data.importer.population.single;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.GraphView;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.shared.SharedController;

public class PopulationSingleController implements ActionListener {

	private PopulationSingleView view;
	private Population population;
	private Population population2;
	private ArrayList<Specimen> specimens;
	private ArrayList<Specimen> specimens2;
	private Specimen specimen;
	private Importer importer;
	private Importer importer2;

	public PopulationSingleController(PopulationSingleView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();

		if (action.equals("OK")) {

			ArrayList<ArrayList<String>> paths = this.view.getPaths();

			int sizeFirstBefore = paths.get(0).size();
			int sizeSecondBefore = paths.get(1).size();

			if ((sizeFirstBefore > 0) && (sizeSecondBefore > 0)
					&& (sizeFirstBefore == sizeSecondBefore)) {

				population = new Population();
				specimens = new ArrayList<Specimen>();

				for (int i = 0; i < sizeFirstBefore; i++) {

					try {
						String path = paths.get(0).get(i);
						importer = new Importer(path);
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
				population.setName("First population");
				SharedController.getInstance().getProject()
						.setFirstPopulation(population);

				population2 = new Population();
				population2.setName("Second population");

				specimens2 = new ArrayList<Specimen>();

				for (int i = 0; i < sizeSecondBefore; i++) {

					try {
						String path2 = paths.get(1).get(i);
						importer2 = new Importer(path2);

						ArrayList<ECG> temp = importer2.importSignals();
						specimen = new Specimen();
						specimen.setBefore(temp.get(0));
						specimen.setPath(path2);
						specimens2.add(specimen);
						specimen.setDetails(importer2.getAttributes());

					} catch (DocumentException e1) {

						e1.printStackTrace();
					}

				}

				population2.setSpecimen(specimens2);

				SharedController.getInstance().getProject()
						.setSecondPopulation(population2);

				SharedController.getInstance().createProjectToolbar();
				GraphView view = new GraphView(SharedController.getInstance()
						.getProject().getFirstPopulation(), 1);

				GraphView view2 = new GraphView(SharedController.getInstance()
						.getProject().getSecondPopulation(), 2);

			} else {
				JOptionPane.showMessageDialog(null,
						"Size of lists should be equal and not empty!");
			}
			view.dispose();
		} else if (action.equals("ADD")) {
			this.view.getFc().setMultiSelectionEnabled(true);
			int returnVal = this.view.getFc().showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				LinkedList<File> fileList = this.view.getCurrentFileList();
				if (fileList == null)
					return;

				File[] file = this.view.getFc().getSelectedFiles();

				for (int i = 0; i < file.length; i++) {
					fileList.add(file[i]);
				}

				this.updateListOfFiles();
			}
		} else if (action.equals("DELETE")) {
			JList<String> list = this.view.getCurrentList();
			if (list == null)
				return;

			int selection = list.getSelectedIndex();
			if (selection < 0)
				return;

			LinkedList<File> fileList = this.view.getCurrentFileList();
			if (fileList == null)
				return;

			fileList.remove(selection);
			this.updateListOfFiles();

		} else if (action.equals("UP")) {
			JList<String> list = this.view.getCurrentList();
			if (list == null)
				return;

			int selection = list.getSelectedIndex();
			if (selection < 1)
				return;

			LinkedList<File> fileList = this.view.getCurrentFileList();
			if (fileList == null)
				return;

			File temp = fileList.get(selection);
			fileList.set(selection, fileList.get(selection - 1));
			fileList.set(selection - 1, temp);

			list.setSelectedIndex(selection - 1);

			this.updateListOfFiles();
		} else if (action.equals("DOWN")) {

			JList<String> list = this.view.getCurrentList();
			if (list == null)
				return;

			LinkedList<File> fileList = this.view.getCurrentFileList();
			if (fileList == null)
				return;

			int selection = list.getSelectedIndex();
			if (selection < 0)
				return;
			if (selection >= fileList.size() - 1)
				return;

			File temp = fileList.get(selection);
			fileList.set(selection, fileList.get(selection + 1));
			fileList.set(selection + 1, temp);

			list.setSelectedIndex(selection + 1);

			this.updateListOfFiles();
		} else if (action.equals("CANCEL")) {
			view.setVisible(false);
		}
	}

	public void updateListOfFiles() {
		DefaultListModel<String> model = this.view.getCurrentListModel();
		if (model == null)
			return;

		JList<String> list = this.view.getCurrentList();
		if (list == null)
			return;

		LinkedList<File> fileList = this.view.getCurrentFileList();
		if (fileList == null)
			return;

		int selection = list.getSelectedIndex();

		model.clear();

		Iterator<File> it = fileList.iterator();
		File file;

		int number = 0;

		while (it.hasNext()) {
			file = it.next();
			number++;
			model.addElement(Integer.toString(number) + ". " + file.getName());
		}

		list.setSelectedIndex(selection);
	}
}
