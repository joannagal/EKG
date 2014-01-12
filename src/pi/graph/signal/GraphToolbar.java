package pi.graph.signal;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pi.graph.signal.Graph;
import pi.gui.autofinder.AutoFinderView;
import pi.inputs.signal.Channel;
import pi.shared.SharedController;
import pi.statistics.gui.StatisticWindowController;
import pi.statistics.gui.StatisticWindowView;

public class GraphToolbar extends JPanel {

	private AutoFinderView afView = new AutoFinderView();
	private JButton informationButton;
	private JButton analysisButton;
	private JButton resultsButton;
	private JPanel segmentPanel;
	private JButton autofinderButton;
	private JButton addSegmentButton;
	private JButton deleteDegmentButton;
	private JSlider segmentHeightSlider;
	private JSlider heightSlider;
	private Graph graph;
	private GraphView graphView;

	private JPanel segmentHeightSliderPanel;
	private JPanel panelHeightSliderPanel;
	private JPanel changeChannelPanel;
	private JComboBox comboBoxChannel;
	private JComboBox comboBoxSpecimen;
	private JButton comboBoxButton;

	private JButton[] buttonArray;
	private String[] itemEvent = new String[] { "INFO", "AUTOFINDER", "ADD",
			"DELETE" };
	private String[] descriptionString = new String[] { "Chanel 1", "Chanel 2",
			"Chanel 3", "Chanel 4", "Chanel 5", "Chanel 6", "Chanel 7",
			"Chanel 8", "Chanel 9" };

