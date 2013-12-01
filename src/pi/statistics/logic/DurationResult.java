package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DurationResult {
    private Map<String, Vector<Double>> value = new HashMap<String, Vector<Double>>();

    public Map<String, Vector<Double>> getValue() {
	return value;
    }

    public void addValue(String name, Double result) {
	if (value.containsKey(name)) {
	    value.get(name).add(result);
	} else {
	    Vector<Double> vec = new Vector<Double>();
	    vec.add(result);
	    value.put(name, vec);
	}
    }

    public void clearValues() {
	value.clear();
    }

    public void printDurations() {
	for (String string : value.keySet()) {
	    System.out.println(string + ": \n");
	    System.out.println(value.get(string) + "\n");

	}
    }

    public void checkRR() {
	if (value.containsKey("RR_interval")) {
	    Vector<Double> toRemove = new Vector<Double>();
	    double min = 99999;
	    for (Double var : value.get("RR_interval")) {
		if (var < 0)
		    toRemove.add(var);
		if (var >= 0 && var < min) min = var;
	    }

	    for (Double var : value.get("RR_interval")) {
		if (var > 1.5*min)
		    toRemove.add(var);
	    }
	    for (Double var : toRemove) {
		value.get("RR_interval").removeElement(var);
	    }
	}

	
	
    }
}
