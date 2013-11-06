package pi.gui.project.population;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import pi.gui.project.population.list.ListView;
import pi.gui.project.population.specimen.SpecimenView;

public class PopulationView extends JPanel
{
	private static final long serialVersionUID = 1L;

	private ListView leftPanel = new ListView();
	private SpecimenView rightPanel = new SpecimenView();
	private JSplitPane splitter;

	//private ProjectController controller;
	
	public PopulationView()
	{
		//this.controller = new ProjectController(this);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0d;
		c.weighty = 1.0d;
		
		this.rightPanel.setMinimumSize(new Dimension(150, 150));
		
		this.leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
					leftPanel, rightPanel);
		this.splitter.setOneTouchExpandable(true);
		this.splitter.setDividerLocation(0.25);
		
		this.add(splitter, c);
		
	}
	
	
}
