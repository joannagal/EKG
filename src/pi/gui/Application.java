package pi.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.nio.channels.Channel;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.data.importer.population.ImportPopulation;
import pi.data.importer.population.ImportPopulationFrame;
import pi.data.importer.population.SingleFile;
import pi.inputs.signal.ECG;
import pi.shared.SharedController;

public class Application {

	private static ECG ecg;

	public static void main(String[] args) {
		
		//ImportPopulationFrame pop = new ImportPopulationFrame();
			
		JFrame frame = new JFrame("ECG Analyzer"); 
		frame.setLocation(100, 0);
		frame.setLayout(null);
		
		SharedController.getInstance().setFrame(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
        
        MenuView menubar = new MenuView(frame);
		frame.setJMenuBar(menubar);
		MenuController menuController = new MenuController(menubar);
		frame.setMinimumSize(new Dimension(1140,1000));
		
		while(true){
			menubar.setLogItemText();
			
			if (!SharedController.getInstance().isLogged()){
				menubar.setEditable(true);
			} else {
				menubar.setEditable(true);
			}
		}
	 
	
		
	}
		
}
