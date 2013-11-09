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
			    .get(waveName).getValue().get(statName)
			    .firstElement();
		    for (String nameAfter : after.getValue().keySet()) {
			for (String waveNameAfter : after.getValue()
				.get(nameAfter).getValue().keySet()) {
			    for (String statNameAfter : after.getValue()
				    .get(nameAfter).getValue()
				    .get(waveNameAfter).getValue().keySet()) {
				double varAfter = after.getValue()
					.get(nameAfter).getValue()
					.get(waveNameAfter).getValue()
					.get(statNameAfter).firstElement();
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
