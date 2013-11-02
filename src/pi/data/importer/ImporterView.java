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
import pi.inputs.signal.Channel;
import pi.inputs.signal.ECG;
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
	        		GraphView graphView = new GraphView(path);
	        		dispose();
	        	}
	        }
	      });

	   
	}
	
	public void setText(String text, JTextField field){
    	field.setText(text);
    }
}
