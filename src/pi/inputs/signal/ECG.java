package pi.inputs.signal;

import pi.inputs.Input;

import java.util.ArrayList;
import java.util.Date;

//-------------------------------------------
/*
	KLASA SYGNALU ECG
*/
//-------------------------------------------

public class ECG implements Input
{
	// TE DWA PONIZEJ MOZNA WYWALIC
	private Date date;
	private String name;
	
	// WEKTOR KANALOW (ONE TRZYMAJA GLOWNE DANE)
	// TE KANALY TA SA DANE Z POSZCZEGOLNYCH ELEKTROD
	// PRZY WCZYTYWANIU DANYCH Z KOLEJNYCH ELEKTROD
	// DAJEMY WLASNIE TUTAJ
	private ArrayList <Channel> channel;
	
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public ArrayList <Channel> getChannel()
	{
		return channel;
	}
	
	public void setChannel(ArrayList <Channel> channel)
	{
		this.channel = channel;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
}
