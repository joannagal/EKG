package pi.statistics.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class PopulationResult {
	private ArrayList<SpecimenResult> result = new ArrayList<>();
	private Map<String, Map<String, Vector<Double>>> vectorsBefore = new HashMap<String, Map<String, Vector<Double>>>();
	private Map<String, Map<String, Vector<Double>>> vectorsAfter = new HashMap<String, Map<String, Vector<Double>>>();
	
	public void summary(){
		//TODO
	}

	public ArrayList<SpecimenResult> getResult() {
		return result;
	}

	public void addResult(SpecimenResult result) {
		this.result.add(result);
	}

	public Map<String, Map<String, Vector<Double>>> getVectorsBefore() {
	    return vectorsBefore;
	}

	public void setVectorsBefore(Map<String, Map<String, Vector<Double>>> vectors) {
	    this.vectorsBefore = vectors;
	}

	public Map<String, Map<String, Vector<Double>>> getVectorsAfter() {
	    return vectorsAfter;
	}

	public void setVectorsAfter(Map<String, Map<String, Vector<Double>>> vectorsAfter) {
	    this.vectorsAfter = vectorsAfter;
	}
}
