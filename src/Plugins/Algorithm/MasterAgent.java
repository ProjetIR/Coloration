package Plugins.Algorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import algorithm.Algorithm;

import Model.Edge;
import Model.Graphe;
import Model.Vertex;
import Model.Generator.RandomGenerator;
import Utils.RandomBetween;

public class MasterAgent extends Thread{
	
	private Algorithm coloration;
	private Collection<VertexMin> processus;
	private Collection<VertexColorMax> agents;
	private Collection<Edge> edges;
	private Plugins.Algorithm.State state;
	private int totalConflits;
	private int oldTotalConflits;
	private int nbWakeUp;
	private int counter;
	private RandomBetween generator;
	private static int MINIMISATION = 0;
	private static int COLORMAX = 1;
	private int currentStep;
	
	public MasterAgent(Algorithm coloration,Collection<VertexMin> processus,Collection<VertexColorMax> agents,Collection<Edge> edges,Plugins.Algorithm.State state,int nbWakeUp) {
		super();
		this.coloration = coloration;
		this.processus = processus;
		this.agents = agents;
		this.edges = edges;
		this.state = state;
		totalConflits = 0;
		oldTotalConflits = 0;
		this.nbWakeUp = nbWakeUp;
		this.generator = new RandomBetween(System.currentTimeMillis());
		this.currentStep = 0;
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		try {
			while(!isInterrupted()){
			  
			  synchronized (this.state) {
			
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
						currentStep = (currentStep+1)%2;
						
						if(currentStep == MINIMISATION){
							System.out.println("Passage maximisation couleur voisinage");
							for(VertexMin t : processus){
								t.Sleeping();
							}
							for(VertexColorMax t : agents){
								t.Wake();
							}
						}else{
							
							System.out.println("Passage minimisation conflits");
							Color c = generator.giveNewRandomColor(state.getCollectionColors());
							state.addColor(c);
							for(VertexMin t : processus){
								t.Wake();
							}
							for(VertexColorMax t : agents){
								t.Sleeping();
							}
						}
						

						counter = 0;
					}
				}
				
				oldTotalConflits = totalConflits;
				Thread.sleep(10);
			
			
			}
		  }
		} catch (InterruptedException e) {
			// TODO Bloc catch généré automatiquement
			System.out.println("Thread master interrompu -- fin de l'algorithme");
			Color[] c = state.getColors();
			int total = 0;
			try {
				int [] nbColor = getRepartionColor(c, this.processus);
				for(int i = 0;i<c.length;i++){
					System.out.println(c[i]+" nombre = "+nbColor[i]);
					if(nbColor[i] != 0){
						total++;
					}
				}
				System.out.println("Nombre de couleur: "+total);
			} catch (InterruptedException e1) {
				// TODO Bloc catch généré automatiquement
				e1.printStackTrace();
			}
			
			
		}
	}

	private void interruptAll() {
		// TODO Stub de la méthode généré automatiquement
		for(VertexMin t : processus){
			t.interrupt();
		}
		for(VertexColorMax t : agents){
			t.interrupt();
		}
		this.interrupt();
		this.notifyEnd();
	}
	
	private void notifyEnd(){
		this.coloration.end();
	}
	

	private int[] getRepartionColor(Color[] availableColor,Collection<VertexMin> neighbours ) throws InterruptedException{
		
		int[] repartition = new int[availableColor.length];
		for(int i = 0;i<availableColor.length;i++){
			for(VertexMin v : neighbours){
			
					if(availableColor[i].equals(v.getVertex().getInfo().getCol())){
						repartition[i]++;
					}
				
			}

		}
		return repartition;
	}
	
}
