package pi.statistics.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

public class StatisticWindowView extends JFrame {

    private static final long serialVersionUID = 3655555564299592830L;
    private JButton[] buttonArray;
    private String[] itemEvent = new String[] { "CANCEL", "REPORT", "COUNT" };

    public StatisticWindowView() {
	this.setTitle("Analysis");
	this.setSize(630, 426);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 184, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 240, 14, 0 };
	gridBagLayout.columnWeights = new double[] { 1.0,
		Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0,
		Double.MIN_VALUE };
	getContentPane().setLayout(gridBagLayout);

	JPanel panel_2 = new JPanel();
	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	gbc_panel_2.insets = new Insets(0, 0, 5, 0);
	gbc_panel_2.fill = GridBagConstraints.BOTH;
	gbc_panel_2.gridx = 0;
	gbc_panel_2.gridy = 1;
	getContentPane().add(panel_2, gbc_panel_2);
	GridBagLayout gbl_panel_2 = new GridBagLayout();
	gbl_panel_2.columnWidths = new int[] { 186, 287, 0 };
	gbl_panel_2.rowHeights = new int[] { 268, 0 };
	gbl_panel_2.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
	gbl_panel_2.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	panel_2.setLayout(gbl_panel_2);

	JPanel panel_3 = new JPanel();
	GridBagConstraints gbc_panel_3 = new GridBagConstraints();
	gbc_panel_3.insets = new Insets(0, 0, 0, 5);
	gbc_panel_3.fill = GridBagConstraints.BOTH;
	gbc_panel_3.gridx = 0;
	gbc_panel_3.gridy = 0;
	panel_2.add(panel_3, gbc_panel_3);
	GridBagLayout gbl_panel_3 = new GridBagLayout();
	gbl_panel_3.columnWidths = new int[] { 0, 0, 0 };
	gbl_panel_3.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
	gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
		0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	panel_3.setLayout(gbl_panel_3);
	panel_3.setBorder(BorderFactory.createTitledBorder("Waves"));

	JCheckBox chckbxTwave = new JCheckBox("T-wave");
	GridBagConstraints gbc_chckbxTwave = new GridBagConstraints();
	gbc_chckbxTwave.anchor = GridBagConstraints.WEST;
	gbc_chckbxTwave.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxTwave.gridx = 1;
	gbc_chckbxTwave.gridy = 0;
	panel_3.add(chckbxTwave, gbc_chckbxTwave);

	JCheckBox chckbxPwave = new JCheckBox("P-wave");
	GridBagConstraints gbc_chckbxPwave = new GridBagConstraints();
	gbc_chckbxPwave.anchor = GridBagConstraints.WEST;
	gbc_chckbxPwave.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxPwave.gridx = 1;
	gbc_chckbxPwave.gridy = 1;
	panel_3.add(chckbxPwave, gbc_chckbxPwave);

	JCheckBox chckbxUwave = new JCheckBox("U-wave");
	GridBagConstraints gbc_chckbxUwave = new GridBagConstraints();
	gbc_chckbxUwave.anchor = GridBagConstraints.WEST;
	gbc_chckbxUwave.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxUwave.gridx = 1;
	gbc_chckbxUwave.gridy = 2;
	panel_3.add(chckbxUwave, gbc_chckbxUwave);

	JCheckBox chckbxPrInterval = new JCheckBox("PR interval");
	GridBagConstraints gbc_chckbxPrInterval = new GridBagConstraints();
	gbc_chckbxPrInterval.anchor = GridBagConstraints.WEST;
	gbc_chckbxPrInterval.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxPrInterval.gridx = 1;
	gbc_chckbxPrInterval.gridy = 3;
	panel_3.add(chckbxPrInterval, gbc_chckbxPrInterval);

	JCheckBox chckbxNewCheckBox = new JCheckBox("PR segment");
	GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
	gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
	gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxNewCheckBox.gridx = 1;
	gbc_chckbxNewCheckBox.gridy = 4;
	panel_3.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

	JCheckBox chckbxStSegment = new JCheckBox("ST segment");
	GridBagConstraints gbc_chckbxStSegment = new GridBagConstraints();
	gbc_chckbxStSegment.anchor = GridBagConstraints.WEST;
	gbc_chckbxStSegment.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxStSegment.gridx = 1;
	gbc_chckbxStSegment.gridy = 5;
	panel_3.add(chckbxStSegment, gbc_chckbxStSegment);

	JCheckBox chckbxStInterval = new JCheckBox("ST interval");
	GridBagConstraints gbc_chckbxStInterval = new GridBagConstraints();
	gbc_chckbxStInterval.anchor = GridBagConstraints.WEST;
	gbc_chckbxStInterval.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxStInterval.gridx = 1;
	gbc_chckbxStInterval.gridy = 6;
	panel_3.add(chckbxStInterval, gbc_chckbxStInterval);

