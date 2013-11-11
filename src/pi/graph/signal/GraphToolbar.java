package pi.graph.signal;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pi.graph.signal.Graph;
import pi.gui.autofinder.AutoFinderView;
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
	
	private JButton[] buttonArray;
	private String[] itemEvent = new String[]{"INFO", "AUTOFINDER", "ADD", "DELETE"};
	private String[] descriptions = new String[]{"Chanel 1","Chanel 2","Chanel 3","Chanel 4",
			"Chanel 5", "Chanel 6", "Chanel 7", "Chanel 8", "Chanel 9"};
	
	
	public GraphToolbar(final Graph graph, GraphView view){
		this.graphView = view;
		this.graph = graph;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBounds(10, 10, 1100, 100);
		this.setBorder(BorderFactory.createTitledBorder("Settings"));
		this.setVisible(true);
		
		informationButton = new JButton("Information");
		informationButton.setVisible(true);
		
		autofinderButton = new JButton("AutoFinder");
		autofinderButton.setVisible(true);
		
		
		segmentPanel = new JPanel();
		segmentPanel.setBorder(BorderFactory.createTitledBorder("+/- Segments"));
		addSegmentButton = new JButton("+");
		addSegmentButton.setVisible(true);
		segmentPanel.add(addSegmentButton);
		deleteDegmentButton = new JButton("-");
		deleteDegmentButton.setVisible(true);
		segmentPanel.add(deleteDegmentButton);
		
		changeChannelPanel = new JPanel();
		changeChannelPanel.setBorder(BorderFactory.createTitledBorder("Change channel"));
		
		
		
		
		buttonArray = new JButton[]{informationButton,autofinderButton,
				addSegmentButton, deleteDegmentButton};
		
		
		
		/* setting panel with segment height slider */
		segmentHeightSliderPanel = new JPanel();
		segmentHeightSliderPanel.setBorder(BorderFactory.createTitledBorder("Panel Height"));
		segmentHeightSliderPanel.setVisible(true);
	
		segmentHeightSlider = new JSlider();
		segmentHeightSlider.setMinimum(200);
		segmentHeightSlider.setMaximum(1000);
		segmentHeightSlider.setValue(200);
		segmentHeightSlider.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent arg0) {
				graph.setHeight(segmentHeightSlider.getValue());
				graphView.setSize(1100, 120 + segmentHeightSlider.getValue());
				
				graphView.validate();
				graphView.repaint();
				
			}
			
		});
		segmentHeightSliderPanel.add(segmentHeightSlider);
	
        
        /* setting panel with panel height slider*/
		panelHeightSliderPanel = new JPanel();
		panelHeightSliderPanel.setBorder(BorderFactory.createTitledBorder("Segment Height"));
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
        
        
        //add configured elements
		this.add(informationButton);
		this.add(autofinderButton);
		this.add(segmentPanel);
		this.add(segmentHeightSliderPanel);
		this.add(panelHeightSliderPanel);
		SharedController.getInstance().packFrame();
	}

	public void setButtonsListener(ActionListener al){
		for (int i = 0; i < buttonArray.length; i++){
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
	
}
