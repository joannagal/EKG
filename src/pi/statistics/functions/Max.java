package pi.statistics.functions;

import java.util.Vector;

import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Max extends Function {

    private double max;
    
    public Max() {
	super("Max");
    }

    @Override
    public void countResult() {
	Vector<Double> result = new Vector<Double>();
	result.add(max);
	StatisticResult.addValue(this.getName(), result);
    }

    @Override
    public void iterate(double value) {
	if (value < max) max = value;
    }

}
