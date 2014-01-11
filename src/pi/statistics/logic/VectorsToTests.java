package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class VectorsToTests {
    private Vector<Double> numbers;
    private Map<String, Vector<Double>> values;
    private Map<String, Map<String, Vector<Double>>> waves = new HashMap<String, Map<String, Vector<Double>>>();
    private Map<String, Map<String, Map<String, Vector<Double>>>> vectors = new HashMap<String, Map<String, Map<String, Vector<Double>>>>();

    public void clearVectors() {
	getVectors().clear();
	// values.clear();
	// numbers.clear();
    }

    public void printVectors() {
	for (String vectName : getVectors().keySet()) {
	    System.out.println(vectName);
	    for (String waveName : getVectors().get(vectName).keySet()) {
		System.out.println(waveName);
		for (String statName : getVectors().get(vectName).get(waveName)
			.keySet()) {
		    System.out.println(statName);
		    System.out.println(getVectors().get(vectName).get(waveName)
			    .get(statName));
		}
	    }
	}
    }

    public Map<String, Map<String, Vector<Double>>> getWaves() {
	return waves;
    }

    public void addWave(String name, String statName, double value) {

	if (waves.containsKey(name) == false) {
	    values = new HashMap<String, Vector<Double>>();
	    addValue(statName, value);
	    waves.put(name, values);
	} else {
	    if (waves.get(name).get(statName) == null) {
		addValue(statName, value);
	    } else {
		waves.get(name).get(statName).add(value);
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

    public Map<String, Map<String, Map<String, Vector<Double>>>> getVectors() {
	return vectors;
    }

    //TODO do poprawki
    public void addVector(String name, String waveName, String statName,
	    double value) {
	if (vectors.containsKey(name) == false) {
	    waves = new HashMap<String, Map<String, Vector<Double>>>();
	    addWave(waveName, statName, value);
	    vectors.put(name, waves);
	} else {
	    if (vectors.get(name).get(waveName) == null) {
		addWave(waveName, statName, value);
	    } else {
		if (vectors.get(name).get(waveName).get(statName) == null) {
		    addValue(statName, value);
		} else
		    vectors.get(name).get(waveName).get(statName).add(value);
	    }
	}

    }

}
