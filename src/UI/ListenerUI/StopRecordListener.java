package UI.ListenerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import statistics.Statistics;

public class StopRecordListener implements ActionListener {

	private Statistics stats;

	public StopRecordListener(Statistics stats) {
		super();
		this.stats = stats;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.stats.stopRecord();
	}
	
	

}
