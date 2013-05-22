package UIListenerGraph;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import UIGraph.GraphVisualizer;
import UIGraph.UIModel.EdgeUi;


/**
 * Listener des actions utilisateurs sur une arrête
 * @author KIEFFER
 *
 */
public class ListenerEdge implements MouseListener{

	private GraphVisualizer visu;
	
	public ListenerEdge(GraphVisualizer visu) {
		super();
		this.visu = visu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		// supprimer une arrête

		

			/**
			 * Attention retransmission des évènements aux panels qui gère
			 * l'intéraction arrêtes-utilisateurs
			 */
			EdgeUi uie = (EdgeUi)e.getSource();
			MouseListener[] listeners = uie.getParent().getMouseListeners();
			for(MouseListener ml : listeners){
				ml.mouseClicked(e);
				
			}

		 
		

		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
	}

}
