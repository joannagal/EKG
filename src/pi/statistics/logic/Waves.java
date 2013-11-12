package pi.statistics.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pi.inputs.signal.Cycle;
import pi.utilities.Range;

public class Waves {
	private Map<Range, String> waves = new HashMap<Range, String>();
	private Range pr_interval = new Range(0, 0);
	private Range st_interval = new Range(0, 0);
	private Range qt_interval = new Range(0, 0);
	private Cycle cycle;
    
    public Waves(Cycle cycle, ArrayList<String> wavesNames) {
	this.cycle = cycle;

	if (cycle.getP_wave() != null && cycle.getPr_segment() != null) {
	    pr_interval.setRange(cycle.getP_wave().getLeft(), cycle
		    .getPr_segment().getRight());
	}

	if (cycle.getQrs_complex() != null && cycle.getT_wave() != null) {
	    st_interval.setRange(cycle.getQrs_complex().getRight(), cycle
		    .getT_wave().getRight());
	    qt_interval.setRange(cycle.getQrs_complex().getLeft(), cycle
		    .getT_wave().getRight());
	}

	for (String name : wavesNames){
	    if (name == "pWave" && cycle.getP_wave() != null){
		addWaves(cycle.getP_wave(), "P_wave");
	    } else if (name == "tWave" && cycle.getT_wave() != null){
		addWaves(cycle.getT_wave(), "T_wave");
	    } else if (name == "uWave" && cycle.getU_wave() != null){
		addWaves(cycle.getU_wave(), "U_wave");
	    } else if (name == "prInterval" && pr_interval != null){
		addWaves(pr_interval, "Pr_interval");
	    } else if (name == "prSegment" && cycle.getPr_segment() != null){
		addWaves(cycle.getPr_segment(), "Pr_segment");
	    } else if (name == "stSegment" && cycle.getSt_segment() != null){
		addWaves(cycle.getSt_segment(), "St_segment");
	    } else if (name == "stInterval" && st_interval != null){
		addWaves(st_interval, "St_interval");
	    } else if (name == "qtInterval" && qt_interval != null){
		addWaves(qt_interval, "Qt_interval");
	    } else if (name == "qrsComplex" && cycle.getQrs_complex() != null){
		addWaves(cycle.getQrs_complex(), "Qrs_complex");
	    }   
	}
    }
    
    public void setJPoint(DurationResult dResult){
	double res = (double) cycle.getQrs_complex().getRight();
	System.out.println("setting j point");
	dResult.addValue("J-point", res);
    }

    public Map<Range, String> getWaves() {
	return waves;
    }

    public void addWaves(Range key, String name) {
	this.waves.put(key, name);
    }
}
