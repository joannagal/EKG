package pi.statistics.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import pi.project.Project;
import pi.shared.SharedController;
import pi.statistics.logic.ProjectResult;

public class StatisticTestsController implements ActionListener
{
	StatisticTestsView view;

	public StatisticTestsController(StatisticTestsView view)
	{
		this.view = view;
	}

	public void setDetails(int column, String channel, String wave,
			String statistics)
	{
		view.getHypoTestEdit().setText("");
		view.getHypoEqualEdit().setText("");
		view.getHypoLeftEdit().setText("");
		view.getHypoRightEdit().setText("");
		
		ProjectResult pResult = SharedController.getInstance().getProjectRes();

		String[] params =
		{ null, null, null, null };

		this.makeParams(column, params);
		if (params[0] == null)
			return;

		Vector<Double> first = pResult.getPopul1().getVectorsBefore().get(channel).get(wave).get(statistics);
		Vector<Double> second = pResult.getPopul2().getVectorsBefore().get(channel).get(wave).get(statistics);

		if ((first == null) || (second == null))
			return;
		
		//  -------------------------------------
		// Details
		
		String columnName = this.getColumnName(column);
		if (columnName != null)
		{
			Map<String, Map<String, Map<String, Vector<Double>>>> map = pResult.getTestResult().get(columnName);
			
			System.out.printf("Column Name %s", columnName);
			
			if (map != null)
			{
				
				
				Vector<Double> result = map.get(channel).get(wave).get(statistics);
				if (result != null)
				{
					boolean isT = true;
					if (result.get(0) < 0.0d) isT = false;
					
					boolean isPaired = true;
					if (result.get(1) < 0.0d) isPaired = false;
					
					if (isT) 
					{
						if (isPaired) view.getHypoTestEdit().setText("T-Test - Paired");
						else view.getHypoTestEdit().setText("T-Test - Unpaired");
					}
					else
					{
						if (isPaired) view.getHypoTestEdit().setText("Wilcoxon Test");
						else view.getHypoTestEdit().setText("U Mann�Whitney Test");
					}
					
					view.getHypoEqualEdit().setText(Double.toString(result.get(2)));
				
					
				}
			}
		}

		//  ------------------------------------
		
		ArrayList <ArrayList <Double>> toHist = new ArrayList <ArrayList <Double>>(2);
		
		toHist.add(this.getArrayFromVector(first));
		toHist.add(this.getArrayFromVector(second));
		
		if (toHist.get(0).size() < 2) return;
		if (toHist.get(1).size() < 2) return;
		
		this.view.getHistogram().setData(toHist);
		this.view.getHistogram().recalculate();
		this.view.getHistogram().draw();

	}

	public void set(String channel, String wave)
	{
		boolean after = false;
		int type = SharedController.getInstance().getProject().getType();
		if ((type == Project.POPULATION_PAIR)
				|| (type == Project.SPECIMEN_PAIR))
			after = true;

		if (after)
		{
			String[] maps =
			{ "P1AB", "P2AB", "BB", "AA", "dAB", };

			for (int i = 0; i < 5; i++)
			{
				this.fillPopulation(maps[i], channel, wave, i + 1);
			}
		} else
		{
			String[] maps =
			{ "BB" };

			for (int i = 0; i < 1; i++)
			{
				this.fillPopulation(maps[i], channel, wave, i + 1);
			}
		}
	}

	public void fillPopulation(String source, String channel, String wave,
			int column)
	{
		ProjectResult pResult = SharedController.getInstance().getProjectRes();
		Map<String, Map<String, Map<String, Vector<Double>>>> map = pResult
				.getTestResult().get(source);

		Vector<Double> result;

		int where = 0;

		for (int i = 0; i <  StatisticWindowController.statsList.length; i++)
		{
			result = map.get(channel).get(wave)
					.get(StatisticWindowController.statsList[i]);
			if (result == null) continue;
			
			if (result.size() < 3) {where++; continue;}
			
			String label = "";
			
			boolean isT = true;
			if (result.get(0) < 0.0d) isT = false;
			
			boolean isPaired = true;
			if (result.get(1) < 0.0d) isPaired = false;
			
			if (isT) 
			{
				if (isPaired){
					label = "T (P): ";
				}
				else
				{
					label = "T (U): ";
				}
			}
			else
			{
				if (isPaired)
				{
					label = "W: ";
				}
				else
				{
					label = "MWW: ";
				}
			}
			
			label += Double.toString(result.get(2));

			view.getModel().setValueAt(label, where, column);
			view.getModel().setValueAt(StatisticWindowController.statsList[i], where, 0);
			where++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String com = arg0.getActionCommand();

		if (com.equals("SAVE"))
		{

		}

		if (com.equals("CHANGE_FIGURE"))
		{
			view.prepare(view.getChannelCombo().getSelectedItem().toString(),
					view.getWaveStr());

			view.getReport().changeSelection(0, 1, false, false);
			
			view.changeSelection();
		}

		if (com.equals("CLOSE"))
		{
			view.setVisible(false);
		}

	}

	public void makeParams(int column, String[] params)
	{
		int type = SharedController.getInstance().getProject().getType();
		if (type == Project.POPULATION_PAIR)
		{
			if ((column < 1) || (column > 5))
				return;
			if (column == 1)
			{
				params[0] = "First";
				params[1] = "Before";
				params[2] = "First";
				params[3] = "After";
			} else if (column == 2)
			{
				params[0] = "Second";
				params[1] = "Before";
				params[2] = "Second";
				params[3] = "After";
			} else if (column == 3)
			{
				params[0] = "First";
				params[1] = "Before";
				params[2] = "Second";
				params[3] = "Before";
			} else if (column == 4)
			{
				params[0] = "First";
				params[1] = "After";
				params[2] = "Second";
				params[3] = "After";
			} else if (column == 5)
			{
				params[0] = "First";
				params[1] = "Diff";
				params[2] = "Second";
				params[3] = "Diff";
			}
		} else
		{
			if (column != 1)
				return;

			params[0] = "First";
			params[1] = "Before";
			params[2] = "Second";
			params[3] = "Before";
		}
	}
	
	public String getColumnName(int column)
	{
		String result = "";
		
		int projectType = SharedController.getInstance().getProject().getType();
		
		//{ "P1AB", "P2AB", "BB", "AA", "dAB", };
		
		if (projectType == Project.POPULATION_SINGLE)
		{
			if (column == 1) return "BB";
		}
		else
		{
			if (column == 1) return "P1AB";
			else if (column == 2) return "P2AB";
			else if (column == 3) return "BB";
			else if (column == 4) return "AA";
			else if (column == 5) return "dAB";
		}
		
		return result;
	}

	public ArrayList <Double> getArrayFromVector(Vector<Double> input)
	{
		
		ArrayList <Double> output = new ArrayList <Double> (input.size());
		Iterator <Double> it = input.iterator();
		
		Double value;
		while (it.hasNext())
		{
			value = it.next();
			output.add(value);
		}		
		return output;
	}
	
}