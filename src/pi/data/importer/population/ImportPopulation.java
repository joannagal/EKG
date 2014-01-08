package pi.data.importer.population;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.shared.SharedController;

public class ImportPopulation extends JPanel {
	
	private JPanel panelWithPanels;
	private JButton add;
	private int gridy;
	public ArrayList<SingleFile> list;
	private ImportPopulationFrame parent;
	
	
	public ImportPopulation(ImportPopulationFrame parent){
				
		this.parent = parent;
		this.setGridy(0);
		list = new ArrayList<>();
		
		this.setLayout(new BorderLayout());

		panelWithPanels = new JPanel(new GridBagLayout());
		this.add(panelWithPanels, BorderLayout.CENTER);
		this.setVisible(true);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		add = new JButton("ADD");
		buttons.add(add);
		this.add(buttons, BorderLayout.PAGE_END);
		
		
		class MyActionListener implements ActionListener
		{
			private ImportPopulation parent;
			
			public MyActionListener(ImportPopulation parent)
			{
				this.setParent(parent);
			}

			
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
	        	fileChooser.setCurrentDirectory(SharedController.getInstance().getLastDirectory());
	        	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML (*.xml)","xml");
	        	fileChooser.addChoosableFileFilter(filter);
	        	fileChooser.setFileFilter(filter);
	        	int returnValue = fileChooser.showDialog(getContext(), "Open file");
	        	
	          
	        	if (returnValue == JFileChooser.APPROVE_OPTION) {
	        		File selectedFile = fileChooser.getSelectedFile();
	        		String path = selectedFile.getAbsolutePath();
	        		Importer importer;
	        		String[] temp = null;
	        		
					try {
						importer = new Importer(path);
						temp = new String[4];
		        		temp = importer.getAttributes();

					} catch (DocumentException e1) {
						e1.printStackTrace();
					}
	        		System.out.println(path);
	        		String name = temp[0] + " " + temp[1];
	        		
	        		gridy++;
	        		SingleFile test = new SingleFile(getParent(), panelWithPanels, name);
					GridBagConstraints testCons = new GridBagConstraints();
					testCons.fill = GridBagConstraints.HORIZONTAL;
					testCons.gridx = 0;
					System.out.println(getGridy());
					testCons.gridy = getGridy();
					testCons.gridwidth = 1;
					
					panelWithPanels.add(test, testCons);
					
					test.setString(path);
					list.add(test);
					
	        		SharedController.getInstance().setLastDirectory(fileChooser.getSelectedFile());
		        		
	        	}
	        	
	        	getParent().validate();
			}

			public ImportPopulation getParent() {
				return parent;
			}

			public void setParent(ImportPopulation parent) {
				this.parent = parent;
			}


					
		}
		
		MyActionListener addAction = new MyActionListener(this);
		add.addActionListener(addAction);
		
		this.revalidate();
		this.repaint();
	}


	public int getGridy() {
		return gridy;
	}


	public void setGridy(int gridy) {
		this.gridy = gridy;
	}
	

	
	public int getIndex(String string){
		
		int tmp = 0;
		for (int i = 0; i <= list.size(); i++){
			if (list.get(i).getString().equals(string)){
				tmp = i;
				break;
			}
		}
		
		return tmp;
	}
	
	public void up(int tmp){
		if (tmp > 0){
			String a = list.get(tmp).getString();
			String b = list.get(tmp-1).getString();
		
			list.get(tmp).setString(b);
			list.get(tmp).setFileLabelText(b.substring(b.length() - 30));
		
			list.get(tmp - 1).setString(a);
			list.get(tmp - 1).setFileLabelText(a.substring(a.length() - 30));
		
			this.validate();
			this.repaint();
		}
	}
		
	public void down(int tmp){
		
		if (tmp < list.size() - 1){
			String a = list.get(tmp).getString();
			String b = list.get(tmp + 1).getString();
			list.get(tmp).setString(b);
			list.get(tmp).setFileLabelText(b.substring(b.length() - 30));

			list.get(tmp + 1).setString(a);
			list.get(tmp + 1).setFileLabelText(a.substring(a.length() - 30));
			
			this.validate();
			this.repaint();
		}
	}
	
	public void delete(int tmp){
		list.remove(tmp);
	}
	
	public void add(int tmp){
		this.add(tmp);
	}
	
	
	
	 public ImportPopulation getContext(){
		 return this;
	 }
	
}
