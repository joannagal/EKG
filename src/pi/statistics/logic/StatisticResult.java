package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;

public class StatisticResult {

	private Map<String, Double> value = new HashMap<String, Double>();

	public Map<String, Double> getValue() {
		return value;
	}

	public void addValue(String name, Double result) {

		value.put(name, result);
	}

	public void clearValues() {
		value.clear();
	}


}
