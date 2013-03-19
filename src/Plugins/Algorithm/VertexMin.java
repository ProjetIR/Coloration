package Plugins.Algorithm;

import java.awt.Color;
import java.util.Collection;
import Model.Vertex;
import Utils.RandomBetween;

public class VertexMin extends Thread {
	
	private Vertex v;
	private Collection<Vertex> neighbours;
	private Plugins.Algorithm.State state;


	
	public VertexMin(Vertex v,Collection<Vertex> neighbours,Plugins.Algorithm.State state,boolean sleep) {
		super();
			this.v = v;
			this.neighbours = neighbours;
			this.state = state;

	}
	
	public Vertex getVertex(){
		
		return v;
	}
	
	public int getNbConflicts() {
		try {
			Color[] colors =  state.getColors();
			int posColor = indiceFromColor(this.v.getInfo().getCol(), colors);
			int[] repartition = getRepartionColor(colors, neighbours);
			return repartition[posColor];
		} catch (InterruptedException e) {
			// TODO Bloc catch généré automatiquement
			return -1;
		}
	}


	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		try {
			while(!this.isInterrupted()){
				if( ((Plugins.Algorithm.State) state).getCurrentDegree() >= this.v.getDegree()){
					Color[] colors =  state.getColors();
					int[] repartition = getRepartionColor(colors, neighbours);
					int posColor = indiceFromColor(this.v.getInfo().getCol(), colors);
					int argmin = argMIN(repartition,colors);
					if(repartition[posColor] > 0) {
						 if(repartition[posColor] != repartition[argmin])
							synchronized (this.v) {
							this.v.getInfo().setCol(colors[argmin]);	
							}
						
					}
				}
				Thread.sleep(10);
				
					
			}
		} catch (InterruptedException e) {
			// TODO Bloc catch généré automatiquement
			//System.out.println("Mort du Thread vertex "+this.v.getId());
		}
	}

	private int[] getRepartionColor(Color[] availableColor,Collection<Vertex> neighbours ) throws InterruptedException{
		
		int[] repartition = new int[availableColor.length];
		for(int i = 0;i<availableColor.length;i++){
			for(Vertex v : neighbours){
				synchronized (v) {
					if(availableColor[i].equals(v.getInfo().getCol())){
						repartition[i]++;
					}
				}
			}

		}
		return repartition;
	}
	
	private int argMIN(int[] tab,Color[] colors){
		int min = Integer.MAX_VALUE;
		int argmin = 0;
		for(int i = 0 ;i <tab.length;i++){
			if(tab[i]<min && (!isVertexHigherDegreeWith(colors[i]))){
				argmin = i;
				min = tab[i];
			}

		}
		return argmin;
	}
	
	private boolean isVertexHigherDegreeWith(Color c){
		
		for(Vertex v : this.neighbours){
			if(v.getInfo().getCol().equals(c) && v.getDegree() > this.v.getDegree()){
				return true;
			}
		}
		return false;
	}
	
	public int indiceFromColor(Color c,Color[] colors){
		
		for(int i =0;i<colors.length;i++){
			if(colors[i].equals(c)) return i;
		}
		return -1;
	}
	
	public int nbConflicts(){
		int val = 0;
		for(Vertex v : this.neighbours){
			if(this.v.getInfo().getCol().equals(v.getInfo().getCol())){
				val++;
			}
		}
		return val;
	}

	
}
