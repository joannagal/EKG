package pi.data.importer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.dom4j.DocumentException;

import pi.graph.signal.Graph;
import pi.graph.signal.GraphToolbar;
import pi.graph.signal.GraphView;
import pi.gui.project.ProjectView;
import pi.inputs.signal.Channel;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.project.Project;
import pi.shared.SharedController;



public class ImporterView extends JDialog{
	
	private JFileChooser fileChooser;
	
	public ImporterView() {
		this.setTitle("Choose file...");
		setVisible(true);
		
		JPanel panel = new JPanel();		
		panel.setVisible(true);
		this.add(panel);
		
		fileChooser = new JFileChooser();
		
	    JLabel fileLabel = new JLabel("File");
	    fileLabel.setVisible(true);
	    panel.add(fileLabel);
	    
	    final JTextField pathField = new JTextField();
	    panel.add(pathField);
	    pathField.setVisible(true);
	    pathField.setPreferredSize(new Dimension(150,20));
	    pathField.setEditable(false);
	    pathField.setEnabled(true);
		
		
	    JButton button = new JButton("Select File");
		panel.add(button);
	 

	    button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
	        	JFileChooser fileChooser = new JFileChooser();
	        	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML (*.xml)","xml");
	        	fileChooser.addChoosableFileFilter(filter);
	        	fileChooser.setFileFilter(filter);
	        	int returnValue = fileChooser.showDialog(null, "Open file");
	          
	        	if (returnValue == JFileChooser.APPROVE_OPTION) {
	        		File selectedFile = fileChooser.getSelectedFile();
	        		String path = selectedFile.getAbsolutePath();
	        		Importer importer;
	        		
					try {
						
						importer = new Importer(path);
						ArrayList<ECG> temp = importer.importSignals();
						
		        		Specimen specimen = new Specimen();
		        		specimen.setBefore(temp.get(0));
		        		ArrayList <Specimen> pop = new ArrayList<>(1);
		        		pop.add(specimen);
		        		specimen.setDetails(importer.getAttributes());
		        		
		        		Population population = new Population();
		        		population.setSpecimen(pop);
		        		
						Project project = new Project();
						project.setFirstPopulation(population);
						project.setType(1);
						SharedController.getInstance().setProject(project);
						
						ProjectView view = new ProjectView();
						
						view.setBounds(80, 80, 1000, 800);
						//view.setSize(new Dimension(500,500));
						//view.setLocation(100, 100);
						SharedController.getInstance().getFrame().add(view);

		        			        		
					} catch (DocumentException e) {
						e.printStackTrace();
					}
	        		dispose();
	        		
	        		
	        	}
	        }
	      });

	   
	}
	
	public void setText(String text, JTextField field){
    	field.setText(text);
    }
}
