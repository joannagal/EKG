package pi.data.importer.population;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class SingleFile extends JPanel {

	private JButton upButton;
	private JButton downButton;
	private JButton deleteButton;
	private JLabel fileLabel;
	private String path;
	private ImportPopulation importPopulation;
	private JPanel container;
	
	public SingleFile(ImportPopulation pop, final JPanel container, String path){
		
		this.path = path;
		this.importPopulation = pop;
		this.container = container;
		this.setVisible(true);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		upButton = new JButton("up");
		downButton = new JButton("down");
		deleteButton = new JButton("delete");
		fileLabel = new JLabel(shortPath(path));
		this.add(fileLabel);
		fileLabel.setVisible(true);
		fileLabel.setSize(75, 15);
		this.setPreferredSize(new Dimension(400,38));
		this.setBorder(new LineBorder(Color.GREEN, 1));
		
		this.add(upButton);	
		upButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int tmp = importPopulation.getIndex(getString());
				importPopulation.up(tmp);
			}
		});
		
		
		this.add(downButton);
		downButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int tmp = importPopulation.getIndex(getString());
				importPopulation.down(tmp);
			}
		});
		
		this.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int tmp = importPopulation.getIndex(getString());
				importPopulation.delete(tmp);
				
				container.remove(((JButton)(e.getSource())).getParent());
				importPopulation.setGridy(importPopulation.getGridy() - 1);
				importPopulation.validate();
				importPopulation.repaint();
			}
		});
		
		
	}
	
	
	public void setFileLabelText(String string){
		fileLabel.setText(string);
	}

	public String getString() {
		return path;
	}

	public void setString(String path) {
		this.path = path;
	}
	
	public String shortPath(String str){
		return str.substring(str.length() - 30);
	}

}
