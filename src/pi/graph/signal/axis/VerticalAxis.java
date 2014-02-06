package pi.graph.signal.axis;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import pi.shared.schemes.signal.SignalScheme;

public class VerticalAxis {
	private static int MEMORY = 20;

	private double x, y;
	private int length;
	private double distance;
	private double shift;
	private double startValue;
	private double interval;

	private Value[] point;
	private Marker[] marker;
	private SignalScheme scheme;

	public VerticalAxis() {
		this.point = new Value[MEMORY];
		this.marker = new Marker[MEMORY];

		for (int i = 0; i < MEMORY; i++) {
			this.point[i] = new Value();
			this.marker[i] = new Marker();
		}

	}

	public boolean isInAxis(int x, int y) {
		if ((x < this.x) && (x > this.x - scheme.getFontSize() * 4)
				&& (y <= this.y) && (y >= this.y - this.length)) {
			return true;
		}

		return false;
	}

	public void draw(Graphics graphics, Font font) {
		graphics.setFont(font);
		graphics.setColor(scheme.getFontColor());

		for (int i = 0; point[i].isActive(); i++) {
			graphics.drawString(String.format("%.2fmV", point[i].getValue()),
					point[i].getPosition().x, point[i].getPosition().y);

			graphics.drawLine(marker[i].getPosition().x,
					marker[i].getPosition().y, marker[i].getEndX(),
					marker[i].getEndY());
		}

	}

	public void shiftFonts(FontMetrics fontMetrics) {
		double xshift = 0.0d;

		for (int i = 0; point[i].isActive(); i++) {
			xshift = fontMetrics.stringWidth(String.format("%.2fmV",
					point[i].getValue()));

			point[i].setPosition((int) (point[i].getPosition().x - xshift),
					(int) (point[i].getPosition().y));
		}
	}

	public void recalculate() {

		double y = this.y - this.shift;
		int n = 0;

		double value = this.startValue;
		double xshift = 0.0d;
		double yshift = 0.0d;

		yshift = scheme.getFontSize() / 2;

		for (; y > this.y - length; n++) {
			value = this.startValue + n * this.interval;
			y = this.y - this.shift - n * this.distance;

			marker[n].setPosition((int) this.x, (int) y);
			marker[n].setVector(-scheme.getFontSize() / 2, 0);

			point[n].setId(0);
			point[n].setActive(true);
			point[n].setValue(value);

			xshift = -(scheme.getFontSize() / 2 + 5);
			point[n].setPosition((int) (this.x + xshift), (int) (y + yshift));
		}

		for (; n < MEMORY; n++) {
			point[n].setActive(false);
		}

	}

	public void setBegin(int x, int y) {
		this.x = x;
		this.y = y;
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
