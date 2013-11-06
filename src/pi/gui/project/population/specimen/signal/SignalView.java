package pi.gui.project.population.specimen.signal;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.Graph;
import pi.gui.project.population.specimen.signal.toolbar.ToolbarView;
import pi.inputs.Input;
import pi.inputs.signal.ECG;
import pi.shared.SharedController;


public class SignalView extends JPanel
{
	private static final long serialVersionUID = 1L;

	private ToolbarView toolbar;
	private Graph graph;
	
	public SignalView()
	{
		this.graph = new Graph(new Dimension(500, 150), null);

		ECG ecg = SharedController.getInstance().getProject().getFirstPopulation().getSpecimen().get(0).getBefore();
		this.graph.setSignal(ecg.getChannel().get(0));
		
		this.toolbar = new ToolbarView();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setMinimumSize(new Dimension(150, 150));
	
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.0d;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		this.add(this.toolbar, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0d;
		c.weighty = 1.0d;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 0;
		this.add(this.graph, c);
		
		this.graph.recalculate();
		this.graph.recalculate();
		this.graph.draw();
	}


}
