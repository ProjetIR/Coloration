package algorithm;

/**
 * Classe permettant de décrire les informations dans la file d'algorithmes
 * @author KIEFFER
 *
 */
public class StateHandler {

	private int numberInQueue;
	private String currentAddAlgo;
	private boolean currentStart;
	public StateHandler(int numberInQueue, String currentAddAlgo,
			boolean curentWait) {
		super();
		this.numberInQueue = numberInQueue;
		this.currentAddAlgo = currentAddAlgo;
		this.currentStart = currentStart;
	}
	public int getNumberInQueue() {
		return numberInQueue;
	}
	public String getCurrentAddAlgo() {
		return currentAddAlgo;
	}
	public boolean isCurrentStart() {
		return currentStart;
	}
	@Override
	public String toString() {
		return "StateHandler [numberInQueue=" + numberInQueue
				+ ", currentAddAlgo=" + currentAddAlgo + ", curentWait="
				+ currentStart + "]";
	}
	
	
	
}
