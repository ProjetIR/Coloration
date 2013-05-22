package statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;

import algorithm.AlgorithmHandler;

import statistics.IO.StatisticsWriter;

import Model.Graphe;
import UI.Task;

/**
 * Classe permettant de réaliser des statistiques actives (dynamiques) sur un graphe
 * @author KIEFFER
 *
 */
public class Statistics extends Observable{

	private Graphe g;
	private ArrayList<Observator> obs; // une liste d'observator
	/**
	 * Attention la classe Observator != classe Observer de Java
	 */
	private Timer t;
	public static final int TIME = 1000;
	
	public Statistics(Graphe g){
		this.g = g;
		this.t = new Timer();
		this.obs = new ArrayList<Observator>();
		this.addObservator(new ColorVertexObservator(g));
		/**
		 *  on ajoute les observators que l'on souhaite
		 */
		this.addObservator(new ConflictsObservator(g));
		this.addObservator(new ThreadObservator(g));

	}
	
	public boolean addObservator(Observator ob){
		
		return obs.add(ob);
	}
	
	public Observator removeObservator(int i){
		
		return obs.remove(i);
	}
	
	public boolean removeObservator(Observator ob){
		
		return obs.remove(ob);
	}
	
	public void clearAllObservators(){
		
		 obs.clear();
	}
	
	/**
	 * Méthode permettant de démarrer les enregistrements réguliers des données provenant des
	 * observators
	 * @param delai
	 */
	public void startRecord(int delai){
		if(t == null){
			t = new Timer();
		}
		t.scheduleAtFixedRate(new StatisticsTask(this), new Date(System.currentTimeMillis()),delai);
	}
	
	/**
	 * Méthode permettant l'arrêt de l'enregistrement
	 */
	public void stopRecord(){
		
		if(t != null){
			t.cancel();
		}
		t = null;
		this.setChanged();
		this.notifyObservers(null);
	}
	
	/**
	 * Méthode permettant pour chaque type de résultat provenant des observators 
	 * de socker le type d'enregistrement et la dernière valeur lue pour cet enregistrement
	 */
	public void record(){
		Record c = new Record();
		for(Observator ob : obs){
			ob.update();
			c.addRecord(ob.recordType(), ob.getLastValue());
		}
		this.setChanged();
		this.notifyObservers(c);
	}
	

	public Graphe getGraphe() {
		return this.g;
	}

	/**
	 * Attention l'instance graphe pointe sur une référence non constante. Si chargement d'un autre
	 * graphe, cette méthode doit être appelée
	 * @param graphe
	 */
	public void setGraphe(Graphe graphe) {
		this.g = graphe;
		for(Observator ob : this.obs){
			ob.setGraphe(graphe);
		}
	}
}
