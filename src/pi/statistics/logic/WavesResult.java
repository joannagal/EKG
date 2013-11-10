package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;

public class WavesResult {
    private Map<String, StatisticResult> wavesResult = new HashMap<String, StatisticResult>();

    public Map<String, StatisticResult> getValue() {
            return wavesResult;
    }

    public void addValue(String name, StatisticResult result ) {
            wavesResult.put(name, result);
    }

}
