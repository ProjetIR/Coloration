package Model.LayoutAlgorithm;
import java.awt.Point;


public class Particule {
	
	public Point velocity;
	public Point force;
	
	public Particule(){
		this.velocity = new Point(0,0);
		this.force = new Point(0,0);
	}

}
