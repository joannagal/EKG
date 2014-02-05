package pi.data.importer.open;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class OpenPopulationView extends JDialog {

	private static final long serialVersionUID = 1L;
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
		chooseButton = new JButton("CHOOSE");
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
