package algorithm.Agents;

import java.awt.Color;
import java.util.Collection;
import Model.Vertex;

public class VertexController extends Thread {
	
	private Vertex v;
	private Collection<Vertex> neighbours;
	private algorithm.Agents.State state;
	
	public VertexController(Vertex v,Collection<Vertex> neighbours,algorithm.Agents.State state) {
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
				Color[] colors =  state.getColors();
				int[] repartition = getRepartionColor(colors, neighbours);
				int posColor = indiceFromColor(this.v.getInfo().getCol(), colors);
				int argmin = argMIN(repartition);
				if(posColor != argmin){
					
					if(repartition[posColor] != 0){
						
						synchronized (this.v) {
							//System.out.println("Thread vertex "+this.v.getId()+" change de couleur pour le "+colors[argmin]);
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
	
	private int argMIN(int[] tab){
		int min = Integer.MAX_VALUE;
		int argmin = 0;
		int[] values = new int[2];
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
