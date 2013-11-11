package pi.data.importer.signal;

import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.GraphView;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.project.Project;
import pi.shared.SharedController;

public class ImportPanel extends JPanel{

	private JFileChooser fileChooser;
	private JLabel fileLabel;
	final JTextField pathField;
	private String path;
	
	public ImportPanel(){	
		
		fileChooser = new JFileChooser();
	
		JLabel fileLabel = new JLabel("File");
		fileLabel.setVisible(true);
		this.add(fileLabel);
    
		pathField = new JTextField();
		pathField.setVisible(true);
		pathField.setPreferredSize(new Dimension(150,20));
    	pathField.setEditable(false);
    	pathField.setEnabled(true);
    	this.add(pathField);
    	
    	
    	JButton button = new JButton("Select File");
 		this.add(button);
 		
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
	        		System.out.println(path);
	        		setPath(path);
	        	}
	        }
	      });

	}
	
	public void setText(String text, JTextField field){
    	field.setText(text);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
}
