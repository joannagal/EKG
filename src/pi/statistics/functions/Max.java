package pi.statistics.functions;

import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Max extends Function {

    private double max;
    
    public Max() {
	super("Max");
    }

    @Override
    public void countResult() {
	StatisticResult.addValue(this.getName(), max);
    }

    @Override
    public void iterate(double value) {
	if (value < max) max = value;
    }

}
