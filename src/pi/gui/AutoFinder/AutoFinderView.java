package pi.gui.AutoFinder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import pi.graph.signal.Graph;

public class AutoFinderView extends JFrame
{
	private static final long serialVersionUID = 1L;

	// ---------------------
	private String[] columnNames =
	{ "Parameter", "Value" };
	private Object[][] data =
	{
	{ "SIMPLIFIED_RANGE", "" },
	{ "IS_DOWN_SIGNAL", "" },
	{ "QRS_ASC_DERIV", "" },
	{ "QRS_ASC_TIME", "" },
	{ "QRS_MAX_NEG_ASC", "" },
	{ "QRS_DESC_DERIV", "" },
	{ "QRS_DESC_TIME", "" },
	{ "QRS_MAX_NEG_DESC", "" },
	{ "QRS_RIGHT_UP_DERIV", "" },
	{ "QRS_RIGHT_UP_TIME", "" },
	{ "QRS_MAX_NEG_RIGHT_UP", "" },
	{ "QRS_JUMP_AFTER", "" },
	{ "T_IS_UP", "" },
	{ "T_LEFT_PROP", "" },
	{ "T_LEFT_DERIV", "" },
	{ "T_LEFT_TIME", "" },
	{ "T_LEFT_NEG", "" },
	{ "T_RIGHT_PROP", "" },
	{ "T_RIGHT_DERIV", "" },
	{ "T_RIGHT_TIME", "" },
	{ "T_RIGHT_NEG", "" },
	{ "P_MOD", "" },
	{ "P_LEFT_PROP", "" },
	{ "P_RIGHT_PROP", "" } };

	private JTable tableBase = new JTable(data, columnNames)
	{
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column)
		{
			if (column == 0)
				return false;
			return true;
		}
	};

	private JScrollPane table = new JScrollPane(getTableBase());
	private JButton closeButton = new JButton("Close");
	private JButton autoFindButton = new JButton("Find");
	private JProgressBar progressBar = new JProgressBar();

	private AutoFinderController controller = new AutoFinderController();

	// ---------------------

	public void showWithSignal(Graph graph)
	{
		this.controller.applyParams(graph, this);
		this.setVisible(true);
	}

	public AutoFinderView()
	{
		this.setResizable(false);
		this.setTitle("Parameters");
		this.setLayout(new GridBagLayout());
		this.setSize(500, 500);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weightx = 1.0d;
		c.weighty = 0.7d;
		this.add(table, c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weighty = 0.2d;
		this.add(new JPanel(), c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0.15d;
		c.weighty = 1.5d;
		this.add(this.getCloseButton(), c);
		this.closeButton.addActionListener(this.controller.getCloseAction());

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.15d;
		c.weighty = 0.2d;
		this.add(this.autoFindButton, c);
		this.autoFindButton.addActionListener(this.controller.getFindAction());

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		c.weightx = 0.65d;
		c.weighty = 0.2d;
		this.add(this.getProgressBar(), c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.weighty = 0.15d;
		this.add(new JPanel(), c);

	}

	public JTable getTableBase()
	{
		return tableBase;
	}

	public JButton getCloseButton()
	{
		return closeButton;
	}

	public JProgressBar getProgressBar()
	{
		return progressBar;
	}

}
