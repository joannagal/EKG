package pi.statistics.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pi.population.Specimen;
import pi.shared.SharedController;

public class StatisticWindowView extends JFrame {

    private static final long serialVersionUID = 3655555564299592830L;
    private String[] itemEvent = new String[] { "CANCEL", "COUNT" };
    private JButton[] buttonArray;
    public JCheckBox[] checkBoxArray;
    public JCheckBox tWave;
    public JCheckBox pWave;
    public JCheckBox uWave;
    public JCheckBox prInterval;
    public JCheckBox prSegment;
    public JCheckBox stSegment;
    public JCheckBox stInterval;
    public JCheckBox qtInterval;
    public JCheckBox qrsComplex;
    public JComboBox<String> comboBox;

    public StatisticWindowView() {
	this.setTitle("Count statistics");
	this.setSize(252, 406);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 184, 0 };
	gridBagLayout.rowHeights = new int[] { 345, 35, 0 };
	gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
	getContentPane().setLayout(gridBagLayout);

	JPanel panel_2 = new JPanel();
	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	gbc_panel_2.insets = new Insets(0, 0, 5, 0);
	gbc_panel_2.fill = GridBagConstraints.BOTH;
	gbc_panel_2.gridx = 0;
	gbc_panel_2.gridy = 0;
	getContentPane().add(panel_2, gbc_panel_2);
	GridBagLayout gbl_panel_2 = new GridBagLayout();
	gbl_panel_2.columnWidths = new int[] { 287, 0 };
	gbl_panel_2.rowHeights = new int[] { 268, 0 };
	gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panel_2.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	panel_2.setLayout(gbl_panel_2);

	JPanel panel_4 = new JPanel();
	GridBagConstraints gbc_panel_4 = new GridBagConstraints();
	gbc_panel_4.fill = GridBagConstraints.BOTH;
	gbc_panel_4.gridx = 0;
	gbc_panel_4.gridy = 0;
	panel_2.add(panel_4, gbc_panel_4);
	GridBagLayout gbl_panel_4 = new GridBagLayout();
	gbl_panel_4.columnWidths = new int[] { 0, 0 };
	gbl_panel_4.rowHeights = new int[] { 25, 105, 0 };
	gbl_panel_4.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panel_4.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
	panel_4.setLayout(gbl_panel_4);

	JPanel panel_1 = new JPanel();
	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	gbc_panel_1.insets = new Insets(0, 0, 5, 0);
	gbc_panel_1.fill = GridBagConstraints.BOTH;
	gbc_panel_1.gridx = 0;
	gbc_panel_1.gridy = 0;
	panel_4.add(panel_1, gbc_panel_1);
	GridBagLayout gbl_panel_1 = new GridBagLayout();
	gbl_panel_1.columnWidths = new int[] { 49, 138, 0 };
	gbl_panel_1.rowHeights = new int[] { 0, 0 };
	gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panel_1.setLayout(gbl_panel_1);

	JLabel lblCountFor = new JLabel("Count for:");
	GridBagConstraints gbc_lblCountFor = new GridBagConstraints();
	gbc_lblCountFor.insets = new Insets(0, 0, 0, 5);
	gbc_lblCountFor.anchor = GridBagConstraints.EAST;
	gbc_lblCountFor.gridx = 0;
	gbc_lblCountFor.gridy = 0;
	panel_1.add(lblCountFor, gbc_lblCountFor);
	if (SharedController.getInstance().getProject().getType() == 1
		|| SharedController.getInstance().getProject().getType() == 2) {
	    lblCountFor.setVisible(false);
	}

	comboBox = new JComboBox<String>();
	GridBagConstraints gbc_comboBox = new GridBagConstraints();
	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox.gridx = 1;
	gbc_comboBox.gridy = 0;
	panel_1.add(comboBox, gbc_comboBox);

