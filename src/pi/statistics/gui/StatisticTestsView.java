package pi.statistics.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import pi.project.Project;
import pi.shared.SharedController;
import pi.statistics.gui.histogram.Histogram;
import pi.statistics.logic.StatisticsController;

public class StatisticTestsView extends JFrame {
	private static final long serialVersionUID = 1L;

	private StatisticTestsController controller;

	private StatisticsController stController;

	public static String[] attributes = { "Duration", "Amplitude" };

	public JLabel channelLabel = new JLabel("Channel");

	private JComboBox<String> channelCombo = new JComboBox<String>();
	private JComboBox<String> attributeCombo = new JComboBox<String>(attributes);
	private JList<String> wavesList;

	private String channelStr = "I";
	private String waveStr = "pWave";
	private String atrStr = "Duration";

	private JButton closeButton = new JButton("Close");
	private JButton saveButton = new JButton("Save report");
	private JButton reportButton = new JButton("Display report");

	private Histogram histogram = new Histogram();

	public class MyTableModel extends DefaultTableModel {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	private JTabbedPane tabbedPane = new JTabbedPane();

	private JTable report = new JTable();
	private DefaultTableModel model = new MyTableModel();
	private JScrollPane reportPane = new JScrollPane(report);

	private JPanel detailPanel = new JPanel();

	private JLabel hypoLeftStatLabel = new JLabel("P1 Avg+-SD");
	private JLabel hypoRightStatLabel = new JLabel("P2 Avg+-SD");
	private JLabel hypoTestLabel = new JLabel("Test performed");
	private JLabel hypoEqualLabel = new JLabel("P-Value");
	private JLabel hypoRightLabel = new JLabel("Right sided test");
	private JLabel hypoLeftLabel = new JLabel("Left sided test");

	private JTextField hypoLeftStatEdit = new JTextField();
	private JTextField hypoRightStatEdit = new JTextField();
	private JTextField hypoTestEdit = new JTextField();
	private JTextField hypoEqualEdit = new JTextField();
	private JTextField hypoRightEdit = new JTextField();
	private JTextField hypoLeftEdit = new JTextField();

