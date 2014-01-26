package pi.data.importer.pair;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import pi.shared.SharedController;

public class ImportPairView extends JDialog{

	GridBagConstraints constraints;
	private JButton okButton;
	private JButton cancelButton;
	private JButton chooseFirstButton;
	private JButton chooseSecondButton;
	private JPanel buttonPanel;	
	private JLabel firstLabel;
	private JLabel secondLabel;
	private JTextArea firstPathArea;
	private JTextArea secondPathArea;
	private JPanel importFirstPanel;
	private JPanel importSecondPanel;
	private ImportPairController controller;
	private final JFileChooser fileChooser = new JFileChooser();
	
	
	public ImportPairView(){
		
		okButton = new JButton("OK");
		cancelButton = new JButton("CANCEL");
		chooseFirstButton = new JButton("CHOOSE");
		chooseSecondButton = new JButton("CHOOSE");
		buttonPanel = new JPanel();
		importFirstPanel = new JPanel();
		importSecondPanel = new JPanel();
		setFirstPathArea(new JTextArea());
		setSecondPathArea(new JTextArea());
		importFirstPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		importSecondPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	
		//first importPanel configuration
		firstLabel = new JLabel("Specimen: Before");
		firstLabel.setVisible(true);
		importFirstPanel.add(firstLabel);
		getFirstPathArea().setEditable(true);
		getFirstPathArea().setPreferredSize(new Dimension(150,20));
		getFirstPathArea().setEditable(true);
		importFirstPanel.add(getFirstPathArea());
		importFirstPanel.add(chooseFirstButton);
		importFirstPanel.setVisible(true);
		importFirstPanel.setSize(300,500);
		
		//second importPanel configuration
		secondLabel = new JLabel("Specimen: After   ");
		secondLabel.setVisible(true);
		importSecondPanel.add(secondLabel);
		getSecondPathArea().setEditable(true);
		getSecondPathArea().setPreferredSize(new Dimension(150,20));
		getSecondPathArea().setEditable(true);
		importSecondPanel.add(getSecondPathArea());
		importSecondPanel.add(chooseSecondButton);
		importSecondPanel.setVisible(true);
		importSecondPanel.setSize(300,500);
		
		
		//buttonPanel configuration
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(cancelButton);
		buttonPanel.add(okButton);
		
		//this panel configuration
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		this.add(importFirstPanel, constraints);
	
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(importSecondPanel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		this.add(buttonPanel, constraints);
	
		this.controller = new ImportPairController(this);
		this.setTitle("Create Specimen: Pair");
		this.setVisible(true);
		this.setLocation(100, 100);
		this.setSize(500,150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//configuration of ActionListeners
		chooseFirstButton.setActionCommand("CHOOSE_FIRST");
		chooseFirstButton.addActionListener(this.controller);
		
		chooseSecondButton.setActionCommand("CHOOSE_SECOND");
		chooseSecondButton.addActionListener(this.controller);
		
		okButton.setActionCommand("OK");
		okButton.addActionListener(this.controller);
		
		cancelButton.setActionCommand("CANCEL");
		cancelButton.addActionListener(this.controller);
		
		//FileChooser configuration
    	fileChooser.setCurrentDirectory(SharedController.getInstance().getLastDirectory());
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML (*.xml)","xml");
    	fileChooser.addChoosableFileFilter(filter);
    	fileChooser.setFileFilter(filter);
    	
		
	}


	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	
	public ImportPairView getContext(){
		return this;
	}


	public JTextArea getFirstPathArea() {
		return firstPathArea;
	}


	public void setFirstPathArea(JTextArea firstPathArea) {
		this.firstPathArea = firstPathArea;
	}


	public JTextArea getSecondPathArea() {
		return secondPathArea;
	}


	public void setSecondPathArea(JTextArea secondPathArea) {
		this.secondPathArea = secondPathArea;
	}
	
	
	
	
}
