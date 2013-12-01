package pi.statistics.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pi.population.Specimen;
import pi.shared.SharedController;
import pi.statistics.functions.*;
import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticsController;

public class StatisticWindowController implements ActionListener {

    private StatisticWindowView window;
    StatisticsController stControl = new StatisticsController();

    public StatisticWindowController(StatisticWindowView window) {
	this.window = window;
	window.setButtonsListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	String action = e.getActionCommand();
	if (action.equals("CANCEL")) {
	    window.setVisible(false);
	}
	if (action.equals("REPORT")) {
	    if (stControl.getFinalResult() != null) {
		// TODO raport koñcowy ze statystyki
	    } else {
		JOptionPane
			.showMessageDialog(window, "Count statistics first!");
	    }
	}
	if (action.equals("COUNT")) {
	    ArrayList<Function> functions = new ArrayList<Function>();
	    ArrayList<String> wavesNames = new ArrayList<String>();
	    double alpha;
    	    try {
    		alpha = Double.parseDouble(window.alphaTextField.getText());
    	    } catch (Exception ex) {
    	       	alpha = 0;
    	    }

	    int index = window.comboBox.getSelectedIndex();
	    String specimen = window.comboBox.getItemAt(index);
	    String id = null;

	    if (specimen.equals("Count for all")) {
		id = null;
	    } else {
		String[] ids = specimen.split(" ");
		for (Specimen man : SharedController.getInstance().getProject()
			.getFirstPopulation().getSpecimen()) {
		    if (ids[0].equals(man.getName())
			    && ids[1].equals(man.getSurname())) {
			id = man.getId();
		    }
		}
		if (SharedController.getInstance().getProject()
			.getSecondPopulation() != null) {
		    for (Specimen man : SharedController.getInstance()
			    .getProject().getSecondPopulation().getSpecimen()) {
			if (ids[0].equals(man.getName())
				&& ids[1].equals(man.getSurname())) {
			    id = man.getId();
			}
		    }
		}
	    }

	    Function average = new Average();
	    functions.add(average);
	    // Function avgSignal = new AverageSignal();
	    // functions.add(avgSignal);
	    Function max = new Max();
	    functions.add(max);
	    // Function median = new Median();
	    // functions.add(median);
	    Function min = new Min();
	    functions.add(min);
	    Function amplitude = new Amplitude();
	    functions.add(amplitude);
	    Function sd = new SD();
	    functions.add(sd);
	    Function variance = new Variance();
	    functions.add(variance);

	    for (int i = 0; i < window.checkBoxArray.length; i++) {
		if (window.checkBoxArray[i].isSelected() == true) {
		    wavesNames.add(window.checkBoxArray[i].getName());
		}
	    }

	    if (alpha > 0 && alpha <= 0.05) {
		stControl.countStatistics(functions, wavesNames, alpha, id);
	    } else
		stControl.countStatistics(functions, wavesNames, 0, id);
	}

    }

}
