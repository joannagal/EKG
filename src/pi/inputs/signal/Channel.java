package pi.inputs.signal;

import java.util.ArrayList;
import java.util.LinkedList;

import pi.inputs.signal.autofinder.Parameters;

//-------------------------------------------
/*
 JEDEN CHANNEL Z ECG

 DAJEMY TU DANE Z JEDNEJ ELEKTRODY

 */
//-------------------------------------------

public class Channel {
	// PARENT
	private ECG parent = null;

	// NAZWA CHANNELU
	private String name;

	// TRANSLACJA KANALU W CZASIE
	// DOMYSLNIE 0.0d ALE NA WYKRESIE
	// BEDZIE SOBIE MOZNA PRZESUWAC
	private Double translation = 0.0d;

	// ODLEGLOSC W CZASIE MIEDZY KOLEJNYMI
	// PROBKAMI
	// CZYLI JAK MAMY SYGNAL 50HZ
	// TO OGLEDLOSC JEST ROWNA 1/50s
	private Double interval = 0.0d;

	// POMOCNICZA PIERDOLA DO WYKRESOW
	private Double endTime = 0.0d;

	// HIPOTETYCZNY POZIOM ZAZNACZENIA
	// WSZYSTKICH ZALAMKOW ITP.
	// SPROBOJE OGARNAC W FUNKCJI AUTOMATYCZNIE
	// ODNAJDUJACYCH TE BAJERY
	private Float fill = 0.0f;

	// LISTA PRZEDZIALOW PROBEK, GDZIE ZNAJDUJA SIE MARKERY
	// JAK LISTA JEST EMPTY TO BIEZEMY DANE DO STATY Z LISTY
	// cycle
	// A JAK SA JAKIES WPISY TO BIEZEMY CYKLE DO STATY Z
	// cycleMarker
	// W TYCH CYKLACH MAMY INFORMACJE O PRZEDZIALE (RANGE)
	// NP. p-wave. BIEZEMY Z TAMTAD LEWY I PRAWY KONIEC TEGO PRZEDZIALU
	// A NASTEPNIE SUROWE DANE Z WEKTORA probe OD LEWEGO DO PRAWEGO
	// KONCA

	// LISTA CYKLI
	private LinkedList<Cycle> cycle;

	// SUROWE DANE ZAWIERAJACE PROBKI
	private ArrayList<Probe> probe;

	// MINIMALNA I MAKSYMALNA WARTOSC, USTALANA PRZY WCZYTYWANIU
	private double minValue = 0.0d;
	private double maxValue = 0.0d;

	// TO MOJE PIERDY DO WYKRESOW, NIE WAZNE
	private Double startAxis = 0.0d;
	private Double scale = 0.0d;

	// PARAMETRY AUTOFINDERA
	private Parameters params;

	public Channel() {
		this.cycle = new LinkedList<Cycle>();
	}

	// POWINNO BYC CALLED PO UTWORZENIU WEKTORA PROBE
	public void recalculate() {
		this.endTime = this.translation + this.probe.size() * this.interval;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

		if (this.name.compareTo("I") == 0)
			this.params = Parameters.getIParameters();
		else if (this.name.compareTo("II") == 0)
			this.params = Parameters.getIIParameters();
		else if (this.name.compareTo("III") == 0)
			this.params = Parameters.getIIIParameters();
		else if (this.name.compareTo("V1") == 0)
			this.params = Parameters.getV1Parameters();
		else if (this.name.compareTo("V2") == 0)
			this.params = Parameters.getV2Parameters();
		else if (this.name.compareTo("V3") == 0)
			this.params = Parameters.getV3Parameters();
		else if (this.name.compareTo("V4") == 0)
			this.params = Parameters.getV4Parameters();
		else if (this.name.compareTo("V5") == 0)
			this.params = Parameters.getV5Parameters();
		else if (this.name.compareTo("V6") == 0)
			this.params = Parameters.getV6Parameters();
		else
			this.params = Parameters.getIParameters();
	}

	public Double getInterval() {
		return interval;
	}

	public void setInterval(Double interval) {
		this.interval = interval;
		this.recalculate();
	}

	public Float getFill() {
		return fill;
	}

	public void setFill(Float fill) {
		this.fill = fill;
	}

	public ArrayList<Probe> getProbe() {
		return probe;
	}

	public void setProbe(ArrayList<Probe> probe) {
		this.probe = probe;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public double getRange() {
		return (this.maxValue - this.minValue);
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public Double getTranslation() {
		return translation;
	}

	public void setTranslation(Double translation) {
		this.translation = translation;
		this.recalculate();
	}

	public Double getStartAxis() {
		return startAxis;
	}

	public void setStartAxis(Double startAxis) {
		this.startAxis = startAxis;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public LinkedList<Cycle> getCycle() {
		return cycle;
	}

	public void setCycle(LinkedList<Cycle> cycle) {
		this.cycle = cycle;
	}

	public Double getEndTime() {
		return endTime;
	}

	public void setEndTime(Double endTime) {
		this.endTime = endTime;
	}

	public Parameters getParams() {
		return params;
	}

	public void setParams(Parameters params) {
		this.params = params;
	}

	public ECG getParent() {
		return parent;
	}

	public void setParent(ECG parent) {
		this.parent = parent;
	}

}
