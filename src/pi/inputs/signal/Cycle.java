package pi.inputs.signal;

import pi.utilities.Range;

//-------------------------------------------
/*
	KLASA CYKL
	
	ZAWIERA INFORMACJE O PRZEDZIALACH
	(NO W SENSIE MIEDZY KTORA A KTORA PROBKA 
	SIE ZNAJDUJE):
	
	- SWOJ PRZEDZIAL (CYKL JAKO CALOSC)
	- ORAZ TE PZOSTALE ZALAMKI, SEGMENTY ITP
	
*/
//-------------------------------------------

public class Cycle
{
	private Range range;
	private Range p_wave;
	private Range pr_segment;
	private Range qrs_complex;
	private Range st_segment;
	private Range t_wave;
	private Range u_wave;
	
	private Integer R = 0;
	
	private Boolean markered = false;
	
	public Cycle(Range range)
	{
		this.range = new Range(range.getLeft(), range.getRight());
		this.prepare();
	}
	
	public Cycle(int left, int right)
	{
		this.range = new Range(left, right);
		this.prepare();
	}
	
	public Cycle()
	{
		this.prepare();
	}
	
	public void prepare()
	{
		this.p_wave = null;
		this.pr_segment = null;
		this.qrs_complex = null;
		this.st_segment = null;
		this.t_wave = null;
		this.u_wave = null;
	}
	

	public Range getRange()
	{
		return range;
	}

	public void setRange(Range range)
	{
		this.range = range;
	}

	public Range getP_wave()
	{
		return p_wave;
	}

	public void setP_wave(Range p_wave)
	{
		this.p_wave = p_wave;
	}

	public Range getPr_segment()
	{
		return pr_segment;
	}

	public void setPr_segment(Range pr_segment)
	{
		this.pr_segment = pr_segment;
	}

	public Range getQrs_complex()
	{
		return qrs_complex;
	}

	public void setQrs_complex(Range qrs_complex)
	{
		this.qrs_complex = qrs_complex;
	}

	public Range getSt_segment()
	{
		return st_segment;
	}

	public void setSt_segment(Range st_segment)
	{
		this.st_segment = st_segment;
	}

	public Range getT_wave()
	{
		return t_wave;
	}

	public void setT_wave(Range t_wave)
	{
		this.t_wave = t_wave;
	}

	public Range getU_wave()
	{
		return u_wave;
	}

	public void setU_wave(Range u_wave)
	{
		this.u_wave = u_wave;
	}

	public Boolean getMarkered()
	{
		return markered;
	}

	public void setMarkered(Boolean markered)
	{
		this.markered = markered;
	}

	public Integer getR()
	{
		return R;
	}

	public void setR(Integer r)
	{
		R = r;
	}
	
	
}
