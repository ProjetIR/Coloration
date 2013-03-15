package statistics;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;

import Model.Graphe;
import Model.Vertex;

public class ColorVertexObservator extends Observator {
	
	

	public ColorVertexObservator(Graphe g) {
		super(g);
		// TODO Stub du constructeur généré automatiquement
	}

	@Override
	public void update() {
		// TODO Stub de la méthode généré automatiquement
		Collection<Vertex> vertices = g.getAllVertex();
		HashSet<Color> ensColor = new HashSet<Color>();
		int cpt = 0;
		for(Vertex v : vertices){
			synchronized(v){
				if(!(ensColor.contains(v.getInfo().getCol()))){
				ensColor.add(v.getInfo().getCol());
				cpt++;
				}
			}
		}
		this.values.add(cpt);
	}

	@Override
	public String recordType() {
		// TODO Stub de la méthode généré automatiquement
		return "Number of different Colors";
	}


}
