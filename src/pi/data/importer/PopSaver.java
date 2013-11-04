package pi.data.importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.project.Project;

public class PopSaver {

	private Project project;
	// private String path;
	private XMLStreamWriter out;

	public PopSaver(Project project) {
		this.project = project;
	}

	public void save(String path) throws FileNotFoundException,
			UnsupportedEncodingException, XMLStreamException,
			FactoryConfigurationError {
		OutputStream outStream = new FileOutputStream(new File(path));
		out = XMLOutputFactory.newInstance().createXMLStreamWriter(
				new OutputStreamWriter(outStream, "utf-8"));

		out.writeStartDocument();
		out.writeStartElement("PROJECT");
		// TODO atrybuty projektu...

		if (project.getFirstPopulation() != null) {
			savePopul(project.getFirstPopulation());
		}

		if (project.getSecondPopulation() != null) {
			savePopul(project.getSecondPopulation());
		}

		out.writeEndElement();
		out.writeEndDocument();
		
		out.close();
	}

	private void savePopul(Population popul) throws XMLStreamException {
		out.writeStartElement("POPUL");
		// TODO atrybuty populacji

		for (Specimen s : popul.getSpecimen()) {
			saveSpecimen(s);
		}

		out.writeEndElement();
	}

	private void saveSpecimen(Specimen s) throws XMLStreamException {
		out.writeStartElement("SPECIMEN");
		// TODO atrybuty

		if (s.getBefore() != null) {
			saveECG((ECG) s.getBefore());
		}
		
		if (s.getAfter() != null) {
			saveECG((ECG) s.getAfter());
		}

		out.writeEndElement();
	}

	private void saveECG(ECG ecg) throws XMLStreamException {
		out.writeStartElement("INPUT");
		//TODO atrybuty
		
		for(Channel ch : ecg.getChannel()){
			saveChannel(ch);
		}
		
		out.writeEndElement();
		
	}

	private void saveChannel(Channel ch) throws XMLStreamException {
		out.writeStartElement("CHANNEL");
		//TODO atrybuty 
		
		out.writeStartElement("RAW_DATA");
		//TODO raw data
		out.writeEndElement();
		
		out.writeStartElement("CYCLES");
		for(Cycle c : ch.getCycle()){
			saveCycle(c);
		}
		
		out.writeEndElement();
		
		out.writeEndElement();
	}

	private void saveCycle(Cycle c) throws XMLStreamException {
		out.writeStartElement("CYCLE");
		//TODO atrybuty
		out.writeCharacters("dane");
		//TODO dane!
		
		out.writeEndElement();
	}
	
	
}
