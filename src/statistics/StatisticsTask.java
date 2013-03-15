package statistics;

import java.util.TimerTask;

public class StatisticsTask extends TimerTask{

	private Statistics stats;

	public StatisticsTask(Statistics stats) {
		super();
		this.stats = stats;
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		stats.record();
	}
	
	

}