	public StatisticTestsView() {
		this.setTitle("Tests results");

		this.setLayout(null);
		this.setSize(new Dimension(1000, 500));
		this.setResizable(false);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		this.controller = new StatisticTestsController(this);

		this.channelLabel.setBounds(5, 22, 100, 15);
		this.add(this.channelLabel);

		this.attributeCombo.setActionCommand("CHANGE_ATR");
		this.attributeCombo.addActionListener(controller);
		this.attributeCombo.setBounds(15, 409, 140, 20);
		getContentPane().add(this.attributeCombo);

		this.channelCombo.setBounds(55, 18, 100, 19);
		this.channelCombo.setActionCommand("CHANGE_FIGURE");
		this.channelCombo.addActionListener(controller);
		fillChannelCombo();
		this.add(this.channelCombo);

		fillWavesList();
		this.wavesList.setBounds(15, 45, 140, 350);
		this.wavesList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				prepare(getChannelStr(), getAtrStr(), getWavesList()
						.getSelectedValue());
				report.changeSelection(0, 1, false, false);
				changeSelection();
			}
		});
		this.add(this.wavesList);

		this.closeButton.setActionCommand("CLOSE");
		this.closeButton.addActionListener(controller);
		this.closeButton.setBounds(15, 440, 140, 25);
		this.add(this.closeButton);

		this.saveButton.setActionCommand("SAVE");
		this.saveButton.addActionListener(controller);
		this.saveButton.setBounds(850, 440, 140, 25);
		this.add(this.saveButton);

		this.reportButton.setActionCommand("DISPLAY");
		this.reportButton.addActionListener(controller);
		this.reportButton.setBounds(699, 440, 140, 25);
		getContentPane().add(this.reportButton);

		this.report.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.report.setCellSelectionEnabled(true);
		this.report.setDragEnabled(false);

		this.report.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				changeSelection();
			}
		});

		this.detailPanel.setBounds(165, 18, 820, 100);
		this.add(this.detailPanel);

		this.detailPanel.setLayout(null);
		this.detailPanel.setBorder(BorderFactory.createTitledBorder("Details"));

		this.hypoTestLabel.setBounds(10, 25, 110, 15);
		this.detailPanel.add(this.hypoTestLabel);
		this.hypoTestEdit.setBounds(110, 23, 290, 20);
		this.detailPanel.add(this.hypoTestEdit);

		this.hypoLeftStatLabel.setBounds(10, 50, 90, 15);
		this.detailPanel.add(this.hypoLeftStatLabel);
		this.getHypoLeftStatEdit().setBounds(110, 48, 290, 20);
		this.detailPanel.add(this.getHypoLeftStatEdit());

		this.hypoRightStatLabel.setBounds(10, 75, 90, 15);
		this.detailPanel.add(this.hypoRightStatLabel);
		this.getHypoRightStatEdit().setBounds(110, 73, 290, 20);
		this.detailPanel.add(this.getHypoRightStatEdit());

		this.hypoEqualLabel.setBounds(410, 25, 110, 15);
		this.detailPanel.add(this.hypoEqualLabel);
		this.hypoEqualEdit.setBounds(520, 23, 290, 20);
		this.detailPanel.add(this.hypoEqualEdit);

		this.hypoRightLabel.setBounds(410, 50, 110, 15);
		this.detailPanel.add(this.hypoRightLabel);
		this.hypoRightEdit.setBounds(520, 48, 290, 20);
		this.detailPanel.add(this.hypoRightEdit);

		this.hypoLeftLabel.setBounds(410, 75, 110, 15);
		this.detailPanel.add(this.hypoLeftLabel);
		this.hypoLeftEdit.setBounds(520, 73, 290, 20);
		this.detailPanel.add(this.hypoLeftEdit);

		this.reportPane.setBounds(165, 120, 820, 317);
		this.report.getTableHeader().setReorderingAllowed(false);

		this.histogram.setBounds(165, 120, 820, 317);
		this.histogram.recalculate();
		this.histogram.draw();

		this.tabbedPane.setBounds(165, 120, 820, 317);
		this.tabbedPane.add("Results", this.reportPane);
		this.tabbedPane.add("Histogram", this.histogram);
		this.add(this.tabbedPane);

	}

	public void enableReports(boolean b) {
		this.saveButton.setEnabled(b);
		this.reportButton.setEnabled(b);
	}

	public void changeSelection() {
		int row = report.getSelectedRow();
		int column = report.getSelectedColumn();

		if ((row == -1) || (column == -1))
			return;

		String channel = getChannelStr();
		String wave = getWaveStr();
		String atr = getAtrStr();

		String statistics = model.getValueAt(row, 0).toString();

		controller.setDetails(column, channel, atr, wave, statistics);
	}

	public void prepare(String channel, String atr, String wave) {
		int type = SharedController.getInstance().getProject().getType();

		String[] columns;

		this.channelStr = channel;
		this.waveStr = wave;
		this.atrStr = atr;

		if (type == Project.POPULATION_SINGLE) {
			columns = new String[2];
			Project project = SharedController.getInstance().getProject();
			columns[0] = "";
			columns[1] = project.getFirstPopulation().getName();

			this.getModel().setDataVector(null, columns);

			for (int i = 0; i < 2; i++)
				columns[i] = "";
			for (int i = 0; i < 10; i++)
				this.model.addRow(columns);

			controller.set(channel, atr, wave);

		} else if (type == Project.POPULATION_PAIR) {
			columns = new String[6];
			Project project = SharedController.getInstance().getProject();
			columns[0] = "";
			columns[1] = project.getFirstPopulation().getName() + ": B with A";
			columns[2] = project.getSecondPopulation().getName() + ": B with A";
			columns[3] = "B with B";
			columns[4] = "A with A";
			columns[5] = "(A - B) with (A - B)";

			this.getModel().setDataVector(null, columns);

			for (int i = 0; i < 6; i++)
				columns[i] = "";
			for (int i = 0; i < 10; i++)
				this.model.addRow(columns);

			controller.set(channel, atr, wave);
		}

		this.report.setModel(this.getModel());
	}

	public void fillChannelCombo() {
		for (String channelName : SharedController.getInstance()
				.getProjectRes().getTestResult().get("BB").keySet()) {
			int tmp = SharedController.getInstance().getProjectRes()
					.getTestResult().get("BB").keySet().size();
			ArrayList<String> names = new ArrayList<String>();
			for (int i = 0; i < tmp; i++) {
				if (!names.contains(channelName)) {
					this.channelCombo.addItem(channelName);
					names.add(channelName);
				}
			}
		}
	}

	public void fillWavesList() {
		this.wavesList = new JList<String>(StatisticWindowController.wavesList);
	}

	public String getChannelStr() {
		return channelStr;
	}

	public void setChannelStr(String channelStr) {
		this.channelStr = channelStr;
	}

	public String getWaveStr() {
		return waveStr;
	}

	public void setWaveStr(String waveStr) {
		this.waveStr = waveStr;
	}

	public JList<String> getWavesList() {
		return wavesList;
	}

	public void setWavesList(JList<String> wavesList) {
		this.wavesList = wavesList;
	}

	public JComboBox<String> getChannelCombo() {
		return channelCombo;
	}

	public void setChannelCombo(JComboBox<String> channelCombo) {
		this.channelCombo = channelCombo;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getReport() {
		return report;
	}

	public void setReport(JTable report) {
		this.report = report;
	}

	public Histogram getHistogram() {
		return histogram;
	}

	public void setHistogram(Histogram histogram) {
		this.histogram = histogram;
	}

	public JTextField getHypoTestEdit() {
		return hypoTestEdit;
	}

	public void setHypoTestEdit(JTextField hypoTestEdit) {
		this.hypoTestEdit = hypoTestEdit;
	}

	public JTextField getHypoEqualEdit() {
		return hypoEqualEdit;
	}

	public void setHypoEqualEdit(JTextField hypoEqualEdit) {
		this.hypoEqualEdit = hypoEqualEdit;
	}

	public JTextField getHypoRightEdit() {
		return hypoRightEdit;
	}

	public void setHypoRightEdit(JTextField hypoRightEdit) {
		this.hypoRightEdit = hypoRightEdit;
	}

	public JTextField getHypoLeftEdit() {
		return hypoLeftEdit;
	}

	public void setHypoLeftEdit(JTextField hypoLeftEdit) {
		this.hypoLeftEdit = hypoLeftEdit;
	}

	public JTextField getHypoLeftStatEdit() {
		return hypoLeftStatEdit;
	}

	public void setHypoLeftStatEdit(JTextField hypoLeftStatEdit) {
		this.hypoLeftStatEdit = hypoLeftStatEdit;
	}

	public JTextField getHypoRightStatEdit() {
		return hypoRightStatEdit;
	}

	public void setHypoRightStatEdit(JTextField hypoRightStatEdit) {
		this.hypoRightStatEdit = hypoRightStatEdit;
	}

	public StatisticsController getStController() {
		return stController;
	}

	public void setStController(StatisticsController stController) {
		this.stController = stController;
	}

	public JComboBox<String> getAttributeCombo() {
		return attributeCombo;
	}

	public void setAttributeCombo(JComboBox<String> attributeCombo) {
		this.attributeCombo = attributeCombo;
	}

	public String getAtrStr() {
		return atrStr;
	}

	public void setAtrStr(String atrStr) {
		this.atrStr = atrStr;
	}
}
