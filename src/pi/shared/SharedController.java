package pi.shared;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import pi.data.importer.Importer;
import pi.graph.signal.GraphView;
import pi.gui.OurFrame;
import pi.gui.toolbar.ProjectToolbar;
import pi.gui.toolbar.ProjectToolbarController;
import pi.project.Project;
import pi.shared.schemes.Scheme;
import pi.shared.schemes.signal.SignalScheme;
import pi.statistics.logic.ProjectResult;
import pi.statistics.logic.report.PopulReportMngr;
import pi.statistics.logic.report.SpecimenReportMngr;
import pi.utilities.Margin;


public class SharedController {
	private static SharedController instance = null;

	private Scheme whiteScheme;
	private Scheme blackScheme;
	private Scheme currentScheme;

	private int maxSegments;
	private double pixelsForScale;

	private double minScale;
	private double maxScale;

	private double minInterval;
	private double maxInterval;

	private boolean logged = false;

	private OurFrame frame;
	private Importer importer;
	private Container container;

	private int firstPanelWidth = 1250;
	private int firstPanelHeight = 300;
	private int firstPanelX = 10;
	private int firstPanelY = 5;

	private GraphView firstGraphView;
	private GraphView secondGraphView;
	private File lastDirectory;
	private SpecimenReportMngr specimenReportMgr;
	private PopulReportMngr populReportMngr;
	
	private Project project;
	private ProjectResult projectRes;

	private JProgressBar progressBar = null;

	private double pulse;
	private boolean isFirstPopulationSet;

	private boolean toolbarSet = false;

	private ProjectToolbarController toolConroller;
	private ProgressView progress = new ProgressView();

	private final AtomicLong sequence = new AtomicLong();
	
	public long getNextId(){
	    return sequence.incrementAndGet();
	}
	
	
	public void updateProgressBar() {
		if (this.progressBar != null) {
			this.progressBar.setValue(this.progressBar.getValue() + 1);
		}
	}

	private SharedController() {
		this.pixelsForScale = 100;
		this.maxSegments = 3;
		this.createWhiteScheme();
		this.createBlackScheme();
		this.setCurrentScheme(this.whiteScheme);
		this.minScale = 0.05d;
		this.maxScale = 0.5d;
		this.minInterval = 0.001d;
		this.maxInterval = 0.02d;
	}

	public static SharedController getInstance() {
		if (instance == null)
			instance = new SharedController();
		return instance;
	}

	public void createWhiteScheme() {
		whiteScheme = new Scheme();

		SignalScheme signal = whiteScheme.getSignalScheme();
		signal.setBackgroundColor(new Color(255, 255, 255));
		signal.setBorderColor(new Color(0, 0, 0));
		signal.setMainGridColor(new Color(200, 200, 200));
		signal.setSubGridColor(new Color(230, 230, 230));
		signal.setGridColor(new Color(255, 255, 255));
		signal.setMargin(new Margin(60, 25, 35, 35));

		signal.setFontSize(12);
		signal.setFont(new Font("Serif", Font.PLAIN, signal.getFontSize()));
		signal.setFontColor(new Color(0, 0, 0));

		signal.setSignalColor(new Color(255, 0, 0));
		signal.setProbeColor(new Color(0, 0, 0));

		signal.setSelectColor(new Color(100, 100, 255, 123));

		signal.setP_WaveColor(new Color(145, 200, 0, 123));
		signal.setPr_SegmentColor(new Color(0, 40, 200, 123));
		signal.setQrs_SegmentColor(new Color(160, 200, 60, 123));
		signal.setSt_SegmentColor(new Color(145, 40, 60, 123));
		signal.setT_WaveColor(new Color(0, 200, 100, 123));
		signal.setU_WaveColor(new Color(255, 0, 0, 123));
		signal.setP_WaveColor(new Color(0, 255, 0, 123));
		signal.setMarkeredCycleColor(new Color(125, 255, 125, 123));
		signal.setCycleColor(new Color(255, 125, 125, 123));

		signal.setMainDivider(4);
		signal.setSubDivider(3);

		signal.setSignalStroke(new BasicStroke(1));
		signal.setProbeStroke(new BasicStroke(1));
	}

	public void createBlackScheme() {
		blackScheme = new Scheme();

		SignalScheme signal = blackScheme.getSignalScheme();
		signal.setBackgroundColor(new Color(0, 0, 0));
		signal.setBorderColor(new Color(255, 255, 255));
		signal.setMainGridColor(new Color(60, 60, 60));
		signal.setSubGridColor(new Color(30, 30, 30));
		signal.setGridColor(new Color(0, 0, 0));
		signal.setMargin(new Margin(60, 25, 15, 30));

		signal.setFontSize(12);
		signal.setFont(new Font("Serif", Font.PLAIN, signal.getFontSize()));
		signal.setFontColor(new Color(255, 255, 255));

		signal.setSignalColor(new Color(0, 255, 0));
		signal.setProbeColor(new Color(255, 255, 0));

		signal.setSelectColor(new Color(80, 80, 255, 123));

		signal.setMainDivider(4);
		signal.setSubDivider(3);

		signal.setSignalStroke(new BasicStroke(1));
		signal.setProbeStroke(new BasicStroke(1));
	}

	public Scheme getWhiteScheme() {
		return whiteScheme;
	}

