package statictics;

import java.util.TimerTask;

public class StatisticsTask extends TimerTask{

	private Statictics stats;

	public StatisticsTask(Statictics stats) {
		super();
		this.stats = stats;
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		stats.record();
	}
	
	

}
