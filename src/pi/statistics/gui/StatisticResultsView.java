package pi.statistics.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import pi.statistics.gui.histogram.Histogram;
import pi.project.Project;
import pi.shared.SharedController;

public class StatisticResultsView extends JFrame
{
	private static final long serialVersionUID = 1L;

	private StatisticResultsController controller;

	public JLabel channelLabel = new JLabel("Channel");

	private JComboBox<String> channelCombo = new JComboBox<String>(
		StatisticWindowController.channelsList);

	private JList<String> wavesList = new JList<String>(StatisticWindowController.wavesList);

	private String channelStr = "1";
	private String waveStr = "P wave";

	private JButton closeButton = new JButton("Close");
	private JButton saveButton = new JButton("Save");

	private Histogram histogram = new Histogram();

	public class MyTableModel extends DefaultTableModel
	{
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	}

	private JTabbedPane tabbedPane = new JTabbedPane();

	private JTable report = new JTable();
	private DefaultTableModel model = new MyTableModel();
	private JScrollPane reportPane = new JScrollPane(report);

	public StatisticResultsView()
	{
		this.setTitle("Statistics");

		this.setLayout(null);
		this.setSize(new Dimension(1000, 500));
		this.setResizable(false);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

		this.controller = new StatisticResultsController(this);

		this.channelLabel.setBounds(15, 20, 100, 15);
		this.add(this.channelLabel);

		this.channelCombo.setBounds(55, 18, 100, 19);
		this.channelCombo.setActionCommand("CHANGE_FIGURE");
		this.channelCombo.addActionListener(controller);
		this.add(this.channelCombo);

		this.wavesList.setBounds(15, 45, 140, 390);
		this.wavesList.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				prepare(getChannelStr(), getWavesList().getSelectedValue());
				report.changeSelection(0, 1, false, false);
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

		this.report.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.report.setCellSelectionEnabled(true);
		this.report.setDragEnabled(false);

		ListSelectionModel cellSelectionModel = this.report.getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				int row = report.getSelectedRow();
				int column = report.getSelectedColumn();
				//System.out.printf("-- %d %d\n", column, row);
				
				if ((row == -1) || (column == -1)) return;
				

				String channel = getChannelStr();
				String wave = getWaveStr();

				String statistics = model
						.getValueAt(row, 0).toString();	
				
				//System.out.printf("C:: %s %s %s\n", figure, attribute, statistics);
				
				controller.setHistogram(column, channel, wave, statistics);
			}
		});

		this.reportPane.setBounds(165, 18, 820, 417);
		this.report.getTableHeader().setReorderingAllowed(false);

		this.histogram.setBounds(165, 18, 820, 417);
		this.histogram.recalculate();
		this.histogram.draw();

		this.tabbedPane.setBounds(165, 18, 820, 417);
		this.tabbedPane.add("Results", this.reportPane);
		this.tabbedPane.add("Histogram", this.histogram);
		this.add(this.tabbedPane);

	}

//	class ShowThread implements Runnable
//	{
//
//		StatisticsView view;
//
//		public ShowThread(StatisticsView view)
//		{
//			this.view = view;
//		}
//
//		public void run()
//		{
//			Project project = SharedController.getInstance().getProject();
//			int specimens = project.getFirstPopulation().getSpecimen().size();
//			if (project.getSecondPopulation() != null)
//			{
//				specimens += project.getSecondPopulation().getSpecimen().size();
//			}
//
//			//SharedController.getInstance().getProgressView().init(specimens);
//			//project.calculateStatistic();
//
//			view.prepare(view.getChannelStr(), view.getWaveStr());
//
//			view.report.changeSelection(0, 1, false, false);
//
//			view.setVisible(true);
//		}
//	}

	//public void showWithData()
	//{
	//	ShowThread runnable = new ShowThread(this);
	//	Thread thread = new Thread(runnable);
	//	thread.start();

	//}

	public void prepare(String channel, String wave)
	{
		int type = SharedController.getInstance().getProject().getType();

		String[] columns;
		
		this.channelStr = channel;
		this.waveStr = wave;

		if (type == Project.POPULATION_SINGLE)
		{
			columns = new String[3];
			Project project = SharedController.getInstance().getProject();
			columns[0] = "";
			columns[1] = project.getFirstPopulation().getName();
			columns[2] = project.getSecondPopulation().getName();

			this.getModel().setDataVector(null, columns);

			for (int i = 0; i < 3; i++)
				columns[i] = "";
			for (int i = 0; i < 10; i++)
				this.model.addRow(columns);

			controller.set(channel, wave);

		} else if (type == Project.POPULATION_PAIR)
		{
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

			controller.set(channel, wave);
		}

		this.report.setModel(this.getModel());
	}

	public String getChannelStr()
	{
		return channelStr;
	}

	public void setChannelStr(String channelStr)
	{
		this.channelStr = channelStr;
	}

	public String getWaveStr()
	{
		return waveStr;
	}

	public void setWaveStr(String waveStr)
	{
		this.waveStr = waveStr;
	}

	public JList<String> getWavesList()
	{
		return wavesList;
	}

	public void setWavesList(JList<String> wavesList)
	{
		this.wavesList = wavesList;
	}

	public JComboBox<String> getChannelCombo()
	{
		return channelCombo;
	}

	public void setChannelCombo(JComboBox<String> channelCombo)
	{
		this.channelCombo = channelCombo;
	}

	public DefaultTableModel getModel()
	{
		return model;
	}

	public void setModel(DefaultTableModel model)
	{
		this.model = model;
	}

	public JTable getReport()
	{
		return report;
	}

	public void setReport(JTable report)
	{
		this.report = report;
	}

	public Histogram getHistogram()
	{
		return histogram;
	}

	public void setHistogram(Histogram histogram)
	{
		this.histogram = histogram;
	}
}
