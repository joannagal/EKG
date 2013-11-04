package pi.data.importer;

import java.io.IOException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

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
			
			System.out.println("\nZapis: ");
			
			PopSaver ps = new PopSaver(importedProject);
			ps.save("savedXml.xml");
					
		} catch (IOException | SAXException e) {
			System.out.println("Project load failure");
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("B£¥D ZAPISU");
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("B£¥D ZAPISU");
		}
		
		
		
	}

}
