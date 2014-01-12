package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.math3.stat.inference.MannWhitneyUTest;
import org.apache.commons.math3.stat.inference.TTest;
import org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest;

import pi.statistics.tests.LillieforsNormality;

public class ProjectResult {
    private PopulationResult popul1Result;
    private PopulationResult popul2Result;
    // private Map<String, Double> testResult = new HashMap<String, Double>();
    private Map<String, Map<String, Map<String, Map<String, Vector<Double>>>>> testResult = new HashMap<String, Map<String, Map<String, Map<String, Vector<Double>>>>>();
    private Map<String, Map<String, Map<String, Vector<Double>>>> channels;
    private Map<String, Map<String, Vector<Double>>> waves;
    private Map<String, Vector<Double>> stats;

    private double alpha;

    public void summarize() { // TODO sprawdzic czy taki jest wlasciwy wniosek?
	for (String name : testResult.keySet()) {
	    System.out.println(name);
	    System.out.println("wynik:");
	    System.out.println(testResult.get(name));

	    // if (testResult.get(name) > alpha) {
	    // System.out
	    // .println("Wynik dla "
	    // + name
	    // +
	    // ": nie mozna odrzucic hipotezy ze wyniki dla obu populacji sa rowne");
	    // } else {
	    // System.out
	    // .println("Wynik dla "
	    // + name
	    // +
	    // ": mozna odrzucic hipoteze ze wyniki dla obu populacji sa rowne, zatem wyniki sa rozne");
	    // }
	}
    }

    public void performUnpairedTest() {
	beforeUnpaired();
    }

    public void performDifferencesTest() { // przed, po roznych populacji i
					   // roznice po-przed

	// ROZNICE PRZED
	beforeUnpaired();

	// ROZNICE PO
	afterUnpaired();

	// ROZNICA po - przed
	afterBeforeDeffierences();
    }

    public void performPairedTest(int populNo) { // zalezne, roznice miedzy
						 // przed
						 // i po dla tej samej populacji
	PopulationResult popResult = null;
	if (populNo == 1) {
	    popResult = popul1Result;
	} else if (populNo == 2)
	    popResult = popul2Result;

	for (String channelName : popResult.getVectorsBefore().keySet()) {
	    for (String waveName : popResult.getVectorsBefore()
		    .get(channelName).keySet()) {
		for (String statName : popResult.getVectorsBefore()
			.get(channelName).get(waveName).keySet()) {
		    if (popResult.getVectorsAfter().get(channelName)
			    .get(waveName).get(statName) != null) {
			Vector<Double> vector1 = popResult.getVectorsBefore()
				.get(channelName).get(waveName).get(statName);
			double[] ar1 = new double[vector1.size()];
			for (int i = 0; i < vector1.size(); i++) {
			    ar1[i] = vector1.get(i);
			}
			Vector<Double> vector2 = popResult.getVectorsAfter()
				.get(channelName).get(waveName).get(statName);
			double[] ar2 = new double[vector2.size()];
			for (int i = 0; i < vector2.size(); i++) {
			    ar2[i] = vector2.get(i);
			}
			LillieforsNormality.compute(ar1, 5, false);
			boolean normal = LillieforsNormality
				.isTrueForAlpha(0.05);
			if (normal == true) {
			    LillieforsNormality.compute(ar2, 5, false);
			    normal = LillieforsNormality.isTrueForAlpha(0.05);
			}
			if (normal == true) {
			    if (populNo == 1) tStudentPairedTest("P1AB", channelName, waveName, statName, ar1, ar2);
			    else if (populNo == 2) tStudentPairedTest("P2AB", channelName, waveName, statName, ar1, ar2);
			} else {
			    if (populNo == 1) wilcoxonTest("P1AB", channelName, waveName, statName, ar1, ar2);
			    else if (populNo == 2) wilcoxonTest("P2AB", channelName, waveName, statName, ar1, ar2);
			}
		    }
		}
	    }
	}
    }

    public void beforeUnpaired() {
	for (String channelName : popul1Result.getVectorsBefore().keySet()) {
	    for (String waveName : popul1Result.getVectorsBefore()
		    .get(channelName).keySet()) {
		for (String statName : popul1Result.getVectorsBefore()
			.get(channelName).get(waveName).keySet()) {
		    if (popul2Result.getVectorsBefore().get(channelName)
			    .get(waveName).get(statName) != null) {
			Vector<Double> vector1 = popul1Result
				.getVectorsBefore().get(channelName)
				.get(waveName).get(statName);
			double[] ar1 = new double[vector1.size()];
			for (int i = 0; i < vector1.size(); i++) {
			    ar1[i] = vector1.get(i);
			}
			Vector<Double> vector2 = popul2Result
				.getVectorsBefore().get(channelName)
				.get(waveName).get(statName);
			double[] ar2 = new double[vector2.size()];
			for (int i = 0; i < vector2.size(); i++) {
			    ar2[i] = vector2.get(i);
			}

			LillieforsNormality.compute(ar1, 5, false);
			boolean normal = LillieforsNormality
				.isTrueForAlpha(0.05);
			if (normal == true) {
			    LillieforsNormality.compute(ar2, 5, false);
			    normal = LillieforsNormality.isTrueForAlpha(0.05);
			}
			if (normal == true) {
			    tStudentUnpairedTest("BB", channelName, waveName, statName, ar1, ar2);
			} else {
			    mannWhitneyUTest("BB", channelName, waveName, statName, ar1, ar2);
			}

		    }
		}
	    }
	}
    }

