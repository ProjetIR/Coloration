package statictics;

import java.lang.Thread.State;
import java.util.Collection;

import Model.Graphe;
import Plugins.Algorithm.VertexController;

public class ProcessusObservator extends Observator {

	private Collection<VertexController> processus;
	
	public ProcessusObservator(Collection<VertexController> processus) {
		super(null);
		this.processus = processus;
		// TODO Stub du constructeur généré automatiquement
	}

	@Override
	public void update() {
		// TODO Stub de la méthode généré automatiquement
		int cpt = 0;
		for(VertexController v : processus){
			if(v.getState() == State.TIMED_WAITING){
				cpt++;
			}
		}
		this.values.add(cpt);

	}

	@Override
	public String recordType() {
		// TODO Stub de la méthode généré automatiquement
		return "Number of wainting thread";
	}



}
