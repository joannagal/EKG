package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;

public class EcgResult {
	private Map<String, ChannelResult> value = new HashMap<String, ChannelResult>();

	public Map<String, ChannelResult> getValue() {
		return value;
	}

	public void addValue(String name, ChannelResult value) {
		this.value.put(name, value);
	}

}