	JPanel panel_3 = new JPanel();
	GridBagConstraints gbc_panel_3 = new GridBagConstraints();
	gbc_panel_3.gridx = 0;
	gbc_panel_3.gridy = 1;
	panel_4.add(panel_3, gbc_panel_3);
	GridBagLayout gbl_panel_3 = new GridBagLayout();
	gbl_panel_3.columnWidths = new int[] { 191, 0 };
	gbl_panel_3.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	gbl_panel_3.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
	gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	panel_3.setLayout(gbl_panel_3);
	panel_3.setBorder(BorderFactory.createTitledBorder("Waves"));

	tWave = new JCheckBox("T-wave");
	tWave.setName("tWave");
	GridBagConstraints gbc_tWave = new GridBagConstraints();
	gbc_tWave.anchor = GridBagConstraints.WEST;
	gbc_tWave.insets = new Insets(0, 0, 5, 0);
	gbc_tWave.gridx = 0;
	gbc_tWave.gridy = 0;
	panel_3.add(tWave, gbc_tWave);

	pWave = new JCheckBox("P-wave");
	pWave.setName("pWave");
	GridBagConstraints gbc_pWave = new GridBagConstraints();
	gbc_pWave.anchor = GridBagConstraints.WEST;
	gbc_pWave.insets = new Insets(0, 0, 5, 0);
	gbc_pWave.gridx = 0;
	gbc_pWave.gridy = 1;
	panel_3.add(pWave, gbc_pWave);

	uWave = new JCheckBox("U-wave");
	uWave.setName("uWave");
	GridBagConstraints gbc_uWave = new GridBagConstraints();
	gbc_uWave.anchor = GridBagConstraints.WEST;
	gbc_uWave.insets = new Insets(0, 0, 5, 0);
	gbc_uWave.gridx = 0;
	gbc_uWave.gridy = 2;
	panel_3.add(uWave, gbc_uWave);

	prInterval = new JCheckBox("PR interval");
	prInterval.setName("prInterval");
	GridBagConstraints gbc_prInterval = new GridBagConstraints();
	gbc_prInterval.anchor = GridBagConstraints.WEST;
	gbc_prInterval.insets = new Insets(0, 0, 5, 0);
	gbc_prInterval.gridx = 0;
	gbc_prInterval.gridy = 3;
	panel_3.add(prInterval, gbc_prInterval);

	prSegment = new JCheckBox("PR segment");
	prSegment.setName("prSegment");
	GridBagConstraints gbc_prSegment = new GridBagConstraints();
	gbc_prSegment.anchor = GridBagConstraints.WEST;
	gbc_prSegment.insets = new Insets(0, 0, 5, 0);
	gbc_prSegment.gridx = 0;
	gbc_prSegment.gridy = 4;
	panel_3.add(prSegment, gbc_prSegment);

	stSegment = new JCheckBox("ST segment");
	stSegment.setName("stSegment");
	GridBagConstraints gbc_stSegment = new GridBagConstraints();
	gbc_stSegment.anchor = GridBagConstraints.WEST;
	gbc_stSegment.insets = new Insets(0, 0, 5, 0);
	gbc_stSegment.gridx = 0;
	gbc_stSegment.gridy = 5;
	panel_3.add(stSegment, gbc_stSegment);

	stInterval = new JCheckBox("ST interval");
	stInterval.setName("stInterval");
	GridBagConstraints gbc_stInterval = new GridBagConstraints();
	gbc_stInterval.anchor = GridBagConstraints.WEST;
	gbc_stInterval.insets = new Insets(0, 0, 5, 0);
	gbc_stInterval.gridx = 0;
	gbc_stInterval.gridy = 6;
	panel_3.add(stInterval, gbc_stInterval);

	qtInterval = new JCheckBox("QT interval");
	qtInterval.setName("qtInterval");
	GridBagConstraints gbc_qtInterval = new GridBagConstraints();
	gbc_qtInterval.anchor = GridBagConstraints.WEST;
	gbc_qtInterval.insets = new Insets(0, 0, 5, 0);
	gbc_qtInterval.gridx = 0;
	gbc_qtInterval.gridy = 7;
	panel_3.add(qtInterval, gbc_qtInterval);

