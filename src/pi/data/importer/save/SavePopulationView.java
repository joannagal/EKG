package pi.data.importer.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import pi.shared.SharedController;

public class SavePopulationView extends JDialog {

	public SavePopulationView() throws FileNotFoundException,
			UnsupportedEncodingException, XMLStreamException,
			FactoryConfigurationError {

		this.setAlwaysOnTop(true);
		PopSaver ps = new PopSaver(SharedController.getInstance().getProject());
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
			} else {
				ps.save(file.getAbsolutePath());
			}
		}
	}

}
