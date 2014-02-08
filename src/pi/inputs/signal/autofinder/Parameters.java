package pi.inputs.signal.autofinder;

public class Parameters {
	public int SIMPLIFIED_RANGE = 1;
	public boolean DOWN_SIGNAL = false;
	public double QRS_ASC_DERIV = 2.0d;
	public double QRS_ASC_TIME = 0.01d;
	public int QRS_MAX_NEG_ASC = 2;

	public double QRS_DESC_DERIV = -7.0d;
	public double QRS_DESC_TIME = 0.027d;
	public int QRS_MAX_NEG_DESC = 2;

	public double QRS_RIGHT_UP_DERIV = -7.0d;
	public double QRS_RIGHT_UP_TIME = 0.027d;
	public int QRS_MAX_NEG_RIGHT_UP = 2;

	public double QRS_JUMP_AFTER = 0.4d;
	public boolean T_IS_UP = true;
	public double T_LEFT_PROP = 0.15d;
	public double T_RIGHT_PROP = 0.35d;
	public double T_RIGHT_DERIV = -4.0d;
	public double T_RIGHT_TIME = 0.08d;
	public int T_RIGHT_NEG = 50;
	public double T_LEFT_DERIV = 4.0d;
	public double T_LEFT_TIME = 0.07d;
	public int T_LEFT_NEG = 50;
	public double P_MOD = 1.0d;
	public double P_LEFT_PROP = 0.25d;
	public double P_RIGHT_PROP = 0.07d;

	public static Parameters getIParameters() {
		Parameters params = new Parameters();
		params.SIMPLIFIED_RANGE = 2;
		params.DOWN_SIGNAL = false;
		params.QRS_ASC_DERIV = 2.0d;
		params.QRS_ASC_TIME = 0.01d;
		params.QRS_MAX_NEG_ASC = 2;
		params.QRS_DESC_DERIV = -5.0d;
		params.QRS_DESC_TIME = 0.024d;
		params.QRS_MAX_NEG_DESC = 5;
		params.QRS_RIGHT_UP_DERIV = 5.0d;
		params.QRS_RIGHT_UP_TIME = 0.025d;
		params.QRS_MAX_NEG_RIGHT_UP = 20;
		params.QRS_JUMP_AFTER = 0.6d;

		params.T_IS_UP = true;
		params.T_LEFT_PROP = 0.15d;
		params.T_RIGHT_PROP = 0.35d;
		params.T_RIGHT_DERIV = -4.0d;
		params.T_RIGHT_TIME = 0.08d;
		params.T_RIGHT_NEG = 50;
		params.T_LEFT_DERIV = 4.0d;
		params.T_LEFT_TIME = 0.07d;
		params.T_LEFT_NEG = 50;

		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.18d;
		params.P_RIGHT_PROP = 0.08d;
		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.09d;
		return params;
	}

	public static Parameters getIIParameters() {
		Parameters params = new Parameters();
		params.SIMPLIFIED_RANGE = 2;
		params.DOWN_SIGNAL = false;
		params.QRS_ASC_DERIV = 2.0d;
		params.QRS_ASC_TIME = 0.01d;
		params.QRS_MAX_NEG_ASC = 2;

		params.QRS_DESC_DERIV = -5.0d;
		params.QRS_DESC_TIME = 0.05d;
		params.QRS_MAX_NEG_DESC = 10;

		params.QRS_RIGHT_UP_DERIV = 10.0d;
		params.QRS_RIGHT_UP_TIME = 0.005d;
		params.QRS_MAX_NEG_RIGHT_UP = 10;

		params.QRS_JUMP_AFTER = 0.4d;

		params.T_IS_UP = true;
		params.T_LEFT_PROP = 0.10d;
		params.T_RIGHT_PROP = 0.35d;
		params.T_RIGHT_DERIV = -4.0d;
		params.T_RIGHT_TIME = 0.06d;
		params.T_RIGHT_NEG = 50;
		params.T_LEFT_DERIV = 3.0d;
		params.T_LEFT_TIME = 0.05d;
		params.T_LEFT_NEG = 80;

		params.P_MOD = 0.7d;
		params.P_LEFT_PROP = 0.2d;
		params.P_RIGHT_PROP = 0.05d;
		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.09d;
		return params;
	}

