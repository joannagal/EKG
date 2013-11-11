package pi.statistics.logic;

public class SpecimenResult {
    private ChannelResult after;
    private ChannelResult before;

    @SuppressWarnings("static-access")
    public void compareResult() {
	for (String name : before.getValue().keySet()) {// PO CHANNELACH
	    for (String waveName : before.getValue().get(name).getValue()
		    .keySet()) {// PO WAVE
		for (String statName : before.getValue().get(name).getValue()
			.get(waveName).getValue().keySet()) {// PO STATYSTYKACH
		    double varBefore = before.getValue().get(name).getValue()
			    .get(waveName).getValue().get(statName);
		    for (String nameAfter : after.getValue().keySet()) {
			if (nameAfter == name) {
			    for (String waveNameAfter : after.getValue()
				    .get(nameAfter).getValue().keySet()) {
				if (waveNameAfter == waveName) {
				    for (String statNameAfter : after
					    .getValue().get(nameAfter)
					    .getValue().get(waveNameAfter)
					    .getValue().keySet()) {
					if (statNameAfter == statName) {
					    double varAfter = after.getValue()
						    .get(nameAfter).getValue()
						    .get(waveNameAfter)
						    .getValue()
						    .get(statNameAfter);
					    if (varBefore > varAfter) {
						// TODO wniosek do raportu
					    } else if (varBefore < varAfter) {
						// TODO wniosek do raportu
					    } else {
						// TODO wniosek do raportu
					    }
					}
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }
    
    @SuppressWarnings("static-access")
    public void addToVectors(VectorsToTests vectors, ChannelResult result){
	for (String name : result.getValue().keySet()) {// PO CHANNELACH
	    for (String waveName : result.getValue().get(name).getValue()
		    .keySet()) {// PO WAVE
		for (String statName : result.getValue().get(name).getValue()
			.get(waveName).getValue().keySet()) {// PO STATYSTYKACH
		    double value = result.getValue().get(name).getValue()
			    .get(waveName).getValue().get(statName);
		    vectors.addVector(waveName, statName, value);
		}
	    }
	}
    }
    
    public ChannelResult getAfter() {
	return after;
    }

    public void setAfter(ChannelResult after) {
	this.after = after;
    }

    public ChannelResult getBefore() {
	return before;
    }

    public void setBefore(ChannelResult before) {
	this.before = before;
    }
}
