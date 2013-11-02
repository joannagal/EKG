package pi.statistics.functions;

import java.util.Vector;

import pi.inputs.signal.Probe;
import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Max extends Function {

    private int max;
    
    public Max() {
	super("Max");
    }

    @Override
    public void countResult() {
	Vector<Double> result = new Vector<Double>();
	result.add((double)max);
	StatisticResult.addValue(this.getName(), result);
    }

    @Override
    public void iterate(Probe probe) {
	if (probe.getValue() < max) max = probe.getValue();
    }

    public void setName(String waveName){
	super.setName(waveName);
    }

    @Override
    public void setWaveName(String waveName) {
	// TODO Auto-generated method stub
	
    }
}
