package pi.gui.information.specimen;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InformationSpecimenController implements ActionListener{

	private InformationSpecimenView view;
	
	public InformationSpecimenController(InformationSpecimenView view){
		this.setView(view);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String action = e.getActionCommand();

		if (action.equals("OK")){
		}
		if (action.equals("CANCEL")){
			
		}
	}

	public InformationSpecimenView getView() {
		return view;
	}

	public void setView(InformationSpecimenView view) {
		this.view = view;
	}
	
	
}
