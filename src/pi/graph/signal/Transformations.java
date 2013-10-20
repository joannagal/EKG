package pi.graph.signal;

import pi.inputs.signal.Channel;
import pi.shared.SharedController;
import pi.shared.schemes.signal.SignalScheme;
import pi.utilities.State;

// ------------------------------------------
// --- PROVIDES STANDARD TRANSFORMATIONS 
// --- FOR GRAPH
public class Transformations
{
	// --- OWNER
	private Graph graph;
	// --- CURRENT SIGNAL
	private Channel signal;
	// --- CURRENT SCHEME
	private SignalScheme scheme;

	// --- TRANSFORMATION STATES
	private State translation = new State();
	private State scale = new State();
	private State select = new State();

	// --- TRANSFORMATIONS WITH/WITHOUT TIME
	private boolean axis;

	// ------------------------------------------
	// --- STANDARD CONSTRUCTOR (GET OWNER)
	public Transformations(Graph graph)
	{
		this.graph = graph;
	}

	// ------------------------------------------
	// --- CALLED WHEN OWNER RECALCULATE
	public void recalculate()
	{
		this.signal = this.graph.getSignal();
		this.scheme = this.graph.getScheme();
	}

	// ------------------------------------------
	// --- INPUT  -> DISTANCE IN PIXELS
	// --- OUTPUT -> ADEQUATE PERIOD OF TIME 
	public double getTimeFromDistance(double dx)
	{
		double result = dx * signal.getScale()
				/ SharedController.getInstance().getPixelsForScale();
		return result;
	}

	// ------------------------------------------
	// --- INPUT  -> Y-POSITION IN GRID
	// --- OUTPUT -> ADEQUATE SIGNAL VALUE 
	public double getValueFromHeight(double y)
	{
		double min = this.signal.getMinValue();
		double max = this.signal.getMaxValue();
		double height = this.graph.getSegment(0).getGrid().getHeight();
		return min + y * (max - min) / height;
	}

	// ------------------------------------------
	// --- INPUT  -> X-POSITION IN GRID IN SEGMENT
	// --- OUTPUT -> ADEQUATE DISTANCE
	public double getAbsoluteDistance(double x, Segment segment)
	{
		double result = x - this.scheme.getMargin().getLeft();
		if (segment.getId() > 0)
		{
			result += (segment.getGrid().getWidth()) * segment.getId();
		}
		return result;
	}

	// ------------------------------------------
	// --- INPUT  -> X-POSITION IN GRID IN SEGMENT
	// --- OUTPUT -> ADEQUATE TIME
	public double getTimeFromPosition(double x, Segment segment)
	{
		double result = this.getAbsoluteDistance(x, segment);
		result = this.getTimeFromDistance(result);
		result += segment.getGraph().getSignal().getStartAxis();
		return result;
	}

	// ------------------------------------------
	// --- INPUT  -> SIGNAL TIME
	// --- OUTPUT -> ADEQUATE PROBE
	public int getProbeFromTime(double time)
	{
		int result = (int) ((time - this.signal.getTranslation())
				/ this.signal.getInterval() + 0.5f);
		
		if (result < 0) result = 0;
		
		return result;
	}

	// ------------------------------------------
	// --- INPUT  -> SIGNAL PROBE
	// --- OUTPUT -> ADEQUATE TIME
	public double getTimeFromProbe(int probe)
	{
		return this.signal.getTranslation() + (double) probe
				* this.signal.getInterval();
	}


	// ------------------------------------------
	// --- INPUT  -> X, Y POSITION, SEGMENTS
	// --- OUTPUT -> SEGMENT ID WHERE X, Y IS IN IT
	public int checkInGrid(int x, int y, Segment[] segment)
	{
		for (int i = 0; i < graph.getSegments(); i++)
		{
			if (segment[i].getGrid().isInGrid(x, y))
			{
				return i;
			}
		}
		return -1;
	}

	// ------------------------------------------
	// --- SETS AXIS VALUE - X, Y IN AXIS
	// --- OR IN SEGMENT
	public int checkInside(int x, int y, Segment[] segment)
	{
		for (int i = 0; i < graph.getSegments(); i++)
		{
			if (segment[i].getAxis().getHorizontal().isInAxis(x, y))
			{
				this.axis = true;
				return i;
			}

			if ((!this.axis) && (segment[i].getGrid().isInGrid(x, y)))
			{
				this.axis = false;
				return i;
			}
		}
		return -1;
	}

	
	// ------------------------------------------
	// --- SELECTION MODE
	// ------------------------------------------
	public boolean beginSelect(int x, int y, Segment[] segment)
	{
		int inside = this.checkInGrid(x, y, segment);

		if (inside == -1)
			return false;

		double time = this.getTimeFromPosition(x, segment[inside]);

		int probe = this.getProbeFromTime(time);
		time = this.getTimeFromProbe(probe);

		this.select.setActive(true);
		this.select.setPrevious(time);
		this.select.getHandling().clear();

		return false;
	}

