package pi.statistics.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
	this.setTitle("Statistics");
	this.setSize(630, 500);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 36, 184, 0, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 359, 37, 0, 0 };
	gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0,
		Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0,
		Double.MIN_VALUE };
	getContentPane().setLayout(gridBagLayout);

	JPanel panel_2 = new JPanel();
	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	gbc_panel_2.insets = new Insets(0, 0, 5, 5);
	gbc_panel_2.fill = GridBagConstraints.BOTH;
	gbc_panel_2.gridx = 1;
	gbc_panel_2.gridy = 1;
	getContentPane().add(panel_2, gbc_panel_2);
	GridBagLayout gbl_panel_2 = new GridBagLayout();
	gbl_panel_2.columnWidths = new int[] { 210, 287, 0 };
	gbl_panel_2.rowHeights = new int[] { 0, 0 };
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
	gbl_panel_3.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
	gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	panel_3.setLayout(gbl_panel_3);

	JLabel lblElementyDoAnalizy = new JLabel("Waves:");
	GridBagConstraints gbc_lblElementyDoAnalizy = new GridBagConstraints();
	gbc_lblElementyDoAnalizy.insets = new Insets(0, 0, 5, 0);
	gbc_lblElementyDoAnalizy.gridx = 1;
	gbc_lblElementyDoAnalizy.gridy = 0;
	panel_3.add(lblElementyDoAnalizy, gbc_lblElementyDoAnalizy);

	JCheckBox chckbxTwave = new JCheckBox("T-wave");
	GridBagConstraints gbc_chckbxTwave = new GridBagConstraints();
	gbc_chckbxTwave.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxTwave.gridx = 1;
	gbc_chckbxTwave.gridy = 1;
	panel_3.add(chckbxTwave, gbc_chckbxTwave);

	JCheckBox chckbxPwave = new JCheckBox("P-wave");
	GridBagConstraints gbc_chckbxPwave = new GridBagConstraints();
	gbc_chckbxPwave.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxPwave.gridx = 1;
	gbc_chckbxPwave.gridy = 2;
	panel_3.add(chckbxPwave, gbc_chckbxPwave);

	JCheckBox chckbxUwave = new JCheckBox("U-wave");
	GridBagConstraints gbc_chckbxUwave = new GridBagConstraints();
	gbc_chckbxUwave.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxUwave.gridx = 1;
	gbc_chckbxUwave.gridy = 3;
	panel_3.add(chckbxUwave, gbc_chckbxUwave);

	JCheckBox chckbxPrInterval = new JCheckBox("PR interval");
	GridBagConstraints gbc_chckbxPrInterval = new GridBagConstraints();
	gbc_chckbxPrInterval.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxPrInterval.gridx = 1;
	gbc_chckbxPrInterval.gridy = 4;
	panel_3.add(chckbxPrInterval, gbc_chckbxPrInterval);

	JCheckBox chckbxNewCheckBox = new JCheckBox("PR segment");
	GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
	gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxNewCheckBox.gridx = 1;
	gbc_chckbxNewCheckBox.gridy = 5;
	panel_3.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

	JCheckBox chckbxStSegment = new JCheckBox("ST segment");
	GridBagConstraints gbc_chckbxStSegment = new GridBagConstraints();
	gbc_chckbxStSegment.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxStSegment.gridx = 1;
	gbc_chckbxStSegment.gridy = 6;
	panel_3.add(chckbxStSegment, gbc_chckbxStSegment);

	JCheckBox chckbxStInterval = new JCheckBox("ST interval");
	GridBagConstraints gbc_chckbxStInterval = new GridBagConstraints();
	gbc_chckbxStInterval.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxStInterval.gridx = 1;
	gbc_chckbxStInterval.gridy = 7;
	panel_3.add(chckbxStInterval, gbc_chckbxStInterval);

	JCheckBox chckbxQtInterval = new JCheckBox("QT interval");
	GridBagConstraints gbc_chckbxQtInterval = new GridBagConstraints();
	gbc_chckbxQtInterval.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxQtInterval.gridx = 1;
	gbc_chckbxQtInterval.gridy = 8;
	panel_3.add(chckbxQtInterval, gbc_chckbxQtInterval);

	JCheckBox chckbxQrsSegment = new JCheckBox("QRS complex");
	GridBagConstraints gbc_chckbxQrsSegment = new GridBagConstraints();
	gbc_chckbxQrsSegment.gridx = 1;
	gbc_chckbxQrsSegment.gridy = 9;
	panel_3.add(chckbxQrsSegment, gbc_chckbxQrsSegment);

	JPanel panel_4 = new JPanel();
	GridBagConstraints gbc_panel_4 = new GridBagConstraints();
	gbc_panel_4.fill = GridBagConstraints.BOTH;
	gbc_panel_4.gridx = 1;
	gbc_panel_4.gridy = 0;
	panel_2.add(panel_4, gbc_panel_4);
	GridBagLayout gbl_panel_4 = new GridBagLayout();
	gbl_panel_4.columnWidths = new int[] { 0, 0, 0 };
	gbl_panel_4.rowHeights = new int[] { 0, 0 };
	gbl_panel_4.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
	gbl_panel_4.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panel_4.setLayout(gbl_panel_4);

	JLabel lblParameters = new JLabel("Parameters:");
	GridBagConstraints gbc_lblParameters = new GridBagConstraints();
	gbc_lblParameters.gridx = 1;
	gbc_lblParameters.gridy = 0;
	panel_4.add(lblParameters, gbc_lblParameters);

	JPanel panel = new JPanel();
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.insets = new Insets(0, 0, 5, 5);
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 1;
	gbc_panel.gridy = 2;
	getContentPane().add(panel, gbc_panel);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[] { 0, 125, 0, 204, 0, 159, 0 };
	gbl_panel.rowHeights = new int[] { 0, 0 };
	gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		Double.MIN_VALUE };
	gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panel.setLayout(gbl_panel);

	JButton cancelButton = new JButton("Cancel");
	GridBagConstraints gbc_cancelButton = new GridBagConstraints();
	gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
	gbc_cancelButton.gridx = 1;
	gbc_cancelButton.gridy = 0;
	panel.add(cancelButton, gbc_cancelButton);

	JButton reportButton = new JButton("Show report");
	GridBagConstraints gbc_reportButton = new GridBagConstraints();
	gbc_reportButton.insets = new Insets(0, 0, 0, 5);
	gbc_reportButton.gridx = 3;
	gbc_reportButton.gridy = 0;
	panel.add(reportButton, gbc_reportButton);

	JButton countButton = new JButton("Count");
	GridBagConstraints gbc_countButton = new GridBagConstraints();
	gbc_countButton.gridx = 5;
	gbc_countButton.gridy = 0;
	panel.add(countButton, gbc_countButton);

	JPanel panel_1 = new JPanel();
	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	gbc_panel_1.insets = new Insets(0, 0, 0, 5);
	gbc_panel_1.fill = GridBagConstraints.BOTH;
	gbc_panel_1.gridx = 1;
	gbc_panel_1.gridy = 3;
	getContentPane().add(panel_1, gbc_panel_1);

	JProgressBar progressBar = new JProgressBar();
	panel_1.add(progressBar);

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
