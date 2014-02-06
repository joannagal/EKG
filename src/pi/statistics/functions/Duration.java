package pi.statistics.functions;

import pi.statistics.logic.DurationResult;

public class Duration {

	private String name;

	public void countDuration(int left, int right, Double interval,
			DurationResult dResult) {
		double dur = (right - (left + 1)) * interval;

		dResult.addValue(this.getName(), dur);
	}

	public String getName() {
		return name;
	}

	public void setName(String waveName) {
		this.name = waveName;
	}

}
