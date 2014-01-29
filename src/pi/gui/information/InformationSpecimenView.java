package pi.gui.information;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InformationSpecimenView extends JDialog{
	
	private JLabel firstnameLabel;
	private JLabel surnameLabel;
	private JLabel birthLabel;
	private JLabel weightLabel;
	private JLabel methadoneLabel;
	private JLabel methadoneTreatmentLabel;
	private JLabel hivLabel;

	
	private JTextField firstnameField;
	private JTextField surnameField;
	private JTextField birthField;
	private JTextField weightField;
	private JTextField methadoneField;
	private JTextField methadoneTreatmentField;

	private JCheckBox hivCheckBox;
	
	private JPanel firstnamePanel;
	private JPanel surnamePanel;
	private JPanel birthPanel;
	private JPanel weightPanel;
	private JPanel methadonePanel;
	private JPanel methadoneTreatmentPanel;
	private JPanel hivPanel;
	private JPanel buttonPanel;
	
	private JButton okButton;
	private JButton cancelButton;
	
	
	GridBagConstraints constraints;
	private InformationSpecimenController controller;
	
	public InformationSpecimenView(){
		
		this.controller = new InformationSpecimenController(this);

		this.setTitle("Specimen Information");
		this.setBounds(100, 100, 500, 400);
		this.setVisible(true);
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		//firstname
		firstnameLabel = new JLabel("First name:");
		firstnameField = new JTextField();
		firstnamePanel = new JPanel();
		intializePanel(firstnameLabel, firstnameField, false, firstnamePanel);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		this.add(firstnamePanel, constraints);
		
		//surname
		surnameLabel = new JLabel("Surname:");
		surnameField = new JTextField();
		surnamePanel = new JPanel();
		intializePanel(surnameLabel, surnameField, false, surnamePanel);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(surnamePanel, constraints);
		
		//birthdate
		birthLabel = new JLabel("Birth date:");
		birthField = new JTextField();
		birthPanel = new JPanel();
		intializePanel(birthLabel, birthField, true, birthPanel);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		this.add(birthPanel, constraints);
					
		//weight
		weightLabel = new JLabel("Weight:");
		weightField = new JTextField();
		weightPanel = new JPanel();
		intializePanel(weightLabel, weightField, true, weightPanel);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		this.add(weightPanel, constraints);
		
		//methadone [ml]
		methadoneLabel = new JLabel("Methadone [ml]:");
		methadoneField = new JTextField();
		methadonePanel = new JPanel();
		intializePanel(methadoneLabel, methadoneField, true, methadonePanel);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		this.add(methadonePanel, constraints);
		
		//methadone treatment
		methadoneTreatmentLabel = new JLabel("Methadone Treatment [months]:");
		methadoneTreatmentField = new JTextField();
		methadoneTreatmentPanel = new JPanel();
		intializePanel(methadoneTreatmentLabel, methadoneTreatmentField, true, methadoneTreatmentPanel);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		this.add(methadoneTreatmentPanel, constraints);
		
		//hiv
		hivLabel = new JLabel("HIV");
		hivLabel.setPreferredSize(new Dimension(200, 20));
		hivCheckBox = new JCheckBox();
		hivCheckBox.setPreferredSize(new Dimension(160, 20));
		hivCheckBox.setVisible(true);
		hivPanel = new JPanel();
		hivPanel.add(hivLabel);
		hivPanel.add(hivCheckBox);
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		this.add(hivPanel, constraints);
		
		//buttonPanel
		okButton = new JButton("OK");
		cancelButton = new JButton("CANCEL");
		okButton.addActionListener(this.controller);
		okButton.setActionCommand("OK");
		cancelButton.addActionListener(this.controller);
		cancelButton.setActionCommand("CANCEL");
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(cancelButton);
		buttonPanel.add(okButton);
		buttonPanel.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		this.add(buttonPanel, constraints);
				
		this.validate();
		this.repaint();
		
	} 
	
	public void intializePanel(JLabel label, JTextField field, boolean state, JPanel panel){
		label.setPreferredSize(new Dimension(200,20));
		field.setVisible(true);
		field.setEditable(state);
		field.setPreferredSize(new Dimension(160, 20));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(label);
		panel.add(field);
	}
	

}
