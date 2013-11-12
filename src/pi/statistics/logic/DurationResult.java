package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DurationResult {
	private  Map<String, Vector<Double>> value = new HashMap<String, Vector<Double>>();

	public  Map<String, Vector<Double>> getValue() {
		return value;
	}

	public  void addValue(String name, Double result ) {
		if (value.containsKey(name)){
		    value.get(name).add(result);
		}
		else {
		Vector<Double> vec = new Vector<Double>();
		vec.add(result);
	    	value.put(name, vec);
		}
	}
	
	public  void clearValues(){
		value.clear();
	}
	
	public  void printDurations(){
	    for (String string : value.keySet()){
		System.out.println(string + ": \n");
		System.out.println(value.get(string) + "\n");
		
	    }
	}

}
