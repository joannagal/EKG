package pi.statistics.logic;

import java.util.ArrayList;

public class PopulationResult {
	private ArrayList<SpecimenResult> result = new ArrayList<>();
	
	public void summary(){
		//TODO
	}

	public ArrayList<SpecimenResult> getResult() {
		return result;
	}

	public void addResult(SpecimenResult result) {
		this.result.add(result);
	}
}
