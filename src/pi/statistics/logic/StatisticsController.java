package pi.statistics.logic;

import java.util.ArrayList;

import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.inputs.signal.ECG;
import pi.inputs.signal.Probe;
import pi.population.Population;
import pi.population.Specimen;
import pi.shared.SharedController;
import pi.statistics.functions.Collector;
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
	    if (!channel.getName().equals("STATUS")) {
		DurationResult dResult = new DurationResult();
		AmplitudeResult aResult = new AmplitudeResult();
		ArrayList<DurationResult> durArray = new ArrayList<DurationResult>();
		ArrayList<AmplitudeResult> ampArray = new ArrayList<AmplitudeResult>();
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
			boolean flag = false;
			int max = -9999;
			int min = 9999;
			for (Probe probe : channel.getProbe()){
			    if (probe.getNumber() == left) flag = true;
			    if (probe.getNumber() == right) flag = false;
			    if (flag == true) {
				if (probe.getValue() > max) max = probe.getValue();
				if (probe.getValue() < min) min = probe.getValue();
			    }
			}
			double amplitude = max - min;
			aResult.addValue(waveName, amplitude);
		    }

		}

		dResult.checkRR();
		durArray.add(dResult);
		ampArray.add(aResult);
		
		AttributeResult atrResult = new AttributeResult();

		WavesResult resultD = new WavesResult();
		
		for (DurationResult dur : durArray) {
		    Collector collector = new Collector();
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
			    if (statResult.getValue().get("Average") != null) {
				double avg = statResult.getValue()
					.get("Average").doubleValue();

				pulse = 1 / avg;

				statResult.addValue("Pulse(s)", pulse);
				statResult.addValue("Pulse(min)", pulse * 60);
			    }
			}
			// PULS I KOREKCJA
			if (name.equals("qtInterval")) {
			    double QTcB = 0;
			    double QTcF = 0;
			    double QTcR = 0;

			    // PODEJSCIE Z POROWNYWANIEM SREDNICH
			    if (resultD.getWavesResult().get("RR_interval")
				    .getValue().get("Average") != null) {
				double avg = resultD.getWavesResult()
					.get("RR_interval").getValue()
					.get("Average").doubleValue();

				QTcB = statResult.getValue().get("Average")
					.doubleValue()
					/ (Math.sqrt(avg));

				QTcF = statResult.getValue().get("Average")
					.doubleValue()
					/ (Math.pow(avg, 1 / 3));

				QTcR = statResult.getValue().get("Average")
					.doubleValue()
					+ 0.154 * (1 - avg);
			    }

			    statResult.addValue("QTcB", QTcB);
			    statResult.addValue("QTcF", QTcF);
			    statResult.addValue("QTcR", QTcR);
			}

			for (Double number : dur.getValue().get(name)) {
			    collector.iterate(number);
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
			ArrayList<Double> tmp = new ArrayList<Double>(
				collector.getResult());
			collector.backToBegin();
			resultD.addCollector(name, tmp);
			for (Function function : functions) {
			    function.backToBegin();
			}
			resultD.addValue(name, statResult);
		    }
		}
		atrResult.addValue("Duration", resultD);
		WavesResult resultA = new WavesResult();
		for (AmplitudeResult amp : ampArray){
		    Collector collector = new Collector();
		    for (String name : amp.getValue().keySet()) {
			StatisticResult statResult = new StatisticResult();
			statResult.clearValues();

			for (Double number : amp.getValue().get(name)) {
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
			for (Double number : amp.getValue().get(name)) {
			    collector.iterate(number);
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
			ArrayList<Double> tmp = new ArrayList<Double>(
				collector.getResult());
			collector.backToBegin();
			resultA.addCollector(name, tmp);
			for (Function function : functions) {
			    function.backToBegin();
			}
			resultA.addValue(name, statResult);
		    }
		}
		atrResult.addValue("Amplitude", resultA);
		channelResult.addValue(channel.getName(), atrResult);
	    }
	}
	return channelResult;
    }

    public PopulationResult countForPopulation(Population popul) {
	PopulationResult popResult = new PopulationResult();
	SpecimenResult specResult = new SpecimenResult();
	VectorsToTests vectorsBefore = new VectorsToTests();
	VectorsToTests vectorsAfter = new VectorsToTests();
	vectorsBefore.clearVectors();
	vectorsAfter.clearVectors();
	if (specimenId == null) {
	    for (Specimen man : popul.getSpecimen()) {
		ECG before = man.getBefore();
		specResult.setBefore(count(before));
		specResult.addToVectors(vectorsBefore, specResult.getBefore());
		ECG after = man.getAfter();
		if (after != null) {
		    specResult.setAfter(count(after));
		    // specResult.compareResult();
		    specResult
			    .addToVectors(vectorsAfter, specResult.getAfter());
		}
		popResult.addResult(specResult);
		man.setStatisticResults(specResult);
	    }
	} else {
	    for (Specimen man : popul.getSpecimen()) {
		if (man.getId() == specimenId) {
		    ECG before = man.getBefore();
		    specResult.setBefore(count(before));
		    specResult.addToVectors(vectorsBefore,
			    specResult.getBefore());
		    ECG after = man.getAfter();
		    if (after != null) {
			specResult.setAfter(count(after));
			// specResult.compareResult();
			specResult.addToVectors(vectorsAfter,
				specResult.getAfter());
		    }
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
	    ArrayList<String> wavesNames, String id) {
	this.specimenId = id;
	setFinalResult(new ProjectResult());
	this.functions = functions;
	this.wavesNames = wavesNames;
	loadPopulation();
	getFinalResult().setPopul1(countForPopulation(popul1));
	if (popul2 != null) {
	    getFinalResult().setPopul2(countForPopulation(popul2));
	    if (SharedController.getInstance().getProject().getType() == 3
		    && specimenId == null) {
		getFinalResult().performUnpairedTest();
	    } else if (SharedController.getInstance().getProject().getType() == 4
		    && specimenId == null) {
		getFinalResult().performPairedTest(1);
		getFinalResult().performPairedTest(2);
		getFinalResult().performDifferencesTest();
	    }
	}
	SharedController.getInstance().setProjectRes(getFinalResult());

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
