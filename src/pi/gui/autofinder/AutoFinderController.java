package pi.gui.autofinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;

import pi.graph.signal.Graph;
import pi.inputs.signal.Channel;
import pi.inputs.signal.autofinder.Parameters;
import pi.shared.SharedController;

public class AutoFinderController
{
	AutoFinderView view;
	private Graph graph;
	private Channel signal;
	private Parameters params;
	
	class FinderRunnable implements Runnable {
		public void run() {
			JProgressBar bar = view.getProgressBar();
			bar.setMinimum(0);
			bar.setMaximum(4);
			bar.setValue(0);
			SharedController.getInstance().setProgressBar(bar);
			// run finding
			signal.getParent().findForChannel(signal);
			graph.recalculate();
			graph.draw();
		}
	}

	public void showError(String message)
	{
		JOptionPane.showMessageDialog(view, message);
	}
	
	private ActionListener closeAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent evt)
		{
			view.setVisible(false);
		}
	};
	
	private ActionListener findAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent evt)
		{
			view.getCloseButton().setEnabled(false);
			
			if (verifyData())
			{
				FinderRunnable runnable = new FinderRunnable();
				Thread thread = new Thread(runnable);
				thread.start();
			}
			
			view.getCloseButton().setEnabled(true);
		}
	};
	
	
	public ActionListener getCloseAction() {return this.closeAction;}
	public ActionListener getFindAction() {return this.findAction;}
	
	public boolean verifyData()
	{
		// SIMPLIFIED_RANGE
		JTable table = this.view.getTableBase();
	
		
		// --------------------------------------------------------------
		Object value = table.getValueAt(0, 1);
		boolean pass = true;
		int simplified_range = 0;
		try {simplified_range = Integer.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && ((simplified_range < 1) || (simplified_range > 5))) pass = false; 
		if (!pass) {this.showError("This field should be: [1 ; 5] integer number"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(1, 1);
		boolean down_signal = false;
		pass = true;
		if (value.toString().compareTo("true") == 0) down_signal = true;
		else if (value.toString().compareTo("false") == 0) down_signal = false;
		else {this.showError("This field should be: 'true' or 'false'"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(2, 1);
		double qrs_asc_deriv = 0.0d;
		try {qrs_asc_deriv = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if (!pass) {this.showError("This field should be: real number f.i 1.15"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(3, 1);
		double qrs_asc_time = 0.0d;
		try {qrs_asc_time = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (qrs_asc_time < 0.0000d)) pass = false;
		if (!pass) {this.showError("This field should be: real number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(4, 1);
		int qrs_max_neg_asc = 0;
		try {qrs_max_neg_asc = Integer.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (qrs_max_neg_asc < 0)) pass = false;
		if (!pass) {this.showError("This field should be: integer number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(5, 1);
		double qrs_desc_deriv = 0.0d;
		try {qrs_desc_deriv = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if (!pass) {this.showError("This field should be: real number f.i 1.15"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(6, 1);
		double qrs_desc_time = 0.0d;
		try {qrs_desc_time = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (qrs_desc_time < 0.0000d)) pass = false;
		if (!pass) {this.showError("This field should be: real number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(7, 1);
		int qrs_max_neg_desc = 0;
		try {qrs_max_neg_desc = Integer.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (qrs_max_neg_desc < 0)) pass = false;
		if (!pass) {this.showError("This field should be: integer number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(8, 1);
		double qrs_right_up_deriv = 0.0d;
		try {qrs_right_up_deriv = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if (!pass) {this.showError("This field should be: real number f.i 1.15"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(9, 1);
		double qrs_right_up_time = 0.0d;
		try {qrs_right_up_time = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (qrs_right_up_time < 0.0000d)) pass = false;
		if (!pass) {this.showError("This field should be: real number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(10, 1);
		int qrs_max_neg_right_up = 0;
		try {qrs_max_neg_right_up = Integer.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (qrs_max_neg_right_up < 0)) pass = false;
		if (!pass) {this.showError("This field should be: integer number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(11, 1);
		double qrs_jump_after = 0.0d;
		try {qrs_jump_after = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (qrs_jump_after < 0.0000d)) pass = false; 
		if (!pass) {this.showError("This field should be: real number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(12, 1);
		boolean t_is_up = false;
		pass = true;
		if (value.toString().compareTo("true") == 0) t_is_up = true;
		else if (value.toString().compareTo("false") == 0) t_is_up = false;
		else {this.showError("This field should be: 'true' or 'false'"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(13, 1);
		double t_left_prop = 0.0d;
		try {t_left_prop = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (t_left_prop < 0.0000d)) pass = false; 
		if (!pass) {this.showError("This field should be: real number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(14, 1);
		double t_left_deriv = 0.0d;
		try {t_left_deriv = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if (!pass) {this.showError("This field should be: real number f.i 1.15"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(15, 1);
		double t_left_time = 0;
		try {t_left_time = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (t_left_time < 0.0000d)) pass = false; 
		if (!pass) {this.showError("This field should be: real number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(16, 1);
		int t_left_neg = 0;
		try {t_left_neg = Integer.valueOf((String)value);}
		catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (qrs_max_neg_right_up < 0)) pass = false;
		if (!pass) {this.showError("This field should be: integer number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(17, 1);
		double t_right_prop = 0.0d;
		try {t_right_prop = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && ((t_right_prop < 0.0000d) || (t_right_prop < t_left_prop))) pass = false; 
		if (!pass) {this.showError("This field should be: real number greater/equal t_left_prop"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(18, 1);
		double t_right_deriv = 0.0d;
		try {t_right_deriv = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if (!pass) {this.showError("This field should be: real number f.i 1.15"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(19, 1);
		double t_right_time = 0;
		try {t_right_time = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (t_right_time < 0.0000d)) pass = false; 
		if (!pass) {this.showError("This field should be: real number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(20, 1);
		int t_right_neg = 0;
		try {t_right_neg = Integer.valueOf((String)value);}
		catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (t_right_neg < 0)) pass = false;
		if (!pass) {this.showError("This field should be: integer number greater/equal 0"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(21, 1);
		double p_mod = 0.0d;
		try {p_mod = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && (p_mod < 0.0000d) ) pass = false;
		if (!pass) {this.showError("This field should be: real number f.i 1.15"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(22, 1);
		double p_left_prop = 0;
		try {p_left_prop = Double.valueOf((String)value);}
	    catch (NumberFormatException exc) {pass = false;}
		if ((pass) && ((p_left_prop < 0.0000d) || (p_left_prop > 1.0d))) pass = false; 
		if (!pass) {this.showError("This field should be: real number [0.0 ; 1.0]"); return false;}
		// --------------------------------------------------------------
		value = table.getValueAt(23, 1);
		double p_right_prop = 0;
		try {p_right_prop = Double.valueOf((String)value);}
		catch (NumberFormatException exc) {pass = false;}
		if ((pass) && ((p_right_prop < 0.0000d) || (p_left_prop < p_right_prop))) pass = false; 
		if (!pass) {this.showError("This field should be: real number greater/equal 0.0 and smaller then t_left_prop"); return false;}
		// --- FILL PARAMS ---------
		params.SIMPLIFIED_RANGE = simplified_range;
		params.DOWN_SIGNAL = down_signal;
		params.QRS_ASC_DERIV = qrs_asc_deriv;
		params.QRS_ASC_TIME = qrs_asc_time;
		params.QRS_MAX_NEG_ASC = qrs_max_neg_asc;
		params.QRS_DESC_DERIV = qrs_desc_deriv;
		params.QRS_DESC_TIME = qrs_desc_time;
		params.QRS_MAX_NEG_DESC = qrs_max_neg_desc;
		params.QRS_RIGHT_UP_DERIV = qrs_right_up_deriv;
		params.QRS_RIGHT_UP_TIME = qrs_right_up_time;
		params.QRS_MAX_NEG_RIGHT_UP = qrs_max_neg_right_up;
		params.QRS_JUMP_AFTER = qrs_jump_after;
		params.T_IS_UP = t_is_up;
		params.T_LEFT_PROP = t_left_prop;
		params.T_LEFT_DERIV = t_left_deriv;
		params.T_LEFT_TIME = t_left_time;
		params.T_LEFT_NEG = t_left_neg;
		params.T_RIGHT_PROP = t_right_prop;
		params.T_RIGHT_DERIV = t_right_deriv;
		params.T_RIGHT_TIME = t_right_time;
		params.T_RIGHT_NEG = t_right_neg;
		params.P_MOD = p_mod;
		params.P_LEFT_PROP = p_left_prop;
		params.P_RIGHT_PROP = p_right_prop;
		return true;
	}
	
	public void applyParams(Graph graph, AutoFinderView view)
	{
		this.view = view;
		this.graph = graph;
		this.signal = graph.getSignal();
		this.params = signal.getParams();

		JTable table = view.getTableBase();
		table.setValueAt(String.valueOf(params.SIMPLIFIED_RANGE), 0, 1);
		table.setValueAt(String.valueOf(params.DOWN_SIGNAL), 1, 1);
		table.setValueAt(String.valueOf(params.QRS_ASC_DERIV), 2, 1);
		table.setValueAt(String.valueOf(params.QRS_ASC_TIME), 3, 1);
		table.setValueAt(String.valueOf(params.QRS_MAX_NEG_ASC), 4, 1);
		table.setValueAt(String.valueOf(params.QRS_DESC_DERIV), 5, 1);
		table.setValueAt(String.valueOf(params.QRS_DESC_TIME), 6, 1);
		table.setValueAt(String.valueOf(params.QRS_MAX_NEG_DESC), 7, 1);
		table.setValueAt(String.valueOf(params.QRS_RIGHT_UP_DERIV), 8, 1);
		table.setValueAt(String.valueOf(params.QRS_RIGHT_UP_TIME), 9, 1);
		table.setValueAt(String.valueOf(params.QRS_MAX_NEG_RIGHT_UP), 10, 1);
		table.setValueAt(String.valueOf(params.QRS_JUMP_AFTER), 11, 1);
		table.setValueAt(String.valueOf(params.T_IS_UP), 12, 1);
		table.setValueAt(String.valueOf(params.T_LEFT_PROP), 13, 1);
		table.setValueAt(String.valueOf(params.T_LEFT_DERIV), 14, 1);
		table.setValueAt(String.valueOf(params.T_LEFT_TIME), 15, 1);
		table.setValueAt(String.valueOf(params.T_LEFT_NEG), 16, 1);
		table.setValueAt(String.valueOf(params.T_RIGHT_PROP), 17, 1);
		table.setValueAt(String.valueOf(params.T_RIGHT_DERIV), 18, 1);
		table.setValueAt(String.valueOf(params.T_RIGHT_TIME), 19, 1);
		table.setValueAt(String.valueOf(params.T_RIGHT_NEG), 20, 1);
		table.setValueAt(String.valueOf(params.P_MOD), 21, 1);
		table.setValueAt(String.valueOf(params.P_LEFT_PROP), 22, 1);
		table.setValueAt(String.valueOf(params.P_RIGHT_PROP), 23, 1);
		

	}
}
