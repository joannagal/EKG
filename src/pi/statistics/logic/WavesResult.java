package pi.statistics.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WavesResult {

	private Map<String, StatisticResult> wavesResult = new HashMap<String, StatisticResult>();
	private Map<String, ArrayList<Double>> wavesCollector = new HashMap<String, ArrayList<Double>>();

	public Map<String, StatisticResult> getWavesResult() {
		return wavesResult;
	}

	public void addValue(String name, StatisticResult result) {
		wavesResult.put(name, result);
	}

	public Map<String, ArrayList<Double>> getWavesCollector() {
		return wavesCollector;
	}

	public void addCollector(String name, ArrayList<Double> collector) {
		wavesCollector.put(name, collector);
	}

	public void clearAll() {
		wavesResult.clear();
		wavesCollector.clear();
	}

}
