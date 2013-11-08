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

import pi.data.importer.Importer;

public class ImportPopulation extends JPanel {
	
	private JPanel panelWithPanels;
	private JButton add;
	private int gridy;
	private ArrayList<String> list;
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
				this.parent = parent;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
	        	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML (*.xml)","xml");
	        	fileChooser.addChoosableFileFilter(filter);
	        	fileChooser.setFileFilter(filter);
	        	int returnValue = fileChooser.showDialog(null, "Open file");
	          
	        	if (returnValue == JFileChooser.APPROVE_OPTION) {
	        		File selectedFile = fileChooser.getSelectedFile();
	        		String path = selectedFile.getAbsolutePath();
	        		System.out.println(path);
	        		
	        		gridy++;
	        		SingleFile test = new SingleFile(parent, panelWithPanels, path);
					GridBagConstraints testCons = new GridBagConstraints();
					testCons.fill = GridBagConstraints.HORIZONTAL;
					testCons.gridx = 0;
					System.out.println(getGridy());
					testCons.gridy = getGridy();
					testCons.gridwidth = 1;
					
					panelWithPanels.add(test, testCons);
					
					test.setString(path);
					list.add(path);
		        		
	        	}
	        	
	        	parent.validate();
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
		
		for (int i = 1; i <= list.size(); i ++){
			if(list.get(i).equals(string)){
				tmp = i;
				break;
			}
		}
		
		return tmp;
	}
	
	public void up(int tmp){
			
		if (tmp > 0){
			String a = list.get(tmp);
			String b = list.get(tmp - 1);
			list.set(tmp, b);
			list.set(tmp-1, a);
		}
		
	}
	
	public void down(int tmp){
		
		if (tmp < list.size()){
			String a = list.get(tmp);
			String b = list.get(tmp + 1);
			list.set(tmp, b);
			list.set(tmp + 1, a);
		}
	}
	
	public void delete(int tmp){

		list.remove(tmp);

	}
	
	
}
