package pi.graph.signal;

import javax.swing.JPanel;



import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.ListIterator;

import pi.graph.signal.popup.CyclePopUp;
import pi.graph.signal.popup.SelectPopUp;
import pi.graph.signal.popup.ToolsPopUp;
import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.shared.SharedController;
import pi.shared.schemes.signal.SignalScheme;
import pi.utilities.Range;
import pi.utilities.State;

public class Graph extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------
	// SOME PROJECT OBJECTS
	// -------------------------------------------
	// --- CURRENT SIGNAL
	private Channel signal;
	// --- CURRENT DRAWING STYLE
	private SignalScheme scheme;
	// --- OWN TOOLBOX (selection, scale, view etc)
	private ToolBox toolBox;
	private SharedController controller;
	private Transformations transform;
	private State yProbe;
	private ToolsPopUp toolsPopUp;
	private SelectPopUp selectPopUp;
	private CyclePopUp cyclePopUp;
	// -------------------------------------------
	private Segment[] segment;
	private int segments;
	private Dimension segmentSize;
	// -------------------------------------------


	// -------------------------------------------
	
	public Graph(Dimension size, Channel signal)
	{
		this.controller = SharedController.getInstance();
		this.setToolBox(new ToolBox());
		this.prepareGraph(size, signal, 1);
	}

	public Graph(Dimension size, Channel signal, int segments)
	{
		this.controller = SharedController.getInstance();
		this.setToolBox(new ToolBox());
		this.prepareGraph(size, signal, segments);
	}

	public void prepareGraph(Dimension size, Channel signal, int segments)
	{
		this.setSize(size);
		this.setSignal(signal);
		this.setSegmentSize(new Dimension(size.width, size.height));
		this.setScheme(controller.getCurrentScheme().getSignalScheme());
		this.segments = segments;

		this.yProbe = new State();
		this.transform = new Transformations(this);
		this.segment = new Segment[this.controller.getMaxSegments()];

		this.toolsPopUp = new ToolsPopUp(this);
		this.selectPopUp = new SelectPopUp(this);
		this.cyclePopUp = new CyclePopUp(this);
		
		this.setComponentPopupMenu(toolsPopUp);

		for (int i = 0; i < this.controller.getMaxSegments(); i++)
		{
			this.segment[i] = new Segment(this, i);
		}

		this.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseMoved(MouseEvent evt)
			{
				checkYProbe(evt.getX(), evt.getY());
				int result = isInsideSelection(evt.getX(), evt.getY());
				
				if (result == Segment.CYCLE_LEVEL)
				{
					setComponentPopupMenu(cyclePopUp);
				} else if (result == Segment.SEGMENT_LEVEL)
				{
					setComponentPopupMenu(selectPopUp);
				} else
				{
					setComponentPopupMenu(toolsPopUp);
				}
			}

			public void mouseDragged(MouseEvent evt)
			{

				if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK)
				{
					return;
				}

				checkYProbe(evt.getX(), evt.getY());

				if (transform.applyTranslation(evt.getX(), segment))
				{
					draw();
					return;
				}

				if (transform.applyScale(evt.getX(), segment))
				{
					draw();
					return;
				}

				if (transform.applySelect(evt.getX(), evt.getY(), segment))
				{
					recalculate();
					draw();
					return;
				}

				getToolBox().setSelectionExists(false);

			}
		});

		this.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{

			}

			public void mouseEntered(java.awt.event.MouseEvent evt)
			{

			}

			public void mouseExited(java.awt.event.MouseEvent evt)
			{
				yProbe.setActive(false);
				draw();
			}

			public void mousePressed(java.awt.event.MouseEvent evt)
			{
				if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK)
				{
					return;
				}

				if ((getToolBox().isTranslate())
						&& (transform.beginTranslation(evt.getX(), evt.getY(),
								segment)))
					return;

				if ((getToolBox().isScale())
						&& (transform.beginScale(evt.getX(), evt.getY(),
								segment)))
					return;

				if ((getToolBox().isSelect())
						&& (transform.beginSelect(evt.getX(), evt.getY(),
								segment)))
					return;

				getToolBox().setSelectionExists(false);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt)
			{
				if (transform.endTranslation())
				{
					draw();
					return;
				}

				if (transform.endScale())
				{
					draw();
					return;
				}

				if (transform.endSelect())
				{
					draw();
					return;
				}

				getToolBox().setSelectionExists(false);

			}
		});
	}

	public boolean isSelectionPossible()
	{
		int left = this.getTranform().getProbeFromTime(
				this.toolBox.getLeftSelection());
		int right = this.getTranform().getProbeFromTime(
				this.toolBox.getRightSelection());

		Range range = new Range(left, right);

		for (int i = 0; i < this.segments; i++)
		{
			if (!this.segment[i].isSelectionPosssible(range))
			{
				this.getToolBox().setSelectionExists(false);
				return false;
			}
		}

		return true;
	}
	
	// SET MARKERS TO CYCLES IN RANGE
	public void setMarkers(boolean value, Range range)
	{
		LinkedList<Cycle> cycles = this.signal.getCycle();
		ListIterator<Cycle> itr = cycles.listIterator();

		Cycle cycle;

		while (itr.hasNext())
		{
			cycle = itr.next();
			if ((cycle.getRange().isInside(range)) || (cycle.getRange().isIntersecting(range)))
			{
				cycle.setMarkered(value);
			}
		}
	}

	// ------------------------------------------
	// CHECK IF X Y IS IN SOME SELECTION 
	// RETURNS -> SEGMENT.NONE etc.
	public int isInsideSelection(int x, int y)
	{
		int result = 0;

		for (int i = 0; i < this.segments; i++)
		{
			result = segment[i].isInsideSelection(x, y);
			if (result != 0)
				return result;
		}
		return 0;
	}

	// ------------------------------------------
	// CHECK IF CAN DRAW PROBE
	public void checkYProbe(int x, int y)
	{
		this.yProbe.setActive(false);

		for (int i = 0; i < this.segments; i++)
		{
			if ((this.getToolBox().isProbe())
					&& ((this.segment[i].getAxis().getHorizontal().isInAxis(x,
							y)) || (this.segment[i].getGrid().isInGrid(x, y))))
			{
				this.yProbe.setActive(true);
				this.yProbe.setActual(x);
				this.yProbe.getHandling().clear();
				this.yProbe.getHandling().add((double) i);
				break;
			}
		}

		this.draw();
	}

	@Override
	public void paintComponent(Graphics graphics)
	{
		this.drawBackground(graphics);

		for (int i = 0; i < this.segments; i++)
		{
			this.segment[i].draw(graphics);
		}

		if (this.yProbe.isActive())
		{
			this.drawYProbe(graphics);
		}

		this.drawBorder(graphics);
	}

	// ------------------------------------------
	// DRAWING PROBE
	public void drawYProbe(Graphics graphics)
	{
		graphics.setColor(this.scheme.getProbeColor());

		double x = yProbe.getActual();

		if (this.transform.isScale())
		{
			x = this.transform.getLockedScalePosition();
		}

		int seg = this.yProbe.getHandling().get(0).intValue();

		double yBottom = (seg) * this.segmentSize.height;

		double y1 = this.segment[seg].getSignalAdapter().getYFromX(x) + yBottom;
		double y0 = yBottom + this.segmentSize.height
				- this.scheme.getMargin().getBottom();

		if (y1 != -1)
		{
			Graphics2D g2d = (Graphics2D) graphics;
			g2d.setStroke(this.scheme.getProbeStroke());

			graphics.drawLine((int) x, (int) y0, (int) x, (int) y1);

			// -------
			// double h = this.segment[seg].getGrid().getHeight() - (y1 -
			// this.scheme.getMargin().getTop());
			double time = this.transform.getTimeFromPosition(x,
					this.segment[seg]);
			double value = this.segment[seg].getSignalAdapter()
					.getValueFromTime(time);

			int temp = 0;

			int probeWidth = graphics.getFontMetrics().stringWidth(
					String.format("value = %.2f", value));
			temp = graphics.getFontMetrics().stringWidth(
					String.format("time  = %.4fs", time));

			if (temp > probeWidth)
				probeWidth = temp;
			probeWidth += 10;

			int probeHeight = 6 + this.scheme.getFontSize() * 2;
			int dx = 10;

			if (x + dx + probeWidth > this.getWidth())
			{
				dx = -10 - probeWidth;
			}

			if (y1 - 5 - probeHeight < 5)
				y1 += (5 - (y1 - 5 - probeHeight));

			graphics.setColor(this.scheme.getGridColor());
			graphics.fillRect((int) x + dx, (int) y1 - 5 - probeHeight,
					probeWidth, probeHeight);
			graphics.setColor(this.scheme.getBorderColor());
			graphics.drawRect((int) x + dx, (int) y1 - 5 - probeHeight,
					probeWidth, probeHeight);

			graphics.drawString(String.format("value = %.2f", value), (int) x
					+ dx + 3, (int) y1 - 9 - this.scheme.getFontSize());
			graphics.drawString(String.format("time  = %.4fs", time), (int) x
					+ dx + 3, (int) y1 - 9);
		}

	}
	
	// ------------------------------------------
	// SIMPLE DRAWING BORDER
	public void drawBorder(Graphics graphics)
	{
		Rectangle frame = this.getBounds();
		graphics.setColor(scheme.getBorderColor());
		graphics.drawRect(0, 0, frame.width - 1, frame.height - 1);
	}
	
	// ------------------------------------------
	// SIMPLE DRAWING BACKGROUND
	public void drawBackground(Graphics graphics)
	{
		Rectangle frame = this.getBounds();
		graphics.setColor(scheme.getBackgroundColor());
		graphics.fillRect(0, 0, frame.width - 1, frame.height - 1);
	}

	// ------------------------------------------
	public void draw()
	{
		this.repaint();
	}
	
	// ------------------------------------------
	// RECALCULATE ALL GRAPH
	public void recalculate()
	{
		this.setScheme(controller.getCurrentScheme().getSignalScheme());

		for (int i = 0; i < this.segments; i++)
		{
			this.segment[i].recalculate();
		}

		this.transform.recalculate();
	}

	// ------------------------------------------
	// INCREASE NUMBER OF SEGMENTS
	public void addSegment()
	{
		System.out.println("W addSegment");
		if (this.segments < this.controller.getMaxSegments())
		{
			System.out.println("W ifie addSegment");
			this.segments++;
			this.recalculate();
			this.draw();
		}
	}
	
	// ------------------------------------------
	// DECREASE NUMBER OF SEGMENTS
	public void delSegment()
	{
		if (this.segments > 1)
		{
			this.segments--;
			this.recalculate();
			this.draw();
		}
	}

	// --- GETTERS SETTERS -----------------------------
	public void setWidth(int width)
	{
		this.segmentSize.width = width;
		this.setSize(width, this.getSize().height);
		this.recalculate();
		this.draw();
	}

	public void setSegmentHeight(int height)
	{
		this.segmentSize.height = height;
		this.recalculate();
		this.draw();
	}

	public void setHeight(int height)
	{
		this.setSize(this.getSize().width, height);
		this.recalculate();
		this.draw();
	}

	public int getSegments()
	{
		return segments;
	}

	public Segment getSegment(int from)
	{
		if ((from < 0) || (from >= this.controller.getMaxSegments()))
		{
			throw new IllegalArgumentException();
		}

		return this.segment[from];
	}

	public CyclePopUp getCyclePopUp()
	{
		return this.cyclePopUp;
	}

	public SelectPopUp getSelectPopUp()
	{
		return this.selectPopUp;
	}

	public Transformations getTranform()
	{
		return this.transform;
	}

	public Channel getSignal()
	{
		return signal;
	}

	public void setSignal(Channel signal)
	{
		this.getToolBox().setSelectionExists(false);
		this.signal = signal;
	}

	public SignalScheme getScheme()
	{
		return scheme;
	}

	public void setScheme(SignalScheme scheme)
	{
		this.scheme = scheme;
	}

	public ToolBox getToolBox()
	{
		return toolBox;
	}

	public void setToolBox(ToolBox toolBox)
	{
		this.toolBox = toolBox;
	}

	public Dimension getSegmentSize()
	{
		return segmentSize;
	}

	public void setSegmentSize(Dimension segmentSize)
	{
		this.segmentSize = segmentSize;
	}

	public void setCycleShown(boolean cycleShown)
	{
		this.getToolBox().setCycleShown(cycleShown);
	}

	public void setP_waveShown(boolean p_waveShown)
	{
		this.getToolBox().setP_waveShown(p_waveShown);
	}

	public void setQrs_complexShown(boolean qrs_complexShown)
	{
		this.getToolBox().setQrs_complexShown(qrs_complexShown);
	}

	public void setT_waveShown(boolean t_waveShown)
	{
		this.getToolBox().setT_waveShown(t_waveShown);
	}

	public void setPr_SegmentShown(boolean pr_SegmentShown)
	{
		this.getToolBox().setPr_SegmentShown(pr_SegmentShown);
	}

	public void setU_waveShown(boolean u_waveShown)
	{
		this.getToolBox().setU_waveShown(u_waveShown);
	}

	public void setSt_segmentShown(boolean st_segmentShown)
	{
		this.getToolBox().setSt_segmentShown(st_segmentShown);
	}
	

}