    public void afterUnpaired() {
	for (String channelName : popul1Result.getVectorsAfter().keySet()) {
	    for (String waveName : popul1Result.getVectorsAfter()
		    .get(channelName).keySet()) {
		for (String statName : popul1Result.getVectorsAfter()
			.get(channelName).get(waveName).keySet()) {
		    if (popul2Result.getVectorsAfter().get(channelName)
			    .get(waveName).get(statName) != null) {
			Vector<Double> vector1 = popul1Result.getVectorsAfter()
				.get(channelName).get(waveName).get(statName);
			double[] ar1 = new double[vector1.size()];
			for (int i = 0; i < vector1.size(); i++) {
			    ar1[i] = vector1.get(i);
			}
			Vector<Double> vector2 = popul2Result.getVectorsAfter()
				.get(channelName).get(waveName).get(statName);
			double[] ar2 = new double[vector2.size()];
			for (int i = 0; i < vector2.size(); i++) {
			    ar2[i] = vector2.get(i);
			}

			LillieforsNormality.compute(ar1, 5, false);
			boolean normal = LillieforsNormality
				.isTrueForAlpha(0.05);
			if (normal == true) {
			    LillieforsNormality.compute(ar2, 5, false);
			    normal = LillieforsNormality.isTrueForAlpha(0.05);
			}
			if (normal == true) {
			    tStudentUnpairedTest("AA", channelName, waveName, statName, ar1, ar2);
			} else {
			    mannWhitneyUTest("AA", channelName, waveName, statName, ar1, ar2);
			}

		    }
		}
	    }
	}
    }

