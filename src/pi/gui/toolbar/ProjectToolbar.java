package pi.gui.toolbar;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import pi.statistics.gui.StatisticWindowController;
import pi.statistics.gui.StatisticWindowView;

public class ProjectToolbar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton projectButton = new JButton("Project info");
	private JButton statisticsButton = new JButton("Calculate statistics");
	private JButton resultsButton = new JButton("Display statistic results");
	private JButton[] buttons;
	private String[] buttonsEvent = new String[]{"PROJECT_INFO", "CALCULATE", "STATS"};
	private StatisticWindowView stView = new StatisticWindowView();
	private StatisticWindowController stControl = new StatisticWindowController(stView);

	public ProjectToolbar() {

		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		layout.setAlignment(FlowLayout.CENTER);
		this.setBorder(BorderFactory.createTitledBorder("Project Settings"));

		this.add(this.projectButton);
		this.add(this.statisticsButton);	
		this.add(this.resultsButton);
		buttons = new JButton[]{projectButton, statisticsButton, resultsButton};
	}
	
	public void setButtonsListener(ActionListener al){
		
		for (int i = 0; i < buttons.length; i++){
			buttons[i].setActionCommand(buttonsEvent[i]);
			buttons[i].addActionListener(al);
		}
	}
	
	public StatisticWindowView getStView() {
	    return stView;
	}

	public void setStView(StatisticWindowView stView) {
	    this.stView = stView;
	}

	public StatisticWindowController getStControl() {
	    return stControl;
	}

	public void setStControl(StatisticWindowController stControl) {
	    this.stControl = stControl;
	}

}
