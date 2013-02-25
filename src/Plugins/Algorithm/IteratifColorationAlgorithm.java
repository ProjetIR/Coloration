package Plugins.Algorithm;

import Model.Graphe;
import algorithm.AResult;
import algorithm.Algorithm;

public class IteratifColorationAlgorithm extends Algorithm {

	private Graphe g;
	
	
	
	public IteratifColorationAlgorithm(Graphe g) {
		super();
		this.g = g;
	}

	@Override
	public void compute() {
		// TODO Stub de la méthode généré automatiquement
		IteratifColoration it = new IteratifColoration(this,g);
		it.start();
	}

	@Override
	protected AResult sendAResult() {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

}
