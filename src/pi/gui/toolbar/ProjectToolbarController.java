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
			this.tool.getStView().showWindow();
			this.tool.getStControl();
		}
		
		if (action.equals("REPORT")){

		}
		
	}

}
