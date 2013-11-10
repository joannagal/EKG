package pi.data.importer.population;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;



public class ImportPopulationFrame extends JFrame{

	private ImportPopulation import1;
	private ImportPopulation import2;
	private JButton nextButton;
	private JButton[] buttons;
	private String[] buttonsEvent = new String[]{"NEXT"};
	
	
	public ImportPopulationFrame(){
		
		this.setMinimumSize(new Dimension(1175, 775));
		this.setLayout(null);
		this.setVisible(true);
		
		import1 = new ImportPopulation(this);
		import1.setBounds(50,50, 500, 600);
		TitledBorder title;
		title = BorderFactory.createTitledBorder("POPULATION 1");
		import1.setBorder(title);
		import1.setVisible(true);
		
		
		import2 = new ImportPopulation(this);
		title = BorderFactory.createTitledBorder("POPULATION 2");
		import2.setBorder(title);
		import2.setBounds(600,50, 500, 600);
		import2.setVisible(true);
		
		nextButton = new JButton("NEXT");
		nextButton.setVisible(true);
		nextButton.setBounds(1000, 675, 100, 30 );
				
		this.add(import1);
		this.add(import2);
		this.add(nextButton);
		buttons = new JButton[]{nextButton};
		
		pack();
	}
	
	public void setButtonListener(ActionListener al) {
		for (int i = 0; i < buttons.length; i++){
			buttons[i].setActionCommand(buttonsEvent[i]);
			buttons[i].addActionListener(al);
		}
	}			
	
}
