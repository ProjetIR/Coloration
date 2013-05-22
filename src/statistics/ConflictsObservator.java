package statistics;

import java.awt.Color;

import Model.Edge;
import Model.Graphe;

/**
 * Classe permettant d'observer le nombre de conflicts à un instant
 * @author KIEFFER
 *
 */
public class ConflictsObservator extends Observator {

	public ConflictsObservator(Graphe g) {
		super(g);
		// TODO Stub du constructeur généré automatiquement
	}

	@Override
	public void update() {
		// TODO Stub de la méthode généré automatiquement
		int cpt = 0;
		for(Edge e: g.getAllEdges()){
			Color start = e.getStart().getInfo().getCol();
			Color end = e.getEnd().getInfo().getCol();
			if(start.equals(end)){
				cpt++;
			}
		}
		this.values.add(cpt);
	}

	@Override
	public String recordType() {
		// TODO Stub de la méthode généré automatiquement
		return "Number of conflicts";
	}



}
