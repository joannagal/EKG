package pi.statistics.logic;

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
