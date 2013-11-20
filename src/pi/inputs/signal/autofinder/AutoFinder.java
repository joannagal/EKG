package pi.inputs.signal.autofinder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import pi.inputs.signal.Channel;
import pi.inputs.signal.Cycle;
import pi.inputs.signal.Probe;
import pi.shared.SharedController;
import pi.utilities.Range;

public class AutoFinder
{
	private Channel upSignal;
	private Channel signal;
	private Channel baseSignal;

	// -------------
	public static final double MY_MIN = -10000.0d;
	public static final double MY_MAX = 10000.0d;

	private Parameters params = new Parameters();

	public class Derivative
	{
		public Derivative(double time, double derivative)
		{
			this.derivative = derivative;
		}

		public double derivative = 0.0d;
	}

	ArrayList<Derivative> derivative;

	public void setSignal(Channel signal)
	{
		
		this.params = signal.getParams();
		
		this.baseSignal = signal;
		this.signal = new Channel();
		this.upSignal = new Channel(); 
		
		int size = this.baseSignal.getProbe().size();
		ArrayList<Probe> probes = new ArrayList<Probe>(size); 
		ArrayList<Probe> upProbes = new ArrayList<Probe>(size); 
		
		ArrayList<Probe> base = this.baseSignal.getProbe();
		  
		for (int i = 0; i < size; i++) 
		{ 
			probes.add(base.get(i).getCopy()); 
			upProbes.add(base.get(i).getCopy());
		}
		 
		this.signal.setProbe(probes);
		this.signal.setInterval(this.baseSignal.getInterval());
		
		this.upSignal.setProbe(upProbes);
		this.upSignal.setInterval(this.baseSignal.getInterval());
		 
	}

	public AutoFinder()
	{

	}
	
	public double getQRSTime(LinkedList<Range> candidateQRS)
	{
		int sum = 0, div = 0;
		Iterator<Range> itQRS = candidateQRS.iterator();
		Range temp;
		
		while (itQRS.hasNext())
		{
			temp = itQRS.next();
			sum += temp.getInterval();
			div++;
		}
		return ((double) sum / (double) div) * this.signal.getInterval();
	}

	public void autoFind() 
	{
		// CREATE CANDIDATES
		this.createDerivatives();
		this.simplifySignal(!this.params.DOWN_SIGNAL, this.params.SIMPLIFIED_RANGE, this.signal);
		this.simplifySignal(true, this.params.SIMPLIFIED_RANGE, this.upSignal);
		this.createDerivatives();

		LinkedList<Range> candidateQRS = new LinkedList<Range>();

		SharedController.getInstance().updateProgressBar();
		
		this.getCandidatesQRS(candidateQRS);

		double pulse = this.getPulse(candidateQRS);
		SharedController.getInstance().setPulse(pulse);
		int left = this.getProbesFromTime(pulse * this.params.T_LEFT_PROP);
		int right = this.getProbesFromTime(pulse * this.params.T_RIGHT_PROP);
		LinkedList<Range> candidateT = this.getCandidatesT(candidateQRS, left,
				right);

		SharedController.getInstance().updateProgressBar();
		
		left = this.getProbesFromTime(pulse * this.params.P_LEFT_PROP);
		right = this.getProbesFromTime(pulse * this.params.P_RIGHT_PROP);
		
		
		//double avgQRS = this.getQRSTime(candidateQRS);
		//left =  this.getProbesFromTime(avgQRS * this.params.P_LEFT_PROP / 0.1);
		//right =  this.getProbesFromTime(avgQRS * this.params.P_RIGHT_PROP / 0.1);
		LinkedList<Range> candidateP = this.getCandidatesP(candidateQRS, left,
				right);

		SharedController.getInstance().updateProgressBar();
		
		// BUILD ALL
		Iterator<Range> itQRS = candidateQRS.iterator();
		Iterator<Range> itT = candidateT.iterator();
		Iterator<Range> itP = candidateP.iterator();
		
		Range rQRS, rT, rP;
		Cycle cycle;
		left = 0;
		right = 0;
		while (itQRS.hasNext())
		{
			rQRS = itQRS.next();
			rT = itT.next();
			rP = itP.next();
			
			left = rQRS.getLeft();
			right = rQRS.getRight();
			
			if ((rT != null) && (rT.getLeft() > rQRS.getRight() + 3)) right = rT.getRight();
			if ((rP != null) && (rP.getRight() < rQRS.getLeft() - 3)) left = rP.getLeft();
		
			cycle = new Cycle(new Range(left, right));
			cycle.setQrs_complex(rQRS);
			
			if ((rT != null) && (rT.getLeft() > rQRS.getRight() + 3))
			{
				cycle.setSt_segment(new Range(rQRS.getRight(), rT.getLeft()));
				cycle.setT_wave(rT);
			}
			if ((rP != null) && (rP.getRight() < rQRS.getLeft() - 3))
			{
				cycle.setPr_segment(new Range(rP.getRight(), rQRS.getLeft()));
				cycle.setP_wave(rP);	
			}
			baseSignal.getCycle().add(cycle);
		}
		
		SharedController.getInstance().updateProgressBar();
	}

