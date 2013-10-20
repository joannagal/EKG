package pi.graph.signal;

import java.awt.Graphics;

import pi.shared.schemes.signal.SignalScheme;
import pi.utilities.Margin;

//------------------------------------------
//--- GRID FOR EACH SEGMENT
public class Grid
{
	// --- OWNER
	private Segment segment;

	// --- DRAWING SCHEME
	private SignalScheme scheme;

	// --- MARGINS
	private Margin margin;

	// --- SIZE
	private double width;
	private double height;

	private double yPosition;

	// --- DIVIDERS OF GRID
	private int mainDivider;
	private int subDivider;

	//------------------------------------------
	// --- STANDARD CONSTRUCTOR
	Grid(Segment segment)
	{
		this.segment = segment;
		this.scheme = segment.getGraph().getScheme();
	}

	//------------------------------------------
	// --- CHECK IF (x, y) IS IN GRID
	public boolean isInGrid(int x, int y)
	{
		if ((x >= this.margin.getLeft())
				&& (x <= this.margin.getLeft() + this.width)
				&& (y >= this.yPosition) && (y <= this.yPosition + this.height))
		{
			return true;
		}

		return false;
	}

	//------------------------------------------
	// --- CALLED WHEN SEGMENT IS RECALCULATED
	public void recalculate()
	{
		this.scheme = this.segment.getGraph().getScheme();
		this.margin = scheme.getMargin();

		this.height = (double) segment.getGraph().getSegmentSize().height
				- (margin.getTop() + margin.getBottom());

		this.width = (double) segment.getGraph().getSegmentSize().width
				- (margin.getLeft() + margin.getRight());

		this.yPosition = segment.getGraph().getSegmentSize().height
				* segment.getId() + margin.getTop();

		this.mainDivider = scheme.getMainDivider();
		this.subDivider = scheme.getSubDivider();
	}

	//------------------------------------------
	// --- SIMPLE DRAW GRID
	public void draw(Graphics graphics)
	{
		//------------------------------------------
		// --- SUB GRID

		graphics.setColor(scheme.getGridColor());

		graphics.drawRect((int) this.margin.getLeft(), (int) this.yPosition,
				(int) this.width, (int) this.height);

		double delta = (double) this.height
				/ (double) (this.mainDivider * this.subDivider);

		graphics.setColor(scheme.getSubGridColor());

		int m = 0;
		double y = 0.0f;
		double x = 0.0f;

		for (y = this.yPosition, m = 0; y <= this.yPosition + this.height
				- 2.0d; y += delta, m++)
		{
			if (m % this.subDivider != 0)
			{
				graphics.drawLine((int) this.margin.getLeft(), (int) (y),
						(int) (this.margin.getLeft() + this.width), (int) (y));
			}
		}

		for (x = this.margin.getLeft(), m = 0; x <= this.margin.getLeft()
				+ this.width; x += delta, m++)
		{
			if (m % this.subDivider != 0)
			{
				graphics.drawLine((int) x, (int) (this.yPosition), (int) x,
						(int) (this.yPosition + this.height));
			}
		}
		
		//------------------------------------------
		// --- MAIN GRID

		delta = (float) this.height / (float) (this.mainDivider);

		graphics.setColor(this.scheme.getMainGridColor());

		for (y = this.yPosition; y <= this.yPosition + this.height; y += delta)
		{
			graphics.drawLine((int) this.margin.getLeft(), (int) y,
					(int) (this.margin.getLeft() + this.width), (int) y);
		}

		graphics.drawLine((int) this.margin.getLeft(),
				(int) (this.yPosition + this.height),
				(int) (this.margin.getLeft() + this.width),
				(int) (this.yPosition + this.height));

		for (x = this.margin.getLeft(); x <= this.margin.getLeft() + this.width; x += delta)
		{
			graphics.drawLine((int) x, (int) this.yPosition, (int) x,
					(int) (this.yPosition + this.height));
		}

		graphics.drawLine((int) (this.margin.getLeft() + this.width),
				(int) this.yPosition,
				(int) (this.margin.getLeft() + this.width),
				(int) (this.yPosition + this.height));
	}

	//------------------------------------------
	// --- GETTERS, SETTERS
	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

}
