package pi.graph.signal;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

import pi.graph.signal.axis.AxisController;
import pi.graph.signal.signaladapter.SignalAdapter;
import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.shared.SharedController;
import pi.shared.schemes.signal.SignalScheme;
import pi.utilities.Range;

// ------------------------------------------
// --- SEGMENT CLASS
public class Segment
{
	// --- OWNER CLASS
	private Graph graph;
	// --- CURRENT SIGNAL
	private Channel signal;
	// --- CURRENT SCHEME
	private SignalScheme scheme;

	// --- GRID CLASS
	private Grid grid;
	// --- AXIS CONTROLLER
	private AxisController axis;
	// --- SIGNAL ----> SEGMENT
	private SignalAdapter signalAdapter;

	// --- ID
	private int id;
	// --- AXIS START TIME
	private double startTime;
	// --- AXIS END TIME
	private double stopTime;

	// --- STATIC CONSTANT FIELDS
	public static final int NONE_LEVEL = 0;
	public static final int CYCLE_LEVEL = 1;
	public static final int SEGMENT_LEVEL = 2;

	public static final int p_wave = 0;
	public static final int pr_segment = 1;
	public static final int qrs_complex = 2;
	public static final int st_segment = 3;
	public static final int t_wave = 4;
	public static final int u_wave = 5;
	public static final int cycle = 6;
	public static final int selection = 7;
	public static final int cycleSelection = 8;
	// --- RESPONSIBLE FOR KEEPING NECESSARY DATA
	// --- FOR SELECTIONS DRAWING
	public class SelectionFlyWeight
	{
		public int leftPoint;
		public int rightPoint;
		public double left;
		public double right;
		public double rPos;
		public Color color;
		public int type;
		public Range range;
		public Cycle cycle;
	};

	private LinkedList<SelectionFlyWeight> adapter;

	// ------------------------------------------
	// --- STANDARD CONSTRUCTOR
	public Segment(Graph graph, int id)
	{
		this.graph = graph;
		this.setId(id);

		this.grid = new Grid(this);
		this.axis = new AxisController(this);
		this.signalAdapter = new SignalAdapter(this);

		this.startTime = 0.0d;
		this.stopTime = 0.0d;

		adapter = new LinkedList<SelectionFlyWeight>();
	}

	// ------------------------------------------
	// --- CHECK IF X,Y POINT IS INSIDE SOME
	// --- SELECTION AND RETURNS A VALUE
	// --- NONE_LEVEL - NOT IN SELECTION
	// --- CYCLE_LEVEL - IN SOME CYCLE SELECTION
	// --- MARKER_LEVEL - TO CREATE NEW MARKERS
	// --- SEGMENT_LEVEL - IN SME STANDARD SEL.
	
	public int isInsideSelection(int x, int y)
	{
		if (!this.grid.isInGrid(x, y))
			return Segment.NONE_LEVEL;

		ListIterator<SelectionFlyWeight> itr = adapter.listIterator();

		SelectionFlyWeight value;

		while (itr.hasNext())
		{
			value = itr.next();

			if (value.right < scheme.getMargin().getLeft())
				continue;
			if (value.left > scheme.getMargin().getLeft() + grid.getWidth())
				continue;

			if ((x >= value.left) && (x <= value.right))
			{
				this.graph.getCyclePopUp().setAdapter(value);
				this.graph.getSelectPopUp().setAdapter(value);

				if ((value.type == Segment.cycle)
						|| (value.type == Segment.cycleSelection))
				{
					return Segment.CYCLE_LEVEL;
				}
				else
				{
					return Segment.SEGMENT_LEVEL;
				}
			}
		}
		return Segment.NONE_LEVEL;
	}
	