	public double getPulse(LinkedList<Range> candidate)
	{
		// kubelkowanie
		double result = 0.0d;
		int box[] = new int[15];
		int accu[] = new int[15];
		int scale = this.getProbesFromTime(0.2d);
		for (int i = 0; i < 15; i++)
		{
			box[i] = 0;
			accu[i] = 0;
		}

		Iterator<Range> mItr = candidate.iterator();
		Range A;
		int previous = -1;
		int current = -1;
		int diff = 0;
		int where = 0;
		int maxValue = (int) AutoFinder.MY_MIN, maxPointer = 0;

		while (mItr.hasNext())
		{
			A = mItr.next();
			if (previous == -1)
				previous = A.getLeft();
			else
			{
				current = A.getLeft();
				diff = current - previous;
				previous = current;

				where = diff / scale;
				if (where > 14)
					where = 14;
				box[where]++;
				accu[where] += diff;
				if (box[where] > maxValue)
				{
					maxValue = box[where];
					maxPointer = where;
				}
			}
		}
		result = signal.getInterval()
				* ((double) accu[maxPointer] / (double) box[maxPointer]);
		return result;
	}

	public void simplifySignal(boolean max, int level, Channel input)
	{
		ArrayList<Probe> probe = input.getProbe();
		int range = level;
		int size = this.signal.getProbe().size();

		int upCnt = 0;
		int downCnt = 0;

		int pointer = 0;
		
		int mod = 1;
		if (!max) mod = -1;

		for (int i = 0; i + range < size - 1; i++)
		{
			if (this.derivative.get(i).derivative * mod > 0)
			{
				upCnt++;
				downCnt = 0;
			} else
				downCnt++;

			if ((upCnt >= range) && (downCnt >= range))
			{
				double delta = probe.get(i - downCnt).getNormalized()
						- probe.get(pointer).getNormalized();
				delta /= (double) (i - downCnt - pointer);

				double value = 0.0d;
				double y0 = probe.get(pointer).getNormalized();

				for (int j = pointer; j < i - downCnt; j++)
				{
					value = y0 + delta * (double) (j - pointer);
					probe.get(j).setNormalized(value);
				}
				pointer = i - downCnt;
				upCnt = 0;
				downCnt = 0;
			}
		}
	}


