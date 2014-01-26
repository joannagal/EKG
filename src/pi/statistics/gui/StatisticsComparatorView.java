package pi.statistics.gui;

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
import pi.statistics.gui.dependgraph.DependGraph;
import pi.statistics.gui.histogram.Histogram;

public class StatisticsComparatorView extends JFrame {
    private static final long serialVersionUID = 1L;

    //private Specimen[] specimen = new Specimen[2];
    private StatisticsComparatorController controller;

    public JLabel channelLabel = new JLabel("Channel");
    public JLabel specimanLabel = new JLabel("Person");

    private JComboBox<String> channelCombo = new JComboBox<String>();
    private JComboBox<String> specimanCombo = new JComboBox<String>();
    private JList<String> wavesList;

    private String specimanStr = "";
    private String channelStr = "I";
    private String waveStr = "pWave";

    private JButton closeButton = new JButton("Close");
    private JButton saveButton = new JButton("Save");
    private Histogram histogram = new Histogram();
    private DependGraph dGraph = new DependGraph();

    private JTabbedPane tabbedPane = new JTabbedPane();

    private JTable report = new JTable();
    private DefaultTableModel model = new DefaultTableModel();
    private JScrollPane reportPane = new JScrollPane(report);

    public StatisticsComparatorView() {
	this.setTitle("Statistics");

	getContentPane().setLayout(null);
	this.setSize(new Dimension(1000, 500));
	this.setResizable(false);

	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	this.setLocation(x, y);

	controller = new StatisticsComparatorController(this);

	fillSpecimanCombo();
	//this.specimanCombo.setSelectedIndex(0);
	this.specimanCombo.setActionCommand("CHANGE_SPEC");
	this.specimanCombo.addActionListener(controller);
	
	this.specimanCombo.setBounds(55, 0, 100, 19);
	
	getContentPane().add(this.specimanCombo);

	
	specimanLabel.setBounds(10, 2, 46, 14);
	getContentPane().add(specimanLabel);
	
	this.channelCombo.setActionCommand("CHANGE_FIGURE");
	this.channelCombo.addActionListener(controller);

	this.channelLabel.setBounds(5, 22, 100, 15);
	getContentPane().add(this.channelLabel);
	
	
	this.channelCombo.setBounds(55, 18, 100, 19);
	//this.channelCombo.setSelectedIndex(1);
	fillChannelCombo();
	getContentPane().add(this.channelCombo);

	fillWavesList();
	this.wavesList.setBounds(15, 45, 140, 390);
	this.wavesList.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent arg0) {
		prepare(getSpecimanStr(), getChannelStr(), getWavesList().getSelectedValue());
	    }
	});
	getContentPane().add(this.wavesList);

	this.closeButton.setActionCommand("CLOSE");
	this.closeButton.addActionListener(controller);
	this.closeButton.setBounds(15, 440, 140, 25);
	getContentPane().add(this.closeButton);

	this.saveButton.setActionCommand("SAVE");
	this.saveButton.addActionListener(controller);
	this.saveButton.setBounds(850, 440, 140, 25);
	getContentPane().add(this.saveButton);

	this.tabbedPane.addTab("Data", this.reportPane);
	this.tabbedPane.addTab("Histogram", this.histogram);
	this.tabbedPane.addTab("Dependency Graph", this.dGraph);
	this.tabbedPane.setBounds(165, 18, 820, 417);
	getContentPane().add(this.tabbedPane);

	this.histogram.recalculate();
	this.histogram.draw();

	this.dGraph.setType(DependGraph.MIXED);



	this.dGraph.recalculate();
	this.dGraph.draw();

    }

    // public void setSpecimen(Specimen first, Specimen second)
    // {
    // this.specimen[0] = first;
    // this.specimen[1] = second;
    // }

    // class ShowThread implements Runnable
    // {
    // private StatisticsComparatorView view;
    // private Specimen first;
    // private Specimen second;
    //
    // public ShowThread(StatisticsComparatorView view, Specimen first,
    // Specimen second)
    // {
    // this.view = view;
    // this.first = first;
    // this.second = second;
    // }
    //
    // public void run()
    // {
    // SharedController.getInstance().getProgressView().init(1);
    //
    // view.setSpecimen(first, second);
    //
    // view.specimen[0].calculateStatistic();
    // if (view.specimen[1] != null)
    // specimen[1].calculateStatistic();
    //
    // view.prepare(view.getFigureStr(), view.getElementStr());
    // }
    // }

    // public void showWithData(Specimen first, Specimen second)
    // {
    // ShowThread runnable = new ShowThread(this, first, second);
    // Thread thread = new Thread(runnable);
    // thread.start();
    //
    // }

    public void prepare(String speciman, String channel, String wave) {
	this.specimanStr = speciman;
	this.channelStr = channel;
	this.waveStr = wave;

	int size = 1;

	Specimen spec = new Specimen();
	String[] credentials = this.specimanStr.split(" ");
	for (int i = 0; i < SharedController.getInstance().getProject()
		.getFirstPopulation().getSpecimen().size(); i++){
	    if (credentials[0].equals(SharedController.getInstance().getProject()
		.getFirstPopulation().getSpecimen().get(i).getName()) && credentials[1].equals(SharedController.getInstance().getProject()
			.getFirstPopulation().getSpecimen().get(i).getSurname())){
		spec = SharedController.getInstance().getProject()
			.getFirstPopulation().getSpecimen().get(i);
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
	    columns[++pntr] = String.format("%s %s: Before",
		    spec.getName(), spec.getSurname());
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
	for (String channelName : SharedController.getInstance()
		.getProjectRes().getPopul1().getResult().get(0).getBefore().getValue().keySet()) {
	    int tmp = SharedController.getInstance()
			.getProjectRes().getPopul1().getResult().get(0).getBefore().getValue().keySet().size();
	    ArrayList<String> names = new ArrayList<String>();
	    for (int i = 0; i < tmp; i++) {
		if (!names.contains(channelName)){
		this.channelCombo.addItem(channelName);
		names.add(channelName);
		}
	    }
	}
    }

    public void fillSpecimanCombo() {
	for (Specimen man : SharedController.getInstance().getProject()
		.getFirstPopulation().getSpecimen()) {
	    int tmp = SharedController.getInstance().getProject()
		    .getFirstPopulation().getSpecimen().size();

	    for (int i = 0; i < tmp; i++) {
		this.specimanCombo.addItem(man.getName() + " "
			+ man.getSurname());
	    }
	}
	if (SharedController.getInstance().getProject().getSecondPopulation() != null) {
	    for (Specimen man : SharedController.getInstance().getProject()
		    .getSecondPopulation().getSpecimen()) {
		int tmp = SharedController.getInstance().getProject()
			.getSecondPopulation().getSpecimen().size();

		for (int i = 0; i < tmp; i++) {
		    this.specimanCombo.addItem(man.getName() + " "
			    + man.getSurname());
		}
	    }
	}

    }

    public void fillWavesList() {
	this.wavesList = new JList<String>(StatisticWindowController.wavesList);
    }

//    public Specimen[] getSpecimen() {
//	return specimen;
//    }
//
//    public void setSpecimen(Specimen[] specimen) {
//	this.specimen = specimen;
//    }

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

    public DependGraph getdGraph() {
	return dGraph;
    }

    public void setdGraph(DependGraph dGraph) {
	this.dGraph = dGraph;
    }

    public JComboBox<String> getSpecimanCombo() {
	return specimanCombo;
    }

    public void setSpecimanCombo(JComboBox<String> specimanCombo) {
	this.specimanCombo = specimanCombo;
    }

    public String getSpecimanStr() {
	return specimanStr;
    }

    public void setSpecimanStr(String specimanStr) {
	this.specimanStr = specimanStr;
    }
}
