package pi.graph.signal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pi.gui.information.specimen.InformationSpecimenController;
import pi.gui.information.specimen.InformationSpecimenView;



public class GraphToolbarController implements ActionListener {

	private GraphToolbar view;
	private Graph model; 
	
	public GraphToolbarController(GraphToolbar view, Graph model){
		this.view = view;
		this.model = model;
		
		view.setButtonsListener(this);
	}
	
	public void actionPerformed(ActionEvent ae){
		
		String action = ae.getActionCommand();	
		if (action.equals("INFO")){
			InformationSpecimenView view = new InformationSpecimenView(this.view);
			@SuppressWarnings("unused")
			InformationSpecimenController controller = new InformationSpecimenController(view);
		}
		if (action.equals("AUTOFINDER")){
			this.view.getAfView().showWithSignal(model);
		}
		if (action.equals("ADD")){
			model.addSegment();

		}
		if (action.equals("DELETE")){
			model.delSegment();
		}
		
	}
}
