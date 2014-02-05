package pi.inputs.signal;

import java.util.ArrayList;
import java.util.LinkedList;

import pi.inputs.signal.autofinder.Parameters;

public class Channel {

	private ECG parent = null;
	private String name;
	private Double translation = 0.0d;
	private Double interval = 0.0d;
	private Double endTime = 0.0d;
	private Float fill = 0.0f;
	private LinkedList<Cycle> cycle;
	private ArrayList<Probe> probe;
	private double minValue = 0.0d;
	private double maxValue = 0.0d;
	private Double startAxis = 0.0d;
	private Double scale = 0.0d;
	private Parameters params;

	public Channel() {
		this.cycle = new LinkedList<Cycle>();
	}

	public void recalculate() {
		this.endTime = this.translation + this.probe.size() * this.interval;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

		if (this.name.compareTo("I") == 0)
			this.params = Parameters.getIParameters();
		else if (this.name.compareTo("II") == 0)
			this.params = Parameters.getIIParameters();
		else if (this.name.compareTo("III") == 0)
			this.params = Parameters.getIIIParameters();
		else if (this.name.compareTo("V1") == 0)
			this.params = Parameters.getV1Parameters();
		else if (this.name.compareTo("V2") == 0)
			this.params = Parameters.getV2Parameters();
		else if (this.name.compareTo("V3") == 0)
			this.params = Parameters.getV3Parameters();
		else if (this.name.compareTo("V4") == 0)
			this.params = Parameters.getV4Parameters();
		else if (this.name.compareTo("V5") == 0)
			this.params = Parameters.getV5Parameters();
		else if (this.name.compareTo("V6") == 0)
			this.params = Parameters.getV6Parameters();
		else
			this.params = Parameters.getIParameters();
	}

	public Double getInterval() {
		return interval;
	}

	public void setInterval(Double interval) {
		this.interval = interval;
	}

	public Float getFill() {
		return fill;
	}

	public void setFill(Float fill) {
		this.fill = fill;
	}

	public ArrayList<Probe> getProbe() {
		return probe;
	}

	public void setProbe(ArrayList<Probe> probe) {
		this.probe = probe;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public double getRange() {
		return (this.maxValue - this.minValue);
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public Double getTranslation() {
		return translation;
	}

	public void setTranslation(Double translation) {
		this.translation = translation;
	}

	public Double getStartAxis() {
		return startAxis;
	}

	public void setStartAxis(Double startAxis) {
		this.startAxis = startAxis;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public LinkedList<Cycle> getCycle() {
		return cycle;
	}

	public void setCycle(LinkedList<Cycle> cycle) {
		this.cycle = cycle;
	}

	public Double getEndTime() {
		return endTime;
	}

	public void setEndTime(Double endTime) {
		this.endTime = endTime;
	}

	public Parameters getParams() {
		return params;
	}

	public void setParams(Parameters params) {
		this.params = params;
	}

	public ECG getParent() {
		return parent;
	}

	public void setParent(ECG parent) {
		this.parent = parent;
	}

}
