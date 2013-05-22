package UIGraph.UIModel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import Model.Vertex;

/**
 *  Classe enveloppe d'un sommet pour le mode graphique 
 * Attention le centre théorique n'est pas le même que le centre d'affichage
 * @author emmanuel
 *
 */
public class VertexUI extends Component implements Observer{


	private Vertex v;
	private boolean selected;

	public VertexUI(Vertex v){
		super();
		this.v = v;
		this.selected = false;
		int rayon = v.getInfo().getRayon();
		int x = v.getInfo().getCoord().x;
		int y = v.getInfo().getCoord().y;
		this.setSize(2*rayon,2*rayon);
		this.setLocation(x,y);
		v.getInfo().addObserver(this);
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Stub de la méthode généré automatiquement
		
		Graphics2D g2d = (Graphics2D)g;
		if(selected){
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, .6f);
	    	g2d.setComposite(c);
		}
		v.getInfo().paint(g2d);
		
	}

	public Vertex getVertex() {
		return v;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	
	public Point getRealLocation() {
		// TODO Stub de la méthode généré automatiquement
		return v.getInfo().getCoord();
	}

	@Override
	public void setLocation(int x, int y) {
		// TODO Stub de la méthode généré automatiquement
		super.setLocation(x-v.getInfo().getRayon(), y-v.getInfo().getRayon());
	}

	@Override
	public void setLocation(Point p) {
		// TODO Stub de la méthode généré automatiquement
		setLocation(p.x-v.getInfo().getRayon(), p.y-v.getInfo().getRayon());
		this.v.getInfo().setCoord(p);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.v.hashCode();
	}
	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof VertexUI)) return false;
			Vertex se = ((VertexUI)arg0).getVertex();
		return this.v.equals(se);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Stub de la méthode généré automatiquement
	
			Point p = (Point)arg;
			Point c = this.getLocation();
			if((c.x == p.x-v.getInfo().getRayon()) && (c.y == p.y-v.getInfo().getRayon())) 
				{
				//System.out.println("pas de changement");
				return;
				}
			this.setLocation(p.x,p.y);
		
			
		
	}
	
	
	

}
