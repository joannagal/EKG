package pi.gui.project.toolbar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolbarView extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JButton projectButton = new JButton("Project Info");
	private JButton statisticsButton = new JButton("Calculate Statistics");
	private JButton raportButton = new JButton("Display report");
	
	public ToolbarView()
	{
		this.setMinimumSize(new Dimension(50, 50));
		
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		layout.setAlignment(FlowLayout.LEADING);
		
		this.add(this.projectButton);
		this.add(this.statisticsButton);
		this.add(this.raportButton);
	}
	
}
