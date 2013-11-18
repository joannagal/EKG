package pi.statistics.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.math3.stat.inference.TestUtils;

public class ProjectResult {
	private PopulationResult popul1Result;
	private PopulationResult popul2Result;
	private Map<String, Double> tTestResult = new HashMap<String, Double>();
	private Map<String, Boolean> tTestWithAlphaResult = new HashMap<String, Boolean>();
	private double alpha;
	
	public void compareResult(){
		//TODO
	}
	
	public void tStudentTest(){

	    for (String waveName : popul1Result.getVectorsBefore().keySet()){
		for (String statName : popul1Result.getVectorsBefore().get(waveName).keySet()){
		    Vector<Double> vector1 = popul1Result.getVectorsBefore().get(waveName).get(statName);
		    double[] ar1 = new double[vector1.size()];
		    for (int i = 0; i < vector1.size(); i++){
			ar1[i] = vector1.get(i);
		    }
		    Vector<Double> vector2 = popul2Result.getVectorsBefore().get(waveName).get(statName);
		    double[] ar2 = new double[vector2.size()];
		    for (int i = 0; i < vector2.size(); i++){
			ar2[i] = vector2.get(i);
		    }
		    addtTestResult(waveName + " " + statName, TestUtils.pairedTTest(ar1, ar2));
		    addtTestWithAlphaResult(waveName + " " + statName, TestUtils.pairedTTest(ar1, ar2, alpha));
		    
		}
	    }
	    
	}
	
	public void wilcoxonTest(){
	    //TODO
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

	public Map<String, Double> gettTestResult() {
	    return tTestResult;
	}

	public void addtTestResult(String name, Double result) {
	    this.tTestResult.put(name, result);
	}

	public Map<String, Boolean> gettTestWithAlphaResult() {
	    return tTestWithAlphaResult;
	}

	public void addtTestWithAlphaResult(String name, Boolean result) {
	    this.tTestWithAlphaResult.put(name, result);
	}

	public double getAlpha() {
	    return alpha;
	}

	public void setAlpha(double alpha) {
	    this.alpha = alpha;
	};
	
	 
}
