package pi.project;

import java.util.LinkedList;

import pi.analyze.AnalysisResult;
import pi.analyze.Analyzer;
import pi.analyze.ECG.ECGAnalyzer;
import pi.population.Population;

public class Project {
	private String name;
	private String path;
	private java.util.Date date;
	private Population firstPopulation;
	private Population secondPopulation;
	private Analyzer analyzer;
	private LinkedList<AnalysisResult> results;
	private int type;
	public static final int SPECIMEN_SINGLE = 1;
	public static final int SPECIMEN_PAIR = 2;
	public static final int POPULATION_SINGLE = 3;
	public static final int POPULATION_PAIR = 4;

	public Project() {
		setAnalyzer(new ECGAnalyzer());
	}

	public boolean save(String path) {
		SaverThread runnable = new SaverThread(path, this);
		Thread thread = new Thread(runnable);
		thread.start();

		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date2) {
		this.date = date2;
	}

	public Population getFirstPopulation() {
		return firstPopulation;
	}

	public void setFirstPopulation(Population firstPopulation) {
		this.firstPopulation = firstPopulation;
	}

	public Population getSecondPopulation() {
		return secondPopulation;
	}

	public void setSecondPopulation(Population secondPopulation) {
		this.secondPopulation = secondPopulation;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public LinkedList<AnalysisResult> getResults() {
		return results;
	}

	public void setResults(LinkedList<AnalysisResult> results) {
		this.results = results;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
