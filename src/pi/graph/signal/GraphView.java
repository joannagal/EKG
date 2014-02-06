package pi.graph.signal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

import pi.inputs.signal.Channel;
import pi.population.Population;
import pi.shared.SharedController;

public class GraphView extends JPanel {

	private static final long serialVersionUID = 1L;
	private Channel signal1;
	private Graph graph;
	private GraphToolbar toolbar;
	private Population population;
	private int type;
	private int minimumWidth = 1110;
	private ComponentListener cl = new ComponentListener() {

		@Override
		public void componentShown(ComponentEvent e) {
		}

		@Override
		public void componentResized(ComponentEvent e) {
			Component[] children = getContext().getComponents();
			int width = getContext().getParent().getWidth();
			if (width < minimumWidth) {
				width = minimumWidth;
			}

			for (Component c : children) {
				c.setSize(width, c.getHeight());
			}

		}

		@Override
		public void componentMoved(ComponentEvent e) {
		}

		@Override
		public void componentHidden(ComponentEvent e) {
		}
	};

	public GraphView(Population population, int type) {

		this.setPopulation(population);

		this.addComponentListener(cl);
		this.setVisible(true);
		this.setLayout(null);
		this.setType(type);
		SharedController sharedController = SharedController.getInstance();

		if (this.getType() == 1) {
			this.setBounds(sharedController.getFirstPanelX(),
					sharedController.getFirstPanelY(),
					sharedController.getFirstPanelWidth(),
					sharedController.getFirstPanelHeight());

			signal1 = this.getPopulation().getSpecimen().get(0).getBefore()
					.getChannel().get(0);

			sharedController.setFirstGraphView(this);

		}

		if (this.getType() == 2) {

			int projectType = sharedController.getProject().getType();

			this.setBounds(sharedController.getFirstPanelX(),
					sharedController.getFirstPanelHeight() + 20
							+ sharedController.getFirstPanelY(),
					sharedController.getFirstPanelWidth(),
					sharedController.getFirstPanelHeight());

			if (projectType == 2) {

				signal1 = this.getPopulation().getSpecimen().get(0).getAfter()
						.getChannel().get(0);
			} else if (projectType == 4) {

				signal1 = this.getPopulation().getSpecimen().get(0).getBefore()
						.getChannel().get(0);
			} else if (projectType == 3) {

				signal1 = this.getPopulation().getSpecimen().get(0).getBefore()
						.getChannel().get(0);

			}

			sharedController.setSecondGraphView(this);

		}

		graph = new Graph(new Dimension(1100, 190), signal1);
		graph.setLocation(0, 105);
		graph.recalculate();

		toolbar = new GraphToolbar(graph, this);
		toolbar.setLocation(0, 5);

		@SuppressWarnings("unused")
		GraphToolbarController controller = new GraphToolbarController(toolbar,
				graph);

		this.add(toolbar);
		this.add(graph);

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

	public Graph getGraph() {
		return graph;
	}

	public GraphView getContext() {
		return this;
	}
}
