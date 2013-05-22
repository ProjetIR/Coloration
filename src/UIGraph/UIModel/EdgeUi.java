package UIGraph.UIModel;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import Model.Edge;
import Utils.CompositeStroke;

/**
 * Classe enveloppe d'une arrête pour le mode graphique
 * @author KIEFFER
 *
 */
public class EdgeUi extends Component implements Observer{

	private Edge e;
	private boolean selected;

	public EdgeUi(Edge e){
		super();
		this.e = e;
		this.selected = false;
		Point p1  = e.getStart().getInfo().getCoord();
		Point p2 = e.getEnd().getInfo().getCoord();
		this.setSize(Math.max(5,Math.abs(p2.x-p1.x)), Math.max(5,Math.abs(p2.y-p1.y)));
		this.setLocation(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y));
		e.getStart().getInfo().addObserver(this);
		e.getEnd().getInfo().addObserver(this);
		this.setBackground(Color.ORANGE);
	}

	public Edge getEdge() {
		return e;
	}
	
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.e.hashCode();
	}
	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof EdgeUi)) return false;
			Edge se = ((EdgeUi)arg0).getEdge();
		return this.e.equals(se);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Stub de la méthode généré automatiquement
		Point p1  = e.getStart().getInfo().getCoord();
		int r1 = e.getStart().getInfo().getRayon();
		Point p2 = e.getEnd().getInfo().getCoord();
		int r2 = e.getEnd().getInfo().getRayon();
		Color c = e.getInfo().getCol();
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2F));
		if(selected){
			float[] dash1 = {10.0f};
			g2d.setStroke( new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, dash1, 0.0f));
			g2d.setColor(Color.BLUE);
		}
		/*
		 * Il faut éviter la division par zéro
		 * 
		 * 
		 */
		int sensX = (p1.x-p2.x)/Math.max(1,Math.abs(p1.x-p2.x));
		int sensY = (p1.y-p2.y)/Math.max(1,Math.abs(p1.y-p2.y));
		g2d.drawLine(Math.max(0, this.getWidth()*sensX) , Math.max(0,this.getHeight()*sensY) ,Math.max(0, this.getWidth()*sensX*(-1)) , Math.max(0,this.getHeight()*sensY*(-1)) );
		e.getInfo().paint(g2d);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Stub de la méthode généré automatiquement
		
		Point p1  = e.getStart().getInfo().getCoord();
		Point p2 = e.getEnd().getInfo().getCoord();
		this.setSize(Math.max(5,Math.abs(p2.x-p1.x)), Math.max(5,Math.abs(p2.y-p1.y)));
		this.setLocation(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y));
		
	}
	
	
	

}
