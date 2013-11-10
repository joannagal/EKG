package pi.gui.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ProjectToolbar extends JPanel {

	private JButton projectButton = new JButton("Project Info");
	private JButton statisticsButton = new JButton("Calculate Statistics");
	private JButton raportButton = new JButton("Display report");
	private JButton[] buttons;
	private String[] buttonsEvent = new String[]{"PROJECT_INFO", "CALCULATE", "REPORT"};

	public ProjectToolbar() {

		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		layout.setAlignment(FlowLayout.CENTER);
		this.setBorder(BorderFactory.createTitledBorder("Project Settings"));

		this.add(this.projectButton);
		this.add(this.statisticsButton);
		this.add(this.raportButton);
		
		buttons = new JButton[]{projectButton, statisticsButton, raportButton};
	}
	
	public void setButtonsListener(ActionListener al){
		
		for (int i = 0; i < buttons.length; i++){
			buttons[i].setActionCommand(buttonsEvent[i]);
			buttons[i].addActionListener(al);
		}
	}

}
