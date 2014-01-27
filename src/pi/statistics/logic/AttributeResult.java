package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;

public class AttributeResult {
    private Map<String, WavesResult> atrResult = new HashMap<String, WavesResult>();

    public Map<String, WavesResult> getValue() {
	return atrResult;
    }

    public void addValue(String name, WavesResult result) {
	atrResult.put(name, result);
    }

    public void clearValues() {
	atrResult.clear();
    }

}
