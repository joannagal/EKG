package pi.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

public class ProjectDialog extends JDialog{
	
	private JPanel projectPanel;
	private JLabel title;
	private JRadioButton oneSignalButton;
	private JRadioButton twoSignalsButton;
	private JRadioButton twoPopulationsButton;
	private JRadioButton diffrencesPopulationButton;
	private ButtonGroup projectGroup;
	private JButton cancelButton;
	private JButton proceedButton;
	private JPanel buttonPanel;
	private JLabel projectLabel;
	
	public ProjectDialog(){
			
		setVisible(true);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setVisible(true);
		this.add(panel);
		panel.setPreferredSize(new Dimension(350,220));
		this.setResizable(false);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		projectLabel = new JLabel("New project");
		projectLabel.setFont(new Font("Arial", Font.BOLD, 30));
		panel.add(projectLabel);
		
		oneSignalButton = new JRadioButton("Single singal");
		oneSignalButton.setFont(new Font("Arial", Font.PLAIN, 16));
		twoSignalsButton = new JRadioButton("Signal pair analyzis");
		twoSignalsButton.setFont(new Font("Arial", Font.PLAIN, 16));
		twoPopulationsButton = new JRadioButton("Two populations");
		twoPopulationsButton.setFont(new Font("Arial", Font.PLAIN, 16));
		diffrencesPopulationButton = new JRadioButton("Diffrences between populations");
		diffrencesPopulationButton.setFont(new Font("Arial", Font.PLAIN, 16));
		
		
		projectGroup = new ButtonGroup();
		projectGroup.add(oneSignalButton);
		projectGroup.add(twoPopulationsButton);
		projectGroup.add(twoSignalsButton);
		projectGroup.add(diffrencesPopulationButton);
		
		projectLabel.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		panel.add(oneSignalButton, constraints);
		
		oneSignalButton.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		panel.add(oneSignalButton, constraints);
		
		twoSignalsButton.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		panel.add(twoSignalsButton, constraints);
		
		twoPopulationsButton.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		panel.add(twoPopulationsButton, constraints);
		
		diffrencesPopulationButton.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		panel.add(diffrencesPopulationButton, constraints);
		
		buttonPanel = new JPanel();
		cancelButton = new JButton("Cancel");
		proceedButton = new JButton("Next");

		buttonPanel.add(cancelButton);
		buttonPanel.add(proceedButton);
		
		buttonPanel.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		panel.add(buttonPanel, constraints);
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		
		getContentPane().add(panel, BorderLayout.CENTER);		
		
		pack();
		
		
		
				
		
	}

}
