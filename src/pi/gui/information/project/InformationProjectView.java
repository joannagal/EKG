package pi.gui.information.project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pi.shared.SharedController;

public class InformationProjectView extends JDialog {
	private static final long serialVersionUID = 1L;

	private InformationProjectController controller;

	private JButton cancelButton = new JButton("Cancel");
	private JButton okButton = new JButton("OK");

	private JLabel projectLabel = new JLabel("Project name");
	private JLabel firstLabel = new JLabel("First population name");
	private JLabel secondLabel = new JLabel("Second population name");

	private JTextField projectField = new JTextField();
	private JTextField firstField = new JTextField();
	private JTextField secondField = new JTextField();

	private JPanel projectPanel = new JPanel();
	private JPanel firstPanel = new JPanel();
	private JPanel secondPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();

	GridBagConstraints constraints;

	public InformationProjectView() {
		URL iconURL = getClass().getResource("../../../../images/logo1.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		
		this.setTitle("Project Informations");
		controller = new InformationProjectController(this);
		int type = SharedController.getInstance().getProject().getType();

		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		// projectPanel
		projectPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		projectPanel.setVisible(true);
		projectLabel.setSize(100, 20);
		projectField.setPreferredSize(new Dimension(150, 20));
		projectLabel.setPreferredSize(new Dimension(160, 20));
		projectPanel.add(projectLabel);
		projectPanel.add(projectField);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		this.add(projectPanel, constraints);

		// firstPanel
		firstPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		firstPanel.setVisible(true);
		firstLabel.setPreferredSize(new Dimension(160, 20));
		firstField.setPreferredSize(new Dimension(150, 20));
		firstPanel.add(firstLabel);
		firstPanel.add(firstField);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(firstPanel, constraints);

		// secondPanel
		secondPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		secondPanel.setVisible(true);
		secondField.setPreferredSize(new Dimension(150, 20));
		secondLabel.setPreferredSize(new Dimension(160, 20));
		secondPanel.add(secondLabel);
		secondPanel.add(secondField);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		this.add(secondPanel, constraints);
		this.setSize(400, 200);

		// buttonPanel
		okButton.addActionListener(this.controller);
		okButton.setActionCommand("OK");
		cancelButton.addActionListener(this.controller);
		cancelButton.setActionCommand("CANCEL");
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(cancelButton);
		buttonPanel.add(okButton);
		buttonPanel.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		this.add(buttonPanel, constraints);

		if (type == 1 || type == 2) {
			this.secondPanel.setVisible(false);
		}

		this.setVisible(true);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.showWithData();

	}

	public void showWithData() {
		String name = SharedController.getInstance().getProject().getName();
		if (name != null)
			this.getProjectField().setText(name);

		String first = SharedController.getInstance().getProject()
				.getFirstPopulation().getName();
		if (first != null)
			this.getFirstField().setText(first);

		if (SharedController.getInstance().getProject().getSecondPopulation() != null) {
			String second = SharedController.getInstance().getProject()
					.getSecondPopulation().getName();
			if (second != null)
				this.getSecondField().setText(second);
		}

		this.setVisible(true);
	}

	public JTextField getProjectField() {
		return projectField;
	}

	public void setProjectField(JTextField projectField) {
		this.projectField = projectField;
	}

	public JTextField getFirstField() {
		return firstField;
	}

	public void setFirstField(JTextField firstField) {
		this.firstField = firstField;
	}

	public JTextField getSecondField() {
		return secondField;
	}

	public void setSecondField(JTextField secondField) {
		this.secondField = secondField;
	}

}
