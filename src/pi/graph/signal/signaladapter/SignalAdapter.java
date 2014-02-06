package pi.graph.signal.signaladapter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import pi.graph.signal.Segment;
import pi.inputs.signal.Channel;
import pi.inputs.signal.Probe;
import pi.shared.schemes.signal.SignalScheme;
import pi.utilities.DPoint;
import pi.utilities.Margin;

public class SignalAdapter {
	private Segment segment;

	private Channel signal;
	private SignalScheme scheme;

	private int startPoint;
	private int stopPoint;

	private Margin margin;
	private double height;
	private double width;

	private double posY;

	public SignalAdapter(Segment segment) {
		this.segment = segment;
		this.signal = this.segment.getGraph().getSignal();
		this.startPoint = -1;
		this.stopPoint = -1;
	}

	public void recalculate() {
		ArrayList<Probe> probe = this.signal.getProbe();

		this.scheme = this.segment.getGraph().getScheme();
		this.signal = this.segment.getGraph().getSignal();
		this.margin = this.scheme.getMargin();
		this.width = segment.getGrid().getWidth();
		this.height = segment.getGrid().getHeight();

		posY = segment.getId() * segment.getGraph().getSegmentSize().height;

		int capacity = probe.size();

		this.startPoint = getPointFromTime(this.segment.getStartTime()) - 1;
		this.stopPoint = getPointFromTime(this.segment.getStopTime());

		if (this.startPoint == -1)
			this.startPoint = 0;
		if (this.stopPoint == -1)
			this.stopPoint = 0;

		if (this.startPoint > capacity)
			this.startPoint = capacity - 1;
		if (this.stopPoint > capacity)
			this.stopPoint = capacity - 1;

		if ((this.startPoint + this.stopPoint == 0)
				|| (this.startPoint + this.stopPoint == (capacity - 1) * 2)) {
			this.startPoint = -1;
			return;
		}

		capacity = stopPoint - startPoint + 1;
	}

	public int getPointFromTime(double time) {
		int n = 0;

		double current = this.signal.getTranslation();

		while (current < time) {
			n++;
			current = this.signal.getTranslation() + (double) n
					* this.signal.getInterval();
		}

		return n;
	}

	public double getValueFromTime(double time) {
		int left = this.getPointFromTime(time) - 1;
		int right = left + 1;

		int capacity = this.signal.getProbe().size();

		if (left == -1)
			return -1;
		if (right >= capacity)
			return -1;

		Probe probe = this.signal.getProbe().get(left);

		double check = (double) probe.getNumber() * this.signal.getInterval()
				+ this.signal.getTranslation();

		DPoint p1, p2;

		p1 = new DPoint(0.0d, 0.0d);
		p2 = new DPoint(0.0d, 0.0d);

		p1.x = check;
		p1.y = probe.getNormalized();

		probe = this.signal.getProbe().get(right);

		p2.x = check + this.signal.getInterval();
		p2.y = probe.getNormalized();

		this.crossPoint(p1, p2, time, true);

		return p1.y;
	}

	public double getYFromX(double x) {
		double time = this.segment.getTimeFormPosition(x);

		double value = this.getValueFromTime(time);
		return this.getYFromValue(value);
	}

	public double getXFromTime(double time) {
		double result = (time - segment.getStartTime()) * this.width
				/ (segment.getStopTime() - segment.getStartTime());

		result += (double) this.margin.getLeft();

		return result + 0.5d;
	}

	public double getYFromValue(double value) {
		double result = (double) (value - this.signal.getMinValue())
				* this.height
				/ (this.signal.getMaxValue() - this.signal.getMinValue());

		return this.margin.getTop() + height - result + 0.5d;
	}

	public void crossPoint(DPoint p1, DPoint p2, double x, boolean side) {
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;

		double a = dy / dx;
		double b = p1.y - a * p1.x;

		double newY = a * (double) x + b;

		if (side) {
			p1.x = x;
			p1.y = newY;
		} else {
			p2.x = x;
			p2.y = newY;
		}
	}

	public void draw(Graphics graphics) {
		if (this.startPoint == -1)
			return;

		ArrayList<Probe> probe = this.signal.getProbe();

		Graphics2D g2d = (Graphics2D) graphics;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setColor(this.scheme.getSignalColor());

		DPoint p1 = new DPoint(0.0d, 0.0d);
		DPoint p2 = new DPoint(0.0d, 0.0d);

		g2d.setStroke(this.scheme.getSignalStroke());

		for (int i = this.startPoint; i < this.stopPoint; i++) {
			Probe tempProbe = probe.get(i);
			double time = (double) tempProbe.getNumber()
					* this.signal.getInterval() + this.signal.getTranslation();

			p1.x = this.getXFromTime(time);
			p1.y = this.getYFromValue(tempProbe.getNormalized());

			int from = i + 1;
			if (from > this.stopPoint)
				from = i;

			tempProbe = probe.get(from);
			time = (double) tempProbe.getNumber() * this.signal.getInterval()
					+ this.signal.getTranslation();

			p2.x = this.getXFromTime(time);
			p2.y = this.getYFromValue(tempProbe.getNormalized());

			if ((p1.x <= this.margin.getLeft())
					&& (p2.x >= this.margin.getLeft())) {
				this.crossPoint(p1, p2, (int) this.margin.getLeft(), true);
			}

			if ((p1.x <= this.margin.getLeft() + this.width)
					&& (p2.x >= this.margin.getLeft() + this.width)) {
				this.crossPoint(p1, p2,
						(int) (this.margin.getLeft() + this.width), false);
			}

			graphics.drawLine((int) p1.x, (int) (posY + p1.y), (int) p2.x,
					(int) (posY + p2.y));
		}

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

}