	/*public int isInsideSelection(int x, int y)
	{
		if (!this.grid.isInGrid(x, y))
			return Segment.NONE_LEVEL;

		ListIterator<SelectionFlyWeight> itr = adapter.listIterator();

		SelectionFlyWeight value;

		while (itr.hasNext())
		{
			value = itr.next();

			if (value.right < scheme.getMargin().getLeft())
				continue;
			if (value.left > scheme.getMargin().getLeft() + grid.getWidth())
				continue;

			if ((x >= value.left) && (x <= value.right))
			{
				this.graph.getCyclePopUp().setAdapter(value);
				this.graph.getSelectPopUp().setAdapter(value);

				if ((value.type == Segment.cycle)
						|| (value.type == Segment.cycleSelection))
				{
					return Segment.CYCLE_LEVEL;
				}
				else if ((value.type == Segment.markerSelection)
							|| (value.type == Segment.marker))
				{
					return Segment.MARKER_LEVEL;
				} 
				else
				{
					return Segment.SEGMENT_LEVEL;
				}
			}
		}
		return Segment.NONE_LEVEL;
	}*/

	// ------------------------------------------
	// --- INPUT -> POSITION
	// --- OUTPUT -> TIME CALC BY A PROPORTION
	public double getTimeFormPosition(double x)
	{
		double time = ((double) x - this.scheme.getMargin().getLeft())
				* (this.stopTime - this.startTime);
		time /= this.grid.getWidth();
		time += this.startTime;

		return time;
	}

	// ------------------------------------------
	// --- CALLED WHEN GRAPH RECALCULATE
	public void recalculate()
	{
		this.scheme = graph.getScheme();

		grid.recalculate();

		this.assignTime();
		this.axis.recalculate();
		this.signalAdapter.recalculate();
		this.prepareSelectAdapter();
	}

	// ------------------------------------------
	// --- JUST DRAW
	public void draw(Graphics graphics)
	{
		this.grid.draw(graphics);
		this.axis.draw(graphics);
		this.signalAdapter.draw(graphics);
		this.drawSelections(graphics);
	}

	// ------------------------------------------
	// --- ASSIGN BEGIN, END TIME OF SEGMENT
	public void assignTime()
	{
		this.signal = this.graph.getSignal();

		this.startTime = signal.getStartAxis();

		double timeScale = signal.getScale()
				/ SharedController.getInstance().getPixelsForScale();
		double duration = grid.getWidth() * timeScale;

		if (this.id > 0)
		{
			startTime += (double) (this.id) * duration;
		}

		this.stopTime = signal.getStartAxis() + (double) (this.id + 1)
				* duration;
	}

	// ------------------------------------------
	// --- CHEKCS IF SELECTION IS POSSIBLE IN
	// --- SOME RANGE, FOR CREATE NEW SELECTIONS
	public boolean isSelectionPosssible(Range range)
	{
		ListIterator<SelectionFlyWeight> itr = adapter.listIterator();

		SelectionFlyWeight value;

		while (itr.hasNext())
		{
			value = itr.next();

			if ((value.type == Segment.cycle) && (range.isInside(value.range)))
				continue;

			if ((value.type != Segment.selection)
					&& (value.type != Segment.cycleSelection) &&

					((range.isIntersecting(value.range))

					|| (value.range.isInside(range)))

			)
				return false;
		}

		return true;
	}

