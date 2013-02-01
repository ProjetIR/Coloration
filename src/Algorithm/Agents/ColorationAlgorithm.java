package Algorithm.Agents;

import java.awt.Color;
import java.util.ArrayList;

import Model.GraphException;
import Model.Graphe;
import Model.Vertex;

public class ColorationAlgorithm {
	
	private Graphe g;
	private ArrayList<Thread> processus;
	private State state;
	
	public ColorationAlgorithm(Graphe g){
		
		try {
			this.g = g;
			ArrayList<Color> col = new ArrayList<Color>();
			col.add(Color.red);
			col.add(Color.GREEN);
			col.add(Color.BLACK);
			state = new State(col, false);
			processus = new ArrayList<Thread>();
			for(Vertex v : g.getAllVertex()){
				
				processus.add(new Thread(new VertexController(v, g.getNeighbours(v), state)));
			}
		} catch (GraphException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	
	public void start(){
		
		for(Thread p : processus){
			p.start();
		}
	}

}