	public boolean applySelect(int x, int y, Segment[] segment)
	{
		int inside = this.checkInGrid(x, y, segment);

		if (inside == -1)
		{
			if (this.graph.getToolBox().isSelectionExists())
				return true;
			else
				return false;
		}

		double time = this.getTimeFromPosition(x, segment[inside]);
		int probe = this.getProbeFromTime(time);
		time = this.getTimeFromProbe(probe);

		this.select.setActual(time);

		if ((this.select.getResult() < -0.00001d)
				|| (this.select.getResult() > 0.00001d))
		{
			this.graph.getToolBox().setSelectionExists(true);

			if (time > this.select.getPrevious())
			{
				this.graph.getToolBox().setLeftSelection(
						this.select.getPrevious());
				this.graph.getToolBox().setRightSelection(time);
			} else
			{
				this.graph.getToolBox().setLeftSelection(time);
				this.graph.getToolBox().setRightSelection(
						this.select.getPrevious());
			}
		} else
		{
			this.graph.getToolBox().setSelectionExists(false);
		}

		return true;
	}

	public boolean endSelect()
	{
		if (this.select.isActive())
		{
			this.graph.recalculate();
			this.select.setActive(false);
			this.axis = false;
			return true;
		}
		return false;
	}

	// ------------------------------------------
	// --- SCALE MODE
	// ------------------------------------------
	public double getLockedScalePosition()
	{
		if (this.axis)
		{
			return this.scale.getHandling().get(3).doubleValue();
		}

		return this.scale.getHandling().get(4).doubleValue();
	}

	
	public boolean beginScale(int x, int y, Segment[] segment)
	{
		int inside = this.checkInside(x, y, segment);

		if (inside == -1)
			return false;

		this.scale.setActive(true);
		this.scale.setPrevious(x);

		this.scale.getHandling().clear();

		if (this.axis)
		{
			this.scale.getHandling().add(this.signal.getScale());
			this.scale.getHandling().add(this.signal.getStartAxis());
			this.scale.getHandling().add(
					this.getTimeFromPosition(x, segment[inside]));
		} else
		{
			this.scale.getHandling().add(this.signal.getInterval());
			this.scale.getHandling().add(this.signal.getTranslation());

			double time = this.getTimeFromPosition(x, segment[inside]);
			double prop = (time - this.signal.getTranslation())
					/ this.signal.getInterval();

			this.scale.getHandling().add(time);
			this.scale.getHandling().add(prop);
		}

		this.scale.getHandling().add((double) x);
		this.scale.getHandling().add((double) inside);

		return true;
	}

	public boolean applyScale(int x, Segment[] segment)
	{
		if (!this.scale.isActive())
			return false;

		this.scale.setActual(x);

		if (this.axis)
		{
			double result = -this.scale.getResult() / 1000.0d;
			double scale = this.scale.getHandling().get(0) + result;

			if (scale < SharedController.getInstance().getMinScale())
			{
				scale = SharedController.getInstance().getMinScale();
			} else if (scale > SharedController.getInstance().getMaxScale())
			{
				scale = SharedController.getInstance().getMaxScale();
			}

			double before = this.scale.getHandling().get(2);
			signal.setScale(scale);
			double after = this.getTimeFromPosition((int) this.scale
					.getHandling().get(3).doubleValue(), segment[this.scale
					.getHandling().get(4).intValue()]);

			signal.setStartAxis(signal.getStartAxis() - (after - before));
		} else
		{
			double result = this.scale.getResult() / 10000.0d;
			double interval = this.scale.getHandling().get(0) + result;

			if (interval < SharedController.getInstance().getMinInterval())
			{
				interval = SharedController.getInstance().getMinInterval();
			} else if (interval > SharedController.getInstance()
					.getMaxInterval())
			{
				interval = SharedController.getInstance().getMaxInterval();
			}

			double before = this.scale.getHandling().get(2);
			double after = this.scale.getHandling().get(3) * interval
					+ this.scale.getHandling().get(1);

			this.signal.setInterval(interval);
			this.signal.setTranslation(this.scale.getHandling().get(1)
					+ (before - after));

		}

		this.graph.recalculate();
		return true;
	}

	public boolean endScale()
	{
		if (this.scale.isActive())
		{
			this.scale.setActive(false);
			this.axis = false;
			return true;
		}
		return false;
	}

	// ------------------------------------------
	// --- TRANSLATION MODE
	// ------------------------------------------
	public boolean beginTranslation(int x, int y, Segment[] segment)
	{
		int inside = this.checkInside(x, y, segment);

		if (inside == -1)
			return false;

		Channel signal = this.graph.getSignal();

		this.translation.setActive(true);
		this.translation.setPrevious(x);
		this.translation.getHandling().clear();

		if (this.axis)
		{
			this.translation.getHandling().add(signal.getStartAxis());
		} else
		{
			this.translation.getHandling().add(signal.getTranslation());
		}

		return true;

	}

	public boolean applyTranslation(int x, Segment[] segment)
	{
		if (this.translation.isActive())
		{
			this.translation.setActual(x);
			double dt = this.getTimeFromDistance(this.translation.getResult());

			if (this.axis)
			{
				signal.setStartAxis(this.translation.getHandling().get(0) - dt);
			} else
			{
				signal.setTranslation(this.translation.getHandling().get(0)
						+ dt);
			}

			this.graph.recalculate();

			return true;
		}
		return false;
	}

	public boolean endTranslation()
	{
		if (this.translation.isActive())
		{
			this.graph.recalculate();
			this.translation.setActive(false);
			this.axis = false;
			return true;
		}
		return false;
	}
	
	// ------------------------------------------
	// --- SOME SETTERS, GETTERS

	public boolean isScale()
	{
		return this.scale.isActive();
	}

	public State getSelect()
	{
		return select;
	}

	public void setSelect(State selection)
	{
		this.select = selection;
	}

}
