package pi.project;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import pi.data.importer.save.PopSaver;

public class SaverThread implements Runnable {

	String path = null;
	Project project;

	public SaverThread(String path, Project project) {
		this.path = path;
		this.project = project;
	}

	public void run() {
		if (path == null)
			path = project.getPath();

		PopSaver ps = new PopSaver(this.project);
		try {
			ps.save(path);
			project.setPath(path);

		} catch (FileNotFoundException | UnsupportedEncodingException
				| XMLStreamException | FactoryConfigurationError e) {

		}
	}
}
