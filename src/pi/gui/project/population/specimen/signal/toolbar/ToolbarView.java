package pi.gui.project.population.specimen.signal.toolbar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolbarView extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JButton specimenButton = new JButton("A");
	private JButton searchButton = new JButton("B");
	private JButton raportButton = new JButton("C");

	public ToolbarView()
	{
		this.setMinimumSize(new Dimension(50, 50));

		/*try
		{
			Image img = ImageIO.read(getClass().getResource(
					"/resources/icon-info.png"));
			this.specimenButton.setIcon(new ImageIcon(img));
		} catch (IOException ex)
		{
		}
		
		try
		{
			Image img = ImageIO.read(getClass().getResource(
					"/resources/icon-search.png"));
			this.searchButton.setIcon(new ImageIcon(img));
		} catch (IOException ex)
		{
		}
		
		try
		{
			Image img = ImageIO.read(getClass().getResource(
					"/resources/icon-split.png"));
			this.raportButton.setIcon(new ImageIcon(img));
		} catch (IOException ex)
		{
		}*/


		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTHWEST;

		c.gridy = 0;
		this.add(this.specimenButton, c);
		c.gridy = 1;
		this.add(this.searchButton, c);
		c.gridy = 2;
		c.weighty = 1.0d;
		this.add(this.raportButton, c);
	}

}