    public void afterBeforeDeffierences() {
	for (String channelName : popul1Result.getVectorsBefore().keySet()) {
	    for (String waveName : popul1Result.getVectorsBefore()
		    .get(channelName).keySet()) {
		for (String statName : popul1Result.getVectorsBefore()
			.get(channelName).get(waveName).keySet()) {
		    if (popul1Result.getVectorsAfter().get(channelName)
			    .get(waveName).get(statName) != null) {
			Vector<Double> vector1 = popul1Result
				.getVectorsBefore().get(channelName)
				.get(waveName).get(statName);
			Vector<Double> vector2 = popul1Result.getVectorsAfter()
				.get(channelName).get(waveName).get(statName);
			double[] ar1 = new double[vector1.size()];
			for (int i = 0; i < vector1.size(); i++) {
			    ar1[i] = vector2.get(i) - vector1.get(i);
			}

			for (String channelName2 : popul2Result
				.getVectorsBefore().keySet()) {
			    for (String waveName2 : popul2Result
				    .getVectorsBefore().get(channelName2)
				    .keySet()) {
				for (String statName2 : popul2Result
					.getVectorsBefore().get(channelName2)
					.get(waveName2).keySet()) {
				    if (popul2Result.getVectorsAfter()
					    .get(channelName2).get(waveName2)
					    .get(statName2) != null) {
					Vector<Double> vector1P2 = popul2Result
						.getVectorsBefore()
						.get(channelName2)
						.get(waveName2).get(statName2);
					Vector<Double> vector2P2 = popul2Result
						.getVectorsAfter()
						.get(channelName2)
						.get(waveName2).get(statName2);
					double[] ar2 = new double[vector1P2
						.size()];
					for (int i = 0; i < vector1P2.size(); i++) {
					    ar2[i] = vector2P2.get(i)
						    - vector1P2.get(i);
					}
					LillieforsNormality.compute(ar1, 5,
						false);
					boolean normal = LillieforsNormality
						.isTrueForAlpha(0.05);
					if (normal == true) {
					    LillieforsNormality.compute(ar2, 5,
						    false);
					    normal = LillieforsNormality
						    .isTrueForAlpha(0.05);
					}
					if (normal == true) {
					    tStudentPairedTest("dAB", channelName, waveName, statName, ar1,
						    ar2);
					} else {
					    wilcoxonTest("dAB", channelName, waveName, statName, ar1, ar2);
					}
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }

    
    //TODO sprawdzic dlugosc wektorów - ustalic rozmiar na krotszy z woch przy zamianie z vectora na tablice doubli
    public void tStudentPairedTest(String testName, String channelName, String waveName, String statName,
	    double[] ar1, double[] ar2) { // t Student dla zaleznych
	TTest test = new TTest();
	Vector<Double> result = new Vector<Double>();
	result.add(1.0d);
	result.add(1.0d);
	double pval = 0.0d;
	pval = test.pairedTTest(ar1, ar2);
	result.add(pval);
	addTestResult(testName, channelName, waveName, statName, result);
    }

    public void tStudentUnpairedTest(String testName, String channelName, String waveName, String statName,
	    double[] ar1, double[] ar2) { // t Student dla niezaleznych
	TTest test = new TTest();
	Vector<Double> result = new Vector<Double>();
	result.add(1.0d);
	result.add(-1.0d);
	double pval = 0.0d;
	pval = test.tTest(ar1, ar2);
	result.add(pval);
	addTestResult(testName, channelName, waveName, statName, result);
    }

    public void wilcoxonTest(String testName, String channelName, String waveName, String statName, double[] ar1,
	    double[] ar2) { // zalezne
	WilcoxonSignedRankTest wilcoxTest = new WilcoxonSignedRankTest();
	Vector<Double> result = new Vector<Double>();
	result.add(1.0d);
	result.add(1.0d);
	double pval = 0.0d;
	if (ar1.length <= 30 && ar2.length <= 30) {
	    pval = wilcoxTest.wilcoxonSignedRankTest(ar1, ar2, true);
	    result.add(pval);
	    addTestResult(testName, channelName, waveName, statName, result);
	} else {
	    pval = wilcoxTest.wilcoxonSignedRankTest(ar1, ar2, false);
	    result.add(pval);
	    addTestResult(testName, channelName, waveName, statName, result);
	}
    }

    public void mannWhitneyUTest(String testName, String channelName, String waveName, String statName,
	    double[] ar1, double[] ar2) { // niezalezne
	MannWhitneyUTest mannWhitneyUTest = new MannWhitneyUTest();
	Vector<Double> result = new Vector<Double>();
	result.add(1.0d);
	result.add(-1.0d);
	double pval = 0.0d;
	pval = mannWhitneyUTest.mannWhitneyUTest(ar1, ar2);
	result.add(pval);
	addTestResult(testName, channelName, waveName, statName, result);
    }

    public PopulationResult getPopul1() {
	return popul1Result;
    }

    public void setPopul1(PopulationResult popul1) {
	this.popul1Result = popul1;
    }

    public PopulationResult getPopul2() {
	return popul2Result;
    }

    public void setPopul2(PopulationResult popul2) {
	this.popul2Result = popul2;
    }

    public double getAlpha() {
	return alpha;
    }

    public void setAlpha(double alpha) {
	this.alpha = alpha;
    }

    public Map<String, Map<String, Map<String, Map<String, Vector<Double>>>>> getTestResult() {
	return testResult;
    }

    public void addTestResult(String name, String channel, String wave,
	    String statistic, Vector<Double> result) {
	if (testResult.containsKey(name) == false) {
	    channels = new HashMap<String, Map<String, Map<String, Vector<Double>>>>();
	    addChannels(channel, wave, statistic, result);
	    testResult.put(name, channels);
	} else {
	    if (testResult.get(name).get(channel) == null) {
		addChannels(channel, wave, statistic, result);
	    } else if (testResult.get(name).get(channel).get(wave) == null) {
		addWaves(wave, statistic, result);
	    } else {
		if (testResult.get(name).get(channel).get(wave).get(statistic) == null) {
		    addStats(statistic, result);
		}
	    }
	}
    }

    public Map<String, Map<String, Map<String, Vector<Double>>>> getChannels() {
	return channels;
    }

    public void addChannels(String channel, String wave, String statistic,
	    Vector<Double> result) {
	if (channels.containsKey(channel) == false) {
	    waves = new HashMap<String, Map<String, Vector<Double>>>();
	    addWaves(wave, statistic, result);
	    channels.put(channel, waves);
	} else {
	    if (channels.get(channel).get(wave) == null) {
		addWaves(wave, statistic, result);
	    } else {
		if (channels.get(channel).get(wave).get(statistic) == null) {
		    addStats(statistic, result);
		}
	    }
	}
    }

    public Map<String, Map<String, Vector<Double>>> getWaves() {
	return waves;
    }

    public void addWaves(String wave, String statistic, Vector<Double> result) {
	if (waves.containsKey(wave) == false) {
	    stats = new HashMap<String, Vector<Double>>();
	    addStats(statistic, result);
	    waves.put(wave, stats);
	} else {
	    if (waves.get(wave).get(statistic) == null) {
		addStats(statistic, result);
	    }
	}
    }

    public Map<String, Vector<Double>> getStats() {
	return stats;
    }

    public void addStats(String statistic, Vector<Double> result) {
	stats.put(statistic, result);
    };

}
