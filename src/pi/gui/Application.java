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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.data.importer.pair.ImportPairController;
import pi.data.importer.pair.ImportPairView;
import pi.data.importer.population.ImportPopulation;
import pi.data.importer.population.ImportPopulationFrame;
import pi.data.importer.population.SingleFile;
import pi.data.importer.signal.ImportSingleView;
import pi.gui.menu.MenuController;
import pi.gui.menu.MenuView;
import pi.gui.toolbar.ProjectToolbar;
import pi.gui.toolbar.ProjectToolbarController;
import pi.inputs.signal.ECG;
import pi.shared.SharedController;

public class Application {

	private static ECG ecg;

	public static void main(String[] args) {
		
		OurFrame ourFrame = new OurFrame();
		
		while(true){
			ourFrame.getMenubar().setLogItemText();
			
			if (!SharedController.getInstance().isLogged()){
				ourFrame.getMenubar().setEditable(true);
			} else {
				ourFrame.getMenubar().setEditable(true);
			}
		}
		 
	
		
	}
		
}
