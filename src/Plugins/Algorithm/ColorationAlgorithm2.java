package Plugins.Algorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import algorithm.Algorithm;
import Model.GraphException;
import Model.Graphe;
import Model.Vertex;

public class ColorationAlgorithm2  extends Algorithm {
	
	private Graphe g;
	private ArrayList<VertexAgent2> processus;
	private State state;
	private MasterAgent2 master;
	

	public ColorationAlgorithm2(Graphe g){
		super();
		try {
			this.g = g;
			this.setOneColor();
			ArrayList<Color> col = new ArrayList<Color>();
			col.add(Color.red);
			col.add(Color.blue);
			state = new State(col);
			processus = new ArrayList<VertexAgent2>();
			ArrayList<Vertex> vertices = new ArrayList<Vertex>(g.getAllVertex());
			for(Vertex v : vertices){
				System.out.println("Vertex "+v+" , degree = "+v.getDegree());
				VertexAgent2 vc = new VertexAgent2(v, g.getNeighbours(v), state);
				processus.add(vc);
			}
			this.master = new MasterAgent2(this,this.g,processus,state, 10);
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
