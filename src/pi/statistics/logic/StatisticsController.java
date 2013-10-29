package pi.statistics.logic;

import java.util.ArrayList;

import pi.inputs.Input;
import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.inputs.signal.Probe;
import pi.population.Population;
import pi.population.Specimen;

public class StatisticsController {
	private ProjectResult finalResult;
	private ArrayList<Function> functions;
	private Population popul1;
	private Population popul2;
	
	public void loadPopulation(){
	    Population popul1 = null;
	    Population popul2 = null;
	    //TODO wczytanie danych zaimportowanych przez sax (popul1)
	    setPopul1(popul1);
	    //TODO wczytanie danych zaimportowanych przez sax (popul2)
	    setPopul2(popul2);
	}
	
	@SuppressWarnings("static-access")
	private AttributeResult count(Input input){
	    Channel channel = (Channel)input;
	    AttributeResult atrResult = new AttributeResult();
	    StatisticResult statResult = new StatisticResult();
	    for (Cycle cycle : channel.getCycle()){
		if (cycle.getMarkered() == true){
		    int left = cycle.getRange().getLeft();
		    int right = cycle.getRange().getRight();
		    Boolean calculate = false;
		    for (Probe probe : channel.getProbe()){
			if (probe.getNumber() == left) calculate = true;
			if (probe.getNumber() == right) calculate = false;
			if (calculate == true) {
			    statResult.clearValues();
			    for (Function function : functions){
				function.iterate(probe);
			    }
			}
		    }
		    for (Function function : functions){
			function.countResult();
		    }
		    atrResult.addValue(statResult);
		    }
	    }
	    return atrResult;
	}

	public PopulationResult countForPopulation(Population popul){
	    PopulationResult popResult = new PopulationResult();
	    SpecimenResult specResult = new SpecimenResult();
	    for (Specimen man : popul.getSpecimen()){
		Input before = man.getBefore();
		specResult.setBefore(count(before));
		Input after = man.getAfter();
		if (after != null){
		    specResult.setAfter(count(after));
		    //TODO compare();
		}
		popResult.addResult(specResult);
	    }
	    return popResult;
	}
	
	public void countStatistics(ArrayList<Function> functions){
		finalResult = new ProjectResult();
		this.functions = functions;
		loadPopulation();
		finalResult.setPopul1(countForPopulation(popul1));
		
		if (popul2 != null){
			finalResult.setPopul2(countForPopulation(popul2));
			//TODO porównaj populacje?
		}
		
		//TODO zwróæ wynik - jakas metoda w ProjectResult?
	}

	public Population getPopul1() {
	    return popul1;
	}

	public void setPopul1(Population popul1) {
	    this.popul1 = popul1;
	}

	public Population getPopul2() {
	    return popul2;
	}

	public void setPopul2(Population popul2) {
	    this.popul2 = popul2;
	}
	
}
