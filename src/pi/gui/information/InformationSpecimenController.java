package pi.gui.information;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class InformationSpecimenController implements ActionListener{

	private InformationSpecimenView view;
	
	public InformationSpecimenController(InformationSpecimenView view){
		this.view = view;
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
	
	
}
