package pi.statistics.functions;

import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Amplitude extends Function {
    
    public Amplitude() {
	super("Amplitude");
    }

    @Override
    public void countResult() {
	//TODO null?
	System.out.println("obliczanie amplitudy");
	double min = StatisticResult.getValue().get("Min").doubleValue();
	double max = StatisticResult.getValue().get("Max").doubleValue();
	double amplitude = max - min;
	
	StatisticResult.addValue(this.getName(), amplitude);
    }

    @Override
    public void iterate(double value) {

    }

}
