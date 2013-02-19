package statictics;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;

import Model.Graphe;
import UI.Task;

public class Statictics extends Observable{

	private Graphe g;
	private ArrayList<Observator> obs;
	private Timer t;
	
	public Statictics(Graphe g){
		this.g = g;
		this.t = new Timer();
		this.obs = new ArrayList<Observator>();
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
		
		t.scheduleAtFixedRate(new StatisticsTask(this), new Date(System.currentTimeMillis()),delai);
	}
	
	public void stopRecord(){
		
		t.cancel();
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
}
