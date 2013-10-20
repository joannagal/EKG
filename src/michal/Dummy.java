package michal;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pi.graph.signal.Graph;
import pi.inputs.signal.Channel;
import pi.inputs.signal.Probe;
import pi.shared.SharedController;

public class Dummy
{
	private Graph graph;
	
	private JFrame frame;
	private Channel signal1;
	private Channel signal2;
	private JButton addSegment;
	private JButton delSegment;
	
	
	private JButton black;
	private JButton white;
	
	private JButton channel1;
	private JButton channel2;
	
	private JSlider heightSlider;
	private JSlider segmentHSlider;
	private JSlider widthSlider;


	
	public Dummy()
	{
		frame = new JFrame("EKG Prototype");
		frame.setBounds(200, 100, 1400, 900);
		frame.setLayout(null);

		frame.addWindowListener(new MyWindowListener());
		
		// SIGNAL
		createDummySignal1();
		createDummySignal2();
		
		graph = new Graph(new Dimension(1100, 200), signal1);
		graph.recalculate();
		graph.setLocation(200, 30);

		//Segment temp = graph.getSegment(0);
		frame.add(graph);
		
		
		white = new JButton("White");
		white.setBounds(30, 150, 100, 20);
		white.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				SharedController instance = SharedController.getInstance();
				instance.setCurrentScheme(instance.getWhiteScheme());
				
				graph.recalculate();
				graph.draw();
			}
		});
		frame.add(white);
		
		black = new JButton("Black");
		black.setBounds(30, 180, 100, 20);
		black.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				SharedController instance = SharedController.getInstance();
				instance.setCurrentScheme(instance.getBlackScheme());
				
				graph.recalculate();
				graph.draw();
			}
		});
		frame.add(black);
		
		channel1 = new JButton("Channel 1");
		channel1.setBounds(30, 250, 100, 20);
		channel1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				graph.setSignal(signal1);
				graph.recalculate();
				graph.draw();
			}
		});
		frame.add(channel1);
		
		channel2 = new JButton("Channel 2");
		channel2.setBounds(30, 280, 100, 20);
		channel2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				graph.setSignal(signal2);
				graph.recalculate();
				graph.draw();
			}
		});
		frame.add(channel2);
		
		// Button add/delete frame
		addSegment = new JButton("+");
		addSegment.setBounds(30, 30, 50, 20);
		addSegment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				graph.addSegment();
			}
		});
		frame.add(addSegment);

		delSegment = new JButton("-");
		delSegment.setBounds(90, 30, 50, 20);
		delSegment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				graph.delSegment();
			}
		});
		frame.add(delSegment);
		
		segmentHSlider = new JSlider();
		segmentHSlider.setMinimum(200);
		segmentHSlider.setMaximum(1000);
		segmentHSlider.setValue(200);
		segmentHSlider.setBounds(30, 50, 120, 30);
		segmentHSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				graph.setHeight(segmentHSlider.getValue());
			}
		});
		frame.add(segmentHSlider);

		heightSlider = new JSlider();
		heightSlider.setMinimum(200);
		heightSlider.setMaximum(500);
		heightSlider.setValue(200);
		heightSlider.setBounds(30, 80, 120, 30);
		heightSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				graph.setSegmentHeight(heightSlider.getValue());
			}
		});
		frame.add(heightSlider);

		widthSlider = new JSlider();
		widthSlider.setMinimum(800);
		widthSlider.setMaximum(1500);
		widthSlider.setValue(1100);
		widthSlider.setBounds(30, 110, 120, 30);
		widthSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{	
				graph.setWidth(widthSlider.getValue());
			}
		});
		frame.add(widthSlider);

		

		frame.setVisible(true);
	}

	public void createDummySignal1()
	{
		signal1 = new Channel();

		signal1.setName("Dummy");

		signal1.setTranslation(0.0d);
		signal1.setInterval(1.0d / 100.0d);

		signal1.setMinValue(-100);
		signal1.setMaxValue(300);

		signal1.setStartAxis(0.0d);
		signal1.setScale(0.2d);
		
		//LinkedList<Range> temp = new LinkedList<Range>();
		//signal1.setP_wave(temp);

		//temp = new LinkedList<Range>();
		//signal1.setQrs_complex(temp);
		
		//temp = new LinkedList<Range>();
		//signal1.setSt_segment(temp);
		
		ArrayList<Probe> array = new ArrayList<Probe>(20 * 100);

		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				array.add(new Probe(i * 100 + j, 0));
			}

			//signal1.getP_wave().add(new Range(i * 100 + 15, i * 100 + 15 + 8));
			
			for (int j = 0; j < 8; j++)
			{
				int value = (int) (50 * Math.sin((double) ((double)j / 8.0d) * Math.PI));
				array.add(new Probe(i * 100 + 15 + j, value));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 23 + j, 0));
			}

			//signal1.getQrs_complex().add(new Range(i * 100 + 28, i * 100 + 58));
			
			
			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 28 + j, -7 * j));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 33 + j, -7 * (5 - j)));
			}

			
			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 38 + j, 50 * j));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 43 + j, 50 * (5 - j)));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 48 + j, -11 * j));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 53 + j, -11 * (5 - j)));
			}

			//signal1.getSt_segment().add(new Range(i * 100 + 58, i * 100 + 63));
			
			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 58 + j, 0));
			}

			for (int j = 0; j < 10; j++)
			{
				int value = (int) (50 * Math.sin((double) ((double)j / 10.0d) * Math.PI));
				array.add(new Probe(i * 100 + 63 + j, value));
			}

			for (int j = 0; j < 27; j++)
			{
				array.add(new Probe(i * 100 + 73 + j, 0));
			}

		}
		
		signal1.setProbe(array);
		signal1.recalculate();

	}
	
	public void createDummySignal2()
	{
		signal2 = new Channel();

		signal2.setName("Dummy");

		signal2.setTranslation(0.0d);
		signal2.setInterval(1.0d / 100.0d);

		signal2.setMinValue(-100);
		signal2.setMaxValue(300);

		signal2.setStartAxis(0.0d);
		signal2.setScale(0.2d);

		ArrayList<Probe> array = new ArrayList<Probe>(20 * 100);

		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				array.add(new Probe(i * 100 + j, 100));
			}

			for (int j = 0; j < 8; j++)
			{
				int value = (int) (50 * Math.sin((double) ((double)j / 8.0d) * Math.PI));
				array.add(new Probe(i * 100 + 15 + j, 200 - value));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 23 + j, 100));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 28 + j, 200 - -7 * j));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 33 + j,200 -  -7 * (5 - j)));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 38 + j, 200 - 50 * j));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 43 + j, 200 - 50 * (5 - j)));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 48 + j,200 -  -11 * j));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 53 + j, 200 - -11 * (5 - j)));
			}

			for (int j = 0; j < 5; j++)
			{
				array.add(new Probe(i * 100 + 58 + j, 100));
			}

			for (int j = 0; j < 10; j++)
			{
				int value = (int) (50 * Math.sin((double) ((double)j / 10.0d) * Math.PI));
				array.add(new Probe(i * 100 + 63 + j, 200 - value));
			}

			for (int j = 0; j < 27; j++)
			{
				array.add(new Probe(i * 100 + 73 + j, 100));
			}

		}
		
		signal2.setProbe(array);

	}
	
}




class MyWindowListener implements WindowListener
{
	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}
}