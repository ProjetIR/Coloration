package Plugins.Algorithm;

import Model.Graphe;
import algorithm.AResult;
import algorithm.Algorithm;

public class IteratifColorationAlgorithm extends Algorithm {

	private Graphe g;
	private IteratifColoration it;
	
	
	public IteratifColorationAlgorithm(Graphe g) {
		super();
		this.g = g;
	}

	@Override
	public void compute() {
		// TODO Stub de la méthode généré automatiquement
		it = new IteratifColoration(this,g);
		it.start();
	}

	@Override
	protected String sendAResult() {
		// TODO Stub de la méthode généré automatiquement
		return "";
	}

}
