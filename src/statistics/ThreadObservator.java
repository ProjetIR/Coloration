package statistics;

import java.lang.Thread.State;

import Model.Graphe;

public class ThreadObservator extends Observator{

	public ThreadObservator(Graphe g) {
		super(g);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		Thread[] tab = new Thread[Thread.activeCount()];
		int cpt = 0;
		for(Thread t : tab){
			if(t != null && t.getState().equals(State.TIMED_WAITING)){
				cpt++;
			}
		}
		
		this.values.add(Thread.activeCount() - cpt);
		
	}

	@Override
	public String recordType() {
		// TODO Auto-generated method stub
		return "Nombre de Threads Actifs";
	}

}
