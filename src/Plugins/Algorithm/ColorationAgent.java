package Plugins.Algorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;



import algorithm.Algorithm;




import Model.GraphException;
import Model.Graphe;
import Model.Vertex;

public class ColorationAgent  extends Algorithm {
	
	private Graphe g;
	private ArrayList<VertexMin> processus;
	private ArrayList<VertexColorMax> agents;
	private State state;
	private MasterAgent master;
	

	public ColorationAgent(Graphe g){
		super();
		try {
			this.g = g;
			this.setOneColor();
			int numberOfVertices = g.getVertexNumber();
			ArrayList<Color> col = new ArrayList<Color>();
			col.add(Color.red);
			col.add(Color.blue);
			state = new State(col,0,maxdegree(this.g.getAllVertex()));
			processus = new ArrayList<VertexMin>();
			agents = new ArrayList<VertexColorMax>();
			for(Vertex v : g.getAllVertex()){
				System.out.println("Vertex "+v+" , degree = "+v.getDegree());
				int degree = v.getDegree();
				int prior = givePriority(degree, numberOfVertices);
				VertexMin vc = new VertexMin(v, g.getNeighbours(v), state,false);
				VertexColorMax vcmax = new VertexColorMax(v, g.getNeighbours(v), state,true);
				vc.setPriority(prior);
				vcmax.setPriority(prior);
				processus.add(vc);
				agents.add(vcmax);
			}
			this.master = new MasterAgent(this,processus,agents,g.getAllEdges(), state, 3);
		} catch (GraphException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	
	public void setOneColor(){
		for(Vertex v : g.getAllVertex()){
			v.getInfo().setCol(Color.RED);
		}
	}
	@Override
	public void compute() {
		// TODO Stub de la méthode généré automatiquement
		for(Thread p : processus){
			p.start();
		}
		for(Thread p : agents){
			p.start();
		}
		this.master.start();
	

	}
	
	/**
	 * 
	 * High priority is given to thread which works on high dregree vertex
	 * 
	 * Let's x the dregree of the vertex V
	 * 
	 * We know =>  0<=x=<=N-1  where N is the number of vertices
	 *             0<= (52/N-1) <=1
	 *Thread priority on Java platform =>  MIN_PRIORITY <= P <= MAX_PRIORITY
	 *		
	 *				0 <= (x/N-1)*(MAX_PRIORITY - MIN_PRIORITY) <= MAX_PRIORITY - MIN_PRIORITY
	 *				MIN_PRIORITY <= (x/N-1)*(MAX_PRIORITY - MIN_PRIORITY) + MIN_PRIORITY <= MAX_PRIORITY
	 * 
	 *
	 */
	public static int givePriority(int degree,int nb){
		
		double d = (double)degree;
		return (int)(d/Math.max(nb,1))*(Thread.MAX_PRIORITY-Thread.MIN_PRIORITY) + Thread.MIN_PRIORITY;
	}


	@Override
	protected String sendAResult() {
		// TODO Stub de la méthode généré automatiquement
		return "Fin d'algorithme : durée = "+this.duration+"ms. Nombre de couleurs : "+this.state.getNumberColors();
	}


	private int maxdegree(Collection<Vertex> liste){
		int max = Integer.MIN_VALUE;
		for(Vertex v : liste){
			int degree = v.getDegree();
			if(degree > max) max = degree;
		}
		return max;
	}

	

}
