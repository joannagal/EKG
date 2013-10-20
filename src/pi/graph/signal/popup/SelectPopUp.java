package pi.graph.signal.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import pi.graph.signal.Graph;
import pi.graph.signal.Segment;
import pi.graph.signal.Segment.SelectionFlyWeight;
import pi.utilities.Range;

public class SelectPopUp extends JPopupMenu
{
	private static final long serialVersionUID = 1L;
	private Graph graph;
	
	private JMenuItem p_Wave = new JMenuItem("P-Wave");
	private JMenuItem pr_Segment = new JMenuItem("Pr-Segment");
	private JMenuItem qrs_Segment = new JMenuItem("Qrs-Complex");
	private JMenuItem st_Segment = new JMenuItem("St-Segment");
	private JMenuItem t_Wave = new JMenuItem("T-Wave");
	private JMenuItem u_Wave = new JMenuItem("U_Wave");
	private JMenuItem delete = new JMenuItem("Delete");

	private SelectionFlyWeight adapter;
	
	public void removePrevious()
	{	
		if (this.adapter.cycle.getP_wave() == this.adapter.range) this.adapter.cycle.setP_wave(null);
		else if (this.adapter.cycle.getPr_segment() == this.adapter.range) this.adapter.cycle.setPr_segment(null);
		else if (this.adapter.cycle.getQrs_complex() == this.adapter.range) this.adapter.cycle.setQrs_complex(null);
		else if (this.adapter.cycle.getSt_segment() == this.adapter.range) this.adapter.cycle.setSt_segment(null);
		else if (this.adapter.cycle.getT_wave() == this.adapter.range) this.adapter.cycle.setT_wave(null);
		else if (this.adapter.cycle.getU_wave() == this.adapter.range) this.adapter.cycle.setU_wave(null);
	}
	
	
	public boolean iterateIntersect(LinkedList <Range> list)
	{
		ListIterator <Range> itr = list.listIterator();
		Range temp;
		
		while(itr.hasNext())
		{
			temp = itr.next();
			
			if ( (adapter.leftPoint < temp.getRight()) && (adapter.rightPoint > temp.getLeft() ) ) return true;	
		}
		return false;
	}
	
	public SelectPopUp(Graph graph)
	{
		this.graph = graph;
		
		this.add(this.p_Wave);
		this.p_Wave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.selection)
				{
					if (getGraph().isSelectionPossible())
					{
						adapter.cycle.setP_wave(adapter.range);
						getGraph().getToolBox().setSelectionExists(false);
					}
				}
				else 
				{
					removePrevious();
					adapter.cycle.setP_wave(adapter.range);
				}
				getGraph().getToolBox().setSelectionExists(false);
				
				getGraph().recalculate();
				getGraph().draw();
			}
		});
		
		this.add(this.pr_Segment);
		this.pr_Segment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.selection)
				{
					if (getGraph().isSelectionPossible())
					{
						adapter.cycle.setPr_segment(adapter.range);
						getGraph().getToolBox().setSelectionExists(false);
					}
				}
				else 
				{
					removePrevious();
					adapter.cycle.setPr_segment(adapter.range);
				}
				getGraph().getToolBox().setSelectionExists(false);
				
				getGraph().recalculate();
				getGraph().draw();
			}
		});
		
		this.add(this.qrs_Segment);
		this.qrs_Segment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.selection)
				{
					if (getGraph().isSelectionPossible())
					{
						adapter.cycle.setQrs_complex(adapter.range);
						getGraph().getToolBox().setSelectionExists(false);
					}
				}
				else 
				{
					removePrevious();
					adapter.cycle.setQrs_complex(adapter.range);
				}
				getGraph().getToolBox().setSelectionExists(false);
				
				getGraph().recalculate();
				getGraph().draw();
			}
		});
		
		this.add(this.st_Segment);
		this.st_Segment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.selection)
				{
					if (getGraph().isSelectionPossible())
					{
						adapter.cycle.setSt_segment(adapter.range);
						getGraph().getToolBox().setSelectionExists(false);
					}
				}
				else 
				{
					removePrevious();
					adapter.cycle.setSt_segment(adapter.range);
				}
				getGraph().getToolBox().setSelectionExists(false);
				
				getGraph().recalculate();
				getGraph().draw();
			}
		});
		
		this.add(this.t_Wave);
		this.t_Wave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.selection)
				{
					if (getGraph().isSelectionPossible())
					{
						adapter.cycle.setT_wave(adapter.range);
						getGraph().getToolBox().setSelectionExists(false);
					}
				}
				else 
				{
					removePrevious();
					adapter.cycle.setT_wave(adapter.range);
				}
				getGraph().getToolBox().setSelectionExists(false);
				
				getGraph().recalculate();
				getGraph().draw();
			}
		});		
		
		this.add(this.u_Wave);
		this.u_Wave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if (adapter.type == Segment.selection)
				{
					if (getGraph().isSelectionPossible())
					{
						adapter.cycle.setU_wave(adapter.range);
						getGraph().getToolBox().setSelectionExists(false);
					}
				}
				else 
				{
					removePrevious();
					adapter.cycle.setU_wave(adapter.range);
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
				if (adapter.type == Segment.selection)
				{
					
				}
				else 
				{
					removePrevious();
				}
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
