package pi.data.importer.open;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.data.importer.signal.ImportPanel;
import pi.shared.SharedController;

public class OpenPopulationView extends JDialog {

	private String[] actions = new String[] { "OPEN", "CANCEL" };
	private JButton[] buttons;
	private ImportPanel importPanel;
	private GridBagConstraints constraints;
	private String path;

	public OpenPopulationView() {
		// this.setAlwaysOnTop(true);
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.setVisible(true);
		this.setTitle("Open Project...");
		this.setBounds(400, 200, 350, 200);

		/*
		 * setImportPanel(new ImportPanel(new ImporterView()));
		 * constraints.gridx = 0; constraints.gridy = 0; constraints.gridwidth =
		 * 1; this.add(getImportPanel(), constraints);
		 */
		JPanel chooser = new JPanel();
		JLabel fileLabel = new JLabel("Specimen");
		fileLabel.setVisible(true);
		chooser.add(fileLabel);

		JButton button = new JButton("Choose");
		this.add(button);

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(SharedController.getInstance()
						.getLastDirectory());
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XML (*.xml)", "xml");
				fileChooser.addChoosableFileFilter(filter);
				fileChooser.setFileFilter(filter);
				int returnValue = fileChooser.showDialog(null,
						"Choose specimen...");

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					SharedController.getInstance().setLastDirectory(
							fileChooser.getSelectedFile());
					System.out.println(path);
					setPath(path);
				}

			}
		});

		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton openButton = new JButton("OPEN");
		JButton cancelButton = new JButton("CANCEL");
		panel.add(cancelButton);
		panel.add(openButton);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(panel, constraints);

		buttons = new JButton[] { openButton, cancelButton };

	}

	public void addActionListener(OpenPopulationController controller) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setActionCommand(actions[i]);
			buttons[i].addActionListener(controller);
		}
	}

	public ImportPanel getImportPanel() {
		return importPanel;
	}

	public void setImportPanel(ImportPanel importPanel) {
		this.importPanel = importPanel;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
