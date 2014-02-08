package pi.statistics.logic.report;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import pi.shared.SharedController;
import pi.statistics.logic.ProjectResult;

public class PopulStatistic {

	private int sampleSize;
	private String firstPopul;
	private String secondPopul;
	private String tableName;
	private String channelName;
	private String attributeName;
	private String waveName;
	private String statisticName;
	private double firstAverage;
	private double firstDeviation;
	private double secondAverage;
	private double secondDeviation;
	private double p_value;

	@SuppressWarnings("rawtypes")
	public static Collection getPopulStatistics() {
		Vector<PopulStatistic> statistics = new Vector<PopulStatistic>();

		ProjectResult projectResult = SharedController.getInstance()
				.getProjectRes();

		Map<String, Map<String, Map<String, Map<String, Map<String, Vector<Double>>>>>> map = projectResult
				.getTestResult();

		String[] names = { "P1AB", "P2AB", "dAB", "BB", "AA" };

		for (int c = 0; c < 5; c++) {
			Map<String, Map<String, Map<String, Map<String, Vector<Double>>>>> columnEntry = map
					.get(names[c]);
			if (columnEntry == null)
				continue;

			int s1 = SharedController.getInstance().getProject()
					.getFirstPopulation().getSpecimen().size();
			int max = s1;

			if (SharedController.getInstance().getProject()
					.getSecondPopulation() != null) {
				int s2 = SharedController.getInstance().getProject()
						.getSecondPopulation().getSpecimen().size();
				if (s2 > max)
					max = s2;
			}

			String firstName = SharedController.getInstance().getProject()
					.getFirstPopulation().getName();

			String secondName = "";
			if (SharedController.getInstance().getProject()
					.getSecondPopulation() != null) {
				secondName = SharedController.getInstance().getProject()
						.getSecondPopulation().getName();
			}

			for (String channelName : columnEntry.keySet()) {
				for (String waveName : columnEntry.get(channelName)
						.get("Duration").keySet()) {
					for (String statName : columnEntry.get(channelName)
							.get("Duration").get(waveName).keySet()) { 

						Vector<Double> list = columnEntry.get(channelName)
								.get("Duration").get(waveName).get(statName);

						if (list == null)
							continue;

						if (list.size() < 1)
							continue;

						PopulStatistic ps = new PopulStatistic();
						ps.setFirstPopul(firstName);
						ps.setSecondPopul(secondName);
						ps.setSampleSize(max);

						String name = "";
						if (names[c].equals("P1AB"))
							name = SharedController.getInstance().getProject()
									.getFirstPopulation().getName();

						else if (names[c].equals("P2AB"))
							name = SharedController.getInstance().getProject()
									.getSecondPopulation().getName();
						else if (names[c].equals("dAB"))
							name = "Differences between changes in populations";
						else if (names[c].equals("AA"))
							name = "Differences between populations (Before)";
						else if (names[c].equals("BB"))
							name = "Differences between populations (After)";

						ps.setTableName(name);
						ps.setChannelName(channelName);
						ps.setAttributeName("Duration");
						ps.setWaveName(waveName);
						ps.setStatisticName(statName);

						ps.setFirstAverage(list.get(0));
						ps.setFirstDeviation(list.get(1));
						ps.setSecondAverage(list.get(2));
						ps.setSecondDeviation(list.get(3));
						ps.setP_value(list.get(6));
						statistics.add(ps);

					}
				}
			}
			for (String channelName : columnEntry.keySet()) {
				for (String waveName : columnEntry.get(channelName)
						.get("Amplitude").keySet()) {
					for (String statName : columnEntry.get(channelName)
							.get("Amplitude").get(waveName).keySet()) {
						Vector<Double> list = columnEntry.get(channelName)
								.get("Amplitude").get(waveName).get(statName);

						if (list == null)
							continue;

						if (list.size() < 1)
							continue;

						PopulStatistic ps = new PopulStatistic();
						ps.setFirstPopul(firstName);
						ps.setSecondPopul(secondName);
						ps.setSampleSize(max);

						String name = "";
						if (names[c].equals("P1AB"))
							name = SharedController.getInstance().getProject()
									.getFirstPopulation().getName();

						else if (names[c].equals("P2AB"))
							name = SharedController.getInstance().getProject()
									.getSecondPopulation().getName();
						else if (names[c].equals("dAB"))
							name = "Differences between changes in populations";
						else if (names[c].equals("AA"))
							name = "Differences between populations (Before)";
						else if (names[c].equals("BB"))
							name = "Differences between populations (After)";

						ps.setTableName(name);
						ps.setChannelName(channelName);
						ps.setAttributeName("Amplitude");
						ps.setWaveName(waveName);
						ps.setStatisticName(statName);

						ps.setFirstAverage(list.get(0));
						ps.setFirstDeviation(list.get(1));
						ps.setSecondAverage(list.get(2));
						ps.setSecondDeviation(list.get(3));
						ps.setP_value(list.get(6));
						statistics.add(ps);

					}
				}
			}

		}

		return statistics;
	}

	public int getSampleSize() {
		return sampleSize;
	}

	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}

	public String getFirstPopul() {
		return firstPopul;
	}

	public void setFirstPopul(String firstPopul) {
		this.firstPopul = firstPopul;
	}

	public String getSecondPopul() {
		return secondPopul;
	}

	public void setSecondPopul(String secondPopul) {
		this.secondPopul = secondPopul;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getWaveName() {
		return waveName;
	}

	public void setWaveName(String waveName) {
		this.waveName = waveName;
	}

	public String getStatisticName() {
		return statisticName;
	}

	public void setStatisticName(String statisticName) {
		this.statisticName = statisticName;
	}

	public double getFirstAverage() {
		return firstAverage;
	}

	public void setFirstAverage(double firstAverage) {
		this.firstAverage = firstAverage;
	}

	public double getFirstDeviation() {
		return firstDeviation;
	}

	public void setFirstDeviation(double firstDeviation) {
		this.firstDeviation = firstDeviation;
	}

	public double getSecondAverage() {
		return secondAverage;
	}

	public void setSecondAverage(double secondAverage) {
		this.secondAverage = secondAverage;
	}

	public double getSecondDeviation() {
		return secondDeviation;
	}

	public void setSecondDeviation(double secondDeviation) {
		this.secondDeviation = secondDeviation;
	}

	public double getP_value() {
		return p_value;
	}

	public void setP_value(double p_value) {
		this.p_value = p_value;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

}
