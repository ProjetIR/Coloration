package Plugins.Algorithm;

import java.awt.Color;
import java.util.Collection;


import Model.Vertex;
import Utils.RandomBetween;

public class VertexColorMax extends Thread {
	
	private Vertex v;
	private Collection<Vertex> neighbours;
	private Plugins.Algorithm.State state;
	private RandomBetween generator;
	private boolean sleep;

	
	public VertexColorMax(Vertex v,Collection<Vertex> neighbours,Plugins.Algorithm.State state,boolean sleep) {
		super();
			this.v = v;
			this.neighbours = neighbours;
			this.state = state;
			this.generator = new RandomBetween(System.currentTimeMillis());
			this.sleep = sleep;
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


	public void Wake(){
		this.sleep = false;
	}
	
	public void Sleeping(){
		this.sleep = true;
	}
	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		try {
			while(!this.isInterrupted()){
				if(! sleep){
				Color[] colors =  state.getColors();
				int[] repartition = getRepartionColor(colors, neighbours);
				int posColor = indiceFromColor(this.v.getInfo().getCol(), colors);
				int argmax = argMAX(repartition);
				if(repartition[posColor] > 0 ) {
					for(Vertex v : this.neighbours)
						if(this.v.getInfo().getCol().equals(v.getInfo().getCol()) && v.getDegree() < this.v.getDegree())
						synchronized (v) {
							v.getInfo().setCol(colors[argmax]);	
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
	
	private int argMIN(int[] tab){
		int min = Integer.MAX_VALUE;
		int argmin = 0;
		for(int i = 0 ;i <tab.length;i++){
			if(tab[i]<min){
				argmin = i;
				min = tab[i];
			}

		}
		return argmin;
	}
	
	private int argMAX(int[] tab){
		int max = Integer.MIN_VALUE;
		int argmax = 0;
		for(int i = 0 ;i <tab.length;i++){
			if(tab[i]>=max){
				argmax = i;
				max = tab[i];
			}

		}
		return argmax;
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
