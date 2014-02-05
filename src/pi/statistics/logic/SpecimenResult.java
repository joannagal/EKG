package pi.statistics.logic;

public class SpecimenResult {
    //private ChannelResult after;
    //private ChannelResult before;
    private EcgResult value;
    // public void compareResult() {
    // System.out.println("START");
    // for (String name : before.getValue().keySet()) {// PO CHANNELACH
    // for (String waveName : before.getValue().get(name).getWavesResult()
    // .keySet()) {// PO WAVE
    // for (String statName : before.getValue().get(name).getWavesResult()
    // .get(waveName).getValue().keySet()) {// PO STATYSTYKACH
    // double varBefore = before.getValue().get(name).getWavesResult()
    // .get(waveName).getValue().get(statName);
    //
    // for (String nameAfter : after.getValue().keySet()) {
    //
    // if (name.equals(nameAfter)) {
    // for (String waveNameAfter : after.getValue()
    // .get(nameAfter).getWavesResult().keySet()) {
    // if (waveName.equals(waveNameAfter)) {
    // for (String statNameAfter : after
    // .getValue().get(nameAfter)
    // .getWavesResult().get(waveNameAfter)
    // .getValue().keySet()) {
    // if (statName.equals(statNameAfter)) {
    //
    //
    // double varAfter = after.getValue()
    // .get(nameAfter).getWavesResult()
    // .get(waveNameAfter)
    // .getValue()
    // .get(statNameAfter);
    //
    // if (varBefore > varAfter) {
    // System.out.println("Przed wiêksze, kanal: " + name + ", wave: " +
    // waveName + ", statystyka: " + statName);
    // // TODO wniosek do raportu
    // } else if (varBefore < varAfter) {
    // System.out.println("Po wiêksze, kanal: " + name + ", wave: " + waveName +
    // ", statystyka: " + statName);
    // // TODO wniosek do raportu
    // } else {
    // System.out.println("Rowne, kanal: " + name + ", wave: " + waveName +
    // ", statystyka: " + statName);
    // // TODO wniosek do raportu
    // }
    // }
    // }
    // }
    // }
    // }
    // }
    // }
    // }
    // }
    // }
    public static String[] attributes = { "Duration", "Amplitude"};
    public static String[] channelsList = { "I", "II", "III", "V1", "V2", "V3",
	    "V4", "V5", "V6" };
    public static String[] statsList = { "Average", "Max", "Min", "Amplitude",
	    "Variance", "SD" };
    public static String[] wavesList = { "pWave", "tWave", "uWave",
	    "prInterval", "prSegment", "stSegment", "stInterval", "qtInterval",
	    "qrsComplex", "J-point", "RR_interval" };

    public void addToVectors(VectorsToTests vectors, ChannelResult result) {

	for (String channelName : channelsList) {
	    for (String atrName : attributes) {
	    for (String waveName : wavesList) {
		for (String statName : statsList) {
		    try {
			double value = result.getValue().get(channelName)
				.getValue().get(atrName).getWavesResult()
				.get(waveName).getValue().get(statName);
			System.out.println(value);
			vectors.addVector(channelName, atrName, waveName,
				statName, value);
		    } catch (Exception ex) {
			vectors.addVector(channelName, atrName, waveName,
				statName, null);
		    }
		}
	    }
	}
	}
    }

    public EcgResult getValue() {
	return value;
    }

    public void setValue(EcgResult value) {
	this.value = value;
    }

//    public ChannelResult getAfter() {
//	return after;
//    }
//
//    public void setAfter(ChannelResult after) {
//	this.after = after;
//    }
//
//    public ChannelResult getBefore() {
//	return before;
//    }
//
//    public void setBefore(ChannelResult before) {
//	this.before = before;
//    }
}
