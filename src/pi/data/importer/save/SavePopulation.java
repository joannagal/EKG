package pi.data.importer.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import pi.shared.SharedController;

public class SavePopulation extends JDialog {

	private static final long serialVersionUID = 1L;

	int type;

	public SavePopulation(int type) throws FileNotFoundException,
			UnsupportedEncodingException, XMLStreamException,
			FactoryConfigurationError {

		this.type = type;

		if (type == 1) {
			this.setAlwaysOnTop(true);
			PopSaver ps = new PopSaver(SharedController.getInstance()
					.getProject());
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML (*.xml)", "xml");
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setFileFilter(filter);
			fileChooser.setSelectedFile(new File("*.xml"));
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (!file.getName().endsWith(".xml")) {
					ps.save(file.getAbsolutePath() + ".xml");
					SharedController.getInstance().getProject()
							.setPath(file.getAbsolutePath() + ".xml");
					;
				} else {
					ps.save(file.getAbsolutePath());
					SharedController.getInstance().getProject()
							.setPath(file.getAbsolutePath());
					;
				}
			}
		}
		else if (type == 2){
			String path = SharedController.getInstance().getProject().getPath();
			PopSaver ps = new PopSaver(SharedController.getInstance()
					.getProject());
			ps.save(path);

		}
	}

}