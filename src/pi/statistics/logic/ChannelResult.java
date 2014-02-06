package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;

public class ChannelResult {
	private Map<String, AttributeResult> channelResult = new HashMap<String, AttributeResult>();

	public Map<String, AttributeResult> getValue() {
		return channelResult;
	}

	public void addValue(String name, AttributeResult result) {
		channelResult.put(name, result);
	}

	public void clearValues() {
		channelResult.clear();
	}

}
