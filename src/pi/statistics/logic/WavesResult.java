package pi.statistics.logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class WavesResult {
	
	private Map<String, StatisticResult> wavesResult = new HashMap<String, StatisticResult>();

	public Map<String, StatisticResult> getWavesResult() {
		return wavesResult;
	}

	public void addValue(String name, StatisticResult result) {
		wavesResult.put(name, result);
	}



	// Set, List, Map, SortedSet, SortedMap, HashSet, TreeSet, ArrayList,
	// LinkedList, Vector, Collections, Arrays, AbstractCollection
}
