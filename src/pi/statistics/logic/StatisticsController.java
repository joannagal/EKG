package pi.statistics.logic;

import java.util.ArrayList;

import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.inputs.signal.ECG;
import pi.inputs.signal.Probe;
import pi.population.Population;
import pi.population.Specimen;
import pi.shared.SharedController;
import pi.statistics.functions.Duration;
import pi.statistics.functions.Variance;
import pi.utilities.Range;

public class StatisticsController {
    private ProjectResult finalResult;
    private ArrayList<Function> functions;
    private ArrayList<String> wavesNames;
    private Population popul1;
    private Population popul2;
    private String specimenId;
    private double pulse;

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

    private ChannelResult count(ECG input) {
	ECG signal = input;
	ChannelResult channelResult = new ChannelResult();
	channelResult.clearValues();
	Duration duration = new Duration();
	for (Channel channel : signal.getChannel()) {
	    AttributeResult atrResult = new AttributeResult();
	    DurationResult dResult = new DurationResult();
	    dResult.clearValues();
	    for (Cycle cycle : channel.getCycle()) {
		//TODO RR
		int leftRR = 0;
		int rightRR = 0;
		int index = channel.getCycle().indexOf(cycle);
		//leftRR = cycle.getR();
		Cycle nextCycle = channel.getCycle().get(index + 1);
		//rightRR = nextCycle.getR();
		Range RR = new Range(leftRR, rightRR);
		if (cycle.getMarkered() == false) {
		    Waves waves = new Waves(cycle, wavesNames);
		    waves.addWaves(RR, "RR_interval");
		    waves.setJPoint(dResult);
		    for (Range wave : waves.getWaves().keySet()) {
			int left = wave.getLeft();
			int right = wave.getRight();
			String waveName = waves.getWaves().get(wave);
			duration.setName(waveName);
			duration.countDuration(left, right,
				channel.getInterval(), dResult);
		    }

		}
	    }
	    atrResult.addValue(dResult);
	    for (DurationResult dur : atrResult.getValue()) {
		dur.printDurations();
	    }

	    WavesResult result = new WavesResult();
	    for (DurationResult dur : atrResult.getValue()) {
		System.out.println("obliczanie statystyk");
		for (String name : dur.getValue().keySet()) {
		    StatisticResult statResult = new StatisticResult();
		    statResult.clearValues();
		     //PULS I KOREKCJA
		     if (name.equals("Qt_interval")) {
		     double QTc = 0;
		     //TODO sprawdzic puls (wzorki z RR?)
		     pulse = SharedController.getInstance().getPulse()*100;
		     for (Double val : dur.getValue().get(name)) {
		     QTc = val + (1.75 * (pulse - 60));
		     }
		     statResult.addValue("QTc", QTc);
		     }

		    for (Double number : dur.getValue().get(name)) {
			for (Function function : functions) {
			    if (function.getName() != "Variance"
				    && function.getName() != "SD") {
				function.iterate(number);
			    }
			}
			for (Function function : functions) {
			    if (function.getName() != "Variance"
				    && function.getName() != "SD") {
				function.countResult(statResult);
			    }
			}
		    }
		    for (Double number : dur.getValue().get(name)) {
			for (Function function : functions) {
			    if (function.getName() == "Variance") {
				Variance func = (Variance) function;
				func.setAverage(statResult);
				func.iterate(number);
			    }
			}
			for (Function function : functions) {
			    if (function.getName() == "Variance") {
				function.countResult(statResult);
			    }
			}
			for (Function function : functions) {
			    if (function.getName() == "SD") {
				function.iterate(number);
			    }
			}
			for (Function function : functions) {
			    if (function.getName() == "SD") {
				function.countResult(statResult);
			    }
			}
		    }
		    for (Function function : functions) {
			function.backToBegin();
		    }
		    // statResult.printValues(name);
		    result.addValue(name, statResult);

		}
	    }
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
	specResult.clear();
	vectorsBefore.clearVectors();
	vectorsAfter.clearVectors();
	if (specimenId == null) {
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
		    System.out.println("porownywarka");
		    specResult.compareResult();
		    System.out.println("koniec porownan");
		    specResult
			    .addToVectors(vectorsAfter, specResult.getAfter());
		}
		System.out.println("Wektory przed:");
		vectorsBefore.printVectors();
		System.out.println("Wektory po:");
		vectorsAfter.printVectors();
		popResult.addResult(specResult);
		man.setStatisticResults(specResult);
	    }
	} else {
	    for (Specimen man : popul.getSpecimen()) {
		if (man.getId() == specimenId) {
		    ECG before = man.getBefore();
		    System.out.println("pobrane before");
		    specResult.setBefore(count(before));
		    specResult.addToVectors(vectorsBefore,
			    specResult.getBefore());
		    System.out.println("koniec before");
		    ECG after = man.getAfter();
		    System.out.println("pobrane after");
		    if (after != null) {
			System.out.println("count after");
			specResult.setAfter(count(after));
			System.out.println("porownywarka");
			specResult.compareResult();
			System.out.println("koniec porownan");
			specResult.addToVectors(vectorsAfter,
				specResult.getAfter());
		    }
		    System.out.println("Wektory przed:");
		    vectorsBefore.printVectors();
		    System.out.println("Wektory po:");
		    vectorsAfter.printVectors();
		    popResult.addResult(specResult);
		    man.setStatisticResults(specResult);
		}
	    }
	}
	popResult.setVectorsBefore(vectorsBefore.getVectors());
	popResult.setVectorsAfter(vectorsAfter.getVectors());
	return popResult;
    }

    public void countStatistics(ArrayList<Function> functions,
	    ArrayList<String> wavesNames, double alpha, String id) {
	this.specimenId = id;
	setFinalResult(new ProjectResult());
	getFinalResult().setAlpha(alpha);
	this.functions = functions;
	this.wavesNames = wavesNames;
	loadPopulation();
	System.out.println("zaladowane populacje");
	getFinalResult().setPopul1(countForPopulation(popul1));
	if (SharedController.getInstance().getProject().getType() == 3) {
	    System.out.println("niezalezne (3)");
	    getFinalResult().perform3TypeTest(1);
	}
	System.out.println("koniec populacji 1");
	if (popul2 != null) {
	    getFinalResult().setPopul2(countForPopulation(popul2));
	    // TODO SPRAWDZIC TESTY!!
	    if (SharedController.getInstance().getProject().getType() == 3) {
		System.out.println("niezalezne (3)");
		getFinalResult().perform3TypeTest(2);
	    } else if (SharedController.getInstance().getProject().getType() == 4) {
		System.out.println("zalezne (4)");
		getFinalResult().perform3TypeTest(1);
		getFinalResult().perform3TypeTest(2);
		getFinalResult().perform4TypeTest();
	    }
	    getFinalResult().summarize();
	    System.out.println("koniec populacji2");
	}
	System.out.println("raport");
	// TODO generowanie raportu koñcowego
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
