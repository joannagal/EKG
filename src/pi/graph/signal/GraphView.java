package pi.graph.signal;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.Graph;
import pi.gui.toolbar.ProjectToolbar;
import pi.gui.toolbar.ProjectToolbarController;
import pi.inputs.signal.Channel;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.project.ChooseProjectController;
import pi.project.ChooseProjectView;
import pi.project.Project;
import pi.shared.SharedController;

public class GraphView extends JPanel {

	private String path;
	private ECG ecg;
	private Channel signal1;
	private Graph graph;
	private GraphToolbarController controller;
	private GraphToolbar toolbar;
	private Specimen specimen;
	private String[] attributes = new String[4];
	private Population population;
	private int type;

	public GraphView(String path, Population population, int type) {

		this.setPopulation(population);

		this.setVisible(true);
		this.setLayout(null);
		this.setType(type);

		if (this.getType() == 1) {

			this.setBounds(10, 80, 1250, 600);

			signal1 = this.getPopulation().getSpecimen().get(0).getBefore()
					.getChannel().get(0);

		}

		if (this.getType() == 2) {

			this.setBounds(10, 400, 1250, 600);

			signal1 = this.getPopulation().getSpecimen().get(0).getAfter()
					.getChannel().get(0);

		}

		graph = new Graph(new Dimension(1100, 190), signal1);
		graph.setLocation(0, 105);
		graph.recalculate();

		toolbar = new GraphToolbar(graph, this);
		toolbar.setLocation(0, 5);

		controller = new GraphToolbarController(toolbar, graph);

		this.add(graph);
		this.add(toolbar);

		SharedController.getInstance().addPanel(this);

	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
