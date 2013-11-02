package pi.statistics.functions;

import java.util.Vector;

import pi.inputs.signal.Probe;
import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Variance extends Function {
    private String waveName;
    private double x = 0;
    private double y = 0;
    private double var = 0;
    private int n = 0;

    public Variance() {
	super("Variance");
    }

    @Override
    public void countResult() {
	Vector<Double> result = new Vector<Double>();

	if (n != 0) {
	    var = y / n;
	    result.add(var);
	} else {
	    // TODO Co jeœli mianownik (liczba próbek) jest zerem
	}
	StatisticResult.addValue(this.getName(), result);

    }

    @Override
    public void iterate(Probe probe) {
	double avg = StatisticResult.getValue().get("Average " + waveName).firstElement();
	// TODO co z nullami?

	if (avg != 0) {
	    x = Math.pow((probe.getValue() - avg), 2);
	    y += x;
	    n++;
	}

    }

    public void setName(String waveName) {
	super.setName(waveName);
    }

    @Override
    public void setWaveName(String waveName) {
	this.waveName = waveName;
    }
}
