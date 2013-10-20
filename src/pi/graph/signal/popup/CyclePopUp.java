package pi.graph.signal.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import pi.graph.signal.Graph;
import pi.graph.signal.Segment;
import pi.graph.signal.Segment.SelectionFlyWeight;
import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;

public class CyclePopUp extends JPopupMenu
{
	private static final long serialVersionUID = 1L;
	private Graph graph;
	
	private JMenuItem cycle = new JMenuItem("Cycle");
	
	private JMenuItem select = new JMenuItem("Select Markers");
	private JMenuItem deselect = new JMenuItem("Deselect Markers");
	
	private JMenuItem delete = new JMenuItem("Delete");
	
	private SelectionFlyWeight adapter;
	
	
	public void removeSelect()
	{
		Channel signal = this.graph.getSignal();
		signal.getCycle().remove(this.adapter.cycle);
	}
	
	public CyclePopUp(Graph graph)
	{
		this.graph = graph;
		this.add(this.cycle);
		this.cycle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.cycleSelection)
				{
					if (getGraph().isSelectionPossible())
					{
						getGraph().getToolBox().setSelectionExists(false);
						getGraph().getSignal().getCycle().add(new Cycle(adapter.range));
					}
					else 
					{
							
					}
				}
				else if (adapter.type == Segment.cycle)
				{

				}
				
				getGraph().recalculate();
				getGraph().draw();
			}
		});
		
		this.addSeparator();
		
		this.add(this.select);
		this.select.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.cycle)
				{
					adapter.cycle.setMarkered(true);
				}
				else if (adapter.type == Segment.cycleSelection)
				{
					getGraph().setMarkers(true, adapter.range);
				}
				
				getGraph().recalculate();
				getGraph().draw();
			}
		});
		
		this.add(this.deselect);
		this.deselect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.cycle)
				{
					adapter.cycle.setMarkered(false);
				}
				else if (adapter.type == Segment.cycleSelection)
				{
					getGraph().setMarkers(false, adapter.range);
				}
				
				getGraph().recalculate();
				getGraph().draw();
			}
		});
		
		this.addSeparator();
		
		this.add(this.delete);
		this.delete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.cycleSelection)
				{
					
				}
				else 
				{
					removeSelect();
				}
				
				/*if (adapter.range != null)
				{
					
				}
				else
				{
					getGraph().getToolBox().setSelectionExists(false);
				}*/
				
				getGraph().getToolBox().setSelectionExists(false);
				getGraph().recalculate();
				getGraph().draw();
			}
		});
	}
	
	public Graph getGraph()
	{
		return this.graph;
	}

	public SelectionFlyWeight getAdapter()
	{
		return adapter;
	}

	public void setAdapter(SelectionFlyWeight adapter)
	{
		this.adapter = adapter;
	}
}
