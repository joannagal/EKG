package pi.data.importer.open;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.inputs.signal.ECG;
import pi.inputs.signal.Probe;
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
	private StringBuffer rawDataBuffer;

	private int specimenIndex = 0;
	private int channelIndex = 0;

	boolean rawDataNode = false;
	private Date d;

	@Override
	public void startDocument() {
		System.out.println("Start project import");
	}

	@Override
	public void endDocument() {
		input.findAll();
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
			rawDataBuffer = new StringBuffer();
		} else if (qName.equalsIgnoreCase("CYCLES")) {
			initCycles(attributes);
		} else if (qName.equalsIgnoreCase("CYCLE")) {
			initCycle(attributes);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("RAW_DATA")) {
			rawDataNode = false;
			importRowData();
		}
		// System.out.println("End Element :" + qName);
	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (rawDataNode) {
			getRawData(ch, start, length);

		}
	}

	public void initProject(Attributes attributes) {
		project = new Project();
		project.setName(attributes.getValue("name"));
		project.setPath(attributes.getValue("path"));
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
		setD(new Date());

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
			spec.setMethadone(Integer.parseInt(metadon));

		String mta = attributes.getValue("metadon_time_application");
		if (mta != "")
			spec.setMetadonTimeApplication(Integer.parseInt(mta));

		String ttgm = attributes.getValue("time_to_good_mood");
		if (ttgm != null && ttgm != "")
			spec.setTimeToGoodMood(Integer.parseInt(ttgm));

		String gmd = attributes.getValue("good_mood_duration");
		if (gmd != "")
			spec.setGoodMoodDuration(Integer.parseInt(gmd));

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
	}

	public void initChannel(Attributes attributes) {
		channel = new Channel();
		channel.setName(attributes.getValue("name"));

		String translation = attributes.getValue("translations");
		if (translation != "")
			channel.setTranslation(Double.valueOf(translation));

		String interval = attributes.getValue("interval");
		if ((interval != null) && (interval != ""))
			channel.setInterval(Double.valueOf(interval));

		channel.setTranslation(0.0d);
		channel.setStartAxis(0.0d);
		channel.setScale(0.2d);
		channel.setParent(input);
		channelList.add(channelIndex, channel);
		channelIndex++;

	}

	public void getRawData(char ch[], int start, int length) {
		String temp = new String(ch, start, length);
		rawDataBuffer.append(temp);
	}

	public void importRowData() {
		int max = 0;
		int min = 0;
		String data[] = rawDataBuffer.toString().split(" ");
		ArrayList<Probe> probes = new ArrayList<>(data.length);
		for (int i = 0; i < data.length; i++) {
			try {
				Probe p = new Probe(i, Integer.parseInt(data[i]));
				int probeValue = p.getValue();
				if (probeValue > max)
					max = probeValue;
				if (probeValue < min)
					min = probeValue;
				probes.add(i, p);
			} catch (NumberFormatException nfe) {
				System.out.println(nfe);
				System.out.println("\nprev: " + data[i - 1] + "\ncurr: "
						+ data[i]);
				System.out.println("\n\nROW DATA:\n=================\n"
						+ rawDataBuffer.toString());
			}
		}
		channel.setMaxValue((double) max / 1000.0d);
		channel.setMinValue((double) min / 1000.0d);
		channel.setProbe(probes);
		channel.recalculate();
	}

	public void initCycles(Attributes attributes) {
		cyclesList = new LinkedList<>();
		channel.setCycle(cyclesList);
	}

	public void initCycle(Attributes attributes) {
		cycle = new Cycle();

		// TODO Zast¹piæ try-catch ifami

		if (attributes.getValue("range") != null) {
			String r[] = attributes.getValue("range").split(" ");
			if (r.length >= 2 && r[0].matches("\\d+") && r[1].matches("\\d+")) {
				Range range = new Range(Integer.parseInt(r[0]),
						Integer.parseInt(r[1]));
				cycle.setRange(range);
			} else {
				JOptionPane.showMessageDialog(null, "Wrong format");
			}
		}

		if (attributes.getValue("u_wave") != null) {
			String u[] = attributes.getValue("u_wave").split(" ");
			if (u.length >= 2 && u[0].matches("\\d+") && u[1].matches("\\d+")) {
				Range u_wave = new Range(Integer.parseInt(u[0]),
						Integer.parseInt(u[1]));
				cycle.setU_wave(u_wave);
			}
		}

		if (attributes.getValue("t_wave") != null) {
			String t[] = attributes.getValue("t_wave").split(" ");
			if (t.length >= 2 && t[0].matches("\\d+") && t[1].matches("\\d+")) {
				Range t_wave = new Range(Integer.parseInt(t[0]),
						Integer.parseInt(t[1]));
				cycle.setT_wave(t_wave);
			}
		}

		if (attributes.getValue("pr_segment") != null) {
			String pr[] = attributes.getValue("pr_segment").split(" ");
			if (pr.length >= 2 && pr[0].matches("\\d+")
					&& pr[1].matches("\\d+")) {
				Range pr_seg = new Range(Integer.parseInt(pr[0]),
						Integer.parseInt(pr[1]));
				cycle.setPr_segment(pr_seg);
			}
		}

		if (attributes.getValue("p_wave") != null) {
			String p[] = attributes.getValue("p_wave").split(" ");
			if (p.length >= 2 && p[0].matches("\\d+") && p[1].matches("\\d+")) {
				Range p_wave = new Range(Integer.parseInt(p[0]),
						Integer.parseInt(p[1]));
				cycle.setP_wave(p_wave);
			}
		}

		if (attributes.getValue("qrs_complex") != null) {
			String qrs[] = attributes.getValue("qrs_complex").split(" ");
			if (qrs.length >= 2 && qrs[0].matches("\\d+")
					&& qrs[1].matches("\\d+")) {
				Range qrs_complex = new Range(Integer.parseInt(qrs[0]),
						Integer.parseInt(qrs[1]));
				cycle.setQrs_complex(qrs_complex);
			}
		}
		
		if (attributes.getValue("st_segment") != null) {
			String st[] = attributes.getValue("st_segment").split(" ");
			if (st.length >= 2 && st[0].matches("\\d+")
					&& st[1].matches("\\d+")) {
				Range st_segment = new Range(Integer.parseInt(st[0]),
						Integer.parseInt(st[1]));
				cycle.setSt_segment(st_segment);
			}
		}

		Boolean markered = Boolean
				.parseBoolean(attributes.getValue("markered"));
		cycle.setMarkered(markered);

		cyclesList.add(cycle);
	}

	public Project getProject() {
		return project;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}
}
