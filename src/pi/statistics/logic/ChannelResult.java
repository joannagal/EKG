package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;

public class ChannelResult {
    private Map<String, WavesResult> channelResult = new HashMap<String, WavesResult>();

        public Map<String, WavesResult> getValue() {
                return channelResult;
        }

        public void addValue(String name, WavesResult result ) {
                channelResult.put(name, result);
        }

}
