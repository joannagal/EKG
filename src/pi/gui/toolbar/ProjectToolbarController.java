package pi.gui.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectToolbarController implements ActionListener {

	private ProjectToolbar tool;
	
	public ProjectToolbarController(ProjectToolbar tool){
		this.tool = tool;
		
		tool.setButtonsListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();	
		
		if (action.equals("PROJECT_INFO")){

		}
		
		if (action.equals("CALCULATE")){

		}
		
		if (action.equals("REPORT")){

		}
		
	}

}
