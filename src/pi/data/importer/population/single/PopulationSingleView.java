package pi.data.importer.population.single;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import pi.shared.SharedController;


public class PopulationSingleView extends JDialog{

	private PopulationSingleController controller;
	private JTabbedPane tabbedPane;
	private DefaultListModel<String> firstBeforeListModel;
	private DefaultListModel<String> secondBeforeListModel;
	private JList<String> firstBeforeList;
	private JList<String> secondBeforeList;
	private JScrollPane firstBeforePane;
	private JScrollPane secondBeforePane;
	private LinkedList <File> firstBeforeFiles;
	private LinkedList <File> secondBeforeFiles;
	private JButton addButton;
	private JButton delButton;
	private JButton upButton;
	private JButton downButton;
	private JButton okButton;
	private JButton cancelButton;
	private JFileChooser fileChooser;
	
	public PopulationSingleView(){
		this.controller = new PopulationSingleController(this);
		tabbedPane = new JTabbedPane();
		firstBeforeListModel = new DefaultListModel<String>();
		firstBeforeList = new JList<String>(firstBeforeListModel);
		firstBeforePane = new JScrollPane(firstBeforeList,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		firstBeforeFiles = new LinkedList<File>();
		
		secondBeforeListModel = new DefaultListModel<String>();
		secondBeforeList = new JList<String>(secondBeforeListModel);
		secondBeforePane = new JScrollPane(secondBeforeList,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		secondBeforeFiles = new LinkedList<File>();
		
		addButton = new JButton("SELECT");
		addButton.setActionCommand("ADD");
		addButton.addActionListener(this.controller);
		
		delButton = new JButton("DELETE");
		delButton.addActionListener(this.controller);
		delButton.setActionCommand("DELETE");
		
		upButton = new JButton("UP");
		upButton.addActionListener(this.controller);
		upButton.setActionCommand("UP");
		
		downButton = new JButton("DOWN");
		downButton.addActionListener(this.controller);
		downButton.setActionCommand("DOWN");
		
		okButton = new JButton("OK");
		okButton.addActionListener(this.controller);
		okButton.setActionCommand("OK");
		
		cancelButton = new JButton("CANCEL");
		cancelButton.addActionListener(this.controller);
		cancelButton.setActionCommand("CANCEL");
		
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(SharedController.getInstance().getLastDirectory());
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML (*.xml)","xml");
    	fileChooser.addChoosableFileFilter(filter);
    	fileChooser.setFileFilter(filter);
		
		
		this.setTitle("Create Two Populations");
		this.setSize(400, 500);
		this.setVisible(true);
		this.setLocation(100, 100);

		JPanel firstPanel = new JPanel();
		firstPanel.add(firstBeforePane);
		this.tabbedPane.addTab("First population", firstPanel);
		this.add(this.tabbedPane);
		
		JPanel secondPanel = new JPanel();
		secondPanel.add(secondBeforePane);
		this.tabbedPane.addTab("Second population", secondPanel);
		this.add(this.tabbedPane);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
		
		this.setResizable(false);
		this.setLayout(null);

		this.tabbedPane.setBounds(0, 0, 400, 380);

		firstPanel.setLayout(null);
		this.firstBeforePane.setBounds(0, 0, 390, 345);

		secondPanel.setLayout(null);
		this.secondBeforePane.setBounds(0, 0, 390, 345);

		this.setResizable(false);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		controlPanel.setVisible(true);
		controlPanel.add(addButton);
		controlPanel.add(delButton);
		controlPanel.add(upButton);
		controlPanel.add(downButton);
		controlPanel.setBounds(0,385, 400, 50);
		this.add(controlPanel);
		

		this.okButton.setBounds(283, 435, 100, 30);
		this.cancelButton.setBounds(17, 435, 100, 30);

		this.add(this.okButton);
		this.add(this.cancelButton);
		
		
	}
	
	public ArrayList<ArrayList<String>> getPaths()
	{
		ArrayList<ArrayList<String>> paths = new ArrayList<ArrayList<String>>(2);

		int size = this.firstBeforeFiles.size();
		ArrayList<String> firstBefore = new ArrayList<String>(size);
		Iterator <File> it = this.firstBeforeFiles.iterator();
		File file;
		
		while (it.hasNext())
		{
			file = it.next();
			System.out.println(file.getAbsolutePath());
			firstBefore.add(file.getAbsolutePath());
		}	
		paths.add(firstBefore);

		size = this.secondBeforeFiles.size();
		ArrayList<String> secondBefore = new ArrayList<String>(size);
		it = this.secondBeforeFiles.iterator();
		
		while (it.hasNext())
		{
			file = it.next();
			System.out.println(file.getAbsolutePath());
			secondBefore.add(file.getAbsolutePath());
		}	
		paths.add(secondBefore);

		return paths;
	}

	public DefaultListModel<String> getCurrentListModel()
	{
		int tab = this.tabbedPane.getSelectedIndex();
		if (tab == -1)
			return null;
		else if (tab == 0)
			return this.firstBeforeListModel;
		else
			return this.secondBeforeListModel;
	}
	
	public LinkedList<File> getCurrentFileList()
	{
		int tab = this.tabbedPane.getSelectedIndex();
		if (tab == -1)
			return null;
		else if (tab == 0)
			return this.firstBeforeFiles;
		else
			return this.secondBeforeFiles;
	}

	public JList<String> getCurrentList()
	{
		int tab = this.tabbedPane.getSelectedIndex();
		if (tab == -1)
			return null;
		else if (tab == 0)
			return this.firstBeforeList;
		else
			return this.secondBeforeList;
	}

	public JFileChooser getFc()
	{
		return fileChooser;
	}

	public LinkedList <File> getFirstBeforeFiles()
	{
		return firstBeforeFiles;
	}

	public void setFirstBeforeFiles(LinkedList <File> firstBeforeFiles)
	{
		this.firstBeforeFiles = firstBeforeFiles;
	}

	public LinkedList <File> getSecondBeforeFiles()
	{
		return secondBeforeFiles;
	}

	public void setSecondBeforeFiles(LinkedList <File> secondBeforeFiles)
	{
		this.secondBeforeFiles = secondBeforeFiles;
	}
}
