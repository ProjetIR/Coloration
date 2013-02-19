package Plugins.Algorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import Model.Edge;
import Model.Graphe;
import Model.Vertex;
import Model.Generator.RandomGenerator;
import Utils.RandomBetween;

public class MasterController extends Thread{
	
	private ColorationAlgorithm coloration;
	private Collection<VertexController> processus;
	private Collection<Edge> edges;
	private Plugins.Algorithm.State state;
	private int totalConflits;
	private int oldTotalConflits;
	private int nbWakeUp;
	private int counter;
	private RandomBetween generator;
	private double temp;
	
	public MasterController(ColorationAlgorithm coloration,Collection<VertexController> processus,Collection<Edge> edges,Plugins.Algorithm.State state,int nbWakeUp) {
		super();
		this.coloration = coloration;
		this.processus = processus;
		this.edges = edges;
		this.state = state;
		totalConflits = 0;
		oldTotalConflits = 0;
		this.nbWakeUp = nbWakeUp;
		this.generator = new RandomBetween(System.currentTimeMillis());
		this.setPriority(1);
		this.temp  = 0;
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
				/*
				 * A chaque réveille du maitre, il faut reaffecter les priorité aux vertices
				 * les plus en conlits
				 */
				for(VertexController t : processus){
					int prior = ColorationAlgorithm.givePriority(t.getNbConflicts(),totalConflits);
					t.setPriority(prior);
				}
				//System.out.println("Thread Master detects "+totalConflits +"conflicts" );
				if(totalConflits == 0){
					interruptAll();
					throw new InterruptedException();
				}
	
				if(totalConflits == oldTotalConflits ){
					
					counter++;
					if(counter == nbWakeUp){
						double val = probabilityFunction(temp++);
						if(Math.random() < val){
							int r1; 
							int r2; 
							do{
								r1 = generator.randomValue(0, processus.size()-1); 
								r2 = generator.randomValue(0, processus.size()-1);
							}while(r1 == r2);
							ArrayList<VertexController> vc = new ArrayList<VertexController>(processus);
							Color tmp = vc.get(r1).getVertex().getInfo().getCol();
							Color bis = vc.get(r2).getVertex().getInfo().getCol();
							vc.get(r1).getVertex().getInfo().setCol(bis);
							vc.get(r2).getVertex().getInfo().setCol(tmp);
							System.out.println(val);
						}else{
						Color c = generator.giveNewRandomColor(state.getCollectionColors());
						//System.out.println("Thread Master decides to generate a new Color" + c );
						state.addColor(c);
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
		for(VertexController t : processus){
			t.interrupt();
		}
		this.interrupt();
		this.notifyEnd();
	}
	
	private void notifyEnd(){
		this.coloration.end();
	}
	
	private double probabilityFunction(double val){
		int dim = edges.size();
		return Math.exp((-1.0)*val);
	}
	private int[] getRepartionColor(Color[] availableColor,Collection<VertexController> neighbours ) throws InterruptedException{
		
		int[] repartition = new int[availableColor.length];
		for(int i = 0;i<availableColor.length;i++){
			for(VertexController v : neighbours){
			
					if(availableColor[i].equals(v.getVertex().getInfo().getCol())){
						repartition[i]++;
					}
				
			}

		}
		return repartition;
	}
	
}