	public Scheme getBlackScheme() {
		return blackScheme;
	}

	public int getMaxSegments() {
		return maxSegments;
	}

	public void setMaxSegments(int maxSegments) {
		this.maxSegments = maxSegments;
	}

	public Scheme getCurrentScheme() {
		return currentScheme;
	}

	public void setCurrentScheme(Scheme currentScheme) {
		this.currentScheme = currentScheme;
	}

	public double getPixelsForScale() {
		return pixelsForScale;
	}

	public void setPixelsForScale(double pixelsForScale) {
		this.pixelsForScale = pixelsForScale;
	}

	public double getMinScale() {
		return minScale;
	}

	public void setMinScale(double minScale) {
		this.minScale = minScale;
	}

	public double getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(double maxScale) {
		this.maxScale = maxScale;
	}

	public double getMinInterval() {
		return minInterval;
	}

	public void setMinInterval(double minInterval) {
		this.minInterval = minInterval;
	}

	public double getMaxInterval() {
		return maxInterval;
	}

	public void setMaxInterval(double maxInterval) {
		this.maxInterval = maxInterval;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public OurFrame getFrame() {
		return frame;
	}

	public void setFrame(OurFrame frame) {
		this.frame = frame;
	}

	public Importer getImporter() {
		return importer;
	}

	public void setImporter(Importer importer) {
		this.importer = importer;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public void addPanel(JPanel panel) {

		JScrollPane sp = new JScrollPane(panel);
		sp.getVerticalScrollBar().setUnitIncrement(15);
		sp.getHorizontalScrollBar().setUnitIncrement(15);
		
		if (getFrame().getContent().getComponentCount() > 0) {
			Component temp = getFrame().getContent().getComponent(0);
			JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, temp,
					sp);
			getFrame().getContent().removeAll();
			getFrame().getContent().add(split);
			split.setContinuousLayout(true);
			split.setResizeWeight(0.5);
			split.setDividerLocation(0.5);
		} else {
			getFrame().getContent().add(sp);
		}
		frame.revalidate();
	}

	public void packFrame() {
		this.frame.pack();
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
		if(project == null){
			Dimension conSize = frame.getContent().getSize();
			frame.getContentPane().removeAll();
			toolbarSet = false;
			frame.initContent();
			frame.getContent().setPreferredSize(conSize);
			frame.repaint();
		}
	}

	public void createProjectToolbar() {
		if (!toolbarSet) {
			ProjectToolbar tool = new ProjectToolbar();
			
			setToolConroller(new ProjectToolbarController(
					tool));
			tool.setVisible(true);
			SharedController.getInstance().getFrame().getContentPane()
					.add(tool, BorderLayout.NORTH);
			toolbarSet = true;
		}
	}

	public double getPulse() {
		return pulse;
	}

	public void setPulse(double pulse) {
		this.pulse = pulse;
	}

	public int getFirstPanelWidth() {
		return firstPanelWidth;
	}

	public void setFirstPanelWidth(int firstPanelWidth) {
		this.firstPanelWidth = firstPanelWidth;
	}

	public int getFirstPanelHeight() {
		return firstPanelHeight;
	}

	public void setFirstPanelHeight(int firstPanelHeight) {
		this.firstPanelHeight = firstPanelHeight;
	}

	public int getFirstPanelX() {
		return firstPanelX;
	}

	public void setFirstPanelX(int firstPanelX) {
		this.firstPanelX = firstPanelX;
	}

	public int getFirstPanelY() {
		return firstPanelY;
	}

	public void setFirstPanelY(int firstPanelY) {
		this.firstPanelY = firstPanelY;
	}

	public GraphView getFirstGraphView() {
		return firstGraphView;
	}

	public void setFirstGraphView(GraphView firstGraphView) {
		this.firstGraphView = firstGraphView;
	}

	public GraphView getSecondGraphView() {
		return secondGraphView;
	}

	public void setSecondGraphView(GraphView secondGraphView) {
		this.secondGraphView = secondGraphView;
	}

	public File getLastDirectory() {
		return lastDirectory;
	}

	public void setLastDirectory(File lastDirectory) {
		this.lastDirectory = lastDirectory;
	}

	public ProjectResult getProjectRes() {
		return projectRes;
	}

	public void setProjectRes(ProjectResult projectRes) {
		this.projectRes = projectRes;
	}

	public boolean isFirstPopulationSet() {
		return isFirstPopulationSet;
	}

	public void setFirstPopulationSet(boolean isFirstPopulationSet) {
		this.isFirstPopulationSet = isFirstPopulationSet;
	}

	public SpecimenReportMngr getSpecimenReportManager() {
		return specimenReportMgr;
	}

	public void setSpecimenReportManager(SpecimenReportMngr reportMgr) {
		this.specimenReportMgr = reportMgr;
	}

	public PopulReportMngr getPopulReportMngr() {
		return populReportMngr;
	}

	public void setPopulReportMngr(PopulReportMngr manager) {
		this.populReportMngr = manager;
	}

	public ProjectToolbarController getToolConroller() {
		return toolConroller;
	}

	public void setToolConroller(ProjectToolbarController toolConroller) {
		this.toolConroller = toolConroller;
	}
	

	public ProgressView getProgress() {
		return progress;
	}

	public void setProgress(ProgressView progress) {
		this.progress = progress;
	}

	
}
