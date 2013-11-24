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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	//final JTextField pathField;
	private String path;
	private JTextArea area;
	
	public ImportPanel(){	
			
		JLabel fileLabel = new JLabel("File");
		fileLabel.setVisible(true);
		this.add(fileLabel);
    
		area = new JTextArea();
		area.setPreferredSize(new Dimension(150,20));
		area.setEditable(false);
		JScrollPane jsp = new JScrollPane(area);

		this.add(jsp);		
		
    	JButton button = new JButton("Select File");
 		this.add(button);
 		
	    button.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent ae) {
	        	JFileChooser fileChooser = new JFileChooser();
	        	fileChooser.setCurrentDirectory(SharedController.getInstance().getLastDirectory());
	        	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML (*.xml)","xml");
	        	fileChooser.addChoosableFileFilter(filter);
	        	fileChooser.setFileFilter(filter);
	        	int returnValue = fileChooser.showDialog(null, "Open file");
	          
	        	if (returnValue == JFileChooser.APPROVE_OPTION) {
	        		File selectedFile = fileChooser.getSelectedFile();
	        		String path = selectedFile.getAbsolutePath();
	        		SharedController.getInstance().setLastDirectory(fileChooser.getSelectedFile());
	        		setText(path, area);
	        		System.out.println(path);
	        		setPath(path);
	        	}
	        }
	      });

	}
	
	public void setText(String text, JTextArea area){
    	area.setText(text);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}
	
	
	
	
}
