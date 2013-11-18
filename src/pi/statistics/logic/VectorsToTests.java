package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class VectorsToTests {
    private Vector<Double> numbers;
    private Map<String, Vector<Double>> values;
    private Map<String, Map<String, Vector<Double>>> vectors = new HashMap<String, Map<String, Vector<Double>>>();
    
    public void printVectors(){
	for (String vectName : vectors.keySet()){
	    System.out.println(vectName);
	    for (String statName : vectors.get(vectName).keySet()){
		    System.out.println(statName);
		    System.out.println(vectors.get(vectName).get(statName));

	    }
	}
    }
    
    public Map<String, Map<String, Vector<Double>>> getVectors() {
	return vectors;
    }

    public void addVector(String name, String statName, double value) {
	
	if (vectors.containsKey(name) == false) {
	    values = new HashMap<String, Vector<Double>>();
	    addValue(statName, value);
	    vectors.put(name, values);
	} else {
	    if (vectors.get(name).get(statName) == null) {
		addValue(statName, value);
	    } else {
		vectors.get(name).get(statName).add(value);
	    }
	}
	    
    }

    public Map<String, Vector<Double>> getValues() {
	return values;
    }

    public void addValue(String name, double value) {
	numbers = new Vector<Double>();
	addNumber(value);
	values.put(name, numbers);
    }

    public Vector<Double> getNumbers() {
	return numbers;
    }

    public void addNumber(double number) {
	numbers.add(number);
    }
}
