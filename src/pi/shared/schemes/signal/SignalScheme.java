package pi.shared.schemes.signal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import pi.utilities.Margin;

public class SignalScheme {
	private Margin margin;

	private int mainDivider;
	private int subDivider;

	private Color backgroundColor;
	private Color borderColor;

	private Color mainGridColor;
	private Color subGridColor;
	private Color gridColor;

	private Color probeColor;
	private Color signalColor;

	private Color selectColor;
	private Color p_WaveColor;
	private Color pr_SegmentColor;
	private Color qrs_SegmentColor;
	private Color st_SegmentColor;
	private Color t_WaveColor;
	private Color u_WaveColor;
	private Color cycleColor;
	private Color markeredCycleColor;

	private int fontSize;
	private Color fontColor;
	private Font font;

	private BasicStroke signalStroke;
	private BasicStroke probeStroke;

	public Margin getMargin() {
		return margin;
	}

	public void setMargin(Margin margin) {
		this.margin = margin;
	}

	public int getMainDivider() {
		return mainDivider;
	}

	public void setMainDivider(int mainDivider) {
		this.mainDivider = mainDivider;
	}

	public int getSubDivider() {
		return subDivider;
	}

	public void setSubDivider(int subDivider) {
		this.subDivider = subDivider;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getSignalColor() {
		return signalColor;
	}

	public void setSignalColor(Color signalColor) {
		this.signalColor = signalColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getMainGridColor() {
		return mainGridColor;
	}

	public void setMainGridColor(Color mainGridColor) {
		this.mainGridColor = mainGridColor;
	}

	public Color getSubGridColor() {
		return subGridColor;
	}

	public void setSubGridColor(Color subGridColor) {
		this.subGridColor = subGridColor;
	}

	public Color getProbeColor() {
		return probeColor;
	}

	public void setProbeColor(Color probeColor) {
		this.probeColor = probeColor;
	}

	public Color getGridColor() {
		return gridColor;
	}

	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}

	public BasicStroke getSignalStroke() {
		return signalStroke;
	}

	public void setSignalStroke(BasicStroke signalStroke) {
		this.signalStroke = signalStroke;
	}

	public BasicStroke getProbeStroke() {
		return probeStroke;
	}

	public void setProbeStroke(BasicStroke probeStroke) {
		this.probeStroke = probeStroke;
	}

	public Color getSelectColor() {
		return selectColor;
	}

	public void setSelectColor(Color selectColor) {
		this.selectColor = selectColor;
	}

	public Color getP_WaveColor() {
		return p_WaveColor;
	}

	public void setP_WaveColor(Color p_WaveColor) {
		this.p_WaveColor = p_WaveColor;
	}

	public Color getPr_SegmentColor() {
		return pr_SegmentColor;
	}

	public void setPr_SegmentColor(Color pr_SegmentColor) {
		this.pr_SegmentColor = pr_SegmentColor;
	}

	public Color getQrs_SegmentColor() {
		return qrs_SegmentColor;
	}

	public void setQrs_SegmentColor(Color qrs_SegmentColor) {
		this.qrs_SegmentColor = qrs_SegmentColor;
	}

	public Color getSt_SegmentColor() {
		return st_SegmentColor;
	}

	public void setSt_SegmentColor(Color st_SegmentColor) {
		this.st_SegmentColor = st_SegmentColor;
	}

	public Color getT_WaveColor() {
		return t_WaveColor;
	}

	public void setT_WaveColor(Color t_WaveColor) {
		this.t_WaveColor = t_WaveColor;
	}

	public Color getU_WaveColor() {
		return u_WaveColor;
	}

	public void setU_WaveColor(Color u_WaveColor) {
		this.u_WaveColor = u_WaveColor;
	}

	public Color getCycleColor() {
		return cycleColor;
	}

	public void setCycleColor(Color cycleColor) {
		this.cycleColor = cycleColor;
	}

	public Color getMarkeredCycleColor() {
		return markeredCycleColor;
	}

	public void setMarkeredCycleColor(Color markeredCycleColor) {
		this.markeredCycleColor = markeredCycleColor;
	}

}
