package pi.statistics.logic;

import java.util.ArrayList;

public class AttributeResult {
	private ArrayList<StatisticResult> value = new ArrayList<>();

	
	public ArrayList<StatisticResult> getValue() {
		return value;
	}

	public void addValue(StatisticResult value) {
		this.value.add(value);
	}
}
