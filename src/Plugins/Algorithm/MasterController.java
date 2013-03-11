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
	private double maxDegree;
	
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
		this.maxDegree  = this.maxdegree(processus);
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
						double val = probabilityFunction(this.state.getNumberColors(),maxDegree);
						if(Math.random() > val){
							
							int r1;
							Color tmp;
							Color bis;
							ArrayList<Edge> vc;
							do{
							r1 = generator.randomValue(0, edges.size()-1); 
							vc = new ArrayList<Edge>(edges);
							tmp = vc.get(r1).getStart().getInfo().getCol();
							bis = vc.get(r1).getEnd().getInfo().getCol();
							}while(tmp.equals(bis));
							vc.get(r1).getStart().getInfo().setCol(bis);
							vc.get(r1).getEnd().getInfo().setCol(tmp);
							System.out.println(val);
						}else{
							if(this.state.getNumberColors() < this.maxDegree + 1){
								Color c = generator.giveNewRandomColor(state.getCollectionColors());
								state.addColor(c);
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
		for(VertexController t : processus){
			t.interrupt();
		}
		this.interrupt();
		this.notifyEnd();
	}
	
	private void notifyEnd(){
		this.coloration.end();
	}
	
	private double probabilityFunction(int nbColor,double val){
		return Math.exp((-1.0)*nbColor/val);
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
	
	private int maxdegree(Collection<VertexController> liste){
		int max = Integer.MIN_VALUE;
		for(VertexController v : liste){
			int degree = v.getVertex().getDegree();
			if(degree > max) max = degree;
		}
		return max;
	}
	
}
