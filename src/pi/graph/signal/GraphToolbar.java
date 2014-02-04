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

import pi.gui.autofinder.AutoFinderView;
import pi.inputs.signal.Channel;
import pi.shared.SharedController;

public class GraphToolbar extends JPanel {

	private static final long serialVersionUID = 1L;
	private AutoFinderView afView = new AutoFinderView();
	private JButton informationButton;

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
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxChannel;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxSpecimen;
	private JButton comboBoxButton;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxTreatment;

	private JButton[] buttonArray;
	private String[] itemEvent = new String[] { "INFO", "AUTOFINDER", "ADD",
			"DELETE" };
	private String[] descriptionString = new String[] { "I", "II", "III", "V1",
			"V2", "V3", "V4", "V5", "V6" };

	@SuppressWarnings("rawtypes")
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

		setComboBoxSpecimen(new JComboBox());
		getComboBoxSpecimen().setVisible(true);
		initComboBoxSpecimen();

		if (SharedController.getInstance().getProject().getType() == 4) {
			comboBoxTreatment = new JComboBox();
			comboBoxTreatment.setVisible(true);
			initComboBoxTreatment();
		}

		comboBoxButton = new JButton("Change");
		comboBoxButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Channel signal = new Channel();

				if (SharedController.getInstance().getProject().getType() <= 2) {

					if (getGraphView().getType() == 1) {
						signal = getGraphView().getPopulation().getSpecimen()
								.get(getComboBoxSpecimen().getSelectedIndex())
								.getBefore().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();

					}

					if (getGraphView().getType() == 2) {
						signal = getGraphView().getPopulation().getSpecimen()
								.get(getComboBoxSpecimen().getSelectedIndex())
								.getAfter().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();

					}
				}

				if (SharedController.getInstance().getProject().getType() == 4) {

					if (getGraphView().getType() == 1) {

						if (comboBoxTreatment.getSelectedIndex() == 0) {
							signal = SharedController
									.getInstance()
									.getProject()
									.getFirstPopulation()
									.getSpecimen()
									.get(getComboBoxSpecimen()
											.getSelectedIndex()).getBefore()
									.getChannel()
									.get(comboBoxChannel.getSelectedIndex());
						} else if (comboBoxTreatment.getSelectedIndex() == 1) {
							signal = SharedController
									.getInstance()
									.getProject()
									.getFirstPopulation()
									.getSpecimen()
									.get(getComboBoxSpecimen()
											.getSelectedIndex()).getAfter()
									.getChannel()
									.get(comboBoxChannel.getSelectedIndex());
						}

						graph.setSignal(signal);
						clear();
					}

					if (getGraphView().getType() == 2) {

						if (comboBoxTreatment.getSelectedIndex() == 0) {
							signal = SharedController
									.getInstance()
									.getProject()
									.getSecondPopulation()
									.getSpecimen()
									.get(getComboBoxSpecimen()
											.getSelectedIndex()).getBefore()
									.getChannel()
									.get(comboBoxChannel.getSelectedIndex());
						} else if (comboBoxTreatment.getSelectedIndex() == 1) {
							signal = SharedController
									.getInstance()
									.getProject()
									.getSecondPopulation()
									.getSpecimen()
									.get(getComboBoxSpecimen()
											.getSelectedIndex()).getAfter()
									.getChannel()
									.get(comboBoxChannel.getSelectedIndex());
						}

						graph.setSignal(signal);
						clear();

					}

				}

				if (SharedController.getInstance().getProject().getType() == 3) {
					if (getGraphView().getType() == 1) {
						signal = SharedController.getInstance().getProject()
								.getFirstPopulation().getSpecimen()
								.get(getComboBoxSpecimen().getSelectedIndex())
								.getBefore().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();
					} else if (getGraphView().getType() == 2) {
						signal = SharedController.getInstance().getProject()
								.getSecondPopulation().getSpecimen()
								.get(getComboBoxSpecimen().getSelectedIndex())
								.getBefore().getChannel()
								.get(comboBoxChannel.getSelectedIndex());
						graph.setSignal(signal);
						clear();
					}
				}

			}
		});

		changeChannelPanel.add(getComboBoxSpecimen());
		changeChannelPanel.add(comboBoxChannel);
		if (SharedController.getInstance().getProject().getType() == 4) {
			changeChannelPanel.add(comboBoxTreatment);
		}
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
				if (getGraphView().getType() == 1) {
					graph.setHeight(segmentHeightSlider.getValue());
				} else if (getGraphView().getType() == 2) {
					graph.setHeight(segmentHeightSlider.getValue());
				}
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

	@SuppressWarnings("unchecked")
	public void initComboBoxChannel() {
		for (int i = 0; i < 9; i++) {
			this.comboBoxChannel.addItem(descriptionString[i]);
		}
	}

	@SuppressWarnings("unchecked")
	public void initComboBoxSpecimen() {
		SharedController controller = SharedController.getInstance();

		int projectType = controller.getProject().getType();

		if (getGraphView().getType() == 1) {

			if (projectType == 1 || projectType == 2) {
				this.getComboBoxSpecimen().addItem(
						controller.getProject().getFirstPopulation()
								.getSpecimen().get(0).getName()
								+ " "
								+ controller.getProject().getFirstPopulation()
										.getSpecimen().get(0).getSurname());
			}

			else if (projectType == 4) {
				int tmp = this.graphView.getPopulation().getSpecimen().size();

				for (int i = 0; i < tmp; i++) {
					this.getComboBoxSpecimen().addItem(
							this.graphView.getPopulation().getSpecimen().get(i)
									.getName()
									+ " "
									+ this.graphView.getPopulation()
											.getSpecimen().get(i).getSurname());
				}
			}

			else if (projectType == 3) {
				int tmp = this.graphView.getPopulation().getSpecimen().size();

				for (int i = 0; i < tmp; i++) {
					this.getComboBoxSpecimen().addItem(
							this.graphView.getPopulation().getSpecimen().get(i)
									.getName()
									+ " "
									+ this.graphView.getPopulation()
											.getSpecimen().get(i).getSurname());
				}

			}

		} else if (getGraphView().getType() == 2) {
			if (projectType == 2) {
				this.getComboBoxSpecimen().addItem(
						controller.getProject().getFirstPopulation()
								.getSpecimen().get(0).getName()
								+ " "
								+ controller.getProject().getFirstPopulation()
										.getSpecimen().get(0).getSurname());
			} else if (projectType == 3) {
				int tmp = this.graphView.getPopulation().getSpecimen().size();

				for (int i = 0; i < tmp; i++) {
					this.getComboBoxSpecimen().addItem(
							this.graphView.getPopulation().getSpecimen().get(i)
									.getName()
									+ " "
									+ this.graphView.getPopulation()
											.getSpecimen().get(i).getSurname());
				}
			} else if (projectType == 4) {
				int tmp = this.graphView.getPopulation().getSpecimen().size();

				for (int i = 0; i < tmp; i++) {
					this.getComboBoxSpecimen().addItem(
							this.graphView.getPopulation().getSpecimen().get(i)
									.getName()
									+ " "
									+ this.graphView.getPopulation()
											.getSpecimen().get(i).getSurname());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void initComboBoxTreatment() {
		comboBoxTreatment.addItem("BEFORE");
		comboBoxTreatment.addItem("AFTER");
	}

	@SuppressWarnings("unused")
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

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxSpecimen() {
		return comboBoxSpecimen;
	}

	@SuppressWarnings("rawtypes")
	public void setComboBoxSpecimen(JComboBox comboBoxSpecimen) {
		this.comboBoxSpecimen = comboBoxSpecimen;
	}

}
