package pi.statistics.logic;

public class SpecimenResult {

	private EcgResult value;

	public static String[] attributes = { "Duration", "Amplitude" };
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

}
