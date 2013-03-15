package statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;

import algorithm.AlgorithmHandler;

import statistics.IO.StatisticsWriter;

import Model.Graphe;
import UI.Task;

public class Statistics extends Observable{

	private Graphe g;
	private ArrayList<Observator> obs;
	private Timer t;
	public static final int TIME = 1000;
	
	public Statistics(Graphe g){
		this.g = g;
		this.t = new Timer();
		this.obs = new ArrayList<Observator>();
		this.addObservator(new ColorVertexObservator(g));
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
	
	public void startRecord(int delai){
		if(t == null){
			t = new Timer();
		}
		t.scheduleAtFixedRate(new StatisticsTask(this), new Date(System.currentTimeMillis()),delai);
	}
	
	public void stopRecord(){
		
		t.cancel();
		t = null;
		this.setChanged();
		this.notifyObservers(null);
	}
	
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


	public void setGraphe(Graphe graphe) {
		this.g = graphe;
		for(Observator ob : this.obs){
			ob.setGraphe(graphe);
		}
	}
}
