package pi.statistics.tests;

import java.util.ArrayList;


public class LillieAverage
{

	static Result result;

	static public void init(Result input)
	{
		result = input;
		input.setValue(new ArrayList<Double>(2));
		input.getValue().add(0.0d);
		input.getValue().add(0.0d);
	}

	static public void iterate(Double value)
	{
		double tmp = result.getValue().get(0) + value;
		result.getValue().set(0, tmp);

		tmp = result.getValue().get(1) + 1.0d;
		result.getValue().set(1, tmp);
	}

	static public void finish()
	{
		double res = result.getValue().get(0) / result.getValue().get(1);
		result.getValue().set(0, res);
	}
}
