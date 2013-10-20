package pi.utilities;

import java.util.ArrayList;

//-------------------------------------------
/*
 	TO JEST TAKA KLASA POMOCNICZA, KTORA W 
	WYKRESIE ECG WYKORZYSTUJE. RACZEJ SIE WAM
	NIE PRZYDA. W KAZDYM RAZIE JEST TO KLASA 
	"STAN". MOZNA STAN AKTYWOWAC/DEZAKTYWOWAC,
	NIESIE ON ZE SOBA WARTOSC BIEZACA, PAMIETA WARTOSC
	POPRZEDNIA, A POTEM STWIERDZILEM ZE DODAM SOBIE WEKTOREK 
	DO TRZYMANIA EWENTUALNYCH INNYCH WARTOSCI.
	OGOLNIE SUCHAREK
 */
//-------------------------------------------
public class State
{
	private boolean active;

	private double previous;
	private double actual;
	
	private ArrayList <Double> handling;

	
	public State()
	{
		this.active = false;
		this.actual = 0.0d;
		this.setPrevious(0.0d);
		this.handling = new ArrayList <Double>();
	}
	
	public double getResult()
	{
		return this.actual - this.previous;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public double getActual()
	{
		return actual;
	}
	
	public void setActual(double actual)
	{
		this.actual = actual;
	}

	public double getPrevious()
	{
		return previous;
	}

	public void setPrevious(double previous)
	{
		this.previous = previous;
	}

	public ArrayList <Double> getHandling()
	{
		return handling;
	}

	public void setHandling(ArrayList <Double> handling)
	{
		this.handling = handling;
	}

}
