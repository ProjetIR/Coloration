package UI.ListenerUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UI.Windows;

/**
 * Listener permettant d'ajouter au handler un nouvel alogorithme
 * Lorsque l'utilisateur clique sur le sous-menu de l'algorithme désiré, la requête
 * est transmise au handler qui gère cette demande en fonction de l'état de l'application
 * @author KIEFFER
 *
 */
public class AlgorithmListener implements ActionListener {

	
	private Windows win;
	private Class algo;
	
	
	public AlgorithmListener(Windows win, Class algo) {
		super();
		this.win = win;
		this.algo = algo;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Stub de la méthode généré automatiquement
		win.getHandler().addAlgorithm(algo);
	}
	
	
}
