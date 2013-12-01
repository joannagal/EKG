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

		int leftRR = 0;
		int rightRR = 0;
		int index = channel.getCycle().indexOf(cycle);
		leftRR = cycle.getR();
		try {
		    Cycle nextCycle = channel.getCycle().get(index + 1);
		    rightRR = nextCycle.getR();
		} catch (Exception e) {
		    rightRR = leftRR;
		}
		Range RR = new Range(leftRR, rightRR);
		// TODO sprawdzic co z markered
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

	    dResult.checkRR();
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
		    if (name.equals("RR_interval")) {
			pulse = 1 / statResult.getValue().get("Average")
				.doubleValue();
			System.out.println("PULS na sekunde");
			System.out.println(pulse);
			System.out.println("PULS na minute");
			System.out.println(pulse * 60);
		    }
		    // PULS I KOREKCJA
		    if (name.equals("Qt_interval")) {
			double QTcB = 0;
			// TODO pierwiastek szescienny?
			double QTcF = 0;
			double QTcR = 0;
			// PODEJSCIE Z POROWNYWANIEM KAZDEJ WARTOSCI
			// for (Double val : dur.getValue().get(name)) {
			// int index = dur.getValue().get(name).indexOf(val);
			// QTcB =
			// val/(Math.sqrt(dur.getValue().get("RR_interval").get(index)));
			// System.out.println("QTcB");
			// System.out.println(QTcB);
			// QTcR = val + 0.154*(1 -
			// dur.getValue().get("RR_interval").get(index));
			// System.out.println("QTcR");
			// System.out.println(QTcR);
			// }

			// PODEJSCIE Z POROWNYWANIEM SREDNICH
			QTcB = statResult.getValue().get("Average")
				.doubleValue()
				/ (Math.sqrt(result.getWavesResult()
					.get("RR_interval").getValue()
					.get("Average").doubleValue()));
			System.out.println("QTcB");
			System.out.println(QTcB);
			QTcF = statResult.getValue().get("Average")
				.doubleValue()
				/ (Math.pow(result.getWavesResult()
					.get("RR_interval").getValue()
					.get("Average").doubleValue(), 1/3));
			System.out.println("QTcF");
			System.out.println(QTcF);
			QTcR = statResult.getValue().get("Average")
				.doubleValue()
				+ 0.154
				* (1 - result.getWavesResult()
					.get("RR_interval").getValue()
					.get("Average").doubleValue());
			System.out.println("QTcR");
			System.out.println(QTcR);
			statResult.addValue("QTcB", QTcB);
			statResult.addValue("QTcF", QTcF);
			statResult.addValue("QTcR", QTcR);
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
		    statResult.printValues(name);
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
