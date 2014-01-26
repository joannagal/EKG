package pi.data.importer.pair;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.dom4j.DocumentException;

import pi.data.importer.Importer;
import pi.graph.signal.GraphView;
import pi.inputs.signal.ECG;
import pi.population.Population;
import pi.population.Specimen;
import pi.project.Project;
import pi.shared.SharedController;

public class ImportPairController implements ActionListener {

	private ImportPairView view;
	private Importer importer;
	private Specimen specimen;
	private Population population;

	public ImportPairController(ImportPairView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();

		if (action.equals("OK")) {
			if ( (!this.view.getFirstPathArea().getText().isEmpty()) &&
					(!this.view.getSecondPathArea().getText().isEmpty()) )
			{
				
			
			}
			else {
				JOptionPane.showMessageDialog(null, "All fields are required, please fill in paths!");
			}
			
			
			
			
		} else if (action.equals("CANCEL")) {
			this.view.setVisible(false);
		} else if (action.equals("CHOOSE_FIRST")) {
			int returnVal = this.view.getFileChooser().showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = this.view.getFileChooser().getSelectedFile();
				this.view.getFirstPathArea().setText(file.getAbsolutePath());
			}
			
			
		} else if(action.equals("CHOOSE_SECOND")){
			int returnVal = this.view.getFileChooser().showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = this.view.getFileChooser().getSelectedFile();
				this.view.getSecondPathArea().setText(file.getAbsolutePath());
			}
			
		}
	}
}
