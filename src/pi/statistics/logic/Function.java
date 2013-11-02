package pi.statistics.logic;

import pi.inputs.signal.Probe;

public abstract class Function {
	//TODO typ zwracany
	private String name;
	
	public Function(String name){
		this.setName(name);
	}
	
	public abstract void countResult();	
	
	public abstract void iterate(Probe probe);
	
	public abstract void setWaveName(String waveName);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
	    this.name = this.name + " " + name;
	}
	
}
