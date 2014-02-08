package pi.data.importer.signal;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import pi.shared.SharedController;

public class ImportSingleView extends JDialog {

	private static final long serialVersionUID = 1L;
	GridBagConstraints constraints;
	private JButton okButton;
	private JButton cancelButton;
	private JButton chooseButton;
	private JPanel buttonPanel;
	private JTextArea pathArea;
	private JPanel importPanel;
	private JLabel fileLabel;
	private ImportSingleController controller;
	private final JFileChooser fileChooser = new JFileChooser();

	public ImportSingleView() {

		super(SharedController.getInstance().getFrame());

		URL iconURL = getClass().getResource("../../../../images/logo1.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());

		okButton = new JButton("OK");
		cancelButton = new JButton("CANCEL");
		chooseButton = new JButton("CHOOSE");
		buttonPanel = new JPanel();
		importPanel = new JPanel();
		setPathArea(new JTextArea());
		importPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		fileLabel = new JLabel("Specimen");
		fileLabel.setVisible(true);
		importPanel.add(fileLabel);
		getPathArea().setEditable(true);
		getPathArea().setPreferredSize(new Dimension(250, 20));
		getPathArea().setEditable(true);
		importPanel.add(getPathArea());
		importPanel.add(chooseButton);
		importPanel.setVisible(true);
		importPanel.setSize(300, 500);

		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(cancelButton);
		buttonPanel.add(okButton);

		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		this.add(importPanel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(buttonPanel, constraints);

		this.controller = new ImportSingleController(this);
		this.setTitle("Create Specimen: Single");
		this.setVisible(true);
		this.setLocation(100, 100);
		this.setSize(500, 150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		chooseButton.setActionCommand("CHOOSE");
		chooseButton.addActionListener(this.controller);

		okButton.setActionCommand("OK");
		okButton.addActionListener(this.controller);

		cancelButton.setActionCommand("CANCEL");
		cancelButton.addActionListener(this.controller);

		fileChooser.setCurrentDirectory(SharedController.getInstance()
				.getLastDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"XML (*.xml)", "xml");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);

	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public JTextArea getPathArea() {
		return pathArea;
	}

	public void setPathArea(JTextArea text) {
		this.pathArea = text;
	}

	public ImportSingleView getContext() {
		return this;
	}

}
