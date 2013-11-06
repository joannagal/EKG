package pi.graph.signal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pi.shared.SharedController;


public class GraphController implements ActionListener {

	private GraphToolbar view;
	private Graph model; 
	
	public GraphController(GraphToolbar view, Graph model){
		this.view = view;
		this.model = model;
		
		view.setButtonsListener(this);
	}
	
	public void actionPerformed(ActionEvent ae){
		
		String action = ae.getActionCommand();	
		if (action.equals("INFO")){

		}
		if (action.equals("ANALYSIS")){
			this.view.getStView().showWindow();
			this.view.getStControl();
		}
		if (action.equals("RESULTS")){
			
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
