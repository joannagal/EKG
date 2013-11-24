package pi.data.importer.signal;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.Graph;
import pi.graph.signal.GraphToolbar;
import pi.graph.signal.GraphView;
import pi.gui.menu.MenuController;
import pi.inputs.signal.Channel;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.project.Project;
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

		this.setTitle("Choose file...");
		this.setVisible(true);
		
		setImportPanels(new ImportPanel[2]);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		this.getImportPanels()[0] = new ImportPanel();
		this.add(getImportPanels()[0], constraints);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setVisible(true);
		
		nextButton = new JButton("NEXT");
		nextButton.setVisible(true);
		
		backButton = new JButton("BACK");
		backButton.setVisible(true);
		
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);
			
		if (SharedController.getInstance().getProject().getType() == 1){
			
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			this.add(buttonPanel, constraints);
			
			
		} else if (SharedController.getInstance().getProject().getType() == 2){
			
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			this.getImportPanels()[1] = new ImportPanel();
			this.add(getImportPanels()[1], constraints);
			

			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.gridwidth = 1;
			this.add(buttonPanel, constraints);
						
		}
		
		buttons = new JButton[]{nextButton};
		
		this.pack();
				
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
	
	public String getPath(int tmp){
		return this.importPanels[tmp].getPath();
	}


}
