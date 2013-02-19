package algorithm;

import java.awt.Panel;
import java.awt.Point;
import java.util.Collection;


import Model.Graphe;
import Model.Vertex;
import Utils.RandomBetween;


public class RandomDisposition extends Algorithm {

	private Panel p;
	private Graphe g;
	private RandomBetween gen;

	
	public RandomDisposition(Panel p,Graphe g) {
		super();
		this.p = p;
		this.g=g;
		this.gen = new RandomBetween(System.currentTimeMillis());
	}
	
	public void compute() {
		// TODO Stub de la méthode généré automatiquement
		Collection<Vertex> vertex = g.getAllVertex();
		for(Vertex v :vertex){
			int x = gen.randomValue(2*v.getInfo().getRayon(), p.getWidth()- 2*v.getInfo().getRayon());
			int y = gen.randomValue(2*v.getInfo().getRayon(), p.getHeight()- 2*v.getInfo().getRayon());
			v.getInfo().setCoord(new Point(x,y));
		}

	}

	@Override
	protected AResult sendAResult() {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}





}
