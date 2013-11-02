package pi.statistics.functions;

import java.util.Vector;

import pi.inputs.signal.Probe;
import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Amplitude extends Function {
    private String waveName;
    
    public Amplitude() {
	super("Amplitude");
    }

    @Override
    public void countResult() {
	Vector<Double> result = new Vector<Double>();
	
	//TODO null?
	double min = StatisticResult.getValue().get("Min "+ waveName).firstElement();
	double max = StatisticResult.getValue().get("Max "+ waveName).firstElement();
	double amplitude = max - min;
	
	result.add(amplitude);
	StatisticResult.addValue(this.getName(), result);
    }

    @Override
    public void iterate(Probe probe) {

    }
    
    public void setName(String waveName){
	super.setName(waveName);
    }

    @Override
    public void setWaveName(String waveName) {
	this.waveName = waveName;
    }
}
