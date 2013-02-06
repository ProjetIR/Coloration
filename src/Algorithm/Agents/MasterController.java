package Algorithm.Agents;

import java.awt.Color;
import java.util.Collection;

import Model.Edge;
import Model.Graphe;
import Model.Vertex;
import Utils.RandomBetween;

public class MasterController extends Thread{
	
	private Collection<VertexController> processus;
	private Collection<Edge> edges;
	private Algorithm.Agents.State state;
	private int totalConflits;
	private int oldTotalConflits;
	private int nbWakeUp;
	private int counter;
	private RandomBetween generator;
	
	public MasterController(Collection<VertexController> processus,Collection<Edge> edges,Algorithm.Agents.State state,int nbWakeUp) {
		super();
		this.processus = processus;
		this.edges = edges;
		this.state = state;
		totalConflits = 0;
		oldTotalConflits = 0;
		this.nbWakeUp = nbWakeUp;
		this.generator = new RandomBetween(System.currentTimeMillis());
		this.setPriority(1);
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
						counter = 0;
						Color c = generator.giveNewRandomColor(state.getCollectionColors());
						//System.out.println("Thread Master decides to generate a new Color" + c );
						state.getCollectionColors().add(c);
					}
				}
				oldTotalConflits = totalConflits;
				Thread.sleep(10);
			
			
			}
		  }
		} catch (InterruptedException e) {
			// TODO Bloc catch généré automatiquement
			System.out.println("Thread master interrompu -- fin de l'algorithme");
			System.out.println("Nombre de couleur: "+state.getNumberColors());
		}
	}

	private void interruptAll() {
		// TODO Stub de la méthode généré automatiquement
		for(VertexController t : processus){
			t.interrupt();
		}
		this.interrupt();
	}
}
