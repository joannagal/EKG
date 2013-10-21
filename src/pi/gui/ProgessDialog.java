package pi.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

public class ProgessDialog extends JDialog{
	
	private JButton cancelButton;
	private JProgressBar progressBar;
	
	public ProgessDialog(Window parent){
		super(parent, "Analyzing...", ModalityType.APPLICATION_MODAL);
		
		cancelButton = new JButton("Cancel");
		progressBar = new JProgressBar();
		
		setLayout(new FlowLayout());
		
		Dimension size = cancelButton.getPreferredSize();
		size.width = 400;
		progressBar.setPreferredSize(size);
		
		add(progressBar);
		add(cancelButton);
		
		pack();
		
		setLocationRelativeTo(parent);
		
	}
	
	public void setMaximum(int value){
		progressBar.setMaximum(value);
	}
	
	public void setValue(int value){
		progressBar.setValue(value);
	}

}
