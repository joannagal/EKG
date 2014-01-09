package pi.data.importer.signal;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import pi.shared.SharedController;



public class ImporterView extends JDialog{
	
	GridBagConstraints constraints;
	private JFileChooser fileChooser;
	ImportPanel[] importPanels;
	private JButton nextButton;
	private JButton backButton;
	private JButton[] buttons;
	private JPanel buttonPanel;
	private String[] buttonEvents = new String[]{"NEXT", "PREVIOUS"};
	ArrayList<String> paths = new ArrayList<String>(2);
	
	public ImporterView() {
		
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		this.setTitle("Choose specimen...");
		this.setVisible(true);
		
		setImportPanels(new ImportPanel[2]);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		this.getImportPanels()[0] = new ImportPanel(this);
		this.add(getImportPanels()[0], constraints);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setVisible(true);
		
		setNextButton(new JButton("NEXT"));
		getNextButton().setVisible(true);
		getNextButton().setEnabled(false);
		
		backButton = new JButton("BACK");
		backButton.setVisible(true);
		
		buttonPanel.add(backButton);
		buttonPanel.add(getNextButton());
			
		if (SharedController.getInstance().getProject().getType() == 1){
			
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			this.add(buttonPanel, constraints);
			
			
		} else if (SharedController.getInstance().getProject().getType() == 2){
			
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			this.getImportPanels()[1] = new ImportPanel(this);
			this.add(getImportPanels()[1], constraints);
			

			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.gridwidth = 1;
			this.add(buttonPanel, constraints);
						
		}
		
		buttons = new JButton[]{getNextButton()};
		
		this.pack();
		this.setAlwaysOnTop(true);
	}
	
	public void setButtonsListener(ImportController al) {
		for (int i = 0; i < buttons.length; i++){
			buttons[i].setActionCommand(buttonEvents[i]);
			buttons[i].addActionListener(al);
		}
	}

	public void addPath(String path){
		this.paths.add(path);
	}
	
	public void addPath(int tmp, String path){
		this.paths.set(tmp, path);
	}

	public ImportPanel[] getImportPanels() {
		return importPanels;
	}

	public void setImportPanels(ImportPanel[] importPanels) {
		this.importPanels = importPanels;
	}
	
	public String getImportPanel(int tmp){
		return this.importPanels[tmp].toString();
	}
	
	public ImportPanel getSingleImportPanel(int tmp){	
		return importPanels[tmp];
	}
	
	public String getPath(int tmp){
		return this.importPanels[tmp].getPath();
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}
	
	public boolean checkPaths(){

		boolean temp = false;	
		
		try {
			if (this.getSingleImportPanel(0).getPath().toString() != null 
					&& this.getSingleImportPanel(1).getPath().toString() != null){
				temp = true;
			}
		}
		catch (NullPointerException npe) {temp = false;}
		
		return temp;
	}


}
