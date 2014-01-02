package pi.data.importer.open;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import pi.data.importer.signal.ImportPanel;

public class OpenPopulationView extends JDialog {
	
	
	private String[] actions = new String[]{"OPEN", "CANCEL"};
	private JButton[] buttons;
	private ImportPanel importPanel;
	private GridBagConstraints constraints;
	
	public OpenPopulationView(){
		this.setAlwaysOnTop(true);
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.setVisible(true);
		this.setTitle("Open Project...");	
		this.setBounds(400, 200, 350, 200);
		
		setImportPanel(new ImportPanel());
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;		
		this.add(getImportPanel(), constraints);
		
		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton openButton = new JButton("OPEN");
		JButton cancelButton = new JButton("CANCEL");
		panel.add(cancelButton);
		panel.add(openButton);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(panel, constraints);
		
		buttons = new JButton[]{openButton, cancelButton};

	}
	
	public void addActionListener(OpenPopulationController controller){
		for (int i = 0; i < buttons.length; i++){
			buttons[i].setActionCommand(actions[i]);
			buttons[i].addActionListener(controller);
		}
	}

	public ImportPanel getImportPanel() {
		return importPanel;
	}

	public void setImportPanel(ImportPanel importPanel) {
		this.importPanel = importPanel;
	}



	
}
