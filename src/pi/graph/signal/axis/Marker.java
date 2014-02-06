package pi.graph.signal.axis;

import java.awt.Point;

public class Marker {
	private Point position;
	private Point vector;

	public Marker() {
		this.vector = new Point();
		this.position = new Point();
	}

	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	public void setVector(int x, int y) {
		this.vector.x = x;
		this.vector.y = y;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getVector() {
		return vector;
	}

	public void setVector(Point vector) {
		this.vector = vector;
	}

	public int getEndX() {
		return position.x + vector.x;
	}

	public int getEndY() {
		return position.y + vector.y;
	}

}
