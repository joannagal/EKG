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

		super(SharedController.getInstance().getFrame());

		this.type = type;

		if (type == 1) {
			this.setAlwaysOnTop(true);
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML (*.xml)", "xml");
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setFileFilter(filter);
			fileChooser.setSelectedFile(new File("*.xml"));
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String path = fileChooser.getSelectedFile().getAbsolutePath();
				if (!path.endsWith(".xml")) {
					path += ".xml";
				}
				SharedController.getInstance().getProject().setPath(path);
				SaveThread runnable = new SaveThread(path);
				Thread thread = new Thread(runnable);
				thread.start();

			}
		} else if (type == 2) {
			String path = SharedController.getInstance().getProject().getPath();
			SaveThread runnable = new SaveThread(path);
			Thread thread = new Thread(runnable);
			thread.start();

		}
	}

	class SaveThread implements Runnable {

		String path;

		@Override
		public void run() {
			PopSaver ps = new PopSaver(SharedController.getInstance()
					.getProject());
			try {
				ps.save(SharedController.getInstance().getProject().getPath());
			} catch (FileNotFoundException | UnsupportedEncodingException
					| XMLStreamException | FactoryConfigurationError e) {
				e.printStackTrace();
			}
		}

		public SaveThread(String path) {
			this.path = path;
		}

	}
}
