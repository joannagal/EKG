package pi.statistics.functions;

import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticResult;

public class Average extends Function {

    public Average() {
	super("Average");
    }

    private int sum = 0;
    private int denominator = 0;

    public void countResult() {
	double avg;
	if (denominator != 0) {
	   avg = (sum / denominator);
	} else {
	    avg = 0;
	    // TODO Co jeœli mianownik (liczba próbek) jest zerem
	}
	StatisticResult.addValue(this.getName(), avg);
    }

    public void iterate(double value) {
	sum += value;
	denominator++;

    }


}
