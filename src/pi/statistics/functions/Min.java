package pi.statistics.functions;

import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Min extends Function {

    private double min = 0;
    
    public Min() {
	super("Min");
    }

    @Override
    public void countResult() {
	StatisticResult.addValue(this.getName(), min);
    }

    @Override
    public void iterate(double value) {
	if (value < min) min = value;
    }


}
