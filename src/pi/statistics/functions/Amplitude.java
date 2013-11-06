package pi.statistics.functions;

import java.util.Vector;

import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Amplitude extends Function {
    
    public Amplitude() {
	super("Amplitude");
    }

    @Override
    public void countResult() {
	Vector<Double> result = new Vector<Double>();
	
	//TODO null?
	double min = StatisticResult.getValue().get("Min").firstElement();
	double max = StatisticResult.getValue().get("Max").firstElement();
	double amplitude = max - min;
	
	result.add(amplitude);
	StatisticResult.addValue(this.getName(), result);
    }

    @Override
    public void iterate(double value) {

    }

}
