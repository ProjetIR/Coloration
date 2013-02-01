package Model.LayoutAlgorithm;

import java.awt.Panel;
import java.awt.Point;
import java.util.Collection;

import Model.Graphe;
import Model.Vertex;
import Utils.RandomBetween;


public class RandomDisposition implements VisualAlgorithm {

	private Panel p;
	private RandomBetween gen;
	
	
	public RandomDisposition(Panel p) {
		super();
		this.p = p;
		this.gen = new RandomBetween(System.currentTimeMillis());
	}


	@Override
	public void place(Graphe g) {
		// TODO Stub de la méthode généré automatiquement
		Collection<Vertex> vertex = g.getAllVertex();
		for(Vertex v :vertex){
			int x = gen.randomValue(2*v.getInfo().getRayon(), p.getWidth()- 2*v.getInfo().getRayon());
			int y = gen.randomValue(2*v.getInfo().getRayon(), p.getHeight()- 2*v.getInfo().getRayon());
			v.getInfo().setCoord(new Point(x,y));
		}
	}



}
