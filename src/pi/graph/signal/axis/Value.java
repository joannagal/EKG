package pi.graph.signal.axis;

import java.awt.Point;

public class Value
{
	private Point position;
	private double value;
	private int width;
	private int id;

	private boolean active;

	public Value()
	{
		position = new Point();
	}

	public Value(Point position, double value, int id)
	{
		this.active = false;
		this.position = position;
		this.value = value;
		this.id = id;
	}

	public void setPosition(int x, int y)
	{
		this.position.x = x;
		this.position.y = y;
	}

	public Point getPosition()
	{
		return position;
	}

	public void setPosition(Point position)
	{
		this.position = position;
	}

	public double getValue()
	{
		return value;
	}

	public void setValue(double value)
	{
		this.value = value;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}
}
