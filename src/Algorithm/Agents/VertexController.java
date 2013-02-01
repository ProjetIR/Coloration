package Algorithm.Agents;

import java.awt.Color;
import java.util.Collection;

import Model.GraphException;
import Model.Graphe;
import Model.Vertex;

public class VertexController extends Thread {
	
	private Vertex v;
	private Graphe graphe;
	private Collection<Vertex> neighbours;
	private int conflicts;
	
	public VertexController(Vertex v, Graphe graphe) {
		super();
		try {
		
			this.v = v;
			this.graphe = graphe;
			neighbours = graphe.getNeighbours(v);
			conflicts = computeConflictsNumber();
			
		} catch (GraphException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		while(!this.isInterrupted()){
			Color minimiseConflicts;
			for(Vertex vertex : neighbours){
				
				synchronized (vertex) {
					
					if(vertex.getInfo().getCol() == v.getInfo().getCol()){
						
					}
				}
			}
			
		}
	}
	
	private int computeConflictsNumber(){
		int nb = 0;
		for(Vertex vertex : neighbours){
			if(v.getInfo().getCol() == v.getInfo().getCol()){
				nb++;
			}
		}
		return nb;
	}
	
	

}
