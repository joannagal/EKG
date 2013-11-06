package pi.data.importer.population;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImportPopulation extends JFrame {
	
	private JPanel panelWithPanels;
	private JButton add;
	private int gridy;
	private ArrayList<String> list;
	//private PopulationViewController controller;
	
	
	
	public ImportPopulation(){
		
		this.setGridy(0);
		this.setResizable(false);
		list = new ArrayList<>();
		
		this.setLayout(new BorderLayout());

		panelWithPanels = new JPanel(new GridBagLayout());
		this.add(panelWithPanels, BorderLayout.CENTER);
		this.setVisible(true);
		
		add = new JButton("ADD");
		this.add(add, BorderLayout.PAGE_END);
		
		
		class MyActionListener implements ActionListener
		{
			private ImportPopulation parent;
			
			public MyActionListener(ImportPopulation parent)
			{
				this.parent = parent;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				gridy++;
				SingleFile test = new SingleFile(parent, panelWithPanels);
				GridBagConstraints testCons = new GridBagConstraints();
				testCons.fill = GridBagConstraints.HORIZONTAL;
				testCons.gridx = 0;
				testCons.gridy = getGridy();
				testCons.gridwidth = 1;
				
				panelWithPanels.add(test, testCons);
								
				test.setNumber(Integer.toString(getGridy()));
				test.setFileLabelText(Integer.toString(getGridy()));
				list.add(Integer.toString(getGridy()));
				pack();
				
				System.out.println("Ilosc elementów na liscie = " + list.size());
			}
			
		}
		
		MyActionListener addAction = new MyActionListener(this);
		add.addActionListener(addAction);


		this.setBounds(500, 500, 500, 500);
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
		
		for (int i = 0; i < list.size(); i++){
			System.out.println("Jestem i = " + i + "i.getNumber = " + list.get(i));
		}
		
		list.remove(tmp);
		
		for (int i = 0; i < list.size(); i++){
			System.out.println("Jestem i = " + i + "i.getNumber = " + list.get(i));
		}
	}
	
	
}
