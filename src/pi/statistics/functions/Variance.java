package pi.statistics.functions;

import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Variance extends Function {
    private double x = 0;
    private double y = 0;
    private double var = 0;
    private int n = 0;

    public Variance() {
	super("Variance");
    }

    @Override
    public void countResult() {
//
//	if (n != 0) {
//	    var = y / n;
//	} else {
//	    // TODO Co jeœli mianownik (liczba próbek) jest zerem
//	}
//	StatisticResult.addValue(this.getName(), var);

    }

    @Override
    public void iterate(double value) {
	//TODO ZMIENIÆ!!
//	double avg = StatisticResult.getValue().get("Average");
//	// TODO co z nullami?
//
//	if (avg != 0) {
//	    x = Math.pow((value - avg), 2);
//	    y += x;
//	    n++;
//	}

    }

}
