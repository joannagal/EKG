package pi.graph.signal;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pi.graph.signal.Graph;
import pi.shared.SharedController;

public class GraphToolbar extends JPanel {
	
	private JButton informationButton;
	private JButton analysisButton;
	private JButton resultsButton;
	private JPanel segmentPanel;
	private JButton addSegmentButton;
	private JButton deleteDegmentButton;
	private JSlider segmentHeightSlider;
	private JSlider heightSlider;
	private JSlider widthSlider;
	private Graph graph;

	private JPanel segmentHeightSliderPanel;
	private JPanel panelHeightSliderPanel;
	private JPanel panelWidthSliderPanel;
	
	private JButton[] buttonArray;
	private JSlider[] sliderArray;
	private String[] itemEvent = new String[]{"INFO", "ANALYSIS", "RESULTS", "ADD", "DELETE"};

	
	public GraphToolbar(final Graph graph){
		this.graph = graph;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBounds(10, 10, 1100, 100);
		this.setBorder(BorderFactory.createTitledBorder("Settings"));
		this.setVisible(true);
		
		informationButton = new JButton("Information");
		informationButton.setVisible(true);
		analysisButton = new JButton("Analysis");
		analysisButton.setVisible(true);
		resultsButton = new JButton("Results");
		resultsButton.setVisible(true);
		
		
		segmentPanel = new JPanel();
		segmentPanel.setBorder(BorderFactory.createTitledBorder("+/- Segments"));
		addSegmentButton = new JButton("+");
		addSegmentButton.setVisible(true);
		segmentPanel.add(addSegmentButton);
		deleteDegmentButton = new JButton("-");
		deleteDegmentButton.setVisible(true);
		segmentPanel.add(deleteDegmentButton);
		
		buttonArray = new JButton[]{informationButton, analysisButton, resultsButton, 
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
			}
			
		});
		segmentHeightSliderPanel.add(segmentHeightSlider);
	
        
        
        /* setting panel with panel width slider*/
        panelWidthSliderPanel = new JPanel();
		panelWidthSliderPanel.setBorder(BorderFactory.createTitledBorder("Panel Width"));
		panelWidthSliderPanel.setVisible(true);
        
        widthSlider = new JSlider();
        widthSlider.setMinimum(800);
        widthSlider.setMaximum(1500);
        widthSlider.setValue(1100);
        widthSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				graph.setWidth(widthSlider.getValue());
			}
		});
        panelWidthSliderPanel.add(widthSlider);
        
        
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
        
        sliderArray = new JSlider[]{segmentHeightSlider, heightSlider, widthSlider};
        
		this.add(informationButton);
		this.add(analysisButton);
		this.add(resultsButton);
		this.add(segmentPanel);
		this.add(segmentHeightSliderPanel);
		this.add(panelWidthSliderPanel);
		this.add(panelHeightSliderPanel);
		SharedController.getInstance().packFrame();
	}

	public void setButtonsListener(ActionListener al){
		for (int i = 0; i < buttonArray.length; i++){
			buttonArray[i].setActionCommand(itemEvent[i]);
			buttonArray[i].addActionListener(al);
		}
	}
	
	
}
