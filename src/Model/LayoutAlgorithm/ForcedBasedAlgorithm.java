package Model.LayoutAlgorithm;

import java.awt.Panel;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;

import Model.Edge;
import Model.Graphe;
import Model.Vertex;


public class ForcedBasedAlgorithm implements VisualAlgorithm{

	private Panel pan;
	public ForcedBasedAlgorithm(Panel pan) {
		super();
		this.pan = pan;
	}	
	
	

	public void place(Graphe g) {
		// TODO Stub de la méthode généré automatiquement
	HashMap<Vertex,Particule> moving = new HashMap<Vertex, Particule>();
	for(Vertex v : g.getAllVertex()){
		moving.put(v, new Particule());
	}
	
		for(Vertex v : g.getAllVertex()){
			for(Vertex u : g.getAllVertex()){
				if(v != u){
					//---------
					int vx = v.getInfo().getCoord().x;
					int vy = v.getInfo().getCoord().y;
					int ux = u.getInfo().getCoord().x;
					int uy = u.getInfo().getCoord().y;		
					double squaredDistance  = ((vx-ux)*(vx-ux)+(vy-uy)*(vy-uy));
					//----------
			         moving.get(v).force.x += this.pan.getWidth() * (vx-ux) /squaredDistance;
			         moving.get(v).force.y += this.pan.getHeight()* (vy-uy) /squaredDistance;
			         
				}// fin if
			}//fin boucle intérieure
		}// fin boucle extérieure
		for(Edge e : g.getAllEdges()){
			Vertex v = e.getStart();
			Vertex u = e.getEnd();
			//---------
			int vx = v.getInfo().getCoord().x;
			int vy = v.getInfo().getCoord().y;
			int ux = u.getInfo().getCoord().x;
			int uy = u.getInfo().getCoord().y;	
			double rapport = this.pan.getHeight()/this.pan.getWidth();
			moving.get(v).force.x += rapport*(ux - vx);
	        moving.get(v).force.y += rapport*(uy - vy);
			moving.get(u).force.x += rapport*(vx - ux);
	        moving.get(u).force.y += rapport*(vy - uy);

		}// fin boucle edge
		for(Vertex v : g.getAllVertex()){
				moving.get(v).velocity.x = (int) ((moving.get(v).velocity.x  + moving.get(v).force.x)*0.85); 
				moving.get(v).velocity.y= (int) ((moving.get(v).velocity.y + moving.get(v).force.y)*0.85);
				int vx = v.getInfo().getCoord().x;
				int vy = v.getInfo().getCoord().y;
				vx += moving.get(v).velocity.x;
				vy += moving.get(v).velocity.y;
				vx = Math.min(this.pan.getWidth() - 2*v.getInfo().getRayon(), Math.max(4*v.getInfo().getRayon(), vx));
				vy = Math.min(this.pan.getHeight() - 2*v.getInfo().getRayon(), Math.max(4*v.getInfo().getRayon(), vy));
				v.getInfo().setCoord(new Point(vx,vy));	
		}// fin boucle vertex
	
	}
	


}
