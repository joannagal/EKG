package pi.statistics.functions;

import java.util.Vector;

import pi.inputs.signal.Probe;
import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Average extends Function {

    public Average() {
	super("Average");
    }

    private int sum = 0;
    private int denominator = 0;

    public void countResult() {
	Vector<Double> result = new Vector<Double>();

	if (denominator != 0) {
	    double avg = (sum / denominator);

	    result.add(avg);
	} else {
	    // TODO Co jeœli mianownik (liczba próbek) jest zerem
	}
	StatisticResult.addValue(this.getName(), result);
    }

    public void iterate(Probe probe) {
	sum += probe.getValue();
	denominator++;

    }

    public void setName(String waveName) {
	super.setName(waveName);
    }

    @Override
    public void setWaveName(String waveName) {
	// TODO Auto-generated method stub
	
    }

}
