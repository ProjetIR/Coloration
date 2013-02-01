package Model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;




public class InfoVertex extends Info implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point coord;
	private int rayon; // rayon
	
	
	
	public InfoVertex(Color col,Point coord,int rayon) {
		super(col);
		this.coord = coord;
		this.rayon = rayon;
		// TODO Stub du constructeur généré automatiquement
	}
	
	public int getRayon(){
		return rayon;
	}
	
	
	public Point getCoord() {
		return coord;
	}
	
	public void setCoord(Point p){
		
		this.coord = p;
		this.setChanged();
		this.notifyObservers(p);
	}

	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Stub de la méthode généré automatiquement
		InfoVertex info = new InfoVertex(this.col, (Point)this.coord.clone(), this.rayon);
		return info;
	}

	public void paint(Graphics2D g2d){
		
		g2d.setColor(this.col);
		g2d.fillOval(0,0, 2*(rayon-2), 2*(rayon-2));
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2F));
		g2d.drawOval(0, 0, 2*(rayon-2), 2*(rayon-2));
	}
	
	@Override
	public String toString() {
		return "InfoVertex [coord=" + coord + ", col=" + col + "]";
	}




	
	
	

}
