package pi.statistics.gui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import net.sf.jasperreports.engine.JRException;
import pi.population.Specimen;
import pi.shared.SharedController;
import pi.statistics.functions.Amplitude;
import pi.statistics.functions.Average;
import pi.statistics.functions.Max;
import pi.statistics.functions.Min;
import pi.statistics.functions.SD;
import pi.statistics.functions.Variance;
import pi.statistics.logic.Function;
import pi.statistics.logic.StatisticsController;
import pi.statistics.logic.report.PopulReportMngr;
import pi.statistics.logic.report.SpecimenReportMngr;

public class StatisticWindowController implements ActionListener {

	private StatisticWindowView window;

	public StatisticsController stControl = new StatisticsController();

	private StatisticTestsView testsView;
	private StatisticsComparatorView comparatorView;

	public static String[] wavesList;
	public static String[] channelsList = { "I", "II", "III", "V1", "V2", "V3",
			"V4", "V5", "V6" };
	public static String[] statsList = { "Average", "Max", "Min", "Amplitude",
			"Variance", "SD" };

	private ArrayList<Function> functions;
	private ArrayList<String> wavesNames;
	private String id;

	private Runnable r = new Runnable() {

		public void run() {

			int sum = SharedController.getInstance().getProject()
					.getFirstPopulation().getSpecimen().size();

			if (SharedController.getInstance().getProject()
					.getSecondPopulation() != null
					&& SharedController.getInstance().getProject()
							.getSecondPopulation().getSpecimen() != null) {
				sum += SharedController.getInstance().getProject()
						.getSecondPopulation().getSpecimen().size();
			}

			SharedController.getInstance().getProgress().init(sum + 1);

			stControl.countStatistics(functions, wavesNames, id);

			int type = SharedController.getInstance().getProject().getType();
			int index = window.comboBox.getSelectedIndex();

			if (type == 1 || type == 2 || id != null) {
				if (comparatorView != null) {
					comparatorView.setVisible(false);
					comparatorView.dispose();
				}
				setComparatorView(new StatisticsComparatorView(specimanStr));

				getComparatorView().setStController(stControl);
				getComparatorView().setSpecimanStr(specimanStr);
				getComparatorView().prepare(
						getComparatorView().getSpecimanStr(),
						getComparatorView().getChannelStr(),
						getComparatorView().getWaveStr());
				getComparatorView().setVisible(true);

				try {
					SharedController.getInstance().setSpecimenReportManager(
							new SpecimenReportMngr(id));
					comparatorView.enableReports(true);

				} catch (JRException e) {
					e.printStackTrace();
				}

				// comparatorView.validate();
				// comparatorView.pack();
				// comparatorView.repaint();
			} else {
				setTestsView(new StatisticTestsView());

				getTestsView().setStController(stControl);
				getTestsView()
						.prepare(getTestsView().getChannelStr(),
								getTestsView().getAtrStr(),
								getTestsView().getWaveStr());
				getTestsView().getReport().changeSelection(0, 1, false, false);
				getTestsView().setVisible(true);

				try {
					SharedController.getInstance().setPopulReportMngr(
							new PopulReportMngr());
					testsView.enableReports(true);

				} catch (JRException e) {
					e.printStackTrace();
				}
				// testsView.validate();
				// testsView.pack();
				// testsView.repaint();
			}

			SharedController.getInstance().getProgress().setVisible(false);
		}
	};

	private String specimanStr;

	public StatisticWindowController(StatisticWindowView window) {
		this.window = window;
		window.setButtonsListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("CANCEL")) {
			window.setVisible(false);
		}

		if (action.equals("COUNT")) {
			ArrayList<Function> functions = new ArrayList<Function>();
			ArrayList<String> wavesNames = new ArrayList<String>();

			int index = window.comboBox.getSelectedIndex();
			String specimen = window.comboBox.getItemAt(index);
			String id = null;
			String specimanStr = null;

			if (specimen.equals("Count for all")) {
				int type = SharedController.getInstance().getProject()
						.getType();
				specimanStr = SharedController.getInstance().getProject()
						.getFirstPopulation().getSpecimen().get(0).getName()
						+ " "
						+ SharedController.getInstance().getProject()
								.getFirstPopulation().getSpecimen().get(0)
								.getSurname();
				if (type == 3 || type == 4) {
					id = null;
				} else {
					Specimen man = SharedController.getInstance().getProject()
							.getFirstPopulation().getSpecimen().get(0);
					if (man.getId() == "") {
						man.setId(String.valueOf(SharedController.getInstance()
								.getNextId()));
					}
					id = man.getId();
				}

			} else {
				specimanStr = specimen;
				String[] ids = specimen.split(" ");
				for (Specimen man : SharedController.getInstance().getProject()
						.getFirstPopulation().getSpecimen()) {
					if (ids[0].equals(man.getName())
							&& ids[1].equals(man.getSurname())) {
						if (man.getId() == "") {
							man.setId(String.valueOf(SharedController
									.getInstance().getNextId()));
						}
						id = man.getId();
					}
				}
				if (SharedController.getInstance().getProject()
						.getSecondPopulation() != null) {
					for (Specimen man : SharedController.getInstance()
							.getProject().getSecondPopulation().getSpecimen()) {
						if (ids[0].equals(man.getName())
								&& ids[1].equals(man.getSurname())) {
							if (man.getId() == "") {
								man.setId(String.valueOf(SharedController
										.getInstance().getNextId()));
							}
							id = man.getId();

						}
					}
				}
			}

			Function average = new Average();
			functions.add(average);
			Function max = new Max();
			functions.add(max);
			Function min = new Min();
			functions.add(min);
			Function amplitude = new Amplitude();
			functions.add(amplitude);
			Function sd = new SD();
			functions.add(sd);
			Function variance = new Variance();
			functions.add(variance);

			for (int i = 0; i < window.checkBoxArray.length; i++) {
				if (window.checkBoxArray[i].isSelected() == true) {
					wavesNames.add(window.checkBoxArray[i].getName());
				}
			}

			wavesList = new String[wavesNames.size() + 2];
			int i;
			for (i = 0; i < wavesNames.size(); i++) {
				wavesList[i] = wavesNames.get(i);
			}
			wavesList[i] = "J-point";
			i++;
			wavesList[i] = "RR_interval";

			// stControl.countStatistics(functions, wavesNames, id);
			// SharedController.getInstance().setReportManager(new
			// ReportManager())\
			this.functions = functions;
			this.wavesNames = wavesNames;
			this.id = id;
			this.specimanStr = specimanStr;

			new Thread(r).start();

		}

	}

	public StatisticTestsView getTestsView() {
		return testsView;
	}

	public void setTestsView(StatisticTestsView testsView) {
		this.testsView = testsView;
	}

	public StatisticsComparatorView getComparatorView() {
		return comparatorView;
	}

	public void setComparatorView(StatisticsComparatorView comparatorView) {
		this.comparatorView = comparatorView;
	}

}