	qrsComplex = new JCheckBox("QRS complex");
	qrsComplex.setName("qrsComplex");
	GridBagConstraints gbc_qrsComplex = new GridBagConstraints();
	gbc_qrsComplex.insets = new Insets(0, 0, 5, 0);
	gbc_qrsComplex.anchor = GridBagConstraints.WEST;
	gbc_qrsComplex.gridx = 0;
	gbc_qrsComplex.gridy = 8;
	panel_3.add(qrsComplex, gbc_qrsComplex);

	JCheckBox checkAll = new JCheckBox("Check/uncheck all");
	checkAll.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent changeEvent) {
		JCheckBox checkBox = (JCheckBox) changeEvent.getSource();
		boolean state = checkBox.isSelected();
		checkAll(state);
	    }
	});

	GridBagConstraints gbc_checkAll = new GridBagConstraints();
	gbc_checkAll.anchor = GridBagConstraints.WEST;
	gbc_checkAll.gridx = 0;
	gbc_checkAll.gridy = 9;
	panel_3.add(checkAll, gbc_checkAll);
	fillCombo();
	if (SharedController.getInstance().getProject().getType() == 1
		|| SharedController.getInstance().getProject().getType() == 2) {
	    comboBox.setVisible(false);
	}

	JPanel panel = new JPanel();
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 0;
	gbc_panel.gridy = 1;
	getContentPane().add(panel, gbc_panel);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[] { 82, 76, 0, 0 };
	gbl_panel.rowHeights = new int[] { 0, 0 };
	gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0,
		Double.MIN_VALUE };
	gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panel.setLayout(gbl_panel);

	JButton cancelButton = new JButton("Cancel");
	GridBagConstraints gbc_cancelButton = new GridBagConstraints();
	gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
	gbc_cancelButton.gridx = 0;
	gbc_cancelButton.gridy = 0;
	panel.add(cancelButton, gbc_cancelButton);

	JButton countButton = new JButton("Count");
	GridBagConstraints gbc_countButton = new GridBagConstraints();
	gbc_countButton.insets = new Insets(0, 0, 0, 5);
	gbc_countButton.gridx = 1;
	gbc_countButton.gridy = 0;
	panel.add(countButton, gbc_countButton);

	buttonArray = new JButton[] { cancelButton, countButton };
	checkBoxArray = new JCheckBox[] { tWave, pWave, uWave, prInterval,
		prSegment, stSegment, stInterval, qtInterval, qrsComplex };

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

    public void checkAll(boolean state) {
	for (int i = 0; i < checkBoxArray.length; i++) {
	    checkBoxArray[i].setSelected(state);
	}
    }

    public void fillCombo() {
	this.comboBox.addItem("Count for all");
	ArrayList<String> names = new ArrayList<String>();
	for (Specimen man : SharedController.getInstance().getProject()
		.getFirstPopulation().getSpecimen()) {
	    int tmp = SharedController.getInstance().getProject()
		    .getFirstPopulation().getSpecimen().size();
	    for (int i = 0; i < tmp; i++) {
		String name = man.getName() + " " + man.getSurname();
		if (!names.contains(name)) {
		    this.comboBox.addItem(name);
		    names.add(name);
		}
	    }
	}
	if (SharedController.getInstance().getProject().getSecondPopulation() != null) {
	    for (Specimen man : SharedController.getInstance().getProject()
		    .getSecondPopulation().getSpecimen()) {
		int tmp = SharedController.getInstance().getProject()
			.getSecondPopulation().getSpecimen().size();

		for (int i = 0; i < tmp; i++) {
		    String name = man.getName() + " " + man.getSurname();
		    if (!names.contains(name)) {
			this.comboBox.addItem(name);
			names.add(name);
		    }
		}
	    }
	}

    }
}
