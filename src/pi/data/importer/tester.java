package pi.data.importer;

import java.io.IOException;

import javax.xml.stream.FactoryConfigurationError;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pi.data.importer.open.PopImporter;
import pi.project.Project;

public class tester {

	private static Project importedProject;

	public static void main(String[] args) {

		//POPULATION (PROJECT)IMPORT
		try {
			PopImporter pi = new PopImporter();
			
			XMLReader p = XMLReaderFactory.createXMLReader();
			p.setContentHandler(pi);
			p.parse("population_ex.xml");
			
			setImportedProject(pi.getProject());
			
//			System.out.println("\nZapis: ");
//			
//			PopSaver ps = new PopSaver(importedProject);
//			ps.save("savedXml.xml");
					
		} catch (IOException | SAXException e) {
			System.out.println("Project load failure");
			e.printStackTrace();
//		} catch (XMLStreamException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("B£¥D ZAPISU");
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("B£¥D ZAPISU");
		}
		
		
		
	}

	public static Project getImportedProject() {
		return importedProject;
	}

	public static void setImportedProject(Project importedProject) {
		tester.importedProject = importedProject;
	}

}
