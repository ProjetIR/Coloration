package Plugins.Algorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

import algorithm.AResult;
import algorithm.Algorithm;

import sun.awt.windows.ThemeReader;

import Model.GraphException;
import Model.Graphe;
import Model.Vertex;

public class ColorationAlgorithm  extends Algorithm {
	
	private Graphe g;
	private ArrayList<VertexController> processus;
	private State state;
	private MasterController master;
	

	public ColorationAlgorithm(Graphe g){
		
		try {
			this.g = g;
			this.setOneColor();
			int numberOfVertices = g.getVertexNumber();
			ArrayList<Color> col = new ArrayList<Color>();
			col.add(Color.red);
			state = new State(col,g.getEdgesNumber()/numberOfVertices*(1.0));
			processus = new ArrayList<VertexController>();
			for(Vertex v : g.getAllVertex()){
				System.out.println("Vertex "+v+" , degree = "+v.getDegree());
				int degree = v.getDegree();
				int prior = givePriority(degree, numberOfVertices);
				VertexController vc = new VertexController(v, g.getNeighbours(v), state);
				//vc.setPriority(prior);
				processus.add(vc);
			}
			this.master = new MasterController(this,processus,g.getAllEdges(), state, 3);
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
	protected AResult sendAResult() {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}




	

}
