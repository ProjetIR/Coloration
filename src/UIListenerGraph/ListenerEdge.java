package UIListenerGraph;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import UIGraph.GraphVisualizer;
import UIGraph.UIModel.EdgeUi;



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
