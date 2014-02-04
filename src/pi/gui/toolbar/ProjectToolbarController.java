package pi.gui.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pi.gui.information.project.InformationProjectController;
import pi.gui.information.project.InformationProjectView;


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
			InformationProjectView view = new InformationProjectView();
			InformationProjectController controller = new InformationProjectController(view);
		}
		
		if (action.equals("CALCULATE")){
			this.tool.getStView().showWindow();
			this.tool.getStControl();
		}
		
		if (action.equals("STATS")){
		    if (this.tool.getStControl().getComparatorView() != null) this.tool.getStControl().getComparatorView().setVisible(true);
		    else if (this.tool.getStControl().getTestsView() != null) this.tool.getStControl().getTestsView().setVisible(true);
		    else JOptionPane.showMessageDialog(tool, "Count statistics first!");
		}
		
	}

}