	JCheckBox chckbxQtInterval = new JCheckBox("QT interval");
	GridBagConstraints gbc_chckbxQtInterval = new GridBagConstraints();
	gbc_chckbxQtInterval.anchor = GridBagConstraints.WEST;
	gbc_chckbxQtInterval.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxQtInterval.gridx = 1;
	gbc_chckbxQtInterval.gridy = 7;
	panel_3.add(chckbxQtInterval, gbc_chckbxQtInterval);

	JCheckBox chckbxQrsSegment = new JCheckBox("QRS complex");
	GridBagConstraints gbc_chckbxQrsSegment = new GridBagConstraints();
	gbc_chckbxQrsSegment.anchor = GridBagConstraints.WEST;
	gbc_chckbxQrsSegment.gridx = 1;
	gbc_chckbxQrsSegment.gridy = 8;
	panel_3.add(chckbxQrsSegment, gbc_chckbxQrsSegment);

	JPanel panel_4 = new JPanel();
	GridBagConstraints gbc_panel_4 = new GridBagConstraints();
	gbc_panel_4.fill = GridBagConstraints.BOTH;
	gbc_panel_4.gridx = 1;
	gbc_panel_4.gridy = 0;
	panel_2.add(panel_4, gbc_panel_4);
	GridBagLayout gbl_panel_4 = new GridBagLayout();
	gbl_panel_4.columnWidths = new int[] { 0, 0 };
	gbl_panel_4.rowHeights = new int[] { 0, 0, 0, 0 };
	gbl_panel_4.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panel_4.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
	panel_4.setLayout(gbl_panel_4);
	
	JLabel lblParameters = new JLabel("Additional parameters");
	GridBagConstraints gbc_lblParameters = new GridBagConstraints();
	gbc_lblParameters.anchor = GridBagConstraints.WEST;
	gbc_lblParameters.insets = new Insets(0, 0, 5, 0);
	gbc_lblParameters.gridx = 0;
	gbc_lblParameters.gridy = 0;
	panel_4.add(lblParameters, gbc_lblParameters);
	//panel_4.setBorder(BorderFactory.createTitledBorder("Parameters"));
	
	JPanel panel_5 = new JPanel();
	GridBagConstraints gbc_panel_5 = new GridBagConstraints();
	gbc_panel_5.insets = new Insets(0, 0, 5, 0);
	gbc_panel_5.fill = GridBagConstraints.BOTH;
	gbc_panel_5.gridx = 0;
	gbc_panel_5.gridy = 1;
	panel_4.add(panel_5, gbc_panel_5);
	panel_5.setBorder(BorderFactory.createTitledBorder("T-student:"));
	
	JPanel panel_6 = new JPanel();
	GridBagConstraints gbc_panel_6 = new GridBagConstraints();
	gbc_panel_6.fill = GridBagConstraints.BOTH;
	gbc_panel_6.gridx = 0;
	gbc_panel_6.gridy = 2;
	panel_4.add(panel_6, gbc_panel_6);
	panel_6.setBorder(BorderFactory.createTitledBorder("Wilcoxon:"));
	
	JPanel panel = new JPanel();
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 0;
	gbc_panel.gridy = 2;
	getContentPane().add(panel, gbc_panel);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[] { 82, 103, 74, 0, 0 };
	gbl_panel.rowHeights = new int[] { 0, 0 };
	gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
		Double.MIN_VALUE };
	gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panel.setLayout(gbl_panel);

	JButton cancelButton = new JButton("Cancel");
	GridBagConstraints gbc_cancelButton = new GridBagConstraints();
	gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
	gbc_cancelButton.gridx = 0;
	gbc_cancelButton.gridy = 0;
	panel.add(cancelButton, gbc_cancelButton);

	JButton reportButton = new JButton("Show report");
	GridBagConstraints gbc_reportButton = new GridBagConstraints();
	gbc_reportButton.insets = new Insets(0, 0, 0, 5);
	gbc_reportButton.gridx = 1;
	gbc_reportButton.gridy = 0;
	panel.add(reportButton, gbc_reportButton);

	JButton countButton = new JButton("Count");
	GridBagConstraints gbc_countButton = new GridBagConstraints();
	gbc_countButton.insets = new Insets(0, 0, 0, 5);
	gbc_countButton.gridx = 2;
	gbc_countButton.gridy = 0;
	panel.add(countButton, gbc_countButton);
	
	JProgressBar progressBar = new JProgressBar();
	GridBagConstraints gbc_progressBar = new GridBagConstraints();
	gbc_progressBar.gridx = 3;
	gbc_progressBar.gridy = 0;
	panel.add(progressBar, gbc_progressBar);

	buttonArray = new JButton[] { cancelButton, reportButton, countButton };
    }

    public void setButtonsListener(ActionListener al) {
	for (int i = 0; i < buttonArray.length; i++) {
	    buttonArray[i].setActionCommand(itemEvent[i]);
	    buttonArray[i].addActionListener(al);
	}
    }

    public void showWindow() {
	this.setVisible(true);
    }

}
