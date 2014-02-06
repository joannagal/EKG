package pi.graph.signal.axis;

import java.awt.Graphics;

import pi.graph.signal.Segment;
import pi.graph.signal.axis.HorizontalAxis;
import pi.graph.signal.axis.VerticalAxis;
import pi.inputs.signal.Channel;
import pi.shared.SharedController;
import pi.shared.schemes.signal.SignalScheme;
import pi.utilities.Margin;

public class AxisController {
	private Segment segment;
	private SignalScheme scheme;

	private VerticalAxis vertical;
	private HorizontalAxis horizontal;

	private boolean firstDraw;

	private Margin margin;
	private Channel signal;

	private double width;
	private double height;

	private double segmentHeight;

	public AxisController(Segment segment) {
		this.segment = segment;
		this.scheme = segment.getGraph().getScheme();

		this.firstDraw = true;

		vertical = new VerticalAxis();
		horizontal = new HorizontalAxis();
	}

	public void prepareHorizontal() {
		horizontal
				.setBegin(
						(int) margin.getLeft(),
						(int) ((segment.getId() * segmentHeight)
								+ margin.getTop() + height));

		horizontal.setLength((int) width);

		horizontal.setDistance(SharedController.getInstance()
				.getPixelsForScale());

		int distance = (int) width * this.segment.getId();
		int shift = -distance;
		int pixels = (int) SharedController.getInstance().getPixelsForScale();

		while (shift < 0) {
			shift += pixels;
		}

		horizontal.setShift(shift);

		if (this.segment.getId() == 0) {
			horizontal.setStartID(0);
		}

		double value = 0.0d;
		value = this.segment.getStartTime();
		value += (double) (shift) * signal.getScale()
				/ SharedController.getInstance().getPixelsForScale();

		horizontal.setStartValue(value);
		horizontal.setInterval(signal.getScale());
		horizontal.setScheme(this.scheme);

		horizontal.recalculate();
	}

	public void prepareVertical() {
		vertical.setBegin(
				(int) margin.getLeft(),
				(int) ((segment.getId() * segmentHeight) + margin.getTop() + height));

		vertical.setLength((int) height);
		vertical.setDistance((height / 2.0d));
		vertical.setShift(0.0d);

		vertical.setStartValue(signal.getMinValue());
		vertical.setInterval((double) (signal.getMaxValue() - signal
				.getMinValue()) / 2.0d);
		vertical.setScheme(this.scheme);
		vertical.recalculate();
	}

	public void recalculate() {
		this.scheme = this.segment.getGraph().getScheme();
		this.firstDraw = true;

		this.margin = this.scheme.getMargin();
		this.signal = this.segment.getGraph().getSignal();

		this.width = segment.getGrid().getWidth();
		this.height = segment.getGrid().getHeight();

		this.segmentHeight = this.segment.getGraph().getSegmentSize().height;

		this.prepareVertical();
		this.prepareHorizontal();
	}

	public void draw(Graphics graphics) {
		if (this.firstDraw) {
			vertical.shiftFonts(graphics.getFontMetrics(this.scheme.getFont()));
			horizontal
					.shiftFonts(graphics.getFontMetrics(this.scheme.getFont()));
			this.firstDraw = false;
		}

		vertical.draw(graphics, this.scheme.getFont());
		horizontal.draw(graphics, this.scheme.getFont());
	}

	public HorizontalAxis getHorizontal() {
		return horizontal;
	}

	public VerticalAxis getVertical() {
		return vertical;
	}
}