	// ------------------------------------------
	public void prepareSelectAdapter()
	{
		this.adapter.clear();

		LinkedList<Cycle> cycles = this.signal.getCycle();
		ListIterator<Cycle> itr = cycles.listIterator();

		Cycle cycle;
		Color color;

		while (itr.hasNext())
		{
			cycle = itr.next();

			color = this.scheme.getP_WaveColor();
			this.constructList(cycle, cycle.getP_wave(), Segment.p_wave, color);

			color = this.scheme.getPr_SegmentColor();
			this.constructList(cycle, cycle.getPr_segment(),
					Segment.pr_segment, color);

			color = this.scheme.getQrs_SegmentColor();
			this.constructList(cycle, cycle.getQrs_complex(),
					Segment.qrs_complex, color);

			color = this.scheme.getSt_SegmentColor();
			this.constructList(cycle, cycle.getSt_segment(),
					Segment.st_segment, color);

			color = this.scheme.getT_WaveColor();
			this.constructList(cycle, cycle.getT_wave(), Segment.t_wave, color);

			color = this.scheme.getU_WaveColor();
			this.constructList(cycle, cycle.getU_wave(), Segment.u_wave, color);

			color = this.scheme.getCycleColor();
			
			if (cycle.getMarkered())
			{
				color = this.scheme.getMarkeredCycleColor();
			}
			
			this.constructList(cycle, cycle.getRange(), Segment.cycle, color);
		}

		if (graph.getToolBox().isSelectionExists())
		{
			double time = this.graph.getToolBox().getLeftSelection();
			if (time < signal.getTranslation()) time = signal.getTranslation();		
			else if (time > signal.getEndTime()) time = signal.getEndTime();
			this.graph.getToolBox().setLeftSelection(time);
			double left = this.signalAdapter.getXFromTime(time);
			
			time = this.graph.getToolBox().getRightSelection();
			if (time < signal.getTranslation()) time = signal.getTranslation();	
			else if (time > signal.getEndTime()) time = signal.getEndTime();
			this.graph.getToolBox().setRightSelection(time);
			double right = this.signalAdapter.getXFromTime(time);

			if ( (left - right > -0.001d) && (left - right < 0.001d) ) 
			{
				this.graph.getToolBox().setSelectionExists(false);
				return;
			}
			
			if ((left < this.scheme.getMargin().getLeft())
					&& (right >= this.scheme.getMargin().getLeft()))
			{
				left = this.scheme.getMargin().getLeft();
			}

			if ((right > this.scheme.getMargin().getLeft()
					+ this.grid.getWidth())
					&& (left <= this.scheme.getMargin().getLeft()
							+ this.grid.getWidth()))
			{
				right = scheme.getMargin().getLeft() + this.grid.getWidth();
			}

			SelectionFlyWeight adapt = new SelectionFlyWeight();

			adapt.left = left;
			adapt.right = right;
			adapt.type = Segment.cycleSelection;
			adapt.color = this.scheme.getSelectColor();

			time = this.graph.getTranform().getTimeFromPosition(left,
					this);
			adapt.leftPoint = this.graph.getTranform().getProbeFromTime(time);
			time = this.graph.getTranform().getTimeFromPosition(right, this);
			adapt.rightPoint = this.graph.getTranform().getProbeFromTime(time);

			adapt.range = new Range((int) this.graph.getTranform()
					.getProbeFromTime(
							this.graph.getToolBox().getLeftSelection()),
					(int) this.graph.getTranform().getProbeFromTime(
							this.graph.getToolBox().getRightSelection()));

			ListIterator<SelectionFlyWeight> adapters = adapter.listIterator();
			SelectionFlyWeight value;

			while (adapters.hasNext())
			{
				value = adapters.next();
				if (adapt.range.isInside(value.range))
				{
					adapt.type = Segment.selection;
					adapt.cycle = value.cycle;
					break;
				}
			}

			//if ()
			
			this.adapter.addFirst(adapt);
		}
	}

	public void constructList(Cycle cycle, Range range, int type, Color color)
	{
		if (range == null)
			return;

		double left, right, rPos = -1;

		left = this.signal.getTranslation() + (double) range.getLeft()
				* this.signal.getInterval();
		right = this.signal.getTranslation() + (double) range.getRight()
				* this.signal.getInterval();
		left = this.signalAdapter.getXFromTime(left);
		right = this.signalAdapter.getXFromTime(right);
		
		if (cycle.getQrs_complex() != null)
		{
			rPos = this.signal.getTranslation() + (double) cycle.getR()
					* this.signal.getInterval();
			
			rPos = this.signalAdapter.getXFromTime(rPos);
		}
		

		if ((left < this.scheme.getMargin().getLeft())
				&& (right >= this.scheme.getMargin().getLeft()))
		{
			left = this.scheme.getMargin().getLeft();
		}

		if ((right > this.scheme.getMargin().getLeft() + this.grid.getWidth())
				&& (left <= this.scheme.getMargin().getLeft()
						+ this.grid.getWidth()))
		{
			right = scheme.getMargin().getLeft() + this.grid.getWidth();
		}

		SelectionFlyWeight adapter = new SelectionFlyWeight();
		adapter.left = left;
		adapter.right = right;
		adapter.rPos = rPos;
		adapter.type = type;
		adapter.color = color;
		adapter.range = range;
		adapter.cycle = cycle;
		this.adapter.add(adapter);
	}

