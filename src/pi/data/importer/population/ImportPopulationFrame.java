package pi.data.importer.population;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;



public class ImportPopulationFrame extends JFrame{

	private ImportPopulation import1;
	private ImportPopulation import2;
	private JButton nextButton;
	private JButton[] buttons;
	private String[] buttonsEvent = new String[]{"NEXT"};
	
	
	public ImportPopulationFrame(){
		this.setAlwaysOnTop(true);
		this.setMinimumSize(new Dimension(1175, 775));
		this.setLayout(null);
		this.setVisible(true);
		
		setImport1(new ImportPopulation(this));
		getImport1().setBounds(50,50, 500, 600);
		TitledBorder title;
		title = BorderFactory.createTitledBorder("POPULATION 1");
		getImport1().setBorder(title);
		getImport1().setVisible(true);
		
		
		setImport2(new ImportPopulation(this));
		title = BorderFactory.createTitledBorder("POPULATION 2");
		getImport2().setBorder(title);
		getImport2().setBounds(600,50, 500, 600);
		getImport2().setVisible(true);
		
		nextButton = new JButton("NEXT");
		nextButton.setVisible(true);
		nextButton.setBounds(1000, 675, 100, 30 );
				
		this.add(getImport1());
		this.add(getImport2());
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

	public ImportPopulation getImport1() {
		return import1;
	}

	private void setImport1(ImportPopulation import1) {
		this.import1 = import1;
	}

	public ImportPopulation getImport2() {
		return import2;
	}

	public void setImport2(ImportPopulation import2) {
		this.import2 = import2;
	}			
	
}
