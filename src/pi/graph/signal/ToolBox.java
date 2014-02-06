package pi.graph.signal;

public class ToolBox {
	private boolean probe = true;
	private boolean select = false;
	private boolean translate = true;
	private boolean scale = false;

	private boolean selectionExists = false;
	private double leftSelection = 0.0d;
	private double rightSelection = 0.0d;

	private boolean cycleShown = true;
	private boolean p_waveShown = true;
	private boolean pr_SegmentShown = true;
	private boolean qrs_complexShown = true;
	private boolean st_segmentShown = true;
	private boolean t_waveShown = true;
	private boolean u_waveShown = true;

	public ToolBox() {
	}

	public void setFalse() {
		this.select = false;
		this.translate = false;
		this.scale = false;
	}

	public boolean isProbe() {
		return probe;
	}

	public void setProbe(boolean probe) {
		this.probe = probe;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.setFalse();
		this.select = select;
	}

	public boolean isTranslate() {
		return translate;
	}

	public void setTranslate(boolean translate) {
		this.setFalse();
		this.translate = translate;
	}

	public boolean isScale() {
		return scale;
	}

	public void setScale(boolean scale) {
		this.setFalse();
		this.scale = scale;
	}

	public boolean isSelectionExists() {
		return selectionExists;
	}

	public void setSelectionExists(boolean selectionExists) {
		this.selectionExists = selectionExists;
	}

	public double getLeftSelection() {
		return leftSelection;
	}

	public void setLeftSelection(double leftSelection) {
		this.leftSelection = leftSelection;
	}

	public double getRightSelection() {
		return rightSelection;
	}

	public void setRightSelection(double rightSelection) {
		this.rightSelection = rightSelection;
	}

	public boolean isCycleShown() {
		return cycleShown;
	}

	public void setCycleShown(boolean cycleShown) {
		this.cycleShown = cycleShown;
	}

	public boolean isP_waveShown() {
		return p_waveShown;
	}

	public void setP_waveShown(boolean p_waveShown) {
		this.p_waveShown = p_waveShown;
	}

	public boolean isQrs_complexShown() {
		return qrs_complexShown;
	}

	public void setQrs_complexShown(boolean qrs_complexShown) {
		this.qrs_complexShown = qrs_complexShown;
	}

	public boolean isT_waveShown() {
		return t_waveShown;
	}

	public void setT_waveShown(boolean t_waveShown) {
		this.t_waveShown = t_waveShown;
	}

	public boolean isPr_SegmentShown() {
		return pr_SegmentShown;
	}

	public void setPr_SegmentShown(boolean pr_SegmentShown) {
		this.pr_SegmentShown = pr_SegmentShown;
	}

	public boolean isU_waveShown() {
		return u_waveShown;
	}

	public void setU_waveShown(boolean u_waveShown) {
		this.u_waveShown = u_waveShown;
	}

	public boolean isSt_segmentShown() {
		return st_segmentShown;
	}

	public void setSt_segmentShown(boolean st_segmentShown) {
		this.st_segmentShown = st_segmentShown;
	}

}
