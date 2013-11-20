package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import jdistlib.disttest.NormalityTest;

import org.apache.commons.math3.stat.inference.MannWhitneyUTest;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest;

public class ProjectResult {
    private PopulationResult popul1Result;
    private PopulationResult popul2Result;
    private Map<String, Double> testResult = new HashMap<String, Double>();
    private double alpha;
    private double lilieforsP1;
    private double lilieforsP2;

    public void compareResult() {
	// TODO
    }

    public void summarize() { // TODO sprawdzic czy taki jest wlasciwy wniosek?
	for (String name : testResult.keySet()) {
	    if (testResult.get(name) > alpha) {
		System.out
			.println("Wynik dla "
				+ name
				+ ": nie mozna odrzucic hipotezy ze wyniki dla obu populacji sa rowne");
	    } else {
		System.out
			.println("Wynik dla "
				+ name
				+ ": mozna odrzucic hipoteze ze wyniki dla obu populacji sa rowne, zatem wyniki sa rozne");
	    }
	}
    }

    public void performPairedTest() {
	for (String waveName : popul1Result.getVectorsBefore().keySet()) {
	    for (String statName : popul1Result.getVectorsBefore()
		    .get(waveName).keySet()) {
		Vector<Double> vector1 = popul1Result.getVectorsBefore()
			.get(waveName).get(statName);
		double[] ar1 = new double[vector1.size()];
		for (int i = 0; i < vector1.size(); i++) {
		    ar1[i] = vector1.get(i);
		}
		Vector<Double> vector2 = popul2Result.getVectorsBefore()
			.get(waveName).get(statName);
		double[] ar2 = new double[vector2.size()];
		for (int i = 0; i < vector2.size(); i++) {
		    ar2[i] = vector2.get(i);
		}
		lilieforsP1 = NormalityTest
			.kolmogorov_lilliefors_statistic(ar1);
		lilieforsP2 = NormalityTest
			.kolmogorov_lilliefors_statistic(ar1);

		if (lilieforsP1 > 0 && lilieforsP2 > 0) {
		    // TODO wartoœæ potwierdzaj¹ca rozklad normalny dla liliefors'a
		    tStudentPairedTest(waveName, statName, ar1, ar2);
		} else {
		    wilcoxonTest(waveName, statName, ar1, ar2);
		}

	    }
	}
    }

    public void performUnpairedTest() {
	for (String waveName : popul1Result.getVectorsBefore().keySet()) {
	    for (String statName : popul1Result.getVectorsBefore()
		    .get(waveName).keySet()) {
		Vector<Double> vector1 = popul1Result.getVectorsBefore()
			.get(waveName).get(statName);
		double[] ar1 = new double[vector1.size()];
		for (int i = 0; i < vector1.size(); i++) {
		    ar1[i] = vector1.get(i);
		}
		Vector<Double> vector2 = popul2Result.getVectorsBefore()
			.get(waveName).get(statName);
		double[] ar2 = new double[vector2.size()];
		for (int i = 0; i < vector2.size(); i++) {
		    ar2[i] = vector2.get(i);
		}
		lilieforsP1 = NormalityTest
			.kolmogorov_lilliefors_statistic(ar1);
		lilieforsP2 = NormalityTest
			.kolmogorov_lilliefors_statistic(ar1);

		if (lilieforsP1 > 0 && lilieforsP2 > 0) {
		 // TODO wartoœæ potwierdzaj¹ca rozklad normalny dla liliefors'a
		    tStudentUnpairedTest(waveName, statName, ar1, ar2);
		} else {
		    mannWhitneyUTest(waveName, statName, ar1, ar2);
		}

	    }
	}
    }

    public void tStudentPairedTest(String waveName, String statName,
	    double[] ar1, double[] ar2) { // t Student dla zaleznych
	addTestResult(waveName + " " + statName,
		TestUtils.pairedTTest(ar1, ar2));
    }

    public void tStudentUnpairedTest(String waveName, String statName,
	    double[] ar1, double[] ar2) { // t Student dla niezaleznych
	addTestResult(waveName + " " + statName, TestUtils.tTest(ar1, ar2));
    }

    public void wilcoxonTest(String waveName, String statName, double[] ar1,
	    double[] ar2) { // zalezne
	WilcoxonSignedRankTest wilcoxTest = new WilcoxonSignedRankTest();
	if (ar1.length <= 30 && ar2.length <= 30) {
	    addTestResult(waveName + " " + statName,
		    wilcoxTest.wilcoxonSignedRankTest(ar1, ar2, true));
	} else {
	    addTestResult(waveName + " " + statName,
		    wilcoxTest.wilcoxonSignedRankTest(ar1, ar2, false));
	}
    }

    public void mannWhitneyUTest(String waveName, String statName,
	    double[] ar1, double[] ar2) { // niezalezne
	MannWhitneyUTest mannWhitneyUTest = new MannWhitneyUTest();
	addTestResult(waveName + " " + statName,
		mannWhitneyUTest.mannWhitneyUTest(ar1, ar2));
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

    public Map<String, Double> getTestResult() {
	return testResult;
    }

    public void addTestResult(String name, Double result) {
	this.testResult.put(name, result);
    };

}
