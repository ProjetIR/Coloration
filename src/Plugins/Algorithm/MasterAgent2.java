package Plugins.Algorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import algorithm.Algorithm;
import Model.Edge;
import Model.Graphe;
import Model.Vertex;
import Utils.RandomBetween;

public class MasterAgent2 extends Thread{

	private Algorithm coloration;
	private Collection<VertexAgent2> processus;
	private ArrayList<Edge> edges;
	private Graphe g;
	private Plugins.Algorithm.State state;
	private int totalConflits;
	private int oldTotalConflits;
	private int nbWakeUp;
	private int counter;
	private double temp;
	private RandomBetween generator;

	public MasterAgent2(Algorithm coloration,Graphe g,Collection<VertexAgent2> processus,Plugins.Algorithm.State state,int nbWakeUp) {
		super();
		this.coloration = coloration;
		this.processus = processus;
		this.g = g;
		this.edges = new ArrayList<Edge>(g.getAllEdges());
		this.state = state;
		totalConflits = 0;
		oldTotalConflits = 0;
		this.nbWakeUp = nbWakeUp;
		this.temp = this.edges.size();
		this.generator = new RandomBetween(System.currentTimeMillis());
	}



	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		try {
			while(!isInterrupted()){

				synchronized (this.state) {
					state.masterSleep = false;
					totalConflits = 0;
					for(Edge e : this.edges){
						Color start = e.getStart().getInfo().getCol();
						Color end = e.getEnd().getInfo().getCol();
						if(start.equals(end)){
							totalConflits++;
						}
					}
					System.out.println("Nb conflits : "+totalConflits);
					if(totalConflits == 0){
						interruptAll();
						throw new InterruptedException();
					}
					

					if(totalConflits == oldTotalConflits ){
						
						
						counter++;
						if(counter == nbWakeUp){
							Color c = generator.giveNewRandomColor(state.getCollectionColors());
							state.addColor(c);
							counter = 0;
						}
						
					}
				}

				oldTotalConflits = totalConflits;
				state.masterSleep = true;
				Thread.sleep(10);


			}
		
	} catch (InterruptedException e) {
		// TODO Bloc catch généré automatiquement
		System.out.println("Thread master interrompu -- fin de l'algorithme");	
	} 
			
}

private void interruptAll() {
	// TODO Stub de la méthode généré automatiquement
	for(VertexAgent2 t : processus){
		t.interrupt();
	}
	this.interrupt();
	this.notifyEnd();
}

private void notifyEnd(){
	this.coloration.end();
}


private int[] getRepartionColor(Color[] availableColor,Collection<Vertex> neighbours ) throws InterruptedException{

	int[] repartition = new int[availableColor.length];
	for(int i = 0;i<availableColor.length;i++){
		for(Vertex v : neighbours){

			if(availableColor[i].equals(v.getInfo().getCol())){
				repartition[i]++;
			}

		}

	}
	return repartition;
}

public int indiceFromColor(Color c,Color[] colors){
	for(int i =0;i<colors.length;i++){
		if(colors[i].equals(c)) return i;
	}
	return -1;
}

}
