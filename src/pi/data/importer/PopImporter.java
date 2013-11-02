package pi.data.importer;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.project.Project;
import pi.utilities.Range;

public class PopImporter extends DefaultHandler {

	private Project project;
	private Population popul;
	private ArrayList<Specimen> specimenList;
	private Specimen spec;
	private ECG input;
	private ArrayList<Channel> channelList;
	private Channel channel;
	private LinkedList<Cycle> cyclesList;
	private Cycle cycle;

	private int specimenIndex = 0;
	private int channelIndex = 0;

	boolean rawDataNode = false;
	boolean cycleNode = false;

	@Override
	public void startDocument() {
		System.out.println("Start project import");
	}

	@Override
	public void endDocument() {
		System.out.println("Project imported");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("PROJECT")) {
			initProject(attributes);
		} else if (qName.equalsIgnoreCase("POPUL")) {
			initPopul(attributes);
		} else if (qName.equalsIgnoreCase("SPECIMEN")) {
			initSpecimen(attributes);
		} else if (qName.equalsIgnoreCase("INPUT")) {
			initInput(attributes);
		} else if (qName.equalsIgnoreCase("CHANNEL")) {
			initChannel(attributes);
		} else if (qName.equalsIgnoreCase("RAW_DATA")) {
			rawDataNode = true;
		} else if (qName.equalsIgnoreCase("CYCLES")) {
			initCycles(attributes);
		} else if (qName.equalsIgnoreCase("CYCLE")) {
			cycleNode = true;
			initCycle(attributes);
		}
	}

	@Override
	public void endElement(String uri, String localName,
	// submitPopul();
			String qName) throws SAXException {
		System.out.println("End Element :" + qName);
	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (rawDataNode) {
			getRawData(ch, start, length);
			rawDataNode = false;
		} else if (cycleNode) {
			getCycle(ch, start, length);
			cycleNode = false;
		}
	}

	public void init() {

	}

	public void finalize() {

	}

	public void initProject(Attributes attributes) {
		project = new Project();
		project.setName(attributes.getValue("name"));
		project.setPath(attributes.getValue("path"));
		Date date = new Date();// TODO parse string to date = new
								// Date(attributes.getValue("date"));
		project.setDate(date);
		String type = attributes.getValue("type");
		if (type != "")
			project.setType(Integer.parseInt(type));
	}

	public void initPopul(Attributes attributes) {
		popul = new Population();
		int popId = Integer.parseInt(attributes.getValue("id"));
		String specCount = attributes.getValue("specimens");
		if (specCount != "")
			specimenList = new ArrayList<>(Integer.parseInt(specCount));
		specimenIndex = 0;
		popul.setSpecimen(specimenList);

		if (popId == 1) {
			project.setFirstPopulation(popul);
		} else if (popId == 2) {
			project.setSecondPopulation(popul);
		}
	}

	public void initSpecimen(Attributes attributes) {
		spec = new Specimen();
		spec.setName(attributes.getValue("name"));
		spec.setSurname(attributes.getValue("surname"));
		Date d = new Date();// TODO attributes.getValue("birth_date")
		// spec.setBirth((java.sql.Date) d);

		String age = attributes.getValue("age");
		if (age != "")
			spec.setAge(Integer.parseInt(age));

		String weight = attributes.getValue("weight");
		if (weight != "")
			spec.setWeight(Integer.parseInt(weight));

		String activityDuration = attributes.getValue("activity_duration");
		if (activityDuration != "")
			spec.setActivityDuration(Integer.parseInt(activityDuration));

		String hiv = attributes.getValue("hiv");
		if (hiv != "")
			spec.setHiv(Integer.parseInt(hiv));

		String metadon = attributes.getValue("metadon");
		if (metadon != "")
			spec.setMetadon(Integer.parseInt(metadon));

		String mta = attributes.getValue("metadon_time_application");
		if (mta != "")
			spec.setMetadonTimeApplication(Integer.parseInt(mta));

		String ttgm = attributes.getValue("time_to_good_mood");
		if (ttgm != "")
			spec.setTimeToGoodMood(Integer.parseInt(ttgm));

		String gmd = attributes.getValue("good_mood_duration");
		if (gmd != "")
			spec.setGoodMoodDuration(Integer.parseInt(gmd));

		String in = attributes.getValue("inputs_numer");
		// TODO Jak siê ma inputsNumber do after i before???

		popul.getSpecimen().add(specimenIndex, spec);
		specimenIndex++;
	}

	public void initInput(Attributes attributes) {
		input = new ECG();
		input.setName(attributes.getValue("id"));
		String channels = attributes.getValue("channels");
		if (channels != "")
			channelList = new ArrayList<>(Integer.parseInt(channels));
		channelIndex = 0;
		input.setChannel(channelList);

		if (spec.getBefore() == null) {
			spec.setBefore(input);
		} else if (spec.getAfter() == null) {
			spec.setAfter(input);
		}

		// TODO !!! ZagnieŸdziæ input w Specimenie

	}

	public void initChannel(Attributes attributes) {
		channel = new Channel();
		channel.setName(attributes.getValue("name"));

		String translation = attributes.getValue("translations");
		if (translation != "")
			channel.setTranslation(Double.valueOf(translation));

		channelList.add(channelIndex, channel);
		channelIndex++;

	}

	public void getRawData(char ch[], int start, int length) {
		// TODO Co bêdzie dok³adnie w raw data?
	}

	public void initCycles(Attributes attributes) {
		// TODO Czy przy LinkedList atrybut number nie jest zbêdny?
		// int cycles = Integer.parseInt(attributes.getValue("number"));
		cyclesList = new LinkedList<>();
		channel.setCycle(cyclesList);
	}

	public void initCycle(Attributes attributes) {
		String range = attributes.getValue("range");
		// TODO Czym jest range, left i right? I jak s¹ reprezentowane w xmlu?
		cycle = new Cycle();

		Boolean markered = Boolean
				.parseBoolean(attributes.getValue("markered"));
		cycle.setMarkered(markered);

		cyclesList.add(cycle);
	}

	public void getCycle(char ch[], int start, int length) {
		// TODO parsowanie zawartoœci cyklu
		Cycle current = cyclesList.getLast();

	}
	
	public Project getProject(){
		return project;
	}
}
