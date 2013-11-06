package pi.statistics.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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
	    if (name == "pWave"){
		addWaves(cycle.getP_wave(), "P_wave");
	    } else if (name == "tWave"){
		addWaves(cycle.getT_wave(), "T_wave");
	    } else if (name == "uWave"){
		addWaves(cycle.getU_wave(), "U_wave");
	    } else if (name == "prInterval"){
		addWaves(pr_interval, "Pr_interval");
	    } else if (name == "prSegment"){
		addWaves(cycle.getPr_segment(), "Pr_segment");
	    } else if (name == "stSegment"){
		addWaves(cycle.getSt_segment(), "St_segment");
	    } else if (name == "stInterval"){
		addWaves(st_interval, "St_interval");
	    } else if (name == "qtInterval"){
		addWaves(qt_interval, "Qt_interval");
	    } else if (name == "qrsComplex"){
		addWaves(cycle.getQrs_complex(), "Qrs_complex");
	    }   
	}
    }
    
    public void setJPoint(){
	Vector<Double> res = new Vector<Double>();
	res.add((double) cycle.getQrs_complex().getRight());
	StatisticResult.addValue("J-point", res);
    }

    public Map<Range, String> getWaves() {
	return waves;
    }

    public void addWaves(Range key, String name) {
	this.waves.put(key, name);
    }
}
