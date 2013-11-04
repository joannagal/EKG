package pi.statistics.functions;

import java.util.Vector;

import pi.inputs.signal.Probe;
import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

//SD - standard deviation (odchylenie standardowe)
public class SD extends Function {
    
    public SD() {
	super("SD");
    }

    @Override
    public void countResult() {
	Vector<Double> result = new Vector<Double>();

	double var = StatisticResult.getValue().get("Variance").firstElement();
	// TODO co z mullami?
	if (var != 0) {
	    double sd = Math.sqrt(var);
	    result.add(sd);
	}

	StatisticResult.addValue(this.getName(), result);
    }

    @Override
    public void iterate(double value) {

    }

}
