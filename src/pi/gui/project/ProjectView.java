package pi.gui.project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import pi.gui.project.population.PopulationView;
import pi.gui.project.toolbar.ToolbarView;
import pi.shared.SharedController;

public class ProjectView extends JPanel
{
	private static final long serialVersionUID = 1L;

	private PopulationView populationView[] = new PopulationView [2];
	private JSplitPane splitter;
	
	private ProjectController controller;
	private ToolbarView toolbarView;
	
	public ProjectView()
	{
		this.controller = new ProjectController(this);
		this.toolbarView = new ToolbarView();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.weightx = 1.0d;
		
		this.add(this.toolbarView, c);
		
		c.weighty = 1.0d;
		c.gridy = 1;
		
		int type = SharedController.getInstance().getProject().getType();
		
		if (type < 3)
		{
			this.populationView[0] = new PopulationView();
			this.add(this.populationView[0], c);
		}
		else
		{
			this.populationView[0] = new PopulationView();
			this.populationView[1] = new PopulationView();
			
			this.splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
					this.populationView[0], this.populationView[1]);
			
			this.splitter.setOneTouchExpandable(true);
			this.splitter.setDividerLocation(0.5d);
		
			this.add(this.splitter, c);
		}
		
		
	
	}

	public void recalculate()
	{
		this.controller.recalculate();
	}
	
	public JSplitPane getSplitter()
	{
		return splitter;
	}

	public void setSplitter(JSplitPane splitter)
	{
		this.splitter = splitter;
	}

	public PopulationView[] getPopulationView()
	{
		return populationView;
	}

	public void setPopulationView(PopulationView populationView[])
	{
		this.populationView = populationView;
	}
	
}
