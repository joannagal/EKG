package pi.shared.schemes;


import pi.shared.schemes.signal.SignalScheme;

//-------------------------------------------
/*
	KLASA ODPOWIEDZIALNA ZA WYGLAD,
	
	NIE TYKAMY, REZYGNUJEMY Z KOLORKOW
*/
//-------------------------------------------

public class Scheme
{
	private SignalScheme signalScheme;
	
	public Scheme()
	{
		signalScheme = new SignalScheme();
	}

	public SignalScheme getSignalScheme()
	{
		return signalScheme;
	}

	public void setSignalScheme(SignalScheme scheme)
	{
		this.signalScheme = scheme;
	}

}
