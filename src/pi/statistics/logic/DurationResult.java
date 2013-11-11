package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;

public class DurationResult {
	private static Map<String, Double> value = new HashMap<String, Double>();

	public static Map<String, Double> getValue() {
		return value;
	}

	public static void addValue(String name, Double result ) {
		value.put(name, result);
	}
	
	public static void clearValues(){
		value.clear();
	}

}
