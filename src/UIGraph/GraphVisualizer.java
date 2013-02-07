package UIGraph;

import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import Model.Edge;
import Model.GraphException;
import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;
import Model.Vertex;
import Model.LayoutAlgorithm.VisualAlgorithm;
import UIGraph.UIModel.EdgeUi;
import UIGraph.UIModel.VertexUI;
import UIListenerGraph.ListenerEdge;
import UIListenerGraph.ListenerPanel;
import UIListenerGraph.ListenerVertex;
import UIListenerGraph.Motion;




public class GraphVisualizer {
	
	private Graphe g;
	private Panel pan;
	private HashSet<VertexUI> selectedVertex;
	private HashSet<EdgeUi> selectedEdge;
	private InfoVertex defaultVertex;
	private InfoEdge defaultEdge;
	
	public GraphVisualizer(Graphe g, Panel pan,InfoVertex defaultVertex,InfoEdge defaultEdge) throws Exception {
		super();
		if(g == null || pan == null || defaultVertex==null || defaultEdge == null){
			throw new Exception("Aucun paramètre de ce constructeur ne peut etre null");
		}
		this.g = g;
		this.pan = pan;
		this.selectedVertex  = new HashSet<VertexUI>();
		this.selectedEdge = new HashSet<EdgeUi>();
		this.defaultVertex = defaultVertex;
		this.defaultEdge = defaultEdge;
		this.pan.addMouseListener(new ListenerPanel(this,this.defaultVertex, this.defaultEdge));
		addUIComponents();

	}
	
	public void addUIComponents() {
		
		Collection<Vertex> vertex = g.getAllVertex();
		for(Vertex v: vertex){
			VertexUI uiv = new VertexUI(v);
			uiv.addMouseMotionListener(new Motion(this));
			uiv.addMouseListener(new ListenerVertex(this));
			this.pan.add(uiv,0);
		}
		/**
		 * On insère pas les arretes doublons dans le cas d'un graphe non orienté
		 */
		Collection<Edge> edges = g.getAllEdges();
		HashSet<Edge> h = new HashSet<Edge>(edges);
		Iterator<Edge> it = h.iterator();
		while(it.hasNext()){
			EdgeUi uie = new EdgeUi(it.next());
			uie.addMouseListener(new ListenerEdge(this));
			this.pan.add(uie);
		}
	}
	
	public void removeAllUIComponents(){
		this.pan.removeAll();
		this.selectedVertex.clear();
		this.selectedEdge.clear();
	}
	
	
	
	public Graphe getGraphe() {
		return g;
	}
	
	
	
	public void setGraphe(Graphe g) {
		this.g = g;
	}

	public Panel getPanel() {
		return pan;
	}

	
	
	public InfoVertex getDefaultVertex() {
		return defaultVertex;
	}

	public void setDefaultVertex(InfoVertex defaultVertex) throws Exception {
		if(defaultVertex == null){
			throw new Exception("Ce paramètre ne peut être dédinie null");
		}
		this.defaultVertex = defaultVertex;
	}

	public InfoEdge getDefaultEdge() {
		return defaultEdge;
	}

	public void setDefaultEdge(InfoEdge defaultEdge) throws Exception {
		if(defaultEdge == null){
			throw new Exception("Ce paramètre ne peut être dédinie null");
		}
		this.defaultEdge = defaultEdge;
	}

	public VertexUI addVertexUI(InfoVertex data) throws CloneNotSupportedException {
		
		Vertex v = g.addVertex((InfoVertex)data.clone());
		VertexUI uiv = new VertexUI(v);
		uiv.addMouseMotionListener(new Motion(this));
		uiv.addMouseListener(new ListenerVertex(this));
		this.pan.add(uiv,0);
		return uiv;
		
	}
	
	public void removeVertexUi(VertexUI uiv) throws GraphException{
		
		selectedVertex.remove(uiv);
		Vertex v = uiv.getVertex();
		Collection<Edge> edges = this.g.getEdges(v);
		for(Component c : this.pan.getComponents()){
			
			if(c instanceof EdgeUi){
				
				Edge e = ((EdgeUi)c).getEdge();
				if(v == e.getStart() || v == e.getEnd()){
					this.selectedEdge.remove(c);
					this.pan.remove(c);
				}
			}
			
		}
		this.pan.remove(uiv);
		this.g.removeVertex(v);
		
		
		
	}
	
	public EdgeUi addEdgeUi(InfoEdge data,VertexUI a , VertexUI b,boolean nonOriented) throws CloneNotSupportedException{
		
		Vertex start = a.getVertex();
		Vertex end = b.getVertex();
		Edge e = g.addEdge((InfoEdge)data.clone(), start, end, nonOriented);
		EdgeUi uie = new EdgeUi(e);
		this.pan.add(uie);
		return uie;
		
		
	}
	
	public void removeEdgeUi(EdgeUi uie){
		
		selectedEdge.remove(uie);
		this.pan.remove(uie);
		this.g.removeEdge(uie.getEdge());
		
		
	}
	
	
	
	
	public HashSet<VertexUI> getSelectedVertex() {
		return selectedVertex;
	}


	public HashSet<EdgeUi> getSelectedEdge() {
		return selectedEdge;
	}



	public int getNumberVertexSelected(){
		
		return this.selectedVertex.size();
	}
	
	public int getNumberEdgeSelected(){
		
		return this.selectedEdge.size();
	}
	
	public boolean selectVertex(VertexUI uiv){
		
		if(!uiv.isSelected()){
			uiv.setSelected(true);
			return	this.selectedVertex.add(uiv);
		}else{
			uiv.setSelected(false);
			return this.selectedVertex.remove(uiv);
		}
		
	}
	
	public boolean selectEdge(EdgeUi uie){
		
		if(!uie.isSelected()){
			uie.setSelected(true);
			return	this.selectedEdge.add(uie);
		}else{
			uie.setSelected(false);
			return this.selectedEdge.remove(uie);
		}
		
	}
	
	
	
	public void addPanelMouseListener(MouseListener ml){
		
		this.pan.addMouseListener(ml);
	}
	
	
	public EdgeUi findEdgeUiFromEdge(Edge e){
		
		for(Component c : this.pan.getComponents()){
			if(c instanceof EdgeUi){
				EdgeUi uie = (EdgeUi)c;
			    if(uie.getEdge() == e) return uie;
			}
		}
		return null;
	}




	

}
