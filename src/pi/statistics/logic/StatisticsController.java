package pi.statistics.logic;

import java.util.ArrayList;

import pi.inputs.Input;
import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.shared.SharedController;
import pi.statistics.functions.Duration;
import pi.utilities.Range;

public class StatisticsController {
    private ProjectResult finalResult;
    private ArrayList<Function> functions;
    private ArrayList<String> wavesNames;
    private Population popul1;
    private Population popul2;

    public void loadPopulation() {
	Population popul1 = null;
	Population popul2 = null;
	popul1 = SharedController.getInstance().getProject()
		.getFirstPopulation();
	setPopul1(popul1);
	popul2 = SharedController.getInstance().getProject()
		.getSecondPopulation();
	setPopul2(popul2);
    }

    @SuppressWarnings("static-access")
    private ChannelResult count(ECG input) {
	System.out.println("count");
	ECG signal = input;
	ChannelResult channelResult = new ChannelResult();
	Duration duration = new Duration();
	for (Channel channel : signal.getChannel()) {
	    System.out.println("petla po channelach");
	    AttributeResult atrResult = new AttributeResult();
	    StatisticResult statResult = new StatisticResult();
	    DurationResult dResult = new DurationResult();
	    for (Cycle cycle : channel.getCycle()) {
		System.out.println("petla po cycle");
		System.out.println(cycle.getMarkered().toString());

		if (cycle.getMarkered() == false) {
		    dResult.clearValues();
		    Waves waves = new Waves(cycle, wavesNames);
		    waves.setJPoint();
		    System.out.println("jPiont");
		    for (Range wave : waves.getWaves().keySet()) {
			System.out.println("zakres");
			int left = wave.getLeft();
			int right = wave.getRight();
			String waveName = waves.getWaves().get(wave);
			System.out.println(waveName);
			duration.setName(waveName);
			duration.countDuration(left, right,
				channel.getInterval());
		    }
		    atrResult.addValue(dResult);
		}
		System.out.println("add atr result");
	    }

	    WavesResult result = new WavesResult();
	    // StatisticResult stResTEMP = new StatisticResult();
	    for (DurationResult dur : atrResult.getValue()) {
		System.out.println("obliczanie statystyk");
		for (String name : dur.getValue().keySet()) {
		    System.out.println("obliczanie " + name);
		    // statResult.clearValues();
		    for (Function function : functions) {
			System.out.println("obliczanie funkcji");
			function.iterate(dur.getValue().get(name));
			// TODO puls i korekcja
		    }
		    for (Function function : functions) {
			function.countResult();
			System.out.println("obliczanie wyniku funkcji");
		    }
		    result.addValue(name, statResult);
		    System.out.println("dodanie wynikow funkcji");
		}
	    }
	    // TODO czy na wy¿szym poziomie interesuj¹ nas wszystkie d³ugoœci
	    // czy tylko wyniki min, max, avg? tymczasowo tylko wyniki
	    channelResult.addValue(channel.getName(), result);
	}
	return channelResult;
    }

    public PopulationResult countForPopulation(Population popul) {
	System.out.println("count for population");
	PopulationResult popResult = new PopulationResult();
	SpecimenResult specResult = new SpecimenResult();
	VectorsToTests vectorsBefore = new VectorsToTests();
	VectorsToTests vectorsAfter = new VectorsToTests();
	for (Specimen man : popul.getSpecimen()) {
	    ECG before = man.getBefore();
	    System.out.println("pobrane before");
	    specResult.setBefore(count(before));
	    specResult.addToVectors(vectorsBefore, specResult.getBefore());
	    System.out.println("koniec before");
	    ECG after = man.getAfter();
	    System.out.println("pobrane after");
	    if (after != null) {
		System.out.println("count after");
		specResult.setAfter(count(after));
		specResult.compareResult();
		specResult.addToVectors(vectorsAfter, specResult.getAfter());
	    }
	    popResult.addResult(specResult);
	}
	return popResult;
    }

    public void countStatistics(ArrayList<Function> functions,
	    ArrayList<String> wavesNames) {
	setFinalResult(new ProjectResult());
	this.functions = functions;
	this.wavesNames = wavesNames;
	loadPopulation();
	System.out.println("zaladowane populacje");
	getFinalResult().setPopul1(countForPopulation(popul1));
	System.out.println("koniec populacji 1");
	if (popul2 != null) {
	    getFinalResult().setPopul2(countForPopulation(popul2));
	    System.out.println("koniec populacji2");
	    // TODO porównaj populacje?
	}
	System.out.println("raport");
	// TODO generowanie raportu koñcowego - motoda w ProjectResult?
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

    public ProjectResult getFinalResult() {
	return finalResult;
    }

    public void setFinalResult(ProjectResult finalResult) {
	this.finalResult = finalResult;
    }

}
