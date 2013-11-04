package pi.statistics.functions;

import java.util.Vector;

import pi.inputs.signal.Probe;
import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Min extends Function {

    private double min = 0;
    
    public Min() {
	super("Min");
    }

    @Override
    public void countResult() {
	Vector<Double> result = new Vector<Double>();
	result.add(min);
	StatisticResult.addValue(this.getName(), result);
    }

    @Override
    public void iterate(double value) {
	if (value < min) min = value;
    }


}
