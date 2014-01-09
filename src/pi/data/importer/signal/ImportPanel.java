package pi.data.importer.signal;

import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
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
	private String path;
	private JTextArea area;
	private JButton button;
	private ImporterView importView;
	
	public ImportPanel(final ImporterView importView){	
		
		JLabel fileLabel = new JLabel("Specimen");
		fileLabel.setVisible(true);
		this.add(fileLabel);
    
		area = new JTextArea();
		area.setPreferredSize(new Dimension(150,20));
		area.setEditable(false);
		JScrollPane jsp = new JScrollPane(area);

		this.add(jsp);		
		
    	button = new JButton("Choose");
 		this.add(button);
 		
	    button.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent ae) {
	        	JFileChooser fileChooser = new JFileChooser();
	        	fileChooser.setCurrentDirectory(SharedController.getInstance().getLastDirectory());
	        	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML (*.xml)","xml");
	        	fileChooser.addChoosableFileFilter(filter);
	        	fileChooser.setFileFilter(filter);
	        	int returnValue = fileChooser.showDialog(getContext(), "Choose specimen...");
	          
	        	if (returnValue == JFileChooser.APPROVE_OPTION) {
	        		File selectedFile = fileChooser.getSelectedFile();
	        		String path = selectedFile.getAbsolutePath();
	        		SharedController.getInstance().setLastDirectory(fileChooser.getSelectedFile());
	        		Importer importer;
					try {
						importer = new Importer(path);
		        		String name = importer.getName();
		        		setText(name, area);

					} catch (DocumentException e) {
						e.printStackTrace();
					}
	        		System.out.println(path);
	        		setPath(path);
	        		
	        		int type = SharedController.getInstance().getProject().getType();
	        		if (type == 1){
		        		importView.getNextButton().setEnabled(true);
	        		} else if (type == 2){
	        			if (importView.checkPaths() == true){
			        		importView.getNextButton().setEnabled(true);
	        			}
	        		}
	        		
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
	
	public ImportPanel getContext(){
		return this;
	}
	

	
	
	
}
