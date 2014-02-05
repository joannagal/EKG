package pi.statistics.logic.report;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import pi.shared.SharedController;
import pi.statistics.logic.ProjectResult;

public class PopulStatistic {

    private int sampleSize;
    private String tableName;
    private String firstPopul;
    private String secondPopul;
    private String channelName;
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

	// //TODO
	// PopulStatistic ps1 = new PopulStatistic();
	// ps1.channelName = "channel 1";
	// ps1.statisticName = "Average";
	// ps1.waveName = "P-wave";
	// ps1.firstAverage = 0.4;
	// ps1.secondAverage = 0.5;
	//
	// PopulStatistic ps2 = new PopulStatistic();
	// ps2.channelName = "channel 1";
	// ps2.statisticName = "Wariance";
	// ps2.waveName = "P-wave";
	// ps2.firstAverage = 0.9;
	// ps2.secondAverage = 0.7;
	//
	// statistics.add(ps1);
	// statistics.add(ps2);

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

	    // dla kazdego kana³u...
	    for (String channelName : columnEntry.keySet()) {
		// wyciagamy kazdy wave...
		for (String waveName : columnEntry.get(channelName).keySet()) {
		    // ... dla niego statystyke ...
		    for (String statName : columnEntry.get(channelName)
			    .get(waveName).get("Duration").keySet()) {

			Vector<Double> list = columnEntry.get(channelName)
				.get(waveName).get("Duration").get(statName);

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
}
