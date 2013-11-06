package pi.gui.project.population.list;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class ListView extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JLabel populationLabel = new JLabel("");
	
	private DefaultListModel  <String> specimenLabels = new DefaultListModel <String> ();
	private JList <String> specimen = new JList <String> (getSpecimenLabels());
	
	private JButton addButton = new JButton("Add");
	private JButton delButton = new JButton("Delete");
	private JButton upButton = new JButton("Up");
	private JButton downButton = new JButton("Down");
	
	public ListView()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setMinimumSize(new Dimension(150, 150));
		
		getPopulationLabel().setText("POPULATION");
		
		specimenLabels.addElement("ASD");
		specimenLabels.addElement("ASD");
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0d;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(this.populationLabel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.weighty = 0.5d;
		this.add(this.specimen, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weighty = 0.0d;
		c.weightx = 0.5d;
		this.add(this.addButton, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.5d;
		c.weighty = 0.0d;
		this.add(this.delButton, c);
		
		c.gridx = 0;
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.5d;
		c.weighty = 0.0d;
		this.add(this.upButton, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0.5d;
		c.weighty = 0.0d;
		this.add(this.downButton, c);
	}


	public DefaultListModel <String> getSpecimenLabels()
	{
		return specimenLabels;
	}

	public void setSpecimenLabels(DefaultListModel <String> specimenLabels)
	{
		this.specimenLabels = specimenLabels;
	}

	public JLabel getPopulationLabel()
	{
		return populationLabel;
	}

	public void setPopulationLabel(JLabel populationLabel)
	{
		this.populationLabel = populationLabel;
	}

	public JButton getAddButton()
	{
		return addButton;
	}


	public void setAddButton(JButton addButtun)
	{
		this.addButton = addButtun;
	}


	public JButton getDelButton()
	{
		return delButton;
	}


	public void setDelButton(JButton delButtun)
	{
		this.delButton = delButtun;
	}

	public JButton getDownButton()
	{
		return downButton;
	}

	public void setDownButton(JButton downButton)
	{
		this.downButton = downButton;
	}

	public JButton getUpButton()
	{
		return upButton;
	}

	public void setUpButton(JButton upButton)
	{
		this.upButton = upButton;
	}
}
