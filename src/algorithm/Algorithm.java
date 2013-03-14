package algorithm;

import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import statictics.ColorVertexObservator;
import statictics.Statictics;
import statictics.IO.StatisticsWriter;

import UI.Task;
/**
 * Classe utilitaire qui permet de démarrer la tâche de fin d'un algorithme. A savoir:
 * - Notification de fin d'algorithme au handler
 * - Transmission des résultat s'il y en a 
 * @author emmanuel
 *
 */
class WaitingCloseAllThread extends TimerTask {

	private Algorithm al;
	
	public WaitingCloseAllThread(Algorithm al) {
		super();
		this.al = al;
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		this.al.NotifyEnd();
	}
	
}

/**
 * Classe de base de tout algorithme ( itératif ou parrallèle)
 * Lorsqu'il se termine, il démarre un timer pour laisser le temps à chaque thread de l'algorithme de se terminer
 * correctement avec de notifier le handler de sa fin réelle. Evite les problèmes de thread qui ne sont pas tout à fait 
 * terminer et cause des problèmes. Ici on donne suffisament de temps à l'algorithme pour se terminer complètement
 * @author emmanuel
 *
 */
public abstract class Algorithm extends Observable{
	
	protected Statictics stats;
	public abstract void compute(); // démarrage de l'algorithme
	protected abstract String sendAResult(); // résultat de l'algorithme
	private long start;
	protected long duration;
	
	public Algorithm(){
		
		this.start = System.currentTimeMillis();
		this.duration = 0;
	}
	
	public void NotifyEnd() {
		// TODO Stub de la méthode généré automatiquement
		this.duration = System.currentTimeMillis() - start;
		this.setChanged();
		this.notifyObservers(this.sendAResult());
		this.t.cancel();
		this.t = null;
		//this.stats.stopRecord();
		//JOptionPane.showMessageDialog(null,"end of algorithm");
	}
	

	private Timer t;
	public void end() {
		// TODO Stub de la méthode généré automatiquement
		this.t  = new Timer();
		t.scheduleAtFixedRate(new WaitingCloseAllThread(this), new Date(System.currentTimeMillis()),50);

	}
	

}
