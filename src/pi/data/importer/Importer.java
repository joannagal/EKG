package pi.data.importer;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import pi.inputs.signal.Channel;
import pi.inputs.signal.ECG;
import pi.inputs.signal.Probe;
import pi.population.Specimen;

//TODO To test!
public class Importer {

	private Document document;
	private String filePath;
	/**
	 * Constructor. From given file path loads the xml document to import data.
	 * @param filePath
	 * @throws DocumentException
	 */
	public Importer(String filePath) throws DocumentException {
		this.filePath = filePath;
		document = loadDocument(filePath);
		System.out.println("Nowy specimen utorzony");
	}
	
	public String[] getAttributes() throws DocumentException {
		String[] test = new String[4];
		String xPath = "//patient";
		List<?> nodes = document.selectNodes(xPath);

		if (nodes.iterator().hasNext())
		{
			Node node = (Node) nodes.iterator().next();
			test[0] = node.valueOf("@surname");
			test[1] = node.valueOf("@firstName");
			test[2]= node.valueOf("@birthDate");
			test[3]= node.valueOf("@ID");
		}
		return test;	
	}

	/**
	 * Adds next ECGSignals to the returned list and fills each channel list with data
	 * @return
	 * @throws DocumentException
	 */
	public ArrayList<ECG> importSignals() throws DocumentException {

		
		String xPath = "//ekgSignal";
		List<?> nodes = document.selectNodes(xPath);
		int size = nodes.size();
		ArrayList<ECG> vectorOfSignals = new ArrayList<>(size);
		
		int index = 0;
		for (Iterator<?> i = nodes.iterator(); i.hasNext();) {
			Node node = (Node) i.next();
			double interval = 1.0d / Double.parseDouble(node.valueOf("@frequency"));
			ECG ecg = new ECG();
			ecg.setChannel(importWaves(node, interval, ecg));
			ecg.findAll();
			vectorOfSignals.add(index, ecg);
			index++;
		}

		return vectorOfSignals;
	}

	// Metoda zwraca wektor kana³ów dla klasy ECG
	/**
	 * 
	 * @param signal
	 * @return
	 * @throws DocumentException
	 */
	public ArrayList<Channel> importWaves(Node signal, double interval, ECG ecg) throws DocumentException {
		
		String xPath = "./ekgWave";
		List<?> nodes = signal.selectNodes(xPath);
		int size = nodes.size();
		ArrayList<Channel> result = new ArrayList<>(size);

		int index = 0;
		for (Iterator<?> i = nodes.iterator(); i.hasNext();) {
			Node node = (Node) i.next();
			String[] values = node.getText().split("[ /\t/\n]");
			ArrayList<Probe> probes = new ArrayList<>(values.length);
			Channel channel = new Channel();
			channel.setName(node.valueOf("@lead"));

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			
			for (int probeNo = 0; probeNo < values.length; probeNo++) {
				int probeValue = Integer.parseInt(values[probeNo]);
				Probe probe = new Probe(probeNo, probeValue);			
				
				if (probeValue > max) max = probeValue;
				if (probeValue < min) min = probeValue;
				
				probes.add(probeNo, probe);
			}

			channel.setProbe(probes);
			channel.setTranslation(0.0d);
			channel.setInterval(interval);
			channel.setMaxValue(max);
			channel.setMinValue(min);
			channel.setStartAxis(0.0d);
			channel.setScale(0.2d);
			channel.setParent(ecg);
			channel.recalculate();
			result.add(index, channel);
			index++;
		}

		return result;
	}

	private Document loadDocument(String path) throws DocumentException {
		File inputFile = new File(path);
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputFile);
		return document;
	}

}
