package pi.gui.information.specimen;


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

import pi.graph.signal.GraphToolbar;
import pi.population.Specimen;
import pi.shared.SharedController;

@SuppressWarnings("serial")
public class InformationSpecimenView extends JDialog {

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

	private Specimen specimen;
	private GraphToolbar toolbar;

	private String name;
	private String surname;
	private String birthDate;
	private int weight;
	private int methadone;
	private int methadoneTreatment;

	GridBagConstraints constraints;
	private InformationSpecimenController controller;

	public InformationSpecimenView(GraphToolbar toolbar) {

		this.setToolbar(toolbar);

		int type = toolbar.getGraphView().getType();

		if (type == 1) {
			setSpecimen(SharedController.getInstance().getProject()
					.getFirstPopulation().getSpecimen()
					.get(toolbar.getComboBoxSpecimen().getSelectedIndex()));
		} else if (type == 2) {
			int projectType = SharedController.getInstance().getProject().getType();
			
			if (projectType == 1 || projectType == 2){
				setSpecimen(SharedController.getInstance().getProject()
						.getFirstPopulation().getSpecimen()
						.get(toolbar.getComboBoxSpecimen().getSelectedIndex()));
			}
			else if (projectType == 3 || projectType == 4){
				setSpecimen(SharedController.getInstance().getProject()
						.getSecondPopulation().getSpecimen()
						.get(toolbar.getComboBoxSpecimen().getSelectedIndex()));
			}
			
		}

		name = specimen.getName();
		surname = specimen.getSurname();
		birthDate = specimen.getBirth();
		weight = specimen.getWeight();
		methadone = specimen.getMethadone();
		methadoneTreatment = specimen.getMetadonTimeApplication();

		this.controller = new InformationSpecimenController(this);

		this.setTitle("Specimen Information");
		this.setBounds(100, 100, 500, 400);
		this.setVisible(true);
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		// firstname
		setFirstnameLabel(new JLabel("First name:"));
		setFirstnameField(new JTextField(name));
		firstnamePanel = new JPanel();
		intializePanel(getFirstnameLabel(), getFirstnameField(), false,
				firstnamePanel);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		this.add(firstnamePanel, constraints);

		// surname
		setSurnameLabel(new JLabel("Surname:"));
		surnameField = new JTextField(surname);
		surnamePanel = new JPanel();
		intializePanel(getSurnameLabel(), surnameField, false, surnamePanel);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(surnamePanel, constraints);

		// birthdate
		birthLabel = new JLabel("Birth date:");
		setBirthField(new JTextField(birthDate));
		birthPanel = new JPanel();
		intializePanel(birthLabel, getBirthField(), true, birthPanel);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		this.add(birthPanel, constraints);

		// weight
		weightLabel = new JLabel("Weight:");
		String temp = Integer.toString(weight);
		setWeightField(new JTextField(temp));
		weightPanel = new JPanel();
		intializePanel(weightLabel, getWeightField(), true, weightPanel);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		this.add(weightPanel, constraints);

		// methadone [ml]
		methadoneLabel = new JLabel("Methadone [ml]:");
		temp = Integer.toString(methadone);
		setMethadoneField(new JTextField(temp));
		methadonePanel = new JPanel();
		intializePanel(methadoneLabel, getMethadoneField(), true,
				methadonePanel);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		this.add(methadonePanel, constraints);

		// methadone treatment
		methadoneTreatmentLabel = new JLabel("Methadone Treatment [months]:");
		temp = Integer.toString(methadoneTreatment);
		setMethadoneTreatmentField(new JTextField(temp));
		methadoneTreatmentPanel = new JPanel();
		intializePanel(methadoneTreatmentLabel, getMethadoneTreatmentField(),
				true, methadoneTreatmentPanel);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		this.add(methadoneTreatmentPanel, constraints);

		// hiv
		hivLabel = new JLabel("HIV");
		hivLabel.setPreferredSize(new Dimension(200, 20));
		setHivCheckBox(new JCheckBox());
		getHivCheckBox().setPreferredSize(new Dimension(160, 20));
		getHivCheckBox().setVisible(true);
		hivPanel = new JPanel();
		hivPanel.add(hivLabel);
		hivPanel.add(getHivCheckBox());

		if (specimen.getHiv() == 0) {
			hivCheckBox.setSelected(false);
		} else {
			hivCheckBox.setSelected(true);
		}
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		this.add(hivPanel, constraints);

		// buttonPanel
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

	public void intializePanel(JLabel label, JTextField field, boolean state,
			JPanel panel) {
		label.setPreferredSize(new Dimension(200, 20));
		field.setVisible(true);
		field.setEditable(state);
		field.setPreferredSize(new Dimension(160, 20));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(label);
		panel.add(field);
	}

	public JTextField getBirthField() {
		return birthField;
	}

	public void setBirthField(JTextField birthField) {
		this.birthField = birthField;
	}

	public JTextField getWeightField() {
		return weightField;
	}

	public void setWeightField(JTextField weightField) {
		this.weightField = weightField;
	}

	public JTextField getMethadoneField() {
		return methadoneField;
	}

	public void setMethadoneField(JTextField methadoneField) {
		this.methadoneField = methadoneField;
	}

	public JTextField getMethadoneTreatmentField() {
		return methadoneTreatmentField;
	}

	public void setMethadoneTreatmentField(JTextField methadoneTreatmentField) {
		this.methadoneTreatmentField = methadoneTreatmentField;
	}

	public JCheckBox getHivCheckBox() {
		return hivCheckBox;
	}

	public void setHivCheckBox(JCheckBox hivCheckBox) {
		this.hivCheckBox = hivCheckBox;
	}

	public JLabel getFirstnameLabel() {
		return firstnameLabel;
	}

	public void setFirstnameLabel(JLabel firstnameLabel) {
		this.firstnameLabel = firstnameLabel;
	}

	public JLabel getSurnameLabel() {
		return surnameLabel;
	}

	public void setSurnameLabel(JLabel surnameLabel) {
		this.surnameLabel = surnameLabel;
	}

	public Specimen getSpecimen() {
		return specimen;
	}

	public void setSpecimen(Specimen specimen) {
		this.specimen = specimen;
	}

	public GraphToolbar getToolbar() {
		return toolbar;
	}

	public void setToolbar(GraphToolbar toolbar) {
		this.toolbar = toolbar;
	}

	public JTextField getFirstnameField() {
		return firstnameField;
	}

	public void setFirstnameField(JTextField firstnameField) {
		this.firstnameField = firstnameField;
	}

}
