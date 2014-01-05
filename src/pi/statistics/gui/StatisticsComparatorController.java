package pi.statistics.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import pi.population.Specimen;
import pi.statistics.logic.AttributeResult;
import pi.statistics.logic.ChannelResult;
import pi.statistics.logic.SpecimenResult;
import pi.statistics.logic.StatisticResult;
import pi.statistics.logic.WavesResult;

public class StatisticsComparatorController implements ActionListener
{
	public StatisticsComparatorView view;

	public StatisticsComparatorController(StatisticsComparatorView view)
	{
		this.view = view;
	}

	private ArrayList<ArrayList<Double>> toHist;
	private ArrayList<ArrayList<Double>> toDepend;
	private ArrayList<ArrayList<Double>> toFFT;

	public void set(Specimen spec, String channel, String wave, int columns)
	{

		
		SpecimenResult specResult = new SpecimenResult();

		if (spec != null)
			specResult = spec.getStatisticResults();
		else
			spec = null;

		// GOING DOWN

		int pntr = 0;

		this.toHist = new ArrayList<ArrayList<Double>>(4);
		this.toDepend = new ArrayList<ArrayList<Double>>(4);

		ChannelResult channelResult = specResult.getBefore();
		
		if (channelResult != null)
		{

			pntr++;
			this.fillColumn(channelResult, channel, wave, pntr);
		}

		channelResult = specResult.getAfter();
		if (channelResult != null)
		{
			pntr++;
			this.fillColumn(channelResult, channel, wave, pntr);
		}

		

		this.view.getHistogram().setData(this.toHist);
		this.view.getHistogram().recalculate();
		this.view.getHistogram().draw();

		this.view.getdGraph().setData(this.toDepend);
		this.view.getdGraph().recalculate();
		this.view.getdGraph().draw();


	}

	public void fillColumn(ChannelResult channelResult, String channel,
			String wave, int column)
	{

		
	    	WavesResult wavesResult = channelResult.getValue().get(channel);
		if (wavesResult != null)
		{
			// PRINT FOR CHECK
			/*
			 * System.out.printf("START\n"); ArrayList<PacketData> pck =
			 * figureResult.getFigure().getParent() .getPacket();
			 * 
			 * Figure fig = figureResult.getFigure(); Iterator<Segment> it =
			 * fig.getSegment().iterator(); while (it.hasNext()) {
			 * System.out.printf("SEGMENT\n"); Segment seg = it.next(); for (int
			 * i = seg.getRange().getLeft(); i <= seg.getRange() .getRight();
			 * i++) { System.out.printf("%d %d %d %d %d %d\n", pck.get(i)
			 * .getPkX(), pck.get(i).getPkY(), pck.get(i) .getPkTime(),
			 * pck.get(i).getPkPressure(), pck .get(i).getPkAltitude(),
			 * pck.get(i).getPkAzimuth()); } }
			 */

			// --------------

			StatisticResult statisticResult = wavesResult.getWavesResult().get(wave);
			if (statisticResult != null)
			{

				Map<String, Double> statResult = statisticResult.getValue();
				Double result = statResult.get("Collector");
//				if (result != null)
//				{
//					ArrayList<Double> value = result.getValue();
//					this.toHist.add(value);
//				}
//
//				result = statResult.get("Dependency Collector");
//				if (result != null)
//				{
//					ArrayList<Double> value = result.getValue();
//					this.toDepend.add(value);
//				}


				int pos = 0;
			    	result = statResult.get("Pulse(s)");
			    	if (result != null){
					this.view.getModel().setValueAt(Double.toString(result),
						pos, column);
					if (column == 1)
					this.view.getModel().setValueAt("Pulse(s)", pos,
							0);
					pos++;
			    	}
			    	result = statResult.get("Pulse(min)");
			    	if (result != null){
					this.view.getModel().setValueAt(Double.toString(result),
						pos, column);
					if (column == 1)
					this.view.getModel().setValueAt("Pulse(min)", pos,
							0);
					pos++;
			    	}
			    	result = statResult.get("QTcB");
			    	if (result != null){
					this.view.getModel().setValueAt(Double.toString(result),
						pos, column);
					if (column == 1)
					this.view.getModel().setValueAt("QTcB", pos,
							0);
					pos++;
			    	}
			    	result = statResult.get("QTcF");
			    	if (result != null){
					this.view.getModel().setValueAt(Double.toString(result),
						pos, column);
					if (column == 1)
					this.view.getModel().setValueAt("QTcF", pos,
							0);
					pos++;
			    	}
			    	result = statResult.get("QTcR");
			    	if (result != null){
					this.view.getModel().setValueAt(Double.toString(result),
						pos, column);
					if (column == 1)
					this.view.getModel().setValueAt("QTcR", pos,
							0);
					pos++;
			    	}
				for (int i = 0; i < StatisticWindowController.statsList.length; i++)
				{
					result = statResult.get(StatisticWindowController.statsList[i]);
					if (result != null)
					{
						this.view.getModel().setValueAt(Double.toString(result),
								pos, column);
						if (column == 1)
							this.view.getModel().setValueAt( StatisticWindowController.statsList[i], pos,
									0);
						pos++;
					}

				}

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String action = e.getActionCommand();

		if (action.equals("CHANGE_FIGURE"))
		{
			view.prepare(view.getSpecimanCombo().getSelectedItem().toString(), view.getChannelCombo().getSelectedItem().toString(),
					view.getWaveStr());
		}
		
		if (action.equals("CHANGE_SPEC"))
		{
			view.prepare(view.getSpecimanCombo().getSelectedItem().toString(),
				view.getChannelCombo().getSelectedItem().toString(),
				view.getWaveStr());
		}

		if (action.equals("CLOSE"))
		{
			view.setVisible(false);
		}

		if (action.equals("SAVE"))
		{

		}
	}

}
