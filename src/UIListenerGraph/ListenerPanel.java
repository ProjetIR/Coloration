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
import UIGraph.GraphVisualizer;
import UIGraph.UIModel.EdgeUi;
import UIGraph.UIModel.VertexUI;


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
	 * bouton1 sur le panel = créer des sommets
	 * bouton3 sur le panel = créer une arrete entre deux sommets selectionnés
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
			if(g.getNumberVertexSelected() == 2){
				try {
					Iterator<VertexUI> it = g.getSelectedVertex().iterator();
					VertexUI a = it.next();
					VertexUI b = it.next();
					Edge edge = g.getGraphe().getEdge(a.getVertex(), b.getVertex());
				
					if(edge == null){
						g.addEdgeUi(dataEdge, a, b, true);

					}
					else{
						EdgeUi uie = g.findEdgeUiFromEdge(edge);
						if(e.isShiftDown()){
							g.removeEdgeUi(uie);
							
							
						}else{
							
							g.selectEdge(uie);
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
