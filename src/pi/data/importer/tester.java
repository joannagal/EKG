package pi.data.importer;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pi.project.Project;

public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//POPULATION (PROJECT)IMPORT
		try {
			PopImporter pi = new PopImporter();
			
			XMLReader p = XMLReaderFactory.createXMLReader();
			p.setContentHandler(pi);
			p.parse("C:\\Users\\nadam_000\\workspace\\ECG-Loader\\population_ex.xml");
			
			Project importedProject = pi.getProject();
		} catch (IOException | SAXException e) {
			System.out.println("Project load failure");
			e.printStackTrace();
		}
	}

}
