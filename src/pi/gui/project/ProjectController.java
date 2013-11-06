package pi.gui.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectController implements ActionListener
{
	ProjectView view;
	
	public ProjectController(ProjectView view)
	{
		this.view = view;
	}
	
	public void recalculate()
	{
		//int height = this.view.getSize().height;
		//this.view.getSplitter().setDividerLocation(height / 2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
