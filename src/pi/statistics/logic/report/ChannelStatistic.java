package pi.statistics.logic.report;

import java.util.Collection;
import java.util.Random;
import java.util.Vector;

public class ChannelStatistic {

	
	private String examination;
	private String channelName;
	private int channelId;
	private String statisticName;

	private Double p_waveResult;
	private Double t_waveResult;
	private Double u_waveResult;
	private Double pr_intervalResult;
	private Double pr_segmentResult;
	private Double st_segmentResult;
	private Double qrs_complexResult;
	private Double st_intervalResult;
	private Double qt_intervalResult;
	private Double j_point;

	public static Collection getChannelStatistics() {
		Vector statistics = new Vector();

		// TODO
		// Temp:
		try {
			for (int i = 0; i < 10; i++) {
				ChannelStatistic cs = new ChannelStatistic();
				cs.setChannelName("channel 1");
				cs.setP_waveResult(new Random().nextDouble() % 1);
				cs.setStatisticName("stat name " + i);
				cs.setExamination("before");
				statistics.add(cs);
			}
			for (int i = 0; i < 6; i++) {
				ChannelStatistic cs = new ChannelStatistic();
				cs.setChannelName("channel 2");
				cs.setP_waveResult(new Random().nextDouble() % 1);
				cs.setStatisticName("stat name " + i);
				cs.setExamination("before");
				statistics.add(cs);
			}
			for (int i = 0; i < 15; i++) {
				ChannelStatistic cs = new ChannelStatistic();
				cs.setChannelName("channel 1");
				cs.setP_waveResult(new Random().nextDouble() % 1);
				cs.setStatisticName("stat name " + i);
				cs.setExamination("after");
				statistics.add(cs);
			}
			for (int i = 0; i < 9; i++) {
				ChannelStatistic cs = new ChannelStatistic();
				cs.setChannelName("channel 2");
				cs.setP_waveResult(new Random().nextDouble() % 1);
				cs.setStatisticName("stat name " + i);
				cs.setExamination("after");
				statistics.add(cs);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return statistics;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getStatisticName() {
		return statisticName;
	}

	public void setStatisticName(String statisticName) {
		this.statisticName = statisticName;
	}

	public Double getP_waveResult() {
		return p_waveResult;
	}

	public void setP_waveResult(Double p_waveResult) {
		this.p_waveResult = p_waveResult;
	}

	public Double getT_waveResult() {
		return t_waveResult;
	}

	public void setT_waveResult(Double t_waveResult) {
		this.t_waveResult = t_waveResult;
	}

	public Double getU_waveResult() {
		return u_waveResult;
	}

	public void setU_waveResult(Double u_waveResult) {
		this.u_waveResult = u_waveResult;
	}

	public Double getPr_segmentResult() {
		return pr_segmentResult;
	}

	public void setPr_segmentResult(Double pr_segmentResult) {
		this.pr_segmentResult = pr_segmentResult;
	}

	public Double getSt_segmentResult() {
		return st_segmentResult;
	}

	public void setSt_segmentResult(Double st_segmentResult) {
		this.st_segmentResult = st_segmentResult;
	}

	public Double getQrs_complexResult() {
		return qrs_complexResult;
	}

	public void setQrs_complexResult(Double qrs_complexResult) {
		this.qrs_complexResult = qrs_complexResult;
	}

	public Double getSt_intervalResult() {
		return st_intervalResult;
	}

	public void setSt_intervalResult(Double st_intervalResult) {
		this.st_intervalResult = st_intervalResult;
	}

	public Double getQt_intervalResult() {
		return qt_intervalResult;
	}

	public void setQt_intervalResult(Double qt_intervalResult) {
		this.qt_intervalResult = qt_intervalResult;
	}

	public Double getJ_point() {
		return j_point;
	}

	public void setJ_point(Double j_point) {
		this.j_point = j_point;
	}

	public Double getPr_intervalResult() {
		return pr_intervalResult;
	}

	public void setPr_intervalResult(Double pr_intervalResult) {
		this.pr_intervalResult = pr_intervalResult;
	}

	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}

}
