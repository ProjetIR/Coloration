package statistics;

import java.util.TimerTask;

/**
 * Tâche effectué par le Timer de la classe Statistics
 * @author KIEFFER
 *
 */
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
