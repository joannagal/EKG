package pi.statistics.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class PopulationResult {
    private ArrayList<SpecimenResult> result = new ArrayList<>();
    private Map<String, Map<String, Map<String, Vector<Object>>>> vectorsBefore = new HashMap<String, Map<String, Map<String, Vector<Object>>>>();
    private Map<String, Map<String, Map<String, Vector<Object>>>> vectorsAfter = new HashMap<String, Map<String, Map<String, Vector<Object>>>>();

    public static String[] channelsList = { "I", "II", "III", "V1", "V2", "V3", "V4",
	    "V5", "V6" };
    public static String[] statsList = { "Average", "Max", "Min", "Amplitude",
	    "Variance", "SD" };
    public static String[] wavesList = { "pWave", "tWave", "uWave", "prInterval",
	    "prSegment", "stSegment", "stInterval", "qtInterval",  "qrsComplex", "J-point", "RR_interval"};
    
    public void summary() {
	// TODO
    }

    public void setVectors(
	    Map<String, Map<String, Map<String, Vector<Object>>>> before,
	    Map<String, Map<String, Map<String, Vector<Object>>>> after) {
	VectorsToTests vectorsB = new VectorsToTests();
	VectorsToTests vectorsA = new VectorsToTests();
	for (String channelName : channelsList) {
	    for (String waveName : wavesList) {
		for (String statName :statsList) {
		    try {
			double valueB = (double)before.get(channelName).get(waveName)
				.get(statName).firstElement();
			double valueA = (double)after.get(channelName).get(waveName)
				.get(statName).firstElement();
			vectorsB.addVector(channelName, waveName, statName, valueB);
			vectorsA.addVector(channelName, waveName, statName, valueA);
		    }
		    catch (Exception ex){
			System.out.println("bladdd");
			    vectorsB.addVector(channelName, waveName, statName, 0);
			    vectorsA.addVector(channelName, waveName, statName, 0);
		    }
				
				    
				    

			    
			
		    
		}
	    }
	}
	vectorsB.printVectors();
	setVectorsBefore(vectorsB.getVectors());
	vectorsA.printVectors();
	setVectorsAfter(vectorsA.getVectors());

    }

    public ArrayList<SpecimenResult> getResult() {
	return result;
    }

    public void addResult(SpecimenResult result) {
	this.result.add(result);
    }

    public Map<String, Map<String, Map<String, Vector<Object>>>> getVectorsBefore() {
	return vectorsBefore;
    }

    public void setVectorsBefore(
	    Map<String, Map<String, Map<String, Vector<Object>>>> vectors) {
	this.vectorsBefore = vectors;
    }

    public Map<String, Map<String, Map<String, Vector<Object>>>> getVectorsAfter() {
	return vectorsAfter;
    }

    public void setVectorsAfter(
	    Map<String, Map<String, Map<String, Vector<Object>>>> vectorsAfter) {
	this.vectorsAfter = vectorsAfter;
    }
}
