
package pi.population;

import java.sql.Date;

import pi.inputs.Input;
import pi.inputs.signal.ECG;


//-------------------------------------------
/*
	KLASA - OSOBNIK
*/
//-------------------------------------------

public class Specimen
{
	
	private String name = "";
	private String surname = "";
	private String birth = null;
	private String id = "";
	
	
	private int age = -1;
	// CZAS TRWALNIA UZALEZNIENIA/UPRAWIANIA SPORTU
	// W LATACH
	private int activityDuration = -1;
	
	// -1 = BRAK, 0 - DODATNI 1 - UJEMNY
	private int hiv = -1;
	
	// DAWKA METADONU W ML
	private int metadon = -1;
	
	// CZAS W MIESIACACH APLIKOWANIA METADONU
	private int metadonTimeApplication = -1;
	
	// CZAS PO JAKIM PACJENT ODCZUWA DOBRE SAMOPOCZUCIE
	// W GODZINACH
	private int timeToGoodMood = -1;
	
	// CZAS TRWANIA DOBREGO SAMOPOCZUCIA
	private int goodMoodDuration = -1;
	
	// -------------------------
	// SYGNAL ECG OSOBNIKA
	private Input before;
	
	// DRUGI SYGNAL AFTER (NP PO LEKACH, ALBO IMPREZIE
	// NIE ZAWSZE OBOWIAZKOWY, ZALEZY OD TEGO KTORY TO PROJEKT
	private Input after;
	
	// TODO NAPISAC LOAD SIGNAL
	public Input loadSignal(String path)
	{
		Input result = new ECG();
		return result;
	}
	public boolean loadBefore(String path)
	{
		this.before = this.loadSignal(path);
		// just dummy signal
		
		if (this.before == null) return false;
		return true;
	}
	
	public boolean loadAfter(String path)
	{
		this.setAfter(this.loadSignal(path));
		// just dummy signal
		
		if (this.before == null) return false;
		return true;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSurname()
	{
		return surname;
	}
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	public String getBirth()
	{
		return birth;
	}
	public void setBirth(String birth)
	{
		this.birth = birth;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getActivityDuration()
	{
		return activityDuration;
	}
	public void setActivityDuration(int activityDuration)
	{
		this.activityDuration = activityDuration;
	}
	public int getHiv()
	{
		return hiv;
	}
	public void setHiv(int hiv)
	{
		this.hiv = hiv;
	}
	public int getMetadon()
	{
		return metadon;
	}
	public void setMetadon(int metadon)
	{
		this.metadon = metadon;
	}
	public int getMetadonTimeApplication()
	{
		return metadonTimeApplication;
	}
	public void setMetadonTimeApplication(int metadonTimeApplication)
	{
		this.metadonTimeApplication = metadonTimeApplication;
	}
	public int getTimeToGoodMood()
	{
		return timeToGoodMood;
	}
	public void setTimeToGoodMood(int timeToGoodMood)
	{
		this.timeToGoodMood = timeToGoodMood;
	}
	public int getGoodMoodDuration()
	{
		return goodMoodDuration;
	}
	public void setGoodMoodDuration(int goodMoodDuration)
	{
		this.goodMoodDuration = goodMoodDuration;
	}
	public Input getAfter()
	{
		return after;
	}
	public void setAfter(Input after)
	{
		this.after = after;
	}
	
	public Input getBefore()
	{
		return before;
	}
	
	public void setBefore(Input before){
		this.before = before;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void setDetails(String[] array){
		this.name = array[0];
		this.surname = array[1];
		this.birth = array[2];
		this.id = array[3];
	}
	
	public void setWeight(int weight) {
		// TODO Auto-generated method stub
		
	}
}
