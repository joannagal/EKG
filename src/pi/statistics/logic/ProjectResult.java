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
    private Map<String, Double> testResult = new HashMap<String, Double>();
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
    public void performUnpairedTest(){
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

    public void performPairedTest(int populNo) { // zalezne, roznice miedzy przed
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
			    tStudentPairedTest("3rd type" + populNo + " "
				    + channelName + " " + waveName, statName,
				    ar1, ar2);
			} else {
			    wilcoxonTest("3rd type" + populNo + " "
				    + channelName + " " + waveName, statName,
				    ar1, ar2);
			}
		    }
		}
	    }
	}
    }
    
    public void beforeUnpaired(){
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
			    tStudentUnpairedTest("before " + channelName + " "
				    + waveName, statName, ar1, ar2);
			} else {
			    mannWhitneyUTest("before " + channelName + " "
				    + waveName, statName, ar1, ar2);
			}

		    }
		}
	    }
	}
    }
    
    public void afterUnpaired(){
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
			    tStudentUnpairedTest("after " + channelName + " "
				    + waveName, statName, ar1, ar2);
			} else {
			    mannWhitneyUTest("after " + channelName + " "
				    + waveName, statName, ar1, ar2);
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
					    tStudentPairedTest("po-przed "
						    + channelName + " "
						    + waveName, statName, ar1,
						    ar2);
					} else {
					    wilcoxonTest("po-przed "
						    + channelName + " "
						    + waveName, statName, ar1,
						    ar2);
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

    public void tStudentPairedTest(String waveName, String statName,
	    double[] ar1, double[] ar2) { // t Student dla zaleznych
	TTest test = new TTest();
	double pval = 0.0d;
	pval = test.pairedTTest(ar1, ar2);
	addTestResult(waveName + " " + statName, pval);
    }

    public void tStudentUnpairedTest(String waveName, String statName,
	    double[] ar1, double[] ar2) { // t Student dla niezaleznych
	TTest test = new TTest();
	double pval = 0.0d;
	pval = test.tTest(ar1, ar2);
	addTestResult(waveName + " " + statName, pval);
    }

    public void wilcoxonTest(String waveName, String statName, double[] ar1,
	    double[] ar2) { // zalezne
	WilcoxonSignedRankTest wilcoxTest = new WilcoxonSignedRankTest();
	double pval = 0.0d;
	if (ar1.length <= 30 && ar2.length <= 30) {
	    pval = wilcoxTest.wilcoxonSignedRankTest(ar1, ar2, true);
	    addTestResult(waveName + " " + statName, pval);
	} else {
	    pval = wilcoxTest.wilcoxonSignedRankTest(ar1, ar2, false);
	    addTestResult(waveName + " " + statName, pval);
	}
    }

    public void mannWhitneyUTest(String waveName, String statName,
	    double[] ar1, double[] ar2) { // niezalezne
	MannWhitneyUTest mannWhitneyUTest = new MannWhitneyUTest();
	double pval = 0.0d;
	pval = mannWhitneyUTest.mannWhitneyUTest(ar1, ar2);
	addTestResult(waveName + " " + statName, pval);
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