	public static Parameters getIIIParameters() {
		Parameters params = new Parameters();
		params.SIMPLIFIED_RANGE = 2;
		params.DOWN_SIGNAL = false;

		params.QRS_ASC_DERIV = 2.0d;
		params.QRS_ASC_TIME = 0.01d;
		params.QRS_MAX_NEG_ASC = 2;

		params.QRS_DESC_DERIV = -7.0d;
		params.QRS_DESC_TIME = 0.027d;
		params.QRS_MAX_NEG_DESC = 2;

		params.QRS_RIGHT_UP_DERIV = 10.0d;
		params.QRS_RIGHT_UP_TIME = 0.005d;
		params.QRS_MAX_NEG_RIGHT_UP = 10;

		params.QRS_JUMP_AFTER = 0.4d;

		params.T_IS_UP = true;
		params.T_LEFT_PROP = 0.15d;
		params.T_RIGHT_PROP = 0.35d;
		params.T_RIGHT_DERIV = -4.0d;
		params.T_RIGHT_TIME = 0.08d;
		params.T_RIGHT_NEG = 50;
		params.T_LEFT_DERIV = 4.0d;
		params.T_LEFT_TIME = 0.07d;
		params.T_LEFT_NEG = 50;

		params.P_MOD = 0.9d;
		params.P_LEFT_PROP = 0.2d;
		params.P_RIGHT_PROP = 0.05d;
		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.09d;
		return params;
	}

	public static Parameters getV1Parameters() {
		Parameters params = new Parameters();
		params.SIMPLIFIED_RANGE = 2;
		params.DOWN_SIGNAL = true;
		params.QRS_ASC_DERIV = 2.0d;
		params.QRS_ASC_TIME = 0.0005d;
		params.QRS_MAX_NEG_ASC = 50;

		params.QRS_DESC_DERIV = -6.0d;
		params.QRS_DESC_TIME = 0.02d;
		params.QRS_MAX_NEG_DESC = 2;

		params.QRS_RIGHT_UP_DERIV = 3.0d;
		params.QRS_RIGHT_UP_TIME = 0.02d;
		params.QRS_MAX_NEG_RIGHT_UP = 1000;

		params.QRS_JUMP_AFTER = 0.4d;
		params.T_IS_UP = true;
		params.T_LEFT_PROP = 0.19d;
		params.T_RIGHT_PROP = 0.35d;
		params.T_RIGHT_DERIV = -4.0d;
		params.T_RIGHT_TIME = 0.05d;
		params.T_RIGHT_NEG = 50;
		params.T_LEFT_DERIV = 4.0d;
		params.T_LEFT_TIME = 0.07d;
		params.T_LEFT_NEG = 50;

		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.2d;
		params.P_RIGHT_PROP = 0.1d;
		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.09d;
		return params;
	}

	public static Parameters getV2Parameters() {
		Parameters params = new Parameters();
		params.SIMPLIFIED_RANGE = 1;
		params.DOWN_SIGNAL = true;
		params.QRS_ASC_DERIV = 2.0d;
		params.QRS_ASC_TIME = 0.0005d;
		params.QRS_MAX_NEG_ASC = 50;

		params.QRS_DESC_DERIV = -6.0d;
		params.QRS_DESC_TIME = 0.02d;
		params.QRS_MAX_NEG_DESC = 2;

		params.QRS_RIGHT_UP_DERIV = 3.0d;
		params.QRS_RIGHT_UP_TIME = 0.02d;
		params.QRS_MAX_NEG_RIGHT_UP = 1000;

		params.QRS_JUMP_AFTER = 0.4d;
		params.T_IS_UP = true;
		params.T_LEFT_PROP = 0.15d;
		params.T_RIGHT_PROP = 0.35d;
		params.T_RIGHT_DERIV = -4.0d;
		params.T_RIGHT_TIME = 0.08d;
		params.T_RIGHT_NEG = 50;
		params.T_LEFT_DERIV = 4.0d;
		params.T_LEFT_TIME = 0.07d;
		params.T_LEFT_NEG = 50;
		params.P_MOD = 0.9d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.1d;
		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.09d;
		return params;
	}

	public static Parameters getV3Parameters() {
		Parameters params = new Parameters();
		params.SIMPLIFIED_RANGE = 1;
		params.DOWN_SIGNAL = true;
		params.QRS_ASC_DERIV = 2.0d;
		params.QRS_ASC_TIME = 0.0005d;
		params.QRS_MAX_NEG_ASC = 50;

		params.QRS_DESC_DERIV = -6.0d;
		params.QRS_DESC_TIME = 0.02d;
		params.QRS_MAX_NEG_DESC = 2;

		params.QRS_RIGHT_UP_DERIV = 3.0d;
		params.QRS_RIGHT_UP_TIME = 0.02d;
		params.QRS_MAX_NEG_RIGHT_UP = 1000;

		params.QRS_JUMP_AFTER = 0.4d;
		params.T_IS_UP = true;
		params.T_LEFT_PROP = 0.15d;
		params.T_RIGHT_PROP = 0.35d;
		params.T_RIGHT_DERIV = -4.0d;
		params.T_RIGHT_TIME = 0.08d;
		params.T_RIGHT_NEG = 50;
		params.T_LEFT_DERIV = 4.0d;
		params.T_LEFT_TIME = 0.07d;
		params.T_LEFT_NEG = 50;
		params.P_MOD = 1.1d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.1d;
		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.09d;
		return params;
	}

