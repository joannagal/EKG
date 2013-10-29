package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class StatisticResult {
	
		private static Map<String, Vector<Double>> value = new HashMap<String, Vector<Double>>();

		public static Map<String, Vector<Double>> getValue() {
			return value;
		}

		public static void addValue(String name, Vector<Double> result ) {
			value.put(name, result);
		}
		
		public static void clearValues(){
			value.clear();
		}
		
}
