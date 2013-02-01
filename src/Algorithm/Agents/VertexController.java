package Algorithm.Agents;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

import Model.GraphException;
import Model.Graphe;
import Model.Vertex;

public class VertexController extends Thread {
	
	private Vertex v;
	private Collection<Vertex> neighbours;
	private MasterController master;
	
	public VertexController(Vertex v,Collection<Vertex> neighbours,MasterController master) {
		super();
		try {
		
			this.v = v;
			this.neighbours = neighbours;
			this.master = master;
			
			
		} catch (GraphException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		try {
			while(!this.isInterrupted()){
				

				
				
			}
		} catch (InterruptedException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	
	
	
	private int[] getRepartionColor(Color[] availableColor,Collection<Vertex> neighbours ) throws InterruptedException{
		
		int[] repartition = new int[availableColor.length];
		for(int i = 0;i<availableColor.length;i++){
			for(Vertex v : neighbours){
				synchronized (v) {
					if(availableColor[i] == v.getInfo().getCol()){
						repartition[i]++;
					}
				}
			}
		//System.out.println("Thread vertex "+this.v.getId()+" s'endort");
		//Thread.sleep(10);
		//System.out.println("Thread vertex "+this.v.getId()+" s'endort");
		}
		return repartition;
	}
	
	private int[] sommeAndargMIN(int[] tab){
		int min = Integer.MAX_VALUE;
		int argmin = 0;
		int somme = 0;
		int[] values = new int[2];
		for(int i = 0 ;i <tab.length;i++){
			if(tab[i]<min){
				argmin = i;
				min = tab[i];
			}
			somme+=tab[i];
		}
		values[0] = somme;
		values[1] = argmin;
		return values;
	}
	
	
}
