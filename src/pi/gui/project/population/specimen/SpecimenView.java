package pi.gui.project.population.specimen;

import pi.data.importer.Importer;
import pi.graph.signal.Graph;
import pi.gui.project.population.specimen.signal.SignalView;
import pi.inputs.signal.ECG;
import pi.shared.SharedController;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SpecimenView extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private SignalView beforeView;
	private SignalView afterView;
	private JSplitPane splitter;

	public SpecimenView()
	{	
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setMinimumSize(new Dimension(150, 150));
	
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0d;
		c.weighty = 1.0d;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		int type = SharedController.getInstance().getProject().getType();
		
		if ((type == 1) || (type == 3))
		{
			this.beforeView = new SignalView();
			this.add(beforeView, c);
		}
		else
		{
			this.beforeView = new SignalView();
			this.afterView = new SignalView();
			
			this.splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
					this.beforeView, this.afterView);
			this.splitter.setOneTouchExpandable(true);
			this.splitter.setDividerLocation(100);
			
			this.add(splitter, c);
		}
		
	
	}

	public void dummy()
	{
		

	}
	
}
