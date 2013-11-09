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
    private ChannelResult count(Input input) {
	ECG signal = (ECG) input;
	ChannelResult channelResult = new ChannelResult();
	Duration duration = new Duration();
	for (Channel channel : signal.getChannel()) {
	    AttributeResult atrResult = new AttributeResult();
	    StatisticResult statResult = new StatisticResult();
	    for (Cycle cycle : channel.getCycle()) {
		if (cycle.getMarkered() == true) {
		    Waves waves = new Waves(cycle, wavesNames);

		    waves.setJPoint();

		    for (Range wave : waves.getWaves().keySet()) {
			int left = wave.getLeft();
			int right = wave.getRight();
			String waveName = waves.getWaves().get(wave);
			duration.setName(waveName);
			duration.countDuration(left, right,
				channel.getInterval());
		    }
		}
		atrResult.addValue(statResult);
	    }

	    WavesResult result = new WavesResult();

	    for (StatisticResult stat : atrResult.getValue()) {
		for (String name : stat.getValue().keySet()) {
		    statResult.clearValues();
		    for (Function function : functions) {
			function.iterate(stat.getValue().get(name)
				.firstElement());
			// TODO puls i korekcja
		    }
		    for (Function function : functions) {
			function.countResult();
		    }
		    result.addValue(name, statResult);
		}
	    }
	    // TODO czy na wy�szym poziomie interesuj� nas wszystkie d�ugo�ci
	    // czy tylko wyniki min, max, avg? tymczasowo tylko wyniki
	    channelResult.addValue(channel.getName(), result);
	}
	return channelResult;
    }

    public PopulationResult countForPopulation(Population popul) {
	PopulationResult popResult = new PopulationResult();
	SpecimenResult specResult = new SpecimenResult();
	for (Specimen man : popul.getSpecimen()) {
	    Input before = man.getBefore();
	    specResult.setBefore(count(before));
	    Input after = man.getAfter();
	    if (after != null) {
		specResult.setAfter(count(after));
		specResult.compareResult();
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
	getFinalResult().setPopul1(countForPopulation(popul1));

	if (popul2 != null) {
	    getFinalResult().setPopul2(countForPopulation(popul2));
	    // TODO por�wnaj populacje?
	}

	// TODO generowanie raportu ko�cowego - motoda w ProjectResult?
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