	public GraphToolbar(final Graph graph, GraphView view) {
		this.setGraphView(view);
		this.setGraph(graph);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setSize(new Dimension(1300, 100));
		this.setBorder(BorderFactory.createTitledBorder("Settings"));
		this.setVisible(true);

		informationButton = new JButton("Information");
		informationButton.setVisible(true);

		autofinderButton = new JButton("AutoFinder");
		autofinderButton.setVisible(true);

		segmentPanel = new JPanel();
		segmentPanel
				.setBorder(BorderFactory.createTitledBorder("+/- Segments"));
		addSegmentButton = new JButton("+");
		addSegmentButton.setVisible(true);
		segmentPanel.add(addSegmentButton);
		deleteDegmentButton = new JButton("-");
		deleteDegmentButton.setVisible(true);
		segmentPanel.add(deleteDegmentButton);

		changeChannelPanel = new JPanel();
		changeChannelPanel.setBorder(BorderFactory
				.createTitledBorder("Change view"));
		changeChannelPanel.setLayout(new FlowLayout());

		comboBoxChannel = new JComboBox();
		comboBoxChannel.setVisible(true);
		initComboBoxChannel();

		comboBoxSpecimen = new JComboBox();
		comboBoxSpecimen.setVisible(true);
		initComboBoxSpecimen();

		comboBoxButton = new JButton("Change");
		comboBoxButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Channel signal = new Channel();

				if (SharedController.getInstance().getProject().getType() <= 2) {

					if (getGraphView().getType() == 1) {
						signal = getGraphView().getPopulation().getSpecimen()
								.get(comboBoxSpecimen.getSelectedIndex())
								.getBefore().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();

					}

					if (getGraphView().getType() == 2) {
						signal = getGraphView().getPopulation().getSpecimen()
								.get(comboBoxSpecimen.getSelectedIndex())
								.getAfter().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();

					}
				}

				if (SharedController.getInstance().getProject().getType() == 4) {

					if (getGraphView().getType() == 1) {
						signal = SharedController.getInstance().getProject()
								.getFirstPopulation().getSpecimen()
								.get(comboBoxSpecimen.getSelectedIndex())
								.getBefore().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();
					}

					if (getGraphView().getType() == 2) {
						signal = SharedController.getInstance().getProject()
								.getSecondPopulation().getSpecimen()
								.get(comboBoxSpecimen.getSelectedIndex())
								.getBefore().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();
					}

				}
				
				if (SharedController.getInstance().getProject().getType() == 3){
					if (getGraphView().getType() == 1){
						signal = SharedController.getInstance().getProject()
								.getFirstPopulation().getSpecimen()
								.get(comboBoxSpecimen.getSelectedIndex())
								.getBefore().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();
					}
					else if (getGraphView().getType() == 2){
						signal = SharedController.getInstance().getProject()
								.getFirstPopulation().getSpecimen()
								.get(comboBoxSpecimen.getSelectedIndex())
								.getAfter().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();
					}
				}

			}
		});

		changeChannelPanel.add(comboBoxSpecimen);
		changeChannelPanel.add(comboBoxChannel);
		changeChannelPanel.add(comboBoxButton);

		buttonArray = new JButton[] { informationButton, autofinderButton,
				addSegmentButton, deleteDegmentButton };

		/* setting panel with segment height slider */
		segmentHeightSliderPanel = new JPanel();
		segmentHeightSliderPanel.setBorder(BorderFactory
				.createTitledBorder("Panel Height"));
		segmentHeightSliderPanel.setVisible(true);

		segmentHeightSlider = new JSlider();
		segmentHeightSlider.setMinimum(200);
		segmentHeightSlider.setMaximum(1000);
		segmentHeightSlider.setValue(200);
		segmentHeightSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {

				SharedController controller = SharedController.getInstance();

				if (getGraphView().getType() == 1) {

					graph.setHeight(segmentHeightSlider.getValue());
					getGraphView().setSize(controller.getFirstPanelWidth(),
							100 + segmentHeightSlider.getValue());
					getGraphView().setMinimumSize(new Dimension(controller.getFirstPanelWidth(),
							100 + segmentHeightSlider.getValue()));
					getGraphView().setPreferredSize(new Dimension(controller.getFirstPanelWidth(),
							100 + segmentHeightSlider.getValue()));
					controller.setFirstPanelHeight(100 + segmentHeightSlider
							.getValue());
					
					
					/*
					if (controller.getSecondGraphView() != null) {
						controller.getSecondGraphView().setLocation(
								controller.getFirstPanelX(),
								controller.getFirstPanelHeight()
										+ controller.getFirstPanelY() + 40);
					}
					*/
					//SharedController.getInstance().getFrame().pack();
				} else if (getGraphView().getType() == 2) {

					graph.setHeight(segmentHeightSlider.getValue());
					getGraphView().setSize(controller.getFirstPanelWidth(),
							100 + segmentHeightSlider.getValue());
					getGraphView().setMinimumSize(new Dimension(controller.getFirstPanelWidth(),
							100 + segmentHeightSlider.getValue()));
					getGraphView().setPreferredSize(new Dimension(controller.getFirstPanelWidth(),
							100 + segmentHeightSlider.getValue()));
				}
				
				getGraphView().getParent().revalidate();

			}

		});
		segmentHeightSliderPanel.add(segmentHeightSlider);

		/* setting panel with panel height slider */
		panelHeightSliderPanel = new JPanel();
		panelHeightSliderPanel.setBorder(BorderFactory
				.createTitledBorder("Segment Height"));
		panelHeightSliderPanel.setVisible(true);

		heightSlider = new JSlider();
		heightSlider.setMinimum(200);
		heightSlider.setMaximum(500);
		heightSlider.setValue(200);
		heightSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				graph.setSegmentHeight(heightSlider.getValue());
			}
		});
		panelHeightSliderPanel.add(heightSlider);

		// add configured elements
		this.add(informationButton);
		this.add(autofinderButton);
		this.add(segmentPanel);
		this.add(changeChannelPanel);
		this.add(segmentHeightSliderPanel);
		this.add(panelHeightSliderPanel);
		SharedController.getInstance().packFrame();
	}

	public void setButtonsListener(ActionListener al) {
		for (int i = 0; i < buttonArray.length; i++) {
			buttonArray[i].setActionCommand(itemEvent[i]);
			buttonArray[i].addActionListener(al);
		}
	}

	public AutoFinderView getAfView() {
		return afView;
	}

	public void setAfView(AutoFinderView afView) {
		this.afView = afView;
	}

	public void initComboBoxChannel() {
		for (int i = 0; i < 9; i++) {
			this.comboBoxChannel.addItem(descriptionString[i]);
		}
	}

	public void initComboBoxSpecimen() {
		SharedController controller = SharedController.getInstance();

		int projectType = controller.getProject().getType();

		if (getGraphView().getType() == 1) {

			if (projectType == 1 || projectType == 2) {
				this.comboBoxSpecimen.addItem(controller.getProject()
						.getFirstPopulation().getSpecimen().get(0).getName()
						+ " "
						+ controller.getProject().getFirstPopulation()
								.getSpecimen().get(0).getSurname());
			}

			else if (projectType == 4) {
				int tmp = this.graphView.getPopulation().getSpecimen().size();

				for (int i = 0; i < tmp; i++) {
					this.comboBoxSpecimen.addItem(this.graphView
							.getPopulation().getSpecimen().get(i).getName()
							+ " "
							+ this.graphView.getPopulation().getSpecimen()
									.get(i).getSurname());
				}
			}

			else if (projectType == 3) {
				int tmp = this.graphView.getPopulation().getSpecimen().size();

				for (int i = 0; i < tmp; i++) {
					this.comboBoxSpecimen.addItem(this.graphView
							.getPopulation().getSpecimen().get(i).getName()
							+ " "
							+ this.graphView.getPopulation().getSpecimen()
									.get(i).getSurname());
				}
				

			}

		} else if (getGraphView().getType() == 2) {
			if (projectType == 2) {
				this.comboBoxSpecimen.addItem(controller.getProject()
						.getFirstPopulation().getSpecimen().get(0).getName()
						+ " "
						+ controller.getProject().getFirstPopulation()
								.getSpecimen().get(0).getSurname());
			} else if (projectType == 3) {
				int tmp = this.graphView.getPopulation().getSpecimen().size();

				for (int i = 0; i < tmp; i++) {
					this.comboBoxSpecimen.addItem(this.graphView
							.getPopulation().getSpecimen().get(i).getName()
							+ " "
							+ this.graphView.getPopulation().getSpecimen()
									.get(i).getSurname());
				}
			} else if (projectType == 4) {
				int tmp = this.graphView.getPopulation().getSpecimen().size();

				for (int i = 0; i < tmp; i++) {
					this.comboBoxSpecimen.addItem(this.graphView
							.getPopulation().getSpecimen().get(i).getName()
							+ " "
							+ this.graphView.getPopulation().getSpecimen()
									.get(i).getSurname());
				}
			}
		}
	}

	private Graph getGraph() {
		return graph;
	}

	private void setGraph(Graph graph) {
		this.graph = graph;
	}

	private GraphView getGraphView() {
		return graphView;
	}

	private void setGraphView(GraphView graphView) {
		this.graphView = graphView;
	}

	private void clear() {
		graph.recalculate();
		graph.draw();
	}

}
