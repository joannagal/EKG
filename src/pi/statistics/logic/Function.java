package pi.statistics.logic;

import pi.inputs.signal.Probe;

public abstract class Function {
	//TODO typ zwracany
	private final String name;
	
	public Function(String name){
		this.name = name;
	}
	
	public abstract void countResult();	
	
	public abstract void iterate(double value);
	
	public String getName() {
		return name;
	}


	
}
