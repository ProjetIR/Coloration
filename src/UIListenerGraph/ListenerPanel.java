package UIListenerGraph;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import Model.Edge;
import Model.GraphException;
import Model.InfoEdge;
import Model.InfoVertex;
import UI.Windows;
import UIGraph.GraphVisualizer;
import UIGraph.UIModel.EdgeUi;
import UIGraph.UIModel.VertexUI;


/**
 * Listener des actions utilisateurs sur le panel
 * @author KIEFFER
 *
 */
public class ListenerPanel implements MouseListener{

	private GraphVisualizer g;
	private InfoVertex dataVertex;
	private InfoEdge dataEdge;

	
	public ListenerPanel(GraphVisualizer g, InfoVertex dataVertex,
			InfoEdge dataEdge) {
		super();
		this.g = g;
		this.dataVertex = dataVertex;
		this.dataEdge = dataEdge;
	}
	
	
	public InfoVertex getDataVertex() {
		return dataVertex;
	}


	public void setDataVertex(InfoVertex dataVertex) {
		this.dataVertex = dataVertex;
	}


	public InfoEdge getDataEdge() {
		return dataEdge;
	}


	public void setDataEdge(InfoEdge dataEdge) {
		this.dataEdge = dataEdge;
	}

	/**
	 * Permet de créer des vertex et des edges
	 * shift + bouton1 souris sur le panel = créer des sommets
	 * shift + bouton3 sur 2 sommets sélectionnés = création d'une arrête entre les 2 sommets
	 * si il n'en existe pas sinon suppression de l'arrête existante
	 * 
	 * bouton3 sur 2 sommets sélectionnés = sélection/déselection arrête si elle existe
	 * shift 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		// ajouter un sommet
		if(e.getButton() == MouseEvent.BUTTON1 && e.isShiftDown()){
			try {
				VertexUI v = g.addVertexUI(dataVertex);
				Point absP = e.getLocationOnScreen();
				Point posParent = v.getParent().getLocationOnScreen();
				v.setLocation((new Point(absP.x-posParent.x,absP.y-posParent.y)));
				g.getPanel().invalidate();
				Windows.log.info("Création d'un Vertex "+v.getVertex().getId());
			} catch (CloneNotSupportedException e1) {
				// TODO Bloc catch généré automatiquement
				e1.printStackTrace();
			}
		}
		if(e.getButton() == MouseEvent.BUTTON2){
				System.out.println(g.getGraphe());
		}
		// créer une arrête
		if(e.getButton() == MouseEvent.BUTTON3){
			if(g.getNumberVertexSelected() == 2){ // si 2 sommets sélectionnés
				try {
					Iterator<VertexUI> it = g.getSelectedVertex().iterator();
					VertexUI a = it.next();
					VertexUI b = it.next();
					Edge edge = g.getGraphe().getEdge(a.getVertex(), b.getVertex());
				
					if(edge == null){ // si pas d'arrête entre les 2 sommets
						g.addEdgeUi(dataEdge, a, b, true);
						Windows.log.info("Création Edge entre Vertex "+a.getVertex().getId()+ " et Vertex "+b.getVertex().getId());
					}
					else{ // on supprimer l'arrête
						EdgeUi uie = g.findEdgeUiFromEdge(edge);
						if(e.isShiftDown()){
							g.removeEdgeUi(uie);
							Windows.log.info("Création Edge entre Vertex "+uie.getEdge().getStart().getId()+ " et Vertex "+uie.getEdge().getEnd().getId());
							
						}else{
							
							g.selectEdge(uie);
							String select = uie.isSelected()?"Sélection ":"Deselection ";
							Windows.log.info(select+"Edge entre Vertex "+uie.getEdge().getStart().getId()+ " et Vertex "+uie.getEdge().getEnd().getId());
						}
					}
					g.selectVertex(a);
					g.selectVertex(b);
					g.getPanel().invalidate();
				} catch (CloneNotSupportedException e1) {
					// TODO Bloc catch généré automatiquement
					e1.printStackTrace();
				} catch (GraphException e1) {
					// TODO Bloc catch généré automatiquement
					e1.printStackTrace();
				}
				
			}
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
