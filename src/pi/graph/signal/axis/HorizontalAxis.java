package pi.graph.signal.axis;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import pi.shared.schemes.signal.SignalScheme;

public class HorizontalAxis {
	private double x;
	private double y;
	private double length;
	private double distance;
	private double shift;
	private double startValue;
	private double interval;

	private int points;
	private int startID;

	private Value[] point;
	private Marker[] marker;
	private SignalScheme scheme;

	public HorizontalAxis() {
		this.point = new Value[20];
		this.marker = new Marker[20];
		this.points = 0;

		for (int i = 0; i < 20; i++) {
			this.point[i] = new Value();
			this.marker[i] = new Marker();
		}
	}

	public void setBegin(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isInAxis(int x, int y) {
		if ((x >= this.x) && (x <= this.x + this.length) && (y >= this.y)
				&& (y <= this.y + this.scheme.getMargin().getBottom())) {
			return true;
		}

		return false;
	}

	public void draw(Graphics graphics, Font font) {
		graphics.setFont(font);
		graphics.setColor(scheme.getFontColor());

		for (int i = 0; point[i].isActive(); i++) {
			graphics.drawString(String.format("%.4fs", point[i].getValue()),
					point[i].getPosition().x, point[i].getPosition().y);

			graphics.drawLine(marker[i].getPosition().x,
					marker[i].getPosition().y, marker[i].getEndX(),
					marker[i].getEndY());

		}
	}

	public void shiftFonts(FontMetrics fontMetrics) {
		double xshift = 0.0d;

		for (int i = 0; point[i].isActive(); i++) {
			xshift = fontMetrics.stringWidth(String.format("%.4fs",
					point[i].getValue())) / 2.0d;

			point[i].setWidth(fontMetrics.stringWidth(String.format("%.4fs",
					point[i].getValue())));
			point[i].setPosition((int) (point[i].getPosition().x - xshift),
					(int) (point[i].getPosition().y));
		}
	}

	public void recalculate() {

		double x = this.x + this.shift;
		int n = 0;

		double value = this.startValue;
		double xshift = 0.0d;
		double yshift = 0.0d;

		for (; x < this.x + length + distance + 1.0d; n++) {
			value = this.startValue + n * this.interval;
			x = this.x + this.shift + n * this.distance;

			if (x <= this.x + length) {
				marker[n].setPosition((int) x, (int) this.y);
				marker[n].setVector(0, +scheme.getFontSize() / 2);

				point[n].setId(startID + n);
				point[n].setActive(true);
				point[n].setValue(value);

				yshift = (scheme.getFontSize() + 10);
				point[n].setPosition((int) (x + xshift),
						(int) (this.y + yshift));
			} else {
				this.points = n + this.startID;
				point[n].setActive(false);
				break;
			}

		}

		for (; n < 20; n++) {
			point[n].setActive(false);
		}

	}

	public int getPoints() {
		return points;
	}

	public void setStartID(int id) {
		this.startID = id;
	}

	public int getStartID() {
		return startID;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setShift(double shift) {
		this.shift = shift;
	}

	public void setInterval(double interval) {
		this.interval = interval;
	}

	public void setStartValue(double startValue) {
		this.startValue = startValue;
	}

	public void setScheme(SignalScheme scheme) {
		this.scheme = scheme;
	}
}
