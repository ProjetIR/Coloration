package Plugins.Algorithm;

import java.awt.Color;
import java.util.Collection;


import Model.Vertex;
import Utils.RandomBetween;

public class VertexAgent2 extends Thread {
	
	private Vertex v;
	private Collection<Vertex> neighbours;
	private Plugins.Algorithm.State state;
	private RandomBetween generator;
	private double temp;

	
	public VertexAgent2(Vertex v,Collection<Vertex> neighbours,Plugins.Algorithm.State state) {
		super();
			this.v = v;
			this.neighbours = neighbours;
			this.state = state;
			this.generator = new RandomBetween(System.currentTimeMillis());
			this.temp = this.v.getDegree();
			
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
				if(state.masterSleep){
					Color[] colors =  state.getColors();
					int[] repartition = getRepartionColor(colors, neighbours);
					int posColor = indiceFromColor(this.v.getInfo().getCol(), colors);
					int argmin = argMIN(repartition);
					if(repartition[posColor] != 0) {
							int indice = generator.randomValue(0, colors.length-1);
							if(repartition[indice] < repartition[posColor]){
								synchronized (this.v) {
									this.v.getInfo().setCol(colors[indice]);	
								}
							}else{
								double proba = Math.random();
								if(proba >= Math.exp((-1.0)*(this.temp - repartition[posColor])/this.temp)){
									synchronized (this.v) {
										this.v.getInfo().setCol(colors[indice]);	
									}
								}
							}
							System.out.println(this.temp);
							this.temp = 0.6*this.temp;
						
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
	
	public int indiceFromColor(Color c,Color[] colors){
		
		for(int i =0;i<colors.length;i++){
			if(colors[i].equals(c)) return i;
		}
		return -1;
	}
	


	
}
