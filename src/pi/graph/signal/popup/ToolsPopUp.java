package pi.graph.signal.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import pi.graph.signal.Graph;

public class ToolsPopUp extends JPopupMenu {
	private Graph graph;

	private JMenuItem probe = new JCheckBoxMenuItem("Probe", true);
	private JMenuItem select = new JRadioButtonMenuItem("Select", false);
	private JMenuItem move = new JRadioButtonMenuItem("Move", true);
	private JMenuItem scale = new JRadioButtonMenuItem("Scale", false);

	private JMenu mgrid = new JMenu("Main Grid");
	private JMenu sgrid = new JMenu("Sub Grid");

	private JMenuItem m2 = new JRadioButtonMenuItem("x2", false);
	private JMenuItem m3 = new JRadioButtonMenuItem("x3", true);
	private JMenuItem m4 = new JRadioButtonMenuItem("x4", false);
	private JMenuItem m5 = new JRadioButtonMenuItem("x5", false);

	private JMenuItem s2 = new JRadioButtonMenuItem("x2", false);
	private JMenuItem s3 = new JRadioButtonMenuItem("x3", true);
	private JMenuItem s4 = new JRadioButtonMenuItem("x4", false);
	private JMenuItem s5 = new JRadioButtonMenuItem("x5", false);

	private static final long serialVersionUID = 1L;

	private void setMFalse() {
		this.m2.setSelected(false);
		this.m3.setSelected(false);
		this.m4.setSelected(false);
		this.m5.setSelected(false);
	}

	private void setSFalse() {
		this.s2.setSelected(false);
		this.s3.setSelected(false);
		this.s4.setSelected(false);
		this.s5.setSelected(false);
	}

	private void checkSelected(JMenuItem button) {
		if (!button.isSelected()) {
			button.setSelected(true);
		}
	}

	public ToolsPopUp(Graph graph) {
		this.graph = graph;

		this.add(this.probe);
		this.probe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				getGraph().getToolBox().setProbe(probe.isSelected());
			}
		});

		this.addSeparator();
		this.add(this.select);
		this.select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				checkSelected(select);
				getGraph().getToolBox().setSelect(true);
				move.setSelected(false);
				scale.setSelected(false);
			}
		});

		this.add(this.move);
		this.move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				checkSelected(move);
				getGraph().getToolBox().setTranslate(true);
				select.setSelected(false);
				scale.setSelected(false);
			}
		});

		this.add(this.scale);
		this.scale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				checkSelected(scale);
				getGraph().getToolBox().setScale(true);
				select.setSelected(false);
				move.setSelected(false);
			}
		});

		this.addSeparator();
		this.add(this.mgrid);
		this.add(this.sgrid);

		this.mgrid.add(m2);
		this.m2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setMFalse();
				checkSelected(m2);
				getGraph().getScheme().setMainDivider(2);
				getGraph().recalculate();
				getGraph().draw();
				m2.setSelected(true);
			}
		});

		this.mgrid.add(m3);
		this.m3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setMFalse();
				checkSelected(m3);
				getGraph().getScheme().setMainDivider(3);
				getGraph().recalculate();
				getGraph().draw();
				m3.setSelected(true);
			}
		});

		this.mgrid.add(m4);
		this.m4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setMFalse();
				checkSelected(m4);
				getGraph().getScheme().setMainDivider(4);
				getGraph().recalculate();
				getGraph().draw();
				m4.setSelected(true);
			}
		});

		this.mgrid.add(m5);
		this.m5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setMFalse();
				checkSelected(m5);
				getGraph().getScheme().setMainDivider(5);
				getGraph().recalculate();
				getGraph().draw();
				m5.setSelected(true);
			}
		});

		this.sgrid.add(s2);
		this.s2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setSFalse();
				checkSelected(s2);
				getGraph().getScheme().setSubDivider(2);
				getGraph().recalculate();
				getGraph().draw();
				s2.setSelected(true);
			}
		});

		this.sgrid.add(s3);
		this.s3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setSFalse();
				checkSelected(s3);
				getGraph().getScheme().setSubDivider(3);
				getGraph().recalculate();
				getGraph().draw();
				s3.setSelected(true);
			}
		});

		this.sgrid.add(s4);
		this.s4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setSFalse();
				checkSelected(s4);
				getGraph().getScheme().setSubDivider(4);
				getGraph().recalculate();
				getGraph().draw();
				s4.setSelected(true);
			}
		});

		this.sgrid.add(s5);
		this.s5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setSFalse();
				checkSelected(s5);
				getGraph().getScheme().setSubDivider(5);
				getGraph().recalculate();
				getGraph().draw();
				s5.setSelected(true);
			}
		});
	}

	public Graph getGraph() {
		return graph;
	}

}
