package pi.inputs.signal;

import pi.inputs.signal.autofinder.AutoFinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class ECG {
	private Date date;
	private String name;

	private AutoFinder autoFinder;

	private ArrayList<Channel> channel;

	public ECG() {
		this.autoFinder = new AutoFinder();
	}

	public void findAll() {
		Iterator<Channel> cItr = channel.iterator();
		Channel signal;

		while (cItr.hasNext()) {
			signal = cItr.next();
			this.findForChannel(signal);
		}
	}

	public void findForChannel(Channel signal) {
		signal.setCycle(new LinkedList<Cycle>());
		this.autoFinder.setSignal(signal);
		this.autoFinder.autoFind();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Channel> getChannel() {
		return channel;
	}

	public void setChannel(ArrayList<Channel> channel) {
		this.channel = channel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