	public Range getMonotonic(int start, boolean right, boolean ascend,
			double border, int accept, int probes, boolean firstLeave)
	{
		Range range = null;
		boolean pass = true;
		int neg = 0;
		int size = this.derivative.size();
		int mod = 1;
		if (!right)
			mod = -1;
		int pointer = start - mod;
		int cnt = 0;

		while (true)
		{
			pointer += mod;
			cnt++;
			if ((pointer < 0) || (pointer >= size))
			{
				pass = false;
				break;
			}

			if (((!ascend) && (this.derivative.get(pointer).derivative > border))
					|| ((ascend) && (this.derivative.get(pointer).derivative < border)))
			{
				if ((cnt == 1) && (firstLeave))
				{
					pass = false;
					break;
				}
				neg++;
			}

			if (cnt == probes)
			{
				if (neg > accept)
				{
					pass = false;
					break;
				}
			} else if (cnt > probes)
			{
				if (((!ascend) && (this.derivative.get(pointer).derivative > border))
						|| ((ascend) && (this.derivative.get(pointer).derivative < border)))
				{
					pass = true;
					break;
				}
			}
		}

		if (pass)
		{
			if (right)
				range = new Range(start, pointer);
			else
				range = new Range(pointer, start);
		}

		return range;
	}

	public void getCandidatesQRS(LinkedList<Range> candidate)
	{
		int size = derivative.size();
		int timeAsc = this.getProbesFromTime(this.params.QRS_ASC_TIME);
		int timeDesc = this.getProbesFromTime(this.params.QRS_DESC_TIME);
		int timeRightUp = this.getProbesFromTime(this.params.QRS_RIGHT_UP_TIME);
		int jump = this.getProbesFromTime(this.params.QRS_JUMP_AFTER);

		for (int i = 0; i < size; i++)
		{
			if (i + timeAsc >= size)
				break;

			Range range = this.getMonotonic(i, true, false,
					this.params.QRS_DESC_DERIV, this.params.QRS_MAX_NEG_DESC,
					timeDesc, true);

			if (range != null)
			{
				Range leftRange = this.getMonotonic(i - 1, false, true,
						this.params.QRS_ASC_DERIV, 
						this.params.QRS_MAX_NEG_ASC,
						timeAsc, false);
				
				Range rightRange = this.getMonotonic(range.getRight(), true, true,
						this.params.QRS_RIGHT_UP_DERIV, 
						this.params.QRS_MAX_NEG_RIGHT_UP,
						timeRightUp, false);
				
				if ((leftRange != null) && (rightRange != null))
				{
					int newLeft = leftRange.getLeft()
							+ this.params.SIMPLIFIED_RANGE;
					int newRight = rightRange.getRight()
							- this.params.SIMPLIFIED_RANGE;
					range.setLeft(newLeft);
					range.setRight(newRight);
					candidate.add(range);
					i += range.getInterval();
					i += jump;
				}
			}
		}

	}


	public LinkedList<Range> getCandidatesP(LinkedList<Range> candidateQRS,
			int shiftLeft, int shiftRight)
	{
		LinkedList<Range> candidateP = new LinkedList<Range>();
		ArrayList<Probe> probe = this.upSignal.getProbe();
		
		
		
		int size = probe.size();
		Iterator<Range> mItr = candidateQRS.iterator();
		Range qrs;

		int middle, left, right;
		double maxValue, value, minValue;
		int maxPointer = 0;
		double avg = 0.0d;
		double avgShift = 0.0d;

		while (mItr.hasNext())
		{
			avg = 0.0d;
			qrs = mItr.next();
			middle = qrs.getLeft() + (qrs.getRight() - qrs.getLeft()) / 2;
			left = middle - shiftLeft;
			right = middle - shiftRight;
	
			if ( (left < 0) || (right >= size) )
			{
				candidateP.addLast(null);
				continue;
			}
			
			// looking for max
			maxValue = (int) AutoFinder.MY_MIN;
			minValue = (int) AutoFinder.MY_MAX;
			for (int i = left; i <= right; i++)
			{
				value = probe.get(i).getNormalized();
				if (value > maxValue)
				{
					maxValue = value;
					maxPointer = i;
				}
				if (value < minValue)
				{
					minValue = value;
				}
			}
			
			avgShift = (maxValue - minValue) / 2.0d;
			avgShift *= this.params.P_MOD;

			avg = minValue + avgShift;
			
			for (int i = maxPointer; i <= right; i++)
			{
				if (probe.get(i).getNormalized() < avg)
				{
					right = i;
					break;
				}
			}

			for (int i = maxPointer; i >= left; i--)
			{
				if (probe.get(i).getNormalized() < avg)
				{
					left = i;
					break;
				}
			}

			candidateP.addLast(new Range(left, right));
		}

		return candidateP;
	}