	public void drawSelections(Graphics graphics)
	{
		ListIterator<SelectionFlyWeight> itr = adapter.listIterator();

		SelectionFlyWeight temp;
		ToolBox toolbox;

		toolbox = graph.getToolBox();

		boolean pass = false;

		while (itr.hasNext())
		{
			pass = true;
			temp = itr.next();

			switch (temp.type)
			{
			case Segment.cycle:
				if (!toolbox.isCycleShown())
					pass = false;
				break;
			case Segment.p_wave:
				if ((!toolbox.isP_waveShown()) || (!toolbox.isCycleShown()))
					pass = false;
				break;
			case Segment.pr_segment:
				if ((!toolbox.isPr_SegmentShown()) || (!toolbox.isCycleShown()))
					pass = false;
				break;
			case Segment.qrs_complex:
				if ((!toolbox.isQrs_complexShown())
						|| (!toolbox.isCycleShown()))
					pass = false;
				break;
			case Segment.st_segment:
				if ((!toolbox.isSt_segmentShown()) || (!toolbox.isCycleShown()))
					pass = false;
				break;
			case Segment.t_wave:
				if ((!toolbox.isT_waveShown()) || (!toolbox.isCycleShown()))
					pass = false;
				break;
			case Segment.u_wave:
				if ((!toolbox.isU_waveShown()) || (!toolbox.isCycleShown()))
					pass = false;
				break;
			}

			if (!pass)
				continue;

	
			this.drawSelection(temp.type, temp.left, temp.right, temp.rPos, temp.color,
					graphics);
		}
	}

	public void drawSelection(int type, double left, double right, double rPos, Color color,
			Graphics graphics)
	{
		if (right < scheme.getMargin().getLeft())
			return;
		if (left > scheme.getMargin().getLeft() + this.grid.getWidth())
			return;

		double yBottom = (this.id) * this.graph.getSegmentSize().getHeight()
				+ this.scheme.getMargin().getTop();

		graphics.setColor(color);
		graphics.fillRect((int) left, (int) yBottom,
				(int) (right - left + 0.5d), (int) this.grid.getHeight());

		// --- LABEL

		yBottom -= 10.0d;
		double center = left + (right - left) / 2.0d;

		String label = "";

		switch (type)
		{
		case Segment.p_wave:
			label = "P";
			break;
		case Segment.pr_segment:
			label = "PR";
			break;
		case Segment.qrs_complex:
			label = "QRS";
			break;
		case Segment.st_segment:
			label = "ST";
			break;
		case Segment.t_wave:
			label = "T";
			break;
		case Segment.u_wave:
			label = "U";
			break;
		}

		int stringWidth = graphics.getFontMetrics().stringWidth(label);
		
		if ((right - left) > stringWidth)
		{
			graphics.setColor(this.scheme.getFontColor());
			graphics.drawString(label, (int) (center - stringWidth / 2.0d),
					(int) yBottom);
			
			if (type == Segment.qrs_complex)
			{
				graphics.drawLine((int)rPos, (int)(yBottom + 10), (int)rPos,
						(int)(yBottom + 10 + this.grid.getHeight()));
			}
		}
	}

	public Grid getGrid()
	{
		return this.grid;
	}

	public SignalAdapter getSignalAdapter()
	{
		return this.signalAdapter;
	}

	public AxisController getAxis()
	{
		return axis;
	}

	public Graph getGraph()
	{
		return graph;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public double getStartTime()
	{
		return startTime;
	}

	public void setStartTime(double startTime)
	{
		this.startTime = startTime;
	}

	public double getStopTime()
	{
		return stopTime;
	}

	public void setStopTime(double stopTime)
	{
		this.stopTime = stopTime;
	}
}
