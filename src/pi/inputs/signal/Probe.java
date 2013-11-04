package pi.inputs.signal;

//-------------------------------------------
/*
	POJEDYNCZA PROBKA W SYGNALE ECG
	
	ZAWIERA:
	
	- NUMER PROBKI, PO KOLEJI 0, 1, 2, 3 ITP
	- WARTOSC PROBKI, DALEM INTA, BO W XML'ACH BYLY INT
	- NORMALIZED, WARTOSC ZNORMALIZOWANA, COKOLWIEK TO OZNACZA 
	  W TYM WYPADKU
	  
	  PEWNIE TRANSFORMACJA WARTOCI PROBKI NA WARTOSCI 0-1 I 
	  LICZAC STATYSTYKI OPIERAMY SIE NA TEJ WARTOSCI
	  
	  LUZ
*/
//-------------------------------------------

public class Probe
{

	private int number;
	private int value;
	private Double normalized;
	
	public Probe(int number, int value)
	{
		if (number < 0)
		{
			throw new IllegalArgumentException();
		}
		
		this.setNumber(number);
		this.setValue(value);
		this.setNormalized(this.normalize());
	}
	
	
	public Probe getCopy()
	{
		Probe result = new Probe(this.number, this.value);
		return result;
	}
	
	// TODO
	private Double normalize()
	{
		return (double) value / 1000.0d;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
		//this.normalize();
	}

	public Double getNormalized()
	{
		return normalized;
	}

	public void setNormalized(Double normalized)
	{
		this.normalized = normalized;
	}
	
}
