package pi.statistics.functions;

import java.util.ArrayList;

import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Collector extends Function {

	private ArrayList<Double> result = new ArrayList<Double>();

	public Collector() {
		super("Collector");
	}

	@Override
	public void countResult(StatisticResult statResult) {
	}

	@Override
	public void iterate(double value) {
		getResult().add(value);
	}

	@Override
	public void backToBegin() {
		this.result.clear();
	}

	public ArrayList<Double> getResult() {
		return result;
	}

	public void setResult(ArrayList<Double> result) {
		this.result = result;
	}

}
