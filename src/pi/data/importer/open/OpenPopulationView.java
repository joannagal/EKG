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
import pi.gui.information.InformationsController;
import pi.shared.SharedController;

public class OpenPopulationView extends JDialog {

	private String[] actions = new String[] { "OPEN", "CANCEL" };
	private JButton[] buttons;
	private JPanel importPanel;
	private GridBagConstraints constraints;
	private String path;
	private OpenPopulationController controller;
	private JLabel fileLabel;
	private JTextArea pathArea;
	private JButton chooseButton;
	private JButton openButton;
	private JButton cancelButton;
	private JPanel buttonPanel;
	

	public OpenPopulationView() {
		controller = new OpenPopulationController(this);
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.setVisible(true);
		this.setTitle("Open Project...");
		this.setBounds(500, 200, 450, 200);
			
		//importPanel configuration
		chooseButton = new JButton("Choose");
		chooseButton.addActionListener(this.controller);
		chooseButton.setActionCommand("CHOOSE");
		fileLabel = new JLabel("Project");
		fileLabel.setVisible(true);
		pathArea = new JTextArea();
		getPathArea().setEditable(true);
		getPathArea().setPreferredSize(new Dimension(250,20));
		getPathArea().setEditable(true);
		importPanel = new JPanel();
		importPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		importPanel.add(fileLabel);
		importPanel.add(getPathArea());
		importPanel.add(chooseButton);
		importPanel.setVisible(true);
		importPanel.setSize(500,440);
		
		openButton = new JButton("OPEN");
		openButton.addActionListener(this.controller);
		openButton.setActionCommand("OPEN");	
		cancelButton = new JButton("CANCEL");
		cancelButton.addActionListener(this.controller);
		cancelButton.setActionCommand("CANCEL");
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setVisible(true);
		buttonPanel.add(cancelButton);
		buttonPanel.add(openButton);
		
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

	}

	public JPanel getImportPanel() {
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

	public JTextArea getPathArea() {
		return pathArea;
	}

	public void setPathArea(JTextArea pathArea) {
		this.pathArea = pathArea;
	}

}