	public LinkedList<Range> getCandidatesT(LinkedList<Range> candidateQRS,
			int shiftLeft, int shiftRight)
	{
		LinkedList<Range> candidateT = new LinkedList<Range>();
		ArrayList<Probe> probe = this.signal.getProbe();

		Iterator<Range> mItr = candidateQRS.iterator();
		Range qrs;

		int size = probe.size();
		int middle, left, right;
		double maxValue, minValue, value;
		int maxPointer = 0;
		int minPointer = 0;
		int time;
	
		
		while (mItr.hasNext())
		{
			qrs = mItr.next();
			middle = qrs.getLeft() + (qrs.getRight() - qrs.getLeft()) / 2;
			left = middle + shiftLeft;
			right = middle + shiftRight;
			if (right >= size)
			{
				candidateT.addLast(null);
				continue;
			}
			// looking for max/min
			minValue = (int) AutoFinder.MY_MAX;
			maxValue = (int) AutoFinder.MY_MIN;
			for (int i = left; i <= right; i++)
			{
				value = probe.get(i).getNormalized();
				if (value > maxValue)
				{
					maxValue = value;
					maxPointer = i;
				}
				if (value < minValue)
				{
					minValue = value;
					minPointer = i;
				}
			}
			//
			left = maxPointer - 10;
			right = maxPointer;

			time = this.getProbesFromTime(this.params.T_RIGHT_TIME);
			
			Range resizeRight = null, resizeLeft = null;
			
			if (this.params.T_IS_UP)
			{
				resizeRight = this.getMonotonic(maxPointer + 1, true, false,
						this.params.T_RIGHT_DERIV, this.params.T_RIGHT_NEG, time,
						false);
				time = this.getProbesFromTime(this.params.T_LEFT_TIME);
				resizeLeft = this.getMonotonic(maxPointer - 1, false, true,
						this.params.T_LEFT_DERIV, this.params.T_LEFT_NEG, time,
						false);
			}
			else
			{
				resizeRight = this.getMonotonic(minPointer + 1, true, true,
						this.params.T_RIGHT_DERIV, this.params.T_RIGHT_NEG, time,
						false);
				time = this.getProbesFromTime(this.params.T_LEFT_TIME);
				resizeLeft = this.getMonotonic(minPointer - 1, false, false,
						this.params.T_LEFT_DERIV, this.params.T_LEFT_NEG, time,
						false);
			}
			

			if ((resizeLeft != null) && (resizeRight != null))
			{
				left = resizeLeft.getLeft();
				right = resizeRight.getRight();
				candidateT.addLast(new Range(left, right));
			} else
			{
				candidateT.addLast(null);
				continue;
			}

		}

		return candidateT;
	}

	
	public int getProbesFromTime(double time)
	{
		double interval = signal.getInterval();

		/*int result = 0;
		double accu = 0.0d;
		while (accu < time)
		{
			result++;
			accu = (double) result * signal.getInterval();
		}
		return result;*/
		
		return (int) (time / interval + 0.5d);
	}

	public void createDerivatives()
	{
		int size = signal.getProbe().size() - 1;

		derivative = new ArrayList<Derivative>(size);

		double start = signal.getTranslation();
		double interv = signal.getInterval();
		double time, value;
		Derivative temp;

		ArrayList<Probe> probe = signal.getProbe();

		for (int i = 0; i < size; i++)
		{
			time = start + (i + 1) * interv;
			value = probe.get(i + 1).getNormalized()
					- probe.get(i).getNormalized();
			value /= interv;
			temp = new Derivative(time, value);
			derivative.add(temp);
		}

	}

}
