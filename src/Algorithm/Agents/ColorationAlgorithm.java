package Algorithm.Agents;

import java.awt.Color;
import java.util.ArrayList;

import Model.GraphException;
import Model.Graphe;
import Model.Vertex;

public class ColorationAlgorithm {
	
	private Graphe g;
	private ArrayList<VertexController> processus;
	private State state;
	private MasterController master;
	
	public ColorationAlgorithm(Graphe g){
		
		try {
			this.g = g;
			ArrayList<Color> col = new ArrayList<Color>();
			col.add(Color.red);
			state = new State(col, false);
			processus = new ArrayList<VertexController>();
			for(Vertex v : g.getAllVertex()){
				
				processus.add(new VertexController(v, g.getNeighbours(v), state));
			}
			this.master = new MasterController(processus, state, 3);
		} catch (GraphException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	
	public void start(){
		
		for(Thread p : processus){
			p.start();
		}
		this.master.start();
	}

}
