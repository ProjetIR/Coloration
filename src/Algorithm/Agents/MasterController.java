package Algorithm.Agents;

import java.awt.Color;
import java.util.Collection;

import Utils.RandomBetween;

public class MasterController extends Thread{
	
	private Collection<VertexController> processus;
	private Algorithm.Agents.State state;
	private int totalConflits;
	private int oldTotalConflits;
	private int nbThreadAlive;
	private int nbWakeUp;
	private int counter;
	private RandomBetween generator;
	
	public MasterController(Collection<VertexController> processus,Algorithm.Agents.State state,int nbWakeUp) {
		super();
		this.processus = processus;
		this.state = state;
		totalConflits = 0;
		oldTotalConflits = 0;
		nbThreadAlive = 0;
		this.nbWakeUp = nbWakeUp;
		this.generator = new RandomBetween(System.currentTimeMillis());
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		while(!isInterrupted()){
			try {
				System.out.println("Thread Master is wake up");
				totalConflits = 0;
				nbThreadAlive = 0;
				for(VertexController t : processus){
					totalConflits += t.getNbConflicts();
					if(!t.isSleep()) nbThreadAlive++;
				}
				System.out.println("Thread Master detects "+totalConflits +"conflicts" );
				if(totalConflits == oldTotalConflits){
					counter++;
					if(counter == nbWakeUp){
						System.out.println("Thread Master decides to generate a new Color" );
						counter = 0;
						Color c = generator.giveNewRandomColor(state.getCollectionColors());
						state.getCollectionColors().add(c);
					}
				}
				oldTotalConflits = totalConflits;
				System.out.println("Master Thread is going to sleep");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
			
		}
		
	}
	
	


}
