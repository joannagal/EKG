package pi.data.importer.population.pair;

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
import pi.project.Project;
import pi.shared.SharedController;

public class PopulationPairController implements ActionListener {
	private PopulationPairView view;
	private ArrayList<Specimen> specimens;
	private ArrayList<Specimen> specimens2;
	private Population population;
	private Population population2;
	private Specimen specimen;
	private Importer importer;
	private GraphView view2;
	private GraphView view3;
	private Project project;

	public PopulationPairController(PopulationPairView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();

		if (action.equals("OK")) {
			ArrayList<ArrayList<String>> paths = this.view.getPaths();

			int sizeFirstBefore = paths.get(0).size();
			int sizeFirstAfter = paths.get(1).size();
			int sizeSecondBefore = paths.get(2).size();
			int sizeSecondAfter = paths.get(3).size();

			if (((sizeFirstBefore > 0) && (sizeFirstAfter > 0) && (sizeFirstBefore == sizeFirstAfter))
					|| ((sizeSecondBefore > 0) && (sizeSecondAfter > 0) && (sizeSecondBefore == sizeSecondAfter))) {

				try {
					project = new Project();
					project.setType(4);
					project.setName("New Project");
					SharedController.getInstance().setProject(project);

					population = new Population();
					specimens = new ArrayList<Specimen>();

					for (int i = 0; i < sizeFirstBefore; i++) {

						String path = paths.get(0).get(i).toString();
						importer = new Importer(path);
						ArrayList<ECG> temp = importer.importSignals();

						specimen = new Specimen();
						specimen.setBefore(temp.get(0));
						specimen.setPath(path);
						specimens.add(specimen);
						specimen.setDetails(importer.getAttributes());
					}

					population.setSpecimen(specimens);
					population.setName("First population");

					SharedController.getInstance().getProject()
							.setFirstPopulation(population);

					for (int i = 0; i < sizeFirstAfter; i++) {

						String firstPathAfter = paths.get(1).get(i).toString();
						importer = new Importer(firstPathAfter);
						ArrayList<ECG> temp = importer.importSignals();

						Specimen specimen = SharedController.getInstance()
								.getProject().getFirstPopulation()
								.getSpecimen().get(i);
						specimen.setAfter(temp.get(0));
						specimen.setPathAfter(firstPathAfter);
					}

					population2 = new Population();
					specimens2 = new ArrayList<Specimen>();

					for (int i = 0; i < sizeSecondBefore; i++) {

						String secondPathBefore = paths.get(2).get(i)
								.toString();
						importer = new Importer(secondPathBefore);
						ArrayList<ECG> temp = importer.importSignals();
						specimen = new Specimen();
						specimen.setBefore(temp.get(0));
						specimen.setPath(secondPathBefore);
						specimens2.add(specimen);
						specimen.setDetails(importer.getAttributes());
					}

					population2.setSpecimen(specimens2);
					population2.setName("Second population");
					SharedController.getInstance().getProject()
							.setSecondPopulation(population2);

					for (int i = 0; i < sizeSecondAfter; i++) {
						String secondPathAfter = paths.get(3).get(i).toString();

						importer = new Importer(secondPathAfter);
						ArrayList<ECG> temp = importer.importSignals();
						Specimen specimen = SharedController.getInstance()
								.getProject().getSecondPopulation()
								.getSpecimen().get(i);
						specimen.setAfter(temp.get(0));
						specimen.setPathAfter(secondPathAfter);
					}

					SharedController.getInstance().createProjectToolbar();
					setView3(new GraphView(SharedController.getInstance()
							.getProject().getFirstPopulation(), 1));
					setView2(new GraphView(SharedController.getInstance()
							.getProject().getSecondPopulation(), 2));

					SharedController.getInstance().getFrame().getMenubar()
							.setInProject(true);
					view.dispose();

				} catch (DocumentException | IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null,
							"Incompatible type of given file!");
				}

			} else
				JOptionPane.showMessageDialog(null,
						"Size of lists should be equal and not empty!");

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
		} else if (action.equals("DEL")) {
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

	public GraphView getView2() {
		return view2;
	}

	public void setView2(GraphView view2) {
		this.view2 = view2;
	}

	public GraphView getView3() {
		return view3;
	}

	public void setView3(GraphView view3) {
		this.view3 = view3;
	}
}
