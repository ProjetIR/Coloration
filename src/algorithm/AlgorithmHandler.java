package algorithm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JOptionPane;

import Model.Graphe;
import UI.Windows;

/**
 * Classe permettant de gérer l'appel à différent algorithme.
 * On évite ainsi de démarrer plusieur algorithme en même temps
 * Le handler gère une file d'algoritme 
 * Lorsque l'utilisateur demande à l'application de démarrer un nouvel algorithme, le handler
 * vérifie le place dans une file d'attente et ne le démarre que lorsque les autres algorithmes
 * le précédent sont terminés. C'est un observateur des algorithmes, c'ets à dire qu'il attend la notification de
 * fin de l'algorithme courant pour en lancer un autre
 * @author emmanuel
 *
 */
public class AlgorithmHandler implements Observer{


	private Graphe g;
	private ConcurrentLinkedQueue<Algorithm> queue;
	private Algorithm current;
	
	public AlgorithmHandler(Graphe g) {
		super();
		this.g = g;
		this.queue = new ConcurrentLinkedQueue<Algorithm>();
		this.current = null;
	}
	/**
	 * A partir d'une classe, il construit une instance de manière dynamique et la place dans la file
	 * Il fait une tentative de démarrage. Si un algorithme est déjà démarré, il attend la fin de l'algorithme
	 * précédent pour démarrer le suivant
	 * @param algo
	 * @return
	 */
	public StateHandler addAlgorithm(Class algo){
		StateHandler state = null;
		try {
			Class[] parameterTypes = {Graphe.class};
			Constructor constructor = algo.getConstructor(parameterTypes);
			Object al = constructor.newInstance(g);
			queue.add(((Algorithm)al));
			boolean start = this.run();
			state = new StateHandler(queue.size(), this.current.getClass().getSimpleName(), start);
			JOptionPane.showMessageDialog(null, state.toString());
		} catch (SecurityException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		}
		
		return state;
	}
	
	/**
	 * La méthode permettant de démarrer l'algorithme
	 * @return
	 */
	public boolean run(){
		
		if(this.current != null) return false;
		this.current = queue.poll();
		if(this.current == null) return false;
		this.current.addObserver(this);
		this.current.compute();
		return true;
		
	}

	/**
	 * Cette méthode est importante car c'est elle qui permet l'enchaînement des algorithmes
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Stub de la méthode généré automatiquement
		this.current = null;
		this.run();
		
	}

	public void setGraphe(Graphe g) {
		this.g = g;
	}
	
	

}
