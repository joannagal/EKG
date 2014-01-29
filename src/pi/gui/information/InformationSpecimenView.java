package pi.gui.information;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InformationSpecimenView extends JDialog{
	
	private JLabel firstnameLabel;
	private JLabel surnameLabel;
	private JLabel birthLabel;
	private JLabel activityLabel;
	private JLabel metadonLabel;
	private JLabel hivLabel;
	private JLabel goodMoodLabel;
	private JLabel durationMoodLabel;
	
	private JTextField firstnameField;
	private JTextField surnameField;
	private JTextField birthField;
	private JTextField activityField;
	private JTextField metadonField;
	private JTextField hivField;
	private JTextField goodMoodField;
	private JTextField durationMoodField;
	
	private JPanel namePanel;
	private JPanel surnamePanel;
	
	public InformationSpecimenView(){
		firstnameLabel = new JLabel("First name: ");
		firstnameLabel.setPreferredSize(new Dimension(100,20));
		firstnameField = new JTextField();
		firstnameField.setPreferredSize(new Dimension(250, 20));
		firstnameField.setEditable(true);
		namePanel = new JPanel();
		namePanel.setVisible(true);
		namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		namePanel.add(firstnameLabel);
		namePanel.add(firstnameField);
		
		surnameLabel = new JLabel("Surname");
		birthLabel = new JLabel("Birth date:");
		activityLabel = new JLabel(""); //to be done
		metadonLabel = new JLabel(); //to be done
		hivLabel = new JLabel(); //to be done
		
		
		
		
		
	}

}
