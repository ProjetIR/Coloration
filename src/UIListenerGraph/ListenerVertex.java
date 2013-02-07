package UIListenerGraph;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.GraphException;
import UI.Windows;
import UIGraph.GraphVisualizer;
import UIGraph.UIModel.VertexUI;


public class ListenerVertex implements MouseListener{

	private GraphVisualizer visu;
	
	public ListenerVertex(GraphVisualizer visu) {
		super();
		this.visu = visu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		try {
			// selectionner un sommet
			if(e.getButton() == MouseEvent.BUTTON1){
				VertexUI uiv = (VertexUI)e.getSource();
				visu.selectVertex(uiv);
				visu.getPanel().invalidate();
				Windows.log.info((uiv.isSelected()?"Selection ":"Deselection ")+"Vertex "+uiv.getVertex().getId());
			}
			// supprimer un sommet
			if(e.getButton() == MouseEvent.BUTTON3 && e.isShiftDown()){
				VertexUI uiv = (VertexUI)e.getSource();
				visu.removeVertexUi(uiv);
				visu.getPanel().invalidate();
				Windows.log.info("Suppression Vertex "+uiv.getVertex().getId());
			}
		} catch (GraphException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
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
