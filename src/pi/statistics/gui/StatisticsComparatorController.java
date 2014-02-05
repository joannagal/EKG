package pi.statistics.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import pi.population.Specimen;
import pi.shared.SharedController;
import pi.statistics.logic.ChannelResult;
import pi.statistics.logic.SpecimenResult;
import pi.statistics.logic.StatisticResult;
import pi.statistics.logic.WavesResult;

public class StatisticsComparatorController implements ActionListener {
	public StatisticsComparatorView view;


	public StatisticsComparatorController(StatisticsComparatorView view) {
		this.view = view;
	}

	private ArrayList<ArrayList<Double>> toHist;

	public void set(Specimen spec, String channel, String wave, int columns) {

		SpecimenResult specResult = new SpecimenResult();

		if (spec != null)
			specResult = spec.getStatisticResults();
		else
			spec = null;

		int pntr = 0;

		this.toHist = new ArrayList<ArrayList<Double>>(2);

//		ChannelResult channelResult = specResult.getBefore();
		ChannelResult channelResult = specResult.getValue().getValue().get("Before");
		if (channelResult != null) {

			pntr++;
			this.fillColumn(channelResult, channel, wave, pntr);
		}

//		channelResult = specResult.getAfter();
		channelResult = specResult.getValue().getValue().get("After");
		if (channelResult != null) {
			pntr++;
			this.fillColumn(channelResult, channel, wave, pntr);
		}

		this.view.getHistogram().setData(this.toHist);
		this.view.getHistogram().recalculate();
		this.view.getHistogram().draw();

	}

	public void fillColumn(ChannelResult channelResult, String channel,
			String wave, int column) {
		WavesResult wavesResult = channelResult
				.getValue()
				.get(channel)
				.getValue()
				.get(this.view.getAttributeCombo().getSelectedItem().toString());
		if (wavesResult != null) {

			ArrayList<Double> collector = wavesResult.getWavesCollector().get(
					wave);
			if (collector != null) {
				this.toHist.add(collector);
			}

			StatisticResult statisticResult = wavesResult.getWavesResult().get(
					wave);
			if (statisticResult != null) {

				Map<String, Double> statResult = statisticResult.getValue();

				int pos = 0;
				Double result = statResult.get("Pulse(s)");
				if (result != null) {
					this.view.getModel().setValueAt(Double.toString(result),
							pos, column);
					if (column == 1)
						this.view.getModel().setValueAt("Pulse(s)", pos, 0);
					pos++;
				}
				result = statResult.get("Pulse(min)");
				if (result != null) {
					this.view.getModel().setValueAt(Double.toString(result),
							pos, column);
					if (column == 1)
						this.view.getModel().setValueAt("Pulse(min)", pos, 0);
					pos++;
				}
				result = statResult.get("QTcB");
				if (result != null) {
					this.view.getModel().setValueAt(Double.toString(result),
							pos, column);
					if (column == 1)
						this.view.getModel().setValueAt("QTcB", pos, 0);
					pos++;
				}
				result = statResult.get("QTcF");
				if (result != null) {
					this.view.getModel().setValueAt(Double.toString(result),
							pos, column);
					if (column == 1)
						this.view.getModel().setValueAt("QTcF", pos, 0);
					pos++;
				}
				result = statResult.get("QTcR");
				if (result != null) {
					this.view.getModel().setValueAt(Double.toString(result),
							pos, column);
					if (column == 1)
						this.view.getModel().setValueAt("QTcR", pos, 0);
					pos++;
				}
				for (int i = 0; i < StatisticWindowController.statsList.length; i++) {
					result = statResult
							.get(StatisticWindowController.statsList[i]);
					if (result != null) {
						this.view.getModel().setValueAt(
								Double.toString(result), pos, column);
						if (column == 1)
							this.view.getModel().setValueAt(
									StatisticWindowController.statsList[i],
									pos, 0);
						pos++;
					}

				}

			}
		}
	}

	public ArrayList<Double> getArrayFromVector(Vector<Double> input) {

		ArrayList<Double> output = new ArrayList<Double>(input.size());
		Iterator<Double> it = input.iterator();

		Double value;
		while (it.hasNext()) {
			value = it.next();
			output.add(value);
		}
		return output;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equals("CHANGE_CHANNEL")) {
			view.prepare(view.getSpecimanStr(), view.getChannelCombo()
					.getSelectedItem().toString(), view.getWaveStr());
		}

		if (action.equals("CHANGE_ATR")) {
			view.prepare(view.getSpecimanStr(), view.getChannelCombo()
					.getSelectedItem().toString(), view.getWaveStr());
		}

		if (action.equals("CLOSE")) {
			view.setVisible(false);
		}

		if (action.equals("SAVE")) {
			if (view.getStController().getFinalResult() != null) {
				try {
					/*
					 * ReportManager rm = new ReportManager();
					 * rm.saveRaportAsPdf(null); rm.saveReportAsHtml(null);
					 */

					Object[] obj = { ".pdf", ".html" };
					int type = JOptionPane.showOptionDialog(view,
							"I want to save report as:", "Save report",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					JFileChooser fc = new JFileChooser();
					int response = fc.showSaveDialog(view);

					if (response == JFileChooser.APPROVE_OPTION) {
						String path = fc.getSelectedFile().getPath();
						if (type == 0)
							SharedController.getInstance().getReportManager()
									.saveRaportAsPdf(path);
						else if (type == 1)
							SharedController.getInstance().getReportManager()
									.saveReportAsHtml(path);
					}
				} catch (JRException ex) {
					System.out.println("Report exception");
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(view, "Count statistics first!");
			}
		}
		if (action.equals("DISPLAY")) {
			long start = System.currentTimeMillis();
			if (view.getStController().getFinalResult() != null) {
				try {
					// ReportManager rm = new ReportManager();
					// rm.viewRaport();

					SharedController.getInstance().getReportManager()
							.viewRaport();
				} catch (JRException ex) {
					System.out.println("Report exception");
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(view, "Count statistics first!");
			}
			long time = System.currentTimeMillis() - start;
			System.out.println("Czas wyœwietlenia raportu: " + time);

		}
	}
}
