package pi.statistics.functions;

import pi.statistics.logic.DurationResult;
import pi.statistics.logic.StatisticResult;

public class Duration {
    
    private String name;
    
    public void countDuration(int left, int right, Double interval){
	System.out.println("obliczanie duration");
	double dur = (right - (left+1)) * interval;
	
	DurationResult.addValue(this.getName(), dur);
    }
    
    public String getName(){
	return name;
    }

    public void setName(String waveName){
        this.name = waveName;
    }
    
}
