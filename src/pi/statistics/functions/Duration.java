package pi.statistics.functions;

import java.util.Vector;
import pi.statistics.logic.StatisticResult;

public class Duration {
    
    private String name = "Duration ";
    
    public void countDuration(int left, int right, Double interval){
	Vector<Double> result = new Vector<Double>();
	
	double dur = (right - (left+1)) * interval;
	result.add(dur);
	
	StatisticResult.addValue(this.getName(), result);
    }
    
    public String getName(){
	return name;
    }

    public void setName(String waveName){
        this.name = this.name + waveName;
    }
    
}