	public static Parameters getV4Parameters() {
		Parameters params = new Parameters();
		params.SIMPLIFIED_RANGE = 2;
		params.DOWN_SIGNAL = false;
		params.QRS_ASC_DERIV = 2.0d;
		params.QRS_ASC_TIME = 0.01d;
		params.QRS_MAX_NEG_ASC = 2;

		params.QRS_DESC_DERIV = -7.0d;
		params.QRS_DESC_TIME = 0.027d;
		params.QRS_MAX_NEG_DESC = 2;

		params.QRS_RIGHT_UP_DERIV = 10.0d;
		params.QRS_RIGHT_UP_TIME = 0.005d;
		params.QRS_MAX_NEG_RIGHT_UP = 10;

		params.QRS_JUMP_AFTER = 0.4d;
		params.T_IS_UP = true;
		params.T_LEFT_PROP = 0.15d;
		params.T_RIGHT_PROP = 0.35d;
		params.T_RIGHT_DERIV = -4.0d;
		params.T_RIGHT_TIME = 0.08d;
		params.T_RIGHT_NEG = 50;
		params.T_LEFT_DERIV = 4.0d;
		params.T_LEFT_TIME = 0.07d;
		params.T_LEFT_NEG = 50;
		params.P_MOD = 0.9d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.1d;
		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.09d;
		return params;
	}

	public static Parameters getV5Parameters() {
		Parameters params = new Parameters();
		params.SIMPLIFIED_RANGE = 2;
		params.DOWN_SIGNAL = false;
		params.QRS_ASC_DERIV = 2.0d;
		params.QRS_ASC_TIME = 0.01d;
		params.QRS_MAX_NEG_ASC = 2;

		params.QRS_DESC_DERIV = -7.0d;
		params.QRS_DESC_TIME = 0.027d;
		params.QRS_MAX_NEG_DESC = 2;

		params.QRS_RIGHT_UP_DERIV = 10.0d;
		params.QRS_RIGHT_UP_TIME = 0.005d;
		params.QRS_MAX_NEG_RIGHT_UP = 10;

		params.QRS_JUMP_AFTER = 0.4d;
		params.T_IS_UP = true;
		params.T_LEFT_PROP = 0.15d;
		params.T_RIGHT_PROP = 0.35d;
		params.T_RIGHT_DERIV = -4.0d;
		params.T_RIGHT_TIME = 0.08d;
		params.T_RIGHT_NEG = 50;
		params.T_LEFT_DERIV = 4.0d;
		params.T_LEFT_TIME = 0.07d;
		params.T_LEFT_NEG = 50;
		params.P_MOD = 0.9d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.1d;
		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.09d;
		return params;
	}

	public static Parameters getV6Parameters() {
		Parameters params = new Parameters();
		params.SIMPLIFIED_RANGE = 2;
		params.DOWN_SIGNAL = false;
		params.QRS_ASC_DERIV = 2.0d;
		params.QRS_ASC_TIME = 0.01d;
		params.QRS_MAX_NEG_ASC = 2;

		params.QRS_DESC_DERIV = -7.0d;
		params.QRS_DESC_TIME = 0.027d;
		params.QRS_MAX_NEG_DESC = 2;

		params.QRS_RIGHT_UP_DERIV = 10.0d;
		params.QRS_RIGHT_UP_TIME = 0.005d;
		params.QRS_MAX_NEG_RIGHT_UP = 10;

		params.QRS_JUMP_AFTER = 0.4d;

		params.T_IS_UP = true;
		params.T_LEFT_PROP = 0.15d;
		params.T_RIGHT_PROP = 0.35d;
		params.T_RIGHT_DERIV = -4.0d;
		params.T_RIGHT_TIME = 0.08d;
		params.T_RIGHT_NEG = 50;
		params.T_LEFT_DERIV = 4.0d;
		params.T_LEFT_TIME = 0.07d;
		params.T_LEFT_NEG = 50;

		params.P_MOD = 0.9d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.1d;
		params.P_MOD = 0.8d;
		params.P_LEFT_PROP = 0.25d;
		params.P_RIGHT_PROP = 0.09d;
		return params;
	}

}
