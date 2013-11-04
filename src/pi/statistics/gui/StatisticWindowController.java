package pi.statistics.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticWindowController implements ActionListener{

    private StatisticWindowView window;
    
    public StatisticWindowController(StatisticWindowView window){
	this.window = window;
	window.setButtonsListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	String action = e.getActionCommand();	
	if (action.equals("CANCEL")){

	}	
	if (action.equals("REPORT")){

	}	
	if (action.equals("COUNT")){

	}	
    
    }

}
