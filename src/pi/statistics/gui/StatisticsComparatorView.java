package pi.statistics.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import pi.population.Specimen;
import pi.shared.SharedController;
import pi.statistics.gui.histogram.Histogram;
import pi.statistics.logic.StatisticsController;

public class StatisticsComparatorView extends JFrame {
	private static final long serialVersionUID = 1L;
	public static String[] attributes = { "Duration", "Amplitude" };
	private StatisticsComparatorController controller;

	public JLabel channelLabel = new JLabel("Channel");

	private JComboBox<String> channelCombo = new JComboBox<String>();
	private JComboBox<String> attributeCombo = new JComboBox<String>(attributes);
	private JList<String> wavesList;

	private String specimanStr = "";
	private String channelStr = "I";
	private String waveStr = "pWave";

	private JButton closeButton = new JButton("Close");
	private JButton saveButton = new JButton("Save report");
	private JButton reportButton = new JButton("Display report");
	private Histogram histogram = new Histogram();
	private JTabbedPane tabbedPane = new JTabbedPane();

	private JTable report = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane reportPane = new JScrollPane(report);

	private StatisticsController stController;

	public StatisticsComparatorView(String spec) {
		this.setTitle("Statistic results");

		setSpecimanStr(spec);

		getContentPane().setLayout(null);
		this.setSize(new Dimension(1000, 500));
		this.setResizable(false);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		controller = new StatisticsComparatorController(this);

		this.attributeCombo.setActionCommand("CHANGE_ATR");
		this.attributeCombo.addActionListener(controller);
		this.attributeCombo.setBounds(15, 409, 140, 20);
		getContentPane().add(this.attributeCombo);

		this.channelCombo.setActionCommand("CHANGE_CHANNEL");
		this.channelCombo.addActionListener(controller);

		this.channelLabel.setBounds(5, 22, 100, 15);
		getContentPane().add(this.channelLabel);

		this.channelCombo.setBounds(55, 18, 100, 19);
		fillChannelCombo();
		getContentPane().add(this.channelCombo);

		fillWavesList();
		this.wavesList.setBounds(15, 45, 140, 350);
		this.wavesList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				prepare(getSpecimanStr(), getChannelStr(), getWavesList()
						.getSelectedValue());
			}
		});
		getContentPane().add(this.wavesList);

		this.closeButton.setActionCommand("CLOSE");
		this.closeButton.addActionListener(controller);
		this.closeButton.setBounds(15, 440, 140, 25);
		getContentPane().add(this.closeButton);

		this.saveButton.setActionCommand("SAVE");
		this.saveButton.addActionListener(controller);
		this.saveButton.setBounds(845, 440, 140, 25);
		getContentPane().add(this.saveButton);

		this.tabbedPane.addTab("Data", this.reportPane);
		this.tabbedPane.addTab("Histogram", this.histogram);
		this.tabbedPane.setBounds(165, 18, 820, 417);
		getContentPane().add(this.tabbedPane);

		this.histogram.recalculate();
		this.histogram.draw();

		this.reportButton.setActionCommand("DISPLAY");
		this.reportButton.addActionListener(controller);
		this.reportButton.setBounds(699, 440, 140, 25);
		getContentPane().add(this.reportButton);

		enableReports(false);
		//TODO cursor
		setReportsCursor(Cursor.WAIT_CURSOR);

	}

	public void enableReports(boolean b) {
		this.saveButton.setEnabled(b);
		this.reportButton.setEnabled(b);
	}

	public void setReportsCursor(int c) {
		this.saveButton.setCursor(Cursor.getPredefinedCursor(c));
		this.reportButton.setCursor(Cursor.getPredefinedCursor(c));
	}

	public void prepare(String speciman, String channel, String wave) {
		this.specimanStr = speciman;
		this.channelStr = channel;
		this.waveStr = wave;

		int size = 1;

		Specimen spec = new Specimen();
		String[] credentials = this.specimanStr.split(" ");
		for (int i = 0; i < SharedController.getInstance().getProject()
				.getFirstPopulation().getSpecimen().size(); i++) {
			if (credentials[0].equals(SharedController.getInstance()
					.getProject().getFirstPopulation().getSpecimen().get(i)
					.getName())
					&& credentials[1].equals(SharedController.getInstance()
							.getProject().getFirstPopulation().getSpecimen()
							.get(i).getSurname())) {
				spec = SharedController.getInstance().getProject()
						.getFirstPopulation().getSpecimen().get(i);
			} else {
				if (SharedController.getInstance().getProject()
						.getSecondPopulation() != null) {
					for (int j = 0; j < SharedController.getInstance()
							.getProject().getSecondPopulation().getSpecimen()
							.size(); j++) {
						if (credentials[0].equals(SharedController
								.getInstance().getProject()
								.getSecondPopulation().getSpecimen().get(j)
								.getName())
								&& credentials[1].equals(SharedController
										.getInstance().getProject()
										.getSecondPopulation().getSpecimen()
										.get(j).getSurname())) {
							spec = SharedController.getInstance().getProject()
									.getSecondPopulation().getSpecimen().get(j);
						}
					}
				}
			}
		}

		if (spec.getBefore() != null) {
			size++;
			if (spec.getAfter() != null)
				size++;
		}

		String[] columns = new String[size];
		columns[0] = "";

		int pntr = 0;
		if (spec != null) {
			columns[++pntr] = String.format("%s %s: Before", spec.getName(),
					spec.getSurname());
			if (spec.getAfter() != null)
				columns[++pntr] = "After";
		}

		getModel().setDataVector(null, columns);

		for (int i = 0; i <= pntr; i++)
			columns[i] = "";
		for (int i = 0; i < 10; i++)
			this.model.addRow(columns);

		this.report.setModel(this.getModel());

		controller.set(spec, channel, wave, pntr);
		this.setVisible(true);

	}

	public void fillChannelCombo() {
		try {
			for (String channelName : SharedController.getInstance()
					.getProjectRes().getPopul1().getResult().get(0).getValue().getValue().get("Before")
					.getValue().keySet()) {
				int tmp = SharedController.getInstance().getProjectRes()
						.getPopul1().getResult().get(0).getValue().getValue().get("Before").getValue()
						.keySet().size();
				ArrayList<String> names = new ArrayList<String>();
				for (int i = 0; i < tmp; i++) {
					if (!names.contains(channelName)) {
						this.channelCombo.addItem(channelName);
						names.add(channelName);
					}
				}
			}
		} catch (Exception ex) {
			for (String channelName : SharedController.getInstance()
					.getProjectRes().getPopul2().getResult().get(0).getValue().getValue().get("Before")
					.getValue().keySet()) {
				int tmp = SharedController.getInstance().getProjectRes()
						.getPopul2().getResult().get(0).getValue().getValue().get("Before").getValue()
						.keySet().size();
				ArrayList<String> names = new ArrayList<String>();
				for (int i = 0; i < tmp; i++) {
					if (!names.contains(channelName)) {
						this.channelCombo.addItem(channelName);
						names.add(channelName);
					}
				}
			}
		}
	}

	public void fillWavesList() {
		this.wavesList = new JList<String>(StatisticWindowController.wavesList);
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

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
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

	public String getSpecimanStr() {
		return specimanStr;
	}

	public void setSpecimanStr(String specimanStr) {
		this.specimanStr = specimanStr;
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
}
